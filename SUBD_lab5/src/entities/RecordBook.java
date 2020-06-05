package entities;

public class RecordBook {
	String[] names = {"Студент", "Предмет", "Семестр", "Форма отчетности", "Оценка"};		
	private int id;
	private int semester;
	private String reportingForm;
	private int mark;
	private String subjectName;
	private String studentName;	
	private int newSemester;	
	private String newReportingForm;	
	private int newMark;
	private int newSubjectId;
	private int newStudentId;
	private String newStudentName;
	private String newSubjectName;
	
	
	public String getSelectQuery() {
		return "select recordbookid, semester, reportingform,mark, studentname, subjectname \r\n" + 
				"from recordbook, student, subject \r\n" + 
				"where recordbook.studentid = student.studentid and recordbook.subjectid = subject.subjectid";	
	}
	
	public String getUpdateQuery(int id) {
		return "update recordbook set semester = '" + newSemester + "'," + "Subjectid = '" + Integer.toString(newSubjectId) + "'," + "Studentid = '" + Integer.toString(newStudentId) + "'," + "Mark = '" + newMark + "' where recordbookid = " + Integer.toString(id);
	}	
	
	public String getDeleteQuery(int id) {
		return "delete from recordbook where recordbookid = " + Integer.toString(id);
	}	
	
	public String getStudents() {
		return "select studentname from student";
	}
	
	public String getSubjects() {
		return "select subjectname from subject";
	}
	
	public String getAddQuery() {	
		return "insert into Recordbook values (nextval('Recordbook_seq')," + Integer.toString(newStudentId) + "," + Integer.toString(newSemester) + ",'" + newReportingForm + "'," + Integer.toString(newMark) +  "," + Integer.toString(newSubjectId) + ")";
	}			
	
	public String[] getNames() {
		return names;
	}	
	
	public void setNewStudentName(String newName) {
		this.newStudentName = newName;
	}	
	
	public void setNewSubjectName(String newName) {
		this.newSubjectName = newName;
	}	
	
	public void setNewStudentId(int newStudentId) {
		this.newStudentId = newStudentId;
	}
	
	public void setNewSubjectId(int newSubjectId) {
		this.newSubjectId = newSubjectId;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getReportingForm() {
		return reportingForm;
	}
	
	public String getNewReportingForm() {
		return newReportingForm;
	}
	
	public int getSemester() {
		return semester;
	}
	
	public int getMark() {
		return mark;
	}	
	
	public void setSemester(int semester) {
		this.semester = semester;
	}
	
	public void setNewSemester(int semester) {
		this.newSemester = semester;
	}
	
	public void setReportingForm(String rf) {
		this.reportingForm = rf;
	}
	
	public void setMark(int mark) {
		this.mark = mark;
	}
	
	public void setNewReportingForm(String rf) {
		this.newReportingForm = rf;
	}
	
	public void setNewMark(int mark) {
		this.newMark = mark;
	}
	
	public String getStudentName() {
		return studentName;
	}
	
	public String getSubjectName() {
		return subjectName;
	}
	
	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}
	
	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}	
	
	public String[] firstString() {
		return new String[] {"Студент", "Предмет", "Семестр", "Форма отчетности", "Оценка"};
	}
}
