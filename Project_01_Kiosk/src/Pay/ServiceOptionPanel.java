package Pay;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.font.TextAttribute;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;

import Main.MainFrame;
import etc.Payment;
import etc.TranslucenceDialog;

class ServiceOptionPanel extends JPanel implements ActionListener { // 결제창 2번
	JLabel lbl1, lbl2, totalPriceLbl;
	JButton btn1, btn2, btn3, btn4; // 이전, 다음
	JPanel pnl1, pnl2, pricePnl, paymentOptionPnl;
	private JPanel mainPnl, btnPnl;
	Payment pm;
	int t; // 로그인 시 t=1 //비회원일 시 t=2

	private MainFrame mainFrame;

	int totalPrice;

	private PayMainPanel payMainPanel;
	Color isColor = Color.WHITE;

	public ServiceOptionPanel(MainFrame mainFrame, PayMainPanel payMainPanel, int totalPrice) {
		this.setLayout(new BorderLayout());
		this.setBackground(isColor);
		
		this.mainFrame = mainFrame;
		
		this.payMainPanel = payMainPanel;
		this.totalPrice = totalPrice; 
		
		pnl1 = new JPanel();
		pnl1.setPreferredSize(new Dimension(600, 150));
		pnl1.setBackground(isColor);
		lbl2 = new JLabel("멤버십 & 쿠폰");
		lbl2.setFont(new Font("맑은 고딕", Font.BOLD, 40));
		Font font = lbl2.getFont();
		Map attributes = font.getAttributes();
		attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
		lbl2.setFont(font.deriveFont(attributes));
		pnl1.add(lbl2);

		paymentOptionPnl = new JPanel();
		paymentOptionPnl.setPreferredSize(new Dimension(600, 350));
		paymentOptionPnl.setBackground(isColor);
		btn3 = setOptionBtn(btn3, "멤버십");
		btn4 = setOptionBtn(btn4, "쿠폰");
		paymentOptionPnl.add(btn3);
		paymentOptionPnl.add(btn4);

		pricePnl = new JPanel();
		pricePnl.setBackground(isColor);
		JLabel totalPriceStr = null;
		totalPriceStr = setPriceLbl(totalPriceStr, "총 결제금액  ", Color.BLACK);
		totalPriceLbl = setPriceLbl(totalPriceLbl, String.valueOf(this.totalPrice), Color.RED);
		JLabel wonLbl = null; 
		wonLbl = setPriceLbl(wonLbl, "원", Color.RED);


		// =============================================

		mainPnl = new JPanel();
		mainPnl.setBackground(isColor);
		mainPnl.add(pnl1);
		mainPnl.add(paymentOptionPnl);
		mainPnl.add(pricePnl);
		this.add(mainPnl, "Center");

		btnPnl = new JPanel();
		btnPnl.setBackground(isColor);
		btn1 = setSouthBtn(btn1, "이전");
		btn2 = setSouthBtn(btn2, "다음");
		btnPnl.add(btn1);
		btnPnl.add(btn2);
		this.add(btnPnl, "South");
	}
	
	public void setTotalPrice(int totalPrice) {
		this.totalPrice = totalPrice;
		totalPriceLbl.setText(String.valueOf(this.totalPrice));
	}

	public JLabel setPriceLbl(JLabel lbl, String str, Color color) {
		lbl = new JLabel(str);
		lbl.setFont(new Font("맑은 고딕", Font.BOLD, 35));
		lbl.setForeground(color);
		pricePnl.add(lbl);
		return lbl;
	}

	public JButton setOptionBtn(JButton btn, String str) {
		btn = new JButton();
		ImageIcon btnIcon = new ImageIcon("ImageFile/PAY/"+str+".png");
		btn.setIcon(btnIcon);
		btn.setBackground(isColor);
		btn.setBorderPainted(false);
		btn.setFocusPainted(false);
		btn.addActionListener(this);
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
		if (e.getSource() == btn3) { // 멤버십 적립
			if(this.mainFrame.getMemberID() != "") {
				TranslucenceDialog transDialog = new TranslucenceDialog(this.mainFrame);
				new MembershipPointDialog(this.mainFrame, this);
				transDialog.dispose();
			} else {
				JLabel label = new JLabel("회원 가입이 필요합니다.");
				label.setFont(new Font("맑은 고딕", Font.BOLD, 18));
				JOptionPane.showMessageDialog(this, label);
			}
		} else if (e.getSource() == btn4) { // 쿠폰 사용
			TranslucenceDialog transDialog = new TranslucenceDialog(this.mainFrame);
			new CouponDialog(this.mainFrame, this);
			transDialog.dispose();
			
		} else if (e.getSource() == btn1) { // 이전
			//this.payMainPanel.changePanel(1); // 패널1로
		} else if (e.getSource() == btn2) { // 다음
			this.payMainPanel.changePanel(3); // 패널3으로
			this.payMainPanel.updateTotal();
		}
	}
}