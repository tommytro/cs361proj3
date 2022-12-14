package fa.tm;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Date 12/9/22
 * Implementation of a Turing Machine for project 3
 * @author Tommy Trovinger & Josh Fenske
 */
public class TM {
    private Set<TMState> states;
    private TMState start;
    // private Set<Character> transitionChar;
	private ArrayList<Integer> tape;
	private int pos = 0;

    public TM(){
        states = new LinkedHashSet<>();
        // transitionChar = new LinkedHashSet<>();
		tape = new ArrayList<Integer>();
    }
   
	/**
	 * Builds the tape using a string, or constructs a blank tape if no input string
	 * @param string
	 */
	public void buildTape(String string){
		char[] input = string.toCharArray();
		for(char ch : input){
			tape.add(Character.getNumericValue(ch));
		}
		//System.out.println(tape);
	}
	public void buildTape(){

		tape.add(0);

	}
	/**
	 * adds a starting state to the TM
	 * @param name
	 */
    public void addStartState(String name){
        TMState s = checkIfExists(name);
		if(s == null){
			s = new TMState(name);
			addState(s);
		} else {
			System.out.println("WARNING: A state with name " + name + " already exists in the TM");
		}
		start = s;
    }

    /**
     * Add a state to the set of states
     * @param name
     */
    public void addState(String name){
		TMState s = checkIfExists(name);
		if( s == null){
			s = new TMState(name);
			addState(s);
		} else {
			System.out.println("WARNING: A state with name " + name + " already exists in the TM");
		}
	}

    /**
     * Alternate way to add to the state set. 
     * @param s
     */
    private void addState(TMState s){
		states.add(s);
	}

	/**
	 * A way to check if a state exists in the TM
	 * @param name
	 * @return the desired state if it exists in the machine
	 */
    private TMState checkIfExists(String name){
		TMState ret = null;
		for(TMState s : states){
			if(s.getName().equals(name)){
				ret = s;
				break;
			}
		}
		return ret;
	}

	/**
	 * Adds a specified state to the machine as a final (halting) state
	 * @param name
	 */
    public void addFinalState(String name){
		TMState s = checkIfExists(name);
		if( s == null){
			s = new TMState(name, true);
			addState(s);
		} else {
			System.out.println("WARNING: A state with name " + name + " already exists in the TM");
		}
	}

	/**
	 * Adds a transition to a state
	 * @param currState the state the transition is on
	 * @param onSymb the symbol we transition with
	 * @param goTo the state to move to
	 * @param writeChar the character that is written on the tape
	 * @param moveDir the direction the head moves along the tape
	 */
    public void addTransition(int currState, int onSymb, String goTo, int writeChar, String moveDir){
		TMState from = checkIfExists(String.valueOf(currState));
		TMState to = checkIfExists(goTo);
		if(from == null){
			System.err.println("ERROR: No TM from state exists with name " + currState);
			System.exit(2);
		} else if (to == null){
			System.err.println("ERROR: No TM to state exists with name " + goTo);
			System.exit(2);
		}
		
		from.addTransition(onSymb, to, writeChar, moveDir.charAt(0));
	}
	/**
	 * 
	 * @return the current state of the TMs tape
	 */
    public String toString(){
		String string = "";
		for(int i : tape){
			string = string + i;
		}
		return string;
	}
	/**
	 * Runs the TM, starting at the start state and moving through the 
	 * different states depending on the tape
	 */
    public void run() {
		Boolean halts = false;
		TMState currState = start;
		//iterate over the chars
		while(halts == false){
			
			int tapeChar = tape.get(pos);
			int writeSymb = currState.getWriteSymb(tapeChar);
			char dir = currState.getDirection(tapeChar);
			tape.set(pos, writeSymb);

			currState = currState.getTo(tapeChar);
			
			if(dir == 'L'){
				if(pos - 1 < 0){
					tape.add(0, 0);
					pos = 0;
				}else{
					pos--;
				}
			}else{
				if(pos + 1 >= tape.size()){
					tape.add(0);
				}else{

				}
				pos++;
			}

			if(currState.isFinal() == true){
				halts = true;
				System.out.println(toString());
				break;
			}

			
		}
	}
	/**
	 * @return returns a set of the states of the machine
	 */
    public Set<TMState> getStates() {
		return states;
	}
	/**
	 * @return Returns a set of the final states of the machine
	 */
	public Set<TMState> getFinalStates() {
		Set<TMState> ret = new LinkedHashSet<TMState>();
		for(TMState s : states){
			if(s.isFinal()){
				ret.add(s);
			}
		}
		return ret;
	}

	/**
	 * 
	 * @return returns the starting state of the machine
	 */
	public TMState getStartState() {
		return start;
	}

	/**
	 * gets the next state based on our current state and the tape input
	 * @param from the state we move from
	 * @param onSymb the symbol we move on
	 * @return the state we will move to based on the current state and input from the tape
	 */
	public TMState getToState(TMState from, char onSymb) {
		return from.getTo(onSymb);
	}
}

