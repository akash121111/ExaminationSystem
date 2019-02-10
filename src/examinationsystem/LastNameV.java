/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package examinationsystem;

import javax.swing.*;

/**
 *
 * @author Dev
 */
public class LastNameV extends InputVerifier {
    public boolean verify(JComponent input)
    {
        JTextField jtf=(JTextField)input;
        if(jtf.getText().matches("[a-zA-z]+([ '-][a-zA-Z]+)*"))
            return true;
         else
        {
            JOptionPane.showMessageDialog(input,"Kindly Enter Your Last Name","Format Ex:Singh ",JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

}
