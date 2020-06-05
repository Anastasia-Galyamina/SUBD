import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.*;

import entities.Faculty;
import entities.Group;
import entities.Lecturer;
import entities.RecordBook;
import entities.Specialization;
import entities.Student;
import entities.Subject;
import entities.Department;

import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JSlider;
import javax.swing.JTable;
import javax.swing.JSpinner;
import javax.swing.JEditorPane;
import javax.swing.JList;
import javax.swing.JComboBox;

public class MainFrame extends JFrame {

	private JPanel contentPane;
	private DBConnect dbconnect = new DBConnect();			
	private JTextField textFieldDatabase;
	private JTextField textFieldUser;
	private JTextField textFieldPassword;
	JLabel lblState;
	DefaultTableModel model = new DefaultTableModel();
	private JTable table = new JTable(model);
	String tableName;
	private JTextField textFieldAdd1;
	private JLabel labelAdd1;
	JLabel lblNewLabelChoose;
	JComboBox comboBox;
	private JTextField textFieldAdd2;
	private JTextField textFieldAdd3;
	JComboBox comboBox1;
	JLabel labelAdd2;
	JLabel labelAdd3;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame frame = new MainFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
		public void addColumns(String[] array) {
			
			for(int i = 0; i < array.length; i++)
				model.addColumn(array);
		}
	
	public MainFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1158, 417);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("\u041F\u043E\u0434\u043A\u043B\u044E\u0447\u0435\u043D\u0438\u0435 \u043A \u0411\u0430\u0437\u0435 \u0434\u0430\u043D\u043D\u044B\u0445");
		lblNewLabel.setBounds(53, 12, 207, 15);
		contentPane.add(lblNewLabel);
		
		
		JButton btnNewConnection = new JButton("\u041D\u043E\u0432\u043E\u0435 \u043F\u043E\u0434\u043A\u043B\u044E\u0447\u0435\u043D\u0438\u0435");		
		btnNewConnection.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(dbconnect.connect("jdbc:postgresql://localhost/" + textFieldDatabase.getText(), textFieldUser.getText(), textFieldPassword.getText()))//dbconnect.connect("jdbc:postgresql://localhost/University", "postgres", "yfcnz157")					
					lblState.setText("connected");
				else
					lblState.setText("failed to connect");	
			}
		});
		btnNewConnection.setBounds(10, 39, 149, 23);
		contentPane.add(btnNewConnection);
		
		JButton buttonDisconnect = new JButton("\u041E\u0442\u043A\u043B\u044E\u0447\u0438\u0442\u044C\u0441\u044F");
		buttonDisconnect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dbconnect.disconnect();
			}
		});
		buttonDisconnect.setBounds(20, 187, 126, 23);
		contentPane.add(buttonDisconnect);
		
		JButton buttonExit = new JButton("\u0412\u044B\u0445\u043E\u0434");
		buttonExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(1);
			}
		});
		buttonExit.setBounds(172, 187, 88, 23);
		contentPane.add(buttonExit);
		
		lblState = new JLabel("");
		lblState.setBounds(154, 222, 106, 15);
		contentPane.add(lblState);				
		
		JLabel label = new JLabel("Database");
		label.setBounds(10, 80, 76, 26);
		contentPane.add(label);
		
		textFieldDatabase = new JTextField();
		textFieldDatabase.setColumns(10);
		textFieldDatabase.setBounds(92, 83, 106, 21);
		contentPane.add(textFieldDatabase);
		
		JLabel label_1 = new JLabel("user");
		label_1.setBounds(10, 118, 50, 15);
		contentPane.add(label_1);
		
		textFieldUser = new JTextField();
		textFieldUser.setColumns(10);
		textFieldUser.setBounds(92, 116, 106, 21);
		contentPane.add(textFieldUser);
		
		textFieldPassword = new JTextField();
		textFieldPassword.setColumns(10);
		textFieldPassword.setBounds(92, 154, 106, 21);
		contentPane.add(textFieldPassword);
		
		JLabel label_2 = new JLabel("password");
		label_2.setBounds(10, 157, 72, 15);
		contentPane.add(label_2);
		
		JLabel label_3 = new JLabel("\u0421\u043E\u0441\u0442\u043E\u044F\u043D\u0438\u0435 \u043F\u043E\u0434\u043A\u043B\u044E\u0447\u0435\u043D\u0438\u044F");
		label_3.setBounds(10, 222, 136, 15);
		contentPane.add(label_3);
		
		JLabel lblNewLabel_1 = new JLabel("\u0421\u043F\u0440\u0430\u0432\u043E\u0447\u043D\u0438\u043A\u0438");
		lblNewLabel_1.setBounds(360, 12, 88, 15);
		contentPane.add(lblNewLabel_1);
		
		JButton btnNewFaculty = new JButton("\u0424\u0430\u043A\u0443\u043B\u044C\u0442\u0435\u0442\u044B");
		btnNewFaculty.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {	
				labelAdd1.setText("Введите название факультета");
				tableName = "Faculty";					
				Faculty faculty = new Faculty();				
				ResultSet res = dbconnect.createQuery(faculty.getSelectQuery());					
				model = new DefaultTableModel();
				addColumns(faculty.getNames());		
				table.setModel(model); 
				model.addRow(faculty.firstString());
				try {
					while(res.next()) {							
						faculty.setId(res.getInt(1));
						faculty.setName(res.getString(2));						
						model.addRow(new String[] {faculty.getName()});	
					}	
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}		
		});
		btnNewFaculty.setBounds(305, 52, 185, 23);
		contentPane.add(btnNewFaculty);		
		
		JButton buttonDepartment = new JButton("\u041A\u0430\u0444\u0435\u0434\u0440\u0430");
		buttonDepartment.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {	
				comboBox.removeAllItems();
				tableName = "department";				
				labelAdd1.setText("Введите название кафедры");
				lblNewLabelChoose.setText("Выберите факультет");				
				Department dep = new Department();
				ResultSet facultyRes = dbconnect.createQuery(dep.getFaculties());				
				try {
					while(facultyRes.next()) {					
						comboBox.addItem(facultyRes.getString(1));						
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}		
				
				ResultSet res = dbconnect.createQuery(dep.getSelectQuery());						
				model = new DefaultTableModel();
				table.setModel(model);
				addColumns(dep.getNames());					
				model.addRow(dep.firstString());				
				try {
					while(res.next()) {						
						dep.setId(res.getInt(1));
						dep.setName(res.getString(2));	
						dep.setFacultyName(res.getString(3));
						model.addRow(new String[] {dep.getName(), dep.getFacultyName()});							
					}						
									
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}	
			}
		});
		buttonDepartment.setBounds(305, 82, 185, 23);
		contentPane.add(buttonDepartment);
		
		JButton buttonSp = new JButton("\u041D\u0430\u043F\u0440\u0432\u043B\u0435\u043D\u0438\u0435 \u043F\u043E\u0434\u0433\u043E\u0442\u043E\u0432\u043A\u0438");
		buttonSp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				comboBox.removeAllItems();
				tableName = "sp";				
				labelAdd1.setText("Введите название специальности");
				lblNewLabelChoose.setText("Выберите кафедру");				
				Specialization sp = new Specialization();
				ResultSet depRes = dbconnect.createQuery(sp.getDepartments());				
				try {
					while(depRes.next()) {					
						comboBox.addItem(depRes.getString(1));						
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}		
				
				ResultSet res = dbconnect.createQuery(sp.getSelectQuery());						
				model = new DefaultTableModel();
				table.setModel(model);
				addColumns(sp.getNames());					
				model.addRow(sp.firstString());				
				try {
					while(res.next()) {						
						sp.setId(res.getInt(1));
						sp.setName(res.getString(2));	
						sp.setDepName(res.getString(3));
						model.addRow(new String[] {sp.getName(), sp.getDepName()});							
					}						
									
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}	
				
			}
		});
		buttonSp.setBounds(305, 114, 185, 23);
		contentPane.add(buttonSp);
		
		JButton btnNewButtonGroup = new JButton("\u0413\u0440\u0443\u043F\u043F\u0430");
		btnNewButtonGroup.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				comboBox.removeAllItems();
				tableName = "group";				
				labelAdd1.setText("Введите название группы");
				lblNewLabelChoose.setText("Выберите специальность");				
				Group group = new Group();
				ResultSet spRes = dbconnect.createQuery(group.getSps());				
				try {
					while(spRes.next()) {					
						comboBox.addItem(spRes.getString(1));						
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}		
				
				ResultSet res = dbconnect.createQuery(group.getSelectQuery());						
				model = new DefaultTableModel();
				table.setModel(model);
				addColumns(group.getNames());					
				model.addRow(group.firstString());				
				try {
					while(res.next()) {						
						group.setId(res.getInt(1));
						group.setName(res.getString(2));	
						group.setSpName(res.getString(3));
						model.addRow(new String[] {group.getName(), group.getSpName()});							
					}						
									
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}	
				
			}
		});
		btnNewButtonGroup.setBounds(305, 149, 185, 23);
		contentPane.add(btnNewButtonGroup);
		
		JButton btnNewButtonStudent = new JButton("\u0421\u0442\u0443\u0434\u0435\u043D\u0442");
		btnNewButtonStudent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				comboBox.removeAllItems();
				tableName = "Student";				
				labelAdd1.setText("Введите ФИО студента");
				lblNewLabelChoose.setText("Выберите группу");				
				Student student = new Student();
				ResultSet groupRes = dbconnect.createQuery(student.getGroups());				
				try {
					while(groupRes.next()) {					
						comboBox.addItem(groupRes.getString(1));						
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}		
				
				ResultSet res = dbconnect.createQuery(student.getSelectQuery());						
				model = new DefaultTableModel();
				table.setModel(model);
				addColumns(student.getNames());					
				model.addRow(student.firstString());				
				try {
					while(res.next()) {						
						student.setId(res.getInt(1));
						student.setName(res.getString(2));	
						student.setGroupName(res.getString(3));
						model.addRow(new String[] {student.getName(), student.getGroupName()});							
					}						
									
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}	
			}
		});
		btnNewButtonStudent.setBounds(305, 184, 185, 23);
		contentPane.add(btnNewButtonStudent);
		
		JButton btnNewButtonlecturer = new JButton("\u041F\u0440\u0435\u043F\u043E\u0434\u0430\u0432\u0430\u0442\u0435\u043B\u044C");
		btnNewButtonlecturer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				labelAdd1.setText("Введите ФИО преподавателя");
				tableName = "Lecturer";					
				Lecturer lecturer = new Lecturer();				
				ResultSet res = dbconnect.createQuery(lecturer.getSelectQuery());					
				model = new DefaultTableModel();
				addColumns(lecturer.getNames());		
				table.setModel(model); 
				model.addRow(lecturer.firstString());
				try {
					while(res.next()) {							
						lecturer.setId(res.getInt(1));
						lecturer.setName(res.getString(2));						
						model.addRow(new String[] {lecturer.getName()});	
					}	
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnNewButtonlecturer.setBounds(305, 218, 185, 23);
		contentPane.add(btnNewButtonlecturer);
		
		JButton btnNewButtonSubject = new JButton("\u041F\u0440\u0435\u0434\u043C\u0435\u0442");
		btnNewButtonSubject.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				comboBox.removeAllItems();
				tableName = "Subject";				
				labelAdd1.setText("Введите название предмета");
				lblNewLabelChoose.setText("Выберите преподавателя");				
				Subject subject = new Subject();
				ResultSet groupRes = dbconnect.createQuery(subject.getLecturers());				
				try {
					while(groupRes.next()) {					
						comboBox.addItem(groupRes.getString(1));						
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}		
				
				ResultSet res = dbconnect.createQuery(subject.getSelectQuery());						
				model = new DefaultTableModel();
				table.setModel(model);
				addColumns(subject.getNames());					
				model.addRow(subject.firstString());				
				try {
					while(res.next()) {						
						subject.setId(res.getInt(1));
						subject.setName(res.getString(2));	
						subject.setLecturerName(res.getString(3));
						model.addRow(new String[] {subject.getName(), subject.getLecturerName()});							
					}						
									
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}	
			}
		});
		btnNewButtonSubject.setBounds(305, 253, 185, 23);
		contentPane.add(btnNewButtonSubject);
		

		JButton btnNewButtonRecordBook = new JButton("\u0417\u0430\u0447\u0451\u0442\u043A\u0430");
		btnNewButtonRecordBook.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				comboBox.removeAllItems();				
				tableName = "RecordBook";				
				labelAdd1.setText("Введите номер саместра");
				labelAdd2.setText("Введите форму отчетности");
				labelAdd3.setText("Введите оценку");
				lblNewLabelChoose.setText("Выберите студента и предмет");				
				RecordBook recordBook = new RecordBook();
				ResultSet studentRes = dbconnect.createQuery(recordBook.getStudents());	
				ResultSet subjectRes = dbconnect.createQuery(recordBook.getSubjects());
				try {
					while(studentRes.next()) {					
						comboBox.addItem(studentRes.getString(1));						
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}	
				try {
					while(subjectRes.next()) {					
						comboBox1.addItem(subjectRes.getString(1));						
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}		
				
				ResultSet res = dbconnect.createQuery(recordBook.getSelectQuery());						
				model = new DefaultTableModel();
				table.setModel(model);
				addColumns(recordBook.getNames());					
				model.addRow(recordBook.firstString());				
				try {
					while(res.next()) {						
						recordBook.setId(res.getInt(1));
						recordBook.setSemester(res.getInt(2));	
						recordBook.setReportingForm(res.getString(3));
						recordBook.setMark(res.getInt(4));	
						recordBook.setStudentName(res.getString(5));
						recordBook.setSubjectName(res.getString(6));
						model.addRow(new String[] {recordBook.getStudentName(), recordBook.getSubjectName(), Integer.toString(recordBook.getSemester()), recordBook.getReportingForm(), Integer.toString(recordBook.getMark())});							
					}						
									
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}	
			}
		});
		btnNewButtonRecordBook.setBounds(305, 288, 185, 23);
		contentPane.add(btnNewButtonRecordBook);
		
		
		JButton btnNewButtonAdd = new JButton("\u0414\u043E\u0431\u0430\u0432\u0438\u0442\u044C");
		btnNewButtonAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				switch(tableName) {
				case "Faculty":
					Faculty faculty = new Faculty();					
					faculty.setNewName(textFieldAdd1.getText());
					try {
						dbconnect.createStatement(faculty.getAddQuery());
					model.addRow(new String[] {textFieldAdd1.getText()});
					} catch (Exception e1){						
					}						
				case "department":
					Department department = new Department();					
					department.setNewName(textFieldAdd1.getText());					
					ResultSet res = dbconnect.createQuery("select facultyid from faculty where facultyname = '" + comboBox.getSelectedItem().toString() + "'");		
					try {						
						int facultyid = 0;		
						while(res.next()){
							facultyid = res.getInt(1);							
						}
						department.setNewFacultyId(facultyid);
						dbconnect.createStatement(department.getAddQuery());
						model.addRow(new String[] {textFieldAdd1.getText(), comboBox.getSelectedItem().toString()});						
						
					} catch (SQLException e3) {
						// TODO Auto-generated catch block
						e3.printStackTrace();
					}	
				case "sp":
					Specialization sp = new Specialization();					
					sp.setNewName(textFieldAdd1.getText());					
					ResultSet res1 = dbconnect.createQuery("select departmentid from department where departmentname = '" + comboBox.getSelectedItem().toString() + "'");		
					try {						
						int depid = 0;		
						while(res1.next()){
							depid = res1.getInt(1);							
						}
						sp.setNewDepId(depid);
						dbconnect.createStatement(sp.getAddQuery());
						model.addRow(new String[] {textFieldAdd1.getText(), comboBox.getSelectedItem().toString()});						
						
					} catch (SQLException e3) {
						// TODO Auto-generated catch block
						e3.printStackTrace();
					}	
				case "group":
					Group group = new Group();					
					group.setNewName(textFieldAdd1.getText());					
					ResultSet res2 = dbconnect.createQuery("select specializationid from specialization where specializationname = '" + comboBox.getSelectedItem().toString() + "'");		
					try {						
						int spid = 0;		
						while(res2.next()){
							spid = res2.getInt(1);							
						}
						group.setNewSpId(spid);
						dbconnect.createStatement(group.getAddQuery());
						model.addRow(new String[] {textFieldAdd1.getText(), comboBox.getSelectedItem().toString()});						
						
					} catch (SQLException e3) {
						// TODO Auto-generated catch block
						e3.printStackTrace();
					}
				case "Student":
					Student student = new Student();					
					student.setNewName(textFieldAdd1.getText());					
					ResultSet res3 = dbconnect.createQuery("select groupid from grouptable where groupname = '" + comboBox.getSelectedItem().toString() + "'");		
					try {						
						int spid = 0;		
						while(res3.next()){
							spid = res3.getInt(1);							
						}
						student.setNewSpId(spid);
						dbconnect.createStatement(student.getAddQuery());						
						model.addRow(new String[] {textFieldAdd1.getText(), comboBox.getSelectedItem().toString()});						
						
					} catch (SQLException e3) {
						// TODO Auto-generated catch block
						e3.printStackTrace();
					}
				case "Lecturer":
					Lecturer lecturer = new Lecturer();					
					lecturer.setNewName(textFieldAdd1.getText());
					try {
						dbconnect.createStatement(lecturer.getAddQuery());
					model.addRow(new String[] {textFieldAdd1.getText()});
					} catch (Exception e1){						
					}	
				case "Subject":
					Subject subject = new Subject();					
					subject.setNewName(textFieldAdd1.getText());					
					ResultSet res4 = dbconnect.createQuery("select lecturerid from lecturer where name = '" + comboBox.getSelectedItem().toString() + "'");		
					try {						
						int lecturerid = 0;		
						while(res4.next()){
							lecturerid = res4.getInt(1);							
						}
						subject.setNewLecturerId(lecturerid);
						dbconnect.createStatement(subject.getAddQuery());						
						model.addRow(new String[] {textFieldAdd1.getText(), comboBox.getSelectedItem().toString()});						
						
					} catch (SQLException e3) {
						// TODO Auto-generated catch block
						e3.printStackTrace();
					}
				case "RecordBook":
					RecordBook recordBook = new RecordBook();					
					recordBook.setNewSemester(Integer.parseInt(textFieldAdd1.getText()));	
					recordBook.setNewReportingForm(textFieldAdd2.getText());
					recordBook.setNewMark(Integer.parseInt(textFieldAdd3.getText()));
					recordBook.setNewStudentName(comboBox.getSelectedItem().toString());
					recordBook.setNewStudentName(comboBox1.getSelectedItem().toString());
					ResultSet res5 = dbconnect.createQuery("select studentid from student where studentname = '" + comboBox.getSelectedItem().toString() + "'");	
					ResultSet res6 = dbconnect.createQuery("select subjectid from subject where subjectname = '" + comboBox1.getSelectedItem().toString() + "'");	
					try {						
						int studentid = 0;		
						while(res5.next()){
							studentid = res5.getInt(1);							
						}
						int subjectid = 0;		
						while(res6.next()){
							subjectid = res6.getInt(1);							
						}						
						recordBook.setNewStudentId(studentid);
						recordBook.setNewSubjectId(subjectid);
						dbconnect.createStatement(recordBook.getAddQuery());						
						model.addRow(new String[] {comboBox.getSelectedItem().toString(), comboBox1.getSelectedItem().toString(), textFieldAdd1.getText(), textFieldAdd2.getText(), textFieldAdd3.getText()});						
						
					} catch (SQLException e3) {
						// TODO Auto-generated catch block
						e3.printStackTrace();
					}
				}	
			
			}
		});
		btnNewButtonAdd.setBounds(1017, 218, 115, 23);
		contentPane.add(btnNewButtonAdd);
		
		JButton btnNewButtonEdit = new JButton("\u0420\u0435\u0434\u0430\u043A\u0442\u0438\u0440\u043E\u0432\u0430\u0442\u044C");
		btnNewButtonEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				switch(tableName) {
				case "Faculty":
				Faculty faculty = new Faculty();	
				//название, которое надо поменять	
				String name = table.getValueAt(table.getSelectedRow(), 0).toString();
				System.out.println(name);
				//нашли id  таблице
				ResultSet res = dbconnect.createQuery("select facultyid from faculty where facultyname = '" + name + "'");	
				int facultyid = 0;		
					try {
						while(res.next()){
							facultyid = res.getInt(1);						
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				faculty.setNewName(textFieldAdd1.getText());					
				dbconnect.createStatement(faculty.getUpdateQuery(facultyid));					
				case "department":	
					Department dep = new Department();						
					String name1 = table.getValueAt(table.getSelectedRow(), 0).toString();	
					ResultSet res1 = dbconnect.createQuery("select departmentid from department where departmentname = '" + name1 + "'");
					ResultSet res2 = dbconnect.createQuery("select facultyid from faculty where facultyname = '" + comboBox.getSelectedItem().toString() + "'");
					int facultyid1=0;
					int depid = 0;		
						try {
							while(res1.next())
								depid = res1.getInt(1);	
							while(res2.next())
								facultyid1 = res2.getInt(1);	
							
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}						
					dep.setNewName(textFieldAdd1.getText());	
					dep.setNewFacultyId(facultyid1);
					dbconnect.createStatement(dep.getUpdateQuery(depid));	
				case "sp":	
					Specialization sp = new Specialization();						
					String spname = table.getValueAt(table.getSelectedRow(), 0).toString();	
					ResultSet spres = dbconnect.createQuery("select specializationid from specialization where specializationname = '" + spname + "'");
					ResultSet depres = dbconnect.createQuery("select departmentid from department where departmentname = '" + comboBox.getSelectedItem().toString() + "'");
					int departid=0;
					int spid = 0;		
						try {
							while(spres.next())
								spid = spres.getInt(1);	
							while(depres.next())
								departid = depres.getInt(1);	
							
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}						
					sp.setNewName(textFieldAdd1.getText());	
					sp.setNewDepId(departid);
					dbconnect.createStatement(sp.getUpdateQuery(spid));
				case "group":	
					Group group = new Group();						
					String groupname = table.getValueAt(table.getSelectedRow(), 0).toString();	
					ResultSet groupres = dbconnect.createQuery("select groupid from grouptable where groupname = '" + groupname + "'");
					ResultSet spesres = dbconnect.createQuery("select specializationid from specialization where specializationname = '" + comboBox.getSelectedItem().toString() + "'");
					int specialid=0;
					int grid = 0;		
						try {
							while(groupres.next())
								grid = groupres.getInt(1);	
							while(spesres.next())
								specialid = spesres.getInt(1);	
							
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}						
					group.setNewName(textFieldAdd1.getText());	
					group.setNewSpId(specialid);
					dbconnect.createStatement(group.getUpdateQuery(grid));
				case "Lecturer":
					Lecturer lecturer = new Lecturer();							
					String lectname = table.getValueAt(table.getSelectedRow(), 0).toString();					
					ResultSet lectres = dbconnect.createQuery("select lecturerid from lecturer where name = '" + lectname + "'");	
					int lecturerid = 0;		
						try {
							while(lectres.next()){
								lecturerid = lectres.getInt(1);						
							}
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					lecturer.setNewName(textFieldAdd1.getText());					
					dbconnect.createStatement(lecturer.getUpdateQuery(lecturerid));
				case "Student":	
					Student student = new Student();						
					String stname = table.getValueAt(table.getSelectedRow(), 0).toString();	
					ResultSet stres = dbconnect.createQuery("select studentid from student where studentname = '" + stname + "'");
					ResultSet grres = dbconnect.createQuery("select groupid from grouptable where groupname = '" + comboBox.getSelectedItem().toString() + "'");
					int groupid=0;
					int studid = 0;		
						try {
							while(stres.next())
								studid = stres.getInt(1);	
							while(grres.next())
								groupid = grres.getInt(1);	
							
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}						
					student.setNewName(textFieldAdd1.getText());	
					student.setNewSpId(groupid);
					dbconnect.createStatement(student.getUpdateQuery(studid));
				case "Subject":	
					Subject subject = new Subject();						
					String subjectname = table.getValueAt(table.getSelectedRow(), 0).toString();	
					ResultSet subjectres = dbconnect.createQuery("select subjectid from subject where subjectname = '" + subjectname + "'");
					ResultSet lres = dbconnect.createQuery("select lecturerid from lecturer where name = '" + comboBox.getSelectedItem().toString() + "'");
					int lid=0;
					int subid = 0;		
						try {
							while(subjectres.next())
								subid = subjectres.getInt(1);	
							while(lres.next())
								lid = lres.getInt(1);	
							
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}						
					subject.setNewName(textFieldAdd1.getText());	
					subject.setNewLecturerId(lid);
					dbconnect.createStatement(subject.getUpdateQuery(subid));
				case "RecordBook":	
					RecordBook rb = new RecordBook();						
					String semester = table.getValueAt(table.getSelectedRow(), 2).toString();	
					String repForm = table.getValueAt(table.getSelectedRow(), 3).toString();
					String mark = table.getValueAt(table.getSelectedRow(), 4).toString();									
					ResultSet rbres = dbconnect.createQuery("select recordbookid from recordbook where semester = " + semester + " and  reportingform = '" + repForm + "' and mark = " + mark );						
					ResultSet studentresult = dbconnect.createQuery("select studentid from student where studentname = '" + comboBox.getSelectedItem().toString() + "'");
					ResultSet subjectresult = dbconnect.createQuery("select subjectid from subject where subjectname = '" + comboBox1.getSelectedItem().toString() + "'");
					int studentsid=0;
					int subjectid=0;
					int recordbookid = 0;	
					
						try {
							while(rbres.next())
								recordbookid = rbres.getInt(1);	
							while(studentresult.next())
								studentsid = studentresult.getInt(1);	
							while(subjectresult.next())
								subjectid = subjectresult.getInt(1);	
							
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}	
						
					rb.setNewSemester(Integer.parseInt(semester));	
					rb.setNewReportingForm(repForm);	
					rb.setNewMark(Integer.parseInt(mark));	
					rb.setNewStudentId(studentsid);	
					rb.setNewSubjectId(subjectid);
					
					dbconnect.createStatement(rb.getUpdateQuery(recordbookid));
					
			}
			
			}
				
		});
		btnNewButtonEdit.setBounds(1017, 253, 115, 23);
		contentPane.add(btnNewButtonEdit);
		
		JButton btnNewButtonDelete = new JButton("\u0423\u0434\u0430\u043B\u0438\u0442\u044C");
		btnNewButtonDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				switch(tableName) {
				case "Faculty":
				Faculty faculty = new Faculty();					
				String name = table.getValueAt(table.getSelectedRow(), 0).toString();				
				ResultSet res = dbconnect.createQuery("select facultyid from faculty where facultyname = '" + name + "'");	
				int facultyid = 0;		
					try {
						while(res.next()){
							facultyid = res.getInt(1);						
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}							
				dbconnect.createStatement(faculty.getDeleteQuery(facultyid));					
				case "department":		
					Department dep = new Department();					
					String depname = table.getValueAt(table.getSelectedRow(), 0).toString();				
					ResultSet depres = dbconnect.createQuery("select departmentid from department where departmentname = '" + depname + "'");	
					int depid = 0;		
						try {
							while(depres.next()){
								depid = depres.getInt(1);						
							}
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}									
					dbconnect.createStatement(dep.getDeleteQuery(depid));	
				case "sp":		
					Specialization sp = new Specialization();					
					String spname = table.getValueAt(table.getSelectedRow(), 0).toString();				
					ResultSet spres = dbconnect.createQuery("select specializationid from specialization where specizlizationname = '" + spname + "'");	
					int spid = 0;		
						try {
							while(spres.next()){
								spid = spres.getInt(1);						
							}
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}							
					dbconnect.createStatement(sp.getDeleteQuery(spid));	
				case "group":		
					Group group = new Group();					
					String groupname = table.getValueAt(table.getSelectedRow(), 0).toString();				
					ResultSet groupres = dbconnect.createQuery("select groupid from grouptable where groupname = '" + groupname + "'");	
					int groupid = 0;		
						try {
							while(groupres.next()){
								groupid = groupres.getInt(1);						
							}
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}									
					dbconnect.createStatement(group.getDeleteQuery(groupid));	
				case "Lecturer":		
					Lecturer lecturer = new Lecturer();					
					String lecturername = table.getValueAt(table.getSelectedRow(), 0).toString();				
					ResultSet lectres = dbconnect.createQuery("select lecturerid from lecturer where name = '" + lecturername + "'");	
					int lectid = 0;		
						try {
							while(lectres.next()){
								lectid = lectres.getInt(1);						
							}
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}							
					dbconnect.createStatement(lecturer.getDeleteQuery(lectid));	
				case "Student":		
					Student student = new Student();					
					String studentname = table.getValueAt(table.getSelectedRow(), 0).toString();				
					ResultSet stres = dbconnect.createQuery("select studentid from student where studentname = '" + studentname + "'");	
					int stid = 0;		
						try {
							while(stres.next()){
								stid = stres.getInt(1);						
							}
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}									
					dbconnect.createStatement(student.getDeleteQuery(stid));	
				case "Subject":		
					Subject subject = new Subject();					
					String subjectname = table.getValueAt(table.getSelectedRow(), 0).toString();				
					ResultSet subjectres = dbconnect.createQuery("select subjectid from subject where subjectname = '" + subjectname + "'");	
					int subjectid = 0;		
						try {
							while(subjectres.next()){
								subjectid = subjectres.getInt(1);						
							}
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}								
					dbconnect.createStatement(subject.getDeleteQuery(subjectid));	
				case "RecordBook":		
					RecordBook recordBook = new RecordBook();	
					String semester = table.getValueAt(table.getSelectedRow(), 2).toString();	
					String repForm = table.getValueAt(table.getSelectedRow(), 3).toString();	
					String mark = table.getValueAt(table.getSelectedRow(), 4).toString();						
					ResultSet rbRes = dbconnect.createQuery("select recordbookid from recordbook where semester = " + semester + " and reportingform = '" + repForm + "' and mark =" + mark );	
					int rbid = 0;		
						try {
							while(rbRes.next()){
								rbid = rbRes.getInt(1);						
							}
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}			
					
					dbconnect.createStatement(recordBook.getDeleteQuery(rbid));	
					
			}
			}
		});
		btnNewButtonDelete.setBounds(1017, 288, 115, 23);
		contentPane.add(btnNewButtonDelete);
		
		JLabel lblNewLabelDelete = new JLabel("");
		lblNewLabelDelete.setBounds(594, 462, 50, 15);
		contentPane.add(lblNewLabelDelete);
		
		JLabel labelEdit = new JLabel("");
		labelEdit.setBounds(594, 402, 50, 15);
		contentPane.add(labelEdit);
		
		JLabel label_4 = new JLabel("");
		label_4.setBounds(594, 352, 50, 15);
		contentPane.add(label_4);
		
		JList list = new JList();
		list.setBounds(85, 365, 74, -45);
		contentPane.add(list);		
		
		
		table.setBounds(517, 12, 473, 355);			
		contentPane.add(table);
		
		comboBox = new JComboBox();
		comboBox.setBounds(1022, 154, 96, 21);
		contentPane.add(comboBox);
		
		lblNewLabelChoose = new JLabel("");
		lblNewLabelChoose.setBounds(1017, 138, 120, 15);
		contentPane.add(lblNewLabelChoose);
		
		labelAdd1 = new JLabel("");
		labelAdd1.setBounds(1000, 12, 132, 15);
		contentPane.add(labelAdd1);
		
		textFieldAdd1 = new JTextField();
		textFieldAdd1.setBounds(1017, 39, 96, 15);
		contentPane.add(textFieldAdd1);
		textFieldAdd1.setColumns(10);
				
		
		comboBox1 = new JComboBox();
		comboBox1.setBounds(1022, 188, 96, 21);
		contentPane.add(comboBox1);
		
		textFieldAdd2 = new JTextField();
		textFieldAdd2.setColumns(10);
		textFieldAdd2.setBounds(1017, 80, 96, 15);
		contentPane.add(textFieldAdd2);
		
		textFieldAdd3 = new JTextField();
		textFieldAdd3.setColumns(10);
		textFieldAdd3.setBounds(1022, 118, 96, 15);
		contentPane.add(textFieldAdd3);
		
		labelAdd2 = new JLabel("");
		labelAdd2.setBounds(1000, 56, 132, 15);
		contentPane.add(labelAdd2);
		
		labelAdd3 = new JLabel("");
		labelAdd3.setBounds(1012, 96, 96, 15);		
		contentPane.add(labelAdd3);
		
		
		
		
		
	}
}
