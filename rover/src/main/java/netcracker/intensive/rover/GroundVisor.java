package netcracker.intensive.rover;

import netcracker.intensive.rover.constants.CellState;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GroundVisor {

    private Logger LOG = LoggerFactory.getLogger(GroundVisor.class);
    private Ground ground;

    public GroundVisor(Ground ground) {
        this.ground = ground;
    }

    public boolean hasObstacles(Point p) throws OutOfGroundException {

        GroundCell groundCell;
        try {
            groundCell = this.ground.getCell(p.getX(), p.getY());
        } catch (Exception e) {
            LOG.info("GroundVisor faced OutOfGroundException");
            throw new OutOfGroundException();
        }

        if (groundCell.getState().equals(CellState.FREE)) {
            LOG.info("GroundVisor status: Point [" + p.getX() + ", " + p.getY() + "] is free");
            return false;
        } else {
            LOG.info("GroundVisor status: Point [" + p.getX() + ", " + p.getY() + "] is not accessible");
            return true;
        }

    }

}
