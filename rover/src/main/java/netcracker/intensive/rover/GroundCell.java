package netcracker.intensive.rover;

import netcracker.intensive.rover.constants.CellState;

public class GroundCell {

    private CellState cellState;

    public GroundCell(CellState cellState) {
        this.cellState = cellState;
    }

    public CellState getState() {
        return this.cellState;
    }
}
