package edu.team04.entity;

/**
 * @author Makenna Halvensleben
 * Holds the data needed to create a Pet and Player object by using the Builder pattern.
 */
public class PlayerSettings {

    private double startingHp;
    private PetTypes petType;
    private PlayerTypes playerType;
    private String petName;
    private String playerName;

    /**
     * Constructor for PlayerSettings.
     * @param builder instance of the PlayerSettingsBuilder
     */
    private PlayerSettings(PlayerSettingsBuilder builder) {
        if (builder.startingHp == null) {
            throw new IllegalStateException("startingHp cannot be null.");
        }
        if (builder.petType == null) {
            throw new IllegalStateException("petType cannot be null.");
        }
        if (builder.playerType == null) {
            throw new IllegalStateException("playerType cannot be null.");
        }
        if (builder.petName == null) {
            throw new IllegalStateException("petName cannot be null.");
        }
        if (builder.playerName == null) {
            throw new IllegalStateException("playerName cannot be null.");
        }
        this.startingHp = builder.startingHp;
        this.petType = builder.petType;
        this.playerType = builder.playerType;
        this.petName = builder.petName;
        this.playerName = builder.playerName;
    }

    /**
     * Getter method for the startingHp.
     * @return startingHp
     */
    public double getStartingHp() {
        return startingHp;
    }

    /**
     * Getter method for the petType.
     * @return petType
     */
    public PetTypes getPetType() {
        return petType;
    }

    /**
     * Getter method for the playerType.
     * @return playerType
     */
    public PlayerTypes getPlayerType() {
        return playerType;
    }

    /**
     * Getter method for the petName.
     * @return petName
     */
    public String getPetName() {
        return petName;
    }

    /**
     * Getter method for the playerName.
     * @return playerName
     */
    public String getPlayerName() {
        return playerName;
    }

    /**
     * Builder class for PlayerSettings.
     */
    public static class PlayerSettingsBuilder {
        private Double startingHp;
        private PetTypes petType;
        private PlayerTypes playerType;
        private String petName;
        private String playerName;

        /**
         * Constructor for the PlayerSettingsBuilder.
         */
        public PlayerSettingsBuilder() {
        }

        /**
         * Sets the startingHp.
         * @param startingHp startingHp
         * @return instance of the builder
         */
        public PlayerSettingsBuilder withStartingHp(Double startingHp) {
            this.startingHp = startingHp;
            return this;
        }

        /**
         * Sets the petType.
         * @param petType petType
         * @return instance of the builder
         */
        public PlayerSettingsBuilder withPetType(PetTypes petType) {
            this.petType = petType;
            return this;
        }

        /**
         * Sets the playerType.
         * @param playerType playerType
         * @return instance of the builder
         */
        public PlayerSettingsBuilder withPlayerType(PlayerTypes playerType) {
            this.playerType = playerType;
            return this;
        }

        /**
         * Sets the petName.
         * @param petName petName
         * @return instance of the builder
         */
        public PlayerSettingsBuilder withPetName(String petName) {
            this.petName = petName;
            return this;
        }

        /**
         * Sets the playerName.
         * @param playerName playerName
         * @return instance of the builder
         */
        public PlayerSettingsBuilder withPlayerName(String playerName) {
            this.playerName = playerName;
            return this;
        }

        /**
         * Builds the PlayerSettings object.
         * @return instance of PlayerSettings
         */
        public PlayerSettings build() {
            return new PlayerSettings(this);
        }
    }

}
