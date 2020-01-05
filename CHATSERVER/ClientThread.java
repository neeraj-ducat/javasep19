import java.net.*;

import java.io.*;
import java.util.*;

class ClientThread extends Thread
{
Socket s;
Hashtable tab;
String name;
String messagein,messageout;
InputStream in;
OutputStream out;
boolean flag;
ChatRoom room;
public ClientThread(String uname)
{
super();
name=uname;
tab=new Hashtable();
start();
}



public void run()
{
try
{
s=new Socket("localhost",2500);
flag=true;
in = s.getInputStream();
out=s.getOutputStream();
messageout="connect"+"$"+name+"#";
putString(messageout);
room = new ChatRoom(this);
}
catch(Exception e)
{
System.out.println(e);
}
while (flag)
{
messagein=parseString(in);
StringTokenizer st= new StringTokenizer(messagein,"$");
String command=st.nextToken();
String u;
if (command.equals("adduser"))
{
 u= st.nextToken();
room.addUser(u);
}
else if (command.equals("removeuser"))
{
 u= st.nextToken();
room.removeUser(u);
}
else
{
boolean findflag=false;
Set s = tab.keySet();
Iterator itr = s.iterator();
while(itr.hasNext())
{
String key=(String)itr.next();
if (key.equals(command))
{
findflag=true;
break;
}
}
if (!findflag)
{
displayChatWindow(command);
}
String msg=st.nextToken();
MessageFrame mf=(MessageFrame) tab.get(command);
mf.display(msg);

}


}
}

public void displayChatWindow(String remote)
{
MessageFrame w = new MessageFrame(name,remote,this);
tab.put(remote,w);
}

public static String parseString(InputStream in)
{
int ch;
String rep="";
try
{
while( (ch=in.read()) !=-1)
{
if (ch=='#')
break;
rep =rep +(char)ch;
}
}
catch(Exception e)
{
System.out.println(e);
}

return rep;
}

public void post(String from,String to,String msg)
{
String rep=to+"$"+msg+"#";
putString(rep);
}
public  void putString(String str)
{
byte a[]=str.getBytes();
try
{
out.write(a);
}
catch(Exception e)
{
System.out.println(e);
}
}
public void disconnect()
{

flag=false;
}
}
