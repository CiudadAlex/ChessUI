package org.leviatan.chess.ui;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ChessApplicationStarter {
    public static void main(String[] args) {
        SpringApplication.run(ChessApplicationStarter.class, args);
        System.out.println("Start with >>>>>>>>>>>>>>>>>>>    http://127.0.0.1:8080/board");
        System.out.println("Start with >>>>>>>>>>>>>>>>>>>    http://127.0.0.1:8080/historicmatch");
    }
}

// FIXME - - - - probar reinforcement learning con solo 2 pasos

