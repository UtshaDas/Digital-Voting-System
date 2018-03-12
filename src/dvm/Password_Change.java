
package dvm;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import static java.lang.System.exit;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class Password_Change {
    Connection cn=null;
    Statement stmt=null;
    ResultSet res=null,res1=null;
    PreparedStatement ps=null;
    String driver="com.mysql.jdbc.Driver";
    String url="jdbc:mysql://localhost:3306/evm";
    JButton b_vote=new JButton("Vote");
    JButton b_change=new JButton("Change Password");
    JFrame f=new JFrame("User Choice");
    public void Password_Change(){
        connect();
        frame();
    }
     public void connect()
    {
         try{
             Class.forName(driver);
             // JOptionPane.showMessageDialog(null, "succed");
         }
         catch(Exception e){
          e.printStackTrace();
         }
    }
     public void frame(){
        f.setSize(600, 500);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);
        
        JPanel p= new JPanel();
        p.add(b_vote);
        p.add(b_change);
        
        f.add(p);
        ActionListener listen1;
       listen1 = (ActionEvent e) ->{
          
           Ballot ob=new Ballot();
       };
       ActionListener listen2;
       listen2 = (ActionEvent e) ->{
          JFrame f1=new JFrame("Change Password");
          JLabel l=new JLabel("NID");
          JLabel l1=new JLabel("New Password");
          JButton b_ok =new JButton("Change");
           JTextField t=new JTextField(45);
            JPasswordField t1=new JPasswordField(40);
            
            f1.setSize(600, 500);
        f1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f1.setVisible(true);
        
        JPanel p1= new JPanel();
        p1.add(l);
        p1.add(t);
        p1.add(l1);
        p1.add(t1);
        p1.add(b_ok);
        f1.add(p1);
        ActionListener listen_ok;
        listen_ok=(ActionEvent ee)->{
            try{
                 cn=DriverManager.getConnection(url,"root","");         
             
         //create statement
               stmt=cn.createStatement();
               
                String user=t.getText().toString();
                String pass=t1.getText();
                
                String query1 = "update info set password= ? where NID="+user;
                     PreparedStatement preparedStmt = cn.prepareStatement(query1);
                     preparedStmt.setString(1, pass);
                     
                     String query2 = "update info set Vote= ? where NID="+user;
                      PreparedStatement preparedStmt1 = cn.prepareStatement(query2);
                      preparedStmt1.setInt(1, 0);
                      
                 // execute the java preparedstatement
                  preparedStmt.executeUpdate();
                  preparedStmt1.executeUpdate();
                     cn.close();
                     exit(1);
            }
            catch(Exception ex1){
                System.out.println("failed");
            }
        };
        b_ok.addActionListener(listen_ok);
       };
       b_vote.addActionListener(listen1);
       b_change.addActionListener(listen2);
        
     }
    /* public static void main(String[] args) {
        Password_Change ob=new Password_Change();
        ob.frame();
    }*/
}
