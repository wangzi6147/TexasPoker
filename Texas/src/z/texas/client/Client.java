package z.texas.client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client implements Runnable{
	private Socket socket;
	private DataOutputStream dos;
	private DataInputStream dis;
	private boolean isStop;
	private Player player;

	public Client(Player player) {
		this.player = player;
	}

	public Boolean connect(String ip, int port){
		try {
			socket = new Socket(ip, port);
			dos = new DataOutputStream(socket.getOutputStream());
			dis = new DataInputStream(socket.getInputStream());
			if(socket.isConnected()){
				setStop(false);
				new Thread(this).start();
				return true;
			}
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	public void send(String string) {
		try {
			dos.writeUTF(string);
			dos.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		while(!isStop){
			try {
				player.parse(dis.readUTF());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void setStop(boolean isStop) {
		this.isStop = isStop;
	}
}
