package netcracker.intensive.rover.command;

import netcracker.intensive.rover.Rover;

public class LiftCommand implements RoverCommand {
    private Rover rover;

    public LiftCommand(Rover rover) {
        this.rover = rover;
    }

    @Override
    public void execute() {
        rover.lift();
    }

    @Override
    public boolean equals(Object o) {
        return !(o == null || getClass() != o.getClass());
    }

    @Override
    public int hashCode() {
        return "lift".hashCode();
    }

    @Override
    public String toString() {
        return "Rover lifted";
    }
}
