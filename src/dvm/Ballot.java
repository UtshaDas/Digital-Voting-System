
package dvm;
import java.awt.GridLayout;//The class GridLayout arranges components in a rectangular grid.
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import static java.lang.System.exit;// to exit(1)
import java.sql.Connection;
import java.sql.DriverManager;//to get connection of JDBC driver
import java.sql.PreparedStatement;//to updatea database
import java.sql.ResultSet;//to get result from database
import java.sql.Statement;//to run query on database
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class Ballot extends JFrame {
    //adding Database
    Connection cn=null;
    Statement stmt=null;
    ResultSet res=null,res1=null;
    PreparedStatement ps=null;
    String driver="com.mysql.jdbc.Driver";
    String url="jdbc:mysql://localhost:3306/evm";
    
     JButton b1,b2,b3,b4;
     JLabel l1,l2,l3,l4;
     public ImageIcon image1=new ImageIcon("D:\\3rd Semester\\Project\\DVM\\Election\\jasod.jpg");
     public ImageIcon image2=new ImageIcon("D:\\3rd Semester\\Project\\DVM\\Election\\aw1.png");
     public ImageIcon image3=new ImageIcon("D:\\3rd Semester\\Project\\DVM\\Election\\jatioparti.png");
     public ImageIcon image4=new ImageIcon("D:\\3rd Semester\\Project\\DVM\\Election\\BNP.jpg");
     
     long VOTE_COUNT[]=new long[100];
     public Ballot()
    {
        setTitle("Ballot Papper");
        setSize(1000,300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        setLayout(new GridLayout(1,4,5,5));//Creates a grid layout with the specified number of rows and columns.
        
        //create label
       l1=new JLabel("Abdul Kasem"); 
       l2=new JLabel("M.A. Aziz");
       l3=new JLabel("Abdul Bari");
       l4=new JLabel("Md. Azad");
       
       //create button
       b1=new JButton("Vote");
       b2=new JButton("Vote");
       b3=new JButton("Vote");
       b4=new JButton("Vote");
       
       //adding label ,images,button
       add(l1);
       add(new JLabel(image1));
       add(b1);
       
       add(l2);
       add(new JLabel(image2));
       add(b2);
       
       add(l3);
       add(new JLabel(image3));
       add(b3);
       
       add(l4);
       add(new JLabel(image4));
       add(b4);
       
       //database adding
       try{
             Class.forName(driver);
              //JOptionPane.showMessageDialog(null, "succed");
         }
         catch(Exception e){
          e.printStackTrace();
         }
      
       //Button
        ActionListener listen1;
        listen1 = (ActionEvent e) -> {
            int ck;
             ck=JOptionPane.showConfirmDialog(null,"Are you sure?");
             if(ck==0){
                 //VOTE_COUNT[1]=VOTE_COUNT[1]+1;
           try{
                cn=DriverManager.getConnection(url,"root","");         
                String VOTE[]=new String[10];
         //create statement
               stmt=cn.createStatement();
                String query="select * from voting";
                res=stmt.executeQuery(query);
                int count=0;
                while(res.next()){
                    VOTE[1]=res.getString(1);
                }
                //System.out.println(VOTE[1]);
                Integer N=Integer.parseInt(VOTE[1]);
                ++N;
                //System.out.println(" "+N);
                String C=N+"";
                //System.out.println(C);
                // create the java mysql update preparedstatement
               String query1 = "update voting set Can1= ?";
               PreparedStatement preparedStmt = cn.prepareStatement(query1);
              preparedStmt.setString(1,C);
            // execute the java preparedstatement
              preparedStmt.executeUpdate();
              
       
              cn.close();
              exit(1);
        }
        catch(Exception ex){
            System.out.println("Failed");
        }
             }
             else if(ck==1){
                 JOptionPane.showMessageDialog(null,"Try again");
             }
             else
             {
                  JOptionPane.showMessageDialog(null,"Try again");
             }
        };
        ActionListener listen2;
        listen2 = (ActionEvent e) -> {
            int ck;
             ck=JOptionPane.showConfirmDialog(null,"Are you sure?");
             if(ck==0){
                 //VOTE_COUNT[2]=VOTE_COUNT[2]+1;
                 try{
                cn=DriverManager.getConnection(url,"root","");         
                String VOTE[]=new String[10];
         //create statement
               stmt=cn.createStatement();
                String query="select * from voting";
                res=stmt.executeQuery(query);
                int count=0;
                while(res.next()){
                    VOTE[2]=res.getString(2);
                }
                //System.out.println(VOTE[2]);
                Integer N=Integer.parseInt(VOTE[2]);
                ++N;
                //System.out.println(" "+N);
                String C=N+"";
                //System.out.println(C);
                // create the java mysql update preparedstatement
               String query1 = "update voting set Can2= ?";
               PreparedStatement preparedStmt = cn.prepareStatement(query1);
              preparedStmt.setString(1,C);
            // execute the java preparedstatement
              preparedStmt.executeUpdate();
              
       
              cn.close();
              exit(1);
        }
        catch(Exception ex){
            System.out.println("Failed");
        }
                 // Front ob=new Front();
             }
             else if(ck==1){
                 JOptionPane.showMessageDialog(null,"Try again");
             }
             else
             {
                  JOptionPane.showMessageDialog(null,"Try again");
             }
        };
        ActionListener listen3;
        listen3 = (ActionEvent e) -> {
            int ck;
             ck=JOptionPane.showConfirmDialog(null,"Are you sure?");
             if(ck==0){
                 //VOTE_COUNT[3]=VOTE_COUNT[3]+1;
                 try{
                cn=DriverManager.getConnection(url,"root","");         
                String VOTE[]=new String[10];
         //create statement
               stmt=cn.createStatement();
                String query="select * from voting";
                res=stmt.executeQuery(query);
                int count=0;
                while(res.next()){
                    VOTE[3]=res.getString(3);
                }
                //System.out.println(VOTE[1]);
                Integer N=Integer.parseInt(VOTE[3]);
                ++N;
                //System.out.println(" "+N);
                String C=N+"";
                //System.out.println(C);
                // create the java mysql update preparedstatement
               String query1 = "update voting set Can3= ?";
               PreparedStatement preparedStmt = cn.prepareStatement(query1);
              preparedStmt.setString(1,C);
            // execute the java preparedstatement
              preparedStmt.executeUpdate();
              
       
              cn.close();
              exit(1);
        }
        catch(Exception ex){
            System.out.println("Failed");
        }
                  //Front ob=new Front();
             }
             else if(ck==1){
                 JOptionPane.showMessageDialog(null,"Try again");
             }
             else
             {
                  JOptionPane.showMessageDialog(null,"Try again");
             }
        };
        ActionListener listen4;
        listen4 = (ActionEvent e) -> {
            int ck;
             ck=JOptionPane.showConfirmDialog(null,"Are you sure?");
             if(ck==0){
                 //VOTE_COUNT[4]=VOTE_COUNT[4]+1;
                 try{
                cn=DriverManager.getConnection(url,"root","");         
                String VOTE[]=new String[10];
         //create statement
               stmt=cn.createStatement();
                String query="select * from voting";
                res=stmt.executeQuery(query);
                int count=0;
                while(res.next()){
                    VOTE[4]=res.getString(4);
                }
                //System.out.println(VOTE[1]);
                Integer N=Integer.parseInt(VOTE[4]);
                ++N;
                //System.out.println(" "+N);
                String C=N+"";
                //System.out.println(C);
                // create the java mysql update preparedstatement
               String query1 = "update voting set Can4= ?";
               PreparedStatement preparedStmt = cn.prepareStatement(query1);
              preparedStmt.setString(1,C);
            // execute the java preparedstatement
              preparedStmt.executeUpdate();
              
       
              cn.close();
              exit(1);
        }
        catch(Exception ex){
            System.out.println("Failed");
        }
                  //Front ob=new Front();
             }
             else if(ck==1){
                 JOptionPane.showMessageDialog(null,"Try again");
             }
             else
             {
                  JOptionPane.showMessageDialog(null,"Try again");
             }
        };
        //adding Actionlistener to button
        b1.addActionListener(listen1);
        b2.addActionListener(listen2);
        b3.addActionListener(listen3);
        b4.addActionListener(listen4);
    }
    /*/public static void main(String[] args) {
       Ballot obj= new Ballot();
    }*/
}
