package fa.nfa;

import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * This class represent a NFA state.
 * @author Landon and Daniel
 *
 */
public class NFAState extends fa.State{
    
    // For storing transition at each character
	private LinkedHashMap<Character, Set<NFAState>> transitionTable;
    
    //check if the state is final or not.
    private boolean isFinalState;

    /**
     * Default constructor when state is not final
     * 
     * @param name The name of the state
     */
    public NFAState(String name){
    	this.name = name;
        transitionTable = new LinkedHashMap<Character, Set<NFAState>>();
        isFinalState = false;
    }

    /**
     * Constructor if state is final
     * 
     * @param name The name of the state
     * @param isFinal True - indicates a final state
     */
    public NFAState(String name, boolean isFinal){
    	this.name = name;
        transitionTable = new LinkedHashMap<Character, Set<NFAState>>();
        this.isFinalState = isFinal;
    }
    

    /**
     * Adds transition
     * 
     * @param onSymb
     * @param toState
     */
    public void addTransition(char onSymb, NFAState toState){
        if(transitionTable.containsKey(onSymb)){
            transitionTable.get(onSymb).add(toState);
        }else{
            Set<NFAState> temp = new LinkedHashSet<>();
            temp.add(toState);
            transitionTable.put(onSymb, temp);
        }
    }

    /**
     * Returns the set of transition states
     * 
     * @param onSymb The alphabet symbol
     * @return Set<NFAState> A set of states that the given state transitions to 
     * 						 on an alphabet symbol. Empty set if none.
     */
    public Set<NFAState> getTo(char onSymb){
        if(transitionTable.containsKey(onSymb)){
            return transitionTable.get(onSymb);
        }else{
            return new LinkedHashSet<>();
        }
    }

    /**
     * Check if a state is final
     * 
     * @return True if state is final else returns false.
     */
    public boolean isFinal(){
        return isFinalState;
    }


}
