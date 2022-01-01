package edu.team04.entity;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author Jasmine Krahn
 * goes through list of players and pairs them together for fights in
 * different rounds
 */
public class SeasonIterator implements Iterator<List<Playable>> {
    private Season season;
    private List<Playable> orderOfPlayers;
    private int round;
    private final int TWO = 2;

    public SeasonIterator(Season season) {
        this.season = season;
        orderOfPlayers = new ArrayList<>(season.getPlayables());
        round = 0;
    }

    /**
     * checks if there is another round to be played
     * @return whether there is another round to be played
     */
    @Override
    public boolean hasNext() {
        int maxRounds;
        if (season.getPlayables().size() % TWO != 0)
            maxRounds = season.getPlayables().size();
        else
            maxRounds = season.getPlayables().size() - 1;
        if(round < maxRounds)
            return true;
        return false;
    }

    /**
     * reorders players to be paired up for the next round
     * @return the list of players in order of how they will fight for each
     * round
     */
    @Override
    public List<Playable> next() {
        if (orderOfPlayers.size() == TWO) { // returns the original player list if theres only 2 players
            return orderOfPlayers;
        }
        List<Playable> temp = new ArrayList<>();
        if(round == 0) {
            if (orderOfPlayers.size() % TWO != 0) {
                orderOfPlayers.add(null);
            }
            for (int j = 0; j < orderOfPlayers.size() / 2; j++) {
                temp.add(orderOfPlayers.get(j));
                temp.add(orderOfPlayers.get(orderOfPlayers.size() - 1 - j));
            }
            orderOfPlayers = new ArrayList<>(temp);
        } else {
            for (int i = 1; i < orderOfPlayers.size(); i++) {
                orderOfPlayers.set(i, decrement(orderOfPlayers.get(i)));
            }
        }
        round++;
        return orderOfPlayers;
    }

    /**
     * Decrements the playable to the earlier playable in the list.
     * @param playable playable that needs to be decremented
     * @return earlier playable
     */
    private Playable decrement(Playable playable) {
        List<Playable> playables = season.getPlayables();
        if (playables.indexOf(playable) == 1) {
            if (playables.size() % 2 == 0) {
                return playables.get(playables.size() - 1);
            } else {
                return null;
            }
        } else if (playable == null) {
            return playables.get(playables.size() - 1);
        } else {
            return playables.get(playables.indexOf(playable) - 1);
        }
    }
}
