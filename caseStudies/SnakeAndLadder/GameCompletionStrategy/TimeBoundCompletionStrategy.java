package caseStudies.SnakeAndLadder.GameCompletionStrategy;

import java.util.Comparator;
import java.util.List;

import caseStudies.SnakeAndLadder.GameBoard.GameBoard;
import caseStudies.SnakeAndLadder.Player.Player;

public class TimeBoundCompletionStrategy implements GameCompletionStrategy {
  private final int maxTurns;

  public TimeBoundCompletionStrategy(int maxTurns) {
    if (maxTurns < 1)
      throw new IllegalArgumentException("maxTurns must be >= 1");
    this.maxTurns = maxTurns;
  }

  @Override
  public boolean isGameOver(Player currentPlayer, GameBoard board, List<Player> allPlayers, int turnCount) {
    return turnCount >= maxTurns;
  }

  @Override
  public String winnerName(GameBoard board, List<Player> allPlayers) {
    return allPlayers.stream()
        .max(Comparator.comparingInt(Player::getPosition))
        .map(Player::getName)
        .orElse("No players");
  }
}