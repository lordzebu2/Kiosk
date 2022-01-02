package AD;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ADPanel extends JPanel {
	private static final int BANNER_ARRAY = 4;
	private File imgFile;
	private Image img;
	private ImageIcon icon;
	private JLabel[] bannerLbl;
	private String bannerName;

	private ADThread adThread;
	private Thread thread;
	
	public ADPanel(String bannerName) {
		this.setLayout(new GridLayout());
		this.setBackground(Color.WHITE);
		this.bannerLbl = new JLabel[BANNER_ARRAY];
		this.bannerName = bannerName;
		this.setBannerLblInit();
		
		adThread = new ADThread(this);	
		thread = new Thread(adThread);
	}
	
	// 배너 레이블에 이미지 담아놓기
	public void setBannerLblInit() {
		for(int i = 0; i < BANNER_ARRAY; i++) {
			try {
				this.loadImageFile("ImageFile/AD/"+bannerName+"배너광고"+(i+1)+".png");
			} catch (IOException e) {
				e.printStackTrace();
			}
			this.bannerLbl[i] = new JLabel();
			this.bannerLbl[i].setIcon(icon);
		}
	}	
	
	public void loadImageFile(String fileName) throws IOException {
		this.imgFile = new File(fileName);
		this.img = ImageIO.read(imgFile);
		this.icon = new ImageIcon(img);
	}
	
	public void setBannerLbl(int count) {
		this.add(bannerLbl[count]);
	}		
	
	// 광고 배너 이미지 변환 - Thread 시작
	public void startAD() {
		// 이미 시작되어있는 쓰레드를 다시 시작할때 예외가 발생하므로 쓰레드의 상태값을 받아와서 예외처리해준다.
		if(thread.getState() == Thread.State.NEW) {	// 스레드 객체가 생성되면 start
			thread.start();
		} else if(thread.getState() == Thread.State.TERMINATED) {	// 쓰레드가 종료상태라면 쓰레드를 새로 생성하고 start
			thread = new Thread(adThread);
			thread.start();
		}
	}	
	// Thread 종료
	public void stopAD() {
		thread.interrupt();
	}
}