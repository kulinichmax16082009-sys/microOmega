package game.gameUtils;

import java.util.Random;


/**
 * This class is solving all problems with random generating.
 *
 * @author Maksym Kulynych
 */
public class RandomGenerator {
    private final Random rnd = new Random();

    /**
     * This method generates random chance in range of 0 to 100 and compares it with percent.
     *
     * @param percent number from 0 to 100 that can/can't be in range of random chance
     * @return true - percent is in range of random chance, false - percent isn't in range of random chance
     */
    public boolean generateProbability(float percent) {
        float randomChance = rnd.nextFloat(101);
        if (percent <= 0) return false;
        if (percent >= 100) return true;
        return randomChance <= percent;
    }

    /**
     * This method generates random number in range of min and max.
     *
     * @param min min number
     * @param max max number
     * @return random generated number
     */
    public int randomNumber(int min, int max) {
        if (max == Integer.MAX_VALUE) return 0;
        return rnd.nextInt(min, max + 1);
    }
}
