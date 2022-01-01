package edu.team04.entity;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author Makenna Halvensleben
 * Iterable object that holds the data for Season mode.
 */
public class Season implements Iterable<List<Playable>> {
    private List<Playable> playables;
    private List<Battle> battles;

    /**
     * Constructor for Season that initializes instance variables.
     * @param playables list of playables in the season
     */
    public Season(List<Playable> playables) {
        this.playables = playables;
        this.battles = new ArrayList<>();
    }

    /**
     * Getter method for the list of playables in the season.
     * @return list of playables
     */
    public List<Playable> getPlayables() {
        return playables;
    }

    /**
     * Setter method for the list of playables in the season.
     * @param playables list of playables
     */
    public void setPlayables(List<Playable> playables) {
        this.playables = playables;
    }

    /**
     * Getter method for the list of battles in the season.
     * @return list of battles
     */
    public List<Battle> getBattles() {
        return battles;
    }

    /**
     * Setter method for the list of battles in the season.
     * @param battles list of battles
     */
    public void setBattles(List<Battle> battles) {
        this.battles = battles;
    }

    /**
     * Returns a SeasonIterator object that iterates over the Season object.
     * @return SeasonIterator object
     */
    @Override
    public Iterator<List<Playable>> iterator() {
        return new SeasonIterator(this);
    }
}
