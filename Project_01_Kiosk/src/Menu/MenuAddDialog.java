package Menu;

import Main.*;
import etc.*;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class MenuAddDialog extends JDialog implements ActionListener {
	private MainFrame mainFrame;
	private MenuListPanel menuListPanel;
	private JButton cancelBtn, okBtn;
	private JPanel  btnPnl;
	private Color isColor = Color.WHITE;	
	private TranslucenceDialog tranDialog;

	public MenuAddDialog(MainFrame mainFrame, TranslucenceDialog tranDialog, MenuLabelManager menuLabelManager, MenuListPanel menuListPanel) {
		super(mainFrame, "", true);	// true => 다이얼로그창이 켜지면 프레임 클릭 안됨
		this.mainFrame = mainFrame;
		this.setLayout(new BorderLayout());
		this.setSize(400, 300);
		
		this.menuListPanel = menuListPanel;

		JPanel menuIconPnl = new JPanel(new BorderLayout());
		menuIconPnl.setBackground(isColor);
		JLabel menuIcon = new JLabel();
		menuIcon.setIcon(menuLabelManager.getMenuIcon());
		menuIcon.setHorizontalAlignment(JLabel.CENTER);
		menuIconPnl.add(menuIcon);
		this.add(menuIconPnl, "Center");

		btnPnl = new JPanel();
		btnPnl.setBackground(isColor);
		cancelBtn = this.setBtn(cancelBtn, "취소");
		okBtn = this.setBtn(okBtn, "담기");
		this.add(btnPnl, "South");

		this.tranDialog = tranDialog;
		this.setUndecorated(true); 	// 타이틀바 삭제
		this.setBackground(isColor);
		this.setLocationRelativeTo(this.mainFrame);	// 중앙 배치. size를 설정한후에 설정해야한다.
		this.setVisible(true);	
	}

	public JButton setBtn(JButton btn, String title) {
		btn = new JButton(title);
		btn.setPreferredSize(new Dimension(150, 50));
		btn.setFont(new Font("맑은 고딕", Font.BOLD, 20));		// 수정 ()
		btn.addActionListener(this);
		btn.setFocusPainted(false);
		btnPnl.add(btn);
		return btn;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == okBtn) {
			this.menuListPanel.selectPut(true);
			this.dispose();
		} else if(e.getSource() == cancelBtn) {
			this.menuListPanel.selectPut(false);
			this.dispose();			
		}
		this.tranDialog.dispose();
	}
}
