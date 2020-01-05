
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class ClientLogin extends JFrame implements ActionListener
{
JButton a;
ClientThread th;
JTextField userId,pwd;
public ClientLogin(String t)
{
super(t);
a=new JButton("Sign in");
userId = new JTextField(15);
pwd = new JTextField(15);
setLayout(new FlowLayout());
add(new JLabel("User Id"));
add(userId);
add(new JLabel("Password"));
add(pwd);
add(a);
a.addActionListener(this);


addWindowListener(new WindowAdapter()
{
public void windowClosing(WindowEvent e)
{
//code to disconnect will be added.
System.exit(0);
}
});
}
public void actionPerformed(ActionEvent  e)
{
String s=e.getActionCommand();
if (s.equals("Sign in"))
{
String uname=userId.getText();
th=new ClientThread(uname);

}
else
{
th.disconnect();
}
}
public static void main(String arr[])
{
ClientLogin w = new ClientLogin("Open Chat");
w.setSize(255,250);
w.setVisible(true);

}

}


