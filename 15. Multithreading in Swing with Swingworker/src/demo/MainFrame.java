///////////////////////////////////////////////////////////////////////////////
//                                                                           //
// Main Class File:  App.java                                                //
// File:             MainFrame.java                                          //
// Author:           Mitul                                                   //
// Date:             17th May,18.                                            //
///////////////////////////////////////////////////////////////////////////////

package demo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 *  Creates a mainframe. Also, runs a SwingWorker for processing tasks in the background.
 *
 *  Bugs: None known.
 *
 *  @author : Mitul
 */
public class MainFrame extends JFrame {
    private JLabel countLabel = new JLabel("0");
    private JLabel statusLabel = new JLabel("Task not completed.");
    private JButton startButton = new JButton("Start");

    /**
    *   Class Constructor. Creates a main frame to hold the components. Also requires a parameter called title.
    *
    *   @param  title   Holds the title for the main frame.
    *   @throws HeadlessException   Thrown when code that is dependent on a keyboard, display, or mouse but not found.
    * */
    public MainFrame(String title) throws HeadlessException {
        super(title);

        setLayout(new GridBagLayout());

        /* This block creates a GridBag*/
        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.fill = GridBagConstraints.NONE;

        /* This block creates a label on the GridBag and add it to the main frame. */
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 1;
        gridBagConstraints.weighty = 1;
        add(countLabel, gridBagConstraints);

        /* This block creates a label on the GridBag and add it to the main frame. */
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.weightx = 1;
        gridBagConstraints.weighty = 1;
        add(statusLabel,gridBagConstraints);

        /* This block creates a button on the GridBag and add it to the main frame. Also creates an action listener to
        * respond.
        * */
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.weightx = 1;
        gridBagConstraints.weighty = 1;
        add(startButton, gridBagConstraints);

        startButton.addActionListener( (ActionEvent arg0) -> {
            start();
        });


        /* Setting the Frame size, closing operation and making it visible. */
        setSize(300,300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }


    /**
     *  Creates a SwingWorker thread to run tasks in the background.
     */
    private void start(){
        //System.out.println("Start");

        // Creates a swing worker thread. And implements a method doInBackground().

        //SwingWorker<Void,Void> swingWorker = new SwingWorker<>();
        //SwingWorker<Boolean,Void> swingWorker = new SwingWorker<>();
        SwingWorker<Boolean,Integer> swingWorker = new SwingWorker<>(){
            /**
             * It processes task in the background.
             *
             * @return Boolean
             * @throws Exception Thrown only when failed to execute.
             */
            @Override
            //protected Void doInBackground() throws Exception
            protected Boolean doInBackground() throws Exception {
                // TODO: Sleep for 30 iterations.
                for (int i = 0; i < 30; i++) {
                    Thread.sleep(100);
                    //System.out.println("Hello: " + i);

                    // Send chunks of data to process function. Even though it sends only one data at a time but the function
                    // add this values to a list called chunk.
                    publish(i);
                }

                //Default. If you use Void in your method type then you must return null. Otherwise it will throw an error.
                //return null;
                return false;
            }

            /**
             * Receives data chunks from publish methcd calls in doInBackground(). And uses it.
             * @param chunks It holds the value of i we send it via publish function.
             */
            @Override
            protected void process(List<Integer> chunks) {
                //super.process(chunks);
                // TODO : Get the values from chunks and set the value to countLabel like progressbar.
                //int value = chunks.get(chunks.size() - 1);
                int value = chunks.get(chunks.size()-1);

                countLabel.setText("Current Value: " + String.valueOf(value + 1));
            }

            /**
             * It executes at the very end. So, whatever tasks we completed in the doInBackground() then we can show the
             * end result from here.
             */
            @Override
            protected void done() {
                // TODO: Update the status bar.
                //System.out.println("Done");

                // Use the boolean for status variable because it holds the boolean value from SwingWorker class template.
                try {
                    Boolean status = get();
                    //statusLabel.setText("Task completed with status: " + status);
                    statusLabel.setText("Task completed.");
                } catch (InterruptedException | ExecutionException e) {
                    e.printStackTrace();
                }
                //statusLabel.setText("Completed.");
            }
        };

        // Execute SwingWorker
        swingWorker.execute();
    }
}
