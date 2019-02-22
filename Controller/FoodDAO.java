package Controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Model.Food;

public class FoodDAO {
	public static ArrayList<Food> dbArrayList = new ArrayList<>();
	public static ArrayList<Food> foodList = new ArrayList<>();
	public static ArrayList<String> sbList = new ArrayList<>();
	
	// 1. �л� ����ϴ� �Լ�
	public static int insertFoodData(Food food) {
		int count = 0;

		// 1-1. �����ͺ��̽��� �л����̺� �Է��ϴ� ������
		StringBuffer insertUserInfo = new StringBuffer();
		insertUserInfo.append("insert into foodtbl ");
		insertUserInfo
				.append("(kind, kategorie, no, pkno, name, caloriePerGram, volume, totalCal) ");
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
			psmt.setString(1, food.getKind());
			psmt.setString(2, food.getKategorie());
			psmt.setInt(3, food.getNo());
			psmt.setInt(4, food.getPkno());
			psmt.setString(5, food.getName());
			psmt.setInt(6, food.getCaloriePerGram());
			psmt.setInt(7, food.getVolume());
			psmt.setInt(8, food.getTotalCal());

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

	public static ArrayList<Food> getFoodData() {
		String foodData = "select * from foodtbl order by kind";
		Connection con = null;

		PreparedStatement psmt = null;

		ResultSet rs = null;
		try {
			con = DBUtility.getConnection();
			psmt = con.prepareStatement(foodData);
			rs = psmt.executeQuery();
			if (rs == null) {
				MainController.callAlert("select ���� : select�� ������ �ֽ��ϴ�.");
				return null;
			}
			while (rs.next()) {
				Food food = new Food(rs.getString(1), rs.getString(2), rs.getInt(3), rs.getInt(4), rs.getString(5),
						rs.getInt(6), rs.getInt(7), rs.getInt(8));
				dbArrayList.add(food);
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

	public static int getOneOneNo(String what) {
		// 2-1. �����ͺ��̽� �л����̺� �ִ� ���ڵ带 ��� �������� ������
		String selectCount = "select * from foodtbl where kategorie ="+what;
		sbList.clear();
		// 2-2. �����ͺ��̽� Connection�� �����;� �Ѵ�.
		Connection con = null;

		// 2-3. �������� �����ؾ��� Statement�� ������ �Ѵ�.
		PreparedStatement psmt = null;

		ResultSet rs;
		// 2-4. �������� �����ϰ��� �����;��� ���ڵ带 ����ִ� ���ڱ� ��ü
		try {
			con = DBUtility.getConnection();
			psmt = con.prepareStatement(selectCount);
			rs=psmt.executeQuery();
			// 2-5. ���������͸� ������ �������� �����Ѵ�.(������ ġ�°�)
			// executeQuery(); �������� �����ؼ� ����� �����ö� ����ϴ� ������
			if (rs == null) {
				MainController.callAlert("����");
			}
			while(rs.next()) {
				sbList.add(rs.getString(1));
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
		return sbList.size();
	}

	// 3. ���̺�信�� ������ ���ڵ带 �����ͺ��̽����� �����ϴ� �Լ�
	public static int deleteFoodData(String what) {

		// 3-1. �����ͺ��̽� �л����̺� �ִ� ���ڵ带 �����ϴ� ������
		String deleteFood = "delete from foodtbl where pkno = '"+what+"'";

		// 3-2. �����ͺ��̽� Connection�� �����;� �Ѵ�.
		Connection con = null;

		// 3-3. �������� �����ؾ��� Statement�� ������ �Ѵ�.
		PreparedStatement psmt = null;

		// 3-4. �������� �����ϰ��� �����;��� ���ڵ带 ����ִ� ���ڱ� ��ü
		int count = 0;
		try {
			con = DBUtility.getConnection();
			psmt = con.prepareStatement(deleteFood);
			// 3-5. ���������͸� ������ �������� �����Ѵ�.(������ ġ�°�)
			// executeQuery(); �������� �����ؼ� ����� �����ö� ����ϴ� ������
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
