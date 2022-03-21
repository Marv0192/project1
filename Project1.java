import java.io.File;
import java.io.FileNotFoundException;
import java.text.CharacterIterator;
import java.text.StringCharacterIterator;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Scanner;

public class Project1 {

	public static void main(String[] args) throws FileNotFoundException {
		File testFile = new File("Example.txt");
		Scanner scr = new Scanner(testFile);
		HashMap<String, String> stateTransitionMap = new HashMap<>();
		ArrayList<ArrayList<Character>> powerSet = new ArrayList<>();
		int startingIndex = -1;
		String emptySubSet = "";
		
		while(scr.hasNext()) {
			String line = scr.nextLine();
			String[] split = line.split(",", 2); //split the line into the states and transitions
			
			if(split[1].equals("empty")) {
				stateTransitionMap.put(split[0], ""); //if transition is empty add state as the key 
														//and "" as the value to h-map
			}
			else {
				String temp = "";
				for(int i = 0; i < split[1].length(); i++) {
					if(Character.isDigit(split[1].charAt(i))) { 		//if the character in transition is a digit build a string of all
						temp += Character.toString(split[1].charAt(i));	//the digits	
						
					}
				}
				stateTransitionMap.put(split[0], temp); //add the state as the key and the string of digits as the value
			}
		}
		scr.close();
		
		//create an array of all the states and pass to findPowerSet()
		Object[] states = stateTransitionMap.keySet().toArray();
		System.out.print("Power Set: ");
		findPowerSet(states, emptySubSet, startingIndex, powerSet);
		System.out.println(powerSet.toString());
		
		//for each state in our map find E(state)
		for(Entry<String, String> e : stateTransitionMap.entrySet()) {
			System.out.print("E(" + e.getKey() + ") = " + e.getKey() + " ");
			E(stateTransitionMap, e.getKey());
		}

	}
	
	private static void E(HashMap<String, String> stateTransitionMap, String key) {
		String currTransition = stateTransitionMap.get(key);
		if(currTransition.isEmpty()) {
			System.out.println();
			return;
		}
		
		CharacterIterator iterator = new StringCharacterIterator(currTransition);
		while(iterator.current() != CharacterIterator.DONE) {
			System.out.print(iterator.current() + " ");
			iterator.next();
		}
		
		key = Character.toString(currTransition.charAt(currTransition.length()-1));
		E(stateTransitionMap, key);
		
	}

	static void findPowerSet(Object[] states, String currSubSet, int start, ArrayList<ArrayList<Character>> powerSet) {
		int length = states.length;
		
		if (start == length) {
			return;
		}
		
		ArrayList<Character> temp = new ArrayList<>();
		CharacterIterator iterator = new StringCharacterIterator(currSubSet);
		while(iterator.current() != CharacterIterator.DONE) {
			temp.add(iterator.current());
			iterator.next();
		}
		powerSet.add(temp);
		
		for(int i = start + 1; i < length; i++) {
			currSubSet += states[i];
			findPowerSet(states, currSubSet, i, powerSet);
			currSubSet = currSubSet.substring(0, currSubSet.length()- 1);
		}
	}
	
}
