package Controller;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.text.DecimalFormat;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.ResourceBundle;

import Model.Exercise;
import Model.Food;
import Model.UserCal;
import Model.UserInfo;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Series;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class MainController implements Initializable {
	public Stage mainStage;
	private ObservableList<Food> foodTableList = FXCollections.observableArrayList();
	private ObservableList<Exercise> exerciseTableList = FXCollections.observableArrayList();
	private ObservableList<String> foodCmbKindList = FXCollections.observableArrayList();
	private ObservableList<String> exerCmbKindList = FXCollections.observableArrayList();
	private ObservableList<String> foodCmbKategorieList1 = FXCollections.observableArrayList();
	private ObservableList<UserCal> userMinusCalRecordList = FXCollections.observableArrayList();
	private ObservableList<UserCal> userCalRecordList = FXCollections.observableArrayList();

	public ArrayList<UserInfo> loginUserMan = new ArrayList<>();

	public void setLoginUserMan(ArrayList<UserInfo> loginUserMan) {
		this.loginUserMan = loginUserMan;
	}

	public ArrayList<UserInfo> getLoginUserMan() {
		return loginUserMan;
	}

	private ArrayList<Food> dbArrayList;
	private ArrayList<UserInfo> userInfodbArrayList;
	private ArrayList<Exercise> exercisedbArrayList;
	private ArrayList<UserCal> userCalArrayList;
	private ArrayList<UserCal> userMinusCalArrayList;

	private String firstPkno = null;
	private String secondPkno = null;
	// =======================������ ���=======================
	private Food selectedFood;
	private int selectedFoodIndex;
	private Exercise selectedExercise;
	private int selectedExerciseIndex;
	@FXML
	private Label t1LabelLimit;
	@FXML
	private Label t1LabelEat;
	@FXML
	private TextField t1txtBrk1;
	@FXML
	private Button t1BtnBrk1;
	@FXML
	private TextField t1txtBrk2;
	@FXML
	private TextField t1txtBrk3;
	@FXML
	private Button t1BtnBrk3;
	@FXML
	private Button t1BtnBrk2;
	@FXML
	private Button t1BtnLun2;
	@FXML
	private Button t1BtnLun3;
	@FXML
	private TextField t1txtLun3;
	@FXML
	private TextField t1txtLun2;
	@FXML
	private Button t1BtnLun1;
	@FXML
	private TextField t1txtLun1;
	@FXML
	private TextField t1txtDin1;
	@FXML
	private Button t1BtnDin1;
	@FXML
	private TextField t1txtDin2;
	@FXML
	private TextField t1txtDin3;
	@FXML
	private Button t1BtnDin3;
	@FXML
	private Button t1BtnDin2;
	@FXML
	private Button t1BtnEtc2;
	@FXML
	private Button t1BtnEtc3;
	@FXML
	private TextField t1txtEtc3;
	@FXML
	private TextField t1txtEtc2;
	@FXML
	private Button t1BtnEtc1;
	@FXML
	private TextField t1txtEtc1;
	@FXML
	private Button t1BtnBrkMinus1;
	@FXML
	private Button t1BtnBrkMinus2;
	@FXML
	private Button t1BtnBrkMinus3;
	@FXML
	private Button t1BtnLunMinus3;
	@FXML
	private Button t1BtnLunMinus2;
	@FXML
	private Button t1BtnLunMinus1;
	@FXML
	private Button t1BtnDinMinus1;
	@FXML
	private Button t1BtnDinMinus2;
	@FXML
	private Button t1BtnDinMinus3;
	@FXML
	private Button t1BtnEtcMinus3;
	@FXML
	private Button t1BtnEtcMinus2;
	@FXML
	private Button t1BtnEtcMinus1;
	@FXML
	private DatePicker t1DatePicker;
	@FXML
	private Label t1LabelFire;
	@FXML
	private Label t1LabelCan;
	@FXML
	private Button t1BtnTodaySave;
	@FXML
	private Button t1BtnTodayCal;
	@FXML
	private Button t1BtnFoodRecord;
	@FXML
	private Button t1BtnExerRecord;

	// =======================Į�θ�����Ʈ=======================
	@FXML
	private Tab t3Tab;
	@FXML
	private BarChart t3BarChart;
	@FXML
	private Label t3LabelLimit;
	@FXML
	private Label t3LabelComment;
	@FXML
	private Label t3LabelTotalCal;
	@FXML
	private Label t3LabelAvgCal;

	private XYChart.Series<String, Integer> series1 = new XYChart.Series<>();
	// =========================ü�ߺ�ȭ=========================
	@FXML
	private Tab t4Tab;
	@FXML
	private Label t4LabelWantWeight;
	@FXML
	private LineChart t4LineChart;
	@FXML
	private TextField t4txtTodayWeight;
	@FXML
	private Button t4BtnWeightSave;
	private Series series2 = new XYChart.Series<>();

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// �α��ν� �ȳ���Ʈ
		startMainInfoMent();

		// =========================������ ���==========================

		// ù ȭ�� ������ ����
		itemSettingFirst();

		// ���ݱ��� �Ļ��� ���� ��ư �������� �׼�
		t1BtnFoodRecord.setOnAction(e -> {
			handlerT1BtnFoodRecordAction();
		});

		// ���ݱ��� ���� ���� ��ư �������� �׼�
		t1BtnExerRecord.setOnAction(e -> {
			handlerT1BtnExerRecordAction();
		});

		// ������ ��� ���� ��ư�� �������� �׼�
		t1BtnTodaySave.setOnAction(e -> {
			handlerT1BtnTodaySaveAction();
		});

		// ������ Į�θ� ��� ��ư�� �������� �׼�
		t1BtnTodayCal.setOnAction(e -> {
			handlerT1BtnTodayCalAction();
		});

		// ù��° �Ļ��� +��ư�� ���������� �׼�
		t1BtnBrk1.setOnAction(e -> {
			handlerT1BtnBrk1Action(t1txtBrk1, t1txtBrk2, t1BtnBrk1, t1BtnBrkMinus1, t1BtnBrk2, t1BtnBrkMinus2);
		});

		// ù��° �Ļ��� -��ư�� ���������� �׼�
		t1BtnBrkMinus1.setOnAction(e -> {
			handlerT1BtnBrkMinusAction(t1txtBrk1, t1BtnBrk1, t1BtnBrkMinus1);
		});

		// �ι�° �Ļ��� +��ư�� ���������� �׼�
		t1BtnBrk2.setOnAction(e -> {
			handlerT1BtnBrk1Action(t1txtBrk2, t1txtBrk3, t1BtnBrk2, t1BtnBrkMinus2, t1BtnBrk3, t1BtnBrkMinus3);
		});

		// �ι�° �Ļ��� -��ư�� ���������� �׼�
		t1BtnBrkMinus2.setOnAction(e -> {
			handlerT1BtnBrkMinusAction(t1txtBrk2, t1BtnBrk2, t1BtnBrkMinus2);
		});

		// ����° �Ļ��� +��ư�� ���������� �׼�
		t1BtnBrk3.setOnAction(e -> {
			handlerT1BtnBrk1Action(t1txtBrk3, t1txtLun1, t1BtnBrk3, t1BtnBrkMinus3, t1BtnLun1, t1BtnLunMinus1);
		});

		// ����° �Ļ��� -��ư�� ���������� �׼�
		t1BtnBrkMinus3.setOnAction(e -> {
			handlerT1BtnBrkMinusAction(t1txtBrk3, t1BtnBrk3, t1BtnBrkMinus3);
		});

		// �׹�° �Ļ��� +��ư�� ���������� �׼�
		t1BtnLun1.setOnAction(e -> {
			handlerT1BtnBrk1Action(t1txtLun1, t1txtLun2, t1BtnLun1, t1BtnLunMinus1, t1BtnLun2, t1BtnLunMinus2);
		});

		// �׹�° �Ļ��� -��ư�� ���������� �׼�
		t1BtnLunMinus1.setOnAction(e -> {
			handlerT1BtnBrkMinusAction(t1txtLun1, t1BtnLun1, t1BtnLunMinus1);
		});

		// �ټ���° �Ļ��� +��ư�� ���������� �׼�
		t1BtnLun2.setOnAction(e -> {
			handlerT1BtnBrk1Action(t1txtLun2, t1txtLun3, t1BtnLun2, t1BtnLunMinus2, t1BtnLun3, t1BtnLunMinus3);
		});

		// �ټ���° �Ļ��� -��ư�� ���������� �׼�
		t1BtnLunMinus2.setOnAction(e -> {
			handlerT1BtnBrkMinusAction(t1txtLun2, t1BtnLun2, t1BtnLunMinus2);
		});

		// ������° �Ļ��� +��ư�� ���������� �׼�
		t1BtnLun3.setOnAction(e -> {
			handlerT1BtnLun3Action();
		});

		// ������° �Ļ��� -��ư�� ���������� �׼�
		t1BtnLunMinus3.setOnAction(e -> {
			handlerT1BtnBrkMinusAction(t1txtLun3, t1BtnLun3, t1BtnLunMinus3);
		});

		// ù��° ���� +��ư�� ���������� �׼�
		t1BtnDin1.setOnAction(e -> {
			handlerT1BtnDin1Action(t1txtDin1, t1txtDin2, t1BtnDin1, t1BtnDinMinus1, t1BtnDin2, t1BtnDinMinus2);
		});

		// ù��° ���� -��ư�� ���������� �׼�
		t1BtnDinMinus1.setOnAction(e -> {
			handlerT1BtnBrkMinusAction(t1txtDin1, t1BtnDin1, t1BtnDinMinus1);
		});

		// �ι�° ���� +��ư�� ���������� �׼�
		t1BtnDin2.setOnAction(e -> {
			handlerT1BtnDin1Action(t1txtDin2, t1txtDin3, t1BtnDin2, t1BtnDinMinus2, t1BtnDin3, t1BtnDinMinus3);
		});

		// �ι�° ���� -��ư�� ���������� �׼�
		t1BtnDinMinus2.setOnAction(e -> {
			handlerT1BtnBrkMinusAction(t1txtDin2, t1BtnDin2, t1BtnDinMinus2);
		});

		// ����° ���� +��ư�� ���������� �׼�
		t1BtnDin3.setOnAction(e -> {
			handlerT1BtnDin1Action(t1txtDin3, t1txtEtc1, t1BtnDin3, t1BtnDinMinus3, t1BtnEtc1, t1BtnEtcMinus1);
		});

		// ����° ���� -��ư�� ���������� �׼�
		t1BtnDinMinus3.setOnAction(e -> {
			handlerT1BtnBrkMinusAction(t1txtDin3, t1BtnDin3, t1BtnDinMinus3);
		});

		// �׹�° ���� +��ư�� ���������� �׼�
		t1BtnEtc1.setOnAction(e -> {
			handlerT1BtnDin1Action(t1txtEtc1, t1txtEtc2, t1BtnEtc1, t1BtnEtcMinus1, t1BtnEtc2, t1BtnEtcMinus2);
		});

		// �׹�° ���� -��ư�� ���������� �׼�
		t1BtnEtcMinus1.setOnAction(e -> {
			handlerT1BtnBrkMinusAction(t1txtEtc1, t1BtnEtc1, t1BtnEtcMinus1);
		});

		// �ټ���° ���� +��ư�� ���������� �׼�
		t1BtnEtc2.setOnAction(e -> {
			handlerT1BtnDin1Action(t1txtEtc2, t1txtEtc3, t1BtnEtc2, t1BtnEtcMinus2, t1BtnEtc3, t1BtnEtcMinus3);
		});

		// �ټ���° ���� -��ư�� ���������� �׼�
		t1BtnEtcMinus2.setOnAction(e -> {
			handlerT1BtnBrkMinusAction(t1txtEtc2, t1BtnEtc2, t1BtnEtcMinus2);
		});

		// ������° ���� +��ư�� ���������� �׼�
		t1BtnEtc3.setOnAction(e -> {
			handlerT1BtnEtc3Action();
		});

		// ������° ���� -��ư�� ���������� �׼�
		t1BtnEtcMinus3.setOnAction(e -> {
			handlerT1BtnBrkMinusAction(t1txtEtc3, t1BtnEtc3, t1BtnEtcMinus3);
		});

		// =========================Į�θ�����Ʈ==========================

		// Į�θ�����Ʈ ���� Ŭ���� �׼�(����Ʈ)
		t3Tab.selectedProperty().addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				if (newValue) {
					handlerT3TabClickAction();
				}
			}
		});

		// =========================ü�ߺ�ȭ==========================

		// ü�� ���� ��ư �׼�
		t4BtnWeightSave.setOnAction(e -> {
			handlerT4BtnWeightSaveAction();
		});

		// ü�ߺ�ȭ ���� Ŭ���� �׼�(������Ʈ)
		t4Tab.selectedProperty().addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				if (newValue) {
					handlerT4TabClickAction();
				}
			}
		});

	}

//=============================���ȭ=============================

	// �α��ν� �ȳ���Ʈ
	private void startMainInfoMent() {
		callAlert("�� ���α׷��� �� 3���� ������ �̷�����ֽ��ϴ�: ");
		callAlert(
				"ù��° �� - '������ ���' :������ �Ļ�� ��� ���� ����� �Ͽ� Į�θ��� ����մϴ�.\n������ '������ Į�θ� ���'�� ���� �ǽð����� Į�θ��� ��� �����ϸ�\n�ϴ��� '������ ��� ����'�� ���� �ݿ��˴ϴ�.\n****���ǻ���****\n'������ ��� ����'�� ������ ����ä �����ϸ�\n����� ������� ������ �����ϼ���!!!");
		callAlert(
				"�ι�° �� - 'Į�θ� ����Ʈ' :������ �������� �ֱ� �������� Į�θ��� ���� ����Ʈ �Դϴ�.\n�׻� Ȯ���Ͽ� ���� �� ��� ���� Į�θ� ����, �Ҹ� ���Ͽ�\n�����Ͻð� ��ǥ�� ���� �����ϼ���!!");
		callAlert("����° �� - 'ü�ߺ�ȭ' :�Ϸ翡 �ѹ� ü���� ����ϼ���.\n������ �������� �ֱ� �������� ü�� ��ȭ�� �����ݴϴ�.\n��ǥ ü���� ���� ȭ����!!");
	}

	// ù ȭ�� ������ ����
	private void itemSettingFirst() {
		// =========================������ ���=======================
		t1BtnBrk1.setDisable(false);
		t1txtBrk1.setDisable(true);
		t1txtBrk1.clear();
		t1BtnBrkMinus1.setDisable(true);
		t1txtBrk2.setVisible(false);
		t1txtBrk2.clear();
		t1txtBrk3.setVisible(false);
		t1txtBrk3.clear();
		t1BtnBrk2.setVisible(false);
		t1BtnBrk3.setVisible(false);
		t1BtnBrkMinus2.setVisible(false);
		t1BtnBrkMinus3.setVisible(false);
		t1DatePicker.setDisable(true);
		
		t1txtLun1.setVisible(false);
		t1txtLun1.clear();
		t1txtLun2.setVisible(false);
		t1txtLun2.clear();
		t1txtLun3.setVisible(false);
		t1txtLun3.clear();
		t1BtnLun1.setVisible(false);
		t1BtnLun2.setVisible(false);
		t1BtnLun3.setVisible(false);
		t1BtnLunMinus1.setVisible(false);
		t1BtnLunMinus2.setVisible(false);
		t1BtnLunMinus3.setVisible(false);

		t1BtnDin1.setDisable(false);
		t1txtDin1.setDisable(true);
		t1txtDin1.clear();
		t1BtnDinMinus1.setDisable(true);
		t1txtDin2.setVisible(false);
		t1txtDin2.clear();
		t1txtDin3.setVisible(false);
		t1txtDin3.clear();
		t1BtnDin2.setVisible(false);
		t1BtnDin3.setVisible(false);
		t1BtnDinMinus2.setVisible(false);
		t1BtnDinMinus3.setVisible(false);

		t1txtEtc1.setVisible(false);
		t1txtEtc1.clear();
		t1txtEtc2.setVisible(false);
		t1txtEtc2.clear();
		t1txtEtc3.setVisible(false);
		t1txtEtc3.clear();
		t1BtnEtc1.setVisible(false);
		t1BtnEtc2.setVisible(false);
		t1BtnEtc3.setVisible(false);
		t1BtnEtcMinus1.setVisible(false);
		t1BtnEtcMinus2.setVisible(false);
		t1BtnEtcMinus3.setVisible(false);
		LocalDateTime ldt1 = LocalDateTime.now();
		t1DatePicker.setValue(ldt1.toLocalDate());

		// =========================Į�θ�����Ʈ=======================

		// =========================ü�ߺ�ȭ=======================
		inputDecimalFormat(t4txtTodayWeight);
	}

	// ���ݱ��� �Ļ��� ���� ��ư �������� �׼�
	private void handlerT1BtnFoodRecordAction() {
		if (!userCalRecordList.isEmpty() && !userCalArrayList.isEmpty()) {
			userCalRecordList.clear();
			userCalArrayList.clear();
		}
		Stage recordStage;
		Parent root;
		try {
			recordStage = new Stage();
			FXMLLoader loader = new FXMLLoader(getClass().getResource("../View/food_record.fxml"));
			root = loader.load();
			TableView<UserCal> frTableView = (TableView) root.lookup("#frTableView");

			TableColumn tcDay = frTableView.getColumns().get(0);
			tcDay.setCellValueFactory(new PropertyValueFactory<>("day"));
			tcDay.setStyle("-fx-alignment: CENTER;");

			TableColumn tcFoodname = frTableView.getColumns().get(1);
			tcFoodname.setCellValueFactory(new PropertyValueFactory<>("foodname"));
			tcFoodname.setStyle("-fx-alignment: CENTER;");

			TableColumn tcPluscal = frTableView.getColumns().get(2);
			tcPluscal.setCellValueFactory(new PropertyValueFactory<>("plusCal"));
			tcPluscal.setStyle("-fx-alignment: CENTER;");

			userCalArrayList = UserCalDAO.getUserPlusCalRecord(loginUserMan.get(0).getUserID());
			for (UserCal uc : userCalArrayList) {
				userCalRecordList.add(uc);
			}
			frTableView.setItems(userCalRecordList);

			Scene scene = new Scene(root);
			recordStage.setScene(scene);
			recordStage.show();

		} catch (Exception e) {

		}

	}

	// ���ݱ��� ���� ���� ��ư �������� �׼�
	private void handlerT1BtnExerRecordAction() {
		if (!userMinusCalRecordList.isEmpty() && !userMinusCalArrayList.isEmpty()) {
			userMinusCalRecordList.clear();
			userMinusCalArrayList.clear();
		}
		Stage recordStage;
		Parent root;
		try {
			recordStage = new Stage();
			FXMLLoader loader = new FXMLLoader(getClass().getResource("../View/food_record.fxml"));
			root = loader.load();
			TableView<UserCal> frTableView = (TableView) root.lookup("#frTableView");

			TableColumn tcDay = frTableView.getColumns().get(0);
			tcDay.setCellValueFactory(new PropertyValueFactory<>("day"));
			tcDay.setStyle("-fx-alignment: CENTER;");

			TableColumn tcExername = frTableView.getColumns().get(1);
			tcExername.setCellValueFactory(new PropertyValueFactory<>("exername"));
			tcExername.setStyle("-fx-alignment: CENTER;");

			TableColumn tcMinuscal = frTableView.getColumns().get(2);
			tcMinuscal.setCellValueFactory(new PropertyValueFactory<>("minusCal"));
			tcMinuscal.setStyle("-fx-alignment: CENTER;");

			userMinusCalArrayList = UserCalDAO.getUserMinusCalRecord(loginUserMan.get(0).getUserID());
			for (UserCal uc : userMinusCalArrayList) {
				userMinusCalRecordList.add(uc);
			}
			frTableView.setItems(userMinusCalRecordList);

			Scene scene = new Scene(root);
			recordStage.setScene(scene);
			recordStage.show();
		} catch (Exception e) {

		}

	}

	// ������ ��� ���� ��ư �������� �׼�
	private void handlerT1BtnTodaySaveAction() {
		Date day = Date.valueOf(t1DatePicker.getValue());
		if (t1BtnBrk1.isDisable()) {
			String[] split = t1txtBrk1.getText().split(":");
			String calValue = split[1];
			UserCal usercal = new UserCal(loginUserMan.get(0).getUserID(), day, t1txtBrk1.getText(), calValue, null,
					null);
			UserCalDAO.insertUserCalData(usercal);
			callAlert("������ �Ļ��� ����Ϸ�!:");
		} else {
			callAlert("������ �Ļ����� �����ϴ�: Ȯ�κ�Ź�����^^");
		}
		if (t1BtnBrk2.isDisable()) {
			String[] split = t1txtBrk2.getText().split(":");
			String calValue = split[1];
			UserCal usercal = new UserCal(loginUserMan.get(0).getUserID(), day, t1txtBrk2.getText(), calValue, null,
					null);
			UserCalDAO.insertUserCalData(usercal);
		}
		if (t1BtnBrk3.isDisable()) {
			String[] split = t1txtBrk3.getText().split(":");
			String calValue = split[1];
			UserCal usercal = new UserCal(loginUserMan.get(0).getUserID(), day, t1txtBrk3.getText(), calValue, null,
					null);
			UserCalDAO.insertUserCalData(usercal);
		}
		if (t1BtnLun1.isDisable()) {
			String[] split = t1txtLun1.getText().split(":");
			String calValue = split[1];
			UserCal usercal = new UserCal(loginUserMan.get(0).getUserID(), day, t1txtLun1.getText(), calValue, null,
					null);
			UserCalDAO.insertUserCalData(usercal);
		}
		if (t1BtnLun2.isDisable()) {
			String[] split = t1txtLun2.getText().split(":");
			String calValue = split[1];
			UserCal usercal = new UserCal(loginUserMan.get(0).getUserID(), day, t1txtLun2.getText(), calValue, null,
					null);
			UserCalDAO.insertUserCalData(usercal);
		}
		if (t1BtnLun3.isDisable()) {
			String[] split = t1txtLun3.getText().split(":");
			String calValue = split[1];
			UserCal usercal = new UserCal(loginUserMan.get(0).getUserID(), day, t1txtLun3.getText(), calValue, null,
					null);
			UserCalDAO.insertUserCalData(usercal);
		}

		if (t1BtnDin1.isDisable()) {
			String[] split = t1txtDin1.getText().split(":");
			String calValue = split[1];
			UserCal usercal = new UserCal(loginUserMan.get(0).getUserID(), day, null, null, t1txtDin1.getText(),
					calValue);
			UserCalDAO.insertUserCalData(usercal);
			callAlert("������ ���� ����Ϸ�!:");
		} else {
			callAlert("������ ������ �����ϴ�: Ȯ�κ�Ź�����^^");
		}
		if (t1BtnDin2.isDisable()) {
			String[] split = t1txtDin2.getText().split(":");
			String calValue = split[1];
			UserCal usercal = new UserCal(loginUserMan.get(0).getUserID(), day, null, null, t1txtDin2.getText(),
					calValue);
			UserCalDAO.insertUserCalData(usercal);
		}
		if (t1BtnDin3.isDisable()) {
			String[] split = t1txtDin3.getText().split(":");
			String calValue = split[1];
			UserCal usercal = new UserCal(loginUserMan.get(0).getUserID(), day, null, null, t1txtDin3.getText(),
					calValue);
			UserCalDAO.insertUserCalData(usercal);
		}
		if (t1BtnEtc1.isDisable()) {
			String[] split = t1txtEtc1.getText().split(":");
			String calValue = split[1];
			UserCal usercal = new UserCal(loginUserMan.get(0).getUserID(), day, null, null, t1txtEtc1.getText(),
					calValue);
			UserCalDAO.insertUserCalData(usercal);
		}
		if (t1BtnEtc2.isDisable()) {
			String[] split = t1txtEtc2.getText().split(":");
			String calValue = split[1];
			UserCal usercal = new UserCal(loginUserMan.get(0).getUserID(), day, null, null, t1txtEtc2.getText(),
					calValue);
			UserCalDAO.insertUserCalData(usercal);
		}
		if (t1BtnEtc3.isDisable()) {
			String[] split = t1txtEtc3.getText().split(":");
			String calValue = split[1];
			UserCal usercal = new UserCal(loginUserMan.get(0).getUserID(), day, null, null, t1txtEtc3.getText(),
					calValue);
			UserCalDAO.insertUserCalData(usercal);
		}
		itemSettingFirst();
	}

	// ������ Į�θ� ��� ��ư �������� �׼�
	private void handlerT1BtnTodayCalAction() {
		t1LabelLimit.setText(String.valueOf(loginUserMan.get(0).getKeepCalorie()));
		int sum = 0;
		int sum2 = 0;
		sum = UserCalDAO.getUserPlusCalData(loginUserMan.get(0).getUserID(), String.valueOf(t1DatePicker.getValue()));
		sum2 = UserCalDAO.getUserMinusCalData(loginUserMan.get(0).getUserID(), String.valueOf(t1DatePicker.getValue()));
		t1LabelFire.setText(String.valueOf(sum2));
		t1LabelEat.setText(String.valueOf(sum));
		t1LabelCan.setText(String.valueOf(loginUserMan.get(0).getKeepCalorie() - sum + sum2));
	}

	// �Ļ��� +��ư�� ���������� �׼�
	private void handlerT1BtnBrk1Action(TextField t1, TextField t2, Button b1, Button b2, Button b3, Button b4) {
		if (!foodTableList.isEmpty() && !dbArrayList.isEmpty()) {
			foodTableList.clear();
			dbArrayList.clear();
		}
		Stage foodStage;
		FXMLLoader loader = new FXMLLoader(getClass().getResource("../View/eat_food.fxml"));
		Parent root;
		try {
			foodStage = new Stage();
			root = loader.load();

			// ===================ID���================================
			TableView<Food> foodTableView = (TableView) root.lookup("#foodTableView");

			TextField foodTxtSearch = (TextField) root.lookup("#foodTxtSearch");
			Button foodBtnSearch = (Button) root.lookup("#foodBtnSearch");
			Button foodBtnCalTotalCal = (Button) root.lookup("#foodBtnCalTotalCal");
			Button foodBtnSave = (Button) root.lookup("#foodBtnSave");
			Button foodBtnDel = (Button) root.lookup("#foodBtnDel");
			Button foodBtnExit = (Button) root.lookup("#foodBtnExit");
			Button foodBtnRefresh = (Button) root.lookup("#foodBtnRefresh");

			ComboBox<String> foodCmbKind = (ComboBox) root.lookup("#foodCmbKind");
			ComboBox<String> foodCmbKategorie = (ComboBox) root.lookup("#foodCmbKategorie");

			TextField foodTxtNo = (TextField) root.lookup("#foodTxtNo");
			TextField foodTxtPkNo = (TextField) root.lookup("#foodTxtPkNo");
			TextField foodTxtName = (TextField) root.lookup("#foodTxtName");
			TextField foodTxtCal = (TextField) root.lookup("#foodTxtCal");
			inputDecimalFormat(foodTxtCal);
			TextField foodTxtVolume = (TextField) root.lookup("#foodTxtVolume");
			inputDecimalFormat(foodTxtVolume);
			TextField foodTxtTotalCal = (TextField) root.lookup("#foodTxtTotalCal");

			Button foodBtnSelect = (Button) root.lookup("#foodBtnSelect");
			Button foodBtnInput = (Button) root.lookup("#foodBtnInput");
			Button foodBtnCorInput = (Button) root.lookup("#foodBtnCorInput");
			foodBtnCorInput.setVisible(false);

			// ================���̺�� �÷� ����====================
			TableColumn tcKind = foodTableView.getColumns().get(0);
			tcKind.setCellValueFactory(new PropertyValueFactory<>("kind"));
			tcKind.setStyle("-fx-alignment: CENTER;");

			TableColumn tcKategorie = foodTableView.getColumns().get(1);
			tcKategorie.setCellValueFactory(new PropertyValueFactory<>("kategorie"));
			tcKind.setStyle("-fx-alignment: CENTER;");

			TableColumn tcNo = foodTableView.getColumns().get(2);
			tcNo.setCellValueFactory(new PropertyValueFactory<>("no"));
			tcNo.setStyle("-fx-alignment: CENTER;");

			TableColumn tcPkno = foodTableView.getColumns().get(3);
			tcPkno.setCellValueFactory(new PropertyValueFactory<>("pkno"));
			tcPkno.setStyle("-fx-alignment: CENTER;");

			TableColumn tcName = foodTableView.getColumns().get(4);
			tcName.setCellValueFactory(new PropertyValueFactory<>("name"));
			tcName.setStyle("-fx-alignment: CENTER;");

			TableColumn tcCaloriePerGram = foodTableView.getColumns().get(5);
			tcCaloriePerGram.setCellValueFactory(new PropertyValueFactory<>("caloriePerGram"));
			tcCaloriePerGram.setStyle("-fx-alignment: CENTER;");

			TableColumn tcVolume = foodTableView.getColumns().get(6);
			tcVolume.setCellValueFactory(new PropertyValueFactory<>("volume"));
			tcVolume.setStyle("-fx-alignment: CENTER;");

			TableColumn tcTotalCal = foodTableView.getColumns().get(7);
			tcTotalCal.setCellValueFactory(new PropertyValueFactory<>("totalCal"));
			tcTotalCal.setStyle("-fx-alignment: CENTER;");

			dbArrayList = FoodDAO.getFoodData();
			for (Food food : dbArrayList) {
				foodTableList.add(food);
			}
			foodTableView.setItems(foodTableList);

			// ================ó��ȭ�� �ʵ�� ����======================
			foodBtnCalTotalCal.setDisable(true);
			foodTxtNo.setDisable(true);
			foodTxtPkNo.setDisable(true);
			foodTxtName.setDisable(true);
			foodTxtCal.setDisable(true);
			foodTxtTotalCal.setDisable(true);
			foodCmbKind.setDisable(true);
			foodCmbKategorie.setDisable(true);
			foodTxtVolume.setDisable(true);
			foodBtnSave.setDisable(true);

			// ================�޺��ڽ� ����===========================
			if(!foodCmbKindList.isEmpty()) {
				foodCmbKindList.clear();
			}
			foodCmbKindList.add("1)����");
			foodCmbKindList.add("2)����");
			foodCmbKindList.add("3)�Ļ���(��, �ø��� ��)");
			foodCmbKindList.add("4)�Ļ��");
			foodCmbKindList.add("5)��Ÿ");
			foodCmbKind.setItems(foodCmbKindList);

			if(!foodCmbKategorieList1.isEmpty()) {
				foodCmbKategorieList1.clear();
			}
			foodCmbKategorieList1.add("1)����Ĩ");
			foodCmbKategorieList1.add("2)�׷�����");
			foodCmbKategorieList1.add("3)������ġ");
			foodCmbKategorieList1.add("4)��Ÿ");
			foodCmbKategorie.setItems(foodCmbKategorieList1);
			foodCmbKind.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
				@Override
				public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
					if (newValue != null) {
						switch (newValue.toString()) {
						case "1)����":
							firstPkno = "1";
							foodCmbKategorie.setItems(foodCmbKategorieList1);
							break;
						case "2)����":
							ObservableList<String> foodCmbKategorieList2 = FXCollections.observableArrayList();
							firstPkno = "2";
							foodCmbKategorieList2.add("1)�߰�����");
							foodCmbKategorieList2.add("2)�������");
							foodCmbKategorieList2.add("3)����");
							foodCmbKategorieList2.add("4)��Ÿ");
							foodCmbKategorie.setItems(foodCmbKategorieList2);
							break;
						case "3)�Ļ���(��, �ø��� ��)":
							ObservableList<String> foodCmbKategorieList3 = FXCollections.observableArrayList();
							firstPkno = "3";
							foodCmbKategorieList3.add("1)�ø���");
							foodCmbKategorieList3.add("2)�佺Ʈ");
							foodCmbKategorieList3.add("3)��");
							foodCmbKategorieList3.add("4)��Ÿ");
							foodCmbKategorie.setItems(foodCmbKategorieList3);
							break;
						case "4)�Ļ��":
							ObservableList<String> foodCmbKategorieList4 = FXCollections.observableArrayList();
							firstPkno = "4";
							foodCmbKategorieList4.add("1)����");
							foodCmbKategorieList4.add("2)�Ľ�Ÿ");
							foodCmbKategorieList4.add("3)��");
							foodCmbKategorieList4.add("4)��Ÿ");
							foodCmbKategorie.setItems(foodCmbKategorieList4);
							break;
						case "5)��Ÿ":
							ObservableList<String> foodCmbKategorieList5 = FXCollections.observableArrayList();
							firstPkno = "5";
							foodCmbKategorieList5.add("1)��Ÿ");
							foodCmbKategorie.setItems(foodCmbKategorieList5);
							break;
						}
					}
				}
			});

			// ================���̺�並 �������� ����====================
			foodTableView.setOnMouseClicked(e -> {
				selectedFoodIndex = foodTableView.getSelectionModel().getSelectedIndex();
				selectedFood = foodTableView.getSelectionModel().getSelectedItem();
				foodTxtNo.setText(String.valueOf(selectedFood.getNo()));
				foodTxtNo.setDisable(true);
				foodTxtPkNo.setText(String.valueOf(selectedFood.getPkno()));
				foodTxtPkNo.setDisable(true);
				foodTxtName.setText(selectedFood.getName());
				foodTxtName.setDisable(true);
				foodTxtCal.setText(String.valueOf(selectedFood.getCaloriePerGram()));
				foodTxtCal.setDisable(true);
				foodTxtTotalCal.setDisable(true);
				foodTxtTotalCal.clear();
				foodCmbKind.setValue(selectedFood.getKind());
				foodCmbKind.setDisable(true);
				foodCmbKategorie.setValue(selectedFood.getKategorie());
				foodCmbKategorie.setDisable(true);
				foodBtnCalTotalCal.setDisable(false);
				foodTxtVolume.setDisable(false);
				foodBtnDel.setDisable(false);
			});

			// ================���ΰ�ħ ��ư Ŭ����====================
			foodBtnRefresh.setOnAction(e -> {
				foodTableView.refresh();
				foodTableView.setItems(foodTableList);
				foodBtnCalTotalCal.setDisable(true);
				foodTxtNo.setDisable(true);
				foodTxtNo.clear();
				foodTxtPkNo.setDisable(true);
				foodTxtPkNo.clear();
				foodTxtName.setDisable(true);
				foodTxtName.clear();
				foodTxtCal.setDisable(true);
				foodTxtCal.clear();
				foodTxtTotalCal.setDisable(true);
				foodTxtTotalCal.clear();
				foodCmbKind.setDisable(true);
				foodCmbKategorie.setDisable(true);
				foodTxtVolume.setDisable(true);
				foodTxtVolume.clear();
				foodBtnSave.setDisable(true);
				foodBtnDel.setDisable(true);
			});

			// ================Į�θ� ��� �ȳ� ��ư Ŭ����====================
			foodBtnSelect.setOnAction(e -> {
				callAlert("������ ������ �� Į�θ��� ����մϴ�:������ ������ �� ���뷮�� g������ �Է��Ͻð�" + "\n�ϴ��� '�� Į�θ� ����ϱ�'��ư�� �����ø�"
						+ "\n�� Į�θ��� �ڵ� ���Ǿ� �Էµ˴ϴ�.\n" + "�Է��� ������ �����ø� ���� �ϴ��� " + "\n'����' ��ư�� ������ �Է��� �Ϸ�˴ϴ�.");
				foodTableView.refresh();
				foodTableView.setItems(foodTableList);
				foodBtnCalTotalCal.setDisable(true);
				foodTxtNo.setDisable(true);
				foodTxtNo.clear();
				foodTxtPkNo.setDisable(true);
				foodTxtPkNo.clear();
				foodTxtName.setDisable(true);
				foodTxtName.clear();
				foodTxtCal.setDisable(true);
				foodTxtCal.clear();
				foodTxtTotalCal.setDisable(true);
				foodTxtTotalCal.clear();
				foodCmbKind.setDisable(true);
				foodCmbKategorie.setDisable(true);
				foodTxtVolume.setDisable(true);
				foodTxtVolume.clear();
				foodBtnSave.setDisable(true);
			});

			// ================���� ���� �Է� ��ư Ŭ����====================
			foodBtnInput.setOnAction(e -> {
				callAlert("������ ������ ���� �Է��մϴ�:������ ������ ������ Ȯ���Ͻ� ��" + "\n�ش� ������ �Է����ּ���" + "\n������ ��� �Է��Ͻð� �ϴܿ�\n"
						+ "'���� ����' ��ư�� �����ø� �Է��� �Ϸ�˴ϴ�. " + "\n������ ������ �ٽ� �˻� �� ������ ��\n�� Į�θ� ����� �������ּ���.");
				foodTableView.refresh();
				foodTableView.setItems(foodTableList);
				foodCmbKind.setValue(foodCmbKindList.get(0));
				foodCmbKind.setDisable(false);
				foodBtnCorInput.setVisible(true);
				foodCmbKategorie.setDisable(false);
				foodTxtNo.clear();
				foodTxtNo.setDisable(true);
				foodTxtPkNo.clear();
				foodTxtPkNo.setDisable(true);
				foodTxtName.setDisable(false);
				foodTxtCal.clear();
				foodTxtCal.setDisable(false);
				foodTxtVolume.clear();
				foodTxtVolume.setDisable(true);
				foodBtnCalTotalCal.setDisable(true);
				foodTxtTotalCal.setDisable(true);
				foodTxtName.clear();
				foodBtnDel.setDisable(true);

				foodCmbKategorie.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
					@Override
					public void changed(ObservableValue<? extends String> observable, String oldValue,
							String newValue) {
						foodTxtPkNo.clear();
						foodTxtNo.clear();
						if (newValue != null) {
							switch (newValue.toString()) {
							case "1)����Ĩ":
							case "1)�߰�����":
							case "1)�ø���":
							case "1)����":
							case "1)��Ÿ":
								secondPkno = "1";
								break;
							case "2)�׷�����":
							case "2)�������":
							case "2)�佺Ʈ":
							case "2)�Ľ�Ÿ":
								secondPkno = "2";
								break;
							case "3)������ġ":
							case "3)����":
							case "3)��":
							case "3)��":
								secondPkno = "3";
								break;
							case "4)��Ÿ":
								secondPkno = "4";
								break;
							}
							String pkno = null;
							int count = FoodDAO.getOneOneNo("'" + newValue.toString() + "'") + 1;
							foodTxtNo.setText(String.valueOf(count));
							if (foodTxtNo.getText().length() == 1) {
								pkno = firstPkno + secondPkno + "0" + "0" + foodTxtNo.getText();
								count = 0;
							} else {
								pkno = firstPkno + secondPkno + "0" + foodTxtNo.getText();
								count = 0;
							}
							foodTxtPkNo.setText(pkno);
						}
					}
				});
			});

			// ================���� �˻� ���====================
			foodBtnSearch.setOnAction(e -> {
				ObservableList<Food> foodTableListSearch = FXCollections.observableArrayList();
				foodTableListSearch.clear();
				for (Food food : foodTableList) {
					if (food.getKind().contains(foodTxtSearch.getText())
							|| food.getKategorie().contains(foodTxtSearch.getText())
							|| food.getName().contains(foodTxtSearch.getText())) {
						foodTableListSearch.add(food);
					}
				}
				foodTableView.setItems(foodTableListSearch);
				foodTxtSearch.clear();
			});

			// ================�� Į�θ� ��� ��ư Ŭ����====================
			foodBtnCalTotalCal.setOnAction(e -> {
				if (foodTxtVolume.getText() != null) {
					double calValue = Integer.parseInt(foodTxtCal.getText()) / 100.0;
					double dValue = calValue * Double.parseDouble(foodTxtVolume.getText());
					int value = (int) Math.round(dValue);
					foodTxtVolume.setDisable(true);
					foodTxtTotalCal.setText(String.valueOf(value));
					foodBtnDel.setDisable(true);
					foodBtnSave.setDisable(false);
					callAlert("�� Į�θ� ��� �Ϸ�^^:�Է��Ͻ� ������ �����ø� '����'�� �����ּ���.\n������ �߸� �Է��ϼ����� '���ΰ�ħ'�� �����ּ���.");
				} else {
					callAlert("���뷮�� �Է� ���ϼ̳׿�^^:�����Ͻŷ��� �뷫�����ζ� �Է����ּ���!");
				}
			});

			// ================���� ���� ��ư Ŭ����====================
			foodBtnCorInput.setOnAction(e -> {
				if (foodCmbKind.getSelectionModel().getSelectedItem() != null
						&& foodCmbKategorie.getSelectionModel().getSelectedItem() != null && foodTxtNo.getText() != null
						&& foodTxtPkNo.getText() != null && foodTxtName.getText() != null
						&& foodTxtCal.getText() != null) {
					Food food = new Food(foodCmbKind.getSelectionModel().getSelectedItem(),
							foodCmbKategorie.getSelectionModel().getSelectedItem(),
							Integer.parseInt(foodTxtNo.getText()), Integer.parseInt(foodTxtPkNo.getText()),
							foodTxtName.getText(), Integer.parseInt(foodTxtCal.getText()), 0, 0);
					FoodDAO.insertFoodData(food);
					foodTableList.add(food);
					callAlert("�������� ����^^:�ش������� �����ϼż� �� Į�θ��� ����غ�����!");
					foodBtnCorInput.setVisible(false);
				} else {
					callAlert("�������� ����!!:�Է°��� Ȯ���غ�����");
				}
			});

			// ================���� ��ư Ŭ����====================
			foodBtnSave.setOnAction(e -> {
				if (foodTxtName.getText() != null && foodTxtTotalCal.getText() != null) {
					callAlert("Į�θ� ������ �Ϸ�Ǿ����ϴ�^^ :�ϴܿ� '������ ��� ����'�� �ϼž� '������ Į�θ� ���'�� �����մϴ�~");
					t1.setText(foodTxtName.getText() + ":" + foodTxtTotalCal.getText() + ":kcal");
					t2.setDisable(true);
					t2.setVisible(true);
					b1.setDisable(true);
					b2.setDisable(false);
					b3.setDisable(false);
					b3.setVisible(true);
					b4.setDisable(false);
					b4.setVisible(true);
					foodStage.close();
				} else {
					callAlert("�׸��� �������ϴ�: ���� �׸��� ������ �ٽ� üũ���ּ���^^");
				}
			});

			// ================���� ��ư Ŭ����====================
			foodBtnDel.setOnAction(e -> {
				selectedFoodIndex = foodTableView.getSelectionModel().getSelectedIndex();
				selectedFood = foodTableView.getSelectionModel().getSelectedItem();
				int value = FoodDAO.deleteFoodData(String.valueOf(selectedFood.getPkno()));
				if (value != 0) {
					foodTableList.remove(selectedFood);
					dbArrayList.remove(selectedFood);
					callAlert("�����Ǿ����ϴ�^^:");
					foodTableView.refresh();
					foodTableView.setItems(foodTableList);
					foodBtnCalTotalCal.setDisable(true);
					foodTxtNo.setDisable(true);
					foodTxtNo.clear();
					foodTxtPkNo.setDisable(true);
					foodTxtPkNo.clear();
					foodTxtName.setDisable(true);
					foodTxtName.clear();
					foodTxtCal.setDisable(true);
					foodTxtCal.clear();
					foodTxtTotalCal.setDisable(true);
					foodTxtTotalCal.clear();
					foodCmbKind.setDisable(true);
					foodCmbKategorie.setDisable(true);
					foodTxtVolume.setDisable(true);
					foodTxtVolume.clear();
					foodBtnSave.setDisable(true);
					foodBtnDel.setDisable(true);
				} else {
					callAlert("��������!!: ????????");
				}
			});

			// ================�ݱ� ��ư Ŭ����====================
			foodBtnExit.setOnAction(e -> foodStage.close());

			Scene scene = new Scene(root);
			foodStage.setScene(scene);
			foodStage.show();

			callAlert("������ ������ �� Į�θ��� ����մϴ�:������ ������ �� ���뷮�� g������ �Է��Ͻð�" + "\n�ϴ��� '�� Į�θ� ����ϱ�'��ư�� �����ø�"
					+ "\n�� Į�θ��� �ڵ� ���Ǿ� �Էµ˴ϴ�.\n" + "�Է��� ������ �����ø� ���� �ϴ��� " + "\n'����' ��ư�� ������ �Է��� �Ϸ�˴ϴ�.");

		} catch (IOException e) {
		}
	}

	// �Ļ��� -��ư�� ���������� �׼�
	private void handlerT1BtnBrkMinusAction(TextField tf, Button plus, Button minus) {
		tf.clear();
		plus.setDisable(false);
		minus.setDisable(true);
		callAlert("�ش����� �����Ǿ����ϴ�:������ Ȯ�����ּ���^^");
	}

	// ������ �Ļ��� +��ư�� ���������� �׼�
	private void handlerT1BtnLun3Action() {
		if (!foodTableList.isEmpty() && !dbArrayList.isEmpty()) {
			foodTableList.clear();
			dbArrayList.clear();
		}
		Stage foodStage;
		FXMLLoader loader = new FXMLLoader(getClass().getResource("../View/eat_food.fxml"));
		Parent root;
		try {
			foodStage = new Stage();
			root = loader.load();

			// ===================ID���================================
			TableView<Food> foodTableView = (TableView) root.lookup("#foodTableView");

			TextField foodTxtSearch = (TextField) root.lookup("#foodTxtSearch");
			Button foodBtnSearch = (Button) root.lookup("#foodBtnSearch");
			Button foodBtnCalTotalCal = (Button) root.lookup("#foodBtnCalTotalCal");
			Button foodBtnSave = (Button) root.lookup("#foodBtnSave");
			Button foodBtnDel = (Button) root.lookup("#foodBtnDel");
			Button foodBtnExit = (Button) root.lookup("#foodBtnExit");
			Button foodBtnRefresh = (Button) root.lookup("#foodBtnRefresh");

			ComboBox<String> foodCmbKind = (ComboBox) root.lookup("#foodCmbKind");
			ComboBox<String> foodCmbKategorie = (ComboBox) root.lookup("#foodCmbKategorie");

			TextField foodTxtNo = (TextField) root.lookup("#foodTxtNo");
			TextField foodTxtPkNo = (TextField) root.lookup("#foodTxtPkNo");
			TextField foodTxtName = (TextField) root.lookup("#foodTxtName");
			TextField foodTxtCal = (TextField) root.lookup("#foodTxtCal");
			inputDecimalFormat(foodTxtCal);
			TextField foodTxtVolume = (TextField) root.lookup("#foodTxtVolume");
			inputDecimalFormat(foodTxtVolume);
			TextField foodTxtTotalCal = (TextField) root.lookup("#foodTxtTotalCal");

			Button foodBtnSelect = (Button) root.lookup("#foodBtnSelect");
			Button foodBtnInput = (Button) root.lookup("#foodBtnInput");
			Button foodBtnCorInput = (Button) root.lookup("#foodBtnCorInput");
			foodBtnCorInput.setVisible(false);

			// ================���̺�� �÷� ����====================
			TableColumn tcKind = foodTableView.getColumns().get(0);
			tcKind.setCellValueFactory(new PropertyValueFactory<>("kind"));
			tcKind.setStyle("-fx-alignment: CENTER;");

			TableColumn tcKategorie = foodTableView.getColumns().get(1);
			tcKategorie.setCellValueFactory(new PropertyValueFactory<>("kategorie"));
			tcKind.setStyle("-fx-alignment: CENTER;");

			TableColumn tcNo = foodTableView.getColumns().get(2);
			tcNo.setCellValueFactory(new PropertyValueFactory<>("no"));
			tcNo.setStyle("-fx-alignment: CENTER;");

			TableColumn tcPkno = foodTableView.getColumns().get(3);
			tcPkno.setCellValueFactory(new PropertyValueFactory<>("pkno"));
			tcPkno.setStyle("-fx-alignment: CENTER;");

			TableColumn tcName = foodTableView.getColumns().get(4);
			tcName.setCellValueFactory(new PropertyValueFactory<>("name"));
			tcName.setStyle("-fx-alignment: CENTER;");

			TableColumn tcCaloriePerGram = foodTableView.getColumns().get(5);
			tcCaloriePerGram.setCellValueFactory(new PropertyValueFactory<>("caloriePerGram"));
			tcCaloriePerGram.setStyle("-fx-alignment: CENTER;");

			TableColumn tcVolume = foodTableView.getColumns().get(6);
			tcVolume.setCellValueFactory(new PropertyValueFactory<>("volume"));
			tcVolume.setStyle("-fx-alignment: CENTER;");

			TableColumn tcTotalCal = foodTableView.getColumns().get(7);
			tcTotalCal.setCellValueFactory(new PropertyValueFactory<>("totalCal"));
			tcTotalCal.setStyle("-fx-alignment: CENTER;");

			dbArrayList = FoodDAO.getFoodData();
			for (Food food : dbArrayList) {
				foodTableList.add(food);
			}
			foodTableView.setItems(foodTableList);

			// ================ó��ȭ�� �ʵ�� ����======================
			foodBtnCalTotalCal.setDisable(true);
			foodTxtNo.setDisable(true);
			foodTxtPkNo.setDisable(true);
			foodTxtName.setDisable(true);
			foodTxtCal.setDisable(true);
			foodTxtTotalCal.setDisable(true);
			foodCmbKind.setDisable(true);
			foodCmbKategorie.setDisable(true);
			foodTxtVolume.setDisable(true);
			foodBtnSave.setDisable(true);

			// ================�޺��ڽ� ����===========================
			if(!foodCmbKindList.isEmpty()) {
				foodCmbKindList.clear();
			}
			foodCmbKindList.add("1)����");
			foodCmbKindList.add("2)����");
			foodCmbKindList.add("3)�Ļ���(��, �ø��� ��)");
			foodCmbKindList.add("4)�Ļ��");
			foodCmbKindList.add("5)��Ÿ");
			foodCmbKind.setItems(foodCmbKindList);

			if(!foodCmbKategorieList1.isEmpty()) {
				foodCmbKategorieList1.clear();
			}
			foodCmbKategorieList1.add("1)����Ĩ");
			foodCmbKategorieList1.add("2)�׷�����");
			foodCmbKategorieList1.add("3)������ġ");
			foodCmbKategorieList1.add("4)��Ÿ");
			foodCmbKategorie.setItems(foodCmbKategorieList1);
			foodCmbKind.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
				@Override
				public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
					if (newValue != null) {
						switch (newValue.toString()) {
						case "1)����":
							firstPkno = "1";
							foodCmbKategorie.setItems(foodCmbKategorieList1);
							break;
						case "2)����":
							ObservableList<String> foodCmbKategorieList2 = FXCollections.observableArrayList();
							firstPkno = "2";
							foodCmbKategorieList2.add("1)�߰�����");
							foodCmbKategorieList2.add("2)�������");
							foodCmbKategorieList2.add("3)����");
							foodCmbKategorieList2.add("4)��Ÿ");
							foodCmbKategorie.setItems(foodCmbKategorieList2);
							break;
						case "3)�Ļ���(��, �ø��� ��)":
							ObservableList<String> foodCmbKategorieList3 = FXCollections.observableArrayList();
							firstPkno = "3";
							foodCmbKategorieList3.add("1)�ø���");
							foodCmbKategorieList3.add("2)�佺Ʈ");
							foodCmbKategorieList3.add("3)��");
							foodCmbKategorieList3.add("4)��Ÿ");
							foodCmbKategorie.setItems(foodCmbKategorieList3);
							break;
						case "4)�Ļ��":
							ObservableList<String> foodCmbKategorieList4 = FXCollections.observableArrayList();
							firstPkno = "4";
							foodCmbKategorieList4.add("1)����");
							foodCmbKategorieList4.add("2)�Ľ�Ÿ");
							foodCmbKategorieList4.add("3)��");
							foodCmbKategorieList4.add("4)��Ÿ");
							foodCmbKategorie.setItems(foodCmbKategorieList4);
							break;
						case "5)��Ÿ":
							ObservableList<String> foodCmbKategorieList5 = FXCollections.observableArrayList();
							firstPkno = "5";
							foodCmbKategorieList5.add("1)��Ÿ");
							foodCmbKategorie.setItems(foodCmbKategorieList5);
							break;
						}
					}
				}
			});

			// ================���̺�並 �������� ����====================
			foodTableView.setOnMouseClicked(e -> {
				selectedFoodIndex = foodTableView.getSelectionModel().getSelectedIndex();
				selectedFood = foodTableView.getSelectionModel().getSelectedItem();
				foodTxtNo.setText(String.valueOf(selectedFood.getNo()));
				foodTxtNo.setDisable(true);
				foodTxtPkNo.setText(String.valueOf(selectedFood.getPkno()));
				foodTxtPkNo.setDisable(true);
				foodTxtName.setText(selectedFood.getName());
				foodTxtName.setDisable(true);
				foodTxtCal.setText(String.valueOf(selectedFood.getCaloriePerGram()));
				foodTxtCal.setDisable(true);
				foodTxtTotalCal.setDisable(true);
				foodTxtTotalCal.clear();
				foodCmbKind.setValue(selectedFood.getKind());
				foodCmbKind.setDisable(true);
				foodCmbKategorie.setValue(selectedFood.getKategorie());
				foodCmbKategorie.setDisable(true);
				foodBtnCalTotalCal.setDisable(false);
				foodTxtVolume.setDisable(false);
				foodBtnDel.setDisable(false);
			});

			// ================���ΰ�ħ ��ư Ŭ����====================
			foodBtnRefresh.setOnAction(e -> {
				foodTableView.refresh();
				foodTableView.setItems(foodTableList);
				foodBtnCalTotalCal.setDisable(true);
				foodTxtNo.setDisable(true);
				foodTxtNo.clear();
				foodTxtPkNo.setDisable(true);
				foodTxtPkNo.clear();
				foodTxtName.setDisable(true);
				foodTxtName.clear();
				foodTxtCal.setDisable(true);
				foodTxtCal.clear();
				foodTxtTotalCal.setDisable(true);
				foodTxtTotalCal.clear();
				foodCmbKind.setDisable(true);
				foodCmbKategorie.setDisable(true);
				foodTxtVolume.setDisable(true);
				foodTxtVolume.clear();
				foodBtnSave.setDisable(true);
				foodBtnDel.setDisable(true);
			});

			// ================Į�θ� ��� �ȳ� ��ư Ŭ����====================
			foodBtnSelect.setOnAction(e -> {
				callAlert("������ ������ �� Į�θ��� ����մϴ�:������ ������ �� ���뷮�� g������ �Է��Ͻð�" + "\n�ϴ��� '�� Į�θ� ����ϱ�'��ư�� �����ø�"
						+ "\n�� Į�θ��� �ڵ� ���Ǿ� �Էµ˴ϴ�.\n" + "�Է��� ������ �����ø� ���� �ϴ��� " + "\n'����' ��ư�� ������ �Է��� �Ϸ�˴ϴ�.");
				foodTableView.refresh();
				foodTableView.setItems(foodTableList);
				foodBtnCalTotalCal.setDisable(true);
				foodTxtNo.setDisable(true);
				foodTxtNo.clear();
				foodTxtPkNo.setDisable(true);
				foodTxtPkNo.clear();
				foodTxtName.setDisable(true);
				foodTxtName.clear();
				foodTxtCal.setDisable(true);
				foodTxtCal.clear();
				foodTxtTotalCal.setDisable(true);
				foodTxtTotalCal.clear();
				foodCmbKind.setDisable(true);
				foodCmbKategorie.setDisable(true);
				foodTxtVolume.setDisable(true);
				foodTxtVolume.clear();
				foodBtnSave.setDisable(true);
			});

			// ================���� ���� �Է� ��ư Ŭ����====================
			foodBtnInput.setOnAction(e -> {
				callAlert("������ ������ ���� �Է��մϴ�:������ ������ ������ Ȯ���Ͻ� ��" + "\n�ش� ������ �Է����ּ���" + "\n������ ��� �Է��Ͻð� �ϴܿ�\n"
						+ "'���� ����' ��ư�� �����ø� �Է��� �Ϸ�˴ϴ�. " + "\n������ ������ �ٽ� �˻� �� ������ ��\n�� Į�θ� ����� �������ּ���.");
				foodTableView.refresh();
				foodTableView.setItems(foodTableList);
				foodCmbKind.setValue(foodCmbKindList.get(0));
				foodCmbKind.setDisable(false);
				foodBtnCorInput.setVisible(true);
				foodCmbKategorie.setDisable(false);
				foodTxtNo.clear();
				foodTxtNo.setDisable(true);
				foodTxtPkNo.clear();
				foodTxtPkNo.setDisable(true);
				foodTxtName.setDisable(false);
				foodTxtCal.clear();
				foodTxtCal.setDisable(false);
				foodTxtVolume.clear();
				foodTxtVolume.setDisable(true);
				foodBtnCalTotalCal.setDisable(true);
				foodTxtTotalCal.setDisable(true);
				foodTxtName.clear();
				foodBtnDel.setDisable(true);

				foodCmbKategorie.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
					@Override
					public void changed(ObservableValue<? extends String> observable, String oldValue,
							String newValue) {
						foodTxtPkNo.clear();
						foodTxtNo.clear();
						if (newValue != null) {
							switch (newValue.toString()) {
							case "1)����Ĩ":
							case "1)�߰�����":
							case "1)�ø���":
							case "1)����":
							case "1)��Ÿ":
								secondPkno = "1";
								break;
							case "2)�׷�����":
							case "2)�������":
							case "2)�佺Ʈ":
							case "2)�Ľ�Ÿ":
								secondPkno = "2";
								break;
							case "3)������ġ":
							case "3)����":
							case "3)��":
							case "3)��":
								secondPkno = "3";
								break;
							case "4)��Ÿ":
								secondPkno = "4";
								break;
							}
							String pkno = null;
							int count = FoodDAO.getOneOneNo("'" + newValue.toString() + "'") + 1;
							foodTxtNo.setText(String.valueOf(count));
							if (foodTxtNo.getText().length() == 1) {
								pkno = firstPkno + secondPkno + "0" + "0" + foodTxtNo.getText();
								count = 0;
							} else {
								pkno = firstPkno + secondPkno + "0" + foodTxtNo.getText();
								count = 0;
							}
							foodTxtPkNo.setText(pkno);
						}
					}
				});
			});

			// ================���� �˻� ���====================
			foodBtnSearch.setOnAction(e -> {
				ObservableList<Food> foodTableListSearch = FXCollections.observableArrayList();
				foodTableListSearch.clear();
				for (Food food : foodTableList) {
					if (food.getKind().contains(foodTxtSearch.getText())
							|| food.getKategorie().contains(foodTxtSearch.getText())
							|| food.getName().contains(foodTxtSearch.getText())) {
						foodTableListSearch.add(food);
					}
				}
				foodTableView.setItems(foodTableListSearch);
				foodTxtSearch.clear();
			});

			// ================�� Į�θ� ��� ��ư Ŭ����====================
			foodBtnCalTotalCal.setOnAction(e -> {
				if (foodTxtVolume.getText() != null) {
					double calValue = Integer.parseInt(foodTxtCal.getText()) / 100.0;
					double dValue = calValue * Double.parseDouble(foodTxtVolume.getText());
					int value = (int) Math.round(dValue);
					foodTxtVolume.setDisable(true);
					foodTxtTotalCal.setText(String.valueOf(value));
					foodBtnDel.setDisable(true);
					foodBtnSave.setDisable(false);
					callAlert("�� Į�θ� ��� �Ϸ�^^:�Է��Ͻ� ������ �����ø� '����'�� �����ּ���.\n������ �߸� �Է��ϼ����� '���ΰ�ħ'�� �����ּ���.");
				} else {
					callAlert("���뷮�� �Է� ���ϼ̳׿�^^:�����Ͻŷ��� �뷫�����ζ� �Է����ּ���!");
				}
			});

			// ================���� ���� ��ư Ŭ����====================
			foodBtnCorInput.setOnAction(e -> {
				if (foodCmbKind.getSelectionModel().getSelectedItem() != null
						&& foodCmbKategorie.getSelectionModel().getSelectedItem() != null && foodTxtNo.getText() != null
						&& foodTxtPkNo.getText() != null && foodTxtName.getText() != null
						&& foodTxtCal.getText() != null) {
					Food food = new Food(foodCmbKind.getSelectionModel().getSelectedItem(),
							foodCmbKategorie.getSelectionModel().getSelectedItem(),
							Integer.parseInt(foodTxtNo.getText()), Integer.parseInt(foodTxtPkNo.getText()),
							foodTxtName.getText(), Integer.parseInt(foodTxtCal.getText()), 0, 0);
					FoodDAO.insertFoodData(food);
					foodTableList.add(food);
					callAlert("�������� ����^^:�ش������� �����ϼż� �� Į�θ��� ����غ�����!");
				} else {
					callAlert("�������� ����!!:�Է°��� Ȯ���غ�����");
				}
			});

			// ================���� ��ư Ŭ����====================
			foodBtnSave.setOnAction(e -> {
				if (foodTxtName.getText() != null && foodTxtTotalCal.getText() != null) {
					callAlert("Į�θ� ������ �Ϸ�Ǿ����ϴ�^^ :�ϴܿ� '������ ��� ����'�� �ϼž� '������ Į�θ� ���'�� �����մϴ�~");
					t1txtLun3.setText(foodTxtName.getText() + ":" + foodTxtTotalCal.getText() + ":kcal");
					foodStage.close();
				} else {
					callAlert("�׸��� �������ϴ�: ���� �׸��� ������ �ٽ� üũ���ּ���^^");
				}
			});

			// ================���� ��ư Ŭ����====================
			foodBtnDel.setOnAction(e -> {
				selectedFoodIndex = foodTableView.getSelectionModel().getSelectedIndex();
				selectedFood = foodTableView.getSelectionModel().getSelectedItem();
				int value = FoodDAO.deleteFoodData(String.valueOf(selectedFood.getPkno()));
				if (value != 0) {
					foodTableList.remove(selectedFood);
					dbArrayList.remove(selectedFood);
					callAlert("�����Ǿ����ϴ�^^:");
					foodTableView.refresh();
					foodTableView.setItems(foodTableList);
					foodBtnCalTotalCal.setDisable(true);
					foodTxtNo.setDisable(true);
					foodTxtNo.clear();
					foodTxtPkNo.setDisable(true);
					foodTxtPkNo.clear();
					foodTxtName.setDisable(true);
					foodTxtName.clear();
					foodTxtCal.setDisable(true);
					foodTxtCal.clear();
					foodTxtTotalCal.setDisable(true);
					foodTxtTotalCal.clear();
					foodCmbKind.setDisable(true);
					foodCmbKategorie.setDisable(true);
					foodTxtVolume.setDisable(true);
					foodTxtVolume.clear();
					foodBtnSave.setDisable(true);
					foodBtnDel.setDisable(true);
				} else {
					callAlert("��������!!: ????????");
				}
			});

			// ================�ݱ� ��ư Ŭ����====================
			foodBtnExit.setOnAction(e -> foodStage.close());

			Scene scene = new Scene(root);
			foodStage.setScene(scene);
			foodStage.show();

			callAlert("������ ������ �� Į�θ��� ����մϴ�:������ ������ �� ���뷮�� g������ �Է��Ͻð�" + "\n�ϴ��� '�� Į�θ� ����ϱ�'��ư�� �����ø�"
					+ "\n�� Į�θ��� �ڵ� ���Ǿ� �Էµ˴ϴ�.\n" + "�Է��� ������ �����ø� ���� �ϴ��� " + "\n'����' ��ư�� ������ �Է��� �Ϸ�˴ϴ�.");

		} catch (IOException e) {
		}
	}

	// ���� +��ư�� ���������� �׼�
	private void handlerT1BtnDin1Action(TextField t1, TextField t2, Button b1, Button b2, Button b3, Button b4) {
		if (!exerciseTableList.isEmpty() && !exercisedbArrayList.isEmpty()) {
			exerciseTableList.clear();
			exercisedbArrayList.clear();
		}
		Stage exerStage;
		FXMLLoader loader = new FXMLLoader(getClass().getResource("../View/execute_exercise.fxml"));
		Parent root;
		try {
			exerStage = new Stage();
			root = loader.load();

			// ===================ID���================================
			TableView<Exercise> exerTableView = (TableView) root.lookup("#exerTableView");
			TextField exerTxtSearch = (TextField) root.lookup("#exerTxtSearch");
			Button exerBtnSearch = (Button) root.lookup("#exerBtnSearch");
			Button exerBtnCalCal = (Button) root.lookup("#exerBtnCalCal");
			Button exerBtnSave = (Button) root.lookup("#exerBtnSave");
			Button exerBtnDel = (Button) root.lookup("#exerBtnDel");
			Button exerBtnExit = (Button) root.lookup("#exerBtnExit");
			ComboBox<String> exerCmbKind = (ComboBox) root.lookup("#exerCmbKind");
			TextField exerTxtNo = (TextField) root.lookup("#exerTxtNo");
			TextField exerTxtPkNo = (TextField) root.lookup("#exerTxtPkNo");
			TextField exerTxtName = (TextField) root.lookup("#exerTxtName");
			TextField exerTxtFireCal = (TextField) root.lookup("#exerTxtFireCal");
			TextField exerTxtTime = (TextField) root.lookup("#exerTxtTime");
			inputDecimalFormat(exerTxtTime);
			TextField exerTxtTotalCal = (TextField) root.lookup("#exerTxtTotalCal");
			Button exerBtnCalinfo = (Button) root.lookup("#exerBtnCalinfo");
			Button exerBtnInput = (Button) root.lookup("#exerBtnInput");
			Button exerBtnExerSave = (Button) root.lookup("#exerBtnExerSave");
			Button exerBtnRefresh = (Button) root.lookup("#exerBtnRefresh");
			ImageView exerImageView = (ImageView) root.lookup("#exerImageView");

			// ===================TableView����================================
			TableColumn tcKind = exerTableView.getColumns().get(0);
			tcKind.setCellValueFactory(new PropertyValueFactory<>("kind"));
			tcKind.setStyle("-fx-alignment: CENTER;");

			TableColumn tcNo = exerTableView.getColumns().get(1);
			tcNo.setCellValueFactory(new PropertyValueFactory<>("no"));
			tcNo.setStyle("-fx-alignment: CENTER;");

			TableColumn tcPkno = exerTableView.getColumns().get(2);
			tcPkno.setCellValueFactory(new PropertyValueFactory<>("pkno"));
			tcPkno.setStyle("-fx-alignment: CENTER;");

			TableColumn tcName = exerTableView.getColumns().get(3);
			tcName.setCellValueFactory(new PropertyValueFactory<>("name"));
			tcName.setStyle("-fx-alignment: CENTER;");

			TableColumn tcFirecal = exerTableView.getColumns().get(4);
			tcFirecal.setCellValueFactory(new PropertyValueFactory<>("firecal"));
			tcFirecal.setStyle("-fx-alignment: CENTER;");

			TableColumn tcTime = exerTableView.getColumns().get(5);
			tcTime.setCellValueFactory(new PropertyValueFactory<>("time"));
			tcTime.setStyle("-fx-alignment: CENTER;");

			TableColumn tcTotalcal = exerTableView.getColumns().get(6);
			tcTotalcal.setCellValueFactory(new PropertyValueFactory<>("totalcal"));
			tcTotalcal.setStyle("-fx-alignment: CENTER;");

			TableColumn tcImages = exerTableView.getColumns().get(7);
			tcImages.setCellValueFactory(new PropertyValueFactory<>("images"));
			tcImages.setStyle("-fx-alignment: CENTER;");

			exercisedbArrayList = ExerciseDAO.getExerciseData();
			for (Exercise ec : exercisedbArrayList) {
				exerciseTableList.add(ec);
			}

			exerTableView.setItems(exerciseTableList);
			// ===================�޺��ڽ� ����================================
			if(!exerCmbKindList.isEmpty()) {
				exerCmbKindList.clear();
			}
			exerCmbKindList.add("1)�����");
			exerCmbKindList.add("2)�����");
			exerCmbKind.setItems(exerCmbKindList);

			// ===================ùȭ�� ������ ����================================

			exerBtnCalCal.setDisable(true);
			exerTxtNo.setDisable(true);
			exerTxtPkNo.setDisable(true);
			exerTxtName.setDisable(true);
			exerTxtFireCal.setDisable(true);
			exerTxtTotalCal.setDisable(true);
			exerCmbKind.setDisable(true);
			exerTxtTime.setDisable(true);
			exerBtnSave.setDisable(true);
			exerBtnExerSave.setVisible(false);
			exerBtnDel.setDisable(true);

			// ===================���̺�� Ŭ���� �׼�================================

			exerTableView.setOnMouseClicked(e -> {
				selectedExercise = exerTableView.getSelectionModel().getSelectedItem();
				selectedExerciseIndex = exerTableView.getSelectionModel().getSelectedIndex();
				exerTxtNo.setText(String.valueOf(selectedExercise.getNo()));
				exerTxtNo.setDisable(true);
				exerTxtPkNo.setText(String.valueOf(selectedExercise.getPkno()));
				exerTxtPkNo.setDisable(true);
				exerTxtName.setText(selectedExercise.getName());
				exerTxtName.setDisable(true);
				exerTxtFireCal.setText(String.valueOf(selectedExercise.getFirecal()));
				exerTxtFireCal.setDisable(true);
				exerTxtTotalCal.setDisable(true);
				exerTxtTotalCal.clear();
				exerCmbKind.setValue(selectedExercise.getKind());
				exerCmbKind.setDisable(true);
				exerBtnCalCal.setDisable(false);
				exerTxtTime.setDisable(false);
				exerBtnDel.setDisable(false);
				if (selectedExercise.getImages() != null) {
					exerImageView.setImage(
							new Image(getClass().getResource("../images/" + selectedExercise.getImages()).toString()));
				}
			});

			// ================���ΰ�ħ ��ư Ŭ����====================
			exerBtnRefresh.setOnAction(e -> {
				exerTableView.refresh();
				exerTableView.setItems(exerciseTableList);
				exerBtnCalCal.setDisable(true);
				exerTxtNo.setDisable(true);
				exerTxtNo.clear();
				exerTxtPkNo.setDisable(true);
				exerTxtPkNo.clear();
				exerTxtName.setDisable(true);
				exerTxtName.clear();
				exerTxtFireCal.setDisable(true);
				exerTxtFireCal.clear();
				exerTxtTotalCal.setDisable(true);
				exerTxtTotalCal.clear();
				exerCmbKind.setDisable(true);
				exerTxtTime.setDisable(true);
				exerTxtTime.clear();
				exerBtnSave.setDisable(true);
				exerBtnDel.setDisable(true);
			});

			// ================� �˻� ���====================
			exerBtnSearch.setOnAction(e -> {
				ObservableList<Exercise> exerTableListSearch = FXCollections.observableArrayList();
				exerTableListSearch.clear();
				for (Exercise ec : exerciseTableList) {
					if (ec.getKind().contains(exerTxtSearch.getText())
							|| ec.getName().contains(exerTxtSearch.getText())) {
						exerTableListSearch.add(ec);
					}
				}
				exerTableView.setItems(exerTableListSearch);
				exerTxtSearch.clear();
			});

			// ================Į�θ� ��� �ȳ� ��ư Ŭ����====================
			exerBtnCalinfo.setOnAction(e -> {
				callAlert("�ǽ��� ��� �� Į�θ��� ����մϴ�:��� ������ �� �ǽýð��� '��'������ �Է��Ͻð�" + "\n�ϴ��� '�� Į�θ� ����ϱ�'��ư�� �����ø�"
						+ "\n�� Į�θ��� �ڵ� ���Ǿ� �Էµ˴ϴ�.\n" + "�Է��� ������ �����ø� ���� �ϴ��� " + "\n'����' ��ư�� ������ �Է��� �Ϸ�˴ϴ�.");
				exerTableView.refresh();
				exerTableView.setItems(exerciseTableList);
				exerBtnCalCal.setDisable(true);
				exerTxtNo.setDisable(true);
				exerTxtNo.clear();
				exerTxtPkNo.setDisable(true);
				exerTxtPkNo.clear();
				exerTxtName.setDisable(true);
				exerTxtName.clear();
				exerTxtFireCal.setDisable(true);
				exerTxtFireCal.clear();
				exerTxtTotalCal.setDisable(true);
				exerTxtTotalCal.clear();
				exerCmbKind.setDisable(true);
				exerTxtTime.clear();
				exerBtnSave.setDisable(true);
			});

			// ================� ���� �Է� ��ư Ŭ����====================
			exerBtnInput.setOnAction(e -> {
				callAlert("�ǽ��� ��� ���� �Է��մϴ�:�ǽ��� ��� ������ Ȯ���Ͻ� ��" + "\n�ش� ������ �Է����ּ���" + "\n������ ��� �Է��Ͻð� �ϴܿ�\n"
						+ "'���� ����' ��ư�� �����ø� �Է��� �Ϸ�˴ϴ�. " + "\n������ ��� �ٽ� �˻� �� ������ ��\n�� Į�θ� ����� �������ּ���.");
				exerTableView.refresh();
				exerTableView.setItems(exerciseTableList);
				exerCmbKind.setValue(exerCmbKindList.get(0));
				exerCmbKind.setDisable(false);
				exerBtnExerSave.setVisible(true);
				exerTxtNo.clear();
				exerTxtNo.setDisable(true);
				exerTxtPkNo.clear();
				exerTxtPkNo.setDisable(true);
				exerTxtName.setDisable(false);
				exerTxtFireCal.clear();
				exerTxtFireCal.setDisable(false);
				exerTxtTime.clear();
				exerTxtTime.setDisable(true);
				exerBtnCalCal.setDisable(true);
				exerTxtTotalCal.setDisable(true);
				exerTxtName.clear();
				exerBtnDel.setDisable(true);

				exerCmbKind.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
					@Override
					public void changed(ObservableValue<? extends String> observable, String oldValue,
							String newValue) {
						exerTxtPkNo.clear();
						exerTxtNo.clear();
						if (newValue != null) {
							switch (newValue.toString()) {
							case "1)�����":
								secondPkno = "1";
								break;
							case "2)�����":
								secondPkno = "2";
								break;
							}
							String pkno = null;
							int count = ExerciseDAO.getExerNo("'" + newValue.toString() + "'") + 1;
							exerTxtNo.setText(String.valueOf(count));
							if (exerTxtNo.getText().length() == 1) {
								pkno = secondPkno + "0" + "0" + exerTxtNo.getText();
								count = 0;
							} else {
								pkno = secondPkno + "0" + exerTxtNo.getText();
								count = 0;
							}
							exerTxtPkNo.setText(pkno);
						}
					}
				});
			});

			// ================�� Į�θ� ��� ��ư Ŭ����====================
			exerBtnCalCal.setOnAction(e -> {
				if (exerTxtTime.getText() != null) {
					double calValue = (Integer.parseInt(exerTxtFireCal.getText()) / 10.0 / 90.0)
							* (double) loginUserMan.get(0).getCurrentWeight();
					double dValue = calValue * Integer.parseInt(exerTxtTime.getText());
					int value = (int) Math.round(dValue);
					exerTxtTime.setDisable(true);
					exerTxtTotalCal.setText(String.valueOf(value));
					exerBtnDel.setDisable(true);
					exerBtnSave.setDisable(false);
					callAlert("�� Į�θ� ��� �Ϸ�^^:�Է��Ͻ� ������ �����ø� '����'�� �����ּ���.\n������ �߸� �Է��ϼ����� '���ΰ�ħ'�� �����ּ���.");
				} else {
					callAlert("�ǽýð��� �Է� ���ϼ̳׿�^^:�ǽ��Ͻ� �ð��� �뷫�����ζ� �Է����ּ���!");
				}
			});

			// ================� ���� ��ư Ŭ����====================
			exerBtnExerSave.setOnAction(e -> {
				if (exerCmbKind.getSelectionModel().getSelectedItem() != null && exerTxtNo.getText() != null
						&& exerTxtPkNo.getText() != null && exerTxtName.getText() != null
						&& exerTxtFireCal.getText() != null) {
					Exercise ec = new Exercise(exerCmbKind.getSelectionModel().getSelectedItem(),
							Integer.parseInt(exerTxtNo.getText()), Integer.parseInt(exerTxtPkNo.getText()),
							exerTxtName.getText(), Integer.parseInt(exerTxtFireCal.getText()), 0, 0, null);
					ExerciseDAO.insertExerciseData(ec);
					exerciseTableList.add(ec);
					callAlert(" ����� ����^^:�ش��� �����ϼż� �� Į�θ��� ����غ�����!");
					exerBtnInput.setVisible(false);
				} else {
					callAlert("����� ����!!:�Է°��� Ȯ���غ�����");
				}
			});

			// ================���� ��ư Ŭ����====================
			exerBtnSave.setOnAction(e -> {
				if (exerTxtName.getText() != null && exerTxtTotalCal.getText() != null) {
					callAlert("Į�θ� ������ �Ϸ�Ǿ����ϴ�^^ :�ϴܿ� '������ ��� ����'�� �ϼž� '������ Į�θ� ���'�� �����մϴ�~");
					t1.setText(exerTxtName.getText() + ":" + exerTxtTotalCal.getText() + ":kcal");
					exerStage.close();
					t2.setDisable(true);
					t2.setVisible(true);
					b1.setDisable(true);
					b2.setDisable(false);
					b3.setDisable(false);
					b3.setVisible(true);
					b4.setDisable(false);
					b4.setVisible(true);
				} else {
					callAlert("�׸��� �������ϴ�: ���� �׸��� ������ �ٽ� üũ���ּ���^^");
				}
			});

			// ================���� ��ư Ŭ����====================
			exerBtnDel.setOnAction(e -> {
				selectedExerciseIndex = exerTableView.getSelectionModel().getSelectedIndex();
				selectedExercise = exerTableView.getSelectionModel().getSelectedItem();
				int value = ExerciseDAO.deleteExerData(String.valueOf(selectedExercise.getPkno()));
				if (value != 0) {
					exerciseTableList.remove(selectedExercise);
					exercisedbArrayList.remove(selectedExercise);
					callAlert("�����Ǿ����ϴ�^^:");
					exerTableView.refresh();
					exerTableView.setItems(exerciseTableList);
					exerBtnCalCal.setDisable(true);
					exerTxtNo.setDisable(true);
					exerTxtNo.clear();
					exerTxtPkNo.setDisable(true);
					exerTxtPkNo.clear();
					exerTxtName.setDisable(true);
					exerTxtName.clear();
					exerTxtFireCal.setDisable(true);
					exerTxtFireCal.clear();
					exerTxtTotalCal.setDisable(true);
					exerTxtTotalCal.clear();
					exerCmbKind.setDisable(true);
					exerTxtTime.setDisable(true);
					exerTxtTime.clear();
					exerBtnSave.setDisable(true);
					exerBtnDel.setDisable(true);
				} else {
					callAlert("��������!!: ????????");
				}
			});

			// ===================�ݱ� ��ư �׼�================================
			exerBtnExit.setOnAction(e -> exerStage.close());

			Scene scene = new Scene(root);
			exerStage.setScene(scene);
			exerStage.show();
		} catch (Exception e) {
		}
	}

	// ������ ���� +��ư�� ���������� �׼�
	private void handlerT1BtnEtc3Action() {
		if (!exerciseTableList.isEmpty() && !exercisedbArrayList.isEmpty()) {
			exerciseTableList.clear();
			exercisedbArrayList.clear();
		}
		Stage exerStage;
		FXMLLoader loader = new FXMLLoader(getClass().getResource("../View/execute_exercise.fxml"));
		Parent root;
		try {
			exerStage = new Stage();
			root = loader.load();

			// ===================ID���================================
			TableView<Exercise> exerTableView = (TableView) root.lookup("#exerTableView");
			TextField exerTxtSearch = (TextField) root.lookup("#exerTxtSearch");
			Button exerBtnSearch = (Button) root.lookup("#exerBtnSearch");
			Button exerBtnCalCal = (Button) root.lookup("#exerBtnCalCal");
			Button exerBtnSave = (Button) root.lookup("#exerBtnSave");
			Button exerBtnDel = (Button) root.lookup("#exerBtnDel");
			Button exerBtnExit = (Button) root.lookup("#exerBtnExit");
			ComboBox<String> exerCmbKind = (ComboBox) root.lookup("#exerCmbKind");
			TextField exerTxtNo = (TextField) root.lookup("#exerTxtNo");
			TextField exerTxtPkNo = (TextField) root.lookup("#exerTxtPkNo");
			TextField exerTxtName = (TextField) root.lookup("#exerTxtName");
			TextField exerTxtFireCal = (TextField) root.lookup("#exerTxtFireCal");
			TextField exerTxtTime = (TextField) root.lookup("#exerTxtTime");
			inputDecimalFormat(exerTxtTime);
			TextField exerTxtTotalCal = (TextField) root.lookup("#exerTxtTotalCal");
			Button exerBtnCalinfo = (Button) root.lookup("#exerBtnCalinfo");
			Button exerBtnInput = (Button) root.lookup("#exerBtnInput");
			Button exerBtnExerSave = (Button) root.lookup("#exerBtnExerSave");
			Button exerBtnRefresh = (Button) root.lookup("#exerBtnRefresh");
			ImageView exerImageView = (ImageView) root.lookup("#exerImageView");

			// ===================TableView����================================
			TableColumn tcKind = exerTableView.getColumns().get(0);
			tcKind.setCellValueFactory(new PropertyValueFactory<>("kind"));
			tcKind.setStyle("-fx-alignment: CENTER;");

			TableColumn tcNo = exerTableView.getColumns().get(1);
			tcNo.setCellValueFactory(new PropertyValueFactory<>("no"));
			tcNo.setStyle("-fx-alignment: CENTER;");

			TableColumn tcPkno = exerTableView.getColumns().get(2);
			tcPkno.setCellValueFactory(new PropertyValueFactory<>("pkno"));
			tcPkno.setStyle("-fx-alignment: CENTER;");

			TableColumn tcName = exerTableView.getColumns().get(3);
			tcName.setCellValueFactory(new PropertyValueFactory<>("name"));
			tcName.setStyle("-fx-alignment: CENTER;");

			TableColumn tcFirecal = exerTableView.getColumns().get(4);
			tcFirecal.setCellValueFactory(new PropertyValueFactory<>("firecal"));
			tcFirecal.setStyle("-fx-alignment: CENTER;");

			TableColumn tcTime = exerTableView.getColumns().get(5);
			tcTime.setCellValueFactory(new PropertyValueFactory<>("time"));
			tcTime.setStyle("-fx-alignment: CENTER;");

			TableColumn tcTotalcal = exerTableView.getColumns().get(6);
			tcTotalcal.setCellValueFactory(new PropertyValueFactory<>("totalcal"));
			tcTotalcal.setStyle("-fx-alignment: CENTER;");

			TableColumn tcImages = exerTableView.getColumns().get(7);
			tcImages.setCellValueFactory(new PropertyValueFactory<>("images"));
			tcImages.setStyle("-fx-alignment: CENTER;");

			exercisedbArrayList = ExerciseDAO.getExerciseData();
			for (Exercise ec : exercisedbArrayList) {
				exerciseTableList.add(ec);
			}

			exerTableView.setItems(exerciseTableList);
			// ===================�޺��ڽ� ����================================
			if(!exerCmbKindList.isEmpty()) {
				exerCmbKindList.clear();
			}
			exerCmbKindList.add("1)�����");
			exerCmbKindList.add("2)�����");
			exerCmbKind.setItems(exerCmbKindList);

			// ===================ùȭ�� ������ ����================================

			exerBtnCalCal.setDisable(true);
			exerTxtNo.setDisable(true);
			exerTxtPkNo.setDisable(true);
			exerTxtName.setDisable(true);
			exerTxtFireCal.setDisable(true);
			exerTxtTotalCal.setDisable(true);
			exerCmbKind.setDisable(true);
			exerTxtTime.setDisable(true);
			exerBtnSave.setDisable(true);
			exerBtnExerSave.setVisible(false);
			exerBtnDel.setDisable(true);

			// ===================���̺�� Ŭ���� �׼�================================

			exerTableView.setOnMouseClicked(e -> {
				selectedExercise = exerTableView.getSelectionModel().getSelectedItem();
				selectedExerciseIndex = exerTableView.getSelectionModel().getSelectedIndex();
				exerTxtNo.setText(String.valueOf(selectedExercise.getNo()));
				exerTxtNo.setDisable(true);
				exerTxtPkNo.setText(String.valueOf(selectedExercise.getPkno()));
				exerTxtPkNo.setDisable(true);
				exerTxtName.setText(selectedExercise.getName());
				exerTxtName.setDisable(true);
				exerTxtFireCal.setText(String.valueOf(selectedExercise.getFirecal()));
				exerTxtFireCal.setDisable(true);
				exerTxtTotalCal.setDisable(true);
				exerTxtTotalCal.clear();
				exerCmbKind.setValue(selectedExercise.getKind());
				exerCmbKind.setDisable(true);
				exerBtnCalCal.setDisable(false);
				exerTxtTime.setDisable(false);
				exerBtnDel.setDisable(false);
				if (selectedExercise.getImages() != null) {
					exerImageView.setImage(
							new Image(getClass().getResource("../images/" + selectedExercise.getImages()).toString()));
				}
			});

			// ================���ΰ�ħ ��ư Ŭ����====================
			exerBtnRefresh.setOnAction(e -> {
				exerTableView.refresh();
				exerTableView.setItems(exerciseTableList);
				exerBtnCalCal.setDisable(true);
				exerTxtNo.setDisable(true);
				exerTxtNo.clear();
				exerTxtPkNo.setDisable(true);
				exerTxtPkNo.clear();
				exerTxtName.setDisable(true);
				exerTxtName.clear();
				exerTxtFireCal.setDisable(true);
				exerTxtFireCal.clear();
				exerTxtTotalCal.setDisable(true);
				exerTxtTotalCal.clear();
				exerCmbKind.setDisable(true);
				exerTxtTime.setDisable(true);
				exerTxtTime.clear();
				exerBtnSave.setDisable(true);
				exerBtnDel.setDisable(true);
			});

			// ================� �˻� ���====================
			exerBtnSearch.setOnAction(e -> {
				ObservableList<Exercise> exerTableListSearch = FXCollections.observableArrayList();
				exerTableListSearch.clear();
				for (Exercise ec : exerciseTableList) {
					if (ec.getKind().contains(exerTxtSearch.getText())
							|| ec.getName().contains(exerTxtSearch.getText())) {
						exerTableListSearch.add(ec);
					}
				}
				exerTableView.setItems(exerTableListSearch);
				exerTxtSearch.clear();
			});

			// ================Į�θ� ��� �ȳ� ��ư Ŭ����====================
			exerBtnCalinfo.setOnAction(e -> {
				callAlert("�ǽ��� ��� �� Į�θ��� ����մϴ�:��� ������ �� �ǽýð��� '��'������ �Է��Ͻð�" + "\n�ϴ��� '�� Į�θ� ����ϱ�'��ư�� �����ø�"
						+ "\n�� Į�θ��� �ڵ� ���Ǿ� �Էµ˴ϴ�.\n" + "�Է��� ������ �����ø� ���� �ϴ��� " + "\n'����' ��ư�� ������ �Է��� �Ϸ�˴ϴ�.");
				exerTableView.refresh();
				exerTableView.setItems(exerciseTableList);
				exerBtnCalCal.setDisable(true);
				exerTxtNo.setDisable(true);
				exerTxtNo.clear();
				exerTxtPkNo.setDisable(true);
				exerTxtPkNo.clear();
				exerTxtName.setDisable(true);
				exerTxtName.clear();
				exerTxtFireCal.setDisable(true);
				exerTxtFireCal.clear();
				exerTxtTotalCal.setDisable(true);
				exerTxtTotalCal.clear();
				exerCmbKind.setDisable(true);
				exerTxtTime.clear();
				exerBtnSave.setDisable(true);
			});

			// ================� ���� �Է� ��ư Ŭ����====================
			exerBtnInput.setOnAction(e -> {
				callAlert("�ǽ��� ��� ���� �Է��մϴ�:�ǽ��� ��� ������ Ȯ���Ͻ� ��" + "\n�ش� ������ �Է����ּ���" + "\n������ ��� �Է��Ͻð� �ϴܿ�\n"
						+ "'���� ����' ��ư�� �����ø� �Է��� �Ϸ�˴ϴ�. " + "\n������ ��� �ٽ� �˻� �� ������ ��\n�� Į�θ� ����� �������ּ���.");
				exerTableView.refresh();
				exerTableView.setItems(exerciseTableList);
				exerCmbKind.setValue(exerCmbKindList.get(0));
				exerCmbKind.setDisable(false);
				exerBtnExerSave.setVisible(true);
				exerTxtNo.clear();
				exerTxtNo.setDisable(true);
				exerTxtPkNo.clear();
				exerTxtPkNo.setDisable(true);
				exerTxtName.setDisable(false);
				exerTxtFireCal.clear();
				exerTxtFireCal.setDisable(false);
				exerTxtTime.clear();
				exerTxtTime.setDisable(true);
				exerBtnCalCal.setDisable(true);
				exerTxtTotalCal.setDisable(true);
				exerTxtName.clear();
				exerBtnDel.setDisable(true);

				exerCmbKind.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
					@Override
					public void changed(ObservableValue<? extends String> observable, String oldValue,
							String newValue) {
						exerTxtPkNo.clear();
						exerTxtNo.clear();
						if (newValue != null) {
							switch (newValue.toString()) {
							case "1)�����":
								secondPkno = "1";
								break;
							case "2)�����":
								secondPkno = "2";
								break;
							}
							String pkno = null;
							int count = ExerciseDAO.getExerNo("'" + newValue.toString() + "'") + 1;
							exerTxtNo.setText(String.valueOf(count));
							if (exerTxtNo.getText().length() == 1) {
								pkno = secondPkno + "0" + "0" + exerTxtNo.getText();
								count = 0;
							} else {
								pkno = secondPkno + "0" + exerTxtNo.getText();
								count = 0;
							}
							exerTxtPkNo.setText(pkno);
						}
					}
				});
			});

			// ================�� Į�θ� ��� ��ư Ŭ����====================
			exerBtnCalCal.setOnAction(e -> {
				if (exerTxtTime.getText() != null) {
					double calValue = (Integer.parseInt(exerTxtFireCal.getText()) / 10.0 / 90.0)
							* (double) loginUserMan.get(0).getCurrentWeight();
					double dValue = calValue * Integer.parseInt(exerTxtTime.getText());
					int value = (int) Math.round(dValue);
					exerTxtTime.setDisable(true);
					exerTxtTotalCal.setText(String.valueOf(value));
					exerBtnDel.setDisable(true);
					exerBtnSave.setDisable(false);
					callAlert("�� Į�θ� ��� �Ϸ�^^:�Է��Ͻ� ������ �����ø� '����'�� �����ּ���.\n������ �߸� �Է��ϼ����� '���ΰ�ħ'�� �����ּ���.");
				} else {
					callAlert("�ǽýð��� �Է� ���ϼ̳׿�^^:�ǽ��Ͻ� �ð��� �뷫�����ζ� �Է����ּ���!");
				}
			});

			// ================� ���� ��ư Ŭ����====================
			exerBtnExerSave.setOnAction(e -> {
				if (exerCmbKind.getSelectionModel().getSelectedItem() != null && exerTxtNo.getText() != null
						&& exerTxtPkNo.getText() != null && exerTxtName.getText() != null
						&& exerTxtFireCal.getText() != null) {
					Exercise ec = new Exercise(exerCmbKind.getSelectionModel().getSelectedItem(),
							Integer.parseInt(exerTxtNo.getText()), Integer.parseInt(exerTxtPkNo.getText()),
							exerTxtName.getText(), Integer.parseInt(exerTxtFireCal.getText()), 0, 0, null);
					ExerciseDAO.insertExerciseData(ec);
					exerciseTableList.add(ec);
					callAlert(" ����� ����^^:�ش��� �����ϼż� �� Į�θ��� ����غ�����!");
					exerBtnInput.setVisible(false);
				} else {
					callAlert("����� ����!!:�Է°��� Ȯ���غ�����");
				}
			});

			// ================���� ��ư Ŭ����====================
			exerBtnSave.setOnAction(e -> {
				if (exerTxtName.getText() != null && exerTxtTotalCal.getText() != null) {
					callAlert("Į�θ� ������ �Ϸ�Ǿ����ϴ�^^ :�ϴܿ� '������ ��� ����'�� �ϼž� '������ Į�θ� ���'�� �����մϴ�~");
					t1txtEtc3.setText(exerTxtName.getText() + ":" + exerTxtTotalCal.getText() + ":kcal");
					exerStage.close();
				} else {
					callAlert("�׸��� �������ϴ�: ���� �׸��� ������ �ٽ� üũ���ּ���^^");
				}
			});

			// ================���� ��ư Ŭ����====================
			exerBtnDel.setOnAction(e -> {
				selectedExerciseIndex = exerTableView.getSelectionModel().getSelectedIndex();
				selectedExercise = exerTableView.getSelectionModel().getSelectedItem();
				int value = ExerciseDAO.deleteExerData(String.valueOf(selectedExercise.getPkno()));
				if (value != 0) {
					exerciseTableList.remove(selectedExercise);
					exercisedbArrayList.remove(selectedExercise);
					callAlert("�����Ǿ����ϴ�^^:");
					exerTableView.refresh();
					exerTableView.setItems(exerciseTableList);
					exerBtnCalCal.setDisable(true);
					exerTxtNo.setDisable(true);
					exerTxtNo.clear();
					exerTxtPkNo.setDisable(true);
					exerTxtPkNo.clear();
					exerTxtName.setDisable(true);
					exerTxtName.clear();
					exerTxtFireCal.setDisable(true);
					exerTxtFireCal.clear();
					exerTxtTotalCal.setDisable(true);
					exerTxtTotalCal.clear();
					exerCmbKind.setDisable(true);
					exerTxtTime.setDisable(true);
					exerTxtTime.clear();
					exerBtnSave.setDisable(true);
					exerBtnDel.setDisable(true);
				} else {
					callAlert("��������!!: ????????");
				}
			});

			// ===================�ݱ� ��ư �׼�================================
			exerBtnExit.setOnAction(e -> exerStage.close());

			Scene scene = new Scene(root);
			exerStage.setScene(scene);
			exerStage.show();
		} catch (Exception e) {
		}

	}

	// ============================Į�θ�����Ʈ==========================

	// Į�θ�����Ʈ �� Ŭ�� �׼�(��Ʈ)
	private void handlerT3TabClickAction() {
		series1.setName("SavingĮ�θ�");
		ObservableList oblist1 = FXCollections.observableArrayList();
		if (!oblist1.isEmpty()) {
			oblist1.clear();
		}
		int year = t1DatePicker.getValue().getYear();
		int month = t1DatePicker.getValue().getMonthValue();
		int day = t1DatePicker.getValue().getDayOfMonth();
		int saveCal = 0;
		int sum = 0;
		for (int i = 6; i >= 0; i--) {
			saveCal = loginUserMan.get(0).getKeepCalorie()
					- UserCalDAO.getUserPlusCalData(loginUserMan.get(0).getUserID(),
							year + "-" + month + "-" + (day - i))
					+ UserCalDAO.getUserMinusCalData(loginUserMan.get(0).getUserID(),
							year + "-" + month + "-" + (day - i));
			oblist1.add(new XYChart.Data<>(month + "/" + (day - i) + "\n" + saveCal + "kcal", saveCal));
			sum += saveCal;
		}
		series1.setData(oblist1);
		if (t3BarChart.getData().isEmpty()) {
			t3BarChart.getData().add(series1);
		}
		t3BarChart.setAnimated(false);
		t3LabelTotalCal.setText(String.valueOf(sum));
		t3LabelAvgCal.setText(String.valueOf(sum / 7));
		t3LabelLimit.setText(String.valueOf(loginUserMan.get(0).getKeepCalorie()));
	}

	// ============================ü�ߺ�ȭ==========================
	
	// ��ǥ ü�� ���� ���� ��ư �׼�
	private void handlerT4BtnWeightSaveAction() {
		UserInfo ui = new UserInfo(loginUserMan.get(0).getUserID(), loginUserMan.get(0).getPassword(),
				Integer.parseInt(t4txtTodayWeight.getText()), loginUserMan.get(0).getHopeControlWeight(),
				loginUserMan.get(0).getGender(), loginUserMan.get(0).getMoveLevel(),
				loginUserMan.get(0).getCurrentWeight(), loginUserMan.get(0).getKeepCalorie());
		loginUserMan.set(0, ui);
		UserInfoDAO.updateGoalData(ui);
		callAlert("��ǥü�� ���� �Ϸ�!!: ��ǥ�� ���� ȭ����!!");
		t4LabelWantWeight.setText(String.valueOf(loginUserMan.get(0).getGoal()));
	}

	// ü�ߺ�ȭ ���� Ŭ���� �׼�(������Ʈ)
	private void handlerT4TabClickAction() {
		t4LabelWantWeight.setText(String.valueOf(loginUserMan.get(0).getGoal()));
		series2.setName("�ֱ� 1���� �� ü�ߺ�ȭ");
		ObservableList oblist1 = FXCollections.observableArrayList();
		int year = t1DatePicker.getValue().getYear();
		int month = t1DatePicker.getValue().getMonthValue();
		int day = t1DatePicker.getValue().getDayOfMonth();
		int currentWeight = 0;
		int sum = 0;
		for (int i = 6; i >= 0; i--) {
			currentWeight = UserDailyWeightDAO.getUserDailyWeightData(loginUserMan.get(0).getUserID(),
					year + "-" + month + "-" + (day - i));
			series2.getData()
					.add(new XYChart.Data<>(month + "�� " + (day - i) + "��\n" + currentWeight + "kg", currentWeight));
		}
		oblist1.addAll(series2);
		t4LineChart.setData(oblist1);
		t4LineChart.setAnimated(false);
	}

	// ============================ü�ߺ�ȭ==========================
	
	// ��Ÿ2: �Է°� �ʵ� ���˼��� ��� : ���� ���ڸ��� �޴� ����� ������
	private void inputDecimalFormat(TextField textField) {
		// ���ڸ� �Է�(������ �Է¹���), �Ǽ��Է¹ް������new DecimalFormat("###.#");
		DecimalFormat format = new DecimalFormat("###");
		// ���� �Է½� ���� ���� �̺�Ʈ ó��
		textField.setTextFormatter(new TextFormatter<>(event -> {
			// �Է¹��� ������ ������ �̺�Ʈ�� ������.
			if (event.getControlNewText().isEmpty()) {
				return event;
			}
			// ������ �м��� ���� ��ġ�� ������.
			ParsePosition parsePosition = new ParsePosition(0);
			// �Է¹��� ����� �м���ġ�� �������������� format ����� ��ġ���� �м���.
			Object object = format.parse(event.getControlNewText(), parsePosition);
			// ���ϰ��� null �̰ų�,�Է��ѱ��̿ͱ����м���ġ���� ���������(�� �м������������� ����)�ų�,�Է��ѱ��̰� 4�̸�(3�ڸ��� �Ѿ����� ����.)
			// �̸� null ������.
			if (object == null || parsePosition.getIndex() < event.getControlNewText().length()
					|| event.getControlNewText().length() == 4) {
				return null;
			} else {
				return event;
			}
		}));
	}

	// ��Ÿ : �˸�â
	public static void callAlert(String contentText) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("�˸�!");
		alert.setHeaderText(contentText.substring(0, contentText.lastIndexOf(":")));
		alert.setContentText(contentText.substring(contentText.lastIndexOf(":") + 1));
		alert.showAndWait();
	}

	// ��Ÿ. ���� ���ڸ��� �޴� ���� �Լ�
	private void inputDecimalFormatTwoDigit(TextField textField) {
		// ���ڸ� �Է�(������ �Է¹���), �Ǽ��Է¹ް������new DecimalFormat("###.#");
		DecimalFormat format = new DecimalFormat("##");
		// ���� �Է½� ���� ���� �̺�Ʈ ó��
		textField.setTextFormatter(new TextFormatter<>(event -> {
			// �Է¹��� ������ ������ �̺�Ʈ�� ������.
			if (event.getControlNewText().isEmpty()) {
				return event;
			}
			// ������ �м��� ���� ��ġ�� ������.
			ParsePosition parsePosition = new ParsePosition(0);
			// �Է¹��� ����� �м���ġ�� �������������� format ����� ��ġ���� �м���.
			Object object = format.parse(event.getControlNewText(), parsePosition);
			// ���ϰ��� null �̰ų�,�Է��ѱ��̿ͱ����м���ġ���� ���������(�� �м������������� ����)�ų�,�Է��ѱ��̰� 4�̸�(3�ڸ��� �Ѿ����� ����.)
			// �̸� null ������.
			if (object == null || parsePosition.getIndex() < event.getControlNewText().length()
					|| event.getControlNewText().length() == 3) {
				return null;
			} else {
				return event;
			}
		}));
	}

}
