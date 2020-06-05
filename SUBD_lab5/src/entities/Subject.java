package entities;

public class Subject {
	String[] names = {"Предмет", "Преподаватель"};		
	private int id;
	private String name;
	private String lecturerName;
	private String newName;	
	private int newLecturerId;
	
	
	public String getSelectQuery() {
		return "select subjectid, subjectname, name \r\n" + 
				"from  subject, lecturer \r\n" + 
				"where lecturer.lecturerid = subject.lecturerid";	
	}
	
	public String getLecturers() {
		return "select name from lecturer";
	}
	
	public String getUpdateQuery(int id) {
		return "update subject set subjectname = '" + newName + "'," + "lecturerid = '" + Integer.toString(newLecturerId) + "' where subjectid = " + Integer.toString(id);
	}	
	
	public String getDeleteQuery(int id) {
		return "delete from subject where subjectid = " + Integer.toString(id);
	}	
	
	public String getAddQuery() {	
		return "insert into Subject values (nextval('Subject_seq'), '" + newName + "'," + Integer.toString(newLecturerId) + ")";
	}			
	
	public String[] getNames() {
		return names;
	}	
	
	public void setNewName(String newName) {
		this.newName = newName;
	}	
	
	public void setNewLecturerId(int newLecturerId) {
		this.newLecturerId = newLecturerId;
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
	
	public String getLecturerName() {
		return lecturerName;
	}
	
	public void setLecturerName(String lecturerName) {
		this.lecturerName = lecturerName;
	}
	
	@Override
	public String toString() {
		return getName() + " " +  getLecturerName();
	}
	
	public String[] firstString() {
		return new String[] {"Предмет", "Преподаватель"};
	}
}
