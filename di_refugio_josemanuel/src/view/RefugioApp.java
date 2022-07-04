package view;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * Main class of the application
 * @author josem
 */
public class RefugioApp extends Application {

    private static double xOffset = 0.0;
    private static double yOffset = 0.0;
    private static Scene scene;
    private static Stage stage;
    
    /**
     * Method to move the stage
     */
    public static void moveStage(Parent root, Stage stage) {
        root.setOnMousePressed((MouseEvent t) -> {
            xOffset = t.getSceneX();
            yOffset = t.getSceneY();
        });

        root.setOnMouseDragged((MouseEvent t) -> {
            stage.setX(t.getScreenX() - xOffset);
            stage.setY(t.getScreenY() - yOffset);
        });
    }

    @Override
    public void start(Stage stage) throws Exception {
        RefugioApp.stage = stage;
        Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));

        moveStage(root, RefugioApp.stage);

        scene = new Scene(root);
        RefugioApp.stage.setTitle("Login");
        RefugioApp.stage.initStyle(StageStyle.UNDECORATED);
        RefugioApp.stage.setScene(scene);
        RefugioApp.stage.show();
    }

    /**
     * Method to change the scene 
     */
    public static void changeScene(Parent root, String nTitle) {
        scene = new Scene(root);
        moveStage(root, stage);
        stage.setTitle(nTitle);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
