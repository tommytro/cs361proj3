package fa.tm;
/**
 * Defines Transitions for TMSimulator
 * @author Tommy Trovinger & Josh Fenske
 */
public class TMTransition {
    private int transSymb;
    private TMState nextState;
    private char writeSymb;
    private char moveDir;
    
    /**
     * default constructor
     * @param currState
     * @param nextState
     * @param writeSymb
     * @param moveDir
     */
    public TMTransition(int transSymb, TMState nextState, char writeSymb, char moveDir) {
        this.setTransSymb(transSymb);
        this.setNextState(nextState);
        this.setWrite(writeSymb);
        this.setDir(moveDir);
    }

    /**
     * Set transition symbol
     * @param transSymb
     */
    public void setTransSymb(int transSymb){
        this.transSymb = transSymb;
    }

    /**
     * Get transition symbol
     * @param transSymb
     */
    public int getTransSymb(){
        return this.transSymb;
    }

    /**
     * Set next state
     * @param nextState int
     */
    public void setNextState(TMState nextState) {
        this.nextState = nextState;
    }

    /**
     * get the next state
     * @return int
     */
    public TMState getNextState() {
        return nextState;
    }

    /**
     * Sets the member of the tape alphabet which is written during the
     * execution of this transition.
     * 
     * @param writeSymbol An integer to write on the tape.
     */
    public void setWrite(char writeSymb) {
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
    public void setDir(char moveDir) {
        if (moveDir == 'L' || moveDir == 'R')
            this.moveDir = moveDir;
    }

    /**
     * get Direction L or R
     * @return direction L or R
     */
    public char getDir() {
        return moveDir;
    }
    
    // /**
    //  * Returns details of current transition
    //  * @return string
    //  */
    // public String toString() {
    //     return currState + "," + nextState + "," + writeSymb + "," + moveDir;
    // }
}
