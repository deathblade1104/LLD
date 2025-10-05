package caseStudies.SnakeAndFood.Movement;

public enum HumanDirections {

  UP(0, -1),
  DOWN(0, 1),
  RIGHT(1, 0),
  LEFT(-1, 0);

  private final int rowDelta, columnDelta;

  private HumanDirections(int rowDelta, int columnDelta) {
    this.rowDelta = rowDelta;
    this.columnDelta = columnDelta;
  }

  public int getRowDelta() {
    return this.rowDelta;
  }

  public int getColumnDelta() {
    return this.columnDelta;
  }

}
