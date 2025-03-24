package ca.mcmaster.se2aa4.island.teamXXX.command;

import org.json.JSONObject;
import ca.mcmaster.se2aa4.island.teamXXX.*;

public interface Command {
  public JSONObject createRequestJSON();
  public void applyC(Drone drone, Result result);
  public CommandOption getCommandType();
}
