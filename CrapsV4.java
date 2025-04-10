/*** Craps
20 points

Write a Java program that investigates the game of craps. Use it to 
analyze your chances of winning a round. Craps is played by a "shooter"
who continually rolls a pair of dice, as decribed below.

To start a round, the shooter makes a "come-out" roll. A come-out roll 
of 2, 3 or 12 is called "craps" or "crapping out", and the shooter loses.
A come-out roll of 7 or 11 is a "natural", and the shooter wins. The other 
possible numbers are the point numbers: 4, 5, 6, 8, 9, and 10. If the shooter 
rolls one of these numbers on the come-out roll, this establishes the "point" 
- to win, the point number must be rolled again before a seven.

By the way, according to the Wizard of Odds site, the house "edge" for a
bet on the pass line, per bet, is 1.41%.
 */

public class CrapsV4 {
	

	public static void main(String args[]) {
		
		CrapsV2 test1 = new CrapsV2();
		MultiDie Die_1 = new MultiDie(6);
		MultiDie Die_2 = new MultiDie(6);
		
		Die_1.roll();
		Die_2.roll();
		
		int combinedDieValue = Die_1.getFaceValue() + Die_2.getFaceValue();
		final int STARTING_MONEY = 10000000; //the total amount of money we have
		int currentMoney = STARTING_MONEY;
		int bet = 1000; //the amount we are betting 
		int point = 0;
		boolean comeOutRoll = true;
		int lossCounter = 0;
		int winCounter = 0;
		int timesPlayed = 0;
		
		
		System.out.println("Die1   Die2    CombinedDieValue   Money   Point   Bet");
		while (currentMoney >= 0)
		{
			
			if (currentMoney == 0)
			{
				System.out.println("You're all out of money! The Game is Over!");
				break;
			}
			
			if (comeOutRoll == true) 
			{
				
				if (combinedDieValue == 2 || combinedDieValue == 3 || combinedDieValue == 12)
				{
					System.out.println(Die_1.getFaceValue()+"      "+Die_2.getFaceValue()+"       "+combinedDieValue+"\t\t  $"+currentMoney+"\t  "+point+"\t"+("  -$" + bet)+"\t Craps!");
					currentMoney = currentMoney-bet;
					lossCounter++;
					
				}
			
				else if(combinedDieValue == 7 || combinedDieValue == 11)
				{
					System.out.println(Die_1.getFaceValue()+"      "+Die_2.getFaceValue()+"       "+combinedDieValue+"\t\t  $"+currentMoney+"\t  "+point+"\t"+"  +$"+(bet)+"\t Natural!");
					currentMoney = currentMoney + bet;
					winCounter++;
				}
				
				else if (combinedDieValue != point) 
				{
					point = combinedDieValue;		
					comeOutRoll = false;
				}
			}
			
			else if(comeOutRoll == false)
			{
				if (combinedDieValue == 7)
				{
					System.out.println(Die_1.getFaceValue()+"      "+Die_2.getFaceValue()+"       "+combinedDieValue+"\t\t  $"+currentMoney+"\t  "+point+"\t"+("  -$" + bet));					
					currentMoney = currentMoney - bet;
					point = -1;
					comeOutRoll = true;
					lossCounter++;
				}
				
				
				if (combinedDieValue == point)
				{	
					System.out.println(Die_1.getFaceValue()+"      "+Die_2.getFaceValue()+"       "+combinedDieValue+"\t\t  $"+currentMoney+"\t  "+point+"\t"+"  +$"+(bet));
					currentMoney = currentMoney + bet;
					point = -1;
					comeOutRoll = true;
					winCounter++;
				}
				
				if (combinedDieValue != point && point == -1)
				{
	
					point = combinedDieValue; 
					
				}		
			}
			
			
			timesPlayed++;
			Die_1.roll();
			Die_2.roll();
			combinedDieValue = Die_1.getFaceValue() + Die_2.getFaceValue();
			
		}
		
		System.out.println("*******************************************************************************************************");
		System.out.println("Loss Counter: " + lossCounter + "\tTimesPlayed: " + timesPlayed + "\tWin Counter: " + winCounter);
		System.out.println("Losses:TimesPlayed: "+ ((double) lossCounter/timesPlayed));
		System.out.println("Wins:TimesPlayed: "+ ((double) winCounter/timesPlayed));
		if (((double) lossCounter/timesPlayed) > ((double) winCounter/timesPlayed))
		{
			System.out.println("Loss Ratio is Higher than Win Ratio.");
		}
		
		else if (((double) winCounter/timesPlayed) > ((double) lossCounter/timesPlayed))
		{
			System.out.println("Win Ratio is Higher than Loss Ratio.");
		}
		System.out.println("Starting Money: $" + STARTING_MONEY);
		System.out.println("Final Money:    $" + currentMoney);
		if (currentMoney < STARTING_MONEY)
		{
			System.out.println("You Lost:      -$"+Math.abs(STARTING_MONEY-currentMoney));
		}
		else if (currentMoney > STARTING_MONEY)
		{
			System.out.println("You Won:       +$"+Math.abs(STARTING_MONEY-currentMoney));
		}
		else if(currentMoney == STARTING_MONEY)
		{
			System.out.println("You Did not Win nor Lose any Money!");
		}
		
	}
	
}
