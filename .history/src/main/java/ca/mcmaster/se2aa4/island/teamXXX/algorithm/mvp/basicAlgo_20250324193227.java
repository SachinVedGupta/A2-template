package ca.mcmaster.se2aa4.island.teamXXX.algorithm.mvp;

import org.json.JSONObject;

import ca.mcmaster.se2aa4.island.teamXXX.drone.Drone;
import eu.ace_design.island.arena.exporters.MapInfo;

public abstract class BasicAlgo {
  private Drone drone;
  private State state;

    public BasicAlgo(Drone drone) {
      this.drone = drone;
      this.state = getStartState(drone);
    }

    public Drone getDrone() {
        return drone;
    }

    public Command takeDecision() {
        return state.getNextCommand();
    }

    public void acknowledgeResults(ActionResult s) {
        state = state.nextState(s);
    }

    protected abstract State getStartState(Drone drone);

    public String deliverFinalReport() {
        MapInfo mapInfo = drone.getMapInfo();

        if (mapInfo.getCreeks().size() == 0) {
            return "no creek found";
        } else {
            POI site = mapInfo.getEmergencySite();

            if (site != null) {
                POI creek = mapInfo.getNearestCreek();

                JSONObject obj = new JSONObject();
                obj.put("emergency_site", site.id());
                obj.put("nearest_creek", creek.id());

                return obj.toString();
            } else {
                JSONObject obj = new JSONObject();
                obj.put("creek", mapInfo.getCreeks().get(0).id());
                return obj.toString();
            }
        }
    }
}
