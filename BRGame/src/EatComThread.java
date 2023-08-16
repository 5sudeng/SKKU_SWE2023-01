import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class EatComThread implements Runnable{
	Thread myThread;
	ImageIcon[] icons;
	JLabel[] lblImages;
	int nMacaron, nEat, nLastEat;
	boolean bFinish;
	
	public EatComThread() {
		myThread = new Thread(this);
		bFinish = false;
	}
	
	public void start() {
		if (myThread != null)
			myThread.start();
	}
	
	public void join() {
		try {
			myThread.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void stop() {
		myThread.stop();
	}
	
	public void setIcons(ImageIcon[] i) {icons = i;}
	
	public void setImages(JLabel[] img) {lblImages = img;}
	
	public void setnMacaron(int num) {nMacaron=num;}
	
	public void setnEat(int num) {nEat = num;}
	
	public void setnLastEat(int num) {nLastEat = num;}
	
	

	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			myThread.sleep(500*nLastEat+1500); // 0.2초 동안 멈춤
		} catch (Exception e) {
		}
		for (int i = nMacaron-nEat; i<nMacaron; i++) {
			
			icons[i] = new ImageIcon("images/eaten.png");
			lblImages[i].setIcon(icons[i]);
		
			try {myThread.sleep(500);} // 0.2초 동안 멈춤} 
			catch (Exception e) {}
		}
		
		}	

}
