package entities;

public class Faculty {	
	
	String[] names = {"Факультет"};	

	private int id;
	private String name;		
	private String newName;		
	
	public String getSelectQuery() {
		return "select * from faculty";
	}	
	public String getUpdateQuery(int id) {
		return "update faculty set facultyname = '" + newName + "' where facultyid = " + Integer.toString(id);
	}	
	public String getDeleteQuery(int id) {
		return "delete from faculty where facultyid = " + Integer.toString(id);
	}	
	
	public String getAddQuery() {
		return "insert into Faculty values (nextval('Faculty_seq'),'" + newName + "')";
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
		return new String[] {"Факультет"};
	}
}
