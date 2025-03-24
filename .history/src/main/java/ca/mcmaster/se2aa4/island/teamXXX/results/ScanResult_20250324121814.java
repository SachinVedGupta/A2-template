package ca.mcmaster.se2aa4.island.teamXXX.results;
import org.json.JSONObject;
import org.json.JSONArray;

public class ScanResult extends CommandResult {

  JSONArray biomes;
  JSONArray creeks;
  JSONArray sites;

  public ScanResult(JSONObject result) {
    super(result);
  }


  
}
