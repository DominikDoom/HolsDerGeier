package de.rabitem.main.gui;

import de.rabitem.main.Main;
import de.rabitem.main.util.Util;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.io.InputStream;

public class GUIUtil {

    public static void drawImageToBackground(final JPanel jPanel, final Graphics g, final String url) {
        final InputStream imageStream = Main.class.getResourceAsStream(url);
        try {
            final ImageIcon img = new ImageIcon(Util.getScaledImage(ImageIO.read(imageStream), Main.getMain().getMainMenuFrame().getWidth(), Main.getMain().getMainMenuFrame().getWidth()));
            g.drawImage(img.getImage(), 0, 0, jPanel.getWidth(), jPanel.getHeight(), null);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void setUIManager() {
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
    }
}
