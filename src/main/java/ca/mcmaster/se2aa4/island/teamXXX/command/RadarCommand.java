package ca.mcmaster.se2aa4.island.teamXXX.command;

import org.json.JSONObject;
import ca.mcmaster.se2aa4.island.teamXXX.*;

public class RadarCommand implements Command {
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
        request.put("command", CommandOption.RADAR);
        return request;
    }

}

