package javaProgramming;

import java.awt.event.*;
import java.awt.*;
import javax.swing.*;

public class TimeConversion implements ActionListener{

    //jframe, textfield, area
    JFrame frame = new JFrame();
    JTextField textfield = new JTextField(10);
    JTextArea textarea = new JTextArea(15,20);
    //Jbutton
    JButton convertButton = new JButton();
    //Jlabel
    JLabel convertLabel = new JLabel();

    //constructor
    public TimeConversion(){
        //frame
        frame.setTitle("24 to 12 Hour Time Converter");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500,100);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setLayout(new FlowLayout());

        //textarea
        textarea.setEditable(false);
        textarea.setLineWrap(true);
        textarea.setWrapStyleWord(true);

        //jbutton
        convertButton.setText("Convert");
        convertButton.addActionListener(this);

        //jlabel
        convertLabel.setText("Enter 24 Hour Time (HHMM): ");
        
        frame.add(convertLabel);
        frame.add(textfield);
        frame.add(convertButton);
        frame.setVisible(true);
        frame.add(textarea);

    }
    //action performed method
    @Override
    public void actionPerformed(ActionEvent e) {
        //convert button 
        if(e.getSource() == convertButton){
            String input = textfield.getText();
            try{
                int militaryTime = Integer.parseInt(input);
                String result = convert12HourTime(militaryTime);
                textarea.setText(result);
            }
            catch(NumberFormatException ex){
                textarea.setText("Invald input. Please enter a valid 24-hour time.");
            }
        }
    }

    //method to convert 24-hour time to 12-hour time
    public static String convert12HourTime(int timeMilitary){
        if(timeMilitary < 0 || timeMilitary > 2359){
            return "Invalid time. Please provide a time between 0000 and 2359.";
            }       

        //getting hours and minutes
        int hours = timeMilitary / 100;
        int min = timeMilitary % 100;

        //Error checking - validate if minutes are in valid range
        if(min < 0 || min >= 60){
            return "Invalid time. Minutes must be between 00 and 59.";
        }

        //AM or PM period range
        String period = "AM";
        if(hours == 0){
            //midnight-morning
            hours = 12; 
        } else if (hours == 12){
            //afternoon
            period = "PM"; 
        } else if (hours > 12){
            //night
            hours -=12; 
            period = "PM";
        }

        //return format hours and minutes to always be two digits
        return String.format("%d:%02d%s", hours, min, period);
    }
}

