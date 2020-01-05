import java.net.*;
import java.util.*;
import java.io.*;

class ChatServer
{
Hashtable list;
public ChatServer()
{
list=new Hashtable();

}
public static void main(String arr[])
{

  try
  {
ChatServer c = new ChatServer();
ServerSocket ss = new ServerSocket(2500);
System.out.println("server is ready...............");
System.out.println("press ctrl+c to shut down");  
while(true)
   {
      
	Socket client= ss.accept();
	ServerThread st=new ServerThread(client,c);
  }
 }
 
catch(Exception e)
  {
           System.out.println(e);
   }
   
}
public void addClient(String name,ServerThread th)
{
Set s= list.entrySet();
Iterator itr = s.iterator();
String msg1;
while(itr.hasNext())
{
Map.Entry m = (Map.Entry)itr.next();
msg1="adduser"+"$"+name+"#";
ServerThread t =(ServerThread) m.getValue();
t.putString(msg1);
String msg2= "adduser"+"$"+m.getKey()+"#";
th.putString(msg2);
}
list.put(name,th);
}
public void removeClient (String name)
{

}
public void deliverMessage(String from,String to,String message)
{
ServerThread rec =(ServerThread)list.get(to);
rec.deliver(from,message);
}
 

}


class ServerThread extends Thread
{
 Socket client;
 ChatServer server;
 boolean flag ;
 String username;
 InputStream in;
 OutputStream out ;
public ServerThread(Socket s,ChatServer c)
{
 client =s;
 server =c; 
 flag=true; 
 start();
}

public void run()
{
try
{
 in = client.getInputStream();
 out = client.getOutputStream();
 String request,command,message,receiver;
    while(flag)
    {
       	request=parseString(in);
	StringTokenizer st=new StringTokenizer(request,"$");
	command=st.nextToken();
	if (command.equals("connect"))
	{
	username=st.nextToken();
	server.addClient(username,this);
	}
	else if(command.equals("disconnect"))
	{
	flag=false;
	server.removeClient(username);
	}
	else
	{
	message = st.nextToken();
	server.deliverMessage(username,command,message);

	}
    }
in.close();
out.close();
client.close();
}
catch(Exception e)
{
}
}
public void deliver(String sender,String msg)
{
msg=sender+"$"+msg+"#";
putString(msg);
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

}