package ca.mcmaster.se2aa4.island.teamXXX.command;
import ca.mcmaster.se2aa4.island.teamXXX.*;
import ca.mcmaster.se2aa4.island.teamXXX.drone.Direction;
import ca.mcmaster.se2aa4.island.teamXXX.drone.Drone;
import ca.mcmaster.se2aa4.island.teamXXX.drone.Position;
import ca.mcmaster.se2aa4.island.teamXXX.results.CommandResult;

import org.json.JSONObject;

public class FlyCommand implements Command {
  
  public JSONObject createRequestJSON() {
    JSONObject request = new JSONObject();
    request.put("action", "fly");
    return request;
  }

  public void applyCommandResult(Drone drone, CommandResult result) {
    // adjust battery level
    // adjust position (+1 in heading direction)

    drone.decreaseBattery(result.getCost());

    Position curr_pos = drone.getPosition();

    if (drone.getDirection() == Direction.NORTH) {
      drone.setPosition(new Position(curr_pos.getX(), curr_pos.getY() + 3));
    } else if (drone.getDirection() == Direction.EAST) {
      drone.setPosition(new Position(curr_pos.getX() + 3, curr_pos.getY()));
    } else if (drone.getDirection() == Direction.SOUTH) {
      drone.setPosition(new Position(curr_pos.getX(), curr_pos.getY() - 3));
    } else if (drone.getDirection() == Direction.WEST) {
      drone.setPosition(new Position(curr_pos.getX() - 3, curr_pos.getY()));
    }

  }
  
  public CommandOption getCommandType() {
    return CommandOption.FLY;
  }

}
