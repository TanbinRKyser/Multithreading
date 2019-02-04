///////////////////////////////////////////////////////////////////////////////
// Title:  MultiThreading 15 - SwingWorker Thread.                           //
// Files:  App.java                                                          //
//         MainFrame.java                                                    //
// Author: Mitul                                                             //
// Date:   17th May,18.                                                      //
///////////////////////////////////////////////////////////////////////////////
package demo;

import javax.swing.*;

/**
 *  Main class. Runs the whole application.
 *
 *  <p>Bugs: None known.
 *
 * @author : Mitul
 */
public class App {
    public static void main(String[] args){

        SwingUtilities.invokeLater( ()-> new MainFrame("Main Frame") );
    }
}
