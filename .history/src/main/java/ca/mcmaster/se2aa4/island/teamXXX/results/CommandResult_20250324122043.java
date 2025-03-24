package ca.mcmaster.se2aa4.island.teamXXX.results;
import org.json.JSONObject;


public class CommandResult {
  int cost;
  boolean success;

  public CommandResult(JSONObject result) {
    this.cost = result.getInt("cost");

    String status = result.getString("status");
    if (status.equals("OK")) {
      this.success = true;
    } else {
      this.success = false;
    }
  }

  public int getCost() {
    return this.cost;
  }

  publi
}
