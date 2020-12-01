package de.rabitem.main;

import de.rabitem.main.card.instances.PointsCard;
import de.rabitem.main.player.Player;
import de.rabitem.main.util.Util;

import java.util.ArrayList;
import java.util.List;

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
        p.fillArraylist(from, to);
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
     * @return
     */
    protected PointsCard getNextPointCard() {
        PointsCard retPointsCard = pointCards.get(Util.random(0, pointCards.size() - 1));
        pointCards.remove(retPointsCard);
        return retPointsCard;
    }

    /**
     * Method to check if Game is running
     * @return boolean isRunning
     */
    public static boolean isRunning() {
        return isRunning;
    }

    /**
     * Method to get all played PointCards
     * @return List<PointsCard>
     */
    public static List<PointsCard> getPointCards() {
        return pointCards;
    }

    /**
     * Method to get all active player
     * @return ArrayList<Player>
     */
    //TODO: rather use a interface such as List, refactor later!
    public static ArrayList<Player> getActivePlayers() {
        return activePlayer;
    }
}
