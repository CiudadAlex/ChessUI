package org.leviatan.chess.ui.framework.utils;

public class StyleUtils {

    public static int getSideCasilla() {
        int windowHeight = 700;
        final int side = (int) (windowHeight * 0.95);
        final int sideCasilla = side / 8;
        return sideCasilla;
    }
}
