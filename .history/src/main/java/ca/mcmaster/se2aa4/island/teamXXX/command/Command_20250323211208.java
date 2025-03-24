package ca.mcmaster.se2aa4.island.teamXXX;

import org.json.JSONObject;

public interface Command {
  public void execute(Drone drone, Result result);
  public JSONObject getJSONcommand();
  public CommandOption getCommandType();
}
