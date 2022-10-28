package org.leviatan.chess.ui.framework.utils.images;

/**
 * FinPartida.
 *
 * @author Alejandro
 */
public enum FinPartida {

    /** VICTORIA_HUMANA. */
    VICTORIA_HUMANA(CargadorImagenesFinPartida.NUMERO_ICONOS_VICTORIA, CargadorImagenesFinPartida.PATH_FICHERO_VICTORIA,
            "¡¡Enhorabuena!! ¡¡Has ganado!!"),

    /** EMPATE. */
    EMPATE(CargadorImagenesFinPartida.NUMERO_ICONOS_EMPATE, CargadorImagenesFinPartida.PATH_FICHERO_EMPATE,
            "Supongo que esto son tablas entonces..."),

    /** DERROTA_HUMANA. */
    DERROTA_HUMANA(CargadorImagenesFinPartida.NUMERO_ICONOS_DERROTA, CargadorImagenesFinPartida.PATH_FICHERO_DERROTA,
            "Lo siento... He ganado...");

    private int numeroIconos;
    private String path;
    private String mensaje;

    private FinPartida(final int numeroIconos, final String path, final String mensaje) {
        this.numeroIconos = numeroIconos;
        this.path = path;
        this.mensaje = mensaje;
    }

    public int getNumeroIconos() {
        return numeroIconos;
    }

    public String getPath() {
        return path;
    }

    public String getMensaje() {
        return mensaje;
    }
}
