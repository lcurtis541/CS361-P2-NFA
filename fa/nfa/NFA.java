package fa.nfa;

import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;

import fa.State;
import fa.dfa.DFA;

public class NFA implements fa.nfa.NFAInterface {

    private NFAState startState;
    private LinkedHashSet<NFAState> nfaStatesSet;
    private LinkedHashSet<Character> alphabetSet;

    /**
     * Default constructor
     */
    public NFA() {
        alphabetSet = new LinkedHashSet<Character>(); // Will hold all the alphabets
        nfaStatesSet = new LinkedHashSet<NFAState>(); // Will hold all the nfa States
    }

    @Override
    public void addStartState(String name) {

        // Check if state exists
        NFAState state = getState(name);

        // if state does not exist than create a start state.
        if (state == null) {
            NFAState st_State = new NFAState(name);
            // After the st_State has been created than add the state to the nfaStateSet
            nfaStatesSet.add(st_State);
            startState = st_State; // set to the global start state. There can only be one start state.
        }
    }

    private NFAState getState(String name) {

        NFAState state = null; // State to be returned after been found.

        // check if state exist, if true than state is returned otherwise null is
        // returned

        for (NFAState st : nfaStatesSet) {
            if (st.getName().equals(name)) {
                state = st;
                return state;
            }
        }
        return state;
    }

    @Override
    public void addState(String name) {
        // Adding the new state
        nfaStatesSet.add(new NFAState(name));

    }

    @Override
    public void addFinalState(String name) {
        // Using the second construcctor to build the final state.
        NFAState state = new NFAState(name, true);
        // Adding the state to the set.
        nfaStatesSet.add(state);

    }

    @Override
    public void addTransition(String fromState, char onSymb, String toState) {

        // Builds the transition
        getState(fromState).addTransition(onSymb, getState(toState));

        if (!alphabetSet.contains(onSymb) && onSymb != 'e') {
            alphabetSet.add(onSymb);
        }
    }

    @Override
    public Set<? extends State> getStates() {
        // return the set of nfa states
        return nfaStatesSet;
    }

    @Override
    public Set<? extends State> getFinalStates() {
        // return a set of all the final states
        LinkedHashSet<NFAState> finalStates = new LinkedHashSet<>();
        // Scan through all the states (nfaStateSet) and check if their isfinal flag is
        // true and than returns them as a set after collecting through the scan. If not
        // found than returns empty.
        for (NFAState state : nfaStatesSet) {
            if (state.isFinal()) {
                finalStates.add(state);
            }
        }

        return finalStates;
    }

    @Override
    public State getStartState() {
        // return the start state
        return startState;
    }

    @Override
    public Set<Character> getABC() {
        // return alphabet set
        return alphabetSet;
    }

    @Override
    public DFA getDFA() {
        // New DFA is initialized here.
        DFA dfa = new DFA();
        // Keeps track of visited states
        Map<Set<NFAState>, String> visitedStates = new LinkedHashMap<>();

        // Get the closure of the NFA's start state
        Set<NFAState> states = eClosure(startState);
        // Add to visited sates set
        visitedStates.put(states, states.toString());

        LinkedList<Set<NFAState>> queue = new LinkedList<>();
        // Adds the set of states to the end of the queue
        queue.add(states);
        // Sets the start state of the DFS
        dfa.addStartState(visitedStates.get(states));

        while (!queue.isEmpty()) {

            // Queue - removes and returns the head first element of the list.
            states = queue.poll();

            for (char c : alphabetSet) {
                LinkedHashSet<NFAState> setOne = new LinkedHashSet<>();
                for (NFAState st : states) {
                    setOne.addAll(st.getTo(c));
                }
                LinkedHashSet<NFAState> setTwo = new LinkedHashSet<>();
                for (NFAState st : setOne) {
                    setTwo.addAll(eClosure(st));
                }
                if (!visitedStates.containsKey(setTwo)) {
                    visitedStates.put(setTwo, setTwo.toString());
                    queue.add(setTwo);

                    if (containsFinalState(setTwo)) {
                        dfa.addFinalState(visitedStates.get(setTwo));
                    } else {
                        dfa.addState(visitedStates.get(setTwo));
                    }
                }

                // Add transitions to the DFA
                dfa.addTransition(visitedStates.get(states), c, visitedStates.get(setTwo));
            }
        }
        return dfa;
    }

    /**
     * Checks to see if a set of states contains a final state
     * 
     * @param states
     * @return boolean
     */
    private boolean containsFinalState(Set<NFAState> states) {
        boolean containFinalState = false; // value to be returned
        for (NFAState state : states) {
            if (state.isFinal()) {
                containFinalState = true; // final state has been found from the list of all the states.
                break;
            }
        }
        return containFinalState;
    }

    @Override
    public Set<NFAState> getToState(NFAState from, char onSymb) {
        return from.getTo(onSymb);
    }

    @Override
    public Set<NFAState> eClosure(NFAState s) {
        /* Keeps track of visited states in the depth first state */
        LinkedHashSet<NFAState> l = new LinkedHashSet<>();
        return depthFirstSearch(l, s);
    }

    /**
     * Perform a depth first search to get a list of all reachable states from a
     * state on the empty string 'e'
     * 
     * @param vState
     * @param state
     * @return Set<NFAState>
     */
    private Set<NFAState> depthFirstSearch(LinkedHashSet<NFAState> vState, NFAState state) {
        LinkedHashSet<NFAState> visitedStates = vState;
        LinkedHashSet<NFAState> eClosureSet = new LinkedHashSet<>();

        eClosureSet.add(state);

        // If there is a state to go to an empty transition.
        if (!state.getTo('e').isEmpty() && !visitedStates.contains(state)) {
            visitedStates.add(state);
            for (NFAState nfa : state.getTo('e')) {
                eClosureSet.addAll(depthFirstSearch(visitedStates, nfa));
            }
        }
        return eClosureSet;
    }

}