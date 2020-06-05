package entities;

public class Group {
	String[] names = {"Группа", "Напрвление подготовки"};		
	private int id;
	private String name;
	private String spName;
	private String newName;	
	private int newSpId;
	
	
	public String getSelectQuery() {
		return "select groupid, groupname, specializationname \r\n" + 
				"from grouptable, specialization \r\n" + 
				"where grouptable.specializationid = specialization.specializationid";	
	}
	
	public String getSps() {
		return "select specializationname from specialization";
	}
	
	public String getUpdateQuery(int id) {
		return "update grouptable set groupname = '" + newName + "'," + "specializationid = '" + Integer.toString(newSpId) + "' where groupid = " + Integer.toString(id);
	}	
	public String getDeleteQuery(int id) {
		return "delete from grouptable where groupid = " + Integer.toString(id);
	}	
	
	public String getAddQuery() {	
		return "insert into Grouptable values (" + Integer.toString(newSpId) +  ", nextval('Group_seq'),'" + newName + "')";
	}			
	
	public String[] getNames() {
		return names;
	}	
	
	public void setNewName(String newName) {
		this.newName = newName;
	}	
	
	public void setNewSpId(int newSpId) {
		this.newSpId = newSpId;
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
	
	public String getSpName() {
		return spName;
	}
	
	public void setSpName(String spName) {
		this.spName = spName;
	}
	
	public String[] firstString() {
		return new String[] {"Группа", "Направление подготовки"};
	}
}
