package user;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/*�ܺζ��̺귯���� �߰�*/
public class UserDAO { /* �����ͺ��̽� ���� ��ü�� ����, �����ͺ��̽��� ȸ�������� �ҷ��� �ְ����Ҷ� ��� */

	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	/* ������ ��� ��ü ���� */

	/* ������ ���� */
	public UserDAO() {
		  try {
		   String dbURL = "jdbc:mysql://localhost:3306/BBS?serverTimezone=UTC";
		   String dbID = "root";
		   String dbPassword = "cyh1110@";
		   Class.forName("com.mysql.cj.jdbc.Driver");
		   conn = DriverManager.getConnection(dbURL, dbID, dbPassword);

		  } catch (Exception e) {
		   e.printStackTrace();
		  }
		 }

	public int login(String userID, String userPassword) {
		String SQL = "SELECT userPassword FROM USER WHERE userID=?";
		try {
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, userID);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				if (rs.getString(1).equals(userPassword))
					return 1; // �α��� ����
				else
					return 0; // ��й�ȣ ����ġ
			}
			return -1; // ���̵� ����

		} catch (Exception e) {
			e.printStackTrace();
		}
		return -2; // ������ ���̽� ����

	}
	// �ϳ��� ������ ���� �α��� �õ��� ���ִ� �Լ��� ������->�α��� ����� ������!
	
	public int join(User user) {
		String SQL = "INSERT INTO USER VALUES (?, ?, ?, ?, ?)";
		try {
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, user.getUserID());
			pstmt.setString(2, user.getUserPassword());
			pstmt.setString(3, user.getUserConfirmPassword());
			pstmt.setString(4, user.getUserName());
			pstmt.setString(5, user.getUserEmail());
			return pstmt.executeUpdate();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		return -1;//�����ͺ��̽� ����
	}
}
