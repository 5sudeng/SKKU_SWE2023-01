import java.util.Scanner;

public class ThreeSixNineGame {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int nNum;

		System.out.print("Input a number between 1 and 99 >> ");
		nNum = scan.nextInt();

		if ((nNum / 10 == 3) || (nNum / 10 == 6) || (nNum / 10 == 9)) {
			System.out.print("Clap");
		} // 십의 자리 판별 (10으로 나눈 몫의 정수 부

		if ((nNum % 10 == 3) || (nNum % 10 == 6) || (nNum % 10 == 9)) {
			System.out.print("Clap");
		} // 일의 자리 판별

		scan.close();
	} // main method

} // ThreeSixNineGame class
