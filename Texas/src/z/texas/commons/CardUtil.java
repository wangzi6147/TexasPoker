package z.texas.commons;

import java.util.ArrayList;

public class CardUtil {
	public static int recognise(PlayerBean playerBean, TexasBean curBean) {
		int type = 0;
		ArrayList<CardBean> allCards = getAllCards(playerBean, curBean);
		allCards = sort(allCards);
		if(isRoyalStraightFlush(allCards)){
			type = 10;
		}else if (isStraightFlush(allCards)) {
			type = 9;
		}else if (isFour(allCards)) {
			type = 8;
		}else if (isGourd(allCards)) {
			type = 7;
		}else if (isFlush(allCards)) {
			type = 6;
		}else if (isStraight(allCards)) {
			type = 5;
		}else if (isTriple(allCards)) {
			type = 4;
		}else if (isTwoPairs(allCards)) {
			type = 3;
		}else if (isPair(allCards)) {
			type = 2;
		}else {
			type = 1;
		}
		return type;
	}
	


	private static boolean isPair(ArrayList<CardBean> allCards) {
		// TODO Auto-generated method stub
		return false;
	}



	private static boolean isTwoPairs(ArrayList<CardBean> allCards) {
		// TODO Auto-generated method stub
		return false;
	}



	private static boolean isTriple(ArrayList<CardBean> allCards) {
		// TODO Auto-generated method stub
		return false;
	}



	private static boolean isStraight(ArrayList<CardBean> allCards) {
		// TODO Auto-generated method stub
		return false;
	}



	private static boolean isFlush(ArrayList<CardBean> allCards) {
		// TODO Auto-generated method stub
		return false;
	}



	private static boolean isGourd(ArrayList<CardBean> allCards) {
		// TODO Auto-generated method stub
		return false;
	}



	private static boolean isFour(ArrayList<CardBean> allCards) {
		// TODO Auto-generated method stub
		return false;
	}



	private static boolean isStraightFlush(ArrayList<CardBean> allCards) {
		// TODO Auto-generated method stub
		return false;
	}



	private static boolean isRoyalStraightFlush(ArrayList<CardBean> allCards) {
		return false;
	}



	public static PlayerBean compareSameType(PlayerBean winnerBean,
			PlayerBean curPlayerBean, int maxType) {
		// TODO Auto-generated method stub
		return winnerBean;
	}
	
	private static ArrayList<CardBean> sort(ArrayList<CardBean> allCards) {
		ArrayList<CardBean> result = new ArrayList<CardBean>();
		result.add(allCards.get(0));
		allCards.remove(0);
		boolean ifInsert = false;
		for(int i = 0; i < allCards.size(); i++){
			for(int j = 0; j < result.size(); j++){
				if(allCards.get(i).getNum()>result.get(j).getNum()){
					result.add(j, allCards.get(i));
					ifInsert = true;
					break;
				}
			}
			if(!ifInsert){
				result.add(allCards.get(i));
			}
		}
		return result;
	}



	public static boolean ifWinInSameType(PlayerBean playerBean, PlayerBean winnerBean,
			TexasBean curBean, int maxType) {
		// TODO Auto-generated method stub
		return false;
	}



	private static ArrayList<CardBean> getAllCards(PlayerBean playerBean,
			TexasBean curBean) {
		ArrayList<CardBean> allCards = playerBean.getHands();
		for (CardBean cardBean : curBean.getFlops()) {
			allCards.add(cardBean);
		}
		allCards.add(curBean.getTurn());
		allCards.add(curBean.getRiver());
		return allCards;
	}
}
