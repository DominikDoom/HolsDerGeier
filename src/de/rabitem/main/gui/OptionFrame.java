package de.rabitem.main.gui;

import de.rabitem.main.HolsDerGeierUtil;
import de.rabitem.main.Main;
import de.rabitem.main.player.Player;
import de.rabitem.main.player.PlayerManager;

import javax.swing.*;
import javax.swing.text.NumberFormatter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.util.ArrayList;

public class OptionFrame extends JPanel {
    private JLabel lPlayer1 = null;
    private JComboBox cbPlayer1 = null;
    private JLabel lPlayer2 = null;
    private JComboBox cbPlayer2 = null;
    private JLabel lRounds = null;
    private JButton bAddPlayer = null;
    private JFormattedTextField tfRounds = null;
    private JButton bClose = null;

    /**
     * Constructor of StatsFrame
     */
    public OptionFrame() {
        this.setLayout(new GridBagLayout());
        this.setDoubleBuffered(true);
        this.setBorder(BorderFactory.createEtchedBorder());

        lPlayer1 = new JLabel("Player 1:");
        lPlayer2 = new JLabel("Player 2:");
        cbPlayer1 = new JComboBox();
        cbPlayer2 = new JComboBox();
        bAddPlayer = new JButton("➕ Player");
        lRounds = new JLabel("Rounds: ");
        bClose = new JButton("Close");

        for (String bot : Main.enabledPlayers) {
            cbPlayer1.addItem(bot);
            cbPlayer2.addItem(bot);
        }

        final NumberFormat format = NumberFormat.getInstance();
        final NumberFormatter formatter = new NumberFormatter(format);
        formatter.setValueClass(Integer.class);
        formatter.setMaximum(Integer.MAX_VALUE);
        formatter.setAllowsInvalid(false);
        // If you want the value to be committed on each keystroke instead of focus lost
        formatter.setCommitsOnValidEdit(true);

        tfRounds = new JFormattedTextField(formatter);
        tfRounds.setPreferredSize(new Dimension(40, 40));
        tfRounds.setValue(1);
        tfRounds.setEditable(true);

        bClose.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Main.getOptionsFrame().setVisible(false);
            }
        });

        bAddPlayer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Currently not working!", "Not implemented yet", JOptionPane.ERROR_MESSAGE);
            }
        });

        this.add(lPlayer1);
        this.add(cbPlayer1);
        this.add(lPlayer2);
        this.add(cbPlayer2);
        this.add(bAddPlayer);
        this.add(lRounds);
        this.add(tfRounds);
        this.add(bClose);
    }

    /**
     * Override this method to paint the background image
     * @param g Graphics g
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        final String url = "/resources/optionsframe/background_OptionsFrame.jpg";
        GUIUtil.drawImageToBackground(this, g, url);
    }

    public JFormattedTextField getTfRounds() {
        return tfRounds;
    }

    /**
     * Returns an ArrayList of JComboBoxes in the OptionsPanel
     * @return ArrayList<JComboBox>
     */
    public ArrayList<JComboBox> getComboBoxes() {
        ArrayList<JComboBox> comboBoxes = new ArrayList<>();
        for (Component component : this.getComponents()){
            if (component instanceof JComboBox) {
                comboBoxes.add((JComboBox) component);
            }
        }
        return comboBoxes;
    }
}
