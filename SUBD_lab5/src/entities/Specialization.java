package entities;

public class Specialization {
	String[] names = {"Напрвление подготовки", "Кафедра"};		
	private int id;
	private String name;
	private String depName;
	private String newName;	
	private int newDepId;
	
	
	public String getSelectQuery() {
		return "select specializationid, specializationname, departmentname \r\n" + 
				"from specialization, department\r\n" + 
				"where specialization.departmentid = department.departmentid";	
	}
	
	public String getDepartments() {
		return "select departmentname from department";
	}
	
	public String getUpdateQuery(int id) {
		return "update specialization set specializationname = '" + newName + "'," + "departmentid = '" + Integer.toString(newDepId) + "' where specializationid = " + Integer.toString(id);
	}	
	public String getDeleteQuery(int id) {
		return "delete from specialization where specializationid = " + Integer.toString(id);
	}	
	
	public String getAddQuery() {		
		return "insert into Specialization values (" + Integer.toString(newDepId) +  ", nextval('Specialization_seq'),'" + newName + "')";
	}			
	
	public String[] getNames() {
		return names;
	}	
	
	public void setNewName(String newName) {
		this.newName = newName;
	}	
	
	public void setNewDepId(int newDepId) {
		this.newDepId = newDepId;
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
	
	public String getDepName() {
		return depName;
	}
	
	public void setDepName(String spName) {
		this.depName = spName;
	}	
	
	public String[] firstString() {
		return new String[] {"Направление подготовки", "Кафедра"};
	}
}
