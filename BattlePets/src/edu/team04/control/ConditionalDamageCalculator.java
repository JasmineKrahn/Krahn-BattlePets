package edu.team04.control;
import edu.team04.entity.PetTypes;
import edu.team04.entity.Skills;
import edu.team04.entity.Damage;
import edu.team04.entity.Playable;

/**
 * @author Jasmine Krahn
 * calculates condition damage delivered by pet based on pet type and
 * random damage
 */
public class ConditionalDamageCalculator
{
   private final double POWER = 5;
   private final double SPEED = 12.5;
   private final double INTELLIGENCE_THREE = 3;
   private final double INTELLIGENCE_TWO = 2;
   private final double THREE_QUARTERS = 75;
   private final double ONE_QUARTER = 25;
   private final double TO_MOON = 20;

   /**
    * constructor for conditional damage
    */
   public ConditionalDamageCalculator() {}

   /**
    * calls appropriate method to calculate conditional damage based on
    * pet type
    * @param ranDamage random damage
    * @param petType pet type
    * @param playerSkill skill of player delivering damage
    * @param opponentSkill skill of player receiving damage
    * @param opponent player receiving damage
    * @return damage damage caused by player to opponent
    */
   public Damage calcDamage(double ranDamage, PetTypes petType,
                            Skills playerSkill, Skills opponentSkill,
                            Playable opponent, Skills predictedSkill,
                            double totalDifferences)
   {
      Damage damage;
      if(playerSkill == Skills.SHOOT_THE_MOON)
         damage = calcShootTheMoon(ranDamage, opponentSkill, predictedSkill);
      else if(playerSkill == Skills.REVERSAL_OF_FORTUNE)
         damage = calcReversalOfFortune(ranDamage, totalDifferences);
      else if(petType == PetTypes.POWER)
         damage = calcPowerDamage(ranDamage, playerSkill,opponentSkill);
      else if(petType == PetTypes.SPEED)
         damage = calcSpeedDamage(ranDamage, playerSkill, opponentSkill,
                 opponent);
      else
         damage = calcIntelligenceDamage(ranDamage, playerSkill,
                 opponent);

      return damage;
   }

   /**
    * calculates damage delivered by a power type pet
    * if player chooses rock_throw and their opponent chooses scissors_poke,
    *    the conditional damage is randomDamage times five
    * if player chooses scissors_poke and their opponent chooses paper_cut,
    *    the conditional damage is randomDamage times five
    * if player chooses paper_cut and their opponent chooses rock_throw,
    *     the conditional damage is randomDamage times five
    * @param ranDamage random damage
    * @param playerSkill skill of player delivering damage
    * @param opponentSkill skill of player receiving damage
    * @return damage total damaged delivered
    */
   private Damage calcPowerDamage(double ranDamage, Skills playerSkill,
                                  Skills opponentSkill)
   {
      double conditional = 0;
      if(playerSkill == Skills.ROCK_THROW
              && opponentSkill == Skills.SCISSORS_POKE)
         conditional = ranDamage * POWER;

      else if(playerSkill == Skills.SCISSORS_POKE
              && opponentSkill == Skills.PAPER_CUT)
         conditional = ranDamage * POWER;

      else if(playerSkill == Skills.PAPER_CUT
              && opponentSkill == Skills.ROCK_THROW)
         conditional = ranDamage * POWER;

      return new Damage(ranDamage, conditional);
   }

   /**
    * calculates damage delivered by a speed type pet
    * if player chooses rock_throw, the opponents hp is over 75% of the
    *    starting hp and their opponent does not choose rock_throw,
    *    the conditional damage is 12.5
    * if player chooses scissors_poke, the opponents hp is between 75%  and
    *    25% of the starting hp and their opponent does not choose
    *    scissors_poke, the conditional damage is 12.5
    * if player chooses paper_cut, the opponents hp is between 0%  and
    *    25% of the starting hp and their opponent does not choose
    *    paper_cut, the conditional damage is 12.5
    * @param ranDamage random damage
    * @param playerSkill skill of player delivering damage
    * @param opponentSkill skill of player receiving damage
    * @param opponent player receiving damage
    * @return damage total damaged delivered
    */
   private Damage calcSpeedDamage(double ranDamage, Skills playerSkill,
                                  Skills opponentSkill, Playable opponent)
   {
      double conditional = 0;
      if(playerSkill == Skills.ROCK_THROW
              && opponent.calculateHpPercent() >= THREE_QUARTERS
              && opponentSkill != Skills.ROCK_THROW)
         conditional = SPEED;

      else if(playerSkill == Skills.SCISSORS_POKE
              && opponent.calculateHpPercent() < THREE_QUARTERS
              && opponent.calculateHpPercent() >= ONE_QUARTER
              && opponentSkill != Skills.SCISSORS_POKE)
         conditional = SPEED;

      if(playerSkill == Skills.PAPER_CUT
              && opponent.calculateHpPercent() < ONE_QUARTER
              && opponent.calculateHpPercent() >= 0
              && opponentSkill != Skills.PAPER_CUT)
         conditional = SPEED;

      return new Damage(ranDamage, conditional);
   }

   /**
    * calculates damage delivered by a intelligence type pet
    * if the opponents shoot the moon skill is recharging then two is added
    *    to conditional damage
    * if player chooses rock_throw and opponent chooses scissor_poke,
    *    conditional damage is an additional three
    * if player chooses rock_throw and opponent chooses rock_throw,
    *    conditional damage is an additional two
    * if player chooses scissor_poke and opponent chooses paper_cut,
    *    conditional damage is an additional three
    * if player chooses scissor_poke and opponent chooses scissor_poke,
    *    conditional damage is an additional two
    * if player chooses paper_cut and opponent chooses rock_throw,
    *    conditional damage is an additional three
    * if player chooses paper_cut and opponent chooses paper_cut,
    *    conditional damage is an additional two
    * @param ranDamage random damage
    * @param playerSkill skill of player delivering damage
    * @param opponent player receiving damage
    * @return damage total damaged delivered
    */
   private Damage calcIntelligenceDamage(double ranDamage, Skills playerSkill,
                                         Playable opponent)
   {
      double conditional = 0;
      if(playerSkill == Skills.ROCK_THROW)
      {
         if(opponent.getSkillRechargeTime(Skills.SHOOT_THE_MOON) > 0)
            conditional += INTELLIGENCE_TWO;
         if(opponent.getSkillRechargeTime(Skills.SCISSORS_POKE) > 0)
            conditional += INTELLIGENCE_THREE;
         if(opponent.getSkillRechargeTime(Skills.ROCK_THROW) > 0)
            conditional =+ INTELLIGENCE_TWO;
      }

      else if(playerSkill == Skills.SCISSORS_POKE)
      {
         if(opponent.getSkillRechargeTime(Skills.SHOOT_THE_MOON) > 0)
            conditional += INTELLIGENCE_TWO;
         if(opponent.getSkillRechargeTime(Skills.PAPER_CUT) > 0)
            conditional += INTELLIGENCE_THREE;
         if(opponent.getSkillRechargeTime(Skills.SCISSORS_POKE) > 0)
            conditional += INTELLIGENCE_TWO;
      }

      else
      {
         if(opponent.getSkillRechargeTime(Skills.SHOOT_THE_MOON) > 0)
            conditional += INTELLIGENCE_TWO;
         if(opponent.getSkillRechargeTime(Skills.ROCK_THROW) > 0)
            conditional += INTELLIGENCE_THREE;
         if(opponent.getSkillRechargeTime(Skills.PAPER_CUT) > 0)
            conditional += INTELLIGENCE_TWO;
      }

      return new Damage(ranDamage, conditional);
   }

   /**
    * calculates conditional damage dealt when shoot the moon is selected
    * skill. if the player guesses their opponents skill correctly,
    * conditional damage becomes 20
    * @param ranDamage random damage
    * @param opponentSkill skill chosen by opponent
    * @param predictedSkill skill player predicts opponent will use
    * @return damage total damage delivered
    */
   private Damage calcShootTheMoon(double ranDamage, Skills opponentSkill,
                                    Skills predictedSkill)
   {
      double conditional = 0;
      if(opponentSkill == predictedSkill)
         conditional = TO_MOON;
      return new Damage(ranDamage, conditional);
   }

   /**
    * calculates conditional damage if reverse of fortune skill is selected.
    * conditional damage is the sum of differences between dealt and
    * taken random damage of all rounds
    * @param ranDamage random damage
    * @param totalDifferences total of difference between damage dealt and
    *                      taken each round
    * @return damage total damage delivered
    */
   private Damage calcReversalOfFortune(double ranDamage,
                                       double totalDifferences)
   {
      double conditional = totalDifferences * -1;
      ranDamage += conditional;
      return new Damage(ranDamage, conditional);
   }
}