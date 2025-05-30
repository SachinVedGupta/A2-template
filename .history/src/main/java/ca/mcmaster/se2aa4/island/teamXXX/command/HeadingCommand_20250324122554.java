package ca.mcmaster.se2aa4.island.teamXXX.command;

import org.json.JSONObject;

import ca.mcmaster.se2aa4.island.teamXXX.drone.Drone;
import ca.mcmaster.se2aa4.island.teamXXX.results.CommandResult;

public class HeadingCommand implements Command {
    public CommandOption getCommandType() {
        return CommandOption.HEADING;
    }
    
    public JSONObject createRequestJSON() {
        JSONObject request = new JSONObject();
        request.put("action", "heading");
        return request;
    }

    public void applyCommandResult(Drone drone, CommandResult result) {
        drone.decreaseBattery(result.getCost());
        drone.changeHeading(result.getDirection());
    }
}
