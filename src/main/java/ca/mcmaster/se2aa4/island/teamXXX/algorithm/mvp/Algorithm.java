package ca.mcmaster.se2aa4.island.teamXXX.algorithm.mvp;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;

import ca.mcmaster.se2aa4.island.teamXXX.command.Command;
import ca.mcmaster.se2aa4.island.teamXXX.drone.Drone;
import ca.mcmaster.se2aa4.island.teamXXX.drone.Map;
import ca.mcmaster.se2aa4.island.teamXXX.drone.POIType;
import ca.mcmaster.se2aa4.island.teamXXX.results.CommandResult;

public class Algorithm {
  private Drone drone;
  protected State state;
  private final Logger logger = LogManager.getLogger();

    public Algorithm(Drone drone) {
      this.drone = drone;
      state = getStartState(drone);
    }

    public Drone getDrone() {
        return drone;
    }

    public Command takeDecision() {
        logger.info("TAKE DECISION IN BASIC ALGO");
        return state.getNextCommand();

    }

    public void acknowledgeResults(CommandResult s) {
        state = state.getNextState(s);
    }

    protected State getStartState(Drone drone) {
      return new ForwardState(drone);
  }

  public String deliverFinalReport() {
    Map searchedMap = drone.getMap();

    if (searchedMap.getCreeks().isEmpty()) {
        return "no creek found";
    }

    else {
        POIType site = searchedMap.getSite();
        
        if (site != null) {
            POIType creek = searchedMap.getCreeks().get(0);

            JSONObject obj = new JSONObject();

            obj.put("emergency_site_id", site.getUID());
            obj.put("nearest_creek_id", creek.getUID());
            obj.put("emergency_site_position", site.getPosition().toString());
            obj.put("nearest_creek_position", creek.getPosition().toString());
            

            return obj.toString();
        } 
        else {
            //Should never happen 
            JSONObject obj = new JSONObject();
            logger.info("Creeks: {}", searchedMap.getCreeks());
            obj.put("creek", searchedMap.getCreeks().get(0).getUID());
            return obj.toString();
        }
    }
  }
}
