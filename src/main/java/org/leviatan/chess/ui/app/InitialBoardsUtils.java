package org.leviatan.chess.ui.app;

import org.leviatan.chess.board.Bando;
import org.leviatan.chess.board.Ficha;
import org.leviatan.chess.board.Tablero;
import org.leviatan.chess.board.TipoFicha;
import org.leviatan.chess.engine.repositorios.RepositorioPosicionesTablero;

/**
 * InitialBoardsUtils.
 *
 * @author Alejandro
 *
 */
public final class InitialBoardsUtils {

    private InitialBoardsUtils() {
    }

    public static Tablero getTablero() {
        return normal();
    }

    protected static Tablero normal() {
        return new Tablero();
    }

    protected static Tablero promocionAReina() {

        final Tablero tablero = new Tablero(false);

        tablero.ponerFichaEnPosicionTablero(new Ficha(Bando.NEGRO, TipoFicha.PEON), RepositorioPosicionesTablero.getPosicionTablero(0, 1));
        tablero.ponerFichaEnPosicionTablero(new Ficha(Bando.BLANCO, TipoFicha.PEON),
                RepositorioPosicionesTablero.getPosicionTablero(0, Tablero.TALLA_TABLERO - 2));

        return tablero;
    }
}
