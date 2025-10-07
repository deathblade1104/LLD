package caseStudies.SnakeAndLadder.GameCompletionStrategy;

import java.util.List;

import caseStudies.SnakeAndLadder.GameBoard.GameBoard;
import caseStudies.SnakeAndLadder.Player.Player;

public interface GameCompletionStrategy {
  boolean isGameOver(Player currentPlayer, GameBoard board, List<Player> allPlayers, int turnCount);

  String winnerName(GameBoard board, List<Player> allPlayers);
}