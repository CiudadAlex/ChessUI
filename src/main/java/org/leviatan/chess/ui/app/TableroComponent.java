package org.leviatan.chess.ui.app;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import org.leviatan.chess.board.Casilla;
import org.leviatan.chess.board.Ficha;
import org.leviatan.chess.board.Tablero;
import org.leviatan.chess.board.TipoCasilla;
import org.leviatan.chess.ui.framework.utils.StyleUtils;

import java.util.function.Function;

public class TableroComponent extends VerticalLayout {

    public TableroComponent(Tablero tablero, final Function<CasillaComponent, Void> onSelect) {

        this.setSpacing(false);
        this.setMargin(false);

        final int sideCasilla = StyleUtils.getSideCasilla();

        for (int row = 7; row >= 0; row--) {

            final HorizontalLayout horizBoard = new HorizontalLayout();
            horizBoard.setSpacing(false);
            horizBoard.setMargin(false);

            for (int col = 0; col < 8; col++) {

                final Casilla casilla = tablero.getCasilla(col, row);
                final TipoCasilla tipoCasilla = casilla.getTipoCasilla();
                final Ficha ficha = casilla.getFicha();

                final Component casillaTablero = new CasillaComponent(col, row, tipoCasilla, ficha, sideCasilla, false, onSelect);
                horizBoard.add(casillaTablero);
            }

            this.add(horizBoard);
        }
    }
}
