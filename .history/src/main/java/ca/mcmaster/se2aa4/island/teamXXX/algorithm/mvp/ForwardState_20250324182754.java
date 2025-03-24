package ca.mcmaster.se2aa4.island.teamXXX.algorithm.mvp;
i

public class ForwardState extends State {
    public ForwardState(Drone drone) {
        super(drone);
    }

    @Override
    public State getNextState(CommandResult result) {
        if (result == CommandResult.SUCCESS) {
            return new ForwardState(this.getDrone());
        } else {
            return new TurnState(this.getDrone());
        }
    }

    @Override
    public Command getNextCommand() {
        return new ForwardCommand();
    }
}
