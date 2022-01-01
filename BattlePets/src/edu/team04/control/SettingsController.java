package edu.team04.control;

import edu.team04.boundary.IOManager;
import edu.team04.boundary.Inputtable;
import edu.team04.entity.PlayerSettings;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Makenna Halvensleben
 * SettingsController initializes and holds the battle's settings.
 */
public class SettingsController {
    private int randomSeed;
    private int numberOfFights;
    private int mode;
    private List<PlayerSettings> playerSettingsList;

    /**
     * Constructor for the SettingsController that intializes playerSettingsList.
     */
    public SettingsController() {
        playerSettingsList = new ArrayList<>();
    }

    /**
     * Getter method for the random seed used in the RNG in RoundController.
     * @return random seed
     */
    public int getRandomSeed() {
        return randomSeed;
    }

    /**
     * Setter method for the random seed used in the RNG in RoundController.
     * @param randomSeed random seed
     */
    public void setRandomSeed(int randomSeed) {
        this.randomSeed = randomSeed;
    }

    /**
     * Getter method for the number of fights in the battle.
     * @return number of fights
     */
    public int getNumberOfFights() {
        return numberOfFights;
    }

    /**
     * Setter method for the number of fights in the battle.
     * @param numberOfFights number of fights
     */
    public void setNumberOfFights(int numberOfFights) {
        this.numberOfFights = numberOfFights;
    }

    /**
     * Getter method for the mode of the game
     * @return mode of the game
     */
    public int getMode() {
        return mode;
    }

    /**
     * Setter method for the mode of the game
     * @param mode mode of the game
     */
    public void setMode(int mode) {
        this.mode = mode;
    }

    /**
     * Getter method for the list of player settings.
     * @return list of player settings
     */
    public List<PlayerSettings> getPlayerSettingsList() {
        return playerSettingsList;
    }

    /**
     * Setter method for the list of player settings.
     * @param playerSettingsList list of player settings
     */
    public void setPlayerSettingsList(List<PlayerSettings> playerSettingsList) {
        this.playerSettingsList = playerSettingsList;
    }

    /**
     * Gets the input needed to initialize the settings.
     * @param numberOfPlayers the number of players in the battle
     */
    public void getSettings(int numberOfPlayers) {
        Inputtable inputtable = IOManager.getInstance().getInputtable();
        for (int i = 0; i < numberOfPlayers; i++) {
            IOManager.getInstance().getOutputtable().outputPlayerTurn("Player " + (i + 1));
            addPlayerSettings();
        }
        this.numberOfFights = inputtable.inputNumberOfFights();
        this.randomSeed = inputtable.inputRandomSeed();
        this.mode = inputtable.inputMode();
    }

    /**
     * Gets the input needed to initialize player settings.
     */
    public void addPlayerSettings() {
        Inputtable inputtable = IOManager.getInstance().getInputtable();
        PlayerSettings playerSettings = new PlayerSettings.PlayerSettingsBuilder()
                .withPlayerName(inputtable.inputPlayerName())
                .withPlayerType(inputtable.inputPlayerType())
                .withPetName(inputtable.inputPetName())
                .withPetType(inputtable.inputPetType())
                .withStartingHp(inputtable.inputStartingHp())
                .build();
        playerSettingsList.add(playerSettings);
        System.out.println();
    }

    /**
     * Resets the list of player settings.
     */
    public void reset() {
        playerSettingsList = new ArrayList<>();
    }
}
