package ca.mcmaster.se2aa4.island.teamXXX.command;

import org.json.JSONObject;

import ca.mcmaster.se2aa4.island.teamXXX.*;
import eu.ace_design.island.game.actions.Heading;

public class RadarCommand implements Command {
    
    private Heading heading;
    
    public RadarCommand(Heading heading) {
        this.heading = heading;
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
        parameters.put("direction", heading.toString());
        request.put("parameters", parameters);
        return request;
    }

}

