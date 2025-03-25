package ca.mcmaster.se2aa4.island.teamXXX.command;
import ca.mcmaster.se2aa4.island.teamXXX.drone.Direction;
import org.apache.logging.log4j.*;

public class CommandFactory {
  private static final Logger logger = LogManager.getLogger();
  public static Command createCommand(CommandOption option) {
    switch (option) {
      case CommandOption.FLY:
        logger.info("Calling new FlyCommand()");
        return new FlyCommand();
      case CommandOption.SCAN:
        return new ScanCommand();
      case CommandOption.STOP:
        return new StopCommand();
      default:
        return null;
    }
  }

  public static Command createCommand(CommandOption option, Direction to_go) {
    switch (option) {
      case HEADING:
        return new HeadingCommand(to_go);
      case RADAR:
        return new RadarCommand(to_go);
      case FLY:
        return new FlyCommand();
      case SCAN:
        return new ScanCommand();
      case STOP:
        return new StopCommand();
      default:
        return null;
    }
  }
}
