package z.texas.commons;

import java.util.ArrayList;

public class CardUtil {
	public static int recognise(PlayerBean playerBean, TexasBean curBean) {
		int type = 0;
		ArrayList<CardBean> allCards = getAllCards(playerBean, curBean);
		allCards = sort(allCards);
		if (isRoyalStraightFlush(allCards)) {
			type = 10;
		} else if (isStraightFlush(allCards)) {
			type = 9;
		} else if (isFour(allCards)) {
			type = 8;
		} else if (isGourd(allCards)) {
			type = 7;
		} else if (isFlush(allCards)) {
			type = 6;
		} else if (isStraight(allCards)) {
			type = 5;
		} else if (isTriple(allCards)) {
			type = 4;
		} else if (isTwoPairs(allCards)) {
			type = 3;
		} else if (isPair(allCards)) {
			type = 2;
		} else {
			type = 1;
		}
		return type;
	}

	private static boolean isPair(ArrayList<CardBean> allCards) {
		int count = 0;
		int curNum = 0;
		for (CardBean cardBean : allCards) {
			if (cardBean.getNum() != curNum) {
				count = 1;
				curNum = cardBean.getNum();
			} else {
				count++;
				if (count > 1) {
					return true;
				}
			}
		}
		return false;
	}

	private static boolean isTwoPairs(ArrayList<CardBean> allCards) {
		int count = 0;
		int curNum = 0;
		int pairNum = 0;
		for (CardBean cardBean : allCards) {
			if (cardBean.getNum() != curNum) {
				count = 1;
				curNum = cardBean.getNum();
			} else {
				count++;
				if (count == 2) {
					pairNum++;
				}
			}
		}
		if (pairNum > 1) {
			return true;
		}
		return false;
	}

	private static boolean isTriple(ArrayList<CardBean> allCards) {
		int count = 0;
		int curNum = 0;
		for (CardBean cardBean : allCards) {
			if (cardBean.getNum() != curNum) {
				count = 1;
				curNum = cardBean.getNum();
			} else {
				count++;
				if (count > 2) {
					return true;
				}
			}
		}
		return false;
	}

	private static boolean isStraight(ArrayList<CardBean> allCards) {
		int count = 0;
		int curNum = 0;
		for (CardBean cardBean : allCards) {
			if (cardBean.getNum() == curNum) {
				continue;
			} else if (cardBean.getNum() == curNum - 1) {
				count++;
				if (count > 4) {
					return true;
				}
			} else {
				count = 1;
			}
			curNum = cardBean.getNum();
		}
		return false;
	}

	private static boolean isFlush(ArrayList<CardBean> allCards) {
		int count = 0;
		int curSuit = -1;
		allCards = sortAsSuit(allCards);
		for (CardBean cardBean : allCards) {
			if (cardBean.getNum() != curSuit) {
				count = 1;
				curSuit = cardBean.getSuit();
			} else {
				count++;
				if (count > 4) {
					return true;
				}
			}
		}
		return false;
	}

	private static boolean isGourd(ArrayList<CardBean> allCards) {
		int count = 0;
		int curNum = 0;
		int tripleNum = 0;
		int pairNum = 0;
		for (CardBean cardBean : allCards) {
			if (cardBean.getNum() != curNum) {
				count = 1;
				curNum = cardBean.getNum();
			} else {
				count++;
				if (count == 3) {
					tripleNum = curNum;
					break;
				}
			}
		}
		count = 0;
		curNum = 0;
		for (CardBean cardBean : allCards) {
			if (cardBean.getNum() != curNum) {
				count = 1;
				curNum = cardBean.getNum();
			} else {
				count++;
				if (count == 2 && curNum != tripleNum) {
					pairNum = curNum;
					break;
				}
			}
		}
		if (tripleNum != 0 && pairNum != 0) {
			return true;
		}
		return false;
	}

	private static boolean isFour(ArrayList<CardBean> allCards) {
		int count = 0;
		int curNum = 0;
		for (CardBean cardBean : allCards) {
			if (cardBean.getNum() != curNum) {
				count = 1;
				curNum = cardBean.getNum();
			} else {
				count++;
				if (count > 3) {
					return true;
				}
			}
		}
		return false;
	}

	private static boolean isStraightFlush(ArrayList<CardBean> allCards) {
		int count = 0;
		int curSuit = -1;
		allCards = sortAsSuit(allCards);
		for (CardBean cardBean : allCards) {
			if (cardBean.getNum() != curSuit) {
				count = 1;
				curSuit = cardBean.getSuit();
			} else {
				count++;
				if (count > 4) {
					break;
				}
			}
		}
		allCards = sort(allCards);
		count = 0;
		int curNum = 0;
		for (CardBean cardBean : allCards) {
			if (cardBean.getSuit() == curSuit) {
				if (cardBean.getNum() == curNum) {
					continue;
				} else if (cardBean.getNum() == curNum - 1) {
					count++;
					if (count > 4) {
						return true;
					}
				} else {
					count = 1;
				}
				curNum = cardBean.getNum();
			}
		}
		return false;
	}

	private static boolean isRoyalStraightFlush(ArrayList<CardBean> allCards) {
		int count = 0;
		int curSuit = -1;
		int maxStraightNum = 0;
		allCards = sortAsSuit(allCards);
		for (CardBean cardBean : allCards) {
			if (cardBean.getNum() != curSuit) {
				count = 1;
				curSuit = cardBean.getSuit();
			} else {
				count++;
				if (count > 4) {
					break;
				}
			}
		}
		allCards = sort(allCards);
		count = 0;
		int curNum = 0;
		for (CardBean cardBean : allCards) {
			if (cardBean.getSuit() == curSuit) {
				if (cardBean.getNum() == curNum) {
					continue;
				} else if (cardBean.getNum() == curNum - 1) {
					count++;
					if (count > 4&&maxStraightNum==14) {
						return true;
					}
				} else {
					count = 1;
					maxStraightNum = cardBean.getNum();
				}
				curNum = cardBean.getNum();
			}
		}
		return false;
	}

	public static PlayerBean compareSameType(PlayerBean winnerBean,
			PlayerBean curPlayerBean, int maxType) {
		// TODO Auto-generated method stub
		return winnerBean;
	}

	private static ArrayList<CardBean> sortAsSuit(ArrayList<CardBean> allCards) {
		ArrayList<CardBean> result = new ArrayList<CardBean>();
		result.add(allCards.get(0));
		allCards.remove(0);
		boolean ifInsert = false;
		for (int i = 0; i < allCards.size(); i++) {
			for (int j = 0; j < result.size(); j++) {
				if (allCards.get(i).getSuit() > result.get(j).getSuit()) {
					result.add(j, allCards.get(i));
					ifInsert = true;
					break;
				}
			}
			if (!ifInsert) {
				result.add(allCards.get(i));
			}
		}
		return result;
	}

	private static ArrayList<CardBean> sort(ArrayList<CardBean> allCards) {
		ArrayList<CardBean> result = new ArrayList<CardBean>();
		result.add(allCards.get(0));
		allCards.remove(0);
		boolean ifInsert = false;
		for (int i = 0; i < allCards.size(); i++) {
			for (int j = 0; j < result.size(); j++) {
				if (allCards.get(i).getNum() > result.get(j).getNum()) {
					result.add(j, allCards.get(i));
					ifInsert = true;
					break;
				}
			}
			if (!ifInsert) {
				result.add(allCards.get(i));
			}
		}
		return result;
	}

	public static boolean ifWinInSameType(PlayerBean playerBean,
			PlayerBean winnerBean, TexasBean curBean, int maxType) {
		// TODO Auto-generated method stub
		return false;
	}

	private static ArrayList<CardBean> getAllCards(PlayerBean playerBean,
			TexasBean curBean) {
		ArrayList<CardBean> allCards = new ArrayList<CardBean>();
		allCards.add(playerBean.getHands().get(0));
		allCards.add(playerBean.getHands().get(1));
		for (CardBean cardBean : curBean.getFlops()) {
			allCards.add(cardBean);
		}
		allCards.add(curBean.getTurn());
		allCards.add(curBean.getRiver());
		return allCards;
	}
}
