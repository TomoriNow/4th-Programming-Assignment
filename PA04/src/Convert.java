/*********************************************
 * File name: Convert.java
 * Author: Muhammad Sean Arsha Galant
 * NPM: 2206822963
 * Collaborators: Hezekial Octora Yudha Tampubolon
 * 
 * A class which implements the algorithm for 
 * infix to postfix transformation and the 
 * algorithm for postfix expression evaluation.
 **********************************************/

// importing the Java utility package
import java.util.*;

public class Convert {
    // declare and encode the operators as integer constants
    private static final int ADD = '+';
    private static final int SUB = '-';
    private static final int MUL = '*';
    private static final int DIV = '/';
    private static final int POW = '^';
    public static String error = "";

    // method to get and return the error message updated throughout the program
    public String getError() {
        return error;
    }

    // method to convert infix notation to postfix notation
    public String infix2postfix(String infix) {
        Stack<Character> stack = new Stack<>(); // create a new Stack object called stack
        ArrayList<String> result = new ArrayList<>(); // create a new ArrayList called result
        StringTokenizer tokens = new StringTokenizer(infix, "[ \\+\\-\\^\\*\\(\\)\\/]", true); // create new StringTokenizer object which evaluates the necessary operators (as tokens) with a regex expression
        error = ""; // reset the error message string every time the method is called

        // iteratively checks each token/operator
        while (tokens.hasMoreTokens()) {
            String output = tokens.nextToken(); // assign each token as a String
            
            if (output.matches("\\d+")) { // if the token matches one or more digits, it is added to the result ArrayList
                result.add(output);
            } else if (output.matches("[+\\-*/]")) { // If the token is an operator, it compares the precedence of the operator with the operators already in the stack
                while (!stack.empty() && precedence(stack.peek()) >= precedence(output.charAt(0))) {
                    result.add(stack.pop().toString()); // Operators with higher precedence are popped and added to the result ArrayList
                }
                stack.push(output.charAt(0)); // The current operator is added back into the stack
            } else if (output.matches("[\\^\\(]")) { // if the token is a caret or open parenthesis operator, push it into the stack
                stack.push(output.charAt(0));
            } else if (output.matches("\\)")) { // if it is a closing parenthesis, keep popping the stack and add to result Arraylist until open parenthesis is found
                while (!stack.empty() && stack.peek() != '(') {
                    result.add(stack.pop().toString()); 
                }
                if (!stack.empty()) {
                    stack.pop(); 
                } else { //if no open parenthesis is found, update the error message.
                    error = "Missing open parenthesis";
                }
            }
        }

        while (!stack.empty()) { // any remaining operators in the stack are popped and added to result ArrayList
            result.add(stack.pop().toString());
        }

        return String.join(" ", result); // return the result as a single String 
    }

    // method to evaluate the postfix expression 
    public String eval(String inputString) {
        Stack<Long> calculate = new Stack<>(); // create a new stack object called calculate
        StringTokenizer tokens = new StringTokenizer(inputString, " ", true); // create a new StringTokenizer object called tokens
        error = ""; // reset the error message String
        
        while (tokens.hasMoreTokens()) { // iteratively loop over each token/operator in the postfix expression
            String output = tokens.nextToken(); // setting the next token/operator to check
    
            if (output.matches("\\d+")) { // if the token matches one or more digits, push it into the stack as a Long integer
                calculate.push(Long.parseLong(output));
            } else { // else, turn turn it into a char type and go through the switch case
                char operator = output.charAt(0);  
                
                // switch case to check each operator
                switch (operator) { 
                    case '(' -> error = "Missing closed parenthesis"; // if the parenthesis mismatch is detected, the error message String will be updated accordingly
                    case ')' -> error = "Missing open parenthesis";
                    case ADD, SUB, MUL, DIV, POW -> calculateBinaryOperation(calculate, operator); // other operators will call the calculateBinaryOperation method
                    default -> {} // default switch case
                }
            }
        }
    
        if (!calculate.isEmpty()) { // After evaluating the postfix expression, the final result is popped from the stack and returned as a string
            return Long.toString(calculate.pop());
        } else { //  If the stack is empty, an empty string is returned
            return "";
        }
    }
    
    private void calculateBinaryOperation(Stack<Long> calculate, int operator) {
        if (calculate.size() < 2) {
            error = "Missing operand"; // if there are not enough operands in the stack, update the error message
        } else {
            Long op1 = calculate.pop(); // else, pop both operands and calculate them based on the operator in the switch case
            Long op2 = calculate.pop();
    
            switch (operator) {
                case ADD -> calculate.push(op2 + op1); // adding the operands and push result to the calculate stack
                case SUB -> calculate.push(op2 - op1); // subtracting the operands and push result to the calculate stack
                case MUL -> calculate.push(op2 * op1); // multiplying the operands and push result to the calculate stack
                case DIV -> {
                    if (op1 == 0) {
                        calculate.push(op2); // division by zero case, push 1 operand into the stack and update the error message
                        error = "Division by zero";
                    } else {
                        calculate.push(op2 / op1); // divide the operands and push result to the calculate stack
                    }
                }
                case POW -> calculate.push((long) Math.pow(op2, op1)); // exponentiate the operands and push them to the calculate stack
                default -> {} // default case
            }
        }
    }
    
    private static int precedence(char operator) { // method which returns the precedence value of each operator
        return switch (operator) {
            case ADD, SUB -> 1; // addition and subtraction have precedence of 1
            case MUL, DIV -> 2; // multiplication and division have precedence of 2
            case POW -> 3; // power/exponentiation has the highest precedence of 3
            case '(', ')' -> 0; // parentheses has the lowest precedence of 0
            default -> -1; // if the operator is not recognized, return -1
        };
    }    
}
