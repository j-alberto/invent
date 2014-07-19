package generic;

public class PersonEntity implements CoreBean{

	private String firstName,lastName;
	
	public PersonEntity() {
		// TODO Auto-generated constructor stub
	}
	public PersonEntity(String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
}
