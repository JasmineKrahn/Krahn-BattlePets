package edu.team04.entity;

import edu.team04.boundary.IOManager;

import java.util.*;

public class Pet implements Playable {
    private String petName;
    private PetTypes petType;
    private Player player;
    private int playerUid;
    private double currentHp;
    private double startingHp;
    private Skills predictedSkill;
    private Map<Skills, Integer> rechargeTimes = new HashMap<Skills, Integer>();
    private Random rng;

    /**
     * Creates a new Pet using PetBuilder
     * Checks for null input
     */
    private Pet(PetBuilder builder) {
        if (builder.petName == null) {
            throw new IllegalStateException("petName cannot be null.");
        }
        if (builder.petType == null) {
            throw new IllegalStateException("petType cannot be null.");
        }
        if (builder.player == null) {
            throw new IllegalStateException("player cannot be null.");
        }
        if (builder.startingHp == null) {
            throw new IllegalStateException("startingHp cannot be null.");
        }

        if (builder.playerUid == null) {
            throw new IllegalStateException("playerUid cannot be null.");
        }

        this.petName = builder.petName;
        this.petType = builder.petType;
        this.player = builder.player;
        this.playerUid = builder.playerUid;
        this.startingHp = builder.startingHp;
        this.currentHp = builder.startingHp;
        this.predictedSkill = null;
        this.rng = new Random();
        this.rng.setSeed(1234);

        rechargeTimes.put(Skills.ROCK_THROW, 0);
        rechargeTimes.put(Skills.PAPER_CUT, 0);
        rechargeTimes.put(Skills.SCISSORS_POKE, 0);
        rechargeTimes.put(Skills.SHOOT_THE_MOON, 0);
        rechargeTimes.put(Skills.REVERSAL_OF_FORTUNE, 0);
    }

    /**
     * @return Returns the player's name
     */
    @Override
    public String getPlayerName() {
        return player.getPlayerName();
    }

    /**
     * @return Returns the pets name
     */
    @Override
    public String getPetName() {
        return petName;
    }

    /**
     * @return Returns the player's type
     */
    @Override
    public PlayerTypes getPlayerType() {
        return player.getType();
    }

    /**
     * @return Returns the pet's type
     */
    @Override
    public PetTypes getPetType() {
        return petType;
    }

    /**
     * @return Returns the pet's current HP
     */
    @Override
    public double getCurrentHp() {
        return currentHp;
    }

    /**
     * Uses inputSkill to choose a skill
     * Sets the predictedSkill attribute if Shoot the Moon is chosen
     * If player is a computer, sets predictedSkill to a random skill
     * @return Returns the chosen skill
     */
    @Override
    public Skills chooseSkill() {
        // predict random skill when choosing STM
        Skills inputtedSkill = null;
        int randomChooseSkill;

        if (player.getType() == PlayerTypes.HUMAN) {
            inputtedSkill = IOManager.getInstance().getInputtable().inputSkill(findRechargingSkill());
            if (inputtedSkill == Skills.SHOOT_THE_MOON)
                predictedSkill = IOManager.getInstance().getInputtable().inputSkillPrediction();
        }

        if (player.getType() == PlayerTypes.COMPUTER) {
            List<Skills> invalidSkills = findRechargingSkill();
            List<Skills> validSkills = new ArrayList<Skills>();

            if (!invalidSkills.contains(Skills.PAPER_CUT))
                validSkills.add(Skills.PAPER_CUT);
            if (!invalidSkills.contains(Skills.ROCK_THROW))
                validSkills.add(Skills.ROCK_THROW);
            if (!invalidSkills.contains(Skills.SCISSORS_POKE))
                validSkills.add(Skills.SCISSORS_POKE);
            if (!invalidSkills.contains(Skills.REVERSAL_OF_FORTUNE))
                validSkills.add(Skills.REVERSAL_OF_FORTUNE);
            if (!invalidSkills.contains(Skills.SHOOT_THE_MOON))
                validSkills.add(Skills.SHOOT_THE_MOON);

            randomChooseSkill = rng.nextInt(validSkills.size());
            inputtedSkill = validSkills.get(randomChooseSkill);
        }

        int randomPredictSkill = rng.nextInt(5);
        if (inputtedSkill == Skills.SHOOT_THE_MOON) {
            if (player.getType() == PlayerTypes.COMPUTER) {
                if (randomPredictSkill == 0)
                    predictedSkill = Skills.PAPER_CUT;
                else if (randomPredictSkill == 1)
                    predictedSkill = Skills.ROCK_THROW;
                else if (randomPredictSkill == 2)
                    predictedSkill = Skills.SCISSORS_POKE;
                else if (randomPredictSkill == 3)
                    predictedSkill = Skills.SHOOT_THE_MOON;
                else if (randomPredictSkill == 4)
                    predictedSkill = Skills.REVERSAL_OF_FORTUNE;
            }
        }
        return inputtedSkill;
    }

    /**
     * This method is called by the game controlling classes to update the pet's hp based on the damage inflicted
     */
    @Override
    public void updateHp(double hp) {
        currentHp -= hp;
    }

    /**
     * Resets the pet's current hp to its starting hp
     */
    @Override
    public void resetHp() {
        currentHp = startingHp;
    }

    /**
     * Sets the pet's hp to the provided value
     */
    @Override
    public void setCurrentHp(double currentHp) {
        this.currentHp = currentHp;
    }

    /**
     * @return Returns true if the pet's hp > 0, false otherwise
     */
    @Override
    public boolean isAwake() {
        if (currentHp > 0)
            return true;
        else
            return false;
    }

    /**
     * returns predicted skill
     */
    @Override
    public Skills getSkillPrediction() {
        return predictedSkill;
    }

    /**
     * @return Returns the pet's current percent of hp
     */
    @Override
    public double calculateHpPercent() {
        return (100 * (currentHp/startingHp));
    }

    /**
     * @return Returns the pet's starting hp
     */
    @Override
    public double getStartingHp() {
        return startingHp;
    }

    /**
     * Called by the game controlling classes.
     * Resets the pet's hp to its starting hp
     * Resets all skills to what they were at the start of the fight
     */
    @Override
    public void reset() {
        resetHp();
        for (Skills i : rechargeTimes.keySet()) {
            rechargeTimes.put(i, 0);
        }
    }

    /**
     * Decrements the recharge times for all recharging skills
     */
    @Override
    public void decrementRechargeTimes() {
        for (Skills i : rechargeTimes.keySet()) {
            if (rechargeTimes.get(i) > 0) {
                rechargeTimes.put(i, rechargeTimes.get(i)-1);
            }
        }
    }

    /**
     * Sets the recharge time for the given skill
     */
    @Override
    public void setRechargeTime(Skills skill, int rechargeTime) {
        rechargeTimes.put(skill, rechargeTime);
    }

    /**
     * @return Returns the unique id for this playable.  The id is set during construction (in the constructor of the concrete playable).
     */
    @Override
    public int getPlayableUid() {
        return playerUid;
    }

    /**
     * Sets the playableUid to the id provided.  This should be implemented but not called.
     * It has special use for Doug's own code.
     * @param playableUid The unique id to assign to the current playable object
     */
    @Override
    public void setPlayableUid(int playableUid) {
        playerUid = playableUid;
    }

    /**
     * @return Returns the current recharge time for the provided skill enumeration
     */
    @Override
    public int getSkillRechargeTime(Skills skill) {
        return rechargeTimes.get(skill);
    }

    /**
     * @return Returns the skill that is recharging
     */
    public List<Skills> findRechargingSkill() {
        List<Skills> rechargingSkills = new ArrayList<Skills>();

        for (Skills i : rechargeTimes.keySet()) {
            if (rechargeTimes.get(i) > 0)
                rechargingSkills.add(i);
        }

        return rechargingSkills;
    }


    /////////////////////////////////////////////////////////////////////

    /**
     * Builder class used to build a Pet
     */
    public static class PetBuilder {
        private String petName;
        private PetTypes petType;
        private Player player;
        private Integer playerUid;
        private Double startingHp;

        /**
         * default constructor for PetBuilder
         */
        public PetBuilder() {
        }

        /**
         * @return Returns the Pet with a name
         */
        public PetBuilder withPetName(String petName) {
            this.petName = petName;
            return this;
        }

        /**
         * @return Returns the Pet with a type
         */
        public PetBuilder withPetType(PetTypes petType) {
            this.petType = petType;
            return this;
        }

        /**
         * @return Returns the Pet with a player
         */
        public PetBuilder withPlayer(Player player) {
            this.player = player;
            return this;
        }

        /**
         * @return Returns the Pet with a starting HP
         */
        public PetBuilder withStartingHp(Double startingHp) {
            this.startingHp = startingHp;
            return this;
        }

        public PetBuilder withPlayerUid(Integer playerUid) {
            this.playerUid = playerUid;
            return this;
        }

        /**
         * @return Returns the completed Pet
         */
        public Pet build() {
            return new Pet(this);
        }
    }
}