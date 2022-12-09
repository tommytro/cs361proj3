/**
 * Defines Transitions for TMSimulator
 * @author Tommy Trovinger & Josh Fenske
 */
public class TMTransitions {
    private int currState;
    private int nextState;
    private int writeSymb;
    private char direction;
    
    /**
     * default constructor
     * @param currState
     * @param nextState
     * @param writeSymb
     * @param direction
     */
    public TMTransitions(int currState, int nextState, int writeSymb, String direction) {
        this.setCurrState(currState);
        this.setNextState(nextState);
        this.setWrite(writeSymb);
        if(direction.length() == 0) {
            this.setDirection('R'); //defaulting to R
        }else {
            this.setDirection(direction.charAt(0));
        }
          
    }

    /**
     * Set next state
     * @param nextState int
     */
    public void setNextState(int nextState) {
        this.nextState = nextState;
    }

    /**
     * get the next state
     * @return int
     */
    public int getNextState() {
        return nextState;
    }

    /**
     * Sets the member of the tape alphabet which is written during the
     * execution of this transition.
     * 
     * @param writeSymbol An integer to write on the tape.
     */
    public void setWrite(int writeSymb) {
        this.writeSymb = writeSymb;
    }

    /**
     * get the symbol to be written
     * @return int
     */
    public int getWrite() {
        return writeSymb;
    }

    /**
     * Set Direction L or R
     * @param L/R
     */
    public void setDirection(char direction) {
        if (direction == 'L' || direction == 'R')
            this.direction = direction;
    }

    /**
     * get Direction L or R
     * @return direction L or R
     */
    public char getDirection() {
        return direction;
    }

    /**
     * set currState
     * @param int value of state
     */
    public void setCurrState(int currState) {
        this.currState = currState;
    }

    /**
     * get currState
     * @return int of currState number
     */
    public int getCurrState() {
        return currState;
    }
    
    /**
     * Returns details of current transition
     * @return string
     */
    public String toString() {
        return currState + "," + nextState + "," + writeSymb + "," + direction;
    }
}
