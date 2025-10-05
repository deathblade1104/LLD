package caseStudies.ParkingLot.ParkingLot;

public class ParkingLotRenderer {
  public static void print(SpotState[][][] grid) {
    System.out.println("***** PRINTING PARKING LOT *****");
    for (int i = 0; i < grid.length; i++) {
      System.out.println("Floor Number : " + i);
      for (int j = 0; j < grid[i].length; j++) {
        for (int k = 0; k < grid[i][j].length; k++) {
          SpotState state = grid[i][j][k];
          boolean occ = state != SpotState.EMPTY;
          System.out.print(occ ? "OCCUPIED" : "UNOCCUPIED");
          System.out.print(" ");
        }
        System.out.println();
      }
      System.out.println("************************************************");
    }
  }
}

