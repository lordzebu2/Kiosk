package Menu;

import Main.*;
import AD.*;
import etc.*;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class MenuMainPanel extends JPanel implements ActionListener{
	private static final int LOGIN_PNL = 1;
	private static final int PAY_PNL = 3;
	
	private MainFrame mainFrame;
	private ADPanel adPanel;	// 배너광고 패널
	private MenuListPanel menuListPanel;	
	private MenuTitlePanel menuTitlePanel;	
	private MenuCartPanel menuCartPanel;
	private JPanel pnlS;
	private JButton cancelBtn, payBtn;
	private Color isColor = Color.WHITE;
	private JScrollPane menuSP;
	private boolean menuOrder;

	public MenuMainPanel(MainFrame mainFrame) {
		this.mainFrame = mainFrame;//test
		this.setBackground(isColor);

		adPanel = new ADPanel("행잉");
		menuCartPanel = new MenuCartPanel();
		menuListPanel = new MenuListPanel(mainFrame, menuCartPanel);
		menuTitlePanel = new MenuTitlePanel(this, menuListPanel);

		// 광고 패널
		this.add(adPanel);

		// 메뉴 관련 패널
		JPanel MenuPnl = new JPanel(new BorderLayout());	
		MenuPnl.setBackground(isColor);	
		MenuPnl.setPreferredSize(new Dimension(550, 505));
		// 메뉴 타이틀
		MenuPnl.add(menuTitlePanel, "North");
		// 메뉴 리스트 + 스크롤
		JPanel MenuPnl_sp = new JPanel();
		MenuPnl_sp.setBackground(isColor);
		MenuPnl_sp.add(menuListPanel);
		menuSP = new JScrollPane(MenuPnl_sp, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		menuSP.setBorder(BorderFactory.createEmptyBorder());	// 스크롤 보더 경계선 없애기
		MenuPnl.add(menuSP, "Center");
		this.add(MenuPnl);

		// Cart 패널
		this.add(menuCartPanel);

		// 버튼
		pnlS = new JPanel();
		pnlS.setBackground(isColor);
		cancelBtn = setBtnInit(cancelBtn, "나가기");
		payBtn = setBtnInit(payBtn, "결제하기");
		this.add(pnlS);
	}

	public JButton setBtnInit(JButton btn, String str) {
		btn = new JButton(str);
		btn.setPreferredSize(new Dimension(288,50));
		btn.setFont(new Font("맑은 고딕", Font.BOLD, 20));		// 수정 ()
		btn.addActionListener(this);
		//btn.setBorderPainted(false);
		btn.setFocusPainted(false);
		pnlS.add(btn);
		return btn;
	}

	// 스크롤바 다시 위로 보내기
	public void setSPInit() {
		menuSP.getVerticalScrollBar().setValue(0);
	}

	public void startAD() {
		this.adPanel.startAD();
	}

	public void setMenuOrder() {
		this.menuOrder = true;
	}
	
	public void getPayPanel() {
		this.adPanel.stopAD();
		this.mainFrame.nextPnl(PAY_PNL);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == payBtn) {
			if(this.menuCartPanel.getCartCount()){	// 카트에 상품이 담겨있다면 결제 진행
				TranslucenceDialog tranDialog = new TranslucenceDialog(mainFrame);
				new MenuPackDialog(mainFrame, this); 
				if(menuOrder) 
					new MenuOrderDialog(this.mainFrame, this, this.menuCartPanel);
				tranDialog.dispose();
				menuOrder = false;
			} else { 		
				TranslucenceDialog tranDialog = new TranslucenceDialog(mainFrame);
				JLabel label = new JLabel("메뉴를 선택해주세요.");
				label.setFont(new Font("맑은 고딕", Font.BOLD, 18));
				JOptionPane.showMessageDialog(this, label);
				tranDialog.dispose();
			}
		} else if(e.getSource() == cancelBtn) {
			//this.adPanel.stopAD();
			//this.mainFrame.nextPnl(LOGIN_PNL);
			
			//test
			System.exit(0);
		} 
	}
}
