package z.texas.game;

import java.util.ArrayList;
import java.util.Random;


public class Dealer {
	private ArrayList<Card> pile;	//牌堆

	public Dealer() {
		pile = new ArrayList<Card>();
		for (int i = 0; i < 4; i++) {
			for (int j = 2; j < 15; j++) {
				pile.add(new Card(j, i));
			}
		}
	}

	// 发牌
	public ArrayList<Card> dealCard(int num) {
		ArrayList<Card> cards = new ArrayList<Card>();
		for (int i = 0; i < num; i++) {
			int index = new Random().nextInt(pile.size());
			cards.add(pile.get(index));
			pile.remove(index);
		}
		return cards;
	}
}
