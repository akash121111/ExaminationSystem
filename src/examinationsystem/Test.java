/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package examinationsystem;

import static examinationsystem.StudentHome.CN;
import static examinationsystem.UserLogin.NM;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.Timer;

/**
 *
 * @author Dev
 */
public class Test extends javax.swing.JFrame {
String connectionURl="jdbc:derby://localhost:1527/ExaminationSystem";
       Connection Connection=null;
    PreparedStatement ps=null;
    Statement st=null;
    ResultSet rs=null;
     ResultSet rs1=null;
    
  
   private String courseName;
    private int Score;
    private int Unattempt;
   
    /**
     * Creates new form Test
     */
    public Test() {
        initComponents();
        this.setLocationRelativeTo(null);
       timer();
        Fill2();
       int p=1;
       
       nextQuestions();
       U.setVisible(false);
       
        
    }
    
    public Test(String courseName){
         initComponents();
        
        this.courseName = courseName;
          this.setLocationRelativeTo(null);
        timer();
        Fill2();
       // Fill();
       nextQuestions();
      
    }
   
    
    public void timer(){
        try{
        
        System.out.println ("T is " + courseName);
         Class.forName("org.apache.derby.jdbc.ClientDriver").newInstance();
            Connection = DriverManager.getConnection(connectionURl,"Akash","Akash");
            String sql="select Hours,Minutes  from Course where CourseName=?";
      
            ps=Connection.prepareStatement(sql);
           ps.setString(1, courseName);
            
            rs=ps.executeQuery();
            
            if(rs.next())
            {
                H.setText(rs.getString("Hours"));
                M.setText(rs.getString("Minutes"));
              
                
            }
            
                S.setText("00");
          Timer();
            
        }
         catch(SQLException se)
        {
            se.printStackTrace();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        
    }
    public void Timer(){
    
    Timer timer = new Timer(1000, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            
               int min=Integer.parseInt(M.getText());
              int  hou=Integer.parseInt(H.getText());
              int sec=Integer.parseInt(S.getText());
              
           
            if(min==0)
    {
        min=59;
        hou--;
    }
           if(sec==0)
    {
        sec=59;
        min--;
    }
         
        
    
            if(hou<0)
            {
               System.out.println("Time up");
            }
          sec--;
          H.setText(Integer.toString(hou));
          M.setText(Integer.toString(min));
          S.setText(Integer.toString(sec));
          
          
        }
    });
        timer.start();
    }

    public void Fill()
    {
        try{
           
            
           Class.forName("org.apache.derby.jdbc.ClientDriver").newInstance();
       Connection=DriverManager.getConnection(connectionURl,"Akash","Akash");
      
      String sql="select QId,Question,Option1,Option2,Option3,Option4 from QueRec where CourseName=?";
       ps=Connection.prepareStatement(sql);
       ps.setString(1, courseName);
       
       
       rs=ps.executeQuery();
       
       while(rs.last())
       {
           QI.setText(rs.getString("QId"));
           Q.setText(rs.getString("Question"));
           O1.setText(rs.getString("Option1"));
           O2.setText(rs.getString("Option2"));
           O3.setText(rs.getString("Option3"));
           O4.setText(rs.getString("Option4"));
          
          //p++;
           
       }
      
    }
       catch(SQLException se)
        {
            se.printStackTrace();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
  

    }  
    int a=0;
   final  public void Fill2()
    {
        
        try{
           
            
           Class.forName("org.apache.derby.jdbc.ClientDriver").newInstance();
       Connection=DriverManager.getConnection(connectionURl,"Akash","Akash");
      
      String sql="select QId from QueRec where CourseName=?";
       ps=Connection.prepareStatement(sql);
       ps.setString(1, courseName);
       
       
       rs=ps.executeQuery();
       
       while(rs.next())
       {
           
          a++;
          
           
       }
       
      System.out.println("Question is"+a);
    }
       catch(SQLException se)
        {
            se.printStackTrace();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
  

    }
  

   


     
  public List<User> getItemList()
  {
      try {
          int s=1;
         
          String CS=TN.getText();
             Class.forName("org.apache.derby.jdbc.ClientDriver").newInstance();
       Connection=DriverManager.getConnection(connectionURl,"Akash","Akash");
          String sql="select * from QueRec where CourseName=? and Qid=?";
       ps=Connection.prepareStatement(sql);
       ps.setString(1, CS);
       ps.setInt(2, s);
       
       rs=ps.executeQuery();    
       List<User> list =new ArrayList<User>();
      

         while(rs.next()) {
    User u=new User(rs.getString("CorrectAnswer"));
            list.add(u);
           
         }
         s++;
      }
           catch(SQLException se)
        {
            se.printStackTrace();
             
        }
        catch(Exception e)
        {
            e.printStackTrace();
             
        }
      return null;
      
  }

  
  
  int p=0;
  int l=0;
   private void nextQuestions() {
       
     
      
       
        try {
            p++;
            l++;
             Class.forName("org.apache.derby.jdbc.ClientDriver").newInstance();
       Connection=DriverManager.getConnection(connectionURl,"Akash","Akash");
          String sql="select QId,Question,Option1,Option2,Option3,Option4 from QueRec where CourseName=? and QId=?";
       ps=Connection.prepareStatement(sql);
       ps.setString(1, courseName);
       ps.setInt(2, p);
       
      
       rs=ps.executeQuery();   
        if(p==a) {
                  nextBtn.setEnabled(false);
                  prevBtn.setEnabled(true);
              }
              else{
              nextBtn.setEnabled(true);
                  prevBtn.setEnabled(true);
              }

         while(rs.next()) {
      
             QI.setText(rs.getString("QId"));
           Q.setText(rs.getString("Question"));
           O1.setText(rs.getString("Option1"));
           O2.setText(rs.getString("Option2"));
           O3.setText(rs.getString("Option3"));
           O4.setText(rs.getString("Option4"));
         
         }
         
         
          
    
                 
                 
              
         } 
         catch(SQLException se)
        {
            se.printStackTrace();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

      
     
    }
   
   public void prevQuestions() {     
         String CS=TN.getText();
          int k=p;
        p--;
        try {
             Class.forName("org.apache.derby.jdbc.ClientDriver").newInstance();
       Connection=DriverManager.getConnection(connectionURl,"Akash","Akash");
          String sql="select QId,Question,Option1,Option2,Option3,Option4 from QueRec where CourseName=? and QId=?";
       ps=Connection.prepareStatement(sql);
       ps.setString(1, courseName);
        ps.setInt(2, p);
       
       
       rs=ps.executeQuery(); 
      // System.out.println(""+p);
          //System.out.println(""+l);
         // System.out.println(""+k);
         if(p==1) {
                  nextBtn.setEnabled(true);
                  prevBtn.setEnabled(false);
              }
              else
              {
                  nextBtn.setEnabled(true);
                  prevBtn.setEnabled(true);
              }

         while(rs.next()) {
    
             QI.setText(rs.getString("QId"));
           Q.setText(rs.getString("Question"));
           O1.setText(rs.getString("Option1"));
           O2.setText(rs.getString("Option2"));
           O3.setText(rs.getString("Option3"));
           O4.setText(rs.getString("Option4"));
        
         
         }
        
         
      
         } 
         catch(SQLException se)
        {
            se.printStackTrace();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
         
     
   }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jSeparator1 = new javax.swing.JSeparator();
        TN = new javax.swing.JLabel();
        Su = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        QI = new javax.swing.JLabel();
        QNUM = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        Q = new javax.swing.JTextArea();
        O2 = new javax.swing.JRadioButton();
        O4 = new javax.swing.JRadioButton();
        O1 = new javax.swing.JRadioButton();
        O3 = new javax.swing.JRadioButton();
        prevBtn = new javax.swing.JButton();
        nextBtn = new javax.swing.JButton();
        M = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        S = new javax.swing.JLabel();
        H = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        O5 = new javax.swing.JLabel();
        U = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 153));

        jSeparator1.setBackground(new java.awt.Color(255, 204, 51));

        TN.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        Su.setBackground(new java.awt.Color(255, 0, 0));
        Su.setForeground(new java.awt.Color(255, 255, 255));
        Su.setText("Submit");
        Su.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SuActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel3.setText("Question ID:");

        QNUM.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        QNUM.setText("1");

        Q.setEditable(false);
        Q.setColumns(100);
        Q.setRows(20);
        jScrollPane1.setViewportView(Q);

        buttonGroup1.add(O2);

        buttonGroup1.add(O4);
        O4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                O4ActionPerformed(evt);
            }
        });

        buttonGroup1.add(O1);

        buttonGroup1.add(O3);

        prevBtn.setBackground(new java.awt.Color(255, 0, 0));
        prevBtn.setForeground(new java.awt.Color(255, 255, 255));
        prevBtn.setText("Priv");
        prevBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                prevBtnActionPerformed(evt);
            }
        });

        nextBtn.setBackground(new java.awt.Color(255, 0, 0));
        nextBtn.setForeground(new java.awt.Color(255, 255, 255));
        nextBtn.setText("Next");
        nextBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nextBtnActionPerformed(evt);
            }
        });

        M.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel7.setText(":");

        S.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        H.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel8.setText(":");

        O5.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N

        jButton1.setBackground(new java.awt.Color(255, 0, 0));
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Pass");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator1)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(TN, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(125, 125, 125)
                        .addComponent(H, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 7, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(5, 5, 5)
                        .addComponent(M, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 7, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(S, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(Su, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(22, 22, 22))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(QNUM, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(O1, javax.swing.GroupLayout.PREFERRED_SIZE, 492, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(O5, javax.swing.GroupLayout.PREFERRED_SIZE, 465, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(O2, javax.swing.GroupLayout.PREFERRED_SIZE, 492, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(O3, javax.swing.GroupLayout.PREFERRED_SIZE, 492, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(O4, javax.swing.GroupLayout.PREFERRED_SIZE, 492, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 752, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 183, Short.MAX_VALUE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(42, 42, 42)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(QI, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(U, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(77, 77, 77))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(112, 112, 112)
                        .addComponent(prevBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(82, 82, 82)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(117, 117, 117)
                        .addComponent(nextBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(TN, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(M, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(S, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addComponent(Su, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(H, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(QI, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(U, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addComponent(QNUM, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(41, 41, 41)
                .addComponent(O1)
                .addGap(14, 14, 14)
                .addComponent(O5, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(O2, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35)
                .addComponent(O3)
                .addGap(45, 45, 45)
                .addComponent(O4)
                .addGap(39, 39, 39)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(prevBtn)
                    .addComponent(nextBtn)
                    .addComponent(jButton1)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void O4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_O4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_O4ActionPerformed

    private void SuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SuActionPerformed
        // TODO add your handling code here:
       // timer();
        //Fill();
         Su.setEnabled(false);
         try {
            boolean q=O1.isSelected();
            boolean w=O2.isSelected();
            boolean e=O3.isSelected();
            boolean r=O4.isSelected();
            
            int t=p;
             
       
       
          String sql2="insert into ch values(?,?,?,?,?)";
       ps=Connection.prepareStatement(sql2);
       ps.setInt(1, t);
       ps.setBoolean(2, q);
        ps.setBoolean(3, w);
         ps.setBoolean(4, e);
          ps.setBoolean(5, r);
        ps.execute();
       
        }  catch(SQLException se)
        {
            se.printStackTrace();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        
       
         
         
         
        try{
    
int a=1;
Score=0;
Unattempt=0;
System.out.println(""+p);
while(a<=p){
      Class.forName("org.apache.derby.jdbc.ClientDriver").newInstance();
       Connection=DriverManager.getConnection(connectionURl,"Akash","Akash");
          String sql="select CorrectAnswer from QueRec where CourseName=? and QId=?";
       ps=Connection.prepareStatement(sql);
       
      
       ps.setString(1, courseName);
        ps.setInt(2, a);
       
       
       rs1=ps.executeQuery();  
       while(rs1.next()){
      
       Class.forName("org.apache.derby.jdbc.ClientDriver").newInstance();
       Connection=DriverManager.getConnection(connectionURl,"Akash","Akash");
       
       String sql7="select * from ch where QNo=?";
       ps=Connection.prepareStatement(sql7);
       ps.setInt(1, a);
       rs=ps.executeQuery();
    while(rs.next()){
        boolean P=rs.getBoolean("opt1");
         boolean q=rs.getBoolean("opt2");
          boolean r=rs.getBoolean("opt3");
           boolean s=rs.getBoolean("opt4");
         
    
       a++;
      System.out.println(""+a);
       if(P == true)
       {
            if(rs1.getString("CorrectAnswer").equals("Answer 1"))
       {
          Score++;
       }
       }
       else if(q == true)
       {
            if(rs1.getString("CorrectAnswer").equals("Answer 2"))
       {
          Score++;
       }
       }
               
       else if(r == true)
       {
            if(rs1.getString("CorrectAnswer").equals("Answer 3"))
       {
          Score++;
       }
       }
        else if(s == true)
       {
            if(rs1.getString("CorrectAnswer").equals("Answer 4"))
       {
          Score++;
       }
       }
       else
        {
            Unattempt++;
                
        }
    }
       }
      
       
         
          
       
     
}
 
 
        }
       
          

  catch(SQLException se)
        {
            se.printStackTrace();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }  
         
         
        
        
        
        
        
          try{
          Class.forName("org.apache.derby.jdbc.ClientDriver").newInstance();
       Connection=DriverManager.getConnection(connectionURl,"Akash","Akash");
          
       st=Connection.createStatement();
       String sql="delete from ch";
       
       st.executeUpdate(sql);
       
         
        }
         catch(SQLException se)
        {
            se.printStackTrace();
        }
          catch(Exception e)
        {
            e.printStackTrace();
        }
          
          
        System.out.println(""+Score);
 System.out.println(""+ Unattempt);
          Test t=new Test();
          t.setVisible(false);
         
          String UserId=U.getText();
          result r=new result(courseName,Score,Unattempt,UserId);
          System.out.println(""+ Unattempt);
          r.setVisible(true);
         
    }//GEN-LAST:event_SuActionPerformed

    private void nextBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nextBtnActionPerformed
        // TODO add your handling code here:
        try {
            boolean q=O1.isSelected();
            boolean w=O2.isSelected();
            boolean e=O3.isSelected();
            boolean r=O4.isSelected();
            
            int t=p;
              Class.forName("org.apache.derby.jdbc.ClientDriver").newInstance();
       Connection=DriverManager.getConnection(connectionURl,"Akash","Akash");
       
       String sql8="select * from ch where QNo=?";
       ps=Connection.prepareStatement(sql8);
       ps.setInt(1, t);
       rs=ps.executeQuery();
       if(!rs.next())
       {
          String sql2="insert into ch values(?,?,?,?,?)";
       ps=Connection.prepareStatement(sql2);
       ps.setInt(1, t);
       ps.setBoolean(2, q);
        ps.setBoolean(3, w);
         ps.setBoolean(4, e);
          ps.setBoolean(5, r);
        ps.execute();
       }
       else
       {
           String sql3="update ch set opt1=?,opt2=?,opt3=?,opt4=? where Qno=?";
       ps=Connection.prepareStatement(sql3);
       ps.setInt(5, t);
       ps.setBoolean(1, q);
        ps.setBoolean(2, w);
         ps.setBoolean(3, e);
          ps.setBoolean(4, r);
        ps.execute();
       }
           
         }
          catch(SQLException se)
        {
            se.printStackTrace();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
         System.out.println(""+p);
          System.out.println(""+l);
          
          
        if(l>p){
            p++;
             try{
          Class.forName("org.apache.derby.jdbc.ClientDriver").newInstance();
       Connection=DriverManager.getConnection(connectionURl,"Akash","Akash");
          String sql="select opt1,opt2,opt3,opt4 from ch where Qno=?";
       ps=Connection.prepareStatement(sql);
       ps.setInt(1, p);
       rs=ps.executeQuery();
      // if(!rs.getBoolean("opt1") && !rs.getBoolean("opt2") && !rs.getBoolean("opt3") && !rs.getBoolean("opt4"))
       {//
          //System.out.println("yyyyy");
           //p--;
             // nextQuestions();
             
         //buttonGroup1.clearSelection();
       //}
       //else
       //{
       while(rs.next()){
       O1.setSelected(rs.getBoolean("opt1"));
       O2.setSelected(rs.getBoolean("opt2"));
       O3.setSelected(rs.getBoolean("opt3"));
       O4.setSelected(rs.getBoolean("opt4"));
       }
       
       
       
            try{
             Class.forName("org.apache.derby.jdbc.ClientDriver").newInstance();
       Connection=DriverManager.getConnection(connectionURl,"Akash","Akash");
          String sql9="select QId,Question,Option1,Option2,Option3,Option4 from QueRec where CourseName=? and QId=?";
       ps=Connection.prepareStatement(sql9);
       ps.setString(1, courseName);
       ps.setInt(2, p);
       
      
       rs=ps.executeQuery();   
      

         while(rs.next()) {
      
             QI.setText(rs.getString("QId"));
           Q.setText(rs.getString("Question"));
           O1.setText(rs.getString("Option1"));
           O2.setText(rs.getString("Option2"));
           O3.setText(rs.getString("Option3"));
           O4.setText(rs.getString("Option4"));
        
         }
         if(p==a) {
                  nextBtn.setEnabled(false);
                  prevBtn.setEnabled(true);
              }
              else{
              nextBtn.setEnabled(true);
                  prevBtn.setEnabled(true);
              }
            
       System.out.println("hello");
         }  catch(SQLException se)
        {
            se.printStackTrace();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
             }
        
             }
           
        catch(SQLException se)
        {
            se.printStackTrace();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        }
        else{
             nextQuestions();
             
         buttonGroup1.clearSelection();
        }
         
         
          
      
         
         
         
           //nextQuestions();
           
        // buttonGroup1.clearSelection();
          
        
        /* pos++;
         if(pos < getItemList().size()){
             showItem(pos);
         }
         else
         {
             pos= getItemList().size()-1;
             showItem(pos);
             JOptionPane.showMessageDialog(null, "LAST");

             
         }*/
    }//GEN-LAST:event_nextBtnActionPerformed

    private void prevBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_prevBtnActionPerformed
        // TODO add your handling code here:
        
        prevQuestions();
        try{
          Class.forName("org.apache.derby.jdbc.ClientDriver").newInstance();
       Connection=DriverManager.getConnection(connectionURl,"Akash","Akash");
          String sql="select opt1,opt2,opt3,opt4 from ch where Qno=?";
       ps=Connection.prepareStatement(sql);
       ps.setInt(1, p);
       rs=ps.executeQuery();
       while(rs.next()){
       O1.setSelected(rs.getBoolean("opt1"));
       O2.setSelected(rs.getBoolean("opt2"));
       O3.setSelected(rs.getBoolean("opt3"));
       O4.setSelected(rs.getBoolean("opt4"));
       }
       
       
       
         System.out.println("Prev");
        }
         catch(SQLException se)
        {
            se.printStackTrace();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
      /*  pos--;
        if(pos>0)
            showItem(pos);
        else
        {
            pos=0;
            showItem(pos);
            JOptionPane.showMessageDialog(null, "FIRST");

        }*/
    }//GEN-LAST:event_prevBtnActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
      nextQuestions();
        
    }//GEN-LAST:event_jButton1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Test.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Test.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Test.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Test.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Test().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel H;
    private javax.swing.JLabel M;
    private javax.swing.JRadioButton O1;
    private javax.swing.JRadioButton O2;
    private javax.swing.JRadioButton O3;
    private javax.swing.JRadioButton O4;
    private javax.swing.JLabel O5;
    private javax.swing.JTextArea Q;
    private javax.swing.JLabel QI;
    private javax.swing.JLabel QNUM;
    private javax.swing.JLabel S;
    private javax.swing.JButton Su;
    public static javax.swing.JLabel TN;
    public static javax.swing.JLabel U;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JButton nextBtn;
    public javax.swing.JButton prevBtn;
    // End of variables declaration//GEN-END:variables
}
