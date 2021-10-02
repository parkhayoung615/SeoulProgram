package layout;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import util.JDBCUtil;

public class MainController implements Initializable {
	// �α��� â FXML
	@FXML
	private TextField userId;
	@FXML
	private TextField userPw;
	@FXML
	private Button StartBtn;
	@FXML
	private Button loginBtn;
	@FXML
	private TextField search;
	@FXML
	private Label addrAll;
	@FXML
	private Label city;
	@FXML
	private Label district;
	@FXML
	private Label dong;
	@FXML
	private Label kind;
	@FXML
	private Label year;
	@FXML
	private Label universityId;
	@FXML
	private Label thisAddr;
	@FXML
	private Label state;
	@FXML
	private Label estab;
	@FXML
	private Label phone;
	@FXML
	private Label pax;
	@FXML
	private Label page;

	@FXML
	private Button GojoinBtn;
	@FXML
	private Button GoLoginBtn;

	// ȸ������
	@FXML
	private Button joinBtn;
	@FXML
	private TextField joinName;
	@FXML
	private TextField joinId;
	@FXML
	private TextField joinPw;

	@FXML
	private void initialize() {
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
	}

	// Index���� login���� ȭ����ȯ
	public void changeScene() {
		try {
			Parent login = FXMLLoader.load(getClass().getResource("/layout/Login.fxml"));
			Scene scene = new Scene(login);
			Stage primaryStage = (Stage) StartBtn.getScene().getWindow();
			scene.getStylesheets().add(getClass().getResource("/application/application.css").toExternalForm());
			primaryStage.setScene(scene);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Login���� ȸ���������� ȭ�� ��ȯ
	public void changeJoinScene() {
		try {
			Parent login = FXMLLoader.load(getClass().getResource("/layout/User.fxml"));
			Scene scene = new Scene(login);
			Stage primaryStage = (Stage) GojoinBtn.getScene().getWindow();
			scene.getStylesheets().add(getClass().getResource("/application/application.css").toExternalForm());
			primaryStage.setScene(scene);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// ȸ�����Կ��� login���� ȭ�� ��ȯ
	public void changeLoginScene() {
		try {
			Parent login = FXMLLoader.load(getClass().getResource("/layout/Login.fxml"));
			Scene scene = new Scene(login);
			Stage primaryStage = (Stage) GoLoginBtn.getScene().getWindow();
			scene.getStylesheets().add(getClass().getResource("/application/application.css").toExternalForm());
			primaryStage.setScene(scene);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// �α��� üũ
	public String userN;
	public String AuserID;
	public void loginCheck() {
		JDBCUtil db = new JDBCUtil();
		Connection con = db.getConnection();
		PreparedStatement pstmt = null;
		String loginId = userId.getText();
		String loginPw = userPw.getText();
		Alert alert = new Alert(AlertType.WARNING);

		if (loginId.isEmpty() && loginPw.isEmpty()) {
			alert.setTitle("�� ���� �ֽ��ϴ�!");
			alert.setHeaderText("Warning Dialog");
			alert.setContentText("���̵�� ��й�ȣ �Է¿� ������ �ֽ��ϴ�!");
			alert.show();
		}

		String sql = "SELECT `id`, `password`, `name` FROM `user` WHERE `id` = ? AND `password` = ?";

		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, loginId);
			pstmt.setString(2, loginPw);
			ResultSet rs = null;
			rs = pstmt.executeQuery();
			if (rs.next()) {
				// login���� MainLayout���� ȭ�� ��ȯ
				try {
					AuserID = rs.getString("id");
					userN = rs.getString("name");

					if (userN == "������" && AuserID == "11") {
						Parent login1 = FXMLLoader.load(getClass().getResource("/layout/MainLayout2.fxml"));
						Scene scene1 = new Scene(login1);
						Stage primaryStage1 = (Stage) loginBtn.getScene().getWindow();
						scene1.getStylesheets()
								.add(getClass().getResource("/application/application.css").toExternalForm());
						primaryStage1.setScene(scene1);
					} else {
						Parent login = FXMLLoader.load(getClass().getResource("/layout/MainLayout.fxml"));
						Scene scene = new Scene(login);
						Stage primaryStage = (Stage) loginBtn.getScene().getWindow();
						scene.getStylesheets()
								.add(getClass().getResource("/application/application.css").toExternalForm());
						primaryStage.setScene(scene);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else {
				alert.setTitle("������ �ֽ��ϴ�!");
				alert.setHeaderText("Warning Dialog");
				alert.setContentText("���̵�� ��й�ȣ �Է¿� ������ �ֽ��ϴ�!");
				alert.show();
			}
		} catch (Exception e) {
			e.printStackTrace();
			alert.setTitle("������ �ֽ��ϴ�!");
			alert.setHeaderText("Warning Dialog");
			alert.setContentText("���̵�� ��й�ȣ �Է¿� ������ �ֽ��ϴ�!");
			alert.show();
		}
	}

	// ȸ������ ���
	public void join() {
		JDBCUtil db = new JDBCUtil();
		Connection con = db.getConnection();
		PreparedStatement pstmt = null;
		String inputJoinId = joinId.getText();
		String inputJoinPw = joinPw.getText();
		String inputJoinName = joinName.getText();
		Alert alert = new Alert(AlertType.WARNING);

		if (inputJoinId.isEmpty() && inputJoinPw.isEmpty() && inputJoinName.isEmpty()) {
			alert.setTitle("�� ���� �ֽ��ϴ�!");
			alert.setHeaderText("Warning Dialog");
			alert.setContentText("�Է¿� ������ �ֽ��ϴ�!");
			alert.show();
		}

		String sql = "INSERT INTO `user`(`id`, `name`, `password`) VALUES (?,?,?)";

		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, inputJoinId);
			pstmt.setString(2, inputJoinName);
			pstmt.setString(3, inputJoinPw);
			pstmt.executeUpdate();
			try {
				Parent login = FXMLLoader.load(getClass().getResource("/layout/Login.fxml"));
				Scene scene = new Scene(login);
				Stage primaryStage = (Stage) GoLoginBtn.getScene().getWindow();
				scene.getStylesheets().add(getClass().getResource("/application/application.css").toExternalForm());
				primaryStage.setScene(scene);
			} catch (Exception e) {
				e.printStackTrace();
				alert.setTitle("������ �ֽ��ϴ�!");
				alert.setHeaderText("Warning Dialog");
				alert.setContentText("�Է¿� ������ �ֽ��ϴ�!");
				alert.show();
			}
		} catch (Exception e) {
			e.printStackTrace();
			alert.setTitle("������ �ֽ��ϴ�!");
			alert.setHeaderText("Warning Dialog");
			alert.setContentText("�Է¿� ������ �ֽ��ϴ�!");
			alert.show();
		}
	}

	// ��Ʈ�����ڸ� �̿��ؼ� ���� ��ƺ���
	// �÷��װ�

}