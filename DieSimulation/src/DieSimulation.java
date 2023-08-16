import java.util.Random;

public class DieSimulation {

	public static void main(String[] args) {
		int nFaceValue;
		int nSum = 0, nCount = 0;

		Random generator = new Random();

		for (int i = 0; i < 100000; i++, nSum = 0) {
			while (nSum < 100) {
				nFaceValue = generator.nextInt(6) + 1; // 1~6
				nSum += nFaceValue;
				nCount++;
			} // while nSum < 100
		} // for 100000

		System.out.println("COUNT >> " + (nCount / 100000.0));

	} // main

} // DieSimulation class