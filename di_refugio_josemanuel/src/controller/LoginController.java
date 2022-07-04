package controller;

import dao.UserDAO;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import io.github.palexdev.materialfx.controls.MFXPasswordField;
import io.github.palexdev.materialfx.controls.MFXTextField;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import view.RefugioApp;
import util.SHA;

/**
 * FXML Controller class for Login
 *
 * @author josem
 */
public class LoginController implements Initializable {

    @FXML
    private FontAwesomeIconView exitIcon;
    @FXML
    private MFXTextField userTextField;
    @FXML
    private MFXPasswordField passwordTextField;

    private UserDAO dao;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }
    
    /**
     * Method to close the application.
     */
    @FXML
    private void handleClose(MouseEvent event) {
        if (event.getSource() == exitIcon) {
            System.exit(0);
        }
    }
    
    /**
     * Method to log into the application. 
     */
    @FXML
    private void loginButtonOnAction(ActionEvent event) {
        if (checkUser()) {
            try {
                Parent root = FXMLLoader.load(getClass().getResource("/view/Main.fxml"));
                RefugioApp.changeScene(root, "Main");
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        } else {
            userTextField.unfocusedLineColorProperty().set(Color.RED);
            passwordTextField.unfocusedLineColorProperty().set(Color.RED);
        }
    }
    
    /**
     * Method to check if the password provided (hashed) is equal to the password on database. 
     */
    private boolean checkUser() {
        String passwordHashed = SHA.generate512(passwordTextField.getText());
        dao = new UserDAO();
        String passwordDB = dao.getPassword(userTextField.getText());

        return passwordDB.equals(passwordHashed);

    }
    
    /**
     * Method to open the register scene.
     */
    private void registerOnMouseClicked(MouseEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/view/Register.fxml"));
            RefugioApp.changeScene(root, "Register");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

}
