package ca.mcmaster.se2aa4.island.teamXXX;

import java.io.StringReader;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;
import org.json.JSONTokener;

import ca.mcmaster.se2aa4.island.teamXXX.algorithm.mvp.*;
import ca.mcmaster.se2aa4.island.teamXXX.drone.Direction;
import ca.mcmaster.se2aa4.island.teamXXX.drone.Drone;
import ca.mcmaster.se2aa4.island.teamXXX.drone.Position;
import ca.mcmaster.se2aa4.island.teamXXX.drone.Map;
import ca.mcmaster.se2aa4.island.teamXXX.results.*;
import eu.ace_design.island.bot.IExplorerRaid;


public class Explorer implements IExplorerRaid {

    private final Logger logger = LogManager.getLogger();
    private Drone drone;
    private BasicAlgo algorithm;

    @Override
    public void initialize(String s) {
        logger.info("** Initializing the Exploration Command Center");
        JSONObject info = new JSONObject(new JSONTokener(new StringReader(s)));
        logger.info("** Initialization info:\n {}", info.toString(2));
        String direction = info.getString("heading");
        Integer batteryLevel = info.getInt("budget");
        logger.info("The drone is facing {}", direction);
        logger.info("Battery level is {}", batteryLevel);

        drone = new Drone(Direction.fromString(direction), batteryLevel, new Position(1, 1), new Map());
        algorithm = new Algorithm(drone);
        logger.info("** Initialization complete");
    }

    @Override
    public String takeDecision() {
        logger.info("** Decision Pending");
        JSONObject decision = algorithm.takeDecision().createRequestJSON();
        logger.info("** Decision Penddwd;mkdk;k;mdemk;ing");
        logger.info("** Decision: {}", decision.toString());
        return decision.toString();
    }

    @Override
    public void acknowledgeResults(String s) {
        JSONObject response = new JSONObject(new JSONTokener(new StringReader(s)));
        logger.info("** Response received:\n" + response.toString(2));
        Integer cost = response.getInt("cost");
        logger.info("The cost of the action was {}", cost);
        String status = response.getString("status");
        logger.info("The status of the drone is {}", status);
        JSONObject extraInfo = response.getJSONObject("extras");
        logger.info("Additional information received: {}", extraInfo);

        try {
            algorithm.acknowledgeResults(new CommandResult(response));
        } catch (Exception e) {
            logger.error("Error in acknowledgeResults: {}", e);
            throw e;
        }
    }

    @Override
    public String deliverFinalReport() {
        return "no creek found";
    }

}
