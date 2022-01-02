package Pay;

import Main.*;
import AD.*;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JPanel;

public class PayMainPanel extends JPanel {
	ServiceOptionPanel serviceOptionPnl; // 결제창 2번
	PaymentOptionPanel paymentOptionPnl; // 결제창 3번
	Panel04 pnl04; // 결제창 4번 - 현금
	Panel05 pnl05; // 결제창 4번 - 카드
	Panel06 pnl06; // 멤버십 적립 - 회원
	int discount; // 적용한 할인쿠폰 만큼 차감
	int point; // 사용한 적립금만큼 차감

	private ADPanel adPanel;
	private MainFrame mainFrame;
	
	int totalPrice;
	
	public PayMainPanel(MainFrame mainFrame, int totalPrice) {
		this.setLayout(new BorderLayout());
		this.setBackground(Color.WHITE);
		
		this.mainFrame = mainFrame;
		this.totalPrice = totalPrice;
		
		adPanel = new ADPanel("행잉");
		this.add(adPanel, "North");
		
		serviceOptionPnl = new ServiceOptionPanel(this.mainFrame, this, totalPrice);
		paymentOptionPnl = new PaymentOptionPanel(this, totalPrice);
		this.add(serviceOptionPnl, "Center");
		

		pnl04 = new Panel04(this);
		pnl05 = new Panel05(this);
		pnl06 = new Panel06(this);

	}
	
	public void startAD() {
		this.adPanel.startAD();
	}
	
	public void changePanel(int pnl) {
		removeAll();
		this.add(adPanel, "North");
		if (pnl == 2) {
			add(serviceOptionPnl, "Center");
		} else if (pnl == 3) {
			add(paymentOptionPnl, "Center");
		} else if (pnl == 4) {
			add(pnl04);
		} else if (pnl == 5) {
			add(pnl05);
		} else if (pnl == 6) {
			add(pnl06);
		} 

		this.revalidate();
		this.repaint();
	}

	public void updateTotal() {
		if(totalPrice > 0)
			totalPrice = totalPrice - discount - point;
		else
			totalPrice = 0;
	}
}
