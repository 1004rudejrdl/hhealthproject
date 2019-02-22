package Controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Model.Exercise;
import Model.Food;

public class ExerciseDAO {
	public static ArrayList<Exercise> dbArrayList = new ArrayList<>();
	public static ArrayList<Food> foodList = new ArrayList<>();
	public static ArrayList<String> sbList = new ArrayList<>();
	
	// 1. �л� ����ϴ� �Լ�
	public static int insertExerciseData(Exercise ec) {
		int count = 0;

		// 1-1. �����ͺ��̽��� �л����̺� �Է��ϴ� ������
		StringBuffer insertUserInfo = new StringBuffer();
		insertUserInfo.append("insert into exercisetbl ");
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
			psmt.setString(1, ec.getKind());
			psmt.setInt(2, ec.getNo());
			psmt.setInt(3, ec.getPkno());
			psmt.setString(4, ec.getName());
			psmt.setInt(5, ec.getFirecal());
			psmt.setInt(6, ec.getTime());
			psmt.setInt(7, ec.getTotalcal());
			psmt.setString(8, ec.getImages());

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

	// � DB��������
	public static ArrayList<Exercise> getExerciseData() {
		String exerData = "select * from exercisetbl order by kind";
		Connection con = null;

		PreparedStatement psmt = null;

		ResultSet rs = null;
		try {
			con = DBUtility.getConnection();
			psmt = con.prepareStatement(exerData);
			rs = psmt.executeQuery();
			if (rs == null) {
				MainController.callAlert("select ���� : select�� ������ �ֽ��ϴ�.");
				return null;
			}
			while (rs.next()) {
				Exercise exer = new Exercise(rs.getString(1), rs.getInt(2), rs.getInt(3), rs.getString(4), rs.getInt(5),
						rs.getInt(6), rs.getInt(7), rs.getString(8));
				dbArrayList.add(exer);
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

	// � DB���� �׸��� what�� �ֵ��� ���� �̱�
	public static int getExerNo(String what) {
		String selectCount = "select * from foodtbl where kind =" + what;
		sbList.clear();
		Connection con = null;
		PreparedStatement psmt = null;

		ResultSet rs;
		try {
			con = DBUtility.getConnection();
			psmt = con.prepareStatement(selectCount);
			rs = psmt.executeQuery();
			if (rs == null) {
				MainController.callAlert("����");
			}
			while (rs.next()) {
				sbList.add(rs.getString(1));
			}
		} catch (SQLException e) {
			MainController.callAlert("���� ���� : �����ͺ��̽� ���Կ� ������ �ֽ��ϴ�.");
		} finally {
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
		return sbList.size();
	}

	// 3. ���̺�信�� ������ ���ڵ带 �����ͺ��̽����� �����ϴ� �Լ�
	public static int deleteExerData(String what) {

		String deleteExer = "delete from exercisetbl where pkno = '" + what + "'";

		Connection con = null;

		PreparedStatement psmt = null;

		int count = 0;
		try {
			con = DBUtility.getConnection();
			psmt = con.prepareStatement(deleteExer);
			count = psmt.executeUpdate();
			if (count == 0) {
				MainController.callAlert("delete ���� : delete�� ������ �ֽ��ϴ�.");
				return count;
			}

		} catch (SQLException e) {
			MainController.callAlert("delete ���� : delete�� ������ �ֽ��ϴ�.");
		} finally {
			// 3-6. �ڿ���ü�� �ݾƾ��Ѵ�.
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
