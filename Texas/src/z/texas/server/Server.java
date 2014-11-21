package z.texas.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import z.texas.game.Dealer;

public class Server extends Thread{
	private ServerSocket serverSocket;
	private boolean isStop = false;

	@Override
	public void run(){
		try {
			serverSocket = new ServerSocket(8000);
			Dealer dealer = new Dealer();
			while(!isStop){
				Socket socket = serverSocket.accept();
				new Thread(new Task(socket, dealer)).start();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally{
			try {
				serverSocket.close();
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
