package solution_03;

public class Person {

	private String name;
	private int yearOfBirth;

	public Person(String name, int yearOfBirth) {
		this.name = name;
		this.yearOfBirth = yearOfBirth;
	}

	public int getAgeIn(int year) {
		return year - yearOfBirth;
	}
	
	public int getYearOfBirth() {
		return yearOfBirth;
	}
	
	public String getName() {
		return name;
	}
	
	public String compareAgeWith(Person otherPerson) {
		if (yearOfBirth < otherPerson.getYearOfBirth()) {
			return name + " is older than " + otherPerson.getName();
		}
		else if (otherPerson.getYearOfBirth() < yearOfBirth) {
			return name + " is younger than " + otherPerson.getName();
		}
		else {
			return "Both are of more or less same age.";
		}
	}
}
