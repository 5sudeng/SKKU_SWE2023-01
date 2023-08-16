
public class StringMutation {
	// Prints a string and various mutations of it
	public static void main(String[] args) {
		String phrase = "Change is inevitable";
		String mut1, mut2, mut3, mut4;
		
		System.out.println("Original string: \""+phrase+"\"");
		System.out.println("Length of string: "+phrase.length());
		
		mut1 = phrase.concat(", except from bending machines.");
		mut2 = mut1.toUpperCase();
		mut3 = mut2.replace('E','X');
		mut4 = mut3.substring(3, 30);
		
		System.out.println("Mutation #1: "+mut1);
		System.out.println("Mutation #2: "+mut2);
		System.out.println("Mutation #3: "+mut3);
		System.out.println("Mutation #4: "+mut4);
		
		System.out.println("Mutation length: "+mut4.length());
		

	} // main()

} // StringMutation class
