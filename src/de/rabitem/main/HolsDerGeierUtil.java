package de.rabitem.main;

import de.rabitem.main.card.instances.PointsCard;
import de.rabitem.main.exception.IllegalMatchSetup;
import de.rabitem.main.player.Player;
import de.rabitem.main.util.Util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Felix Huisinga
 */
public class HolsDerGeierUtil extends HolsDerGeier {

    /**
     * Activates a Player
     *
     * @param p Player p
     */
    protected void activatePlayer(final Player p) {
        p.fillArraylist(Main.getMain().from, Main.getMain().to);
        getActivePlayers().add(p);
    }

    /**
     * Returns active Players
     *
     * @return int activePlayers
     */
    protected int getActivePlayersSize() {
        return activePlayer.size();
    }


    /**
     * Method to get the next PointCard
     *
     * @return
     */
    protected PointsCard getNextPointCard() {
        PointsCard retPointsCard = pointCards.get(Util.random(0, pointCards.size() - 1));
        pointCards.remove(retPointsCard);
        return retPointsCard;
    }

    /**
     * Method to check if Game is running
     *
     * @return boolean finishedSetup
     */
    public boolean finishedSetup(final HolsDerGeier holsDerGeier) {
        return holsDerGeier.finishedSetup;
    }

    /**
     * Method to get all played PointCards
     *
     * @return List<PointsCard>
     */
    public static List<PointsCard> getPointCards() {
        return pointCards;
    }

    /**
     * Method to get all active player
     *
     * @return ArrayList<Player>
     */
    //TODO: rather use a interface such as List, refactor later!
    public static ArrayList<Player> getActivePlayers() {
        return activePlayer;
    }

    /**
     * Method to get fromCards Integer
     *
     * @return int fromCards
     */
    public int fromCards() {
        return this.from;
    }

    /**
     * Method to get toCards Integer
     *
     * @return int toCards
     */
    public int toCards() {
        return this.to;
    }

    /**
     * Returns the Winner of last round
     * null if no winner
     *
     * @return Player getWinner
     */
    public Player getWinner() {
        return this.winningPlayer;
    }

    /**
     * This method is used to Map the Player places after each round
     * @param hashMap
     */
    public void mapPlayerPlaces(final HashMap<Player, Integer> hashMap) {
        ArrayList<Integer> finalPoints = new ArrayList<>();
        hashMap.forEach((k, v) -> {
            finalPoints.add(v);
        });
        AtomicInteger i = new AtomicInteger();
        for (Integer pPoints : Util.bubbleSort(finalPoints)) {
            hashMap.forEach((k, v) -> {
                if (pPoints == k.getPoints()) {
                    k.setPlaceLastRound(finalPoints.size() - i.get());
                    if (k.getPlaceLastRound() == 1) {
                        k.incWinCounter();
                    }
                }
            });
            i.getAndIncrement();
        }
    }

    /**
     * This method is used to Map the Player places after each round or at the end
     * @param hashMap
     */
    public void mapPlayerPlaces(final HashMap<Player, Integer> hashMap, boolean totalPlaces) {
        ArrayList<Integer> finalPoints = new ArrayList<>();
        hashMap.forEach((k, v) -> {
            finalPoints.add(v);
        });
        AtomicInteger i = new AtomicInteger();
        for (Integer pPoints : Util.bubbleSort(finalPoints)) {
            hashMap.forEach((k, v) -> {
                if (totalPlaces) {
                    if (pPoints == k.getTotalPoints()) {
                        k.setPlaceLastRound(finalPoints.size() - i.get());
                    }
                } else {
                    if (pPoints == k.getPoints()) {
                        k.setPlaceLastRound(finalPoints.size() - i.get());
                        if (k.getPlaceLastRound() == 1) {
                            k.incWinCounter();
                        }
                    }
                }
            });
            i.getAndIncrement();
        }
    }

    @Override
    public void onSetupFinished() {
        this.onSetupFinished();
    }

    public void setCardRange(final int from, final int to) throws IllegalMatchSetup {
        if ((to-from+1)%3 != 0 || (to-from) < 0) {
            throw new IllegalMatchSetup("Illegal Card range!");
        }
        Main.getMain().pointCardsFrom = from;
        Main.getMain().pointCardsTo = to;

        Main.getMain().from = -(Main.getMain().pointCardsTo/3);
        Main.getMain().to = Main.getMain().pointCardsTo + Main.getMain().from;
    }
}
