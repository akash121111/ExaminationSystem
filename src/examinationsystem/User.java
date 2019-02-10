/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package examinationsystem;

/**
 *
 * @author Dev
 */
public class User {
    private String CorrecAnswer;
    
        public User(){}
        public User(String _CorrecAnswer){
        this.CorrecAnswer=_CorrecAnswer;
        
        }
        public String getCorrecAnswer(){
            return this.CorrecAnswer;
        }
       
}