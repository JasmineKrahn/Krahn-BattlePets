package edu.team04.boundary;

import edu.team04.entity.*;

import java.util.List;

/**
 * Output Getter implements the outputtable interface to output all the results and such of the battle pets game
 * */
public class OutputGetter implements Outputtable{

    @Override
    public void outputSkillChoices(Skills skills, String petName) {
        System.out.println(petName + " skill choice is: " + skills);
    }

    @Override
    public void outputFightNumber(int fightNumber) {
        System.out.println("Fight #: " + fightNumber);
    }

    @Override
    public void outputPetName(String petName) {
        System.out.println("Pet name: " + petName);
    }

    @Override
    public void outputPetType(PetTypes petType) {
        System.out.println("Pet type: " + petType);
    }

    @Override
    public void outputRoundNumber(int roundNumber) {
        System.out.println("Round #: " + roundNumber);
    }

    @Override
    public void outputHp(String petName, double hp) {
        System.out.println("Pet name: " + petName);
        System.out.println("HP: " + hp);
    }

    @Override
    public void outputDamageInflicted(String petName, Damage damage) {
        double randomDamage = damage.getRandomDamage();
        double conditionalDamage = damage.getConditionalDamage();
        System.out.println("Damage to " + petName + ": ");
        System.out.println(randomDamage + " random damage.");
        System.out.println(conditionalDamage + " conditional damage.");
        System.out.println(damage.calculateTotalDamage() + " total damage.");
    }

    @Override
    public void outputRechargeTime(Skills skill, int skillRechargeTime) {
        System.out.println(skill + " recharge time is: " + skillRechargeTime);
    }

    @Override
    public void outputWinFight(String petName) {
        System.out.println("The fight winner was: " + petName);
    }

    @Override
    public void outputWinBattle(List<Playable> winners) {

        System.out.println("Winners are: ");
        for(int i = 0; i < winners.size(); i++){
            System.out.println(winners.get(i).getPetName());
        }
    }

    @Override
    public void outputWelcomeMessage(){
        System.out.println("Welcome to Battle Pets game!");
    }

    @Override
    public void outputPlayerTurn(String petName){
        System.out.println("It is " + petName + "'s turn.");
    }

    @Override
    public void outputFallenAsleep(String petName){
        System.out.println(petName + " is asleep.");
    }

    @Override
    public void outputBreak(){
        System.out.println();
    }

    @Override
    public void outputBattles(Playable playable, int wins, int losses){
        System.out.println("Player: " + playable.getPetName());
        System.out.println("Battle Wins: " + wins);
        System.out.println("Battle Losses: " + losses);
    }

    @Override
    public void outputFightsWon(Playable playable, int wins){
        System.out.println("Pet: " + playable.getPetName() + " has " + wins + " fight wins.");
    }

    @Override
    public void outputReversalOfFortune(Playable playable, double reversalOfFortuneValue){
        System.out.println(playable.getPetName() + "'s difference in random damage is: " + reversalOfFortuneValue);
    }

    @Override
    public void outputPredictedSkillChoices(Skills skills, String petName) {
        System.out.println(petName + " predicted skill choice is: " + skills);
    }
}
