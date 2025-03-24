package ca.mcmaster.se2aa4.island.teamXXX.command;

public class commandFactory {
  public static Command createCommand(CommandOption option) {
        switch (option) {
            case FLY:
                return new FlyCommand();
            case HEADING:
                return new HeadingCommand();
            case RADAR:
                return new RadarCommand();
            case SCAN:
                return new ScanCommand();
            case STOP:
                return new StopCommand();
            default:
                return null;
        }
  }
}
