package z.texas;

public class Card {
	public static final int SUIT_SPADES = 0;
	public static final int SUIT_HEARTS = 1;
	public static final int SUIT_CLUBS = 2;
	public static final int SUIT_DIAMONDS = 3;
	private int num;
	private int suit;
	public Card(int num, int suit){
		this.num = num;
		this.suit = suit;
	}
	public int getNum() {
		return num;
	}
	public int getSuit() {
		return suit;
	}
}
