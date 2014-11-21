package z.texas;

import java.util.ArrayList;
import java.util.Scanner;

import z.texas.game.Card;
import z.texas.game.Player;
import z.texas.server.Server;

public class Texas {
	@SuppressWarnings("resource")
	public static void main(String[] args) {
		Server server = new Server();
		server.start();
		System.out.println("输入start开始游戏：");
		Scanner in = new Scanner(System.in);
		while (true) {
			String str = in.nextLine();
			if (str.equals("quit")) {
				break;
			}
			if (str.equals("start")) {
				Client client = new Client();
				Player player = new Player(client);
				client.connect("127.0.0.1", 8000);
				for (int i = 0; i < 52; i++) {
					player.takeCard();
					System.out.println("第" + (i + 1) + "张牌是"
							+ querySuit(player.getHands().get(i).getSuit())
							+ player.getHands().get(i).getNum());
				}
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
