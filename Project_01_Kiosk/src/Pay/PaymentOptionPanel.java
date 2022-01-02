package Pay;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.font.TextAttribute;
import java.util.Map;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Main.MainFrame;
import etc.Payment;

class PaymentOptionPanel extends JPanel implements ActionListener, MouseListener { // 결제창
	// 3번
	JLabel lbl1, lbl2, lbl3, lbl4, totalPriceLbl;
	JButton btn1, btn2, btn3, btn4; // 이전, 다음
	JPanel pnl1, pnl2, pnl3, pnl4, pnl5;
	Payment pm;
	int select;
	ImageIcon btn1Icon, btn2Icon, btn3Icon, btn4Icon, btn5Icon, btn6Icon;

	private PayMainPanel payMainPanel;
	private JPanel mainPnl;
	
	Color isColor = Color.WHITE;
	
	int x = 550;
	int y = 80;
	
	int totalPrice;

	public PaymentOptionPanel(PayMainPanel payMainPanel, int totalPrice) {
		this.setLayout(new BorderLayout());
		this.setBackground(isColor);
		this.payMainPanel = payMainPanel;

		this.totalPrice = totalPrice;
		
		pnl1 = new JPanel();
		pnl1.setPreferredSize(new Dimension(600, 150));
		pnl1.setBackground(isColor);
		lbl2 = new JLabel("결제 방법 선택");
		lbl2.setFont(new Font("맑은 고딕", Font.BOLD, 40));
		Font font = lbl2.getFont();
		Map attributes = font.getAttributes();
		attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
		lbl2.setFont(font.deriveFont(attributes));
		pnl1.add(lbl2);

		pnl4 = new JPanel();
		pnl4.setPreferredSize(new Dimension(600, 350));
		pnl4.setBackground(isColor);
		btn3 = setOptionBtn(btn3, "현금");
		btn3Icon = new ImageIcon("ImageFile/PAY/현금.png");
		btn4 = setOptionBtn(btn4, "카드");
		btn4Icon = new ImageIcon("ImageFile/PAY/카드.png");
		pnl4.add(btn3);
		pnl4.add(btn4);

		pnl3 = new JPanel();
		pnl3.setBackground(isColor);
		JLabel totalPriceStr = null;
		totalPriceStr = setPriceLbl(totalPriceStr, "총 결제금액  ", Color.BLACK);
		totalPriceLbl = setPriceLbl(totalPriceLbl, String.valueOf(this.totalPrice), Color.RED);
		JLabel wonLbl = null; 
		wonLbl = setPriceLbl(wonLbl, "원", Color.RED);


		// =============================================
		
		mainPnl = new JPanel();
		mainPnl.setBackground(isColor);
		mainPnl.add(pnl1);
		mainPnl.add(pnl4);
		mainPnl.add(pnl3);
		this.add(mainPnl, "Center");
		
		pnl5 = new JPanel();
		pnl5.setBackground(isColor);
		btn1 = setSouthBtn(btn1, "이전");
		btn2 = setSouthBtn(btn2, "다음");
		pnl5.add(btn1);
		pnl5.add(btn2);
		this.add(pnl5, "South");
	}
	
	public void setTotalPrice(int totalPrice) {
		this.totalPrice = totalPrice;
	}
	
	public JLabel setPriceLbl(JLabel lbl, String str, Color color) {
		lbl = new JLabel(str);
		lbl.setFont(new Font("맑은 고딕", Font.BOLD, 35));
		lbl.setForeground(color);
		pnl3.add(lbl);
		return lbl;
	}
	
	public JButton setOptionBtn(JButton btn, String str) {
		btn = new JButton();
		ImageIcon btnIcon = new ImageIcon("ImageFile/PAY/"+str+".png");
		btn.setIcon(btnIcon);
		btn.setBackground(isColor);
		btn.setBorderPainted(false);
		btn.setFocusPainted(false);
		btn.addMouseListener(this);
		return btn; 
	}

	public JButton setSouthBtn(JButton btn, String str) {
		btn = new JButton();
		btn.addActionListener(this);
		btn.setPreferredSize(new Dimension(286, 70));
		btn.setBorderPainted(false);
		btn.setFocusPainted(false);
		btn.setBackground(isColor);
		ImageIcon icon = new ImageIcon("ImageFile/PAY/"+str+".png");
		Image img = icon.getImage();
		Image imgResize = img.getScaledInstance(286, 70, Image.SCALE_SMOOTH);
		ImageIcon btnIcon = new ImageIcon(imgResize);
		btn.setIcon(btnIcon);
		return btn;
	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btn1) {
			this.payMainPanel.changePanel(2);
		} else if (e.getSource() == btn2) {
			if (select == 1) { // 현금
				this.payMainPanel.changePanel(4);
			} else if (select == 2) { // 카드
				this.payMainPanel.changePanel(5);
				Thread t = new Thread(this.payMainPanel.pnl05, "5");
				this.payMainPanel.pnl05.thread_start = true;
				t.start();
			}
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getSource() == btn3) {
			btn5Icon = new ImageIcon("ImageFile/PAY/현금체크.png");
			btn3.setIcon(btn5Icon);
			btn4.setIcon(btn4Icon);
			select = 1;
		} else if (e.getSource() == btn4) {
			btn6Icon = new ImageIcon("ImageFile/PAY/카드체크.png");
			btn4.setIcon(btn6Icon);
			btn3.setIcon(btn3Icon);
			select = 2;
		}
		this.revalidate();
		this.repaint();
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}
}