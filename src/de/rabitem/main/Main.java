package de.rabitem.main;

import de.rabitem.main.exception.IllegalMatchSetup;
import de.rabitem.main.exception.IllegalPlayerSize;
import de.rabitem.main.player.PlayerManager;
import de.rabitem.main.player.instances.LocalPlayer;
import de.rabitem.main.player.instances.RandomPlayer;

/**
 * @author Felix Huisinga
 */
public class Main extends HolsDerGeier{

    private static PlayerManager playerManager;
    private static Main main;
    private static HolsDerGeierUtil holsDerGeierUtil;

    /**
     * Main method, starting point!
     * @param args String[] args
     */
    public static void main(String[] args) {
        playerManager = new PlayerManager();
        main = new Main();
        main.init();
    }

    /**
     * Returns the PlayerManager
     * @return playerManager
     */
    public static PlayerManager getPlayerManager() {
        return playerManager;
    }

    public void init() {
        /**
         * Initialize utility methods
         */
        holsDerGeierUtil = new HolsDerGeierUtil();

        /**
         * Setup player
         */
        holsDerGeierUtil.activatePlayer(new RandomPlayer("Player1"));
        holsDerGeierUtil.activatePlayer(new LocalPlayer("Player2"));
        holsDerGeierUtil.activatePlayer(new RandomPlayer("Player3"));


        /**
         * Start Game instance
         */
        try {
            startGame();
        }catch(IllegalMatchSetup | IllegalPlayerSize e){
            System.out.println(e.getMessage());
        }
    }

    /**
     * Returns the HolsDerGeierUtil instance
     * @return HolsDerGeierUtil
     */
    public static HolsDerGeierUtil getHolsDerGeierUtil() {
        return holsDerGeierUtil;
    }
}
