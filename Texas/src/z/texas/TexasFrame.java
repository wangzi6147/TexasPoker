package z.texas;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import z.texas.client.Player;
import z.texas.commons.CardBean;
import z.texas.commons.PlayerBean;
import z.texas.commons.TexasBean;
import z.texas.server.Server;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class TexasFrame extends JFrame {

	private JPanel contentPane;
	protected Server server;
	private JTextField txtAddress;
	private JTextField textField_port;
	private JTextField textField_name;
	protected Player player;
	private JLabel lbl_msg;
	private JButton btnNewButton_3;
	private JButton btnCall;
	private JLabel lbl_card_1;
	private JLabel lbl_card_2;
	private JTextField txt_bet;
	private JLabel lbl_p2_card_1;
	private JLabel lbl_p2_card_2;
	private JLabel lbl_p2_bet;
	private JLabel lbl_p2_money;
	private JLabel lbl_money;
	private JLabel lbl_bet;
	private JLabel lbl_flop_1;
	private JLabel lbl_flop_2;
	private JLabel lbl_flop_3;
	private JLabel lbl_turn;
	private JLabel lbl_river;
	private JLabel lbl_state;
	private JLabel lbl_p2_state;
	private JLabel lblWinnerType;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {

			public void run() {
				try {
					TexasFrame frame = new TexasFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * 
	 * @param frame
	 */
	public TexasFrame() {

		player = new Player(this);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 693, 457);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		lbl_msg = new JLabel("Hello Texas");
		lbl_msg.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_msg.setBounds(10, 10, 298, 15);
		contentPane.add(lbl_msg);

		txtAddress = new JTextField();
		txtAddress.setBounds(10, 74, 93, 21);
		contentPane.add(txtAddress);
		txtAddress.setColumns(10);

		textField_port = new JTextField();
		textField_port.setBounds(113, 74, 66, 21);
		contentPane.add(textField_port);
		textField_port.setColumns(10);

		textField_name = new JTextField();
		textField_name.setBounds(10, 105, 66, 21);
		contentPane.add(textField_name);
		textField_name.setColumns(10);

		JButton btnNewButton = new JButton("开启服务器");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				server = new Server();
				server.start();
				lbl_msg.setText("服务器已启动");
			}
		});
		btnNewButton.setBounds(10, 41, 169, 23);
		contentPane.add(btnNewButton);

		JButton btnNewButton_1 = new JButton("链接");
		btnNewButton_1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (player.connect(txtAddress.getText(),
						textField_name.getText(),
						Integer.parseInt(textField_port.getText()))) {
					lbl_msg.setText("连接成功");
				} else {
					lbl_msg.setText("连接失败");
				}
			}
		});
		btnNewButton_1.setBounds(86, 105, 93, 23);
		contentPane.add(btnNewButton_1);

		JButton btnNewButton_2 = new JButton("离开");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (player != null)
					player.quit();
				if (server != null)
					server.setStop(true);
			}
		});
		btnNewButton_2.setBounds(10, 386, 93, 23);
		contentPane.add(btnNewButton_2);

		btnNewButton_3 = new JButton("准备");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				player.ready();
				lbl_msg.setText("已准备");
			}
		});
		btnNewButton_3.setBounds(10, 136, 93, 23);
		contentPane.add(btnNewButton_3);

		btnCall = new JButton("call");
		btnCall.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				player.call();
			}
		});
		btnCall.setBounds(574, 386, 93, 23);
		contentPane.add(btnCall);

		lbl_card_1 = new JLabel("card_1");
		lbl_card_1.setBounds(307, 328, 60, 23);
		contentPane.add(lbl_card_1);

		lbl_card_2 = new JLabel("card_2");
		lbl_card_2.setBounds(377, 328, 60, 23);
		contentPane.add(lbl_card_2);

		lbl_money = new JLabel("money");
		lbl_money.setBounds(307, 365, 60, 23);
		contentPane.add(lbl_money);

		lbl_bet = new JLabel("bet");
		lbl_bet.setBounds(377, 365, 60, 23);
		contentPane.add(lbl_bet);

		lbl_p2_card_1 = new JLabel("card_1");
		lbl_p2_card_1.setBounds(307, 41, 60, 23);
		contentPane.add(lbl_p2_card_1);

		lbl_p2_card_2 = new JLabel("card_2");
		lbl_p2_card_2.setBounds(377, 41, 60, 23);
		contentPane.add(lbl_p2_card_2);

		lbl_p2_bet = new JLabel("bet");
		lbl_p2_bet.setBounds(377, 78, 60, 23);
		contentPane.add(lbl_p2_bet);

		lbl_p2_money = new JLabel("money");
		lbl_p2_money.setBounds(307, 78, 60, 23);
		contentPane.add(lbl_p2_money);

		lbl_flop_1 = new JLabel("flop_1");
		lbl_flop_1.setBounds(214, 174, 60, 23);
		contentPane.add(lbl_flop_1);

		lbl_flop_2 = new JLabel("flop_2");
		lbl_flop_2.setBounds(295, 174, 60, 23);
		contentPane.add(lbl_flop_2);

		lbl_flop_3 = new JLabel("flop_3");
		lbl_flop_3.setBounds(377, 174, 60, 23);
		contentPane.add(lbl_flop_3);

		lbl_turn = new JLabel("turn");
		lbl_turn.setBounds(463, 174, 60, 23);
		contentPane.add(lbl_turn);

		lbl_river = new JLabel("river");
		lbl_river.setBounds(548, 174, 60, 23);
		contentPane.add(lbl_river);

		JButton btnCheck = new JButton("check");
		btnCheck.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				player.check();
			}
		});
		btnCheck.setBounds(574, 354, 93, 23);
		contentPane.add(btnCheck);

		JButton btnRaise = new JButton("raise");
		btnRaise.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				player.raise(Integer.parseInt(txt_bet.getText()));
			}
		});
		btnRaise.setBounds(574, 321, 93, 23);
		contentPane.add(btnRaise);

		txt_bet = new JTextField();
		txt_bet.setBounds(574, 290, 93, 21);
		contentPane.add(txt_bet);
		txt_bet.setColumns(10);

		JButton btnFold = new JButton("fold");
		btnFold.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				player.fold();
			}
		});
		btnFold.setBounds(471, 386, 93, 23);
		contentPane.add(btnFold);
		
		lbl_p2_state = new JLabel("state");
		lbl_p2_state.setBounds(220, 60, 54, 15);
		contentPane.add(lbl_p2_state);
		
		lbl_state = new JLabel("state");
		lbl_state.setBounds(220, 347, 54, 15);
		contentPane.add(lbl_state);
		
		lblWinnerType = new JLabel("winner type");
		lblWinnerType.setBounds(574, 45, 93, 15);
		contentPane.add(lblWinnerType);

	}

	public void print(String string) {
		lbl_msg.setText(string);
	}

	private void showCards(PlayerBean playerBean) {
		lbl_card_1.setText(querySuit(playerBean.getHands().get(0).getSuit())
				+ playerBean.getHands().get(0).getNum());
		lbl_card_2.setText(querySuit(playerBean.getHands().get(1).getSuit())
				+ playerBean.getHands().get(1).getNum());
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

	public void showItems(TexasBean texasBean) {
		int pos = texasBean.getPlayer().getPos();

		// player
		showCards(texasBean.getPlayer());
		lbl_state.setText(texasBean.getPlayer().getState());
		lbl_money.setText(texasBean.getPlayer().getMoney() + "");
		lbl_bet.setText(texasBean.getPlayer().getBet() + "");
		
		// winner
		if(texasBean.getWinnerType()!=0){
			lblWinnerType.setText(getWinnerType(texasBean.getWinnerType()));			
		}else {
			lblWinnerType.setText("winner type");
		}

		// flops
		if (texasBean.getFlops()!=null&&texasBean.getFlops().size()>2) {
			lbl_flop_1.setText(querySuit(texasBean.getFlops().get(0).getSuit())
					+ texasBean.getFlops().get(0).getNum());
			lbl_flop_2.setText(querySuit(texasBean.getFlops().get(1).getSuit())
					+ texasBean.getFlops().get(1).getNum());
			lbl_flop_3.setText(querySuit(texasBean.getFlops().get(2).getSuit())
					+ texasBean.getFlops().get(2).getNum());
		}else {
			lbl_flop_1.setText("flop_1");
			lbl_flop_2.setText("flop_2");
			lbl_flop_3.setText("flop_3");
		}
		
		// turn
		if(texasBean.getTurn()!=null){
			lbl_turn.setText(querySuit(texasBean.getTurn().getSuit())
					+ texasBean.getTurn().getNum());
		}else {
			lbl_turn.setText("turn");
		}
		
		//river
		if(texasBean.getRiver()!=null){
			lbl_river.setText(querySuit(texasBean.getRiver().getSuit())
					+ texasBean.getRiver().getNum());
		}else {
			lbl_river.setText("river");
		}

		// others
		for (int i = pos + 1; i < texasBean.getMaxPlayerNum(); i++) {
			String state = texasBean.getTableState();
			if (state != null) {
				if (state.equals("show")) {
					lbl_p2_card_1.setText(querySuit(texasBean.getOthers()
							.get(i).getHands().get(0).getSuit())
							+ texasBean.getOthers().get(i).getHands().get(0)
									.getNum());
					lbl_p2_card_2.setText(querySuit(texasBean.getOthers()
							.get(i).getHands().get(1).getSuit())
							+ texasBean.getOthers().get(i).getHands().get(1)
									.getNum());
				} else {
					lbl_p2_card_1.setText("card");
					lbl_p2_card_2.setText("card");
				}
				lbl_p2_money.setText(texasBean.getOthers().get(i).getMoney()
						+ "");
				lbl_p2_bet.setText(texasBean.getOthers().get(i).getBet() + "");
				lbl_p2_state.setText(texasBean.getOthers().get(i).getState());
			}
		}
		for (int i = 0; i < pos; i++) {
			String state = texasBean.getTableState();
			if (state != null) {
				if (state.equals("show")) {
					lbl_p2_card_1.setText(querySuit(texasBean.getOthers()
							.get(i).getHands().get(0).getSuit())
							+ texasBean.getOthers().get(i).getHands().get(0)
									.getNum());
					lbl_p2_card_2.setText(querySuit(texasBean.getOthers()
							.get(i).getHands().get(1).getSuit())
							+ texasBean.getOthers().get(i).getHands().get(1)
									.getNum());
				} else {
					lbl_p2_card_1.setText("card");
					lbl_p2_card_2.setText("card");
				}
				lbl_p2_money.setText(texasBean.getOthers().get(i).getMoney()
						+ "");
				lbl_p2_bet.setText(texasBean.getOthers().get(i).getBet() + "");
				lbl_p2_state.setText(texasBean.getOthers().get(i).getState());
			}
		}
	}

	private String getWinnerType(int winnerType) {
		String type = null;
		switch (winnerType) {
		case 10:
			type = "皇家同花顺赢";
			break;
		case 9:
			type = "同花顺赢";
			break;
		case 8:
			type = "四条赢";
			break;
		case 7:
			type = "葫芦赢";
			break;
		case 6:
			type = "同花赢";
			break;
		case 5:
			type = "顺子赢";
			break;
		case 4:
			type = "三条赢";
			break;
		case 3:
			type = "两对赢";
			break;
		case 2:
			type = "对子赢";
			break;
		case 1:
			type = "高牌赢";
			break;
		default:
			break;
		}
		return type;
	}
}
