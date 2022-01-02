package etc;

import Main.*;

import java.awt.Color;

import javax.swing.JDialog;

public class TranslucenceDialog extends JDialog {
	MainFrame mainFrame;
	public TranslucenceDialog(MainFrame mainFrame) {
		super(mainFrame, "", false);	// true => 다이얼로그창이 켜지면 프레임 클릭 안됨
		this.mainFrame = mainFrame;
		this.setUndecorated(true); 	// 타이틀바 삭제
		this.setSize(584, 984);		// mainFrame에 규격된 사이즈
		this.setLocationRelativeTo(this.mainFrame);	// 중앙 배치. size를 설정한후에 설정해야한다.
		this.setBackground(new Color(0,0,0,200));
		this.setVisible(true);		
	}
}
