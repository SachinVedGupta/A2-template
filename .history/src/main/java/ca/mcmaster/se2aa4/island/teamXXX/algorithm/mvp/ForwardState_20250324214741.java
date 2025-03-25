package ca.mcmaster.se2aa4.island.teamXXX.algorithm.mvp;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ca.mcmaster.se2aa4.island.teamXXX.command.Command;
import ca.mcmaster.se2aa4.island.teamXXX.command.CommandFactory;
import ca.mcmaster.se2aa4.island.teamXXX.command.CommandOption;
import ca.mcmaster.se2aa4.island.teamXXX.command.FlyCommand;
import ca.mcmaster.se2aa4.island.teamXXX.drone.Drone;
import ca.mcmaster.se2aa4.island.teamXXX.results.CommandResult;


public class ForwardState extends State {
    private Command command = CommandFactory.createCommand(CommandOption.FLY);
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
        if (command.getCommandType() == CommandOption.FLY) {
          command = CommandFactory.createCommand(CommandOption.SCAN);
          return getDrone().giveCommand(CommandOption.SCAN);
        }
        else  {
          command = CommandFactory.createCommand(CommandOption.FLY);
          logger.info("New position after fly");
          return getDrone().giveCommand(CommandOption.FLY);
        }
    }
}

