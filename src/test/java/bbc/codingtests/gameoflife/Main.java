package bbc.codingtests.gameoflife;

import bbc.codingtests.gameoflife.gamestate.GameStateTest;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

/**
 * Created by lotti on 20/02/2017.
 */

/**
 * Main class used to initialise the program running with specific test cases.
 * It opens up a pop up window alongside the game of life grid window.
 * To start the game the user must first choose a test case to run and it will continue until the window is closed.
 * */
public class Main {

    static String new_str = "";
    //Here is where you can change the test cases to ensure it works
    static String test1 = ".*.\n.*.\n.*.";
    static String test2 = "*..\n.*.\n.**";
    static String test3 = "...\n**.\n***";
    static String[] tests = {test1, test2, test3};
    static int testNo;
    static int xLen = 3;
    static int yLen = 3;

    public static void main(String args[]){

        //Game of life frame setup
        JFrame frame = new JFrame("Game of Life");
        JPanel[] panels = new JPanel[9];
        frame.setVisible(true);
        frame.setSize(300,300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Popup window setup
        JFrame opts = new JFrame();
        JPanel ops_panel = new JPanel();
        ops_panel.add(new JLabel("Choose test to run:"));
        DefaultComboBoxModel model = new DefaultComboBoxModel();
        model.addElement("Test 1");
        model.addElement("Test 2");
        model.addElement("Test 3");
        JComboBox box = new JComboBox(model);
        ops_panel.add(box);

        int selected = JOptionPane.showConfirmDialog(null, ops_panel, "Test#", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
        switch (selected) {
            case JOptionPane.OK_OPTION:
                System.out.println("You selected " + box.getSelectedItem());
                String select_box = (String) box.getSelectedItem();
                if(select_box.equals("Test 1")){
                    testNo = 0;
                }
                else if(select_box.equals("Test 2")){
                    testNo = 1;
                }
                else{
                    testNo = 2;
                }
                break;
            case JOptionPane.CANCEL_OPTION:
                System.exit(0);
        }

        JPanel main_panel = new JPanel(new GridLayout(1,1));

        //Inititalises the game of life with empty squares
        JPanel panel = new JPanel(new GridLayout(3,3));
        main_panel.add(panel);
        frame.add(main_panel);

        String[] split = tests[testNo].split("\\n");
        int counter = 0;
        for(String str : split){
            char[] vals = str.toCharArray();
            for(int i = 0; i < xLen; i++){
                JPanel sub_panel = new JPanel();
                String character = Character.toString(vals[i]);
                JLabel label = new JLabel(character, SwingConstants.CENTER);
                label.setSize(50,50);
                label.setFont(new Font(label.getName(), Font.PLAIN, 20));
                sub_panel.add(label);
                sub_panel.setBackground(Color.gray);
                Border border = BorderFactory.createLineBorder(Color.black);
                sub_panel.setBorder(border);
                panel.add(sub_panel);
                panels[counter] = sub_panel;
                counter++;
            }
        }
        frame.repaint();
        frame.revalidate();
        try {
            Thread.sleep(2000);
        }
        catch(InterruptedException e){

        }

        String ans = (String)box.getSelectedItem();

        //Begins running through the loop depending upon which test case is chosen
        int loop_counter = 0;

        while(true) {
            if(loop_counter == 0) {
                panels = runTest(ans);
            }
            else{
                panels = runTest("");
            }
            panel.removeAll();
            for (int i = 0; i < panels.length; i++) {
                panel.add(panels[i]);
            }
            frame.repaint();
            frame.revalidate();
            try {
                Thread.sleep(2000);
            }
            catch(InterruptedException e){

            }
            loop_counter++;
        }
    }

    /**
     * Operation to outline the string to use in the test depending upon which test case is specified.
     * */
    public static JPanel[] runTest(String testNo){
        LifeTest test = new LifeTest();
        String result;
        JPanel[] panels = new JPanel[(xLen*yLen)];
        if(testNo.equals("Test 1")){
            result = test.test(test1);
        }
        else if(testNo.equals("Test 2")){
            result = test.test(test2);
        }
        else if(testNo.equals("Test 3")){
            result = test.test(test3);
        }
        else{
            result = test.test(new_str);
        }

        String[] split = result.split("\\n");
        int counter = 0;
        for(String str : split){
            char[] vals = str.toCharArray();
            for(int i = 0; i < xLen; i++){
                JPanel pan = new JPanel();
                pan.setBackground(Color.gray);
                Border border = BorderFactory.createLineBorder(Color.black);
                pan.setBorder(border);
                String character = Character.toString(vals[i]);
                JLabel label = new JLabel(character, SwingConstants.CENTER);
                label.setSize(50,50);
                label.setFont(new Font(label.getName(), Font.PLAIN, 20));
                pan.add(label);
                panels[counter] = pan;
                counter++;
            }
        }
        new_str = result;
        return panels;

    }


}
