package ca.mcmaster.se2aa4.island.teamXXX.command;

import org.json.JSONObject;

import ca.mcmaster.se2aa4.island.teamXXX.*;
import ca.mcmaster.se2aa4.island.teamXXX.Direction;
import ca.mcmaster.se2aa4.island.teamXXX.drone.Drone;
import ca.mcmaster.se2aa4.island.teamXXX.results.CommandResult;

public class RadarCommand implements Command {
    
    private Direction direction;
    
    public RadarCommand(Direction direction) {
        this.direction = direction;
    }
    
    @Override
    public CommandOption getCommandType() {
        return CommandOption.RADAR;
    }

    @Override
    public void applyCommandResult(Drone drone, CommandResult result) {
        drone.decreaseBattery(result.getCost());      
    }

    @Override
    public JSONObject createRequestJSON() {
        JSONObject request = new JSONObject();
        request.put("action", "echo");
        JSONObject parameters = new JSONObject();
        parameters.put("direction", direction.toString());
        request.put("parameters", parameters);
        return request;
    }

}

