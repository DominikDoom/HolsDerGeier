package de.rabitem.main.player;

import de.rabitem.main.card.instances.PlayerCard;
import de.rabitem.main.exception.IllegalMoveException;

public interface PlayerPlayables {
    /**
     * Next card
     * @return PlayerCard
     */
    public abstract PlayerCard getNextCard();
}
