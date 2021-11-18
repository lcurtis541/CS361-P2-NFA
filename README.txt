# Project 2: NDFA Machine

* Author: Landon Curtis & Daniel Rao
* Class: CS361 0012
* Semester: Fall 2021

## Overview

This program simulates a Non Deterministic Finite Autonoma.
It uses a specifically formatted input file to test strings
and determine if they are accepted by the machine. It also allows
you to convert the Non Deterministic Finite Autonoma to a Deterministic
Finite Autonoma.


## Compiling and Using

You will need to compile all java files using javac on each java file.
In the 21 folder run the command: "javac ./fa/State.java ./fa/dfa/DFADriver.java 
./fa/dfa/DFAInterface.java ./fa/dfa/DFA.java ./fa/dfa/DFAState.java ./fa/FAInterface.java
./fa/nfa/NFA.java ./fa/nfa/NFADriver.java ./fa/nfa/NFAInterface.java ./fa/nfa/NFAState.java".
"java fa.nfa.NFADriver $" is how you run the program where the '$' must be replaced
by a specifically formatted test file. The fa.nfa.NFADriver is how it traverses through
the folders if ran from the P2 folder. Three tests can be found in the ./tests/
folder so an example to run would be: "java fa.dfa.DFADriver ./tests/p2tc3.txt"

## Discussion

We experienced a few problems while creating this program. The biggest problem we faced was a
.getDFA method. It also took a lot of research and time to be able to understand the Depth First 
Search traversal needed to find the eclosures. Once we got that done we had to brainstorm the
 getDFA method. We ended up using Linked Hash Maps to store the visited states in. This allowed us
 to implement the getDFA method and have it be fairly small. We also had to research the Breath First
 Search, where this and all of our other resources will be listed below. Implementing the NFA itself
 was fairly simple as we have covered it so much in class, its just these two methods especially that
 we found challenging.

## Testing

This project has built-in testing. In the documentation provided for this project,
it shows the correct outputs to the three test files we were given. When running our program,
one can easily see that all of our outputs match exactly to what is expected. We also added some
tests of our own to further test our implementation.

This can also be tested by creating your own file. If you design a NFA, and follow the
instructions on how to format the test file, it will show you if certain strings are accepted
or not. You can compare your own results to what the program says.


## Sources

https://www.youtube.com/watch?v=jwtx6GVPdyw&ab_channel=Jos%C3%A9Vidal
https://www.javatpoint.com/java-hashmap
https://www.geeksforgeeks.org/depth-first-search-or-dfs-for-a-graph/
https://docs.oracle.com/javase/8/docs/api/java/util/Map.html
----------
This README template is using Markdown. To preview your README output, you can copy your file contents to a Markdown editor/previewer such as [https://stackedit.io/editor](https://stackedit.io/editor).

