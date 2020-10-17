package solution_03;

public class Program {
	public static void main(String[] args) {
		
		Person ronald = new Person("Ronald", 1999);
		Person paul = new Person("Paul", 1996);
		
		// This statement should print:
		// 'Ronald is younger than Paul'.
		System.out.println(ronald.compareAgeWith(paul));
	}
}
