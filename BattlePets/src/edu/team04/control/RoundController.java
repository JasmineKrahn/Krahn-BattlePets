package edu.team04.control;

import edu.team04.boundary.IOManager;
import edu.team04.boundary.Outputtable;
import edu.team04.entity.*;
import java.util.*;

/**
 * @author Makenna Halvensleben
 * RoundController holds the logic to execute a round and display the round information.
 */
public class RoundController {

    private final ConditionalDamageCalculator conditionalDamageCalculator = new ConditionalDamageCalculator();

    /**
     * Constructor for the RoundController.
     */
    public RoundController() {
    }

    /**
     * Outputs the playables' current hp.
     * @param playables playables still awake in the round
     */
    public void outputCurrentHp(List<Playable> playables) {
        Outputtable outputtable = IOManager.getInstance().getOutputtable();
        outputtable.outputBreak();
        List<Playable> playablesHighestHp = new ArrayList<>(playables);
        playablesHighestHp.sort(Comparator.comparingDouble(Playable::getCurrentHp));
        Collections.reverse(playablesHighestHp);
        for (Playable playable : playablesHighestHp) {
            outputtable.outputHp(playable.getPetName(), playable.getCurrentHp());
        }
        outputtable.outputBreak();
    }

    /**
     * Outputs the information needed after a round is played.
     * @param round current round
     * @param playables playables still awake in the round
     * @param chosenSkills map of skills chosen by playables
     */
    public void getEndingRoundInfo(Round round, List<Playable> playables, Map<Playable, Skills> chosenSkills) {
        Outputtable outputtable = IOManager.getInstance().getOutputtable();
        outputtable.outputBreak();
        for (Playable playable : playables) {
            outputtable.outputSkillChoices(chosenSkills.get(playable), playable.getPetName());
            if (chosenSkills.get(playable) == Skills.SHOOT_THE_MOON) {
                outputtable.outputPredictedSkillChoices(playable.getSkillPrediction(), playable.getPetName());
            }
        }
        for (Playable playable : playables) {
            outputtable.outputDamageInflicted(playable.getPetName(),
                    round.getDamages().get(getOpponentTakingDamage(playables, playable)));
        }
    }

    /**
     * Executes a round by getting skill input and calculating damage.
     * @param fight current fight
     * @param round current round
     * @param playables playables still awake in the round
     * @param rng random number generator
     */
    public void runRound(Fight fight, Round round, List<Playable> playables, Random rng) {
        Outputtable outputtable = IOManager.getInstance().getOutputtable();
        outputtable.outputBreak();
        outputtable.outputRoundNumber(round.getRoundNumber());
        outputCurrentHp(playables);
        Map<Playable, Skills> chosenSkills = new HashMap<>();
        // Gets chosen skills for each playable.
        for (Playable playable : playables) {
            outputtable.outputBreak();
            outputtable.outputPlayerTurn(playable.getPetName());
            outputtable.outputBreak();
            for (Skills skill : Skills.values()) {
                outputtable.outputRechargeTime(skill, playable.getSkillRechargeTime(skill));
            }
            outputtable.outputBreak();
            Skills skill = playable.chooseSkill();
            chosenSkills.put(playable, skill);
        }
        // Calculates the damage that each playable inflicts.
        for (Playable playable : playables) {
            double randomDamage = rng.nextDouble() * 5;
            Playable opponent = getOpponentDealingDamage(playables, playable);
            Damage damage = conditionalDamageCalculator.calcDamage(randomDamage,
                                                                    playable.getPetType(),
                                                                    chosenSkills.get(playable),
                                                                    chosenSkills.get(opponent),
                                                                    opponent,
                                                                    playable.getSkillPrediction(),
                                                                    fight.getReversalOfFortunes().get(playable));
            round.getDamages().put(playable, damage);
            opponent.updateHp(damage.calculateTotalDamage());
        }
        // Change recharge times
        for (Playable playable : playables) {
            playable.decrementRechargeTimes();
            Skills chosenSkill = chosenSkills.get(playable);
            if (chosenSkill == Skills.SHOOT_THE_MOON || chosenSkill == Skills.REVERSAL_OF_FORTUNE) {
                playable.setRechargeTime(chosenSkill, 6);
            } else {
                playable.setRechargeTime(chosenSkill, 1);
            }
        }
        // Update reversal of fortune values
        for (Playable playable : playables) {
            double currentValue = fight.getReversalOfFortunes().get(playable);
            double damageDealt = round.getDamages().get(playable).getRandomDamage();
            double damageTaken = round.getDamages().get(getOpponentTakingDamage(playables, playable)).getRandomDamage();
            double reversalOfFortuneValue = currentValue + (damageDealt - damageTaken);
            fight.getReversalOfFortunes().put(playable, reversalOfFortuneValue);
            outputtable.outputReversalOfFortune(playable, reversalOfFortuneValue);
        }
        getEndingRoundInfo(round, playables, chosenSkills);
    }

    /**
     * Determines the playable being attacked by the playable taking their turn.
     * @param playables playables still awake in the round
     * @param playable the playable currently taking their turn
     * @return the playable being attacked by the playable currently taking their turn
     */
    private Playable getOpponentDealingDamage(List<Playable> playables, Playable playable) {
        int index = playables.indexOf(playable);
        if (index == playables.size() - 1) {
            return playables.get(0);
        }
        return playables.get(index + 1);
    }

    /**
     * Determines the playable attacking the playable taking their turn.
     * @param playables playables still awake in the round
     * @param playable the playable currently taking their turn
     * @return the playable attacking the playable currently taking their turn
     */
    private Playable getOpponentTakingDamage(List<Playable> playables, Playable playable) {
        int index = playables.indexOf(playable);
        if (index == 0) {
            return playables.get(playables.size() - 1);
        }
        return playables.get(index - 1);
    }
}
