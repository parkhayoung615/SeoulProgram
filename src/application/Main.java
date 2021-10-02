package application;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("/layout/Index.fxml"));
			AnchorPane ap = (AnchorPane) loader.load();
			Scene scene = new Scene(ap, 1000, 700);
			
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			// 배경화면 커지지 못하게 ㅎ마
			primaryStage.setResizable(false);
			primaryStage.setTitle("서울시 대학 및 전문대학 정보 프로그램 (2016 ver.)");
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
