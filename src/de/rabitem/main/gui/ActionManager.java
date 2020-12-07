package de.rabitem.main.gui;

import de.rabitem.main.Main;
import de.rabitem.main.exception.IllegalMatchSetup;

import javax.swing.*;

public class ActionManager {

    public int roundsPlayed = 0;
    private JFrame statsFrame = null;
    private StatsFrame statsPanel = null;

    public void bStartAction() {
        try {
            Main.getHolsDerGeierUtil().setCardRange(Main.getOptionsPanel().getTfCardsFrom(), Main.getOptionsPanel().getTfCardsTo());
        }catch (IllegalMatchSetup e) {
            System.out.println(e.getMessage());
            return;
        }
        Main.getMainMenuFrame().setVisible(false);
        Main.getGameThread().start();
    }

    public void initStatsFrame(){
        if (statsFrame != null && statsFrame.isVisible())
            return;
        statsPanel = new StatsFrame();
        statsPanel.setCancelTask(true);
        statsFrame = Main.getMain().setupFrame(statsPanel, "/resources/iconFrames.png");
        statsFrame.setVisible(true);
    }

    public void bOptionsAction() {
        if (!Main.getOptionsFrame().isVisible()) {
            Main.getOptionsFrame().setVisible(true);
        }
    }

    public void bOptionsAddPlayer() {

    }

    public StatsFrame getStatsPanel() {
        return statsPanel;
    }

    public JFrame getStatsFrame() {
        return statsFrame;
    }
}
