package de.rabitem.main.util;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @author Felix Huisinga
 */
public class Util {

    /**
     * Returns a random Integer within a given range
     *
     * @param min int min
     * @param max int max
     * @return int random
     */
    public static int random(int min, int max) {
        return ThreadLocalRandom.current().nextInt(min, max + 1);
    }

    /**
     * Returns lowest value of an integer array
     * @param values ArrayList<Integer> values
     * @return int lowestValue
     */
    public static int getLowestValue(final ArrayList<Integer> values) {
        int lowestValue = values.get(0);
        for (int i : values) {
            lowestValue = i < lowestValue ? i : lowestValue;
        }
        return lowestValue;
    }

    /**
     * Returns highest value of an integer array
     * @param values ArrayList<Integer> values
     * @return int highestValue
     */
    public static int getHighestValue(final ArrayList<Integer> values) {
        int highestValue = values.get(0);
        for (int i : values) {
            highestValue = i > highestValue ? i : highestValue;
        }
        return highestValue;
    }
}
