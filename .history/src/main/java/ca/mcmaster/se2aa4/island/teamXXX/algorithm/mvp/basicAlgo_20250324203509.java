package ca.mcmaster.se2aa4.island.teamXXX.algorithm.mvp;

import org.json.JSONObject;

import ca.mcmaster.se2aa4.island.teamXXX.drone.Drone;
import eu.ace_design.island.arena.exporters.MapInfo;
import ca.mcmaster.se2aa4.island.teamXXX.results.CommandResult;
import ca.mcmaster.se2aa4.island.teamXXX.command.Command;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public abstract class BasicAlgo {
  private Drone drone;
  prot State state;
  private final Logger logger = LogManager.getLogger();

    public BasicAlgo(Drone drone) {
      this.drone = drone;
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

    protected abstract State getStartState(Drone drone);
}
