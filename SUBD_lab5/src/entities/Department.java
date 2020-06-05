package entities;

public class Department {	
	
	String[] names = {"Кафедра", "Факультет"};		
	private int id;
	private String name;
	private String facultyName;
	private String newName;	
	private int newFacultyId;
	
	
	public String getSelectQuery() {
		return "select departmentid, departmentname, facultyname \r\n" + 
				"from department, faculty\r\n" + 
				"where department.facultyid = faculty.facultyid";	
	}
	public String getUpdateQuery(int id) {
		return "update department set departmentname = '" + newName + "'," + "facultyid = '" + Integer.toString(newFacultyId) + "' where departmentid = " + Integer.toString(id);
	}	
	public String getDeleteQuery(int id) {
		return "delete from department where departmentid = " + Integer.toString(id);
	}	
	
	public String getFaculties() {
		return "select facultyname from faculty";
	}
	
	public String getAddQuery() {
		System.out.println("dep " + newFacultyId);
		return "insert into Department values (" + Integer.toString(newFacultyId) +  ", nextval('Department_seq'),'" + newName + "')";
	}			
	
	public String[] getNames() {
		return names;
	}	
	
	public void setNewName(String newName) {
		this.newName = newName;
	}	
	
	public void setNewFacultyId(int newFacultyId) {
		this.newFacultyId = newFacultyId;
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
	
	public String getFacultyName() {
		return facultyName;
	}
	
	public void setFacultyName(String facultyName) {
		this.facultyName = facultyName;
	}
	
	public String[] firstString() {
		return new String[] {"Кафедра",  "Факультет"};
	}
}
