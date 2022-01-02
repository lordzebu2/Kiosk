package AD;

public class ADThread implements Runnable {
	private static final int BANNER_ARRAY = 4;
	private int lblCount;
	private ADPanel adPanel;
	
	public ADThread(ADPanel adPanel) {
		this.adPanel = adPanel;
	}

	@Override
	public void run() {
		// Thread 실행에 대한 interrupt가 발생할때까지 실행
		while(!Thread.currentThread().isInterrupted()) {
			adPanel.removeAll();
			adPanel.setBannerLbl(lblCount);
			adPanel.revalidate();
			adPanel.repaint();
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				return;
			}					
			lblCount++;
			if(lblCount >= BANNER_ARRAY)
				lblCount = 0;	
		}
	}
}
