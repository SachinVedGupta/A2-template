package ca.mcmaster.se2aa4.island.teamXXX.algorithm.mvp;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;

import ca.mcmaster.se2aa4.island.teamXXX.command.Command;
import ca.mcmaster.se2aa4.island.teamXXX.drone.Drone;
import ca.mcmaster.se2aa4.island.teamXXX.drone.Map;
import ca.mcmaster.se2aa4.island.teamXXX.drone.POIType;
import ca.mcmaster.se2aa4.island.teamXXX.results.CommandResult;

/**
 * The Algorithm class implements the decision-making logic for the drone.
 * It uses the State pattern to manage different behavioral states during exploration.
 */
public class Algorithm {
  private Drone drone;              // The drone being controlled
  protected State state;            // Current state of the drone
  private final Logger logger = LogManager.getLogger();

    /**
     * Constructs a new Algorithm with the specified drone.
     * Sets the initial state using getStartState().
     * 
     * @param drone The drone to control
     */
    public Algorithm(Drone drone) {
      this.drone = drone;
      state = getStartState(drone);
    }

    /**
     * Returns the drone being controlled.
     * 
     * @return The drone object
     */
    public Drone getDrone() {
        return drone;
    }

    /**
     * Makes a decision about the next command to execute.
     * Delegates to the current state to determine the appropriate command.
     * 
     * @return The command to execute
     */
    public Command takeDecision() {
        logger.info("TAKE DECISION IN BASIC ALGO");
        return state.getNextCommand();
    }

    /**
     * Updates the state based on the result of the previous command.
     * 
     * @param s The result of the previous command
     */
    public void acknowledgeResults(CommandResult s) {
        state = state.getNextState(s);
    }

    /**
     * Determines the starting state for the drone.
     * Can be overridden by subclasses to provide different initial states.
     * 
     * @param drone The drone to associate with the state
     * @return The initial state
     */
    protected State getStartState(Drone drone) {
      return new ForwardState(drone);
    }

  /**
   * Delivers the final report at the end of the mission.
   * Reports on discovered creeks and emergency sites.
   * 
   * @return A string containing the final report data
   */
  public String deliverFinalReport() {
    Map searchedMap = drone.getMap();

    // If no creeks were found, report that
    if (searchedMap.getCreeks().isEmpty()) {
        return "no creek found";
    }
    else {
        POIType site = searchedMap.getSite();
        
        // If both emergency site and creek are found, report their information
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
            // Only creeks were found but no emergency site
            JSONObject obj = new JSONObject();
            logger.info("Creeks: {}", searchedMap.getCreeks());
            obj.put("creek", searchedMap.getCreeks().get(0).getUID());
            return obj.toString();
        }
    }
  }
}
