package caseStudies.SnakeAndLadder.Dice;

public class DiceFactory {
  public static Dice standardD6() {
    return new StandardDice(6);
  }

  public static Dice standardD(int faces) {
    return new StandardDice(faces);
  }
}
