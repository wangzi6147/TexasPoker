package z.texas.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;

import z.texas.commons.Card;
import z.texas.commons.TexasBean;

import com.google.gson.Gson;


public class Task implements Runnable {

	private Socket socket;
	private Dealer dealer;
	private boolean isStop;
	private SocketManager socketManager;
	private TexasBean texasBean;
	private Gson gson;
	private DataInputStream dis;
	private DataOutputStream dos;

	public Task(Socket socket, Dealer dealer, SocketManager socketManager) {
		this.socket = socket;
		this.dealer = dealer;
		this.socketManager = socketManager;
	}

	@Override
	public void run() {
		try {
			gson = new Gson();
			isStop = false;
			dis = new DataInputStream(
					socket.getInputStream());
			dos = new DataOutputStream(
					socket.getOutputStream());
			while (!isStop) {
				setTexasBean(gson.fromJson(dis.readUTF(), TexasBean.class));
				switch (getTexasBean().getState()) {
				case "ready":
					if(socketManager.allReady()){
						socketManager.allStart();
					}
					break;
				default:
					break;
				}
			}
			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	public void setStop(boolean isStop) {
		this.isStop = isStop;
	}

	public TexasBean getTexasBean() {
		return texasBean;
	}

	public void setTexasBean(TexasBean texasBean) {
		this.texasBean = texasBean;
	}

	public void gameStart() {
		getTexasBean().setState("start");
		try {
			try {
				Thread.sleep(10000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			dos.writeUTF(gson.toJson(getTexasBean()));
			dos.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
