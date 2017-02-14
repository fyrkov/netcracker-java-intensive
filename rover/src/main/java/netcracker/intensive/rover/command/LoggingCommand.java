package netcracker.intensive.rover.command;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoggingCommand implements RoverCommand {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoggingCommand.class);
    MoveCommand moveCommand;

    public LoggingCommand(MoveCommand moveCommand) {
        this.moveCommand = moveCommand;
    }

    @Override
    public void execute() {
        moveCommand.execute();
        LOGGER.info("Rover moved");
    }

    @Override
    public boolean equals(Object o) {
        return !(o == null || getClass() != o.getClass());
    }

    @Override
    public int hashCode() {
        return "LoggingCommand".hashCode();
    }

    @Override
    public String toString() {
        return "Rover moved";
    }
}
