import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("GameBoard.fxml"));

        stage.setTitle("SnakyyyGame");
        stage.setScene(new Scene(root,500,300));
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
