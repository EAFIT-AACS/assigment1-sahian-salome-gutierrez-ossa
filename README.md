# DFA Minimization Algorithm

## Student Information
- Full Name: Sahian Salome Gutierrez Ossa
- Class Number:  SI2002-2 (7309) 

## Development Environment
- Operating System:Cross-platform (compatible with Windows, Linux, macOS, and more via the JVM) or Cloud (Replit Online IDE) 
- Programming Language: Java  
- Development Platform: Replit 

## Execution Instructions
## Local Environment
1. Ensure Java is installed.
2. Use any Java-compatible IDE (VSCode, IntelliJ IDEA) or a text editor.
3. Create a file named Main.java and paste the provided code.
4. Compile and run the program.


## Online Environment
1. Open Replit at https://replit.com/ and create a new Java project.  
2. Copy and paste the provided Java code into a file named Main.java.  
3. Run the program and provide the input in the expected format:  
   - The first line contains the number of test cases.  
   - Each test case includes:  
     - The number of states.  
     - The alphabet (space-separated characters).  
     - The final states (space-separated integers).  
     - The transition table (one line per state, first value is the state, followed by its transitions).  
4. The output will display the equivalent state pairs in lexicographical order.  

## Algorithm Explanation

The code implements an algorithm to minimize a Deterministic Finite Automaton (DFA). First, it defines the DFA class, which stores the number of states, the final states, the transition table, and a mapping of alphabet symbols to indices. Then, the MinimizadorDFA class applies the minimization algorithm from Lecture 14 of Kozen's book. It first marks as non-equivalent the pairs of states where one is final and the other is not. Then, in a loop, it marks as non-equivalent the pairs of states that, when processing the same alphabet symbol, transition to states already marked as different. Finally, the unmarked pairs are equivalent. In the Main class, the input is read from the console. For each DFA, the number of states, the alphabet, the final states, and the transition table are read. Then, the minimization algorithm is executed, and the equivalent state pairs are printed in lexicographic order.

## Use case

## Input 
4
4
a b
2 3
0 1 2
1 2 3
2 3 2
3 2 3
6
a b
4 5
0 1 2
1 3 4
2 3 5
3 3 3
4 3 4
5 3 5
4
a b
0 1
0 1 2
1 1 2
2 3 1
3 3 3
4
a
2 3
0 1
1 2
2 3
3 3


## Output
(2, 3) 
(1, 2) (4, 5)
(0, 1)
(2, 3)

