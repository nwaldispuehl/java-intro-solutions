package solution_02;

public class Program {
	public static void main(String[] args) {
		
		Person ronald = new Person("Ronald", 1995);
		Person paul = new Person("Paul", 1992);
		
		// This statement should print:
		// 'Paul is older than Ronald'.
		System.out.println(ronald.compareAgeWith(paul));
	}
}
