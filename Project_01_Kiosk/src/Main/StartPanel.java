package Main;

import AD.*;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class StartPanel extends JPanel implements MouseListener{
	private static final int LOGIN_PNL = 1;
	private ADPanel adPanel;
	private JLabel touchLbl;
	private ImageIcon icon;
	private MainFrame mainFrame;
	
	public StartPanel(MainFrame mainFrame) {
		this.mainFrame = mainFrame;
		this.setLayout(new BorderLayout());
		this.setBackground(Color.WHITE);
		this.addMouseListener(this);
		
		adPanel = new ADPanel("족자");
		this.add(adPanel, "Center");
	
		touchLbl = new JLabel();
		icon = new ImageIcon("ImageFile/etc/화면터치.png");
		touchLbl.setIcon(icon);
		this.add(touchLbl,"South");
	}
	
	// 광고 전환
	public void startAD() {
		this.adPanel.startAD();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// 광고 Thread 종료
		this.adPanel.stopAD();	
		// frame 의 패널 전환
		this.mainFrame.nextPnl(LOGIN_PNL);
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
