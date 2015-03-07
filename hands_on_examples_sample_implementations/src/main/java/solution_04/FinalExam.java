package solution_04;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class FinalExam {

	private int currentYear = new GregorianCalendar().get(Calendar.YEAR);
	
	private ArrayList<Student> applicants = new ArrayList<>();
	
	/**
	 * A student is eligible for the final exam if and only if:
	 * He or she is older than 23.
	 */
	public boolean isEligibleForFinalExam(Student student) {
		return 23 < student.getAgeIn(currentYear);
	}
	
	public void applyForFinalExam(Student student) {
		applicants.add(student);
		System.out.println("Added to the final exam applicants list: " + student.getName());
	}

	public void printAcceptedApplicants() {
		for (Student student : applicants) {
			if (isEligibleForFinalExam(student)) {
				System.out.println("Accepted student: " + student.getName() + " (" + student.getStudentNumber() + ")");
			}
		}
	}
	
	
}
