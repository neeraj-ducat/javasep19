import java.awt.*;
import java.awt.event.*;

class ChatRoom extends Frame 
{
ClientThread th;
List users;
Button b;

public ChatRoom(ClientThread thread)
{
super("Open Chat Room");
th=thread;
users=new List();
Label msg=new Label("Currently Logged in Users.");
add(msg);
add(users);
b=new Button("Chat");
add(b);
setLayout(null);
msg.setBounds(10,20,150,30);
users.setBounds(30,50,150,200);
b.setBounds(75,260,80,30);
setSize(200,300);
setVisible(true);
b.addActionListener(new ActionListener()
{
public void actionPerformed(ActionEvent e)
{
th.displayChatWindow(users.getSelectedItem());
}
});
}
public void addUser(String uname)
{
users.add(uname);
}
public void removeUser(String uname)
{
users.remove(uname);
}

}