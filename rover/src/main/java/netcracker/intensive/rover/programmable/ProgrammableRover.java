package netcracker.intensive.rover.programmable;

import netcracker.intensive.rover.GroundVisor;
import netcracker.intensive.rover.Rover;
import netcracker.intensive.rover.command.LandCommand;
import netcracker.intensive.rover.command.MoveCommand;
import netcracker.intensive.rover.command.RoverCommand;
import netcracker.intensive.rover.stats.SimpleRoverStatsModule;

import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * Этот класс должен уметь все то, что умеет обычный Rover, но при этом он еще должен уметь выполнять программы,
 * содержащиеся в файлах
 */
public class ProgrammableRover extends Rover implements ProgramFileAware {

    public SimpleRoverStatsModule simpleRoverStatsModule;
    private List<RoverCommand> commands;
    private Map<String, Object> settings;

    public ProgrammableRover(GroundVisor groundVisor, SimpleRoverStatsModule simpleRoverStatsModule) {
        super(groundVisor);
        this.simpleRoverStatsModule = simpleRoverStatsModule;
    }

    public void executeProgramFile(String file) {
        RoverProgram rp = new RoverCommandParser(this, file).getProgram();
        commands = Collections.unmodifiableList(rp.getCommands());
        settings = Collections.unmodifiableMap(rp.getSettings());
        if ((boolean) settings.get(RoverProgram.STATS)) {
            simpleRoverStatsModule = new SimpleRoverStatsModule();
        }
        commands.forEach((rc) -> {
            simpleRoverStatsModule.registerPosition(this.getCurrentPosition());
            rc.execute();
            if ((rc instanceof MoveCommand) || (rc instanceof LandCommand)) {
                simpleRoverStatsModule.registerPosition(this.getCurrentPosition());
            }
        });
    }

    public Map<String, Object> getSettings() {
        return this.settings;
    }

}
