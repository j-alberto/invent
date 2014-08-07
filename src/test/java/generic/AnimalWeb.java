package generic;

public class AnimalWeb implements WebBean<AnimalWeb,PersonEntity>{

	private String firstName,lastName;
	
	public AnimalWeb() {
		// TODO Auto-generated constructor stub
	}
	public AnimalWeb(String firstName, String lastName) {
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
	public PersonEntity asCoreBean(AnimalWeb bean) {
		return null;
	}
	@Override
	public AnimalWeb asWebBean(PersonEntity bean) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String toString(){
		return String.format("AnimalWeb name: %s,  last name:%s",this.firstName,this.lastName);
	}


}
