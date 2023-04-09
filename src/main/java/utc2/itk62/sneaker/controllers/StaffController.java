package utc2.itk62.sneaker.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.util.Pair;
import javafx.util.StringConverter;
import utc2.itk62.sneaker.models.Position;
import utc2.itk62.sneaker.services.PositionServie;
import utc2.itk62.sneaker.services.StaffService;

import java.util.List;

public class StaffController {
    private static final StaffService staffService = new StaffService();
    private static final PositionServie positionServie = new PositionServie();

    @FXML
    public TextField idStaff;
    @FXML
    public TextField username;
    @FXML
    public TextField address;
    @FXML
    public TextField phoneNumber;
    @FXML
    public TextField fullname;
    @FXML
    public TextField cccd;
    @FXML
    public ComboBox<String> gender;
    @FXML
    public ComboBox<Pair<String, Integer>> idPosition;
    @FXML
    public PasswordField password;

    public void initialize() {
        // Gender
        gender.getItems().addAll("Male", "Female", "Other");
        gender.setValue("Male");

        // idPosition
        List<Position> positions = positionServie.getAllPosition();
        ObservableList<Pair<String, Integer>> items = FXCollections.observableArrayList();
        for (Position position : positions) {
            items.add(new Pair<String, Integer>(position.getName(), position.getId()));
        }

        idPosition.setItems(items);
        idPosition.setConverter(new StringConverter<Pair<String, Integer>>() {

            @Override
            public String toString(Pair<String, Integer> stringIntegerPair) {
                return stringIntegerPair.getKey();
            }

            @Override
            public Pair<String, Integer> fromString(String s) {
                return null;
            }
        });



    }
}
