package ca.mcmaster.se2aa4.island.teamXXX.command;
import org.json.JSONObject;
import ca.mcmaster.se2aa4.island.teamXXX.*;
import ca.mcmaster.se2aa4.island.teamXXX.drone.Drone;
import ca.mcmaster.se2aa4.island.teamXXX.results.CommandResult;

public class StopCommand implements Command {

    // default constructor will automatically be present

    @Override
    public CommandOption getCommandType() {
        return CommandOption.STOP;
    }

    @Override
    public void applyCommandResult(Drone drone, CommandResult result) {
        drone.decreaseBattery(result.getCost());
    }

    @Override
    public JSONObject createRequestJSON() {
        JSONObject request = new JSONObject();
        request.put("action", "stop");
        return request;
    }

}
