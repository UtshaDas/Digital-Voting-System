package dvm;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;
public class Login {
    Connection cn=null;
    Statement stmt=null;
    ResultSet res=null,res1=null;
    PreparedStatement ps=null;
    String driver="com.mysql.jdbc.Driver";
    String url="jdbc:mysql://localhost:3306/evm";
    JFrame f=new JFrame("User login");
    JLabel l=new JLabel("NID");
    JLabel l1=new JLabel("Password");
    JTextField t=new JTextField(20);
    //JTextField t1=new JTextField(12);
    JPasswordField t1=new JPasswordField(15);
    JButton b=new JButton("Login");
    public Login()
    {
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
    public void frame()
    {
        f.setSize(600, 500);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);
        
        JPanel p=new JPanel();
        p.add(l);
        p.add(t);
        p.add(l1);
        p.add(t1);
        p.add(b);
        f.add(p);
        ActionListener listen;
       listen = (ActionEvent e) -> {
            try{
                String NID_DATABASE[]=new String[1000];
                String PASSWORD_DATABASE[]=new String[1000];
                     int VOTE[]=new int[100];
                cn=DriverManager.getConnection(url,"root","");         
             
         //create statement
               stmt=cn.createStatement();
               
                String user=t.getText().toString();
                String pass=t1.getText().toString();
               // String  sql = "select * from info where NID="+user+" AND PASSWORD="+pass;
                String query="select * from info";
                res=stmt.executeQuery(query);
                int count=0,count1=0;
                while(res.next())
                {
                    ++count;
                 NID_DATABASE[count]=res.getString(1);
                 PASSWORD_DATABASE[count]=res.getString(2);
                 VOTE[count]=res.getInt(3);
                }
               int i;
                 /*for( i=0;i<=count;i++){
                    
                    System.out.println(""+NID_DATABASE[i] +" "+PASSWORD_DATABASE[i]);
                }*/
                for( i=0;i<=count;i++){
                    
                    if(user.equals(NID_DATABASE[i]) && pass.equals(PASSWORD_DATABASE[i]) && VOTE[i]==0)
                    {
                        
                        JOptionPane.showMessageDialog(null, "User Valid");
                        // create the java mysql update preparedstatement
                      String query1 = "update info set Vote= ? where NID="+user;
                     PreparedStatement preparedStmt = cn.prepareStatement(query1);
                     preparedStmt.setInt (1, 1);
 
                 // execute the java preparedstatement
                  preparedStmt.executeUpdate();
       
                     cn.close();
                        //Ballot obj=new Ballot();
                     Password_Change ob=new Password_Change();
                     ob.connect();
                     ob.frame();
                        break;
                    }
                }
                if(i==count+1)
                {
                    JOptionPane.showMessageDialog(null, "User is not Valid,Please Check your NID and Password");
                }
            }
            catch(Exception ex)
            {
                JOptionPane.showMessageDialog(null, "Failed");
              t.setText(null);
              t1.setText(null);
            }
        };
          
           b.addActionListener(listen);
         
    }
   /*public static void main(String[] args) {
       Login ob= new Login();
    }*/
   
}
