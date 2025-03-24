package ca.mcmaster.se2aa4.island.teamXXX.command;
import ca.mcmaster.se2aa4.island.teamXXX.drone.Direction;

public class CommandFactory {
  
  public static Command createCommand(CommandOption option) {
    switch (option) {
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

  public static Command createCommand(CommandOption option, Direction to_go) {
    switch (option) {
      case HEADING:
        return new HeadingCommand();
      case RADAR:
        return new RadarCommand();
      default:
        return null;
    }
  }
}
