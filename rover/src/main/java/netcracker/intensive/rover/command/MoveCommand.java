package netcracker.intensive.rover.command;

import netcracker.intensive.rover.Rover;

public class MoveCommand implements RoverCommand {
    private Rover rover;

    public MoveCommand(Rover rover) {
        this.rover = rover;
    }

    @Override
    public void execute() {
        rover.move();
    }

    @Override
    public boolean equals(Object o) {
        return !(o == null || getClass() != o.getClass());
    }

    @Override
    public int hashCode() {
        return "Move".hashCode();
    }

    @Override
    public String toString() {
        return "Rover moved";
    }
}
