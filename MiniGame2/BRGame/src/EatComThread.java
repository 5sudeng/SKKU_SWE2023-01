import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class EatComThread implements Runnable{
	Thread myThread;
	ImageIcon[] icons;
	JLabel[] lblImages;
	int nMacaron, nEat, nLastEat; // nLastEat: 사용자 turn에서 마지막으로 먹은 마카롱 수
	
	public EatComThread() {
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
	
	public void setnLastEat(int num) {nLastEat = num;} // PrimaryPanel의 nLastEat 가져오기
	
	

	@Override
	public void run() {
		try {
			myThread.sleep(500*nLastEat+1500); // (user가 마카롱을 먹는 시간+ lblComsays의 내용이 바뀌는 시간) 동안 멈춤
		} catch (Exception e) {
		}
		for (int i = nMacaron-nEat; i<nMacaron; i++) { // nEat 만큼 macaron 이미지 파일은 eaten으로 바꾸기
			
			icons[i] = new ImageIcon("images/eaten.png");
			lblImages[i].setIcon(icons[i]);
		
			try {myThread.sleep(500);} // 한 라벨의 이미지를 바꿀 때마다 0.5초의 시간 간격을 둠
			catch (Exception e) {}
		}
		
		}	

}
