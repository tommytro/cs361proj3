package fa.tm;

import java.util.ArrayList;
import java.util.HashMap;

import fa.State;

/**
 * Jan 19, 2017
 * Implementation of a DFA state, which
 * mainly contains the information of its
 * neighboring states.
 * @author elenasherman
 *
 */
public class TMState extends State{
	

	private ArrayList<TMTransition> delta = new ArrayList<TMTransition>();//delta
	private boolean isFinal;//remembers its type
	
	/**
	 * Default constructor
	 * @param name the state name
	 */
	public TMState(String name){
		initDefault(name);
		isFinal = false;
	}
	
	/**
	 * Overlaoded constructor that sets the state type
	 * @param name the state name
	 * @param isFinal the type of state: true - final, false - nonfinal.
	 */
	public TMState(String name, boolean isFinal){
		initDefault(name);
		this.isFinal = isFinal;
	}
	
	private void initDefault(String name ){
		this.name = name;
		//delta = new HashMap<Character, TMState>();               //fix
	}
	
	
	/**
	 * Accessor for the state type
	 * @return true if final and false otherwise
	 */
	public boolean isFinal(){
		return isFinal;
	}
	

	/**
	 * Add the transition from <code> this </code> object
	 * @param onSymb the alphabet symbol
	 * @param toState to DFA state
	 * @param writeChar
	 */
	public void addTransition(int currState, int toState, char writeChar, char moveDir){
		delta.add(new TMTransition(currState, toState, writeChar, moveDir));
	}
	
	/**
	 * Retrieves the state that <code>this</code> transitions to
	 * on the given symbol
	 * @param symb - the alphabet symbol
	 * @return the new state 
	 */
	public TMState getTo(char symb){
		TMState ret = delta.get(symb);                                  //fix
		if(ret == null){
			 System.err.println("ERROR: DFAState.getTo(char symb) returns null on " + symb + " from " + name);
			 System.exit(2);
			}
		return delta.get(symb);                                      //fix
	}
	
	
}