package solution_04;


public class Program {
	public static void main(String[] args) {
		
		FinalExam finalExam = new FinalExam();
		finalExam.applyForFinalExam(new Student("345678", "Rosie", 1989));
		finalExam.applyForFinalExam(new Student("123456", "Paula", 1990));
		finalExam.applyForFinalExam(new Student("234567", "Walter", 1991));
		finalExam.applyForFinalExam(new Student("345678", "Brenda", 1992));
		finalExam.applyForFinalExam(new Student("345678", "Chuck", 1993));
		finalExam.applyForFinalExam(new Student("345678", "Dan", 1994));
		
		// The following statements should print a line for every student which is eligible for the final exam like this:
		// Accepted student: Rosie (345678)
		finalExam.printAcceptedApplicants();
	}
}
