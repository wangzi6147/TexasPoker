package z.texas.client;

import java.util.ArrayList;

import z.texas.commons.Card;




public class Player {
	private Client client;
	private ArrayList<Card> hands;

	public Player(Client client) {
		this.client = client;
		hands = new ArrayList<Card>();
	}
	
	public void takeCard(){
		String result = client.send("takeCard");
		String[] splits = result.split(",");
		int num = Integer.parseInt(splits[0]);
		int suit = Integer.parseInt(splits[1]);
		getHands().add(new Card(num, suit));
	}

	public ArrayList<Card> getHands() {
		return hands;
	}

}
