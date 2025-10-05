package caseStudies.SnakeAndFood;

import java.util.Scanner;

import caseStudies.SnakeAndFood.Movement.HumanDirections;

public class SnakeGameMain {
  public static void main(String[] args) {

    int width = 20;
    int height = 15;

    Position[] foodPositions = {
        new Position(5, 5),
        new Position(10, 8),
        new Position(3, 12),
        new Position(8, 17),
        new Position(12, 3)
    };

    SnakeGame game = new SnakeGame(width, height, foodPositions);

    System.out.println("===== SNAKE GAME =====");
    System.out.println(
        "Controls: W (Up), S (Down), A (Left), D (Right), Q (Quit)");
    System.out.println("Eat food to grow your snake and increase your score.");
    System.out.println("Don't hit the walls or bite yourself!");
    System.out.println("=======================");

    Scanner scanner = new Scanner(System.in);
    boolean gameRunning = true;
    int score = 0;

    while (gameRunning) {

      displayGameState(game);
      System.out.print("Enter move (W/A/S/D) or Q to quit: ");
      String input = scanner.nextLine().toUpperCase();

      if (input.equals("Q")) {
        System.out.println("Game ended by player. Final score: " + score);
        gameRunning = false;
        break;
      }

      try {
        HumanDirections direction = convertInput(input);
        score = game.move(direction);
        if (score == -1) {
          System.out.println("GAME OVER! You hit a wall or bit yourself.");
          System.out.println("Final score: " + (game.snake.size() - 1));
          gameRunning = false;
          break;
        } else {
          System.out.println("Score: " + score);
        }
      } catch (Exception exception) {
        System.out.println(exception.getMessage());
        continue;
      }

    }
    scanner.close();
    System.out.println("Thanks for playing!");
  }

  private static HumanDirections convertInput(String input) {
    switch (input) {
      case "W":
        return HumanDirections.UP;
      case "S":
        return HumanDirections.DOWN;
      case "A":
        return HumanDirections.LEFT;
      case "D":
        return HumanDirections.RIGHT;
      default:
        throw new IllegalArgumentException("Invalid input! Use W/A/S/D to move or Q to quit.");
    }
  }

  private static void displayGameState(SnakeGame game) {
    System.out.println("\nCurrent snake length: " + game.snake.size());
  }
}
