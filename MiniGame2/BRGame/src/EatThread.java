import javax.swing.*;

public class EatThread implements Runnable{
	
	Thread myThread;
	ImageIcon[] icons;
	JLabel[] lblImages;
	int nMacaron, nEat; // nMacaron: 현재까지 먹은 마카롱 수, nEat: 이번 턴에서 먹는 마카롱 수
	
	public EatThread() {
		myThread = new Thread(this);
	}
	
	public void start() {
		if (myThread != null)
			myThread.start();
	}
	
	
	public void stop() {
		myThread.stop();
	}
	
	public void setIcons(ImageIcon[] i) {icons = i;} // PrimaryPanel의 ImageIcon array 가져오기
	
	public void setImages(JLabel[] img) {lblImages = img;} // PrimaryPanel의 JLabel array 가져오기
	
	public void setnMacaron(int num) {nMacaron=num;} // PrimaryPanel의 nMacaron 가져오기
	
	public void setnEat(int num) {nEat = num;} // PrimaryPanel의 nEat 가져오기
	


	@Override
	public void run() {
		for (int i = nMacaron-nEat; i<nMacaron; i++) { // nEat 만큼 macaron 이미지 파일을 eaten으로 바꾸기
			
			icons[i] = new ImageIcon("images/eaten.png");
			lblImages[i].setIcon(icons[i]);
		
			try {myThread.sleep(500);} // 한 라벨의 이미지를 바꿀 때마다 0.5초의 시간 간격을 둠
			catch (Exception e) {}
		}
		
		}	
			
	}
	
