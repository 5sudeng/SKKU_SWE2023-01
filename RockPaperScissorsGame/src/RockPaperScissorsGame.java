import java.util.Scanner;

public class RockPaperScissorsGame {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		String smith;
		String jane;
		String who;

		System.out.println("Rock-paper-scissors game");
		System.out.print("Smith >> ");
		smith = scan.next();

		System.out.print("Jane >> ");
		jane = scan.next();

		scan.close();

		if (smith.equals("Scissors")) {
			if (jane.equals("Paper")) {
				who = "s";
			} else if (jane.equals("Rock")) {
				who = "j";
			} else {
				who = "n";
			}
		} else if (smith.equals("Rock")) {
			if (jane.equals("Scissors")) {
				who = "s";
			} else if (jane.equals("Paper")) {
				who = "j";
			} else {
				who = "n";
			}
		} else if (smith.equals("Paper")) {
			if (jane.equals("Rock")) {
				who = "s";
			} else if (jane.equals("Scissors")) {
				who = "j";
			} else {
				who = "n";
			}
		} else {
			who = "None";
		} // if to compare

		if (who.equals("s")) {
			System.out.println("Smith is win!!");
		} else if (who.equals("j")) {
			System.out.println("Jane is win!!");
		} else if (who.equals("n")) {
			System.out.println("DRAW !!");
		} else {
			System.out.println("errors occured");
		} // if for print

	} // main method

} // RockPaperScissors class
