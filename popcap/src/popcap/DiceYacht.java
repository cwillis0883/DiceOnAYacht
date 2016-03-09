package popcap;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;

public class DiceYacht {
	
	public enum Categories {
		Ones, Twos, Threes, Fours, Fives, Sixes, Sevens, Eights, ThreeOfAKind, 
		FourOfAKind, FullHouse, SmallStraight, LargeStraight, AllDifferent, 
		Chance, AllSame }
	
	private static HashMap<Categories,Integer> categorySum;
	
	public static int getScore(Categories category, int [] values) {
		
		categorySum = getCategorySum(values);
		
		return (categorySum.get(category));
	}
	
	public static Categories getSuggestion(int [] values) {
		
		categorySum = getCategorySum(values);
		
		return getKeyFromValue(categorySum,Collections.max(categorySum.values()));
	}
	
	public static HashMap<Integer,Integer> getOccurrences(int [] values) {
		
		HashMap<Integer,Integer> occurrences = new HashMap<Integer,Integer>();
		
		for (int i = 0; i < values.length; i++) {

			if (!occurrences.containsKey(values[i])) {
				
				int counter = 1;
				occurrences.put(values[i], counter);
				
			} else {
				
				occurrences.replace(values[i], occurrences.get(values[i]) + 1);
			}
		}
		
		return occurrences;
	}

	private static int getSum(int [] values) {
		
		int sum = 0;
		
		for (int i = 0; i < values.length; i++) {
			sum += values[i];
		}
		
		return sum;
	}
	
	private static boolean inSequence(int [] values, Categories category) {
		   
		   switch(category) {
		   
		   case SmallStraight:
			   
			   int [] temp1 = Arrays.copyOfRange(values, 0, values.length-1);
			   int [] temp2 = Arrays.copyOfRange(values,1,values.length);
			   
			   if (inSequence(temp1) || inSequence(temp2)) {
				   return true;
			   }
				   return false; 
			   	   
		   case LargeStraight:
			   for (int i = 0; i < values.length-1; i++) {
				   if ((values[i+1] - values[i]) != 1) {
					   return false;
				   }
			   }
			   return true;
		   default:
			   return false;
		   }
		
		}
	   
	private static boolean inSequence(int [] values) {
		   
		   for (int i = 0; i < values.length-1; i++) {
			   if ((values[i+1] - values[i]) != 1) {
				   return false;
			   }
		   }
		   
		   return true; 
	   }

	public static Categories getKeyFromValue(HashMap<Categories, Integer> map, Object value) {
	    for (Categories category : map.keySet()) {
	      if (map.get(category).equals(value)) {
	        return category;
	      }
	    }
	    return null;
	  }

	public static HashMap<Categories,Integer> getCategorySum(int [] values) {
		
		categorySum = new HashMap<Categories,Integer>();
		
		for (Categories category: Categories.values()) {
			
			switch(category) {
			
			case Ones:
				if (getOccurrences(values).containsKey(1)) {
					categorySum.put(Categories.Ones, getOccurrences(values).get(1));
				}
				break;
			case Twos:
				if (getOccurrences(values).containsKey(2)) {
					categorySum.put(Categories.Twos, getOccurrences(values).get(2) * 2);
				}
				break;
			case Threes:
				if (getOccurrences(values).containsKey(3)) {
					categorySum.put(Categories.Threes, getOccurrences(values).get(3) * 3);
				}
				break;
			case Fours:
				if (getOccurrences(values).containsKey(4)) {
					categorySum.put(Categories.Fours, getOccurrences(values).get(4) * 4);
				}
				break;
			case Fives:
				if (getOccurrences(values).containsKey(5)) {
					categorySum.put(Categories.Fives, getOccurrences(values).get(5) * 5);
				}
				break;
			case Sixes:
				if (getOccurrences(values).containsKey(6)) {
					categorySum.put(Categories.Sixes, getOccurrences(values).get(6) * 6);
				}
				break;
			case Sevens:
				if (getOccurrences(values).containsKey(7)) {
					categorySum.put(Categories.Sevens, getOccurrences(values).get(7) * 7);
				}
				break;
			case Eights:
				if (getOccurrences(values).containsKey(8)) {
					categorySum.put(Categories.Eights, getOccurrences(values).get(8) * 8);
				}
				break;
			case ThreeOfAKind:
				if (getOccurrences(values).size() == 3) {
					categorySum.put(Categories.ThreeOfAKind, 13);
				}
				break;
			case FourOfAKind:
				if (getOccurrences(values).size() == 2) {
					categorySum.put(Categories.FourOfAKind, 12);
				}
				break;
			case FullHouse:
				if (getOccurrences(values).size() == 2) {
					
					boolean houseCheck = true;
					
					for (Integer value : getOccurrences(values).keySet()) {
						
						if (!(getOccurrences(values).get(value) == 2 || getOccurrences(values).get(value) == 3)) {
		
							houseCheck = false;
						}
					}
					
					if (houseCheck) {
						categorySum.put(Categories.FullHouse, 25);
					}
				}
				break;
			case SmallStraight:
				if(getOccurrences(values).size() >= 4 && inSequence(values, Categories.SmallStraight)) {
					categorySum.put(Categories.SmallStraight, 30);
				}
				break;
			case LargeStraight:
				if(getOccurrences(values).size() == 5 && inSequence(values, Categories.LargeStraight)) {
					categorySum.put(Categories.LargeStraight,40);
				}
				break;
			case AllDifferent:
				if (getOccurrences(values).size() == 5) {
					categorySum.put(Categories.AllDifferent, 40);
				}
				break;
			case Chance:
				categorySum.put(Categories.Chance, getSum(values));
				break;
			case AllSame:
				if (getOccurrences(values).size() == 1) {
					categorySum.put(Categories.AllSame, 50);
				}
			default:
				break;
			}
			
		}
		
		return categorySum;
	}
}
