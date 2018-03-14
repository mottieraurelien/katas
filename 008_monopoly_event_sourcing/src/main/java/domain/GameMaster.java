package domain;

import exception.IncorrectNumberOfRoundsException;

import java.util.stream.IntStream;

class GameMaster {

    private final Game game;

    GameMaster(final Game game) {
        this.game = game;
    }

    void organize(final Integer numberOfRoundsToPlay) {

        if (numberOfRoundsToPlay == null || numberOfRoundsToPlay < 1) {
            throw new IncorrectNumberOfRoundsException("Une partie doit faire au moins un tour.");
        }

        IntStream.iterate(1, n -> n++).limit(numberOfRoundsToPlay).forEach(currentRound -> {
            this.game.playRound();
        });

    }

}
