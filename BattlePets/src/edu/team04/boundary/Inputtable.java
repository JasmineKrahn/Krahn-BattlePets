package edu.team04.boundary;

import edu.team04.entity.PetTypes;
import edu.team04.entity.PlayerTypes;
import edu.team04.entity.Skills;

import java.util.List;

public interface Inputtable {

    /**
     * Takes in the skill the player would like to choose
     * */
    Skills inputSkill(List<Skills> rechargingSkill);

    /**
     * Takes in the pet type the player would like to choose
     * */
    PetTypes inputPetType();

    /**
     * Takes in the pet name chosen by the player
     * */
    String inputPetName();

    /**
     * Takes in the starting HP chosen by the player
     * */
    double inputStartingHp();

    /**
     * Takes in the type of player, computer or just a human player
     * */
    PlayerTypes inputPlayerType();

    /**
     * Takes in the players chosen name
     * */
    String inputPlayerName();

    /**
     * Takes in the number of fights a player would like
     * */
    int inputNumberOfFights();

    /**
     * Takes in a random seed
     * */
    int inputRandomSeed();

    /**
     * Takes the input for whether the player would like to play again or not
     * */
    int inputPlayAgain();

    /***
     * Takes the input for the number of players in the game
     * */
    int inputNumberOfPlayers();

    /**
     * Takes the input for skill prediction if the player has chosen reversal of fortune
     * **/
    Skills inputSkillPrediction();

    /**
     * Takes the input for whether the players would like season mode or regular mode
     * **/
    int inputMode();

    /**
     * Checks if the inputs for numbers are actual numbers and returns true or false
     * @param check the string that we are checking if it is a number
     * **/
    boolean checkStringAreInt(String check);

}
