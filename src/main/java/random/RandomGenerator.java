package random;

import utils.Constants;
import java.util.Random;

public class RandomGenerator {

  private Random random;

  public RandomGenerator() {
    this.random = new Random();
  }

  public boolean isMeteorGenerated() {
    return random.nextDouble() < Constants.METEOR_PROBABILITY;
  }

  public int generateRandomX() {
    return random.nextInt(Constants.FRAME_WIDTH-2 * Constants.SHIP_WIDTH) + Constants.SHIP_WIDTH;
  }
}
