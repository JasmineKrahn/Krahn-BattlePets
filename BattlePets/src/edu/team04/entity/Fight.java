package edu.team04.entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Makenna Halvensleben
 * Object that holds the fight data including the rounds and winner.
 */
public class Fight {
    private int fightNumber;
    private List<Round> rounds;
    private Playable winner;
    private Map<Playable, Double> reversalOfFortunes;

    /**
     * Constructor for Fight.
     * @param fightNumber the fight number that indicates which fight in the battle
     */
    public Fight(int fightNumber) {
        this.fightNumber = fightNumber;
        rounds = new ArrayList<>();
        reversalOfFortunes = new HashMap<>();
    }

    /**
     * Getter method for the fightNumber.
     * @return fightNumber
     */
    public int getFightNumber() {
        return fightNumber;
    }

    /**
     * Setter method for the fightNumber.
     * @param fightNumber fightNumber
     */
    public void setFightNumber(int fightNumber) {
        this.fightNumber = fightNumber;
    }

    /**
     * Getter method for the list of rounds.
     * @return the list of rounds
     */
    public List<Round> getRounds() {
        return rounds;
    }

    /**
     * Setter method for the list of rounds.
     * @param rounds the list of rounds
     */
    public void setRounds(List<Round> rounds) {
        this.rounds = rounds;
    }

    /**
     * Getter method for the winner of the fight.
     * @return the winner of the fight
     */
    public Playable getWinner() {
        return winner;
    }

    /**
     * Setter method for the winner of the fight.
     * @param winner the winner of the fight
     */
    public void setWinner(Playable winner) {
        this.winner = winner;
    }

    /**
     * Getter method for the reversal of fortune values for each playable
     * @return reversal of fortune values for each playable
     */
    public Map<Playable, Double> getReversalOfFortunes() {
        return reversalOfFortunes;
    }

    /**
     * Setter method for the reversal of fortune values for each playable
     * @param reversalOfFortunes reversal of fortune values for each playable
     */
    public void setReversalOfFortunes(Map<Playable, Double> reversalOfFortunes) {
        this.reversalOfFortunes = reversalOfFortunes;
    }
}
