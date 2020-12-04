package de.rabitem.main.gui;

import de.rabitem.main.HolsDerGeierUtil;
import de.rabitem.main.Main;
import de.rabitem.main.exception.IllegalMatchSetup;
import de.rabitem.main.listener.OnGameOverListener;
import de.rabitem.main.listener.OnSetupFinished;
import de.rabitem.main.player.Player;
import de.rabitem.main.util.StatsManager;

import javax.swing.*;
import java.util.HashMap;

public class ActionManager {

    public final int[] roundsPlayed = {0};
    private JFrame statsFrame = null;
    private StatsFrame statsPanel = null;

    public void bStartAction() {
        roundsPlayed[0]++;
        try {
            Main.getHolsDerGeierUtil().setCardRange(Main.getOptionsPanel().getTfCardsFrom(), Main.getOptionsPanel().getTfCardsTo());
        }catch (IllegalMatchSetup e) {
            System.out.println(e.getMessage());
            return;
        }
        Main.getMainMenuFrame().setVisible(false);
        Main.getGameThread().start();

        Main.getMain().setOnSetupFinishedListener(new OnSetupFinished() {
            @Override
            public void onSetupFinished() {
                if (statsFrame != null && statsFrame.isVisible())
                    return;
                statsPanel = new StatsFrame();
                statsFrame = Main.getMain().setupFrame(statsPanel, "/resources/iconFrames.png");
                statsFrame.setVisible(true);
            }
        });

        Main.getGameThreadRunnable().setOnGameOverListener(new OnGameOverListener() {
            @Override
            public void onGameOver() {
                if (Integer.parseInt(Main.getOptionsPanel().getTfRounds().getValue().toString()) > roundsPlayed[0]) {
                    roundsPlayed[0]++;
                    Main.getMain().reset();

                    Main.getGameThreadRunnable().run();
                } else {
                    System.out.println("Rounds played: " + roundsPlayed[0]);

                    // map total player points
                    HashMap<Player, Integer> mapPlayers = new HashMap<>();
                    for (Player p : Main.getPlayerManager().getPlayerList()) {
                        mapPlayers.put(p, p.getTotalPoints());
                    }

                    Main.getHolsDerGeierUtil().mapPlayerPlaces(mapPlayers, true);
                    Main.getMain().setStatsManager(new StatsManager(HolsDerGeierUtil.getActivePlayers()));
                }
            }
        });
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
