package generic;

public class PersonWeb implements WebBean<PersonWeb, PersonEntity>{

	private String firstName,lastName;
	
	public PersonWeb() {
		// TODO Auto-generated constructor stub
	}
	public PersonWeb(String firstName, String lastName) {
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
	@Override
	public PersonEntity asCoreBean(PersonWeb bean) {
		return null;
	}
	@Override
	public PersonWeb asWebBean(PersonEntity bean) {
		// TODO Auto-generated method stub
		return null;
	}
	
	static{
		
	}


}
