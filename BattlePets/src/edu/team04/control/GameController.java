package edu.team04.control;

import edu.team04.boundary.IOManager;
import edu.team04.boundary.Inputtable;
import edu.team04.boundary.Outputtable;
import edu.team04.entity.*;
import java.util.*;

/**
 * @author Makenna Halvensleben
 * GameController holds the logic to run the game by looping through battles and initializing settings.
 */
public class GameController {

    private final List<Battle> battleList = new ArrayList<>();
    private final List<Season> seasonList = new ArrayList<>();
    private final SettingsController settingsController = new SettingsController();
    private final BattleController battleController = new BattleController();
    private final Random rng = new Random();

    /**
     * Constructor for the GameController.
     */
    public GameController() {
    }

    /**
     * The method called in Main that runs the game.
     * Settings are initialized and battles are created and ran.
     * The process is repeated if the players wish to play again.
     */
    public void run() {
        Outputtable outputtable = IOManager.getInstance().getOutputtable();
        Inputtable inputtable = IOManager.getInstance().getInputtable();
        outputtable.outputWelcomeMessage();
        do {
            outputtable.outputBreak();
            settingsController.getSettings(inputtable.inputNumberOfPlayers());
            List<Playable> playables = createPlayableList();
            rng.setSeed(settingsController.getRandomSeed());
            int numberOfFights = settingsController.getNumberOfFights();

            if (settingsController.getMode() == 2) {
                Season season = new Season(playables);
                seasonList.add(season);
                runSeason(season, numberOfFights);
            } else {
                Battle battle = new Battle(numberOfFights, playables);
                battleList.add(battle);
                battleController.runBattle(battle, rng);
            }

            settingsController.reset();
        } while(playAgain());
    }

    /**
     * Creates list of initialized playables used in the game.
     * @return list of playables
     */
    public List<Playable> createPlayableList() {
        List<Playable> playables = new ArrayList<>();
        for(int i = 0; i < settingsController.getPlayerSettingsList().size(); i++) {
            PlayerSettings playerSettings = settingsController.getPlayerSettingsList().get(i);
            Player player = new Player(playerSettings.getPlayerType(), playerSettings.getPlayerName());
            Playable pet = new Pet.PetBuilder()
                    .withPetName(playerSettings.getPetName())
                    .withPetType(playerSettings.getPetType())
                    .withStartingHp(playerSettings.getStartingHp())
                    .withPlayer(player)
                    .withPlayerUid(i)
                    .build();
            playables.add(pet);
        }
        return playables;
    }

    /**
     * Gets the users input on whether they want to play again.
     * @return if the players want to play another battle
     */
    public boolean playAgain() {
        return IOManager.getInstance().getInputtable().inputPlayAgain() == 1;
    }

    /**
     * Gets the number of battles won by each playable in a season.
     * @param season season being played
     * @return map of the battle win count for each playable
     */
    public Map<Playable, Integer> getPlayableBattleWinCount(Season season) {
        Map<Playable, Integer> map = new HashMap<>();
        for (Playable playable : season.getPlayables()) {
            map.put(playable, 0);
        }
        for (Battle battle : season.getBattles()) {
            for (Playable playable : battle.getWinners()) {
                map.put(playable, map.get(playable) + 1);
            }
        }
        return map;
    }

    /**
     * Sorts the list of playables by the number of battles won and number of fights won.
     * @param map map of the number of battles won by each playable
     * @param season season being played
     * @return a sorted list of playables in the season
     */
    public List<Playable> sortPlayableList(Map<Playable, Integer> map, Season season) {
        List<Playable> tempList = new ArrayList<>();
        List<Playable> playables = season.getPlayables();
        tempList.add(playables.get(0));
        for (int i = 1; i < playables.size(); i++) {
            Playable playable = playables.get(i);
            boolean added = false;
            for (int j = 0; j < i && !added; j++) {
                Playable tempPlayable = tempList.get(j);
                if (map.get(playable) > map.get(tempPlayable)) {
                    tempList.add(j, playable);
                    added = true;
                } else if (map.get(playable).equals(map.get(tempPlayable))) {
                    if (getFightWinCount(playable, season) > getFightWinCount(tempPlayable, season)) {
                        tempList.add(j, playable);
                        added = true;
                    }
                }
                if (j == i - 1 && !added) {
                    tempList.add(playable);
                }
            }
        }
        return tempList;
    }

    /**
     * Calculates the total number of fights won over each battle for the playable
     * @param playable playable that needs to know the number of fights they won
     * @param season season being played
     * @return the number of fights won by the playable parameter
     */
    public int getFightWinCount(Playable playable, Season season) {
        int winCount = 0;
        for (Battle battle : season.getBattles()) {
            if (battle.getPlayables().contains(playable)) {
                winCount += battle.getFightWinCount().get(playable);
            }
        }
        return winCount;
    }

    /**
     * Outputs battle and fight wins for each playable in a season.
     * @param season season being played
     * @param battleWinsMap map of battle wins
     */
    public void outputEndSeason(Season season, Map<Playable, Integer> battleWinsMap) {
        Outputtable outputtable = IOManager.getInstance().getOutputtable();
        for (Playable playable : season.getPlayables()) {
            int battlesWon = battleWinsMap.get(playable);
            outputtable.outputBreak();
            outputtable.outputBattles(playable, battlesWon, season.getPlayables().size()-1-battlesWon);
            outputtable.outputFightsWon(playable, getFightWinCount(playable, season));
        }
    }

    /**
     * Holds the logic to run a season.
     * @param season season being played
     * @param numberOfFights number of fights in each battle in the season
     */
    public void runSeason(Season season, int numberOfFights) {
        for (List<Playable> playableList : season) {
            for (int i = 0; i < playableList.size() - 1; i+=2) {
                List<Playable> playablePair = new ArrayList<>();
                playablePair.add(playableList.get(i));
                playablePair.add(playableList.get(i+1));
                boolean playBattle = true;
                for (Playable playable : playablePair) {
                    if (playable == null) {
                        playBattle = false;
                    }
                }
                if (playBattle) {
                    Battle battle = new Battle(numberOfFights, playablePair);
                    season.getBattles().add(battle);
                    battleController.runBattle(battle, rng);
                }
            }
        }
        Map<Playable, Integer> battleWinsMap = getPlayableBattleWinCount(season);
        season.setPlayables(sortPlayableList(battleWinsMap, season));
        outputEndSeason(season, battleWinsMap);
    }
}