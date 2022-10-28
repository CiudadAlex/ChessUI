package org.leviatan.chess.ui.app;

import java.util.List;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.HasValue;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.page.Page;
import com.vaadin.flow.component.radiobutton.RadioButtonGroup;
import com.vaadin.flow.router.Route;
import org.leviatan.chess.board.Bando;
import org.leviatan.chess.board.Casilla;
import org.leviatan.chess.board.Ficha;
import org.leviatan.chess.board.Movimiento;
import org.leviatan.chess.board.Tablero;
import org.leviatan.chess.board.TipoCasilla;
import org.leviatan.chess.engine.CPUPlayer;
import org.leviatan.chess.engine.CPUPlayerHeuristicDecisionTreeImpl;
import org.leviatan.chess.engine.intel.deeplearning.CPUPlayerDeepLearningImpl;
import org.leviatan.chess.engine.intel.generadorarbol.HelperMovimientosPosibles;
import org.leviatan.chess.ui.UserIntefaceInteractor;
import org.leviatan.chess.ui.framework.constantes.InteligenciaCPU;
import org.leviatan.chess.ui.framework.utils.StyleUtils;


/**
 * TestView.
 *
 * @author alciucam
 *
 */
@Route
public class BoardView extends VerticalLayout {

    private static final long serialVersionUID = 8651722294068587350L;

    private final VerticalLayout vertBoard = new VerticalLayout();
    private Tablero tablero = InitialBoardsUtils.getTablero();

    private CasillaComponent selectedCasilla;

    private final UserIntefaceInteractor userIntefaceInteractor;
    private final Label lastMovement = new Label("");
    private final RadioButtonGroup<String> radioGroupBando = new RadioButtonGroup<String>("Usted juega con el bando:");
    private final RadioButtonGroup<String> radioGroupInteligencia = new RadioButtonGroup<String>("Inteligencia CPU:");

    /** Constructor for BoardView. */
    public BoardView() {

        this.userIntefaceInteractor = buildUserIntefaceInteractor();
        this.lastMovement.setText("Último movimiento CPU");

        final Component monitor = buildMonitor();

        final HorizontalLayout root = new HorizontalLayout();
        root.add(this.vertBoard, monitor);

        add(root);

        repaintGridBoard();
    }

    private void repaintGridBoard() {

        this.vertBoard.removeAll();
        this.vertBoard.setSpacing(false);

        final int sideCasilla = StyleUtils.getSideCasilla();

        for (int row = 7; row >= 0; row--) {

            final HorizontalLayout horizBoard = new HorizontalLayout();
            horizBoard.setSpacing(false);

            for (int col = 0; col < 8; col++) {

                final Casilla casilla = this.tablero.getCasilla(col, row);
                final TipoCasilla tipoCasilla = casilla.getTipoCasilla();
                final Ficha ficha = casilla.getFicha();

                final Component casillaTablero = new CasillaComponent(col, row, tipoCasilla, ficha, sideCasilla, false, this::onSelect);
                horizBoard.add(casillaTablero);
            }

            this.vertBoard.add(horizBoard);
        }
    }

    private Void onSelect(final CasillaComponent casillaComponent) {

        if (this.selectedCasilla == null) {
            this.selectedCasilla = casillaComponent;
            this.selectedCasilla.setSelected(true);

        } else {
            this.selectedCasilla.setSelected(false);
            tryMovement(this.selectedCasilla, casillaComponent);
            this.selectedCasilla = null;
        }

        return null;
    }

    private void tryMovement(final CasillaComponent c1, final CasillaComponent c2) {

        final Ficha ficha = c1.getFicha();

        if (ficha == null) {
            return;
        }

        final Bando bando = ficha.getBando();
        final Bando bandoHumano = getBandoHumano();

        if (!bandoHumano.equals(bando)) {
            // Solo se puede jugar con el bando seleccionado
            return;
        }

        final List<Movimiento> listMovimiento = HelperMovimientosPosibles.getMovimientosPosiblesDeBando(this.tablero, bando);

        for (final Movimiento movimiento : listMovimiento) {

            if (c1.esPosicionTablero(movimiento.getPosicionOrigen()) && c2.esPosicionTablero(movimiento.getPosicionDestino())) {
                realizarMovimientoYMoverCPU(movimiento);
                return;
            }
        }
    }

    private void realizarMovimientoYMoverCPU(final Movimiento movimiento) {

        this.tablero.realizarMovimiento(movimiento);

        moverCPU();
    }

    private void moverCPU() {

        try {

            final Bando bandoHumano = getBandoHumano();
            final Bando bandoCPU = bandoHumano.getBandoOpuesto();

            final String strInteligencia = this.radioGroupInteligencia.getValue().toString();
            final InteligenciaCPU inteligenciaCPU = InteligenciaCPU.getInteligenciaCPUByName(strInteligencia);

            CPUPlayer cpuPlayer = null;

            if (InteligenciaCPU.DECISSION_TREE_HEURISTIC.equals(inteligenciaCPU)) {
                cpuPlayer = new CPUPlayerHeuristicDecisionTreeImpl();

            } else if (InteligenciaCPU.DEEP_LEARNING_MODEL.equals(inteligenciaCPU)) {
                cpuPlayer = new CPUPlayerDeepLearningImpl();
            }

            this.tablero = cpuPlayer.realizarJugadaCPU(this.tablero, this.userIntefaceInteractor, bandoCPU);

            repaintGridBoard();

        } catch (final Exception e) {
            e.printStackTrace();
        }
    }

    private Bando getBandoHumano() {

        final String strBando = this.radioGroupBando.getValue().toString();
        final Bando bandoHumano = Bando.getBandoByName(strBando);
        return bandoHumano;
    }

    private Component buildMonitor() {

        final VerticalLayout monitor = new VerticalLayout();

        this.radioGroupBando.setItems(Bando.BLANCO.name(), Bando.NEGRO.name());
        this.radioGroupBando.setValue(Bando.BLANCO.name());
        this.radioGroupBando.addValueChangeListener(e -> moverCPU());

        this.radioGroupInteligencia.setItems(InteligenciaCPU.DECISSION_TREE_HEURISTIC.name(), InteligenciaCPU.DEEP_LEARNING_MODEL.name());
        this.radioGroupInteligencia.setValue(InteligenciaCPU.DECISSION_TREE_HEURISTIC.name());

        monitor.add(this.radioGroupBando, this.radioGroupInteligencia, this.lastMovement);

        return monitor;
    }

    private UserIntefaceInteractor buildUserIntefaceInteractor() {

        return new UserIntefaceInteractor() {

            @Override
            public void mostrarMensaje(final String text) {
                Notification.show(text);
            }

            @Override
            public void mostrarMensajeVictoriaCPU() {
                Notification.show("Lo siento, ha ganado la CPU");
            }

            @Override
            public void mostrarMensajeVictoriaHumano() {
                Notification.show("Enhorabuena!! Has ganado!!");
            }

            @Override
            public void mostrarMensajeEmpate() {
                Notification.show("Tablas");
            }

            @Override
            public void inicioCalculos() {
                // De momento nada
            }

            @Override
            public void mostrarProgreso(final double progresado) {
                // De momento nada
            }

            @Override
            public void finCalculos(final String textoJugada) {
                BoardView.this.lastMovement.setText(textoJugada);
            }
        };
    }

}
