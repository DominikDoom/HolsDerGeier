package de.rabitem.main.util;

import de.rabitem.main.HolsDerGeierUtil;
import de.rabitem.main.Main;
import de.rabitem.main.exception.IllegalMatchSetup;
import de.rabitem.main.exception.IllegalPlayerSize;
import de.rabitem.main.gui.ActionManagerUtil;
import de.rabitem.main.listener.OnGameOverListener;
import de.rabitem.main.player.Player;

import java.util.HashMap;

public class GameThread implements Runnable {

    private OnGameOverListener onGameOverListener;

    public void setOnGameOverListener(final OnGameOverListener onGameOverListener) {
        this.onGameOverListener = onGameOverListener;
    }


    @Override
    public void run() {
        try {
            for (int i = 0; i < Integer.parseInt(Main.getOptionsPanel().getTfRounds().getValue().toString()); i++) {
                Main.getMain().startGame();
                Main.getMain().reset();
                ActionManagerUtil.addRoundsPlayed();
            }
            if (Integer.parseInt(Main.getOptionsPanel().getTfRounds().getValue().toString()) == ActionManagerUtil.getRoundsPlayed()) {
                this.endMapping();
                Main.getMain().actionManager.getStatsPanel().setCancelTask(true);
                onGameOverListener.onGameOver();
            }
        } catch (IllegalMatchSetup | IllegalPlayerSize e) {
            System.out.println(e.getMessage());
        }
    }

    private void endMapping() {
        // map total player points
        HashMap<Player, Integer> mapPlayers = new HashMap<>();
        for (Player p : Main.getPlayerManager().getPlayerList()) {
            mapPlayers.put(p, p.getTotalPoints());
        }

        Main.getHolsDerGeierUtil().mapPlayerPlaces(mapPlayers, true);
        Main.getMain().setStatsManager(new StatsManager(HolsDerGeierUtil.getActivePlayers()));
    }
}
