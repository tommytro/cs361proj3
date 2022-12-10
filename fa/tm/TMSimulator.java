package fa.tm;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * Simulator of a Turing Machine for project 3
 * @author Tommy Trovinger & Josh Fenske
 */

public class TMSimulator {
    public static void main(String[] args) throws FileNotFoundException, IOException {

		String fileName = args[0];
		File file = new File(fileName);
		if(file.exists()){

			TM tm = new TM();
            int numStates = 0;
            int numSymbols = 0;
            String string = "";
			int numTransitionLines = 0;
			int currState = 0;
			int currLine = 0;
			Boolean init = false;


			BufferedReader reader = null;

			//Reads text file
			try {
				reader = new BufferedReader(new FileReader(file));
				String next, line = reader.readLine();
				for (boolean first = true, last = (line == null); !last; first = false) {
					
					//Takes first line as the number of states
					if(currLine == 0) {
						last = ((next = reader.readLine()) == null);
						numStates = Integer.valueOf(line);
						currLine++;
						line = next;

					//Second line which is the number of symbols in the machine (including 0)
					}else if(currLine == 1){
						last = ((next = reader.readLine()) == null);
						numSymbols = Integer.valueOf(line);
						numTransitionLines = (numStates - 1)*(numSymbols+1);
						currLine++;
						line = next;

					//The number of transition lines is the number of states - 1 times the number of symbols + 1
					}else if(currLine > 1 && currLine < numTransitionLines + 2) {

							//Determines the line being read and creates the resulting state. 
							//If it is the first or last line, it makes a start or final state. 
							if(init == false){
								for(int i = 0; i < numStates; i++){
									if(i == 0){
										tm.addStartState(Integer.toString(i));
									}else if(i == numStates - 1){
										tm.addFinalState(Integer.toString(i));
									}else{
										tm.addState(Integer.toString(i));
									}
								}

								init = true;
							}
							
							//Adds the transitions to each state in the machine. There will be 1 transition for each transition symbol in the language.
							for(int symb = 0; symb < numSymbols + 1; symb++){
								
								//add transition using currState as the state to add to
								String[] splitString = line.split(",", 0);
								
								//addTransition variables should be: fromState, toState, writeSymbol, moveDirection'
								tm.addTransition(currState, symb, splitString[0], Integer.valueOf(splitString[1]), splitString[2]);
	
								last = ((next = reader.readLine()) == null);
								line = next;
								currLine++;
							}
							

							currState++;

					//If there is an extra line it is the string to be used as input
					}else if(currLine >= numTransitionLines + 2){ 
							last = ((next = reader.readLine()) == null);
							string = line;
					}
				}
				    
			} finally {
				if (reader != null) try { reader.close(); } catch (IOException logOrIgnore) {}
			}

			//Determines if there is a given input string. If not, run the machine with a blank tape.
			if(string != ""){
				tm.buildTape(string);
			}else{
				tm.buildTape();
			}
			
			//runs the TM
			tm.accepts();

		//outputs error if there is no file on the file path given
		} else {
			System.err.println(file + " does not exists - please check the file path");
		}


	}
}