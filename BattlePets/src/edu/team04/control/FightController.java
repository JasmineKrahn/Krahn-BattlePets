package edu.team04.control;

import edu.team04.boundary.IOManager;
import edu.team04.boundary.Outputtable;
import edu.team04.entity.Fight;
import edu.team04.entity.Playable;
import edu.team04.entity.Round;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author Makenna Halvensleben
 * FightController holds the logic to run a fight by outputting fight information,
 * looping through rounds, and determining the fight winner.
 */
public class FightController {
    private final RoundController roundController = new RoundController();

    /**
     * Constructor for the FightController.
     */
    public FightController() {
    }

    /**
     * Outputs the information to be displayed before a fight.
     * @param fight current fight
     * @param playables playables in the fight
     */
    public void getFightInfo(Fight fight, List<Playable> playables) {
        Outputtable outputtable = IOManager.getInstance().getOutputtable();
        outputtable.outputFightNumber(fight.getFightNumber());
        for (Playable playable : playables) {
            outputtable.outputPetName(playable.getPetName());
            outputtable.outputPetType(playable.getPetType());
        }
    }

    /**
     * Runs the fight by looping through rounds and determining the winner of the fight.
     * @param fight current fight
     * @param playables playables in the fight
     * @param rng random number generator used in RoundController
     */
    public void runFight(Fight fight, List<Playable> playables, Random rng) {
        Outputtable outputtable = IOManager.getInstance().getOutputtable();
        outputtable.outputBreak();
        getFightInfo(fight, playables);
        List<Playable> awakePlayables = new ArrayList<>(playables);
        int roundNumber = 1;
        while(!hasFightWinner(awakePlayables)) {
            Round round = new Round(roundNumber);
            roundController.runRound(fight, round, awakePlayables, rng);
            updateAwakePlayables(awakePlayables);
            fight.getRounds().add(round);
            roundNumber++;
        }
        roundController.outputCurrentHp(awakePlayables);
        fight.setWinner(getWinner(playables));
        outputtable.outputWinFight(fight.getWinner().getPetName());
    }

    /**
     * Determines the winner of the fight.
     * @param playables playables in the fight
     * @return the winner of the fight
     */
    private Playable getWinner(List<Playable> playables) {
        Playable highestHp = playables.get(0);
        for (Playable playable : playables) {
            if (playable.isAwake()) {
                return playable;
            }
            if (playable.getCurrentHp() > highestHp.getCurrentHp()) {
                highestHp = playable;
            }
        }
        return highestHp;
    }

    /**
     * Determines if the fight has a winner and the rounds should be stopped.
     * @param playables playables in the fight
     * @return if the fight has a winner
     */
    private boolean hasFightWinner(List<Playable> playables) {
        int awakeCount = 0;
        for (Playable playable : playables) {
            if(playable.isAwake()) {
                awakeCount++;
            }
        }
        return awakeCount == 0 || awakeCount == 1;
    }

    /**
     * Removes playables from the list if they are asleep and outputs who fell asleep.
     * @param awakePlayables list of playables still awake
     */
    private void updateAwakePlayables(List<Playable> awakePlayables) {
        Outputtable outputtable = IOManager.getInstance().getOutputtable();
        List<Playable> playables = new ArrayList<>();
        for (Playable playable : awakePlayables) {
            if (!playable.isAwake()) {
                outputtable.outputBreak();
                outputtable.outputFallenAsleep(playable.getPetName());
            } else {
                playables.add(playable);
            }
        }
        awakePlayables.clear();
        awakePlayables.addAll(playables);
    }
}
