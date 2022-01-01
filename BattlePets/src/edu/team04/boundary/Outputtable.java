package edu.team04.boundary;

import edu.team04.entity.PetTypes;
import edu.team04.entity.Playable;
import edu.team04.entity.Skills;
import edu.team04.entity.Damage;

import java.util.List;

/**
 * Interface for getting outputs for the game
 *
 */
public interface Outputtable {

    /**
     * Output the skill choices
     *
     * @param skills the skill chosen by the player
     * */
    void outputSkillChoices(Skills skills, String petName);

    /**
     * Output which fight number the game is on
     *
     * @param fightNumber the current fight the players are in
     * */
    void outputFightNumber(int fightNumber);

    /**
     * Output the pet names of the players
     *
     * @param petName the name of the pets
     * */
    void outputPetName(String petName);

    /**
     * Outputs the petType of the player
     *
     * @param petType the pet type the player has chosen
     * */
    void outputPetType(PetTypes petType);

    /**
     * Calls a method to output which round the players are on.
     *
     * @param roundNumber the current round the players are on
     * */
    void outputRoundNumber(int roundNumber);

    /**
     * Outputs the HP of the pet whose turn it is.
     *
     * @param petName the pets name
     * @param hp the pets current HP
     * */
    void outputHp(String petName, double hp);

    /**
     * Outputs the damage inflicted on that turn
     *
     * @param petName the pets name
     * @param damage damage inflicted onto the pet
     * */
    void outputDamageInflicted(String petName, Damage damage);

    /**
     * Outputs the recharge time for the pets skill
     *
     * @param skill the pets skill
     * @param skillRechargeTime the recharge time for that skill
     * */
    void outputRechargeTime(Skills skill, int skillRechargeTime);

    /**
     * Outputs which pet won the fight
     *
     * @param petName the name of the pet who won the fight
     * */
    void outputWinFight(String petName);

    /**
     * Outputs which pet won the battle
     *
     * @param winners this is the list of the pet who won the battle
     * */
    void outputWinBattle(List<Playable> winners);

    /**
     * Outputs the welcome message at the start of the game
     * */
    void outputWelcomeMessage();

    /**
     * Outputs which player's turn it is
     *
     * @param petName name of the pet whose turn it is
     * */
    void outputPlayerTurn(String petName);

    /**
     * Outputs which pets have fallen asleep
     *
     * @param petName name of the pet whose fallen asleep
     * **/
    void outputFallenAsleep(String petName);

    /**
     * Outputs a break statement for when needed in the game
     *
     * */
    void outputBreak();

    /**
     * This outputs the battle wins and losses of a specific pet name
     *
     * @param playable object used to obtain the pet name
     * @param wins the number of wins that pet has
     * @param losses the number of losses that pet has
     * */
    void outputBattles(Playable playable, int wins, int losses);

    /**
     * The number of fights a pet has won
     *
     * @param playable object to get the pets name
     * @param wins the number of wins that pet has
     * */
    void outputFightsWon(Playable playable, int wins);

    /**
     * This outputs the difference in random damage for a pet
     *
     * @param playable object to get the pets name
     * @param reversalOffortuneValue the amount of random damage and conditional damage difference
     * */
    void outputReversalOfFortune(Playable playable, double reversalOffortuneValue);

    /**
     * Output the skill choices
     *
     * @param skills the skill chosen by the player
     * @param petName the name of the pet who predicted the skills
     * */
    void outputPredictedSkillChoices(Skills skills, String petName);

}
