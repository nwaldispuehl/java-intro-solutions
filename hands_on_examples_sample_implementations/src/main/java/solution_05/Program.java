package solution_05;


public class Program {
	public static void main(String[] args) {
		
		FinalExam finalExam = new FinalExam();
		finalExam.applyForFinalExam(new Student("678901", "Rosie", 1993));
		finalExam.applyForFinalExam(new Student("123456", "Paula", 1994));
		finalExam.applyForFinalExam(new Student("234567", "Walter", 1995));
		finalExam.applyForFinalExam(new Student("345678", "Brenda", 1996));
		finalExam.applyForFinalExam(new Student("456789", "Chuck", 1997));
		finalExam.applyForFinalExam(new Student("567890", "Dan", 1998));
		
		// The following statements should print a line for every student which is eligible for the final exam like this:
		// Accepted student: Rosie (678901)
		finalExam.printAcceptedApplicants();
	}
}
