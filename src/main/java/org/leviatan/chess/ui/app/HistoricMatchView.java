package org.leviatan.chess.ui.app;

import java.util.Iterator;
import java.util.List;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import org.leviatan.chess.board.Movimiento;
import org.leviatan.chess.board.Tablero;
import org.leviatan.chess.data.pgn.PGNReaderManager;
import org.leviatan.chess.data.pgn.Partida;


/**
 * HistoricMatchView.
 *
 * @author Alejandro
 *
 */
@Route
public class HistoricMatchView extends VerticalLayout {

    private static final long serialVersionUID = 8651722294068587350L;

    private final VerticalLayout vertBoard = new VerticalLayout();
    private Tablero tablero = InitialBoardsUtils.normal();

    private List<Partida> listPartida;
    private Iterator<Movimiento> iteratorPartida;

    private int numFile;
    private int numPartidaInFile;

    private final TextField tfNumFile = new TextField("Número de fichero");
    private final TextField tfNumPartidaInFile = new TextField("Número de partida");

    /** Constructor for HistoricMatchView. */
    public HistoricMatchView() {

        final HorizontalLayout root = new HorizontalLayout();
        root.add(this.vertBoard);

        final Component layoutPartidaLoader = buildPartidaLoader();
        root.add(layoutPartidaLoader);

        add(root);

        this.numFile = 0;
        this.numPartidaInFile = 0;
        this.tfNumFile.setValue("0");
        this.tfNumPartidaInFile.setValue("0");

        repaintGridBoard();
    }

    private void repaintGridBoard() {

        this.vertBoard.removeAll();

        TableroComponent tableroComponent = new TableroComponent(this.tablero, cc -> null);
        this.vertBoard.add(tableroComponent);
    }

    private Component buildPartidaLoader() {

        final VerticalLayout layoutPartidaLoader = new VerticalLayout();
        layoutPartidaLoader.add(this.tfNumFile, this.tfNumPartidaInFile);

        final Button botonNext = new Button("Siguiente", e -> {

            try {
                accionButtonNext();

            } catch (final Exception ex) {
                Notification.show("Error en la carga de la partida");
                ex.printStackTrace();
            }
        });

        layoutPartidaLoader.add(botonNext);

        return layoutPartidaLoader;
    }

    private void accionButtonNext() throws Exception {

        final int auxNumFile = Integer.parseInt(this.tfNumFile.getValue());
        final int auxNumPartidaInFile = Integer.parseInt(this.tfNumPartidaInFile.getValue());

        if (this.listPartida == null || auxNumFile != this.numFile) {

            this.numFile = auxNumFile;
            this.listPartida = PGNReaderManager.getPartidasDelFichero(this.numFile);

            if (this.listPartida.isEmpty()) {
                this.listPartida = null;
                Notification.show("No existe el fichero seleccionado");
                return;
            }

            this.tablero = InitialBoardsUtils.normal();
        }

        if (auxNumPartidaInFile != this.numPartidaInFile || this.iteratorPartida == null) {

            this.numPartidaInFile = auxNumPartidaInFile;

            if (this.numPartidaInFile >= this.listPartida.size() || this.numPartidaInFile < 0) {
                Notification.show("No existe esa partida en el fichero seleccionado");
                return;
            }

            final Partida partida = this.listPartida.get(this.numPartidaInFile);
            this.iteratorPartida = partida.getIteratorMovimiento();

            this.tablero = InitialBoardsUtils.normal();
        }

        if (this.iteratorPartida.hasNext()) {

            final Movimiento movimiento = this.iteratorPartida.next();
            this.tablero.realizarMovimiento(movimiento);

            repaintGridBoard();

        } else {
            Notification.show("La partida ha finalizado");
        }

    }


}
