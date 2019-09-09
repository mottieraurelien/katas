package kata.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static java.util.Comparator.comparingInt;

public class WarCardGame {

    private static final int UNLIMITED_ROUNDS = 0;

    private final Deck deck;
    private final List<Player> players;
    private final int maxRounds;

    public WarCardGame(final Deck deck, final List<Player> players, final int maxRounds) {
        this.deck = deck;
        this.players = players;
        this.maxRounds = maxRounds;
    }

    public void start() {

        // Distributes all shuffled cards to the players :
        distributesCardsToAllPlayers();

        // Launches the battle, and start new turns as long as there are 2 remaining players at least :
        int turns = 0;
        final List<Card> cardsToWin = new ArrayList<>();
        while (this.players.size() > 1 && (turns < this.maxRounds || this.maxRounds == UNLIMITED_ROUNDS)) {

            // Lets the players play their card :
            Map<Card, Player> currentPlayedCardByPlayer = new HashMap<>();
            this.players.forEach(player -> {
                Card currentPlayedCard = player.playCard();
                cardsToWin.add(currentPlayedCard);
                currentPlayedCardByPlayer.put(currentPlayedCard, player);
            });

            // Defines if there is a winner of the current battle :
            Optional<Player> winner = defineTheWinnerFromTheCurrentPlayedCardsForThisTurn(currentPlayedCardByPlayer);

            // If there is a winner for this turn (who has a card strictly higher than the other ones) :
            winner.ifPresent(player -> {

                // The winner gets all cards (from this turn and potentially from the previous turns) :
                player.winsCards(cardsToWin);

                // Resets the cards to win for the next turn :
                cardsToWin.clear();

                // Removes all losers :
                this.players.removeIf(Player::hasNotAnyCardAnymore);

            });

            // Increases by one the number of played turns :
            turns++;

        }

        // Declares the winner (the last remaining player of the one who has the best score*) :
        // *Best score : by summing all his points.
        Player finalWinner = this.players.stream().max(comparingInt(Player::getScore)).orElse(this.players.get(0));
        System.out.println("The winner of this game is : " + finalWinner);

    }

    private void distributesCardsToAllPlayers() {
        boolean distributesAllCards = (this.deck.size() % this.players.size()) == 0;
        while ((!this.deck.isEmpty() && distributesAllCards) || (this.deck.size() > this.players.size())) {
            this.players.forEach(player -> player.receiveCard(this.deck.pop()));
        }
    }

    private Optional<Player> defineTheWinnerFromTheCurrentPlayedCardsForThisTurn(
        final Map<Card, Player> playedCardByPlayer) {

        // Assumes there is no winning player for this turn :
        Optional<Player> winningPlayer = Optional.empty();

        // Defines the winning card :
        Optional<Card> winningCard = playedCardByPlayer.keySet().stream()
            .max(comparingInt(card -> card.getFigure().getValue()));

        // If there is a winning card, identifies its owner :
        if (winningCard.isPresent()) {
            winningPlayer = Optional.of(playedCardByPlayer.get(winningCard.get()));
        }

        return winningPlayer;

    }

}