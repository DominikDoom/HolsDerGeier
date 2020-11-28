package de.rabitem.main;

import de.rabitem.main.card.instances.PointsCard;
import de.rabitem.main.player.Player;
import de.rabitem.main.util.Util;

/**
 * @author Felix Huisinga
 */
public class HolsDerGeierUtil extends HolsDerGeier{

    /**
     * Activates a Player
     * @param p Player p
     */
    protected void activatePlayer(final Player p) {
        p.fillArraylist(from, to);
        activePlayer.add(p);
    }

    /**
     * Returns active Players
     * @return int activePlayers
     */
    protected int getActivePlayers() {
        return activePlayer.size();
    }

    protected PointsCard getNextPointCard(){
        PointsCard retPointsCard = pointCards.get(Util.random(0, pointCards.size() -1));
        pointCards.remove(retPointsCard);
        return retPointsCard;
    }
}
