/*********************************************
 * File name: Primary.java
 * Author: Muhammad Sean Arsha Galant
 * NPM: 2206822963
 * Collaborators: Hezekial Octora Yudha Tampubolon
 * 
 * A main class which contains the main method 
 * for setting up the JFrame for the whole application.
 **********************************************/
// import the javax swing package
import javax.swing.*;

public class Primary {
    public static void main(String[] args) {
        // create a new JFrame object with a title for the program
        JFrame frame = new JFrame("Infix -> Postfix Evaluator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // program will close if exit is pressed
        
        frame.add(new PostfixPanel()); //create a new postfix panel and add it to the frame
        
        
        PostfixPanel panel = new PostfixPanel(); // create a new PostfixPanel object
        
        frame.add(panel); // add the panel
        frame.pack(); // pack the frame
        frame.setVisible(true); // make the frame and the panel visible 
    }
}
