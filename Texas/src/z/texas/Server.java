package z.texas;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server extends Thread{
	@Override
	public void run() {
		try {
			ServerSocket serverSocket = new ServerSocket(8080);
			while(true){
				Socket socket = serverSocket.accept();
				DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
				DataInputStream dis = new DataInputStream(socket.getInputStream());
				dos.writeUTF(dis.readUTF()+"接收成功");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
