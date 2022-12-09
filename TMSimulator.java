

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


		BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(file));
            String next, line = reader.readLine();
            for (boolean first = true, last = (line == null); !last; first = false, line = next) {
                last = ((next = reader.readLine()) == null);
				
                if (first) {
                    System.out.println("Number of states line: " + line);
					numStates = Integer.valueOf(line);
					counter = 1;
                } else if (last) {
                    System.out.println("string line: " + line);
					lastLine = line;
					string = line;
                } else {
					if(counter == 1){
						System.out.println("symbols line: " + line);
						symbols = Integer.valueOf(line);
						counter = 0;
					}else{
						System.out.println("transition line: " + line);

					}
                    
                }
            }
        } finally {
            if (reader != null) try { reader.close(); } catch (IOException logOrIgnore) {}
        }

			// Scanner scan = new Scanner(file);

            // while((currLine = scan.nextLine().trim()) != null){
            //     if(counter == 0){
            //         //the first line is the number of states in the TM
            //         numStates = Integer.parseInt(currLine);
            //     }else if(counter == 1){
            //         //the second line is the number of symbols in the language
            //         symbols = Integer.parseInt(currLine);
            //     }else if(counter > 1){
            //         //Add


            //     }
            //     System.out.println(currLine);
            //     lastLine = currLine;
            //     counter++;
            // }
			// System.out.println(lastLine);
            // string = lastLine;

            
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
			// scan.close();
		} else {
			System.err.println(file + " does not exists - please check the file path");
		}
	}
}