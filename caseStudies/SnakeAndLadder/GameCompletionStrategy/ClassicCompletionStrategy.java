package caseStudies.SnakeAndLadder.GameCompletionStrategy;

import java.util.List;

import caseStudies.SnakeAndLadder.GameBoard.GameBoard;
import caseStudies.SnakeAndLadder.Player.Player;

public class ClassicCompletionStrategy implements GameCompletionStrategy {
  @Override
  public boolean isGameOver(Player currentPlayer, GameBoard board, List<Player> allPlayers, int turnCount) {
    return currentPlayer.getPosition() == board.getSize();
  }

  @Override
  public String winnerName(GameBoard board, List<Player> allPlayers) {
    return allPlayers.stream()
        .filter(p -> p.getPosition() == board.getSize())
        .findFirst()
        .map(Player::getName)
        .orElse("No winner");
  }
}