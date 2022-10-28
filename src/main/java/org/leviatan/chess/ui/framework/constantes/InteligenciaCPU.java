package org.leviatan.chess.ui.framework.constantes;

/**
 * InteligenciaCPU.
 *
 * @author Alejandro
 *
 */
public enum InteligenciaCPU {

    /** DECISSION_TREE_HEURISTIC. */
    DECISSION_TREE_HEURISTIC,

    /** DEEP_LEARNING_MODEL. */
    DEEP_LEARNING_MODEL;

    /**
     * Devuelve la InteligenciaCPU por el nombre.
     *
     * @param nombreInteligenciaCPU
     *            nombreInteligenciaCPU
     * @return la InteligenciaCPU por el nombre
     */
    public static InteligenciaCPU getInteligenciaCPUByName(final String nombreInteligenciaCPU) {

        for (final InteligenciaCPU inteligenciaCPU : values()) {

            if (inteligenciaCPU.name().equals(nombreInteligenciaCPU)) {
                return inteligenciaCPU;
            }
        }

        return null;
    }
}
