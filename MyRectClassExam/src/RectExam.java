
public class RectExam {
	public static void main(String[] args) {
		MyRect rect1, rect2;

		rect1 = new MyRect();
		rect2 = new MyRect(30, 50);

		System.out.println(rect1);
		System.out.println(rect2);

		rect1.setSize(10.2, 23.5);

		System.out.println(rect1);
		System.out.println(rect2);

		System.out.println("r1 area : " + rect1.calcArea());
		System.out.println("r2 area : " + rect2.calcArea());

		System.out.println("r1 perimeter : " + rect1.calcPerimeter());
		System.out.println("r2 perimeter : " + rect2.calcPerimeter());

		System.out.println(rect2.compareRect(10, 30));
		System.out.println(rect1.compareRect(rect2));

	}
}
