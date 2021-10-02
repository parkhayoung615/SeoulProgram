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
	// 로그인 창 FXML
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

	// 회원가입
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

	// Index에서 login으로 화면전환
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

	// Login에서 회원가입으로 화면 전환
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

	// 회원가입에서 login으로 화면 전환
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

	// 로그인 체크
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
			alert.setTitle("빈 값이 있습니다!");
			alert.setHeaderText("Warning Dialog");
			alert.setContentText("아이디와 비밀번호 입력에 오류가 있습니다!");
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
				// login에서 MainLayout으로 화면 전환
				try {
					AuserID = rs.getString("id");
					userN = rs.getString("name");

					if (userN == "관리자" && AuserID == "11") {
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
				alert.setTitle("오류가 있습니다!");
				alert.setHeaderText("Warning Dialog");
				alert.setContentText("아이디와 비밀번호 입력에 오류가 있습니다!");
				alert.show();
			}
		} catch (Exception e) {
			e.printStackTrace();
			alert.setTitle("오류가 있습니다!");
			alert.setHeaderText("Warning Dialog");
			alert.setContentText("아이디와 비밀번호 입력에 오류가 있습니다!");
			alert.show();
		}
	}

	// 회원가입 기능
	public void join() {
		JDBCUtil db = new JDBCUtil();
		Connection con = db.getConnection();
		PreparedStatement pstmt = null;
		String inputJoinId = joinId.getText();
		String inputJoinPw = joinPw.getText();
		String inputJoinName = joinName.getText();
		Alert alert = new Alert(AlertType.WARNING);

		if (inputJoinId.isEmpty() && inputJoinPw.isEmpty() && inputJoinName.isEmpty()) {
			alert.setTitle("빈 값이 있습니다!");
			alert.setHeaderText("Warning Dialog");
			alert.setContentText("입력에 오류가 있습니다!");
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
				alert.setTitle("오류가 있습니다!");
				alert.setHeaderText("Warning Dialog");
				alert.setContentText("입력에 오류가 있습니다!");
				alert.show();
			}
		} catch (Exception e) {
			e.printStackTrace();
			alert.setTitle("오류가 있습니다!");
			alert.setHeaderText("Warning Dialog");
			alert.setContentText("입력에 오류가 있습니다!");
			alert.show();
		}
	}

	// 비트연산자를 이용해서 정보 담아보기
	// 플래그값

}