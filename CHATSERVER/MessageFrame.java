import java.awt.*;
import java.awt.event.*;

class MessageFrame extends Frame implements ActionListener
{
TextArea allmsg;
TextArea currentmsg;
Button a,b;
String localuser,remoteuser;
ClientThread th;
public MessageFrame(String local,String remote,ClientThread t)
{
super(remote);
localuser=local;
remoteuser=remote;
th=t;
allmsg=new TextArea("",20,TextArea.SCROLLBARS_BOTH);
allmsg.setEditable(false);
allmsg.setForeground(Color.blue);
currentmsg=new TextArea("",8,TextArea.SCROLLBARS_BOTH);
currentmsg.setForeground(Color.red);
a=new Button ("Send");
b=new Button("Clear");
add(allmsg);
add(currentmsg);
add(a);
add(b);
a.addActionListener(this);
b.addActionListener(this);
Label one=new Label("Sent and Received Messages");
Label two=new Label("Type your Message here");
add(one);
add(two);
setLayout(null);
one.setBounds(20,20,180,30);
allmsg.setBounds(20,60,290,150);
two.setBounds(20,220,150,30);
currentmsg.setBounds(20,255,290,70);
a.setBounds(70,340,60,30);
b.setBounds(140,340,60,30);
setSize(330,400);
setVisible(true);
}
public void display(String s)
{
String text=allmsg.getText();
text=text+"\n"+remoteuser+">"+s;
allmsg.setText(text);
}
public void actionPerformed(ActionEvent e)
{
String s=e.getActionCommand();
if (s.equals("Send"))
{
String text=allmsg.getText();
text=text+"\n"+localuser+">"+currentmsg.getText();
allmsg.setText(text);
th.post(localuser,remoteuser,currentmsg.getText());
}
else
{
currentmsg.setText("");
}
}

}


