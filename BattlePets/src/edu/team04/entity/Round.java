package edu.team04.entity;

import java.util.HashMap;
import java.util.Map;

/**
 * controls and holds information for a single round
 * @author Jasmine Krahn
 */
public class Round
{

   private int roundNumber;  //what round is being played
   Map<Playable, Damage> damages; //what damages have been dealt

   /**
    * constructor for a round
    */
   public Round(int roundNumber)
   {
      this.roundNumber = roundNumber;
      damages = new HashMap<>();
   }

   /**
    * getter for round number
    * @return round number
    */
   public int getRoundNumber()
   {
      return roundNumber;
   }

   /**
    * setter for round number
    * @param roundNumber
    */
   public void setRoundNumber(int roundNumber)
   {
      this.roundNumber = roundNumber;
   }

   /**
    * getter for damages
    * @return damages
    */
   public Map<Playable, Damage> getDamages() {
      return damages;
   }

   /**
    * setter for damages
    * @param damages
    */
   public void setDamages(Map<Playable, Damage> damages) {
      this.damages = damages;
   }
}