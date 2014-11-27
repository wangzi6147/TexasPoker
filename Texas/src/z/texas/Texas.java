package z.texas;

import java.util.Scanner;

import com.google.gson.Gson;

import z.texas.game.Card;
import z.texas.game.Player;
import z.texas.server.Server;

public class Texas {
	private static Server server;
	private static Player player;
	private static Client client;
	private static Gson gson;
	private static TexasBean texasBean;

	@SuppressWarnings("resource")
	public static void main(String[] args) {
		System.out.println("输入命令：");
		Scanner in = new Scanner(System.in);
		while (true) {
			String str = in.nextLine();
			switch (str) {
			case "startserver":
				server = new Server();
				server.start();
				System.out.println("服务器已启动");
				break;
			case "connect":
				client = new Client();
				player = new Player(client);
				if(client.connect("127.0.0.1", 8000)){
					System.out.println("连接成功");
				}else {
					System.out.println("连接失败");
				}
				break;
			case "start":
				gson = new Gson();
				texasBean = new TexasBean();
				texasBean.setState("ready");
				String json = gson.toJson(texasBean);
				String result = client.send(json);
				texasBean = gson.fromJson(result, TexasBean.class);
				System.out.println(texasBean.getState());
//				for (int i = 0; i < 52; i++) {
//					player.takeCard();
//					System.out.println("第" + (i + 1) + "张牌是"
//							+ querySuit(player.getHands().get(i).getSuit())
//							+ player.getHands().get(i).getNum());
//				}
				break;
			default:
				break;
			}
			if (str.equals("quit")) {
				break;
			}
		}
		System.out.println("结束");
		server.setStop(true);
	}

	private static String querySuit(int suitIndex) {
		String suit;
		switch (suitIndex) {
		case Card.SUIT_CLUBS:
			suit = "梅花";
			break;
		case Card.SUIT_DIAMONDS:
			suit = "方块";
			break;
		case Card.SUIT_HEARTS:
			suit = "红桃";
			break;
		case Card.SUIT_SPADES:
			suit = "黑桃";
			break;
		default:
			suit = null;
			break;
		}
		return suit;
	}
}
