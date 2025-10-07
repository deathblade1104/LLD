package caseStudies.SnakeAndLadder;

import caseStudies.SnakeAndLadder.BoardEffect.Booster;
import caseStudies.SnakeAndLadder.BoardEffect.Ladder;
import caseStudies.SnakeAndLadder.BoardEffect.Snake;
import caseStudies.SnakeAndLadder.Dice.DiceFactory;
import caseStudies.SnakeAndLadder.GameBoard.GameBoard;
import caseStudies.SnakeAndLadder.GameBoard.GameBoardBuilder;
import caseStudies.SnakeAndLadder.GameCompletionStrategy.ClassicCompletionStrategy;

public class SnakeAndLadderMain {
  public static void main(String[] args) {
    // 1) Build an extensible board with multiple effect types
    GameBoard board = new GameBoardBuilder()
        .withSize(10, 10) // 100 cells, perfect square
        .addEffect(new Snake(99, 12))
        .addEffect(new Snake(96, 45))
        .addEffect(new Ladder(3, 90))
        .addEffect(new Ladder(11, 38))
        .addEffect(new Booster(27, 3)) // example of "something else"
        .build();

    // 2) Configure GameManager (Singleton)
    GameManager gm = GameManager.getInstance();
    gm.setBoard(board);
    gm.setDice(DiceFactory.standardD6());
    gm.setCompletionStrategy(new ClassicCompletionStrategy()); // or new TimeBoundCompletionStrategy(60)

    // 3) Add players (2–10)
    gm.addPlayer("Alice");
    gm.addPlayer("Bob");
    // gm.addPlayer("Charlie");

    // 4) Start simulation (no user input)
    gm.startGame();
  }
}