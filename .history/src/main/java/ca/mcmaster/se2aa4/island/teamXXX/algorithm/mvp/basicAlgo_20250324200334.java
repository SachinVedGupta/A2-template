package ca.mcmaster.se2aa4.island.teamXXX.algorithm.mvp;

import org.json.JSONObject;

import ca.mcmaster.se2aa4.island.teamXXX.drone.Drone;
import eu.ace_design.island.arena.exporters.MapInfo;
import ca.mcmaster.se2aa4.island.teamXXX.results.CommandResult;
import ca.mcmaster.se2aa4.island.teamXXX.command.Command;

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
        logger.info("")
        return state.getNextCommand();

    }

    public void acknowledgeResults(CommandResult s) {
        state = state.getNextState(s);
    }

    protected abstract State getStartState(Drone drone);
}
