package netcracker.intensive.rover.command;

public interface RoverCommand {
    void execute();

    boolean equals(Object o);

    int hashCode();
}
