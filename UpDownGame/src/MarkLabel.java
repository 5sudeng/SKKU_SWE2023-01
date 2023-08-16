import java.awt.Color;

import javax.swing.JLabel;

public class MarkLabel extends JLabel implements Runnable{
	
	Thread myThread; 
	int nFinish;
	
	public MarkLabel() {
		myThread = new Thread(this); //연결.. 충격 ㄷ ㄷ this -> 나 자신 - MarkLabel
		nFinish = 0;
		
	}
	
	public void start() { // thread를 start
		if (myThread != null) {
			myThread.start();
		}
	}
	
	public void stop() {
		myThread.stop();
	}
	
	public void setFinish(int num) {nFinish = num;}
	
	@Override
	public void run() {
		for (int i=1; i<=nFinish;i++) {
			setText(""+i);
			try {
				myThread.sleep(20);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} // for
		//setForeground(Color.red);
		for (int i=0;i<10;i++) {
			int r, g, b;
			r = (int)(Math.random()*256);
			g = (int)(Math.random()*256);
			b = (int)(Math.random()*256);
			setForeground(new Color(r, g, b));
			setVisible(false);
			try {
				myThread.sleep(70);
			} catch (Exception e) {
				e.printStackTrace();
			}
			setVisible(true);
			try {
				myThread.sleep(70);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	} // run
	
} // MarkLabel class
