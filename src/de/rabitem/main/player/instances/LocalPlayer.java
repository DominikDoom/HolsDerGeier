package de.rabitem.main.player.instances;

import de.rabitem.main.card.instances.PlayerCard;
import de.rabitem.main.exception.IllegalMoveException;
import de.rabitem.main.player.Player;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class LocalPlayer extends Player {

    /**
     * Constructor of LocalPlayer
     * @param name String name
     */
    public LocalPlayer(final String name) {
        super(name);
    }

    @Override
    public PlayerCard getNextCard() {
        try {
            System.out.print("Your Cards left to play: ");
            this.cards.forEach(k -> System.out.print(k.getValue() + " "));
            System.out.print("\nYour card: ");
            final PlayerCard pC =  new PlayerCard(Integer.parseInt(new BufferedReader(new InputStreamReader(System.in)).readLine()));
            setLastMove(pC);
            remove(pC);
            return pC;
        }catch (IOException | IllegalMoveException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}
