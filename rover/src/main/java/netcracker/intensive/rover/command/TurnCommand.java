package netcracker.intensive.rover.command;

import netcracker.intensive.rover.Rover;
import netcracker.intensive.rover.constants.Direction;

public class TurnCommand implements RoverCommand {
    private Rover rover;
    private Direction direction;

    public TurnCommand(Rover rover, Direction direction) {
        this.rover = rover;
        this.direction = direction;
    }

    @Override
    public void execute() {
        rover.turnTo(direction);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TurnCommand that = (TurnCommand) o;

        return direction == that.direction;
    }

    @Override
    public int hashCode() {
        return direction.hashCode();
    }

    @Override
    public String toString() {
        return "Heading " + direction;
    }
}
