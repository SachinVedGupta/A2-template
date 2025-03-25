package ca.mcmaster.se2aa4.island.teamXXX.command;
import org.json.JSONObject;
import ca.mcmaster.se2aa4.island.teamXXX.*;
import ca.mcmaster.se2aa4.island.teamXXX.drone.Drone;
import ca.mcmaster.se2aa4.island.teamXXX.results.CommandResult;

public class ScanCommand implements Command {
    
    // default constructor will automatically be present
    
    @Override
    public CommandOption getCommandType() {
        return CommandOption.SCAN;
    }

    @Override
    public void applyCommandResult(Drone drone, CommandResult result) {
        drone.decreaseBattery(result.getCost());  
        drone.scanIntoMap(null);      
    }

    @Override
    public JSONObject createRequestJSON() {
        JSONObject request = new JSONObject();
        request.put("command", "scan");
        return request;
    }
  
}
