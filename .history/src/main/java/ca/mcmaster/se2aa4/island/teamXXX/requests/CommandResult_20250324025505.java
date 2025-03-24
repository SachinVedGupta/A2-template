package ca.mcmaster.se2aa4.island.teamXXX.requests;
import org.json.JSONObject;

public class CommandResult {
  JSONObject result;
  int cost;

  public CommandResult(JSONObject result) {
    this.result = result;
  }
}
