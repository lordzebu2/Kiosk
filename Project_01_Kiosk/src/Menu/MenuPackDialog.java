package Menu;

import Main.*;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Menu;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MenuPackDialog extends JDialog implements ActionListener, MouseListener {
	private static final int IMAGEICON_COUNT = 4;
	private static final int STORE = 0;
	private static final int STORE_CHECK = 1;
	private static final int PACK = 2;
	private static final int PACK_CHECK = 3;
	
	private MainFrame mainFrame;
	private JButton cancelBtn, okBtn;
	private JPanel lblPnl, btnPnl;
	private JLabel storeLbl, packLbl;
	private ImageIcon[] imageIcon;
	private Color isColor = Color.WHITE;	
	private MenuMainPanel menuMainPanel;
	
	public MenuPackDialog(MainFrame mainFrame, MenuMainPanel menuMainPanel) {
		super(mainFrame, "", true);	// true => 다이얼로그창이 켜지면 프레임 클릭 안됨
		this.mainFrame = mainFrame;
		this.menuMainPanel = menuMainPanel;
		this.setLayout(new BorderLayout());
		this.setSize(550, 450);
	
		JPanel textPnl = new JPanel(new FlowLayout(FlowLayout.CENTER));
		textPnl.setBackground(isColor);
		JLabel textLbl = new JLabel("선택해주세요");
		textLbl.setFont(new Font("맑은 고딕", Font.BOLD, 30));
		textLbl.setBackground(isColor);
		textPnl.add(textLbl);
		this.add(textPnl,"North");

		lblPnl = new JPanel(new FlowLayout(FlowLayout.CENTER, 80, 50));
		lblPnl.setBackground(isColor);
		this.setPackImageIcon();
		storeLbl = setPackLbl(STORE);
		packLbl = setPackLbl(PACK);
		this.add(lblPnl, "Center");

		btnPnl = new JPanel();
		btnPnl.setBackground(isColor);
		okBtn = this.setBtn(okBtn, "확인");
		this.add(btnPnl, "South");
	
		this.setUndecorated(true); 	// 타이틀바 삭제
		this.setBackground(isColor);
		this.setLocationRelativeTo(this.mainFrame);	// 중앙 배치. size를 설정한후에 설정해야한다.
		this.setVisible(true);	
	}

	public JLabel setPackLbl(int sel) {
		JLabel lbl = new JLabel();
		if(sel==STORE) lbl.setIcon(imageIcon[STORE_CHECK]);
		else if(sel==PACK) lbl.setIcon(imageIcon[PACK]);
		lbl.addMouseListener(this);
		lblPnl.add(lbl);
		return lbl;
	}

	public JButton setBtn(JButton btn, String title) {
		btn = new JButton(title);
		btn.setPreferredSize(new Dimension(300, 50));
		btn.setFont(new Font("맑은 고딕", Font.BOLD, 20));		// 수정 ()
		btn.addActionListener(this);
		btn.setFocusPainted(false);
		btnPnl.add(btn);
		return btn;
	}

	public void setPackImageIcon() {
		imageIcon = new ImageIcon[IMAGEICON_COUNT];
		for(int i=0; i<IMAGEICON_COUNT; i++) {
			imageIcon[i] = new ImageIcon("ImageFile/etc/placeCheck"+(i+1)+".png");
		}
	}

	public void changePackLblImage(int sel) {
		if(sel == STORE) {
			storeLbl.setIcon(imageIcon[STORE_CHECK]);
			packLbl.setIcon(imageIcon[PACK]);
		} else if(sel == PACK) {
			packLbl.setIcon(imageIcon[PACK_CHECK]);
			storeLbl.setIcon(imageIcon[STORE]);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		this.dispose();	
		this.menuMainPanel.setMenuOrder();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if(e.getSource() == storeLbl) {
			changePackLblImage(STORE);
		}else if(e.getSource() == packLbl) {
			changePackLblImage(PACK);
		}
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
