package org.leviatan.chess.ui.images;

import com.vaadin.flow.component.html.Image;
import org.leviatan.chess.board.Bando;
import org.leviatan.chess.board.Ficha;
import org.leviatan.chess.board.TipoFicha;


/**
 * ImageLoader.
 *
 * @author Alejandro
 *
 */
public final class ImageLoader {

    private static final String IMAGE_EXT = ".gif";

    private ImageLoader() {
    }

    /**
     * Devuelve la imagen de la ficha dada.
     *
     * @param ficha
     *            ficha
     * @return la imagen de la ficha dada
     */
    public static Image getImage(final Ficha ficha) {

        final String nombreArchivo = generaNombreArchivoImagen(ficha);
        return cargaImagenFicha(nombreArchivo);
    }

    /** Genera el nombre del archivo de la imagen. */
    private static String generaNombreArchivoImagen(final Ficha ficha) {

        final TipoFicha tipoFicha = ficha.getTipoFicha();
        final Bando bando = ficha.getBando();

        return generaNombreArchivoImagen(tipoFicha, bando);
    }

    /** Genera el nombre del archivo de la imagen. */
    private static String generaNombreArchivoImagen(final TipoFicha tipoFicha, final Bando bando) {

        return tipoFicha + "_" + bando + IMAGE_EXT;
    }

    private static Image cargaImagenFicha(final String nombre) {

        final String path = "images/fichas/" + nombre;
        return cargaImagen(path);
    }

    private static Image cargaImagen(final String path) {

        return new Image(path, "");
    }

    /**
     * Carga la imagen de fin de partida.
     *
     * @param nombre
     *            nombre
     * @return la imagen de fin de partida
     */
    public static Image cargaImagenFinPartida(final String nombre) {

        final String path = "images/finpartida/" + nombre;
        return cargaImagen(path);
    }
}
