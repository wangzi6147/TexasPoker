package z.texas;

import java.util.ArrayList;

public class Texas {
	public static void main(String[] args) {
		initDealer();
		new Server().start();
		Client client = new Client();
		if(client.connect("127.0.0.1", 8080))
			System.out.println("连接成功");
		client.send("test");
	}

	private static void initDealer() {
		Dealer dealer = new Dealer();
		String suit;
		ArrayList<Card> hands = dealer.dealCard(2);
		ArrayList<Card> flop = dealer.dealCard(3);
		ArrayList<Card> turn = dealer.dealCard(1);
		ArrayList<Card> river = dealer.dealCard(1);
		for (int i = 0; i < 2; i++) {
			suit = querySuit(hands.get(i).getSuit());
			System.out.println("第" + (i+1) + "张手牌数字是：" + hands.get(i).getNum());
			System.out.println("第" + (i+1) + "张手牌花色是：" + suit);
		}
		for (int i = 0; i < 3; i++) {
			suit = querySuit(flop.get(i).getSuit());
			System.out.println("第" + (i+1) + "张公共牌数字是：" + flop.get(i).getNum());
			System.out.println("第" + (i+1) + "张公共牌花色是：" + suit);
		}

		suit = querySuit(turn.get(0).getSuit());
		System.out.println("第" + (4) + "张公共牌数字是：" + turn.get(0).getNum());
		System.out.println("第" + (4) + "张公共牌花色是：" + suit);

		suit = querySuit(river.get(0).getSuit());
		System.out.println("第" + (5) + "张公共牌数字是：" + river.get(0).getNum());
		System.out.println("第" + (5) + "张公共牌花色是：" + suit);
		
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
