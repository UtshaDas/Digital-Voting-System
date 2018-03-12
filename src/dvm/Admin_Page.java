package dvm;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import static java.lang.System.exit;
import java.sql.*;//To set up a connection to a database
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Admin_Page {
    //adding Database
    Connection cn=null;
    Statement stmt=null;
    ResultSet res=null,res1=null;
    PreparedStatement ps=null;
    String driver="com.mysql.jdbc.Driver";
    String url="jdbc:mysql://localhost:3306/evm";
    public ImageIcon image1=new ImageIcon("D:\\3rd Semester\\Project\\DVM\\Election\\jasod.jpg");
     public ImageIcon image2=new ImageIcon("D:\\3rd Semester\\Project\\DVM\\Election\\aw1.png");
     public ImageIcon image3=new ImageIcon("D:\\3rd Semester\\Project\\DVM\\Election\\jatioparti.png");
     public ImageIcon image4=new ImageIcon("D:\\3rd Semester\\Project\\DVM\\Election\\BNP.jpg");
    
   public Admin_Page(){
    JButton button_res=new JButton("Election Result");
    JButton button_stop=new JButton("Stop the Process");
     JFrame frame=new JFrame();
    frame.setTitle("Admin Activities");
    frame.setSize(600,500);
    frame.setLocation(0,0);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setVisible(true);
    frame.setBackground(Color.black);
   
    
    JPanel panel1=new JPanel();
    frame.add(panel1);
    panel1.add(button_res);
    panel1.add(button_stop);
    
      try{
             Class.forName(driver);
              //JOptionPane.showMessageDialog(null, "succed");
         }
         catch(Exception e){
          e.printStackTrace();
         }
      //Action listener
    ActionListener listen1=(ActionEvent e) -> {
                            try{
                cn=DriverManager.getConnection(url,"root","");         
               // String VOTE[]=new String[10];
                int VOTE[]=new int[100];
         //create statement
               stmt=cn.createStatement();
                String query="select * from voting";
                res=stmt.executeQuery(query);
                int count=0;
                while(res.next()){
                    VOTE[1]=Integer.parseInt(res.getString(1));
                    VOTE[2]=Integer.parseInt(res.getString(2));
                    VOTE[3]=Integer.parseInt(res.getString(3));
                    VOTE[4]=Integer.parseInt(res.getString(4));
                    
                }
                //to show result
                JFrame frame1=new JFrame();
                 frame1.setTitle("Voting Result");
                frame1.setSize(600,500);
                frame1.setLocation(0,0);
                frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
               frame1.setVisible(true);
               frame1.setBackground(Color.black);
               
               long countedvote=VOTE[1]+VOTE[2]+VOTE[3]+VOTE[4];
               JLabel l0=new JLabel("Total Voter : 59"+ "       Total Vote Counted:     "+countedvote+"                                                                                          ");
               
               JLabel l1=new JLabel(" Abdul Kasem  ");
               JLabel l11=new JLabel(":    "+VOTE[1]+"                                                                                              ");
               
               JLabel l2=new JLabel(" M.A. Aziz                 ");
               JLabel l22=new JLabel(":       "+VOTE[2]+"                                                                                             ");
               JLabel l3=new JLabel(" Abdul Bari              ");
               JLabel l33=new JLabel(":   "+VOTE[3]+"                                                                                  ");
               JLabel l4=new JLabel("Md. Azad                       ");
               JLabel l44=new JLabel(":      "+VOTE[4]+"                                                                               ");
               
               JPanel panel2=new JPanel();
               frame1.add(panel2);
               panel2.add(l0);
               panel2.add(l1);
               panel2.add(new JLabel(image1));
               panel2.add(l11);
               panel2.add(l2);
               panel2.add(new JLabel(image2));
               panel2.add(l22);
               panel2.add(l3);
                panel2.add(new JLabel(image3));
                panel2.add(l33);
               panel2.add(l4);
                panel2.add(new JLabel(image4));
                panel2.add(l44);
                //for(int i=1;i<=4;i++)
                // System.out.println(VOTE[i]);
                int max=VOTE[1],position=1;
                for(int i=1;i<=4;i++)
                {
                    if(max<VOTE[i])
                    {
                        max=VOTE[i];
                        position=i;
                    }
                }
                String MAX=max+"";
                int position1=0,j,k;
                for(j=position+1;j<=4;j++)
                {
                   if(VOTE[j]==VOTE[position])
                       position1=1;
                }
                if(position1==1)
                {
                    JOptionPane.showMessageDialog(panel1, "Result is tied");
                }
                else{    
              if(position==1)
              {
                  JOptionPane.showMessageDialog(panel1, "Abdul Kasem is Winner and Total Vote "+MAX);
              }
              else if(position==2)
              {
                  JOptionPane.showMessageDialog(panel1, "M.A. Aziz is Winner and Total Vote "+MAX);
              }
              else if(position==3)
              {
                  JOptionPane.showMessageDialog(panel1, "Abdul Bari is Winner and Total Vote "+MAX);
              }
              else if(position==4)
              {
                  JOptionPane.showMessageDialog(panel1, "Md. Azad is Winner and Total Vote "+MAX);
              }
                }
        }
        catch(Exception ex){
            System.out.println("Failed");
        }
         };
      
     ActionListener listen2=(ActionEvent e) -> {
         //JOptionPane.showMessageDialog(null,"Button 2 is pressed", "Message2",JOptionPane.INFORMATION_MESSAGE);
         exit(1);
         };
      
     button_res.addActionListener(listen1);
     button_stop.addActionListener(listen2);
        }
   /*public static void main(String[] args) {
        new Admin_Page();
    }*/
}
