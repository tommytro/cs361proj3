

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.StringTokenizer;

public class TMSimulator {
    public static void main(String[] args) throws FileNotFoundException {
		//The file name is passed as an argument
		String fileName = args[0];
		File file = new File(fileName);
		if(file.exists()){
			//create a DFA instance -- you need to write DFA.java that
			//implements DFAInterface.java
			// TM tm = new TM();
            int numStates = 0;
            int symbols = 0;
            int acceptStates = 0;
            String string = "";
            String currLine = "";
            String lastLine = "";
            int counter = 0;

			Scanner scan = new Scanner(file);

            while((currLine = scan.nextLine().trim()) != null){
                if(counter == 0){
                    //the first line is the number of states in the TM
                    numStates = Integer.parseInt(currLine);
                }else if(counter == 1){
                    //the second line is the number of symbols in the language
                    symbols = Integer.parseInt(currLine);
                }else if(counter > 1){
                    //Add


                }
                System.out.println(currLine);
                lastLine = currLine;
                counter++;
            }
            string = lastLine;

            
            // //the second line is the number of symbols in the language
            // symbols = Integer.parseInt(scan.nextLine().trim());

            // String startStateName = scan.nextLine().trim();
            // tm.addStartState(startStateName);



			// //using tokenizer to split the string
			// StringTokenizer tk = new StringTokenizer(finalStates, " ");
			// while(tk.hasMoreTokens()){
			// 	tm.addFinalState(tk.nextToken());
			// }
			
			

			// //get the string of other states and split in on space too
			// String otherStates = scan.nextLine().trim();
			// tk = new StringTokenizer(otherStates, " ");
			// while(tk.hasMoreTokens()){
			// 	tm.addState(tk.nextToken());
			// }

			// //read in the transactions
			// String trans = scan.nextLine();
			// tk = new StringTokenizer(trans, " ");
			// while(tk.hasMoreTokens()){
			// 	char[] tran = tk.nextToken().toCharArray();
			// 	tm.addTransition(String.valueOf(tran[0]), tran[1], String.valueOf(tran[2]));
			// }
			scan.close();
		} else {
			System.err.println(file + " does not exists - please check the file path");
		}
	}
}