package jdbc.pkg;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class StudentManagement {
	public static void main(String args[]) {
		try {
			Scanner sc = new Scanner(System.in);

			System.out.println("Enter what you want to do: INSERT / UPDATE / DELETE / SHOW");
			String s = sc.next().toLowerCase(); 

			Class.forName("com.mysql.cj.jdbc.Driver");

			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/information", "root","Hem@nt123");

			switch (s) {

			case "insert":
				String sql = "INSERT INTO StudentInfo(id, name,  course,city, total_marks, phoneno) VALUES(?,?,?,?,?,?)";

				PreparedStatement ps = con.prepareStatement(sql);

				System.out.println("Enter Id, Name, Course, City, Total Marks, Phone No:");
				int id = sc.nextInt();
				String name = sc.next();
				String course = sc.next();
				String city = sc.next();
				int total_marks = sc.nextInt();
				String phoneno = sc.next();

				ps.setInt(1, id);
				ps.setString(2, name);
				ps.setString(3, course);
				ps.setString(4, city);
				ps.setInt(5, total_marks);
				ps.setString(6, phoneno);

				int status = ps.executeUpdate();
				System.out.println(status > 0 ? "Data inserted successfully!" : "Insert failed.");
				break;

			case "update":
				System.out.println("Enter ID to update:");
				int uid = sc.nextInt();

				System.out.println("Enter Id, Name, Course, City, Total Marks, Phone No:");
				String uname = sc.next();
				String ucourse = sc.next();
				String ucity = sc.next();
				int umarks = sc.nextInt();
				String uphone = sc.next();

				String sql1 = "UPDATE StudentInfo SET name=?,  course=?,city=?,, total_marks=?, phoneno=? WHERE id=?";
				
				PreparedStatement ps1 = con.prepareStatement(sql1);
				ps1.setInt(1, uid);
				ps1.setString(2, uname);
				ps1.setString(3, ucity);
				ps1.setString(4, ucourse);
				ps1.setInt(5, umarks);
				ps1.setString(6, uphone);
				

				int rows = ps1.executeUpdate();
				System.out.println(rows > 0 ? "Record updated successfully!" : "Record not found!");
				break;

			case "delete":
				System.out.print("Enter ID to Delete: ");
				int did = sc.nextInt();

				String sql2 = "DELETE FROM StudentInfo WHERE id = ?";
				PreparedStatement ps2 = con.prepareStatement(sql2);
				ps2.setInt(1, did);

				int rows2 = ps2.executeUpdate();
				System.out.println(rows2 > 0 ? "Record Deleted Successfully!" : "No record found!");
				break;

			case "show":
				String sql3 = "SELECT * FROM StudentInfo";
				PreparedStatement ps3 = con.prepareStatement(sql3);
				ResultSet rs = ps3.executeQuery();

				System.out.println("ID | Name |  Course |  City | Total Marks | Phone");
				System.out.println("--------------------------------------------------");

				while (rs.next()) {
					System.out.println(rs.getInt("id") + " | " + rs.getString("name") + " | " + rs.getString("course") + " | "
							+ rs.getString("city") + " | " + rs.getInt("total_marks") + " | "
							+ rs.getString("phoneno"));
				}
				break;

			default:
				System.out.println("Invalid choice! Type INSERT / UPDATE / DELETE / SHOW");
				break;
			}

			con.close(); 

		} catch (Exception e) {
			System.out.println(e);
		}
	}
}
