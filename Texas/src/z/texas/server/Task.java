package z.texas.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;

import com.google.gson.Gson;

import z.texas.TexasBean;
import z.texas.game.Card;
import z.texas.game.Dealer;

public class Task implements Runnable {

	private Socket socket;
	private Dealer dealer;
	private boolean isStop;
	private SocketManager socketManager;
	private TexasBean texasBean;

	public Task(Socket socket, Dealer dealer, SocketManager socketManager) {
		this.socket = socket;
		this.dealer = dealer;
		this.socketManager = socketManager;
	}

	@Override
	public void run() {
		try {
			Gson gson = new Gson();
			isStop = false;
			while (!isStop) {
				DataInputStream dis = new DataInputStream(
						socket.getInputStream());
				DataOutputStream dos = new DataOutputStream(
						socket.getOutputStream());
				setTexasBean(gson.fromJson(dis.readUTF(), TexasBean.class));
				switch (getTexasBean().getState()) {
				case "ready":
					while (true) {
						if(socketManager.getReadyNum()>0){
							break;
						}
					}
					getTexasBean().setState("start");
					dos.writeUTF(gson.toJson(getTexasBean()));
					dos.flush();
					break;
				default:
					break;
				}
//				if (dis.readUTF().equals("takeCard")) {
//					ArrayList<Card> cards = dealer.dealCard(1);
//					DataOutputStream dos = new DataOutputStream(
//							socket.getOutputStream());
//					dos.writeUTF(cards.get(0).getNum() + ","
//							+ cards.get(0).getSuit());
//					dos.flush();
//				}
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

}
