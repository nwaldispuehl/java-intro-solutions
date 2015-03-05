package solution_02;

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
			return otherPerson.getName() + " is older than " + name;
		}
		else if (otherPerson.getYearOfBirth() < yearOfBirth) {
			return otherPerson.getName() + " is younger than " + name;
		}
		else {
			return "Both are of more or less same age.";
		}
	}
}
