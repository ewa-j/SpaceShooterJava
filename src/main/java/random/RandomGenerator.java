package random;

import utils.Constants;
import java.util.Random;
import utils.GameVariables;

public class RandomGenerator {

  private Random random;

  public RandomGenerator() {
    this.random = new Random();
  }

  public boolean isMeteorGenerated() {
    return random.nextDouble() < GameVariables.meteorProbability;
  }

  public boolean isMedicalKitGenerated() {
    return random.nextDouble() < Constants.MEDICAL_KIT_PROBABILITY;
  }

  public int generateRandomX() {
    return random.nextInt(Constants.FRAME_WIDTH-2 * Constants.SHIP_WIDTH) + Constants.SHIP_WIDTH;
  }
}
