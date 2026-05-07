import java.util.Random;

public class RandomGenerator {
    private Random rnd = new Random();

    public boolean generateProbability(float percent) {
        float randomChance = rnd.nextFloat(101);
        if (percent <= 0) return false;
        if (percent >= 100) return true;
        return randomChance <= percent;
    }

    public int randomNumber(int min, int max) {
        if (max == Integer.MAX_VALUE) return 0;
        return rnd.nextInt(min, max + 1);
    }
}
