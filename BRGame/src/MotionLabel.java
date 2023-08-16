import javax.swing.JLabel;

public class MotionLabel extends JLabel implements Runnable {

	Thread myThread;
	int nMacaron, nRandom, nLastEat;
	boolean bButtonClicked;

	public MotionLabel() {
		myThread = new Thread(this);
	}

	public void start() {
		if (myThread != null)
			myThread.start();
	}

	public void stop() {
		myThread.stop();
	}

	public void setnMacaron(int num) {
		nMacaron = num;
	}

	public void setnRandom(int num) {
		nRandom = num;
	}

	public void isButtonClicked(boolean b) {
		bButtonClicked = b;
	}
	
	public void setnLastEat(int num) {
		nLastEat = num;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
		try {
			myThread.sleep(500*nLastEat); // 0.2초 동안 멈춤
		} catch (Exception e) {
		}
		for (int i = 1; i < 4; i++) {
			setVisible(true);
			setText("*".repeat(i));
			try {
				myThread.sleep(500); // 0.2초 동안 멈춤
			} catch (Exception e) {
			}

		}
		if (nRandom == 1) {
			setText("" + nMacaron);
		} else if (nRandom == 2) {
			setText((nMacaron - 1) + ", " + nMacaron);
		} else if (nRandom == 3) {
			setText((nMacaron - 2) + ", " + (nMacaron - 1) + ", " + nMacaron);
		}

		 

	}

}
