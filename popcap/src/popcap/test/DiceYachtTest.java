package popcap.test;

import java.util.Arrays;

import popcap.DiceYacht;
import popcap.FiveDice;

public class DiceYachtTest {
	
	public static void main(String[] args) {
		
		int MAX_ROLL_COUNT = 12;
		
		FiveDice dice;
		int rollCount;
		
		dice = new FiveDice();
		rollCount = 0;
		
		do {
			
			int [] diceRoll = {dice.getDie1(),dice.getDie2(),dice.getDie3(),dice.getDie4(),dice.getDie5()};
			System.out.println("Dice Roll: " + Arrays.toString(diceRoll));
			System.out.println("Possible Categories: " + DiceYacht.getCategorySum(diceRoll));
			System.out.println("Suggested Category: " + DiceYacht.getSuggestion(diceRoll));
			System.out.println("Score: " + DiceYacht.getScore(DiceYacht.getSuggestion(diceRoll),diceRoll) + "\n");
			dice.roll();
			rollCount++;
			
		} while (rollCount < MAX_ROLL_COUNT);
	}
}
