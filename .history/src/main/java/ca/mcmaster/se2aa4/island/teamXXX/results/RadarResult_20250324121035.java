package ca.mcmaster.se2aa4.island.teamXXX.results;

import org.json.JSONObject;

public class RadarResult extends CommandResult 
{
  int range;
  String found;

  public RadarResult(JSONObject result) {
    super(result);

    JSONObject result.getJSONObject("extras");
  }




}
