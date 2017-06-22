package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Customer;
import model.Vehicle;

public class DatabaseManager {

	private static ArrayList<Customer> custList;
	private static ArrayList<Vehicle> vehicList;

	public static Connection getConnection() {
		Connection conn = null;
		try {
			Class.forName("org.sqlite.JDBC");
			conn = DriverManager
					.getConnection("jdbc:sqlite:/home/peter/DB_P/rentDB.db3");
			System.out.println("Sucess");
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conn;
	}

	public static List<Customer> listCustomer(String cid) {
		custList = new ArrayList<Customer>();
		PreparedStatement ps;
		String sql = "";
		try {
			if (cid.equals(""))
				sql = "select * from Customers";
			else
				sql = "select * from Customers where CID='" + cid + "'";

			ps = getConnection().prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				custList.add(new Customer(rs.getString("CID"), rs
						.getString("name"), rs.getString("phone"), rs
						.getString("address")));
			}
			rs.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return custList;
	}

	public static void addCustomer(Customer customer) {

		Statement stmt = null;
		String sql = "INSERT INTO Customers VALUES('" + customer.getCID()
				+ "', '" + customer.getName() + "', '" + customer.getPhone()
				+ "', '" + customer.getAddress() + "')";
		try {
			stmt = getConnection().createStatement();
			stmt.executeUpdate(sql);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void removeCustomer(String cid) {
		Statement stmt = null;
		String sql = "DELETE FROM Customers WHERE CID='" + cid + "'";

		try {
			stmt = getConnection().createStatement();
			stmt.executeUpdate(sql);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static List<Vehicle> listVehicle(String param, String type) {
		vehicList = new ArrayList<Vehicle>();
		Statement stmt = null;
		String sql = null;

		if (param.equals("")) {
			sql = "select * from Vehicles";
		} else {
			if (type.equals("plateNo")) {
				sql = "select * from Vehicles where plateNo='" + param + "'";
			} else if (type.equals("type")) {
				sql = "select * from Vehicles where isCar='" + param + "'";
			} else if (type.equals("rented")) {
				sql = "select * from Vehicles where isRented='" + param + "'";
			} else {
				// beadando.InputTextareaView.addMessage("KO");
			}
		}

		try {
			stmt = getConnection().createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {

				vehicList.add(new Vehicle(Integer.valueOf(rs.getInt("VID")), rs
						.getString("manufacturer"), rs.getBoolean("isCar"), rs
						.getString("chassisNum"), rs.getString("pruchased"),
						Integer.valueOf(rs.getInt("mileage")), Integer
								.valueOf(rs.getInt("fee")), rs
								.getBoolean("isRented"), rs
								.getString("plateNo")));
			}
			rs.close();
		} catch (SQLException se) {
			System.err.println("ERROR - 1");
			se.printStackTrace();

		}
		return vehicList;

	}

	public static void addVehicle(Vehicle vehicle) {
		String VID = String.valueOf(vehicle.getVID());
		String Manufacturer = vehicle.getManufacturer();
		Boolean Iscar = vehicle.isIscar();
		String Chassisnum = vehicle.getChassisNum();
		String Pruchased = vehicle.getPruchased();
		String Milage = String.valueOf(vehicle.getMilage());
		String Fee = String.valueOf(vehicle.getFee());
		Boolean Isrented = vehicle.isIsrented();
		String Plateno = vehicle.getPlateNo();

		String iscarS;
		String isrentedS;

		if (Iscar) {
			iscarS = "1";
		} else
			iscarS = "0";

		if (Isrented) {
			isrentedS = "1";
		} else
			isrentedS = "0";

		Statement stmt = null;
		String sql = "INSERT INTO Vehicles VALUES('" + VID + "', '"
				+ Manufacturer + "', '" + iscarS + "', '" + Chassisnum + "', '"
				+ Pruchased + "', '" + Milage + "', '" + Fee + "', '"
				+ isrentedS + "', '" + Plateno + "')";
		try {
			stmt = getConnection().createStatement();
			stmt.executeUpdate(sql);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void removeVehicle(String VID) {
		String ID = String.valueOf(VID);

		Statement stmt = null;
		String sql = "DELETE FROM Vehicles WHERE VID='" + ID + "'";
		try {
			stmt = getConnection().createStatement();
			stmt.executeUpdate(sql);
		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void addRent(String CID, String VID, int milage, int fee,
			String Date) {
		String ID = String.valueOf(VID);

		Statement stmt = null;
		String sql = "INSERT INTO Rents VALUES('" + CID + "', '" + ID + "', '" + milage + "','" + Date + "','"+ fee + "')";
		String usql = "UPDATE Vehicles SET isRented = '1' WHERE VID = '" + ID
				+ "'";

		try {
			stmt = getConnection().createStatement();
			stmt.executeUpdate(sql);
		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void removeRent(String VID, int mileage) {
		String ID = String.valueOf(VID);
		Statement stmt = null;
		String sql = "DELETE FROM Rents WHERE VID='" + ID + "'";
		String usql = "UPDATE Vehicles SET isRented = '0' WHERE VID = '" + ID
				+ "'";
		String u2sql = "UPDATE Vehicles SET mileage = '" + mileage
				+ "' WHERE VID = '" + ID + "'";
		try {
			stmt = getConnection().createStatement();
			stmt.executeUpdate(sql);
		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static String checkDate(String VID) {
		Statement stmt = null;
		String date = "-1";
		String ID = String.valueOf(VID);

		try {
			stmt = getConnection().createStatement();
			String sql = "SELECT dateRENTED FROM Rents WHERE VID='" + ID + "'";
			System.err.println("DEBUG - 0");
			ResultSet rs = stmt.executeQuery(sql);
			System.err.println("DEBUG - 1");
			// Extract data from result set
			while (rs.next()) {
				date = rs.getString("dateRENTED");
			}
			rs.close();
		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return date;
	}

	public static int checkFee(String VID) {

		Statement stmt = null;
		int fee = -1;

		try {
			stmt = getConnection().createStatement();
			String sql = "SELECT fee FROM Vehicles WHERE VID='" + VID + "'";
			System.err.println("DEBUG - 0");
			ResultSet rs = stmt.executeQuery(sql);
			System.err.println("DEBUG - 1");
			// Extract data from result set
			while (rs.next()) {
				fee = rs.getInt("fee");
			}
			rs.close();
		} catch (SQLException se) {
			se.printStackTrace();
		}
		return fee;
	}

	public static int checkMilage(String VID) {
		Statement stmt = null;
		int milage = -1;
		try {
			stmt = getConnection().createStatement();
			String sql = "SELECT mileage FROM Vehicles WHERE VID='" + VID + "'";
			System.err.println("DEBUG - 0");
			ResultSet rs = stmt.executeQuery(sql);
			System.err.println("DEBUG - 1");
			// Extract data from result set
			while (rs.next()) {
				milage = rs.getInt("mileage");
			}
			rs.close();
			return milage;
		} catch (SQLException se) {
			se.printStackTrace();
		}
		return milage;
	}

	public static String db_aut(String user) throws ClassNotFoundException {

		Statement stmt = null;
		String pass = "-1";

		try {
			stmt = getConnection().createStatement();
			String sql = "SELECT password FROM Admins WHERE username='" + user
					+ "'";
			System.err.println("DEBUG - 0");
			ResultSet rs = stmt.executeQuery(sql);
			System.err.println("DEBUG - 1");
			// Extract data from result set
			while (rs.next()) {
				pass = rs.getString("password");
			}
			rs.close();
			return pass;
		} catch (SQLException se) {
			System.err.println("ERROR - 1");
		}// end try
		return pass;
	}

}