import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBConnect {	
	private Connection connection;		
		
	public boolean connect(String url, String user, String password) {		
			try{	
				connection = DriverManager.getConnection(url, user, password);				
				return true;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return false;
			}
	}	
	
	public void disconnect() {
		try {
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
		//вставка
		public void createStatement(String statement) {
		try {
			Statement st = connection.createStatement();
			st.execute(statement);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
		
		
		//запрос данных
		public ResultSet createQuery(String query ) {
			try {		
				Statement st = connection.createStatement();				
				ResultSet res = st.executeQuery(query);				
				return res;				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
	}
		
}		
	

