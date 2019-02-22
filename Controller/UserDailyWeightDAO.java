package Controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Model.UserDailyWeight;

public class UserDailyWeightDAO {
	public static ArrayList<UserDailyWeight> dbArrayList = new ArrayList<>();
	public static ArrayList<UserDailyWeight> UserDailyWeightList = new ArrayList<>();
	public static ArrayList<String> sbList = new ArrayList<>();


	// 1. Į�θ� ����ϴ� �Լ�
	public static int insertUserDailyWeightData(UserDailyWeight udw) {
		int count = 0;

		// 1-1. �����ͺ��̽��� �л����̺� �Է��ϴ� ������
		StringBuffer insertUserDailyWeight = new StringBuffer();
		insertUserDailyWeight.append("insert into userdailyweighttbl ");
		insertUserDailyWeight.append("values ");
		insertUserDailyWeight.append("(?,?,?) ");

		// 1-2. �����ͺ��̽� Connection�� �����;� �Ѵ�.
		Connection con = null;

		// 1-3. �������� �����ؾ��� Statement�� ������ �Ѵ�.
		PreparedStatement psmt = null;
		try {
			con = DBUtility.getConnection();
			psmt = con.prepareStatement(insertUserDailyWeight.toString());
			// 1-4. �������� ���� �����͸� �����Ѵ�.
			psmt.setString(1, udw.getUserid());
			psmt.setDate(2, udw.getDay());
			psmt.setInt(3, udw.getTodayWeight());
		
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

	public static int getUserDailyWeightData(String userid, String day) {
		String plusCal = "select todayweight from userdailyweighttbl where userid = '"+userid+"' AND day = '"+day+"'";
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
	
}
