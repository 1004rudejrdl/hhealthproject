package Controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Model.UserCal;

public class UserCalDAO {
	public static ArrayList<UserCal> dbArrayList = new ArrayList<>();
	public static ArrayList<UserCal> plusCalList = new ArrayList<>();
	public static ArrayList<UserCal> minusCalList = new ArrayList<>();
	public static ArrayList<String> sbList = new ArrayList<>();

	// 1. Į�θ� ����ϴ� �Լ�
	public static int insertUserCalData(UserCal uc) {
		int count = 0;

		// 1-1. �����ͺ��̽��� �л����̺� �Է��ϴ� ������
		StringBuffer insertUserInfo = new StringBuffer();
		insertUserInfo.append("insert into usercaltbl ");
		insertUserInfo.append("(userid, day, foodname, plusCal, exername, minusCal) ");
		insertUserInfo.append("values ");
		insertUserInfo.append("(?,?,?,?,?,?) ");

		// 1-2. �����ͺ��̽� Connection�� �����;� �Ѵ�.
		Connection con = null;

		// 1-3. �������� �����ؾ��� Statement�� ������ �Ѵ�.
		PreparedStatement psmt = null;
		try {
			con = DBUtility.getConnection();
			psmt = con.prepareStatement(insertUserInfo.toString());
			// 1-4. �������� ���� �����͸� �����Ѵ�.
			psmt.setString(1, uc.getUserid());
			psmt.setDate(2, uc.getDay());
			psmt.setString(3, uc.getFoodname());
			psmt.setString(4, uc.getPlusCal());
			psmt.setString(5, uc.getExername());
			psmt.setString(6, uc.getMinusCal());			

			// 1-5. ���������͸� ������ �������� �����Ѵ�.
			// executeUpdate(); �������� �����ؼ� ���̺� ������ �Ҷ� ����ϴ� ������
			count = psmt.executeUpdate();
			if (count == 0) {
				MainController.callAlert("���� �������� : ���� �������� ������ �ֽ��ϴ�.");
				return count;
			}
		} catch (SQLException e) {
			e.printStackTrace();
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

	public static String getUserCalData(String day) {
		String plusCal = "select sum(pluscal-minuscal) from usercaltbl where day = '"+day+"'";
		String result = "";
		Connection con = null;
		
		PreparedStatement psmt = null;

		ResultSet rs = null;
		try {
			con = DBUtility.getConnection();
			psmt = con.prepareStatement(plusCal);
			rs = psmt.executeQuery();
			if (rs == null) {
				MainController.callAlert("select ���� : select�� ������ �ֽ��ϴ�.");
				return null;
			}
			while (rs.next()) {
				result = rs.getString(0);
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
		return result;
	}

	public static int getUserPlusCalData(String userid, String day) {
		String plusCal = "select sum(pluscal) from usercaltbl where userid = '"+userid+"' AND day = '"+day+"'";
		int result = 0;
		Connection con = null;
		
		PreparedStatement psmt = null;

		ResultSet rs = null;
		try {
			con = DBUtility.getConnection();
			psmt = con.prepareStatement(plusCal);
			rs = psmt.executeQuery();
			if (rs == null) {
				MainController.callAlert("select ���� : select�� ������ �ֽ��ϴ�.");
				return 0;
			}
			while (rs.next()) {
				result = rs.getInt(1);
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
		return result;
	}
	
	public static int getUserMinusCalData(String userid, String day) {
		String minusCal = "select sum(minuscal) from usercaltbl where userid = '"+userid+"' AND day = '"+day+"'";
		int result = 0;
		Connection con = null;
		
		PreparedStatement psmt = null;

		ResultSet rs = null;
		try {
			con = DBUtility.getConnection();
			psmt = con.prepareStatement(minusCal);
			rs = psmt.executeQuery();
			if (rs == null) {
				MainController.callAlert("select ���� : select�� ������ �ֽ��ϴ�.");
				return 0;
			}
			while (rs.next()) {
				result = rs.getInt(1);
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
		return result;
	}
	
	public static int getUserTotalCalData(String userid, String day) {
		String totalCal = "select sum(pluscal)-sum(minuscal) from usercaltbl where userid = '"+userid+"' AND day = '"+day+"'";
		int result = 0;
		Connection con = null;
		
		PreparedStatement psmt = null;

		ResultSet rs = null;
		try {
			con = DBUtility.getConnection();
			psmt = con.prepareStatement(totalCal);
			rs = psmt.executeQuery();
			if (rs == null) {
				MainController.callAlert("select ���� : select�� ������ �ֽ��ϴ�.");
				return 0;
			}
			while (rs.next()) {
				result = rs.getInt(1);
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
		return result;
	}
	
	public static ArrayList<UserCal> getUserPlusCalRecord(String userid) {
		String userPlusCal = "select day, foodname, pluscal from usercaltbl where userid = '"+userid+"' AND length(foodname) > 0 AND length(pluscal) > 0 order by day ";
		int result = 0;
		Connection con = null;
		
		PreparedStatement psmt = null;

		ResultSet rs = null;
		try {
			con = DBUtility.getConnection();
			psmt = con.prepareStatement(userPlusCal);
			rs = psmt.executeQuery();
			if (rs == null) {
				MainController.callAlert("select ���� : select�� ������ �ֽ��ϴ�.");
				return null;
			}
			while (rs.next()) {
				UserCal userCal = new UserCal(rs.getDate(1), rs.getString(2), rs.getString(3));
				plusCalList.add(userCal);
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
		return plusCalList;
	}	
	
	public static ArrayList<UserCal> getUserMinusCalRecord(String userid) {
		String userMinusCal = "select day, exername, minuscal from usercaltbl where userid = '"+userid+"' AND length(exername) > 0 AND length(minuscal) > 0 order by day";
		int result = 0;
		Connection con = null;
		
		PreparedStatement psmt = null;
		
		ResultSet rs = null;
		try {
			con = DBUtility.getConnection();
			psmt = con.prepareStatement(userMinusCal);
			rs = psmt.executeQuery();
			if (rs == null) {
				MainController.callAlert("select ���� : select�� ������ �ֽ��ϴ�.");
				return null;
			}
			while (rs.next()) {
				UserCal userCal = new UserCal(rs.getDate(1), rs.getString(2), rs.getString(3));
				minusCalList.add(userCal);
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
		return minusCalList;
	}	
	
	
	
}
