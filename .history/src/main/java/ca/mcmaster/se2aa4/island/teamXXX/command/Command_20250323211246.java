package ca.mcmaster.se2aa4.island.teamXXX.command;

import org.json.JSONObject;
import ca.mcmaster.se2aa4.island.D;

public interface Command {
  public void execute(Drone drone, Result result);
  public JSONObject getJSONcommand();
  public CommandOption getCommandType();
}
