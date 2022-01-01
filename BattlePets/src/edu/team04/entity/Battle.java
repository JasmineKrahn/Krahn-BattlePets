package edu.team04.entity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Makenna Halvensleben
 * Object that holds battle data including the fights, playables, and winners.
 */
public class Battle {
    private Fight[] fights;
    private List<Playable> playables;
    private List<Playable> winners;
    private Map<Playable, Integer> fightWinCount;

    /**
     * Constructor for Battle that initializes instance variables.
     * @param numberOfFights number of fights in the battle
     * @param playables list of playable in the battle
     */
    public Battle(int numberOfFights, List<Playable> playables) {
        fights = new Fight[numberOfFights];
        for (int i = 0; i < numberOfFights; i++) {
            fights[i] = new Fight(i + 1);
        }
        this.playables = playables;
        fightWinCount = new HashMap<>();
    }

    /**
     * Getter method for list of fights.
     * @return list of fights
     */
    public Fight[] getFights() {
        return fights;
    }

    /**
     * Setter method for list of fights.
     * @param fights list of fights
     */
    public void setFights(Fight[] fights) {
        this.fights = fights;
    }

    /**
     * Getter method for list of playables.
     * @return list of playables
     */
    public List<Playable> getPlayables() {
        return playables;
    }

    /**
     * Setter method for list of playables.
     * @param playables list of playables
     */
    public void setPlayables(List<Playable> playables) {
        this.playables = playables;
    }

    /**
     * Getter method for the winner of the battle.
     * @return the winners of the battle
     */
    public List<Playable> getWinners() {
        return winners;
    }

    /**
     * Setter method for the winner of the battle.
     * @param winners the winners of the battle
     */
    public void setWinners(List<Playable> winners) {
        this.winners = winners;
    }

    /**
     * Getter method for the map of the number of fights won by each playable
     * @return map of fight wins
     */
    public Map<Playable, Integer> getFightWinCount() {
        return fightWinCount;
    }

    /**
     * Setter method for the map of the number of fights won by each playable
     * @param fightWinCount map of fight wins
     */
    public void setFightWinCount(Map<Playable, Integer> fightWinCount) {
        this.fightWinCount = fightWinCount;
    }
}
