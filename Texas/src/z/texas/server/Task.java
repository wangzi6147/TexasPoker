package z.texas.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import z.texas.commons.TexasBean;

import com.google.gson.Gson;


public class Task implements Runnable {

	private Socket socket;
	private Dealer dealer;
	private boolean isStop;
	private TaskManager taskManager;
	private DataInputStream dis;
	private DataOutputStream dos;
	//测试时用
	private String name;

	public Task(Socket socket, Dealer dealer, TaskManager taskManager) {
		this.socket = socket;
		this.dealer = dealer;
		this.taskManager = taskManager;
	}

	@Override
	public void run() {
		try {
			isStop = false;
			dis = new DataInputStream(
					socket.getInputStream());
			dos = new DataOutputStream(
					socket.getOutputStream());
			while (!isStop) {
				dealer.parse(dis.readUTF(), socket.getInetAddress().getHostAddress(), this);
			}
			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	public void setStop(boolean isStop) {
		this.isStop = isStop;
	}

	public void send(String str) {
		try {
			dos.writeUTF(str);
			dos.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
