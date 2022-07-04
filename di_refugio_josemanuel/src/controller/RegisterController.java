package controller;

import dao.UserDAO;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import io.github.palexdev.materialfx.controls.MFXLabel;
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
import util.SHA;
import view.RefugioApp;

/**
 * FXML Controller class for Register
 *
 * @author josem
 */
public class RegisterController implements Initializable {

    @FXML
    private MFXTextField userTextField;
    @FXML
    private MFXPasswordField passwordTextField;
    @FXML
    private FontAwesomeIconView exitIcon;
    @FXML
    private MFXPasswordField repeatPasswordTextField;
    @FXML
    private FontAwesomeIconView backIcon;
    @FXML
    private MFXLabel checkUserLabel;

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
     * Method to register a new user
     */
    @FXML
    private void registerButtonOnAction(ActionEvent event) {
        String userid = userTextField.getText();
        String password = passwordTextField.getText();
        String repeatPassword = repeatPasswordTextField.getText();

        dao = new UserDAO();

        if (userTextField.getText().isEmpty() || passwordTextField.getText().isEmpty() || repeatPasswordTextField.getText().isEmpty()) {
            checkUserLabel.setText("Por favor, rellene todos lo campos.");
        } else if (dao.getExistingUser(userid)) {
            checkUserLabel.setText("Usuario ya existente, por favor, introduce otro.");
        } else {
            if (!password.equals(repeatPassword)) {
                checkUserLabel.setText("Las contraseñas no son iguales.");
            } else {
                String passHashed = this.hashPassword(password);
                dao.insertUser(userid, passHashed);
                try {
                    Parent root = FXMLLoader.load(getClass().getResource("/view/Login.fxml"));
                    RefugioApp.changeScene(root, "Login");
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }
    
    /**
     * Method to return to Login Scene
     */
    @FXML
    private void handleBack(MouseEvent event) {
        if (event.getSource() == backIcon) {
            try {
                Parent root = FXMLLoader.load(getClass().getResource("/view/Login.fxml"));
                RefugioApp.changeScene(root, "Login");
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }
    
    /**
     * Method to hash and return the given password.
     */
    public String hashPassword(String pass) {
        return SHA.generate512(pass);
    }

}
