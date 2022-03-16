import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Project1 {

	public static void main(String[] args) throws FileNotFoundException {
		File testFile = new File("Example.txt");
		Scanner scr = new Scanner(testFile);
		
		String state = "";
		String emptyPSet = "";
		
		ArrayList<String> transitions = new ArrayList<>();
		
		int startingIndex = -1;
		
		while(scr.hasNext()) {
			String line = scr.nextLine();
			String split[] = line.split(",", 2); //split the line into the states and transitions
			state += split[0]; //add the states to the state array list
			
			if(split[1].equals("empty")) {
				transitions.add(null); //if transition element is "empty" then put null
			}
			else {
				ArrayList<Character> temp = new ArrayList<>();
				for(int i = 0; i < split[1].length(); i++) {
					if(Character.isDigit(split[1].charAt(i))) {
						temp.add(split[1].charAt(i));
					}
				}
				transitions.add(temp.toString()); //if transition element is a digit then add it to transition array list
			}
		}
		
		findPowerSet(state, emptyPSet, startingIndex);

	}
	
	static void findPowerSet(String states, String pSet, int start) {
		int length = states.length();
		
		if (start == length) {
			return;
		}
		
		System.out.println(pSet);
		
		for(int i = start + 1; i < length; i++) {
			pSet += states.charAt(i);
			findPowerSet(states, pSet, i);
			pSet = pSet.substring(0, pSet.length()- 1);
		}
	}
	
	

}
