package z.texas.client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client{
	private Socket socket;
	private DataOutputStream dos;
	private DataInputStream dis;

	public Boolean connect(String ip, int port){
		try {
			socket = new Socket(ip, port);
			if(socket.isConnected())
				return true;
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	public String send(String string) {
		try {
			dos = new DataOutputStream(socket.getOutputStream());
			dos.writeUTF(string);
			dos.flush();
			dis = new DataInputStream(socket.getInputStream());
			return dis.readUTF();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
