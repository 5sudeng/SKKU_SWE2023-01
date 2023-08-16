import javax.swing.JLabel;

public class MotionLabel extends JLabel implements Runnable {

	Thread myThread;
	int nMacaron, nRandom, nLastEat; // nRandom: 현재 turn에서 computer가 먹을 마카롱의 수

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

	public void setnMacaron(int num) {nMacaron = num;} // PrimaryPanel의 nMacaron 가져오기

	public void setnRandom(int num) {nRandom = num;} // PrimaryPanel의 nRandom 가져오기

	public void setnLastEat(int num) {nLastEat = num;} // PrimaryPanel의 nLastEat 가져오기

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
		try {myThread.sleep(500*nLastEat);} // user가 마카롱을 먹는 시간 동안 멈춤
		catch (Exception e) {}
		
		for (int i = 1; i < 4; i++) { // 3번 깜빡인 후에, computer가 고른 수 보여줌
			setVisible(true);
			setText("*".repeat(i));
			try {myThread.sleep(500);} // 0.5초 동안 멈춤
			catch (Exception e) {}
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
