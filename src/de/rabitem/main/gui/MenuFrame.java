package de.rabitem.main.gui;

import de.rabitem.main.Main;
import de.rabitem.main.exception.IllegalMatchSetup;
import de.rabitem.main.exception.IllegalPlayerSize;
import de.rabitem.main.util.Util;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author Felix Huisinga
 */
public class MenuFrame extends JPanel {

    private String text = "Hols der Geier";
    private int charIndex = 0;

    private JLabel lHSpacer = new JLabel("SPACER");
    private JLabel lDesignBy = new JLabel("design by rabitem");
    private JLabel lHeadline = new JLabel(text);
    private JButton bStartGame = null;
    private JButton bOptions = null;
    private JButton bClose = null;

    public MenuFrame() {
        super();
        /**
         * Setup frame
         */
        // faster drawings (2-Layer drawings)
        this.setDoubleBuffered(true);
        this.setLayout(new GridBagLayout());
        this.setBorder(BorderFactory.createEtchedBorder());

        // Layout
        final GridBagConstraints constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.WEST;
        constraints.insets = new Insets(10, 10, 10, 10);
        constraints.gridx = 0;
        constraints.gridy = 0;

        // init components
        bStartGame = new JButton("Start");
        bOptions = new JButton("Options");
        bClose = new JButton("Close");

        bStartGame.setPreferredSize(new Dimension(180, 30));
        bOptions.setPreferredSize(new Dimension(180, 30));
        bClose.setPreferredSize(new Dimension(180, 30));

        bStartGame.setBackground(new Color(0x2dce98));
        bStartGame.setForeground(Color.white);
        bStartGame.setUI(new StyledButtonUI());
        bOptions.setBackground(new Color(0x2dce98));
        bOptions.setForeground(Color.white);
        bOptions.setUI(new StyledButtonUI());
        bClose.setBackground(new Color(0x2dce98));
        bClose.setForeground(Color.white);
        bClose.setUI(new StyledButtonUI());

        lHeadline.setFont(new Font("Verdana", Font.BOLD, 46));
        lHeadline.setForeground(Color.darkGray);
        lHeadline.setPreferredSize(new Dimension(370, 150));
        lHeadline.setText("");

        // add components to the panel
        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.gridwidth = 3;
        constraints.anchor = GridBagConstraints.CENTER;
        this.add(bStartGame, constraints);

        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.gridwidth = 3;
        constraints.anchor = GridBagConstraints.CENTER;
        this.add(bOptions, constraints);

        constraints.gridx = 0;
        constraints.gridy = 3;
        constraints.gridwidth = 3;
        constraints.anchor = GridBagConstraints.CENTER;
        this.add(bClose, constraints);

        constraints.gridx = 0;
        constraints.gridy = 4;
        constraints.gridwidth = 3;
        constraints.anchor = GridBagConstraints.CENTER;
        this.add(lDesignBy, constraints);

        constraints.gridx = 0;
        constraints.gridy = 9;
        constraints.gridwidth = 3;
        constraints.anchor = GridBagConstraints.CENTER;
        this.add(lHeadline);

        Timer timer = new Timer(100, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String labelText = lHeadline.getText();
                labelText += text.charAt(charIndex);
                lHeadline.setText(labelText);
                charIndex++;
                if (charIndex >= text.length()) {
                    ((Timer) e.getSource()).stop();
                }
            }
        });
        timer.start();

        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception e) {
            // If Nimbus is not available, fall back to cross-platform
            try {
                UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
            } catch (Exception ex) {
                System.out.println(e.getMessage());
            }
        }

        // Action Listener
        bClose.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //TODO: safe stats to DB
                System.exit(0);
            }
        });

        bStartGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Main.getMain().startGame();
                } catch (IllegalMatchSetup | IllegalPlayerSize e1) {
                    System.out.println(e1.getMessage());
                }
            }
        });
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        final InputStream imageStream = Main.class.getResourceAsStream("/resources/mainframe/background_MainFrame.jpg");
        try {
            final ImageIcon img = new ImageIcon(Util.getScaledImage(ImageIO.read(imageStream), Main.getMain().getMainMenuFrame().getWidth(), Main.getMain().getMainMenuFrame().getHeight()));
            g.drawImage(img.getImage(), 0, 0, this.getWidth(), this.getHeight(), null);
        } catch (IOException e) {
            //TODO: Exception handling
            System.out.println(e.getMessage());
        }
    }
}
