
public class DieTest {

	public static void main(String[] args) {
		Die die = new Die();
		
		System.out.println("DIE >> "+ die.m_nFaceValue);
		
		die.roll();
		
		System.out.println("DIE >> "+ die.m_nFaceValue);
		
		die.m_nFaceValue = 100;
		
		System.out.println("DIE >> "+ die.m_nFaceValue);
		
		

	} // main()

} // DieTest class
