import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AddDAO {

	ArrayList<AddDefect> arrayList = new ArrayList<AddDefect>();
	ArrayList<User> userArray = new ArrayList<User>();
	Connection con = null;
	Statement st = null;
	ResultSet rs = null;

	public AddDAO() {

		makeConnection();
		try {
			String q = "SELECT * from defect";
			st = con.createStatement();
			rs = st.executeQuery(q);
			while (rs.next()) {
				// int tempTicketID = rs.getInt(2);
				// int tempPrio = rs.getInt(0);
				// String tempSummary = rs.getString(3);
				// int tempSubmitter = 1;
				// AddDefect e = new AddDefect(tempSummary, tempPrio,
				// tempSubmitter);
				// arrayList.add(e);
			
			}
			if (rs != null) {
				rs.close();
			}
			if (st != null) {
				st.close();
			}
			if (con != null) {
				con.close();
			}

		} catch (SQLException ex) {
			System.out.println("Error with table or data.");
		}

	}

	public void insertNewDefect(AddDefect i) {
		makeConnection();

		try {
			LocalDateTime time1 = LocalDateTime.now();
			int day1 = time1.getDayOfMonth();
			int mon1 = time1.getMonthValue();
			int year1 = time1.getYear();
			String dateEnter = (year1 + "-" + mon1 + "-" + day1);
			String q = "insert into defect(summary, description, priority_level, date_entered, submitter, status, comments) "
					+ "values ('" + i.getSummary() + "', '" + i.getDescription() + "', '" + i.getPriority() + "', '"
					+ dateEnter + "', " + i.getSubmitter() + ", '" + i.getStatus() + "', '" + i.getComments() + "');";
			System.out.println(q);
			st = con.createStatement();
			st.executeUpdate(q);

			if (rs != null) {
				rs.close();
			}
			if (st != null) {
				st.close();
			}
			if (con != null) {
				con.close();
			}

		} catch (SQLException ex) {
			System.out.println("Error with table or data while inserting.");
		}

	}
	
	public void returnUser(User i) {
		makeConnection();

		try {
			String lastName = "Fry";
			String firstName = "Shelly";
			String q = "select userid from defect where last_name = '" + lastName + "' and first_name = '" + firstName + "';";
			st = con.createStatement();
			rs = st.executeQuery(q);	
			System.out.println(q);
			while (rs.next()) {
				int tempUserID = rs.getInt(2);
				System.out.println(tempUserID);
			}

			if (rs != null) {
				rs.close();
			}
			if (st != null) {
				st.close();
			}
			if (con != null) {
				con.close();
			}

		} catch (SQLException ex) {
			System.out.println("Error with table or data while inserting.");
		}

	}

	public void makeConnection() {
		String url = "jdbc:mysql://localhost:3306/tracker";
		String user = "root";
		String password = "*****";

		try {

			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(url, user, password);
			System.out.println("Connection made");

		} catch (Exception ex) {
			Logger lgr = Logger.getLogger(AddDAO.class.getName());
			lgr.log(Level.SEVERE, ex.getMessage(), ex);
			System.out.println("Sql Exception");

		}

	}

}
