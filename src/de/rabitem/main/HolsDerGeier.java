package de.rabitem.main;

import de.rabitem.main.card.instances.PlayerCard;
import de.rabitem.main.card.instances.PointsCard;
import de.rabitem.main.exception.IllegalMatchSetup;
import de.rabitem.main.exception.IllegalPlayerSize;
import de.rabitem.main.player.Player;
import de.rabitem.main.util.Util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author Felix Huisinga
 */
public abstract class HolsDerGeier {
    protected static boolean isRunning;

    protected final int from = 1;
    protected final int to = 15;

    protected final int pointCardsFrom = -5;
    protected final int pointCardsTo = 10;

    protected int rounds;

    private HolsDerGeierUtil holsDerGeierUtil = null;

    /**
     * Points, to which is played
     */
    private PointsCard currentPointCard = new PointsCard(0);
    private PointsCard lastRoundPointCard = new PointsCard(0);

    public static ArrayList<Player> activePlayer = new ArrayList<>();

    /**
     * Arraylist which contains all cards (range: -5 --> 10)
     */
    protected static List<PointsCard> pointCards = new ArrayList<>();

    /**
     * Constructor for HolsDerGeier class
     */
    public HolsDerGeier() {
    }

    /**
     * Game running related methods:
     */

    /**
     * Resets the Game
     */
    public void reset() {
        /**
         * Clear each active Player
         */
        for (Player p : activePlayer) {
            p.reset();
        }
        activePlayer.clear();

        /**
         * Clear PointCards List
         */
        pointCards.clear();
    }

    /**
     * Starts the game engine
     */
    public void startGame() throws IllegalPlayerSize, IllegalMatchSetup {
        /**
         * Error handling: check if setup is correct
         */
        if (Main.getPlayerManager().getPlayerCount() < 2) {
            throw new IllegalPlayerSize("Invalid number of players (" + activePlayer.size() + ")! The game could not be started.");
        } else if (Main.getPlayerManager().getPlayerCount() > 2) {
            System.out.println("Warning, " + Main.getPlayerManager().getPlayerCount() + " Players! Some Bots might not work...");
        }
        for (Player p : activePlayer) {
            if (!p.isReady())
                throw new IllegalMatchSetup("The game was not set up correctly!");
        }

        System.out.println("Match setup successful! Starting Game...");
        runGame();
    }

    /**
     * Runs the game
     */
    private void runGame() {
        isRunning = true;
        // fill cards
        for (int i = pointCardsFrom; i <= pointCardsTo; i++) {
            if (i == 0)
                continue;
            pointCards.add(new PointsCard(i));
        }
        holsDerGeierUtil = Main.getHolsDerGeierUtil();

        // rounds to play
        rounds = pointCards.size();

        // current round
        int currentRound = 0;

        // values of each player
        ArrayList<Integer> values = new ArrayList<>();

        // winners of the round
        ArrayList<Player> winner = new ArrayList<>();

        // winning card value
        int winningValue;

        HashMap<Player, PlayerCard> usedCards = new HashMap<Player, PlayerCard>();

        System.out.println("We are about to play: " + rounds + " rounds with " + holsDerGeierUtil.getActivePlayers() + " Players!");
        System.out.println();
        /**
         * Loop until there are no PointCards left
         */
        while (!pointCards.isEmpty()) {
            currentRound++;
            /**
             * Setup PointCard for this round
             */
            currentPointCard = holsDerGeierUtil.getNextPointCard();

            System.out.println("-----------------");
            if (currentPointCard.isMouseCard())
                System.out.println("Who is going to win " + currentPointCard.getValue() + " Points?");
            else
                System.out.println("Who is going to lose " + currentPointCard.getValue() * (-1) + " Points?");

            for (Player p : activePlayer) {
                PlayerCard value = p.getNextCard(currentPointCard.getValue());
                values.add(value.getValue());
                usedCards.put(p, value);
            }

            usedCards.forEach((k, v) -> {
                System.out.printf("Player: %s, Card: %d%n", k.getName(), v.getValue());
            });

            winningValue = Util.getHighestValue(values);
            System.out.println("Winning Value: " + winningValue);

            for (Player p : activePlayer) {
                if (currentPointCard.isMouseCard()) {
                    if (p.getLastMove().getValue() == winningValue) {
                        winner.add(p);
                    }
                } else {
                    if (p.getLastMove().getValue() != winningValue) {
                        winner.add(p);
                    }
                }
            }

            if (winner.size() > 1) {
                if (winner.size() != activePlayer.size()) {
                    if (!currentPointCard.isMouseCard()) {
                        ArrayList<Integer> drawValues = new ArrayList<>();
                        for (Player p : activePlayer) {
                            if (!winner.contains(p)){
                                drawValues.add(p.getLastMove().getValue());
                            }
                        }
                        for(Player p : activePlayer) {
                            if (Util.getLowestValue(drawValues) == p.getLastMove().getValue() && !winner.contains(p)){
                                p.addPoints(currentPointCard.addValue(lastRoundPointCard).getValue());
                                drawValues.clear();
                            }
                        }
                    } else {
                        System.out.println("Draw! The points are redirected to the upcoming round.");
                        lastRoundPointCard.setValue(lastRoundPointCard.getValue() + currentPointCard.getValue());
                    }
                } else {
                    System.out.println("Draw! The points are redirected to the upcoming round.");
                    lastRoundPointCard.setValue(lastRoundPointCard.getValue() + currentPointCard.getValue());
                }
            } else {
                if (!currentPointCard.isMouseCard()) {
                    for (Player p : activePlayer) {
                        if (!winner.contains(p)) {
                            p.addPoints(currentPointCard.addValue(lastRoundPointCard).getValue());
                        }
                    }
                } else {
                    winner.get(0).addPoints(currentPointCard.addValue(lastRoundPointCard).getValue());
                }

                System.out.println("The value was: " + winningValue + ". ");

                lastRoundPointCard = new PointsCard(0);
            }
            activePlayer.forEach(k1 -> System.out.println(k1.getName() + " : " + k1.getPoints()));
            System.out.println();

            /**
             * Diese Sektion ggf. auslagern!
             */
            winningValue = 0;
            winner.clear();
            values.clear();
            usedCards.clear();
        }

        System.out.println("Game over!");
        Player mostPointsPlayer = activePlayer.get(0);
        for (Player p : activePlayer) {
            if (p.getPoints() > mostPointsPlayer.getPoints()) { // Unentschieden möglich?
                mostPointsPlayer = p;
            }
            System.out.println(p.getName() + ": " + p.getPoints());
        }
        System.out.println("Winner is: " + mostPointsPlayer.getName());

        isRunning = false;
    }
}
