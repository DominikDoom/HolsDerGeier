package de.rabitem.main;

import de.rabitem.main.util.GameThread;
import de.rabitem.main.gui.MenuFrame;
import de.rabitem.main.player.PlayerManager;
import de.rabitem.main.player.instances.LocalPlayer;
import de.rabitem.main.player.instances.RandomPlayer;
import de.rabitem.main.util.Util;

import javax.imageio.ImageIO;
import javax.swing.*;

import java.io.IOException;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

/**
 * @author Felix Huisinga
 */
public class Main extends HolsDerGeier{

    private static PlayerManager playerManager;
    private static Main main;
    private static HolsDerGeierUtil holsDerGeierUtil;
    private static Thread t1;
    private JFrame mainMenuFrame;

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
        if (!Util.hasReadCopyrightClaim()) {
            final JLabel message = new JLabel("<html>This project uses images that might be protected by copyright. Herewith I confirm not to abuse them and to use them only in my own sense! \n" +
                    "\n" +
                    "This confirmation will be saved for future use!<html>");
            JOptionPane.showMessageDialog(null, message, "Copyright claims", JOptionPane.INFORMATION_MESSAGE);
            Util.acceptCopyrightClaim();
        }
        /**
         * Initialize utility methods
         */
        holsDerGeierUtil = new HolsDerGeierUtil();

        /**
         * Setup player
         */
        holsDerGeierUtil.activatePlayer(new LocalPlayer("Player1"));
        holsDerGeierUtil.activatePlayer(new RandomPlayer("Player2"));
        // holsDerGeierUtil.activatePlayer(new LocalPlayer("Player3"));
        // holsDerGeierUtil.activatePlayer(new LocalPlayer("Player4"));

        t1 = new Thread(new GameThread());


        /**
         * GUI related setup
         */
        mainMenuFrame = new JFrame();
        final JPanel mainMenuPanel = new MenuFrame();
        mainMenuFrame.setContentPane(mainMenuPanel);
        mainMenuFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        mainMenuFrame.setSize(900, 600);
        mainMenuFrame.setUndecorated(true);
        mainMenuFrame.getRootPane().setWindowDecorationStyle(JRootPane.NONE);
        mainMenuFrame.setLocationRelativeTo(null);
        mainMenuFrame.setVisible(true);
        try {
            mainMenuFrame.setIconImage(ImageIO.read(Main.class.getResourceAsStream("/resources/mainframe/iconFrames.png")));
        } catch(IOException e) {
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

    public static Main getMain() {
        return main;
    }

    public static Thread getGameThread() {
        return t1;
    }

    public JFrame getMainMenuFrame() {
        return mainMenuFrame;
    }
}
