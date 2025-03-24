package ca.mcmaster.se2aa4.island.teamXXX.command;

import org.json.JSONObject;

public class FlyCommand implements Command {
  
  public JSONObject createRequestJSON() {
    JSONObject request = new JSONObject();
    request.put("action", "fly");
    return request;
  }

  public void applyCommandResult(Drone drone, CommandResult result) {
    drone.setBatteryLevel(drone.getBatteryLevel() - result.getCost());
    drone.setHeading(result.getHeading());
    drone.setCreek(result.getCreek());
  }
  
  public CommandOption getCommandType() {
    return CommandOption.FLY;
  }

}
