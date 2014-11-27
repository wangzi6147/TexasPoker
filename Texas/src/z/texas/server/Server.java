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
		SocketManager socketManager = new SocketManager();
		Dealer dealer = new Dealer();
		try {
			serverSocket = new ServerSocket(8000);
			while(!isStop){
				Socket socket = serverSocket.accept();
				Task task = new Task(socket, dealer, socketManager);
				socketManager.add(task);
				new Thread(task).start();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally{
			try {
				serverSocket.close();
				socketManager.closeAll();
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
