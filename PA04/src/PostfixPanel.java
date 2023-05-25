/*********************************************
 * File name: PostfixPanel.java
 * Author: Muhammad Sean Arsha Galant
 * NPM: 2206822963
 * Collaborators: Hezekial Octora Yudha Tampubolon
 * 
 * A class which extends JPanel for setting up 
 * the GUI, events, and a listener.
 **********************************************/

// import the java swing and java awt packages
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

// PosfixPanel class that extends JPanel
public class PostfixPanel extends JPanel {
    // declare the variables for JLabel and JTextField for the GUI
    private JLabel infixLabel, postfixLabel, resultLabel, errorLabel; 
    private JTextField infixField;
    private JLabel postfixRes, finalRes, errorMessage;
    
    // Create the constructor for the PostfixPanel
    public PostfixPanel() {
        //setting the layout using GridLayout() manager (4x2 rows by columns)
        setLayout(new GridLayout(4, 2));
        
        // setting the JLabels for the GUI and their respective strings on the initial display
        infixLabel = new JLabel("Enter infix expression: ");
        postfixLabel = new JLabel("Postfix expression: ");
        postfixRes = new JLabel("");
        resultLabel = new JLabel("Result: ");
        finalRes = new JLabel("");
        errorLabel = new JLabel("Error Messages: ");
        errorMessage = new JLabel("");
         
        
        // Create a new JTextField object of size 10
        infixField = new JTextField(10);
        infixField.addActionListener(new PostifxListener()); //add an action Listener from the PostfixListener private class
        
        // add the labels and the textfield to the GUI panel
        add(infixLabel); add(infixField); 
        add(postfixLabel); add(postfixRes);
        add(resultLabel); add(finalRes);
        add(errorLabel); add(errorMessage);
        
        // setting the font
        Font font = new Font("Courier", Font.BOLD, 14);
    
        // setting the fonts for the labels accordingly
        infixLabel.setFont(font); 
        postfixLabel.setFont(font); postfixRes.setFont(font);
        resultLabel.setFont(font); finalRes.setFont(font);
        errorLabel.setFont(font); errorMessage.setFont(font);
        
        // set the background color of the JTextfield to yellow
        infixField.setBackground(Color.YELLOW);
        
        setPreferredSize(new Dimension(600, 150)); // set the dimension of the panel
        setBackground(Color.gray); // set the background color of the panel to gray
    }
    
    // Listener object that that implements the ActionListener interface and waits for events and carries them out
    private class PostifxListener implements ActionListener {
        // actionPerformed method/constructor
        public void actionPerformed(ActionEvent event) {
            String text = infixField.getText(); // get the text from JTextfield
            
            Convert notation = new Convert(); // create new Convert Object
            String postfix = notation.infix2postfix(text); // call the method to convert infix to postfix notation from the Convert class
            String result = notation.eval(postfix); // call the method to evaluate the postfix notation and return the result
            String errorString = "[" + notation.getError() + "]"; // create the error string by calling the error message method from the Convert class
                        
            postfixRes.setText(postfix); // set the text on the GUI panel to the new postfix notation
            finalRes.setText(result); // set the result on the GUI panel to the new evaluated result
            errorMessage.setText(errorString); // set the error string (if an error occurs) to the returned error message
        }
    }
}
