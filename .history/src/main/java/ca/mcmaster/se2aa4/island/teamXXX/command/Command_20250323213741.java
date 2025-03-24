package ca.mcmaster.se2aa4.island.teamXXX.command;

import org.json.JSONObject;
import ca.mcmaster.se2aa4.island.teamXXX.*;

public interface Command {
  public void execute(Drone drone, Result result);
  public JSONObject getRequest();
  public CommandOption getCommandType();
}
