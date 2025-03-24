package ca.mcmaster.se2aa4.island.teamXXX.command;

import org.json.JSONObject;

public class FlyCommand implements Command {
  
  public JSONObject createRequestJSON() {

  }
  
  public CommandOption getCommandType() {
    return CommandOption.FLY;
  }

}
