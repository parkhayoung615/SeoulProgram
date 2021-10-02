package layout;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import util.JDBCUtil;

public class SubController {

	@FXML
	private TextField search;
	@FXML
	private Button showBtn;
	private Stage pop;
	@FXML
	private TableView<GetterSetter> list;
	private ObservableList<GetterSetter> items;
	@FXML
	private TableColumn<GetterSetter, String> idColumn;
	@FXML
	private TableColumn<GetterSetter, String> nameColumn;
	@FXML
	private TableColumn<GetterSetter, String> addrColumn;
	// 리스트 뷰
	static String item;
	@FXML
	private ListView<LikeGS> list2;
	@FXML
	private ObservableList<LikeGS> items2;

	@FXML
	private void initialize() {
		items = FXCollections.observableArrayList();
		list.setItems(items);
		listUp();
		// items2 = FXCollections.observableArrayList();
		// list2.setItems(items2);
	}

	// MainLayout 리스트업
	public void listUp() {
		idColumn.setCellValueFactory(cellData -> cellData.getValue().getId());
		nameColumn.setCellValueFactory(cellData -> cellData.getValue().getAddress());
		addrColumn.setCellValueFactory(cellData -> cellData.getValue().getName());

		JDBCUtil db = new JDBCUtil();
		Connection con = db.getConnection();
		Statement stmt = null;
		ResultSet rs = null;

		String sql = "SELECT u.name, a.addr_all, u.university_id FROM university u, address a WHERE u.university_id = a.university_id ORDER BY 1 ASC";
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);

			while (rs.next()) {
				String id = rs.getString("university_id");
				String name = rs.getString("name");
				String address = rs.getString("addr_all");

				GetterSetter gs = new GetterSetter(id, name, address);
				items.add(gs);
			}
		} catch (Exception e) {
			System.out.println("오류");
		}
	}

	// 새 탭으로 상세 페이지 열기
	// 스레드 충돌남
	public void ChagePageScene() {
		Stage mainStage = (Stage) showBtn.getScene().getWindow();
		int idx = list.getSelectionModel().getSelectedIndex();
		if (idx >= 0) {
			pop = new Stage(StageStyle.DECORATED);
			pop.initModality(Modality.WINDOW_MODAL);
			pop.initOwner(mainStage);

			try {
				Parent root = FXMLLoader.load(getClass().getResource("/MainScene/page.fxml"));
				Scene sc = new Scene(root);
				sc.getStylesheets().add(getClass().getResource("/application/application.css").toExternalForm());

				pop.setScene(sc);
				pop.setTitle("새 탭");
				pop.setResizable(false);
				pop.show();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	// 즐겨찾기 리스트업 함수
	public void Like() {
		JDBCUtil db = new JDBCUtil();
		Connection con = db.getConnection();
		Statement stmt = null;
		ResultSet rs = null;

		String sql = "SELECT name WHERE like ORDER BY 1 ASC";
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);

			while (rs.next()) {
				String name = rs.getString("name");
				LikeGS gs = new LikeGS(name);
				items2.add(gs);
			}
		} catch (Exception e) {
			System.out.println("오류");
		}
	}

	// MainLayout 상세 페이지 리스트업
	public void listUpPage() {
//		JDBCUtil db = new JDBCUtil();
//		Connection con = db.getConnection();
//		PreparedStatement pstmt = null;
//		String sql = "INSERT INTO `user`(`id`, `name`, `password`) VALUES (?,?,?)";

//		try {
//			pstmt = con.prepareStatement(sql);
//			pstmt.setString(1, inputJoinId);
//			pstmt.setString(2, inputJoinName);
//			pstmt.setString(3, inputJoinPw);
//			pstmt.executeUpdate();
//			try {
//				Parent login = FXMLLoader.load(getClass().getResource("/layout/Login.fxml"));
//				Scene scene = new Scene(login);
//				Stage primaryStage = (Stage) GoLoginBtn.getScene().getWindow();
//				scene.getStylesheets().add(getClass().getResource("/application/application.css").toExternalForm());
//				primaryStage.setScene(scene);
//			} catch (Exception e) {
//				e.printStackTrace();
//				alert.setTitle("오류가 있습니다!");
//				alert.setHeaderText("Warning Dialog");
//				alert.setContentText("입력에 오류가 있습니다!");
//				alert.show();
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//			alert.setTitle("오류가 있습니다!");
//			alert.setHeaderText("Warning Dialog");
//			alert.setContentText("입력에 오류가 있습니다!");
//			alert.show();
//		}
	}

}
