package netcracker.intensive.rover.command;

import netcracker.intensive.rover.Point;
import netcracker.intensive.rover.Rover;
import netcracker.intensive.rover.constants.Direction;

public class LandCommand implements RoverCommand {
    private Rover rover;
    private Point position;
    private Direction direction;

    public LandCommand(Rover rover, Point position, Direction direction) {
        this.rover = rover;
        this.position = position;
        this.direction = direction;
    }


    @Override
    public void execute() {
        rover.land(position, direction);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LandCommand that = (LandCommand) o;

        if (!position.equals(that.position)) return false;
        return direction == that.direction;
    }

    @Override
    public int hashCode() {
        int result = position.hashCode();
        result = 31 * result + direction.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Land at (" + position.getX() + ", " + position.getY() + ") heading " + direction;
    }
}
