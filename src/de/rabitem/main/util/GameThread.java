package de.rabitem.main.util;

import de.rabitem.main.Main;
import de.rabitem.main.exception.IllegalMatchSetup;
import de.rabitem.main.exception.IllegalPlayerSize;
import de.rabitem.main.gui.ActionManagerUtil;
import de.rabitem.main.listener.OnGameOverListener;

public class GameThread implements Runnable {

    private OnGameOverListener onGameOverListener;

    public void setOnGameOverListener(final OnGameOverListener onGameOverListener){
        this.onGameOverListener = onGameOverListener;
    }

    @Override
    public void run() {
        try {
            Main.getMain().startGame();
            onGameOverListener.onGameOver();
            if (Integer.parseInt(Main.getOptionsPanel().getTfRounds().getValue().toString()) == ActionManagerUtil.getRoundsPlayed()) {
                Main.getMain().actionManager.getStatsPanel().setCancelTask(true);
            }
        } catch (IllegalMatchSetup | IllegalPlayerSize e) {
            System.out.println(e.getMessage());
        }
    }
}
