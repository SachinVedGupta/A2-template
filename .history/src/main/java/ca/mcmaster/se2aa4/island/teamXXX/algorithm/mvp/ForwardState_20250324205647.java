package ca.mcmaster.se2aa4.island.teamXXX.algorithm.mvp;
import ca.mcmaster.se2aa4.island.teamXXX.drone.Drone;
import ca.mcmaster.se2aa4.island.teamXXX.results.CommandResult;
import ca.mcmaster.se2aa4.island.teamXXX.command.*;
import org.apache.logging.log4j.*;


public class ForwardState extends State {
    private Command command;
    public Drone drone;
    private final Logger logger = LogManager.getLogger();


    public ForwardState(Drone drone) {
        super(drone);
    }

    @Override
    public State getNextState(CommandResult result) {
        return this;
    }


    @Override
    public Command getNextCommand() {

      logger.info("HERE NOW");
      logger.info("1=1");
      return drone.giveCommand(CommandOption.FLY);

    }
}
