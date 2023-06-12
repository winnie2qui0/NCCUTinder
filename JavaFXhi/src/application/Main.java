package application;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;


public class Main extends Application {
	private Database db= new Database();

	@Override
	public void start(Stage primaryStage) {
		try {
			Parent root = 	FXMLLoader.load(getClass().getResource("Login.fxml"));
			Scene scene = new Scene(root,1220,740);
			primaryStage.setTitle("NCCU Tinder");
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	@Override
    public void stop() {
        // your application stop code
        db.shutdownExecutorService(); // call to stop your executor service
    }
	
	public static void main(String[] args) {
		launch(args);
	}
}
