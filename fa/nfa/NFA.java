package fa.nfa;

import java.util.LinkedHashSet;
import java.util.Set;

import fa.State;
import fa.dfa.DFA;
import fa.dfa.DFAState;

/**
 * This simulates a Nondeterministic Finite Autonoma
 * @author Landon and Daniel
 *
 */
public class NFA implements NFAInterface {
	
	private Set<NFAState> states;
	private String start;
	private Set<Character> ordAbc;

	public NFA(){
		states = new LinkedHashSet<NFAState>();
		ordAbc = new LinkedHashSet<Character>();
	}

	@Override
	public void addStartState(String name) {
		NFAState n = checkIfExists(new NFAState(name));
		if(n == null){;
			addState(name);
		} else {
			System.out.println("WARNING: A state with name " + name + " already exists in the DFA");
		}
		start = name;
		
	}

	/**
	 * Check if a state with such name already exists
	 * @param name
	 * @return null if no state exist, or SFAState object otherwise.
	 */
	private NFAState checkIfExists(NFAState name) {
		NFAState ret = null;
		for(NFAState s : states){
			if(s.getName().equals(name.getName())){
				ret = s;
				break;
			}
		}
		return ret;
	}

	@Override
	public void addState(String name) {
		NFAState s = new NFAState(name);
		NFAState n = checkIfExists(s);
		if( n == null){
			states.add(s);
		} else {
			System.out.println("WARNING: A state with name " + name + " already exists in the DFA");
		}
	}

	@Override
	public void addFinalState(String name) {
		NFAState s = new NFAState(name,true);
		NFAState n = checkIfExists(s);
		if( n == null){
			states.add(s);
		} else {
			System.out.println("WARNING: A state with name " + name + " already exists in the DFA");
		}
	}

	@Override
	public void addTransition(String fromState, char onSymb, String toState) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Set<? extends State> getStates() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<? extends State> getFinalStates() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public State getStartState() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<Character> getABC() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DFA getDFA() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<NFAState> getToState(NFAState from, char onSymb) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<NFAState> eClosure(NFAState s) {
		// TODO Auto-generated method stub
		return null;
	}

}
