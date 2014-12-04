package z.texas.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;


public class Server extends Thread{
	private ServerSocket serverSocket;
	private boolean isStop = false;

	@Override
	public void run(){
		TaskManager taskManager = new TaskManager();
		Dealer dealer = new Dealer(taskManager);
		try {
			serverSocket = new ServerSocket(8000);
			while(!isStop){
				Socket socket = serverSocket.accept();
				Task task = new Task(socket, dealer, taskManager);
				taskManager.add(task, socket.getInetAddress().getHostAddress());
				new Thread(task).start();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally{
			try {
				serverSocket.close();
				taskManager.closeAll();
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
