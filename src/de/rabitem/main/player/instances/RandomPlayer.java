package de.rabitem.main.player.instances;

import de.rabitem.main.Main;
import de.rabitem.main.card.instances.PlayerCard;
import de.rabitem.main.exception.IllegalMoveException;
import de.rabitem.main.player.Player;
import de.rabitem.main.util.Util;

/**
 * @author Felix Huisinga
 */
public class RandomPlayer extends Player {
    /**
     * Constructor of Player1
     * @param name String name
     */
    public RandomPlayer(final String name) {
        super(name);
    }

    @Override
    public PlayerCard getNextCard() {
        final PlayerCard pC = cards.get(Util.random(0, cards.size() - 1));
        setLastMove(pC);
        try {
            remove(pC);
        }catch (IllegalMoveException e) {
            System.out.println(e.getMessage());
        }
        return lastMove;
    }
}
