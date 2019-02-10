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
public class DOBV extends InputVerifier{
    public boolean verify(JComponent input)
    {
         JTextField jtf=(JTextField)input;
         if(jtf.getText().matches("^(0[1-9]|[12][0-9]|3[01])[- /.](0[1-9]|1[012])[- /.](19|20)\\d\\d$"))
          return true;
         else
        {
            JOptionPane.showMessageDialog(input,"Kindly Enter Your Date of Birth","Format Ex:DD/MM/YYYY ",JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }
    
}
