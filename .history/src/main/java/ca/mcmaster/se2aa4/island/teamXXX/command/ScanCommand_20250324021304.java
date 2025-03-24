package ca.mcmaster.se2aa4.island.teamXXX.command;

public class ScanCommand implements Command {
    @Override
    public CommandOption getCommandType() {
        return CommandOption.SCAN;
    }

    @Override
    public void applyCommandResult(Drone drone, CommandResult result) {
        drone.decreaseBattery(result.getCost());        
    }

    @Override
    public JSONObject createRequestJSON() {
        JSONObject request = new JSONObject();
        request.put("command", CommandOption.SCAN);
        return request;
    }
  
}
