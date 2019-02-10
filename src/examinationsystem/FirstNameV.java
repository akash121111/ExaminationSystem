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
public class FirstNameV extends InputVerifier {
    public boolean verify(JComponent input)
    {
        JTextField jtf=(JTextField)input;
        if(jtf.getText().matches("[A-Z][a-zA-Z]*"))
            return true;
        else
        {
            JOptionPane.showMessageDialog(input,"Kindly Enter Your First Name","Format Ex:Akash",JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
    }
    
}
