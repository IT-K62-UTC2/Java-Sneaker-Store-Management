package utc2.itk62.sneaker.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import utc2.itk62.sneaker.common.CustomKeyValueCombobox;
import utc2.itk62.sneaker.models.Staff;
import utc2.itk62.sneaker.services.StaffService;

import java.sql.SQLException;
import java.sql.Timestamp;

public class StaffController {
    private static final StaffService staffService = new StaffService();
    private ObservableList<Staff> staffList;
    private ObservableList<CustomKeyValueCombobox<Integer>> positionList = FXCollections.observableArrayList(staffService.getAllIdPositionName());

    public void initialize() throws SQLException {
        updateCurrentProject();

    }

    private void updateCurrentProject() {
        // Gender
        gender.getItems().addAll("Male", "Female", "Other");
        gender.setValue("Male");

        // table view
        staffList = FXCollections.observableArrayList(staffService.getAllStaff());
        colAddress.setCellValueFactory(new PropertyValueFactory<Staff, String>("address"));
        colCccd.setCellValueFactory(new PropertyValueFactory<Staff, String>("cccd"));
        colEmail.setCellValueFactory(new PropertyValueFactory<Staff, String>("email"));
        colFullname.setCellValueFactory(new PropertyValueFactory<Staff, String>("fullName"));
        colPhoneNumber.setCellValueFactory(new PropertyValueFactory<Staff, String>("phoneNumber"));
        colUsername.setCellValueFactory(new PropertyValueFactory<Staff, String>("username"));
        colPosition.setCellValueFactory(new PropertyValueFactory<Staff, Integer>("idPosition"));
        colGender.setCellValueFactory(new PropertyValueFactory<Staff, String>("gender"));
        colCreatedAt.setCellValueFactory(new PropertyValueFactory<Staff, Timestamp>("createdAt"));
        colStatus.setCellValueFactory(new PropertyValueFactory<Staff, Integer>("status"));
        tableListStaff.setItems(staffList);
        tableListStaff.getSelectionModel().selectFirst();
        getStaffCurrentRow();

        // idPosition

        idPosition.setValue(positionList.get(1));
        idPosition.setItems(positionList);

        // keySearch
        ObservableList<CustomKeyValueCombobox<String>> searchList = FXCollections.observableArrayList(
                new CustomKeyValueCombobox<>("id", "Mã nhân viên"),
                new CustomKeyValueCombobox<>("username", "Tên tài khoản"),
                new CustomKeyValueCombobox<>("cccd", "Căn cước công dân"),
                new CustomKeyValueCombobox<>("phone_number", "Số điện thoại")
        );
        keySearch.setItems(searchList);
        keySearch.setValue(searchList.get(0));
    }

    private void getStaffCurrentRow() {
        Staff selectedStaff = tableListStaff.getSelectionModel().getSelectedItem();
        idStaff.setText(String.valueOf(selectedStaff.getId()));
        idPosition.setValue(positionList.get(0));
        username.setText(selectedStaff.getUsername());
        email.setText(selectedStaff.getEmail());
        fullname.setText(selectedStaff.getFullName());
        address.setText(selectedStaff.getAddress());
        phoneNumber.setText(selectedStaff.getPhoneNumber());
        cccd.setText(selectedStaff.getCccd());
        gender.setValue(selectedStaff.getGender());
    }

    @FXML
    public void handleClickedTableView(MouseEvent mouseEvent) {
        getStaffCurrentRow();
    }

    @FXML
    public void handleOnKeyPresedTableView(KeyEvent keyEvent) {
       getStaffCurrentRow();
    }

    @FXML
    public void handleBtnAdd(ActionEvent actionEvent) {
    }

    @FXML
    public void handleBtnUpdate(ActionEvent actionEvent) {
    }

    @FXML
    public void handleBtnDelete(ActionEvent actionEvent) {
    }


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
    public TextField email;
    @FXML
    public ComboBox<CustomKeyValueCombobox<Integer>> idPosition;
    @FXML
    public PasswordField password;
    @FXML
    public TableView<Staff> tableListStaff;
    @FXML
    public TableColumn<Staff, Integer> colStt;
    @FXML
    public TableColumn<Staff, String> colAddress;
    @FXML
    public TableColumn<Staff, String> colUsername;
    @FXML
    public TableColumn<Staff, String> colFullname;
    @FXML
    public TableColumn<Staff, Integer> colPosition;
    @FXML
    public TableColumn<Staff, String> colPhoneNumber;
    @FXML
    public TableColumn<Staff, String> colEmail;
    @FXML
    public TableColumn<Staff, String> colCccd;
    @FXML
    public TableColumn<Staff, String> colGender;
    @FXML
    public TextField search;
    @FXML
    public ComboBox<CustomKeyValueCombobox<String>> keySearch;
    @FXML
    public TableColumn<Staff,Timestamp> colCreatedAt;
    @FXML
    public TableColumn<Staff, Integer> colStatus;
}
