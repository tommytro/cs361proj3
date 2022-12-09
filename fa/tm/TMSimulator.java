package fa.tm;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.StringTokenizer;


public class TMSimulator {
    public static void main(String[] args) throws FileNotFoundException, IOException {

		String fileName = args[0];
		File file = new File(fileName);
		if(file.exists()){

			TM tm = new TM();
            int numStates = 0;
            int numSymbols = 0;
            int acceptStates = 0;
            String string = "";
			int numTransitionLines = 0;
            int counter = 0;
			int currState = 0;
			int currLine = 0;
			int outOf = 0;


			BufferedReader reader = null;
			try {
				reader = new BufferedReader(new FileReader(file));
				String next, line = reader.readLine();
				for (boolean first = true, last = (line == null); !last; first = false) {
					
					//Takes first line as the number of states
					if(currLine == 0) {
						last = ((next = reader.readLine()) == null);
						System.out.println("Number of states line: " + line);
						numStates = Integer.valueOf(line);
						currLine++;
						line = next;

					//Second line which is the number of symbols in the machine (including 0)
					}else if(currLine == 1){
						last = ((next = reader.readLine()) == null);
						System.out.println("symbols line: " + line);
						numSymbols = Integer.valueOf(line);
						numTransitionLines = (numStates - 1)*(numSymbols+1);
						currLine++;
						line = next;

					//The number of transition lines is the number of states - 1 times the number of symbols + 1
					}else if(currLine > 1 && currLine < numTransitionLines+2) {
							for(int i = 0; i < numSymbols + 1; i++){
								System.out.println("transition " + (i+1) + " for state " + currState + " is " + line);
								//add transition using currState as the state to add to
								String[] splitString = line.split("[,], 0");
								
								//addTransition variables should be: fromState, toState, writeSymbol, moveDirection
								tm.addTransition(currState, splitString[0], splitString[1], splitString[2]);
								
								last = ((next = reader.readLine()) == null);
								line = next;
								currLine++;
							}
							currState++;

					//If there is an extra line this is the string line
					}else if(currLine >= numTransitionLines + 2){ 
							last = ((next = reader.readLine()) == null);
							System.out.println("string line: " + line);
							string = line;
							tm.addString(string);
					}
				}     
			} finally {
				if (reader != null) try { reader.close(); } catch (IOException logOrIgnore) {}
			}

		} else {
			System.err.println(file + " does not exists - please check the file path");
		}
	}
}