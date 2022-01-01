package edu.team04.entity;

public class Player {
    private PlayerTypes playerType;
    private String playerName;

    /**
     * @return Constructor of Player
     */
    public Player(PlayerTypes playerType, String playerName) {
        this.playerType = playerType;
        this.playerName = playerName;
    }

    /**
     * @return Returns the player type
     */
    public PlayerTypes getType() {
        return playerType;
    }

    /**
     * @return Returns the player name
     */
    public String getPlayerName() {
        return playerName;
    }
}
