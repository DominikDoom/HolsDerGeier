package de.rabitem.main.player;

import de.rabitem.main.Main;
import de.rabitem.main.card.instances.PlayerCard;
import de.rabitem.main.exception.IllegalMoveException;
import de.rabitem.main.util.Util;

import java.util.ArrayList;

/**
 * @author Felix Huisinga
 */
public abstract class Player implements PlayerPlayables{
    private static int count = 0;
    protected int id;
    protected String name;
    protected PlayerCard lastMove = null;
    private int points;

    /**
     * Constructor of Player
     * @param name String name
     */
    public Player(final String name) {
        this.id = count + 1;
        count++;
        this.name = name;
        Main.getPlayerManager().registerPlayer(this);
    }

    protected void setName(String name) {
        this.name = name;
    }

    protected void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public int getId() {
        return this.id;
    }

    @Override
    public String toString() {
        return "Spieler: " + this.name + "(Id: " + this.id + ")";
    }

    /**
     * Arraylist which contains the Cards
     */
    protected final ArrayList<PlayerCard> cards = new ArrayList<>();

    /**
     * Fills Arraylist with Cards
     * @param from int from
     * @param to int to
     */
    public final void fillArraylist(final int from, final int to) {
        for (int i = from; i <= to; i++) {
            cards.add(new PlayerCard(i));
        }
    }

    /**
     * Player can use card? Still in available card?
     * @param c PlayerCard c
     * @return
     */
    public boolean canUse(final PlayerCard c) {
        return cards.contains(c);
    }

    /**
     * This method is used to remove a PlayerCard
     * and checks if it can be used
     * @param c PlayerCard c
     */
    protected final void remove(final PlayerCard c) throws IllegalMoveException{
        if (canUse(c)) {
            cards.remove(c);
            lastMove = c;
        } else{
            throw new IllegalMoveException("This move is not permitted! You already used this card...");
        }

    }

    /**
     * Resets the Player
     */
    public void reset() {
        cards.clear();
        lastMove = null;
    }

    /**
     * Returns last move
     * null if not available
     * @return getLastMove
     */
    public final PlayerCard getLastMove() {
        return lastMove;
    }

    /**
     * Sets the last move executed
     * @param lastMove
     */
    protected final void setLastMove(PlayerCard lastMove) {
        this.lastMove = lastMove;
    }

    /**
     * Determs if the Player is ready to play
     * @return
     */
    public final boolean isReady() {
        return cards.size() > 0&& lastMove == null;
    }

    /**
     * Returns the next PlayerCard to play
     * @return PlayerCard c
     */
    public PlayerCard getNextCard() {
        PlayerCard pC = getNextCardFromPlayer();
        try {
            setLastMove(pC);
            remove(pC);
        }catch (IllegalMoveException e) {
            System.out.println(e.getMessage());
        }
        return pC;
    }

    /**
     * Returns the next PlayerCard to play from the custom Players
     * @return PlayerCard c
     */
    
    public abstract PlayerCard getNextCardFromPlayer();

    /**
     * Returns current Player Points
     * @return int points
     */
    public final int getPoints() {
        return points;
    }

    /**
     * Adds points to the player
     * @param add int add
     */
    public final void addPoints(final int add){
        points += add;
    }
}

