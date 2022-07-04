package controller;

import dao.AnimalDAO;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import io.github.palexdev.materialfx.controls.MFXDatePicker;
import io.github.palexdev.materialfx.controls.MFXLabel;
import io.github.palexdev.materialfx.controls.MFXTextField;
import io.github.palexdev.materialfx.controls.legacy.MFXLegacyComboBox;
import java.awt.Component;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.TranslateTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
/*import javax.help.HelpBroker;
import javax.help.HelpSet;
import javax.help.HelpSetException;*/
import model.Animal;
import model.Vaccine;
import view.RefugioApp;

/**
 * FXML Controller class for main application
 *
 * @author josem
 */
public class MainController implements Initializable {

    @FXML
    private VBox vBoxContainer;
    @FXML
    private FontAwesomeIconView exitIcon;
    @FXML
    private FontAwesomeIconView deployMenuIcon;
    @FXML
    private AnchorPane anchorPaneRegisterPet;
    @FXML
    private AnchorPane anchorPaneLastAdoptions;
    @FXML
    private AnchorPane anchorPaneModifyPet;
    @FXML
    private AnchorPane anchorPaneSearchPet;
    @FXML
    private AnchorPane anchorPaneFilterPet;
    @FXML
    private AnchorPane anchorPaneVaccinatePet;
    @FXML
    private MFXLabel idPetToVaccinate;
    @FXML
    private MFXLegacyComboBox<String> comboBoxVaccinate;
    @FXML
    private AnchorPane anchorPaneSeeVaccinate;
    @FXML
    private MFXLabel idPetSeeVaccinate;
    @FXML
    private AnchorPane anchorPaneAdoptPet;
    @FXML
    private MFXLabel namePetAdoptLabel;
    @FXML
    private MFXLabel checkAdoptLabel;
    @FXML
    private TableView<Animal> tableSearchPane;
    @FXML
    private MFXTextField textFieldFilterPane;
    @FXML
    private TableView<Animal> tableFilterPane;
    @FXML
    private TableColumn<Animal, Integer> columnIdSearchPane;
    @FXML
    private TableColumn<Animal, String> columnNameSearchPane;
    @FXML
    private TableColumn<Animal, Character> columnSexSearchPane;
    @FXML
    private TableColumn<Animal, String> columnBornSearchPane;
    @FXML
    private TableColumn<Animal, String> columnColorSearchPane;
    @FXML
    private TableColumn<Animal, Integer> columnRaceSearchPane;
    @FXML
    private TableColumn<Animal, Double> columnWeightSearchPane;
    @FXML
    private TableColumn<Animal, String> columnArriveSearchPane;
    @FXML
    private TableColumn<Animal, String> columnAdoptSearchPane;
    @FXML
    private TableColumn<Animal, String> columnFeaturesSearchPane;
    @FXML
    private TableColumn<Animal, Integer> columnIdFilterPane;
    @FXML
    private TableColumn<Animal, String> columnNameFilterPane;
    @FXML
    private TableColumn<Animal, Character> columnSexFilterPane;
    @FXML
    private TableColumn<Animal, String> columnBornFilterPane;
    @FXML
    private TableColumn<Animal, String> columnColorFilterPane;
    @FXML
    private TableColumn<Animal, Integer> columnRaceFilterPane;
    @FXML
    private TableColumn<Animal, Double> columnWeigthFilterPane;
    @FXML
    private TableColumn<Animal, String> columnArriveFilterPane;
    @FXML
    private TableColumn<Animal, String> columnAdoptFilterPane;
    @FXML
    private TableColumn<Animal, String> columnFeaturesFilterPane;
    @FXML
    private TableColumn<Animal, Integer> columnIdLastAdoptionsPane;
    @FXML
    private TableColumn<Animal, String> columnNameLastAdoptionsPane;
    @FXML
    private TableColumn<Animal, Character> columnSexLastAdoptionsPane;
    @FXML
    private TableColumn<Animal, String> columnBornDateLastAdoptionsPane;
    @FXML
    private TableColumn<Animal, String> columnColorLastAdoptionsPane;
    @FXML
    private TableColumn<Animal, Integer> columnRaceLastAdoptionsPane;
    @FXML
    private TableColumn<Animal, Double> columnWeightLastAdoptionsPane;
    @FXML
    private TableColumn<Animal, String> columnArriveDateLastAdoptionsPane;
    @FXML
    private TableColumn<Animal, String> columnAdoptDateLastAdoptionsPane;
    @FXML
    private TableColumn<Animal, String> columnFeaturesLastAdoptionsPane;
    @FXML
    private MFXTextField textFieldNameRegister;
    @FXML
    private MFXLegacyComboBox<Character> comboBoxSexRegister;
    @FXML
    private MFXLegacyComboBox<String> comboBoxRaceRegister;
    @FXML
    private MFXLegacyComboBox<String> comboBoxColorRegister;
    @FXML
    private MFXTextField textFieldWeigthRegister;
    @FXML
    private MFXTextField textFieldDescriptionRegister;
    @FXML
    private MFXLabel checkRegisterLabel;
    @FXML
    private MFXTextField textFieldNameModify;
    @FXML
    private MFXLegacyComboBox<Character> comboBoxSexModify;
    @FXML
    private MFXDatePicker datePickerBornDateModify;
    @FXML
    private MFXLegacyComboBox<String> comboBoxRaceModify;
    @FXML
    private MFXLegacyComboBox<String> comboBoxColorModify;
    @FXML
    private MFXTextField textFieldWeightModify;
    @FXML
    private MFXTextField textFieldDescriptionModify;
    @FXML
    private MFXLabel checkModifyLabel;
    @FXML
    private MFXLabel labelPetID;
    @FXML
    private MFXLegacyComboBox<String> comboBoxSpecieSearch;
    @FXML
    private MFXLegacyComboBox<String> comboBoxRaceSearch;
    @FXML
    private MFXLegacyComboBox<String> comboBoxColorSearch;
    @FXML
    private TableView<Animal> tableLastAdoptionsPane;
    @FXML
    private MFXLegacyComboBox<String> comboBoxPetTypeAdopt;
    @FXML
    private MFXLabel speciePetAdoptLabel;
    @FXML
    private MFXLabel idPetAdoptLabel;
    @FXML
    private FontAwesomeIconView lockIcon;
    @FXML
    private FontAwesomeIconView arrowIcon;
    @FXML
    private MFXLabel checkVaccinate;
    @FXML
    private MFXLabel namePetToVaccinate;
    @FXML
    private MFXDatePicker datePickerBornRegister;
    @FXML
    private TableView<Vaccine> tableVaccinate;
    @FXML
    private MFXLegacyComboBox<Animal> comboBoxShowVaccinate;
    @FXML
    private TableColumn<Vaccine, String> columnVaccinates;
    @FXML
    private FontAwesomeIconView backIcon;

    private AnimalDAO dao;

    private final ObservableList<Animal> data = FXCollections.observableArrayList();

    private final ObservableList<Vaccine> dataVaccunate = FXCollections.observableArrayList();

    private boolean checkSlideToAdopt;
    @FXML
    private FontAwesomeIconView helpIcon;
    @FXML
    private Button helpButton;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        vBoxContainer.setOpacity(0);
        vBoxContainer.setDisable(true);

        this.setConfigurationAnchorPane(anchorPaneFilterPet, 1, false);

        this.setConfigurationAnchorPane(anchorPaneRegisterPet, 0, true);
        this.setConfigurationAnchorPane(anchorPaneLastAdoptions, 0, true);
        this.setConfigurationAnchorPane(anchorPaneModifyPet, 0, true);
        this.setConfigurationAnchorPane(anchorPaneSearchPet, 0, true);
        this.setConfigurationAnchorPane(anchorPaneSeeVaccinate, 0, true);
        this.setConfigurationAnchorPane(anchorPaneVaccinatePet, 0, true);
        this.setConfigurationAnchorPane(anchorPaneAdoptPet, 0, true);

        this.setDataInTables();

        this.addDataIntoComboBoxes();
        
        //this.help();
    }

    /**
     * Method to close the application
     */
    @FXML
    private void handleClose(MouseEvent event) {
        if (event.getSource() == exitIcon) {
            System.exit(0);
        }
    }

    /**
     * Method to return to Login scene
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
     * Method to open a menu
     */
    @FXML
    private void deployMenuOnClick(MouseEvent event) {
        vBoxContainer.setLayoutX(vBoxContainer.getWidth() * -1);
        this.slideMenu(vBoxContainer.getWidth());
        vBoxContainer.setDisable(false);
        vBoxContainer.setOpacity(1);
        deployMenuIcon.setDisable(true);
    }

    /**
     * Method to see the register pane
     */
    @FXML
    private void registerPetMenuOnAction(ActionEvent event) {
        this.resetMenuProperties();

        this.setConfigurationAnchorPane(anchorPaneRegisterPet, 1, false);

        this.setConfigurationAnchorPane(anchorPaneFilterPet, 0, true);
        this.setConfigurationAnchorPane(anchorPaneLastAdoptions, 0, true);
        this.setConfigurationAnchorPane(anchorPaneModifyPet, 0, true);
        this.setConfigurationAnchorPane(anchorPaneSearchPet, 0, true);
        this.setConfigurationAnchorPane(anchorPaneSeeVaccinate, 0, true);
        this.setConfigurationAnchorPane(anchorPaneVaccinatePet, 0, true);
        this.setConfigurationAnchorPane(anchorPaneAdoptPet, 0, true);
    }

    /**
     * Method to see the search pane
     */
    @FXML
    private void searchPetMenuOnAction(ActionEvent event) {
        this.resetMenuProperties();
        this.setDataInTables();

        this.setConfigurationAnchorPane(anchorPaneSearchPet, 1, false);

        this.setConfigurationAnchorPane(anchorPaneFilterPet, 0, true);
        this.setConfigurationAnchorPane(anchorPaneRegisterPet, 0, true);
        this.setConfigurationAnchorPane(anchorPaneModifyPet, 0, true);
        this.setConfigurationAnchorPane(anchorPaneAdoptPet, 0, true);
        this.setConfigurationAnchorPane(anchorPaneSeeVaccinate, 0, true);
        this.setConfigurationAnchorPane(anchorPaneVaccinatePet, 0, true);
        this.setConfigurationAnchorPane(anchorPaneLastAdoptions, 0, true);
    }

    /**
     * Method to see the filter pane
     */
    @FXML
    private void filterPetMenuOnAction(ActionEvent event) {
        this.resetMenuProperties();
        this.setDataInTables();

        this.setConfigurationAnchorPane(anchorPaneFilterPet, 1, false);

        this.setConfigurationAnchorPane(anchorPaneSearchPet, 0, true);
        this.setConfigurationAnchorPane(anchorPaneRegisterPet, 0, true);
        this.setConfigurationAnchorPane(anchorPaneModifyPet, 0, true);
        this.setConfigurationAnchorPane(anchorPaneAdoptPet, 0, true);
        this.setConfigurationAnchorPane(anchorPaneSeeVaccinate, 0, true);
        this.setConfigurationAnchorPane(anchorPaneVaccinatePet, 0, true);
        this.setConfigurationAnchorPane(anchorPaneLastAdoptions, 0, true);
    }

    /**
     * Method to see the last adoptions pane
     */
    @FXML
    private void lastAdoptionsMenuOnAction(ActionEvent event) {
        this.resetMenuProperties();

        dao = new AnimalDAO();
        Collection<Animal> animalsAdopted = dao.getAdoptedPets();
        data.setAll(animalsAdopted);
        tableLastAdoptionsPane.setItems(data);

        this.setConfigurationAnchorPane(anchorPaneLastAdoptions, 1, false);

        this.setConfigurationAnchorPane(anchorPaneFilterPet, 0, true);
        this.setConfigurationAnchorPane(anchorPaneRegisterPet, 0, true);
        this.setConfigurationAnchorPane(anchorPaneModifyPet, 0, true);
        this.setConfigurationAnchorPane(anchorPaneSearchPet, 0, true);
        this.setConfigurationAnchorPane(anchorPaneSeeVaccinate, 0, true);
        this.setConfigurationAnchorPane(anchorPaneVaccinatePet, 0, true);
        this.setConfigurationAnchorPane(anchorPaneAdoptPet, 0, true);
    }

    /**
     * Method to see the adopt pet pane
     */
    @FXML
    private void adoptPetMenuOnAction(ActionEvent event) {
        this.resetMenuProperties();

        this.setConfigurationAnchorPane(anchorPaneAdoptPet, 1, false);

        this.setConfigurationAnchorPane(anchorPaneFilterPet, 0, true);
        this.setConfigurationAnchorPane(anchorPaneRegisterPet, 0, true);
        this.setConfigurationAnchorPane(anchorPaneModifyPet, 0, true);
        this.setConfigurationAnchorPane(anchorPaneSearchPet, 0, true);
        this.setConfigurationAnchorPane(anchorPaneSeeVaccinate, 0, true);
        this.setConfigurationAnchorPane(anchorPaneVaccinatePet, 0, true);
        this.setConfigurationAnchorPane(anchorPaneLastAdoptions, 0, true);
    }

    /**
     * Method to see the vaccinate pane
     */
    @FXML
    private void vaccineMenuOnAction(ActionEvent event) {
        this.resetMenuProperties();
        dao = new AnimalDAO();
        comboBoxShowVaccinate.setItems(dao.getAnimalsName());

        this.setConfigurationAnchorPane(anchorPaneSeeVaccinate, 1, false);

        this.setConfigurationAnchorPane(anchorPaneRegisterPet, 0, true);
        this.setConfigurationAnchorPane(anchorPaneLastAdoptions, 0, true);
        this.setConfigurationAnchorPane(anchorPaneModifyPet, 0, true);
        this.setConfigurationAnchorPane(anchorPaneSearchPet, 0, true);
        this.setConfigurationAnchorPane(anchorPaneFilterPet, 0, true);
        this.setConfigurationAnchorPane(anchorPaneVaccinatePet, 0, true);
        this.setConfigurationAnchorPane(anchorPaneAdoptPet, 0, true);
    }

    /**
     * Method to register a pet on database
     */
    @FXML
    private void registerPetOnAction(ActionEvent event) {
        String nName = textFieldNameRegister.getText();
        Character nSex = comboBoxSexRegister.getValue();
        LocalDate nBornDate = datePickerBornRegister.getDate();
        String nColor = comboBoxColorRegister.getValue();
        String nRace = comboBoxRaceRegister.getValue();
        Double nWeigth = null;
        if (!textFieldWeigthRegister.getText().isEmpty()) {
            try {
                nWeigth = Double.parseDouble(textFieldWeigthRegister.getText());
            } catch (NumberFormatException e) {
            }
        }
        String nDescription = textFieldDescriptionRegister.getText();
        if (textFieldNameRegister.getText().isEmpty() || nSex == null || nBornDate == null || nColor == null || nRace == null || nWeigth == null || textFieldDescriptionRegister.getText().isEmpty()) {
            checkRegisterLabel.setText("Por favor, complete todos los campos correctamente.");
        } else {
            Animal nAimal = new Animal(nName, nSex, nBornDate, nColor, nRace, nWeigth, nDescription);
            dao = new AnimalDAO();
            dao.registerPet(nAimal);
            checkRegisterLabel.setText("¡Mascota registrada correctamente!");
            textFieldNameRegister.setText("");
            comboBoxSexRegister.valueProperty().set(null);
            comboBoxColorRegister.valueProperty().set(null);
            comboBoxRaceRegister.valueProperty().set(null);
            textFieldWeigthRegister.setText("");
            textFieldDescriptionRegister.setText("");
        }
    }

    /**
     * Method to modify a pet of the database
     */
    @FXML
    private void modifyPetOnAction(ActionEvent event) {
        int id = Integer.parseInt(labelPetID.getText());
        String nName = textFieldNameModify.getText();
        Character nSex = comboBoxSexModify.getValue();
        LocalDate nBornDate = datePickerBornDateModify.getDate();
        String nColor = comboBoxColorModify.getValue();
        String nRace = comboBoxRaceModify.getValue();
        Double nWeigth = null;
        if (!textFieldWeightModify.getText().isEmpty()) {
            try {
                nWeigth = Double.parseDouble(textFieldWeightModify.getText());
            } catch (NumberFormatException e) {
            }
        }
        String nDescription = textFieldDescriptionModify.getText();
        if (textFieldNameModify.getText().isEmpty() || nSex == null || nBornDate == null || nColor == null || nRace == null || nWeigth == null || textFieldDescriptionModify.getText().isEmpty()) {
            checkModifyLabel.setText("Por favor, complete todos los campos correctamente.");
        } else {
            Animal nAnimal = new Animal(id, nName, nSex, nBornDate, nColor, nRace, nWeigth, nDescription);
            dao = new AnimalDAO();
            dao.modifyPet(nAnimal);
            checkModifyLabel.setText("¡Mascota actualizada correctamente!");
        }
    }

    /**
     * Method to search a pet according to different parameters
     */
    @FXML
    private void searchPetOnAction(ActionEvent event) {
        String specie = "";
        String race = "";
        String color = "";
        if (comboBoxSpecieSearch.getValue() != null) {
            specie = comboBoxSpecieSearch.getValue();
        }
        if (comboBoxRaceSearch.getValue() != null) {
            race = comboBoxRaceSearch.getValue();
        }
        if (comboBoxColorSearch.getValue() != null) {
            color = comboBoxColorSearch.getValue() + "";
        }

        dao = new AnimalDAO();
        Collection<Animal> animales = dao.getSearch(specie, race, color);

        data.setAll(animales);

        tableSearchPane.setItems(data);
    }

    /**
     * Method to vaccinate a pet
     */
    @FXML
    private void vaccinateOnAction(ActionEvent event) {
        if (comboBoxVaccinate.getValue() != null) {
            int id = Integer.parseInt(idPetToVaccinate.getText());
            String vacName = comboBoxVaccinate.getValue();
            dao = new AnimalDAO();
            dao.vaccinatePet(id, vacName);
            checkVaccinate.setText("¡Animal vacunado correctamente!");
        } else {
            checkVaccinate.setText("Por favor, seleccione una vacuna.");
        }
    }

    /**
     * Method to adopt a pet
     */
    @FXML
    private void adoptPetOnAction(ActionEvent event) {
        if (!idPetAdoptLabel.textProperty().getValue().equals("")) {
            if (checkSlideToAdopt) {
                dao = new AnimalDAO();
                dao.adoptPet(idPetAdoptLabel.textProperty().getValue());
                checkAdoptLabel.setText("¡Animal adoptado correctamente!");
                comboBoxPetTypeAdopt.valueProperty().set(null);
                idPetAdoptLabel.setText("");
                namePetAdoptLabel.setText("");
                speciePetAdoptLabel.setText("");
            } else {
                checkAdoptLabel.setText("Por favor, deslice para poder adoptar.");
            }
        } else {
            checkAdoptLabel.setText("Por favor, seleccione alguna especie para poder adoptarla.");
        }
        lockIcon.setGlyphName("LOCK");
        checkSlideToAdopt = false;
    }

    /**
     * Method to open modify pane according to the selected pet
     */
    @FXML
    private void modifyButtonPaneOnAction(ActionEvent event) {
        Animal nAnimal;
        if (!anchorPaneFilterPet.isDisabled()) {
            nAnimal = tableFilterPane.getSelectionModel().getSelectedItem();
        } else {
            nAnimal = tableSearchPane.getSelectionModel().getSelectedItem();
        }
        if (nAnimal != null) {
            this.resetMenuProperties();

            this.setConfigurationAnchorPane(anchorPaneModifyPet, 1, false);
            this.setConfigurationAnchorPane(anchorPaneFilterPet, 0, true);
            this.setConfigurationAnchorPane(anchorPaneSearchPet, 0, true);

            labelPetID.setText(nAnimal.getId() + "");
            textFieldNameModify.setText(nAnimal.getNombre());
            comboBoxSexModify.valueProperty().set(nAnimal.getSexo());
            datePickerBornDateModify.getContent().currentDateProperty().set(nAnimal.getFechaNacimiento());
            comboBoxColorModify.valueProperty().set(nAnimal.getColor());
            dao = new AnimalDAO();
            String raceName = dao.getRaceName(nAnimal);
            comboBoxRaceModify.valueProperty().set(raceName);
            textFieldWeightModify.setText(nAnimal.getPeso() + "");
            textFieldDescriptionModify.setText(nAnimal.getCaracteristicas());
        }
    }

    /**
     * Method to open vaccinate pane according to the selected pet
     */
    @FXML
    private void vaccinateButtonPaneOnAction(ActionEvent event) {
        Animal nAnimal;
        if (!anchorPaneFilterPet.isDisabled()) {
            nAnimal = tableFilterPane.getSelectionModel().getSelectedItem();
        } else {
            nAnimal = tableSearchPane.getSelectionModel().getSelectedItem();
        }
        if (nAnimal != null) {
            this.resetMenuProperties();

            this.setConfigurationAnchorPane(anchorPaneVaccinatePet, 1, false);
            this.setConfigurationAnchorPane(anchorPaneFilterPet, 0, true);
            this.setConfigurationAnchorPane(anchorPaneSearchPet, 0, true);

            idPetToVaccinate.setText(nAnimal.getId() + "");
            namePetToVaccinate.setText(nAnimal.getNombre());
            dao = new AnimalDAO();
            comboBoxVaccinate.setItems(dao.getAllVaccinate(nAnimal.getId()));
        }
    }

    /**
     * Method to reset all the default properties
     */
    private void resetMenuProperties() {
        vBoxContainer.setOpacity(0);
        vBoxContainer.setDisable(true);
        deployMenuIcon.setDisable(false);
        this.slideMenu(vBoxContainer.getWidth() * -1);
        checkRegisterLabel.setText("");

        textFieldNameRegister.setText("");
        comboBoxSexRegister.valueProperty().set(null);
        comboBoxColorRegister.valueProperty().set(null);
        comboBoxRaceRegister.valueProperty().set(null);
        textFieldWeigthRegister.setText("");
        textFieldDescriptionRegister.setText("");
        comboBoxColorSearch.valueProperty().set(null);
        comboBoxRaceSearch.valueProperty().set(null);
        comboBoxSpecieSearch.valueProperty().set(null);
        checkAdoptLabel.setText("");
        comboBoxPetTypeAdopt.valueProperty().set(null);
        idPetAdoptLabel.setText("");
        namePetAdoptLabel.setText("");
        speciePetAdoptLabel.setText("");
        lockIcon.setGlyphName("LOCK");
        checkSlideToAdopt = false;
        checkVaccinate.setText("");
        idPetSeeVaccinate.setText("");
        tableVaccinate.setItems(null);
    }

    /**
     * Method to get a slide menu
     */
    private void slideMenu(Double width) {
        TranslateTransition slide = new TranslateTransition();
        slide.setDuration(Duration.seconds(0.8));
        slide.setNode(vBoxContainer);
        slide.setToX(width);
        slide.play();
    }

    /**
     * Method to hide/show and disable/enable a Pane
     */
    private void setConfigurationAnchorPane(AnchorPane pane, int n, boolean action) {
        pane.setOpacity(n);
        pane.setDisable(action);
    }

    /**
     * Method to set same values to diferent columns
     */
    private void setDataInColumns(TableColumn column1, TableColumn column2, TableColumn column3, String dataColumn) {
        column1.setCellValueFactory(new PropertyValueFactory<>(dataColumn));
        column2.setCellValueFactory(new PropertyValueFactory<>(dataColumn));
        column3.setCellValueFactory(new PropertyValueFactory<>(dataColumn));
    }

    /**
     * Method to set values on the different columns and tables
     */
    private void setDataInTables() {
        this.setDataInColumns(columnIdSearchPane, columnIdFilterPane, columnIdLastAdoptionsPane, "id");
        this.setDataInColumns(columnNameSearchPane, columnNameFilterPane, columnNameLastAdoptionsPane, "nombre");
        this.setDataInColumns(columnSexSearchPane, columnSexFilterPane, columnSexLastAdoptionsPane, "sexo");
        this.setDataInColumns(columnBornSearchPane, columnBornFilterPane, columnBornDateLastAdoptionsPane, "fechaNacimiento");
        this.setDataInColumns(columnColorSearchPane, columnColorFilterPane, columnColorLastAdoptionsPane, "color");
        this.setDataInColumns(columnRaceSearchPane, columnRaceFilterPane, columnRaceLastAdoptionsPane, "idRaza");
        this.setDataInColumns(columnWeightSearchPane, columnWeigthFilterPane, columnWeightLastAdoptionsPane, "peso");
        this.setDataInColumns(columnArriveSearchPane, columnArriveFilterPane, columnArriveDateLastAdoptionsPane, "fechaLlegada");
        this.setDataInColumns(columnAdoptSearchPane, columnAdoptFilterPane, columnAdoptDateLastAdoptionsPane, "fechaAdopcon");
        this.setDataInColumns(columnFeaturesSearchPane, columnFeaturesFilterPane, columnFeaturesLastAdoptionsPane, "caracteristicas");

        dao = new AnimalDAO();
        Collection<Animal> animals = dao.getAll();
        data.setAll(animals);

        tableSearchPane.setItems(data);
        tableFilterPane.setItems(filterData());
    }

    /**
     * Method to return a sorted data for filtered table
     */
    public SortedList<Animal> filterData() {
        FilteredList<Animal> filteredData = new FilteredList<>(data, p -> true);

        textFieldFilterPane.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(animal -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();

                if (animal.getNombre().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (animal.getColor().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (animal.getCaracteristicas().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (animal.getIdRaza().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (String.valueOf(animal.getId()).toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (String.valueOf(animal.getSexo()).toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                }
                return false;
            });
        });

        SortedList<Animal> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(tableFilterPane.comparatorProperty());
        return sortedData;
    }

    /**
     * Method to set data into the differents combo boxes
     */
    private void addDataIntoComboBoxes() {
        comboBoxSexRegister.setItems(FXCollections.observableArrayList('M', 'H'));
        comboBoxSexModify.setItems(FXCollections.observableArrayList('M', 'H'));
        comboBoxColorRegister.setItems(FXCollections.observableArrayList("blanco", "negro", "gris", "marrón", "amarillo", "rojo", "naranja"));
        comboBoxColorModify.setItems(FXCollections.observableArrayList("blanco", "negro", "gris", "marrón", "amarillo", "rojo", "naranja"));
        comboBoxColorSearch.setItems(FXCollections.observableArrayList("blanco", "negro", "gris", "marrón", "amarillo", "rojo", "naranja"));
        dao = new AnimalDAO();
        comboBoxRaceRegister.setItems(dao.getRacesName());
        comboBoxRaceModify.setItems(dao.getRacesName());
        comboBoxSpecieSearch.setItems(dao.getAllSpecie());
        comboBoxRaceSearch.setItems(dao.getRacesName());
        comboBoxPetTypeAdopt.setItems(dao.getSpecieForAdopt());
    }

    /**
     * Method to set information on adopt pane
     */
    @FXML
    private void setNameSpecieToAdoptOnAction(ActionEvent event) {
        if (comboBoxPetTypeAdopt.getValue() != null) {
            String choice = comboBoxPetTypeAdopt.getValue();
            dao = new AnimalDAO();
            ArrayList<String> information = (ArrayList) dao.getPetToAdopt(choice);
            if (!information.isEmpty()) {
                idPetAdoptLabel.setText(information.get(0));
                namePetAdoptLabel.setText(information.get(1));
                speciePetAdoptLabel.setText(information.get(2));
                checkAdoptLabel.setText("");
            } else {
                idPetAdoptLabel.setText("");
                namePetAdoptLabel.setText("");
                speciePetAdoptLabel.setText("");
                checkAdoptLabel.setText("¡Todos los animales de este tipo han sido adoptados!");
            }
        }
    }

    /**
     * Method to set the drag over on adopt pane
     */
    @FXML
    private void handleDragOver(DragEvent event) {
        event.acceptTransferModes(TransferMode.COPY_OR_MOVE);

        event.consume();
    }

    /**
     * Method to set the drag dropped on adopt pane
     */
    @FXML
    private void handleDragDropped(DragEvent event) throws FileNotFoundException {
        lockIcon.setGlyphName("UNLOCK");
        checkSlideToAdopt = true;
        event.setDropCompleted(true);

        event.consume();
    }

    /**
     * Method to set the drag detected on adopt pane
     */
    @FXML
    private void handleDragDetected(MouseEvent event) {
        Dragboard db = arrowIcon.startDragAndDrop(TransferMode.ANY);

        ClipboardContent content = new ClipboardContent();
        content.putString(arrowIcon.getGlyphName());
        db.setContent(content);

        event.consume();
    }

    /**
     * Method to show the vaccines of a pet
     */
    @FXML
    private void comboBoxShowVaccinateOnAction(ActionEvent event) {
        dao = new AnimalDAO();
        int id = comboBoxShowVaccinate.getValue().getId();
        idPetSeeVaccinate.setText(String.valueOf(id));

        ArrayList<Vaccine> vaccinates = (ArrayList<Vaccine>) dao.showVaccinateInfo(id);

        columnVaccinates.setCellValueFactory(new PropertyValueFactory<>("name"));

        dataVaccunate.setAll(vaccinates);

        tableVaccinate.setItems(dataVaccunate);
    }

    /*private void help() {
        
        try {
            File fichero = new File("javahelp"+File.separator+"help_set.hs");
            URL hsURL = fichero.toURI().toURL();
            
            HelpSet helpset = new HelpSet(getClass().getClassLoader(),hsURL);
            HelpBroker hb = helpset.createHelpBroker();
            
            //hb.enableHelpOnButton(helpIcon.typeOfT., "refugio", helpset);
            hb.enableHelpOnButton(helpIcon, "refugio", helpset, "", "");
            
            
        } catch (MalformedURLException e) {
            System.out.println(e.getMessage());
        } catch (HelpSetException ex) {
            System.out.println(ex.getMessage());
        }
    }*/
}
