
public class DieExam {

	public static void main(String[] args) {
		Die die1, die2; // declaration

		die1 = new Die(); // creating
		die2 = new Die(50);

		System.out.println("DIE 1 >> " + die1.getFaceValue());
		System.out.println("DIE 2 >> " + die2.getFaceValue());

		die1.roll(); // using
		die2.roll();

		die1.setFaceValue(100);

		System.out.println("DIE 1 >> " + die1.getFaceValue());
		System.out.println("DIE 2 >> " + die2.getFaceValue());

		System.out.println(die2);

	} // main()

} // DieExam class (driver class to test Die.java)
