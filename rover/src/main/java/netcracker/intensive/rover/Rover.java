package netcracker.intensive.rover;

import netcracker.intensive.rover.constants.Direction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Rover implements Moveable, Landable, Liftable, Turnable {

    private Direction direction;
    private Point position;
    private boolean isAirborne = false;
    private GroundVisor groundVisor;

    private Logger LOG = LoggerFactory.getLogger(Rover.class);

    public Rover(GroundVisor groundVisor) {
        this.groundVisor = groundVisor;
        this.position = new Point(0, 0);
        this.direction = Direction.SOUTH;
        LOG.info("New Rover is created at [0,0] position and turned to SOUTH");
    }

    public Point getCurrentPosition() {
        if (!isAirborne()) LOG.info("Current rovers's position is [" + position.getX() + ", " + position.getY() + "]");
        return this.position;
    }

    public boolean isAirborne() {
        LOG.info("Current rovers's airborne status is " + isAirborne);
        return (this.isAirborne);
    }

    public Direction getDirection() {
        if (!isAirborne()) LOG.info("Current rovers's direction is " + direction);
        return this.direction;
    }

    @Override
    public void lift() {
        this.isAirborne = true;
        LOG.info("Rover lifted");
        this.position = null;
        this.direction = null;
    }

    @Override
    public void move() {
        Point destination;
        if (!this.isAirborne) {

            switch (direction) {
                case SOUTH:
                    destination = new Point(position.getX(), position.getY() + 1);
                    break;
                case EAST:
                    destination = new Point(position.getX() + 1, position.getY());
                    break;
                case WEST:
                    destination = new Point(position.getX() - 1, position.getY());
                    break;
                case NORTH:
                    destination = new Point(position.getX(), position.getY() - 1);
                    break;
                default:
                    destination = position;
                    break;
            }

            try {
                if (!groundVisor.hasObstacles(destination)) {
                    this.position = destination;
                    LOG.info("Rover successfully moved to new destination cell");
                } else {
                    LOG.info("Rover did not moved because destination cell is not accessible");
                }
            } catch (OutOfGroundException e) {
                this.lift();
                LOG.info("Rover lifted because destination cell is OutOfGround");
            }
        } else {
            LOG.info("Rover is airborne and did not moved");
        }
    }

    @Override
    public void land(Point position, Direction direction) {

        try {
            if (!groundVisor.hasObstacles(position)) {
                this.position = position;
                this.direction = direction;
                this.isAirborne = false;
                LOG.info("Rover successfully landed to new destination cell");

            } else {
                LOG.info("Rover did not landed because destination cell is not accessible");
            }
        } catch (OutOfGroundException e) {
            LOG.info("Rover did not moved because destination cell is OutOfGround");
        }

    }

    @Override
    public void turnTo(Direction direction) {
        if (!isAirborne()) {
            this.direction = direction;
            LOG.info("Rover turned to new direction " + direction);
        }
    }


}
