package de.rabitem.main.gui;

import de.rabitem.main.HolsDerGeierUtil;
import de.rabitem.main.Main;
import de.rabitem.main.player.Player;

import javax.swing.*;
import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;

public class StatsFrame extends JPanel {

    //TODO: über 50 Spielen ungenau, fixen!
    private boolean calledInit = false;
    private boolean cancelTask = false;

    /**
     * Constructor of StatsFrame
     */
    public StatsFrame() {
        this.setDoubleBuffered(true);
        this.setBorder(BorderFactory.createEtchedBorder());
        if (this.calledInit)
            return;
        this.initComponents();
        this.calledInit = true;
    }

    /**
     * Init Components,called after starting game!
     */
    private void initComponents() {
        for (int i = 0; i < HolsDerGeierUtil.getActivePlayers().size(); i++) {
            final JProgressBar progressBar = new JProgressBar(0, 100);
            progressBar.setString(HolsDerGeierUtil.getActivePlayers().get(i).getName());
            progressBar.setValue(0);
            progressBar.setStringPainted(true);
            progressBar.setOpaque(true);
            this.add(progressBar);
        }

        final Component[] components = this.getComponents();
        final Timer t = new Timer();
        t.scheduleAtFixedRate(new TimerTask() {

            @Override
            public void run() {
                for (Component component : components) {
                    if (component instanceof JProgressBar) {
                        for (Player p : HolsDerGeierUtil.getActivePlayers()) {
                            JProgressBar bar = (JProgressBar) component;
                            if (bar.getString().equalsIgnoreCase(p.getName())) {
                                double newValue = (double) p.getWinCounter() / (double) Integer.parseInt(Main.getOptionsPanel().getTfRounds().getValue().toString()) * 100;
                                bar.setValue((int) newValue);
                                bar.setToolTipText("Player " + p.getName() + " won " + p.getWinCounter() + " rounds!");
                                bar.update(bar.getGraphics());
                            }
                        }
                    }
                }
                if (cancelTask) {
                    for (Component component : components) {
                        if (component instanceof JProgressBar) {
                            for (Player p : HolsDerGeierUtil.getActivePlayers()) {
                                JProgressBar bar = (JProgressBar) component;
                                if (bar.getString().equalsIgnoreCase(p.getName())) {
                                    if (Main.getMain().getStatsManager().getTotalFirstPlace().equals(p)) {
                                        bar.setBackground(Color.GREEN);
                                    } else {
                                        bar.setBackground(Color.RED);
                                    }
                                    bar.update(bar.getGraphics());
                                }
                            }
                        }
                    }
                    this.cancel();
                    return;
                }
                this.cancel();
                return;
            }
        }, 1000, 1000);
    }

    /**
     * Override this method to paint the background image
     *
     * @param g Graphics g
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        final String url = "/resources/mainframe/background_MainFrame.jpg";
        GUIUtil.drawImageToBackground(this, g, url);
    }

    /**
     * Sets if the Thread needs to be canceled
     *
     * @param cancelTask boolean cancelTask
     */
    public void setCancelTask(boolean cancelTask) {
        this.cancelTask = cancelTask;
    }
}
