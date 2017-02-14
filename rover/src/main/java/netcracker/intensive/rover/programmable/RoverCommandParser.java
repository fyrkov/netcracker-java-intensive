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
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class RoverCommandParser {

    private static Logger LOG = LoggerFactory.getLogger(RoverCommandParser.class);
    private final String PATH = "C:/Project/java-nc-intensive/rover/src/test/resources/netcracker/intensive/rover/programmable/";
    private Rover rover;
    private String file;
    private List<String> commandStrings;
    private Map<String, Object> settings;
    private List<RoverCommand> commands;

    public RoverCommandParser(Rover rover, String fileName) {
        this.rover = rover;
        file = PATH.concat(fileName);
        LOG.info("RoverCommandParser is looking for " + file);
        parseFileToStrings();
        parseCommandsAndSettings();
    }

    private void parseCommandsAndSettings() {

        commands = new LinkedList<>();
        settings = new HashMap<>();

        for (String s : commandStrings) {
            s.trim();
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
            } else if (s.toLowerCase().startsWith("log")) {
                s = s.substring(4).trim();
                String par1 = s;
                if (par1.equals("on") || par1.equals("off")) {
                    settings.put(RoverProgram.LOG, par1.equals("on"));
                    LOG.info("command parsed: log " + par1);
                } else {
                    LOG.error("command parsed: log wrong property");
                }
            } else if (s.toLowerCase().startsWith("stats")) {
                s = s.substring(6).trim();
                String par1 = s;
                if (par1.equals("on") || par1.equals("off")) {
                    settings.put(RoverProgram.STATS, par1.equals("on"));
                    LOG.info("command parsed: stats " + par1);
                } else {
                    LOG.error("command parsed: stats wrong property");
                }
            } else if (s.toLowerCase().startsWith("turn")) {
                s = s.substring(5).trim();
                Direction par1;
                try {
                    par1 = Direction.valueOf(s.toUpperCase());
                    commands.add(new TurnCommand(rover, par1));
                    LOG.info("command parsed: turn " + par1);
                } catch (IllegalArgumentException e) {
                    LOG.error("command parsed: turn wrong direction parameter");
//                    e.printStackTrace();
                }
            } else {
                LOG.info("command parsed: unknown command");
            }
        }
    }

    private void parseFileToStrings() {
        commandStrings = new LinkedList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line = br.readLine();
            if (line != null) {
                while (!line.equals("===")) {
                    commandStrings.add(line);
                    line = br.readLine();
                }

                line = br.readLine();
                while (line != null) {
                    commandStrings.add(line);
                    line = br.readLine();
                }
            }
        } catch (IOException e) {
            LOG.error("File not found");
            throw new RoverCommandParserException();
            //            System.exit(0);
        }
    }

    RoverProgram getProgram() throws RoverCommandParserException {
        return new RoverProgram(settings, commands);
    }
}
