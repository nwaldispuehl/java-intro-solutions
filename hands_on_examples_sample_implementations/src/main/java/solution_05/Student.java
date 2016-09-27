package solution_05;

public class Student extends Person {

	private String studentNumber;

	public Student(String studentNumber, String name, int yearOfBirth) {
		super(name, yearOfBirth);
		this.studentNumber = studentNumber;
	}

	public String getStudentNumber() {
		return studentNumber;
	}
}
