package netcracker.intensive.rover.programmable;

import netcracker.intensive.rover.Point;
import netcracker.intensive.rover.Rover;
import netcracker.intensive.rover.command.*;
import netcracker.intensive.rover.constants.Direction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class RoverCommandParser {

    private static Logger LOG = LoggerFactory.getLogger(RoverCommandParser.class);
    private final String PATH = Paths.get("").toAbsolutePath().toString().concat("/src/test/resources/netcracker/intensive/rover/programmable/");
    private Rover rover;
    private String file;
    private List<String> commandStrings;
    private Map<String, Object> settings;
    private List<RoverCommand> commands;

    public RoverCommandParser(Rover rover, String fileName) {
        this.rover = rover;
        file = PATH.concat(fileName);
        LOG.info("RoverCommandParser is looking for " + file);
        commands = new LinkedList<>();
        settings = new HashMap<>();
        parseFileToStrings();
        if (!commandStrings.isEmpty()) parseCommandsAndSettings();
    }

    private void parseFileToStrings() {
        commandStrings = new LinkedList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line = br.readLine();
            if (line != null) {
                while (line != null) {
                    commandStrings.add(line);
                    line = br.readLine();
                }
            }
        } catch (IOException e) {
            LOG.error("File not found");
            throw new RoverCommandParserException(e);
            //            System.exit(0);
        }
    }

    private void parseCommandsAndSettings() {

        settings.put(RoverProgram.LOG, commandStrings.get(0).substring(4).trim().equals("on"));
        settings.put(RoverProgram.STATS, commandStrings.get(1).substring(6).trim().equals("on"));
        commandStrings = commandStrings.subList(3, commandStrings.size());

        for (String s : commandStrings) {

            if (s.equalsIgnoreCase("lift")) {
                LOG.info("command parsed: lift");
                commands.add(new LiftCommand(rover));
            } else if (s.equalsIgnoreCase("move")) {
                LOG.info("command parsed: move");
                commands.add(new MoveCommand(rover));
            } else if (s.toLowerCase().startsWith("land")) {
                s = s.substring(5).trim();
                int par1 = 0;
                try {
                    par1 = Integer.parseInt(s.substring(0, s.indexOf(" ")));
                } catch (NumberFormatException e) {
                    LOG.error("command parsed: land wrong coordinate parameter");
                }
                s = s.substring(s.indexOf(" ")).trim();
                int par2 = 0;
                try {
                    par2 = Integer.parseInt(s.substring(0, s.indexOf(" ")));
                } catch (NumberFormatException e) {
                    LOG.error("command parsed: land wrong coordinate parameter");
                }
                s = s.substring(s.indexOf(" ")).trim();
                Direction par3 = null;
                try {
                    par3 = Direction.valueOf(s.toUpperCase());
                } catch (IllegalArgumentException e) {
                    LOG.error("command parsed: land wrong direction parameter");
                }
                LOG.info("command parsed: land " + par1 + " " + par2 + " " + par3);
                commands.add(new LandCommand(rover, new Point(par1, par2), par3));
            } else if (s.toLowerCase().startsWith("turn")) {
                s = s.substring(5).trim();
                Direction par1;
                try {
                    par1 = Direction.valueOf(s.toUpperCase());
                    commands.add(new TurnCommand(rover, par1));
                    LOG.info("command parsed: turn " + par1);
                } catch (IllegalArgumentException e) {
                    LOG.error("command parsed: turn wrong direction parameter");
                }
            } else {
                LOG.info("command parsed: unknown command");
            }
        }
    }

    RoverProgram getProgram() {
        return new RoverProgram(settings, commands);
    }
}
