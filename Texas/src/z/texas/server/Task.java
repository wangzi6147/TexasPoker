package z.texas.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;

import z.texas.game.Card;
import z.texas.game.Dealer;

public class Task implements Runnable {

	private Socket socket;
	private Dealer dealer;

	public Task(Socket socket, Dealer dealer) {
		this.socket = socket;
		this.dealer = dealer;
	}

	@Override
	public void run() {
		try {
			while (true) {
				DataInputStream dis = new DataInputStream(
						socket.getInputStream());
				if (dis.readUTF().equals("takeCard")) {
					ArrayList<Card> cards = dealer.dealCard(1);
					DataOutputStream dos = new DataOutputStream(
							socket.getOutputStream());
					dos.writeUTF(cards.get(0).getNum() + ","
							+ cards.get(0).getSuit());
					dos.flush();
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
