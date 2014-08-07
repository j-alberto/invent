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
		return new PersonEntity(bean.getFirstName(), bean.getLastName());
	}
	@Override
	public PersonWeb asWebBean(PersonEntity bean) {
		return new PersonWeb(bean.getFirstName(), bean.getLastName());
	}

	@Override
	public String toString(){
		return String.format("PersonWeb name: %s,  last name:%s",this.firstName,this.lastName);
	}

}
