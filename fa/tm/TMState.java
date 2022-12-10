package fa.tm;

import java.util.ArrayList;
import java.util.HashMap;

import fa.State;

/**
 * Date 12/9/22
 * Simulator of a Turing Machine for project 3
 * @author Tommy Trovinger & Josh Fenske
 */

public class TMState extends State{
	

	private ArrayList<TMTransition> delta = new ArrayList<TMTransition>();//delta
	private Boolean isFinal = false; //remembers its type
	
	/**
	 * Default constructor
	 * @param name the state name
	 */
	public TMState(String name){
		initDefault(name);
		this.isFinal = false;
	}
	
	/**
	 * Overlaoded constructor that sets the state type
	 * @param name the state name
	 * @param isFinal the type of state: true - final, false - nonfinal.
	 */
	public TMState(String name, boolean isFinal){
		initDefault(name);
		this.isFinal = true;
	}

	/**
	 * Initializes the default name for the state
	 * @param name
	 */
	private void initDefault(String name ){
		this.name = name;
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
	 * @param writeSymb
	 * @param moveDir
	 */
	public void addTransition(int onSymb, TMState toState, int writeSymb, char moveDir){
		delta.add(new TMTransition(onSymb, toState, writeSymb, moveDir));
	}
	
	/**
	 * Retrieves the state that <code>this</code> transitions to
	 * on the given symbol
	 * @param symb - the alphabet symbol
	 * @return the new state 
	 */
	public TMState getTo(int symb){
		TMState nextState = null;
		for(TMTransition transition : delta){
			if(transition.getTransSymb() == symb){
				nextState = transition.getNextState();
				break;
			}
		}
		return nextState;
	}
	/**
	 * a method to get the symbol to be written on the tape after this transition
	 * @param symb
	 * @return the symbol to be written on the tape
	 */
	public int getWriteSymb(int symb){
		int writeSymb = 0;
		//System.out.println(delta);
		for(TMTransition transition : delta){
			if(transition.getTransSymb() == symb){
				writeSymb = transition.getWrite();
				break;
			}
		}
		return writeSymb;
	}
	/**
	 * a method to get the direction the tape head will move
	 * @param symb
	 * @return the direction on which to move the pointer on the tape
	 */
	public char getDirection(int symb){
		char dir = ' ';
		for(TMTransition transition : delta){
			if(transition.getTransSymb() == symb){
				dir = transition.getDir();
				break;
			}
		}
		return dir;
	}
	
}