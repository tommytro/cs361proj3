package fa.tm;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.LinkedList;
/**
 * Implementation of a Turing Machine for project 3
 * @author Tommy Trovinger & Josh Fenske
 */
public class TM {
    private Set<TMState> states;
    private TMState start;
    // private Set<Character> transitionChar;
	private LinkedList<Integer> tape;
	private int pos = 0;

    public TM(){
        states = new LinkedHashSet<>();
        // transitionChar = new LinkedHashSet<>();
		tape = new LinkedList<Integer>();
    }
   
	public void buildTape(String string){
		char[] input = string.toCharArray();
		for(char ch : input){
			tape.add(Character.getNumericValue(ch));
		}
		//System.out.println(tape);
	}

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

    public void addFinalState(String name){
		TMState s = checkIfExists(name);
		if( s == null){
			s = new TMState(name, true);
			addState(s);
		} else {
			System.out.println("WARNING: A state with name " + name + " already exists in the TM");
		}
	}

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

		// if(!transitionChar.contains(currState)){
		// 	transitionChar.add((char)currState);
		// }
	}

    public String toString(){
		String string = "";
		for(int i : tape){
			string = string + i;
		}
		return string;
	}

    public Boolean accepts() {
		Boolean accepts = false;
		TMState currState = start;
		//iterate over the chars
		while(accepts == false){

			int tapeChar = tape.get(pos);
			int writeSymb = currState.getWriteSymb(tapeChar);
			char dir = currState.getDirection(tapeChar);
			tape.add(pos, writeSymb);

			currState = currState.getTo(tapeChar);
			System.out.println("current state: " + currState);
			
			if(dir == 'L'){
				System.out.println("Moving L");
				if(pos - 1 < 0){
					pos = 0;
				}else{
					pos--;
				}
			}else if(dir == 'R'){
				System.out.println("Moving R");
				pos++;
			}else{
				System.out.println("No direction");
			}

			System.out.println(tape);

			if(currState.isFinal() == true){
				accepts = true;
				System.out.println(this.toString());
				break;
			}

			
		}
		
		return accepts;
	}

    public Set<TMState> getStates() {
		return states;
	}

	public Set<TMState> getFinalStates() {
		Set<TMState> ret = new LinkedHashSet<TMState>();
		for(TMState s : states){
			if(s.isFinal()){
				ret.add(s);
			}
		}
		return ret;
	}

	public TMState getStartState() {
		return start;
	}

	public TMState getToState(TMState from, char onSymb) {
		return from.getTo(onSymb);
	}

	// public Set<Character> getABC() {
	// 	return transitionChar;
	// }
}

