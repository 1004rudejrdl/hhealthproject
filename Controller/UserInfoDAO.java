package Controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Model.UserInfo;

public class UserInfoDAO {
	public static ArrayList<UserInfo> dbArrayList = new ArrayList<>();
	public static ArrayList<String> userLoginInfoList = new ArrayList<>();

	// 1. �л� ����ϴ� �Լ�
	public static int insertUserInfoData(UserInfo userInfo) {
		int count = 0;

		// 1-1. �����ͺ��̽��� �л����̺� �Է��ϴ� ������
		StringBuffer insertUserInfo = new StringBuffer();
		insertUserInfo.append("insert into userinfotbl ");
		insertUserInfo
				.append("(userID, password, goal, hopecontrolweight, gender, movelevel, currentweight, keepCalorie) ");
		insertUserInfo.append("values ");
		insertUserInfo.append("(?,?,?,?,?,?,?,?) ");

		// 1-2. �����ͺ��̽� Connection�� �����;� �Ѵ�.
		Connection con = null;

		// 1-3. �������� �����ؾ��� Statement�� ������ �Ѵ�.
		PreparedStatement psmt = null;
		try {
			con = DBUtility.getConnection();
			psmt = con.prepareStatement(insertUserInfo.toString());
			// 1-4. �������� ���� �����͸� �����Ѵ�.
			psmt.setString(1, userInfo.getUserID());
			psmt.setString(2, userInfo.getPassword());
			psmt.setInt(3, userInfo.getGoal());
			psmt.setInt(4, userInfo.getHopeControlWeight());
			psmt.setString(5, userInfo.getGender());
			psmt.setInt(6, userInfo.getMoveLevel());
			psmt.setInt(7, userInfo.getCurrentWeight());
			psmt.setInt(8, userInfo.getKeepCalorie());

			// 1-5. ���������͸� ������ �������� �����Ѵ�.
			// executeUpdate(); �������� �����ؼ� ���̺� ������ �Ҷ� ����ϴ� ������
			count = psmt.executeUpdate();
			if (count == 0) {
				MainController.callAlert("���� �������� : ���� �������� ������ �ֽ��ϴ�.");
				return count;
			}
		} catch (SQLException e) {
			MainController.callAlert("���� ���� : �����ͺ��̽� ���Կ� ������ �ֽ��ϴ�.");
		} finally {
			// 1-6. �ڿ���ü�� �ݾƾ��Ѵ�.
			try {
				if (psmt != null) {
					psmt.close();
				}
				if (con != null) {
					con.close();
				}
			} catch (Exception e) {
				MainController.callAlert("�ڿ� �ݱ� ���� : �ڿ� �ݱ⿡ ������ �ֽ��ϴ�.");
			}
		}
		return count;
	}

	// 2. UserInfo���̺��� �������̵�� �н����带 �������� �Լ�

	public static int getKeepCalorie(String userid) {
		String userId = "select keepCalorie from userinfotbl where userid = " + userid;
		Connection con = null;
		int keepCal = 0;
		PreparedStatement psmt = null;

		ResultSet rs = null;
		try {
			con = DBUtility.getConnection();
			psmt = con.prepareStatement(userId);
			psmt.setString(1, userid);
			rs = psmt.executeQuery();
			if (rs == null) {
				MainController.callAlert("select ���� : select�� ������ �ֽ��ϴ�.");
				return 0;
			}
			while (rs.next()) {
				keepCal = rs.getInt(1);
			}
		} catch (SQLException e) {
		} finally {
			// 2-6. �ڿ���ü�� �ݾƾ��Ѵ�.
			try {
				if (psmt != null) {
					psmt.close();
				}
				if (con != null) {
					con.close();
				}
			} catch (Exception e) {
				MainController.callAlert("�ڿ� �ݱ� ���� : �ڿ� �ݱ⿡ ������ �ֽ��ϴ�.");
			}
		}
		return keepCal;
	}

	public static String getUserID(String pass) {
		String userId = "select userid from userinfotbl where password = ? ";
		Connection con = null;
		String id = "";
		PreparedStatement psmt = null;

		ResultSet rs = null;
		try {
			con = DBUtility.getConnection();
			psmt = con.prepareStatement(userId);
			psmt.setString(2, pass);
			rs = psmt.executeQuery();
			if (rs == null) {
				MainController.callAlert("select ���� : select�� ������ �ֽ��ϴ�.");
				return null;
			}
			while (rs.next()) {
				if (rs.getString(2) == pass) {
					id = rs.getString(1);
				}
			}
		} catch (SQLException e) {
		} finally {
			// 2-6. �ڿ���ü�� �ݾƾ��Ѵ�.
			try {
				if (psmt != null) {
					psmt.close();
				}
				if (con != null) {
					con.close();
				}
			} catch (Exception e) {
				MainController.callAlert("�ڿ� �ݱ� ���� : �ڿ� �ݱ⿡ ������ �ֽ��ϴ�.");
			}
		}
		return id;
	}

	public static ArrayList<UserInfo> getUserInfoData() {
		String userId = "select * from userinfotbl ";
		Connection con = null;

		PreparedStatement psmt = null;

		ResultSet rs = null;
		try {
			con = DBUtility.getConnection();
			psmt = con.prepareStatement(userId);
			rs = psmt.executeQuery();
			if (rs == null) {
				MainController.callAlert("select ���� : select�� ������ �ֽ��ϴ�.");
				return null;
			}
			while (rs.next()) {
				UserInfo userInfo = new UserInfo(rs.getString(1), rs.getString(2), rs.getInt(3), rs.getInt(4),
						rs.getString(5), rs.getInt(6), rs.getInt(7), rs.getInt(8));
				dbArrayList.add(userInfo);
			}
		} catch (SQLException e) {
		} finally {
			// 2-6. �ڿ���ü�� �ݾƾ��Ѵ�.
			try {
				if (psmt != null) {
					psmt.close();
				}
				if (con != null) {
					con.close();
				}
			} catch (Exception e) {
				MainController.callAlert("�ڿ� �ݱ� ���� : �ڿ� �ݱ⿡ ������ �ֽ��ϴ�.");
			}
		}
		return dbArrayList;
	}

	// 0. ������ ü�� �Է¿��� ������ ���ڵ带 �����ͺ��̽� ���̺� �����ϴ� �Լ�.
	public static int updateUserCurrentWeightData(UserInfo ui) {		
		int count = 0;
		// 4-1. �����ͺ��̽��� �л����̺� �����ϴ� ������
		StringBuffer updateUserCurrentWeightData = new StringBuffer();
		updateUserCurrentWeightData.append("update userInfotbl set ");
		updateUserCurrentWeightData
				.append("currentweight=? where userid=? ");

		// 4-2. �����ͺ��̽� Connection�� �����;� �Ѵ�.
		Connection con = null;
		// 4-3. �������� �����ؾ��� Statement�� ������ �Ѵ�.
		PreparedStatement psmt = null;
		try {
			con = DBUtility.getConnection();
			psmt = con.prepareStatement(updateUserCurrentWeightData.toString());
			// 4-4. �������� ���� �����͸� �����Ѵ�.
			psmt.setInt(1, ui.getCurrentWeight());
			psmt.setString(2, ui.getUserID());
			// 4-5. ���������͸� ������ �������� �����Ѵ�.
			// executeUpdate(); �������� �����ؼ� ���̺� ������ �Ҷ� ����ϴ� ������
			count = psmt.executeUpdate();
			if (count == 0) {
				MainController.callAlert("update �������� : update �������� ������ �ֽ��ϴ�.");
				return count;
			}
		} catch (SQLException e) {
			MainController.callAlert("update ���� : update �� ������ �ֽ��ϴ�.");
		} finally {
			// 4-6. �ڿ���ü�� �ݾƾ��Ѵ�.
			try {
				if (psmt != null) {
					psmt.close();
				}
				if (con != null) {
					con.close();
				}
			} catch (Exception e) {
				MainController.callAlert("�ڿ� �ݱ� ���� : �ڿ� �ݱ⿡ ������ �ֽ��ϴ�.");
			}
		}
		return count;
	}

	// 0. ��ǥ ü�� ���濡�� ������ ���ڵ带 �����ͺ��̽� ���̺� �����ϴ� �Լ�.
	public static int updateGoalData(UserInfo ui) {		
		int count = 0;
		// 4-1. �����ͺ��̽��� �л����̺� �����ϴ� ������
		StringBuffer updateGoal = new StringBuffer();
		updateGoal.append("update userInfotbl set ");
		updateGoal
				.append("goal=? where userid=? ");

		// 4-2. �����ͺ��̽� Connection�� �����;� �Ѵ�.
		Connection con = null;
		// 4-3. �������� �����ؾ��� Statement�� ������ �Ѵ�.
		PreparedStatement psmt = null;
		try {
			con = DBUtility.getConnection();
			psmt = con.prepareStatement(updateGoal.toString());
			// 4-4. �������� ���� �����͸� �����Ѵ�.
			psmt.setInt(1, ui.getGoal());
			psmt.setString(2, ui.getUserID());
			// 4-5. ���������͸� ������ �������� �����Ѵ�.
			// executeUpdate(); �������� �����ؼ� ���̺� ������ �Ҷ� ����ϴ� ������
			count = psmt.executeUpdate();
			if (count == 0) {
				MainController.callAlert("update �������� : update �������� ������ �ֽ��ϴ�.");
				return count;
			}
		} catch (SQLException e) {
			MainController.callAlert("update ���� : update �� ������ �ֽ��ϴ�.");
		} finally {
			// 4-6. �ڿ���ü�� �ݾƾ��Ѵ�.
			try {
				if (psmt != null) {
					psmt.close();
				}
				if (con != null) {
					con.close();
				}
			} catch (Exception e) {
				MainController.callAlert("�ڿ� �ݱ� ���� : �ڿ� �ݱ⿡ ������ �ֽ��ϴ�.");
			}
		}
		return count;
	}
	
}
