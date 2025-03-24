package ca.mcmaster.se2aa4.island.teamXXX.requests;
import org.json.JSONObject;

public class CommandResult {
  int cost;
  boolean success;

  public CommandResult(JSONObject result) {
    this.cost = result.getInt("cost");


  }
}
