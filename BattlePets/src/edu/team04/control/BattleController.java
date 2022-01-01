package edu.team04.control;

import edu.team04.boundary.IOManager;
import edu.team04.entity.Battle;
import edu.team04.entity.Fight;
import edu.team04.entity.Playable;
import java.util.*;

/**
 * @author Makenna Halvensleben
 * BattleController holds the logic to run a battle.
 */
public class BattleController {
    private final FightController fightController = new FightController();

    /**
     * Constructor for the BattleController.
     */
    public BattleController() {
    }

    /**
     * Runs the battle by looping through each fight and setting the winner of the battle.
     * @param battle current Battle
     * @param rng random number generator used in RoundController
     */
    public void runBattle(Battle battle, Random rng) {
        for (Fight fight : battle.getFights()) {
            for (Playable playable : battle.getPlayables()) {
                fight.getReversalOfFortunes().put(playable, 0.0);
            }
            fightController.runFight(fight, battle.getPlayables(), rng);
            for (Playable playable : battle.getPlayables()) {
                playable.reset();
            }
        }
        battle.setWinners(determineBattleWinner(battle));
        IOManager.getInstance().getOutputtable().outputWinBattle(battle.getWinners());
    }

    /**
     * Determines the winner of the battle or if it's a tie.
     * @param battle current battle
     * @return the winner of the battle or null if it's a tie
     */
    public List<Playable> determineBattleWinner(Battle battle) {
        List<Playable> playables = battle.getPlayables();
        Map<Playable, Integer> winCount = battle.getFightWinCount();
        // Gets the number of wins for each playable.
        for (Playable playable : playables) {
            winCount.put(playable, 0);
            for (Fight fight : battle.getFights()) {
                if (fight.getWinner().getPlayerName().equals(playable.getPlayerName())) {
                    winCount.put(playable, winCount.get(playable) + 1);
                }
            }
        }
        List<Playable> winners = new ArrayList<>();
        winners.add(playables.get(0));
        // Determines which playable has the most wins or if it's a tie
        for (int i = 1; i < playables.size(); i++) {
            if (winCount.get(playables.get(i)) > winCount.get(winners.get(0))) {
                winners.clear();
                winners.add(playables.get(i));
            } else if (winCount.get(playables.get(i)).equals(winCount.get(winners.get(0)))) {
                winners.add(playables.get(i));
            }
        }
        return winners;
    }
}
