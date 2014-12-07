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
	private JLabel lbl_state;
	private JButton btnNewButton_3;
	private JButton btnCall;
	private JLabel lbl_card_1;
	private JLabel lbl_card_2;
	private JTextField txt_bet;

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

		lbl_state = new JLabel("Hello Texas");
		lbl_state.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_state.setBounds(10, 10, 298, 15);
		contentPane.add(lbl_state);
		
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
				lbl_state.setText("服务器已启动");
			}
		});
		btnNewButton.setBounds(10, 41, 169, 23);
		contentPane.add(btnNewButton);

		JButton btnNewButton_1 = new JButton("链接");
		btnNewButton_1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (player.connect(txtAddress.getText(), textField_name.getText(),
						Integer.parseInt(textField_port.getText()))) {
					lbl_state.setText("连接成功");
				} else {
					lbl_state.setText("连接失败");
				}
			}
		});
		btnNewButton_1.setBounds(86, 105, 93, 23);
		contentPane.add(btnNewButton_1);

		JButton btnNewButton_2 = new JButton("离开");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(player!=null)
					player.quit();
				if(server!=null)
					server.setStop(true);
			}
		});
		btnNewButton_2.setBounds(10, 386, 93, 23);
		contentPane.add(btnNewButton_2);
		
		btnNewButton_3 = new JButton("准备");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				player.ready();
				lbl_state.setText("已准备");
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
		
		JLabel lbl_money = new JLabel("money");
		lbl_money.setBounds(307, 365, 60, 23);
		contentPane.add(lbl_money);
		
		JLabel lbl_bet = new JLabel("bet");
		lbl_bet.setBounds(377, 365, 60, 23);
		contentPane.add(lbl_bet);
		
		JLabel lbl_p1_card_1 = new JLabel("card_1");
		lbl_p1_card_1.setBounds(307, 41, 60, 23);
		contentPane.add(lbl_p1_card_1);
		
		JLabel lbl_p1_card_2 = new JLabel("card_2");
		lbl_p1_card_2.setBounds(377, 41, 60, 23);
		contentPane.add(lbl_p1_card_2);
		
		JLabel lbl_p2_bet = new JLabel("bet");
		lbl_p2_bet.setBounds(377, 78, 60, 23);
		contentPane.add(lbl_p2_bet);
		
		JLabel lbl_p2_money = new JLabel("money");
		lbl_p2_money.setBounds(307, 78, 60, 23);
		contentPane.add(lbl_p2_money);
		
		JLabel lbl_flop_1 = new JLabel("flop_1");
		lbl_flop_1.setBounds(214, 174, 60, 23);
		contentPane.add(lbl_flop_1);
		
		JLabel lbl_flop_2 = new JLabel("flop_2");
		lbl_flop_2.setBounds(295, 174, 60, 23);
		contentPane.add(lbl_flop_2);
		
		JLabel lbl_flop_3 = new JLabel("flop_3");
		lbl_flop_3.setBounds(377, 174, 60, 23);
		contentPane.add(lbl_flop_3);
		
		JLabel lbl_turn = new JLabel("turn");
		lbl_turn.setBounds(463, 174, 60, 23);
		contentPane.add(lbl_turn);
		
		JLabel lbl_river = new JLabel("river");
		lbl_river.setBounds(548, 174, 60, 23);
		contentPane.add(lbl_river);
		
		JButton btnCheck = new JButton("check");
		btnCheck.setBounds(574, 354, 93, 23);
		contentPane.add(btnCheck);
		
		JButton btnRaise = new JButton("raise");
		btnRaise.setBounds(574, 321, 93, 23);
		contentPane.add(btnRaise);
		
		txt_bet = new JTextField();
		txt_bet.setBounds(574, 290, 93, 21);
		contentPane.add(txt_bet);
		txt_bet.setColumns(10);
		
		JButton btnFold = new JButton("fold");
		btnFold.setBounds(471, 386, 93, 23);
		contentPane.add(btnFold);
		
	}
	
	public void print(String string) {
		lbl_state.setText(string);
	}
	
	public void showCards(PlayerBean playerBean) {
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
}
