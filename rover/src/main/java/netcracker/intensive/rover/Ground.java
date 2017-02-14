package netcracker.intensive.rover;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Ground {

    private GroundCell[][] landscape;
    private Logger LOG = LoggerFactory.getLogger(Ground.class);

    public Ground(int x, int y) {
        if (x > 0 && y > 0) {
            landscape = new GroundCell[x][y];
        } else {
            LOG.error("Error creating Ground: incorrect array dimensions");
        }
    }

    void initialize(GroundCell... cell) {
        if (cell.length < this.landscape.length * this.landscape[0].length) {
            LOG.error("Initialize Ground error: Insufficient Parameters");
            throw new IllegalArgumentException();
        }

        for (int i = 0; i < this.landscape.length; i++) {
            for (int j = 0; j < this.landscape[0].length; j++) {
                this.landscape[j][i] = cell[i * this.landscape.length + j];
            }
        }
    }

    public GroundCell getCell(int x, int y) throws OutOfGroundException {
        GroundCell groundCell = null;
        try {
            groundCell = landscape[x][y];
        } catch (ArrayIndexOutOfBoundsException e) {
            LOG.error("GetCell error: cell [" + x + ", " + y + "] is  out of bounds");
            throw new OutOfGroundException();
        }
        return groundCell;
    }
}
