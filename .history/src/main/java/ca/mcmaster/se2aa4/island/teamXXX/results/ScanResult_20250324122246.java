package ca.mcmaster.se2aa4.island.teamXXX.results;
import org.json.JSONObject;
import org.json.JSONArray;

public class ScanResult extends CommandResult {

  JSONArray biomes;
  JSONArray creeks;
  JSONArray sites;

  public ScanResult(JSONObject result) {
    super(result);
    JSONObject extras = result.getJSONObject("extras");

    biomes = extras.getJSONArray("biomes");
    creeks = extras.getJSONArray("creeks");
    sites = extras.getJSONArray("sites");
  }

  public JSONArray getBiomes() {
    return new JSONArray(biomes.toString());
  }

  public JSONArray getCreeks() {
    return new JSONArray(biomes.toString());
  }

  public JSONArray getBiomes() {
    return new JSONArray(biomes.toString());
  }


  
}
