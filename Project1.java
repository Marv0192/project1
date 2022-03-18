import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Scanner;

public class Project1 {

	public static void main(String[] args) throws FileNotFoundException {
		File testFile = new File("Example.txt");
		Scanner scr = new Scanner(testFile);
		String emptySubSet = "";
		HashMap<String, String> stateTransitionMap = new HashMap<>();
		int startingIndex = -1;
		
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
		
		//print out all the states(keys) and transitions(values)
		for(Entry<String, String> e : stateTransitionMap.entrySet()) {
			System.out.println("State:" + e.getKey() + " Transitions: " + e.getValue());
		}
		
		//create an array of all the states and pass to findPowerSet()
		Object[] states = stateTransitionMap.keySet().toArray();
		System.out.print("\nPower Set: { ");
		findPowerSet(states, emptySubSet, startingIndex);
		System.out.print("}\n");
		
		for(Entry<String, String> e : stateTransitionMap.entrySet()) {
			System.out.print("E(" + e.getKey() + ") = " + e.getKey());
			E(stateTransitionMap, e.getKey());
		}

	}
	
	private static void E(HashMap<String, String> stateTransitionMap, String key) {
		String currTransition = stateTransitionMap.get(key);
		if(currTransition.isEmpty()) {
			System.out.println();
			return;
		}
		
		System.out.print(currTransition);
		key = Character.toString(currTransition.charAt(currTransition.length()-1));
		E(stateTransitionMap, key);
		
	}

	static void findPowerSet(Object[] states, String currSubSet, int start) {
		int length = states.length;
		
		if (start == length) {
			return;
		}
		
		System.out.print("{" + currSubSet + "} ");
		
		for(int i = start + 1; i < length; i++) {
			currSubSet += states[i];
			findPowerSet(states, currSubSet, i);
			currSubSet = currSubSet.substring(0, currSubSet.length()- 1);
		}
	}
}
