package org.leviatan.chess.ui.images;

import java.util.Random;

import com.vaadin.flow.component.html.Image;

/**
 * CargadorImagenesFinPartida.
 *
 * @author Alejandro
 */
public final class CargadorImagenesFinPartida {

    private CargadorImagenesFinPartida() {
    }

    /** NUMERO_ICONOS_VICTORIA. */
    public static final int NUMERO_ICONOS_VICTORIA = 3;

    /** NUMERO_ICONOS_EMPATE. */
    public static final int NUMERO_ICONOS_EMPATE = 2;

    /** NUMERO_ICONOS_DERROTA. */
    public static final int NUMERO_ICONOS_DERROTA = 5;

    private static final String PATH_ICONOS_FINPARTIDA = "finpartida";
    private static final String CADENA_REPLACE_NUMERO = "#";

    /** PATH_FICHERO_VICTORIA. */
    public static final String PATH_FICHERO_VICTORIA = PATH_ICONOS_FINPARTIDA + "/victoria" + CADENA_REPLACE_NUMERO + ".gif";

    /** PATH_FICHERO_EMPATE. */
    public static final String PATH_FICHERO_EMPATE = PATH_ICONOS_FINPARTIDA + "/empate" + CADENA_REPLACE_NUMERO + ".gif";

    /** PATH_FICHERO_DERROTA. */
    public static final String PATH_FICHERO_DERROTA = PATH_ICONOS_FINPARTIDA + "/derrota" + CADENA_REPLACE_NUMERO + ".gif";

    /**
     * Carga imagen.
     *
     * @param finPartida
     *            finPartida
     * @return Image
     */
    public static Image cargaImagen(final FinPartida finPartida) {

        final int finPartidaNumeroIconos = finPartida.getNumeroIconos();
        final String finPartidaPath = finPartida.getPath();

        final Integer numeroAleatorio = obtenerNumeroAleatorio(finPartidaNumeroIconos);

        return ImageLoader.cargaImagenFinPartida(finPartidaPath.replace(CADENA_REPLACE_NUMERO, numeroAleatorio.toString()));
    }

    private static int obtenerNumeroAleatorio(final int limiteSuperiorExcluido) {

        final Random random = new Random();
        final int numeroAleatorio = Math.abs(random.nextInt()) % limiteSuperiorExcluido;
        return numeroAleatorio;
    }
}
