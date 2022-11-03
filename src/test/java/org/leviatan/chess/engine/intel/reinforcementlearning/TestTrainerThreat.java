package org.leviatan.chess.engine.intel.reinforcementlearning;

import org.leviatan.chess.board.Bando;
import org.leviatan.chess.board.Tablero;
import org.leviatan.chess.engine.deeplearning.ConfiguracionDeepLearning;
import org.leviatan.chess.engine.intel.reinforcementlearning.training.TrainerThreat;
import org.leviatan.chess.ui.UserIntefaceInteractor;
import org.leviatan.chess.ui.app.InitialBoardsUtils;

public final class TestTrainerThreat {

    private TestTrainerThreat() {
    }

    /**
     * Main method.
     *
     * @param args
     *            args
     * @throws Exception
     */
    public static void main(final String[] args) throws Exception {

        trainReinforcementLearning();
        moveReinforcementLearning();
    }

    public static void trainReinforcementLearning() throws Exception {
        TrainerThreat.trainListPartidasDeFichero(ConfiguracionDeepLearning.DIR_MODELOS, 0, true);
    }

    public static void moveReinforcementLearning() throws Exception {

        CPUPlayerReinforcementLearningImpl cpuPlayer = new CPUPlayerReinforcementLearningImpl();
        Tablero tablero = InitialBoardsUtils.getTablero();
        UserIntefaceInteractor userIntefaceInteractor = buildUserIntefaceInteractor();
        cpuPlayer.realizarJugadaCPU(tablero, userIntefaceInteractor, Bando.BLANCO);
    }

    private static UserIntefaceInteractor buildUserIntefaceInteractor() {

        return new UserIntefaceInteractor() {

            @Override
            public void mostrarMensaje(final String text) {
                System.out.println(text);
            }

            @Override
            public void mostrarMensajeVictoriaCPU() {
                System.out.println("Lo siento, ha ganado la CPU");
            }

            @Override
            public void mostrarMensajeVictoriaHumano() {
                System.out.println("Enhorabuena!! Has ganado!!");
            }

            @Override
            public void mostrarMensajeEmpate() {
                System.out.println("Tablas");
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
                System.out.println(textoJugada);
            }
        };
    }


}
