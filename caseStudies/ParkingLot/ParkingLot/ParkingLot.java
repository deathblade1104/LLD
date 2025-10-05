package caseStudies.ParkingLot.ParkingLot;

import java.util.Optional;

import caseStudies.ParkingLot.Vehicles.VehicleType;

public class ParkingLot {
  private final int floors, rows, columns;
  private final SpotState[][][] grid;
  private int spotsOccupied;

  public ParkingLot(int floors, int rows, int columns) {
    this.floors = floors;
    this.rows = rows;
    this.columns = columns;
    this.grid = new SpotState[floors][rows][columns];
    for (int i = 0; i < floors; i++)
      for (int j = 0; j < rows; j++)
        for (int k = 0; k < columns; k++)
          grid[i][j][k] = SpotState.EMPTY;
  }

  public void markSpaceAsOccupied(ParkingPosition position, int spaceOccupied, VehicleType vehicleType) {
    if (position.column() + spaceOccupied > columns) {
      throw new IllegalArgumentException("Not enough space in this row!");
    }

    for (int i = position.column(); i < position.column() + spaceOccupied; i++) {
      if (grid[position.floor()][position.row()][i] != SpotState.EMPTY) {
        throw new IllegalStateException("Spot already occupied!");
      }

      grid[position.floor()][position.row()][i] = switch (vehicleType) {
        case CAR -> SpotState.OCCUPIED_CAR;
        case BIKE -> SpotState.OCCUPIED_BIKE;
        case TRUCK -> SpotState.OCCUPIED_TRUCK;
      };
    }
    spotsOccupied += spaceOccupied;
  }

  public void markSpaceAsUnoccupied(ParkingPosition position, int spaceOccupied) {
    if (position.column() + spaceOccupied > columns) {
      throw new IllegalArgumentException("Invalid unoccupy range!");
    }
    for (int i = position.column(); i < position.column() + spaceOccupied; i++) {
      grid[position.floor()][position.row()][i] = SpotState.EMPTY;
    }
    spotsOccupied -= spaceOccupied;
  }

  public int totalFreeSpots() {
    int totalSpots = floors * rows * columns;
    return totalSpots - spotsOccupied;
  }

  private int findSpaceInAColumn(SpotState[] arr, int spaceRequired) {
    int consecutiveEmptySpots = 0;
    for (int i = 0; i < columns; i++) {
      if (arr[i] != SpotState.EMPTY) {
        consecutiveEmptySpots = 0;
      } else {
        consecutiveEmptySpots++;
      }

      if (consecutiveEmptySpots == spaceRequired) {
        return i - spaceRequired + 1;
      }
    }
    return -1;
  }

  public Optional<ParkingPosition> getFirstEmptyPositionInGrid(int spaceRequired) {
    for (int i = 0; i < floors; i++) {
      for (int j = 0; j < rows; j++) {
        int colStart = findSpaceInAColumn(grid[i][j], spaceRequired);
        if (colStart != -1) {
          return Optional.of(new ParkingPosition(i, j, colStart));
        }
      }
    }
    return Optional.empty();
  }

  public void print() {
    ParkingLotRenderer.print(grid);
  }
}
