
package dvm;
import java.awt.FlowLayout; //The class FlowLayout components in a left-to-right flow.
import java.awt.event.ActionEvent; // add ActionListener to a JButton. 
import java.awt.event.ActionListener;//to perform the desired action is performed
import javax.swing.ImageIcon;//To insert images
import javax.swing.JButton;//to create button
import javax.swing.JFrame;//To create Frame as user desires
import javax.swing.JLabel;// To create JLabel
public class Front extends JFrame {
    JButton b_voter,b_admin;
     JLabel l1;
    public Front()
    {
        setTitle("");
	setSize(720,720);
	
	setDefaultCloseOperation(EXIT_ON_CLOSE);
	setVisible(true);
	setContentPane(new JLabel(new ImageIcon("D:\\3rd Semester\\Project\\DVM\\Election\\EClogoEngDS.jpg")));
	setLayout(new FlowLayout());//Constructs a new FlowLayout with a centered alignment and a default 5-unit horizontal and vertical gap.
	l1=new JLabel("                       Welcome To  Election Commission , Bangladesh                        ");
        b_voter=new JButton("Voter");
        b_admin=new JButton("Admin");
	add(l1);
	add(b_voter);
        add(b_admin);
         ActionListener listen1;
        listen1 = (ActionEvent e) -> {
            Login ob_login=new Login();
        };
         ActionListener listen2=(ActionEvent e) -> {
             //JOptionPane.showMessageDialog(null, "Sorry Admin is not ready now");
             Admin_Login obj=new Admin_Login();
         };
         b_voter.addActionListener(listen1);
         b_admin.addActionListener(listen2);
	setSize(600,500);
    }
}
