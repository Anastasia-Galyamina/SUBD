package entities;

public class Student {
	String[] names = {"Студент", "Группа"};		
	private int id;
	private String name;
	private String groupName;
	private String newName;	
	private int newGroupId;
	
	
	public String getSelectQuery() {
		return "select studentid, studentname, groupname \r\n" + 
				"from student, grouptable \r\n" + 
				"where student.groupid = grouptable.groupid";	
	}
	
	public String getGroups() {
		return "select groupname from grouptable";
	}
	
	public String getUpdateQuery(int id) {
		return "update student set studentname = '" + newName + "'," + "groupid = '" + Integer.toString(newGroupId) + "' where Studentid = " + Integer.toString(id);
	}	
	
	public String getDeleteQuery(int id) {
		return "delete from student where studentid = " + Integer.toString(id);
	}	
	
	public String getAddQuery() {	
		return "insert into Student values (nextval('Student_seq')," + Integer.toString(newGroupId) +  ",'" + newName + "')";
	}			
	
	public String[] getNames() {
		return names;
	}	
	
	public void setNewName(String newName) {
		this.newName = newName;
	}	
	
	public void setNewSpId(int newGroupId) {
		this.newGroupId = newGroupId;
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
	
	public String getGroupName() {
		return groupName;
	}
	
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}	
	
	
	public String[] firstString() {
		return new String[] {"Студент", "Группа"};
	}
}
