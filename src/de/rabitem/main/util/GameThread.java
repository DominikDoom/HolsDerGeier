package de.rabitem.main.util;

import de.rabitem.main.HolsDerGeierUtil;
import de.rabitem.main.Main;
import de.rabitem.main.exception.IllegalMatchSetup;
import de.rabitem.main.exception.IllegalPlayerSize;

public class GameThread implements Runnable{

    @Override
    public void run() {
        try {
            Main.getMain().startGame();
        }catch(IllegalMatchSetup | IllegalPlayerSize e) {
            System.out.println(e.getMessage());
        }
    }
}
