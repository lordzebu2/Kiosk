package Menu;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.font.TextAttribute;
import java.util.Map;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

public class MenuTitlePanel extends JPanel implements MouseListener {
	private static final int MENUTITLE_ARRAY = 6;
	private JLabel[] menuTitleLabel;
	private MenuListPanel menuListPanel;
	private MenuMainPanel menuMainPanel;
		
	public MenuTitlePanel(MenuMainPanel menuMainPanel, MenuListPanel menuListPanel) {
		this.menuMainPanel = menuMainPanel;
		this.menuListPanel = menuListPanel;
		this.setLayout(new FlowLayout(FlowLayout.LEFT, 14, 16));
		this.setBackground(Color.WHITE);
		
		// 생성 - 초기화 작업
		menuTitleLabel = new JLabel[MENUTITLE_ARRAY];
		for(int i = 0; i < MENUTITLE_ARRAY; i++) {
			switch(i) {
				case 0 : menuTitleLabel[i] = new JLabel("프리미엄"); break;
				case 1 : menuTitleLabel[i] = new JLabel("와퍼"); break;
				case 2 : menuTitleLabel[i] = new JLabel("주니어&버거"); break;
				case 3 : menuTitleLabel[i] = new JLabel("치킨&치킨버거"); break;
				case 4 : menuTitleLabel[i] = new JLabel("사이드"); break;
				case 5 : menuTitleLabel[i] = new JLabel("음료&디저트"); break;
				default : break;
			}
			menuTitleLabel[i].setFont(new Font("맑은 고딕", Font.BOLD, 16));	
			menuTitleLabel[i].addMouseListener(this);			
			//menuTitleLabel[i].setBorder(new LineBorder(Color.RED));	// test
			Font font = menuTitleLabel[i].getFont();
			Map attributes = font.getAttributes();
			attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
			menuTitleLabel[i].setFont(font.deriveFont(attributes));
			this.add(menuTitleLabel[i]);
		}	
		menuTitleLabel[0].setForeground(Color.RED);	// 시작 단어 색
	}
	
	public void setChangeLabel(String menuTitle) {
		menuListPanel.changeMenu(menuTitle);
		for(int i=0; i<MENUTITLE_ARRAY; i++) {
			menuTitleLabel[i].setForeground(Color.BLACK);
		}
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		JLabel lbl = (JLabel)e.getSource();
		this.setChangeLabel(lbl.getText());
		lbl.setForeground(Color.RED);
		this.menuMainPanel.setSPInit();	// 스크롤바 다시 위로
	}
	
	@Override
	public void mouseEntered(MouseEvent e) {}
	@Override
	public void mouseExited(MouseEvent e) {}
	@Override
	public void mousePressed(MouseEvent e) {}
	@Override
	public void mouseReleased(MouseEvent e) {}
}
