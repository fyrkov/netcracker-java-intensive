package netcracker.intensive.rover.programmable;

import netcracker.intensive.rover.command.RoverCommand;

import java.util.List;
import java.util.Map;

public class RoverProgram {
    public static final String LOG = "log";
    public static final String STATS = "stats";
    public static final String SEPARATOR = "===";

    private Map<String, Object> settings;
    private List<RoverCommand> commands;

    public RoverProgram(Map<String, Object> settings, List<RoverCommand> commands) {
        this.settings = settings;
        this.commands = commands;
    }

    public Map<String, Object> getSettings() {
        return settings;
    }

    public List<RoverCommand> getCommands() {
        return commands;
    }
}
