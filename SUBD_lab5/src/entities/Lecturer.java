package entities;

public class Lecturer {
	String[] names = {"Преподаватель"};	

	private int id;
	private String name;		
	private String newName;	
	
	public String getSelectQuery() {
		return "select * from lecturer";
	}
	
	public String getAddQuery() {
		return "insert into Lecturer values (nextval('Lecturer_seq'),'" + newName + "')";
	}	
	
	public String getUpdateQuery(int id) {
		return "update lecturer set name = '" + newName + "' where lecturerid = " + Integer.toString(id);
	}	
	public String getDeleteQuery(int id) {
		return "delete from lecturer where lecturerid = " + Integer.toString(id);
	}	
	
	public String[] getNames() {
		return names;
	}
	
	public void setNewName(String newName) {
		this.newName = newName;
	}	
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}	
	
	public String[] firstString() {
		return new String[] {"Преподаватель"};
	}
}
