package z.texas;

import java.util.Scanner;

import com.google.gson.Gson;

import z.texas.client.Client;
import z.texas.client.Player;
import z.texas.commons.CardBean;
import z.texas.commons.PlayerBean;
import z.texas.commons.TexasBean;
import z.texas.server.Server;

public class TexasUI {
	private static Server server;
	private static Player player;

//	public static void main(String[] args) {
//		new TexasUI().start();
//	}

	private void start() {
		System.out.println("输入命令：");
		Scanner in = new Scanner(System.in);
		while (true) {
			String str = in.nextLine();
			if (str.equals("quit")) {
				break;
			}
			switch (str) {
			case "startserver":
				server = new Server();
				server.start();
				System.out.println("服务器已启动");
				break;
			case "connect":
				//player = new Player(this);
				System.out.println("input name:");
				if (player.connect("127.0.0.1", in.nextLine(), 8000)) {
					System.out.println("连接成功");
				} else {
					System.out.println("连接失败");
				}
				break;
			case "ready":
				player.ready();
				System.out.println("已准备");
				break;
			case "call":
				player.call();
				break;
			default:
				System.out.println("输入错误");
				break;
			}
		}
		System.out.println("结束");
		if(player!=null)
			player.quit();
		if(server!=null)
			server.setStop(true);
	}

	private static String querySuit(int suitIndex) {
		String suit;
		switch (suitIndex) {
		case CardBean.SUIT_CLUBS:
			suit = "梅花";
			break;
		case CardBean.SUIT_DIAMONDS:
			suit = "方块";
			break;
		case CardBean.SUIT_HEARTS:
			suit = "红桃";
			break;
		case CardBean.SUIT_SPADES:
			suit = "黑桃";
			break;
		default:
			suit = null;
			break;
		}
		return suit;
	}

	public void print(String string) {
		System.out.println(string);
	}

	public void showCards(PlayerBean playerBean) {
		for (int i = 0; i < 2; i++) {
			System.out
					.println(querySuit(playerBean.getHands().get(i).getSuit())
							+ playerBean.getHands().get(i).getNum());
		}
	}
}
