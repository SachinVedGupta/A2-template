package ca.mcmaster.se2aa4.island.teamXXX.results;

import org.json.JSONObject;

public class RadarResult extends CommandResult 
{
  int range;
  String found;

  public RadarResult(JSONObject result) {
    super(result);

    JSONObject extras = result.getJSONObject("extras");
    this.range = extras.getInt("range");
    this.found = extras.getString("found");
  }

  
}
