package ca.mcmaster.se2aa4.island.teamXXX.command;
import org.json.JSONObject;

import ca.mcmaster.se2aa4.island.teamXXX.drone.Drone;
import ca.mcmaster.se2aa4.island.teamXXX.results.CommandResult;
import ca.mcmaster.se2aa4.island.teamXXX.results.ScanResult;

public class ScanCommand implements Command {
    
    // default constructor will automatically be present
    
    @Override
    public CommandOption getCommandType() {
        return CommandOption.SCAN;
    }

    @Override
    public void applyCommandResult(Drone drone, CommandResult result) {
        drone.decreaseBattery(result.getCost());  
        //drone.scanIntoMap(result);      
        System.out.println("h");
    }

    public void applyCommandResult(Drone drone, ScanResult result) {
        drone.decreaseBattery(result.getCost());  
        drone.scanIntoMap(result);      
    }

    @Override
    public JSONObject createRequestJSON() {
        JSONObject request = new JSONObject();
        request.put("action", "scan");
        return request;
    }
  
}
