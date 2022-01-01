package edu.team04.boundary;

import java.util.Scanner;
import edu.team04.entity.PetTypes;
import edu.team04.entity.PlayerTypes;
import edu.team04.entity.Skills;
import java.util.List;

/**
 * Input Getter implements the inputtable interface to output all the results and such of the battle pets game
 * */
public class InputGetter implements Inputtable {
    Scanner read = new Scanner(System.in);


    @Override
    public Skills inputSkill(List<Skills> rechargingSkill) {
        Skills chosenSkill = null;
        String check;
        int skill = 0;
        System.out.println("Please input your skill selection: ");
        boolean valid;

        do{
            System.out.println("Choices: 1 for Rock, 2 for Paper, 3 for scissors, 4 for shoot the moon, and 5 for reversal of fortune.");
            check = read.nextLine();
            if(checkStringAreInt(check)){
                skill = Integer.parseInt(check);
            }
            if(skill == 1){
                chosenSkill = Skills.ROCK_THROW;
                valid = true;
            } else if(skill == 2){
                chosenSkill = Skills.PAPER_CUT;
                valid = true;
            } else if(skill == 3){
                chosenSkill = Skills.SCISSORS_POKE;
                valid = true;
            } else if(skill == 4) {
                chosenSkill = Skills.SHOOT_THE_MOON;
                valid = true;
            }else if(skill == 5){
                chosenSkill = Skills.REVERSAL_OF_FORTUNE;
                valid = true;
            }else{
                System.out.println("Invalid key, try again.");
                valid = false;
            }
            for(int i = 0; i < rechargingSkill.size(); i++){
                if(chosenSkill == rechargingSkill.get(i) && rechargingSkill.get(i) != null){
                    valid = false;
                    System.out.println("This skill is currently recharging.");
                }
            }
        }while(!valid);

        return chosenSkill;
    }

    @Override
    public PetTypes inputPetType() {
        PetTypes yourPetType = null;
        String check;
        int pets = 0;
        System.out.println("Please input your pet type: ");

        boolean valid;
        do{
            System.out.println("Choices: 1 for Power, 2 for Speed, 3 for Intelligence.");
            check = read.nextLine();

            if(checkStringAreInt(check)){
                pets = Integer.parseInt(check);
            }

            if(pets == 1){
                yourPetType = PetTypes.POWER;
                valid = true;
            } else if(pets == 2){
                yourPetType = PetTypes.SPEED;
                valid = true;
            } else if(pets == 3){
                yourPetType = PetTypes.INTELLIGENCE;
                valid = true;
            } else {
                System.out.println("Invalid Input, try again.");
                valid = false;
            }
        }
        while(!valid);

        return yourPetType;
    }

    @Override
    public double inputStartingHp() {
        String check;
        double startingHP = 0;
        boolean valid = true;

        do{
            System.out.println("Please input your starting HP: ");
            check = read.nextLine();
            valid = true;

            try{
                startingHP = Double.parseDouble(check);
            }catch(NumberFormatException e){
                valid = false;
            }

            if(startingHP <= 0 || !valid)
            {
                System.out.println("Invalid Input, try again");
            }

        } while(startingHP <= 0 || !valid);

        return startingHP;
    }

    @Override
    public PlayerTypes inputPlayerType() {
        PlayerTypes playerChosen = null;
        String check;
        int playertype = 0;
        boolean valid;
        System.out.println("Please input your player type: ");

        do{
            System.out.println("Choices: 1 for Human, 2 for Computer.");
            check = read.nextLine();

            if(checkStringAreInt(check)){
                playertype = Integer.parseInt(check);
            }

            if(playertype == 1) {
                playerChosen = PlayerTypes.HUMAN;
                valid = true;
            }
            else if(playertype == 2) {
                playerChosen = PlayerTypes.COMPUTER;
                valid = true;
            } else {
                System.out.println("Invalid Input, try again: ");
                valid = false;
            }
        } while(!valid);
        return playerChosen;
    }

    @Override
    public String inputPlayerName() {
        System.out.println("Please input your name: ");
        System.out.println("Names can ONLY start with a letter.");

        String name;
        boolean valid;

        do{
            name = read.nextLine();


            if(name.isEmpty()){
                valid = false;
            } else{
                String check = name.substring(0,1);
                valid = check.matches("[a-zA-Z]");
            }

            if(!valid){
                System.out.println("Error with name. Enter a different name: ");
            }
        }while(!valid);

        return name;
    }

    @Override
    public int inputNumberOfFights() {
        String check;
        int numberOfFights = 0;
        do{
            System.out.println("Please input number of fights: ");
            check = read.nextLine();
            if(checkStringAreInt(check)){
                numberOfFights = Integer.parseInt(check);
            }

            if(numberOfFights <= 0)
            {
                System.out.println("Invalid Input, try again");
            }

        } while(numberOfFights <= 0);
        return numberOfFights;
    }

    @Override
    public int inputRandomSeed() {
        int randomseed = 0;
        String check;
        boolean valid = true;
        do{
            System.out.println("Please input a number as a seed for our random number.");
            check = read.nextLine();
            if(checkStringAreInt(check)){
                randomseed = Integer.parseInt(check);
            }
            else {
                valid = false;
                System.out.println("Invalid Input, try again: ");
            }

        }while(!valid);

        return randomseed;
    }

    @Override
    public int inputPlayAgain() {
        int playAgain = -1;
        String check;

        do {
            System.out.println("Would you like to play again? (1 for yes, 0 for no): ");
            check = read.nextLine();

            if(checkStringAreInt(check)){
                playAgain = Integer.parseInt(check);
            }

            if (playAgain != 0 && playAgain != 1) {
                System.out.println("Invalid Input, try again");
            }
        } while (playAgain != 0 && playAgain != 1);

        return playAgain;
    }

    @Override
    public String inputPetName(){
        System.out.println("Please input the pet name: ");
        System.out.println("Pet names can ONLY start with a letter.");

        boolean valid;
        String petName;

        do{
            petName = read.nextLine();

            if(petName.isEmpty()){
                valid = false;
            } else{
                String check = petName.substring(0,1);
                valid = check.matches("[a-zA-Z]");
            }

            if(!valid){
                System.out.println("Error with pet name. Enter a different name: ");
            }
        }while(!valid);
        
        return petName;
    }

    @Override
    public int inputNumberOfPlayers(){
        int numberOfPlayers = 0;
        String check;
        boolean valid = true;

        do{
            System.out.println("Please input number of players: ");
            check = read.nextLine();
            try{
                Integer.parseInt(check);
                valid = true;
            } catch(NumberFormatException e){
                valid = false;
            }
            
            if(valid){
                numberOfPlayers = Integer.parseInt(check);
            }

            if(numberOfPlayers < 2){
                valid = false;
                System.out.println("Invalid input try again: ");
            }
        }while(!valid);

        return numberOfPlayers;
    }

    @Override
    public Skills inputSkillPrediction(){
        Skills chosenSkill = null;
        System.out.println("Please input your skill prediction: ");
        boolean valid;
        int skill = 0;
        String check;

        do{
            System.out.println("Choices: 1 for Rock, 2 for Paper, 3 for scissors, 4 for shoot the moon, and 5 for reversal of fortune.");

            check = read.nextLine();

            if(checkStringAreInt(check)){
                skill = Integer.parseInt(check);
            }

            if(skill == 1){
                chosenSkill = Skills.ROCK_THROW;
                valid = true;
            } else if(skill == 2){
                chosenSkill = Skills.PAPER_CUT;
                valid = true;
            } else if(skill == 3){
                chosenSkill = Skills.SCISSORS_POKE;
                valid = true;
            } else if(skill == 4) {
                chosenSkill = Skills.SHOOT_THE_MOON;
                valid = true;
            }else if(skill == 5){
                chosenSkill = Skills.REVERSAL_OF_FORTUNE;
                valid = true;
            }else{
                System.out.println("Invalid key, try again.");
                valid = false;
            }

        }while(!valid);

        return chosenSkill;
    }

    @Override
    public int inputMode() {
        String check;
        int mode = 0;
        System.out.println("Please input your mode selection: ");
        boolean valid;

        do {
            System.out.println("Please input a mode: (1 for regular, 2 for season)");
            check = read.nextLine();

            if(checkStringAreInt(check)){
                valid = true;
                mode = Integer.parseInt(check);
            }
            else{
                valid = false;
            }

            if(mode == 1 || mode == 2){
                continue;
            }
            else{
                valid = false;
                System.out.println("Invalid input, please try again: ");
            }

        } while (!valid);
        return mode;
    }

    @Override
    public boolean checkStringAreInt(String check){
        try{
            Integer.parseInt(check);
            return true;
        } catch(NumberFormatException e){
            return false;
        }
    }

}

