package fa.tm;
import java.util.LinkedHashSet;
import java.util.Set;
/**
 * Implementation of a Turing Machine for project 3
 * @author Tommy Trovinger & Josh Fenske
 */
public class TM {
    private Set<TMState> states;
    private TMState start;
    private Set<Character> transitionChar;

    public TM(){
        states = new LinkedHashSet<>();
        transitionChar = new LinkedHashSet<>();
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

    public void addTransition(int currState, String goTo, String writeChar, String moveDir){
		TMState from = checkIfExists(String.valueOf(currState));
		TMState to = checkIfExists(goTo);
		if(from == null){
			System.err.println("ERROR: No TM state exists with name " + currState);
			System.exit(2);
		} else if (to == null){
			System.err.println("ERROR: No TM state exists with name " + goTo);
			System.exit(2);
		}
		
		from.addTransition((int)currState, Integer.valueOf(goTo), writeChar.charAt(0), moveDir.charAt(0));

		if(!transitionChar.contains(currState)){
			transitionChar.add((char)currState);
		}
	}

    public String toString(){

		String s = "Q = { ";
        //ADD MORE
		return s;
	}

    public boolean accepts(String input) {
		boolean ret = false;
		char[] inputString = input.toCharArray();
		TMState currState = start;
		//iterate over the chars
		if(!(inputString.length==1 && inputString[0] == 'e')){
			for(char c : inputString){
				currState = currState.getTo(c);
			}
		}
		if(currState.isFinal()){
			ret = true;
		} 
		return ret;
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

	public Set<Character> getABC() {
		return transitionChar;
	}


    public void addString(String string) {
    }
}

