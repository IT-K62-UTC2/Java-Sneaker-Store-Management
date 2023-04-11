package utc2.itk62.sneaker.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import utc2.itk62.sneaker.common.CustomMessageBox;
import utc2.itk62.sneaker.models.Position;
import utc2.itk62.sneaker.models.Staff;
import utc2.itk62.sneaker.services.PositionServie;
import utc2.itk62.sneaker.services.StaffService;

import java.sql.SQLException;
import java.sql.Timestamp;

public class StaffController {
    private static final StaffService staffService = new StaffService();
    private static final PositionServie positionService = new PositionServie();
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
    public ComboBox<Position> position;
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
    public ComboBox<String> keySearch;
    @FXML
    public TableColumn<Staff, Timestamp> colCreatedAt;
    @FXML
    public TableColumn<Staff, Integer> colStatus;
    private ObservableList<Staff> staffList;
    private ObservableList<Position> positionList = FXCollections.observableArrayList(positionService.getAllPosition());

    public void initialize() throws SQLException {
        // Gender
        gender.getItems().clear();
        gender.getItems().addAll("Male", "Female", "Other");
        position.getItems().clear();
        position.setItems(positionList);
        reload();
    }

    private void  reload() {
        // table view
        tableListStaff.getItems().clear();
        staffList = FXCollections.observableArrayList(staffService.getAllStaff());
        colAddress.setCellValueFactory(new PropertyValueFactory<Staff, String>("address"));
        colCccd.setCellValueFactory(new PropertyValueFactory<Staff, String>("cccd"));
        colEmail.setCellValueFactory(new PropertyValueFactory<Staff, String>("email"));
        colFullname.setCellValueFactory(new PropertyValueFactory<Staff, String>("fullName"));
        colPhoneNumber.setCellValueFactory(new PropertyValueFactory<Staff, String>("phoneNumber"));
        colUsername.setCellValueFactory(new PropertyValueFactory<Staff, String>("username"));
        colPosition.setCellValueFactory(new PropertyValueFactory<Staff, Integer>("position"));
        colGender.setCellValueFactory(new PropertyValueFactory<Staff, String>("gender"));
        colCreatedAt.setCellValueFactory(new PropertyValueFactory<Staff, Timestamp>("createdAt"));
        colStatus.setCellValueFactory(new PropertyValueFactory<Staff, Integer>("status"));
        tableListStaff.setItems(staffList);
        tableListStaff.getSelectionModel().selectFirst();


        // keySearch
//        ObservableList<CustomKeyValueCombobox<String>> searchList = FXCollections.observableArrayList(
//                new CustomKeyValueCombobox<>("id", "Mã nhân viên"),
//                new CustomKeyValueCombobox<>("username", "Tên tài khoản"),
//                new CustomKeyValueCombobox<>("cccd", "Căn cước công dân"),
//                new CustomKeyValueCombobox<>("phone_number", "Số điện thoại")
//        );
//        keySearch.setItems(searchList);
//        keySearch.setValue(searchList.get(0));
        getStaffCurrentRow();
    }

    private Staff getStaffCurrentRow() {
        Staff selectedStaff = tableListStaff.getSelectionModel().getSelectedItem();
        idStaff.setText(String.valueOf(selectedStaff.getId()));
        position.setValue(selectedStaff.getPosition());
        username.setText(selectedStaff.getUsername());
        email.setText(selectedStaff.getEmail());
        fullname.setText(selectedStaff.getFullName());
        address.setText(selectedStaff.getAddress());
        phoneNumber.setText(selectedStaff.getPhoneNumber());
        cccd.setText(selectedStaff.getCccd());
        gender.setValue(selectedStaff.getGender());
        return selectedStaff;
    }

    private Staff getStaffCurrentForm() {
        System.out.println(username.getText());
        Staff staff = getStaffCurrentRow();
        System.out.println("last: "+ username.getText());
        staff.setPosition(position.getValue());
        staff.setUsername(username.getText());
        staff.setId(Integer.parseInt(idStaff.getText()));
        staff.setCccd(cccd.getText());
        staff.setFullName(fullname.getText());
        staff.setPhoneNumber(phoneNumber.getText());
        staff.setAddress(address.getText());
        staff.setGender(gender.getValue());
        staff.setEmail(email.getText());
        return staff;
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
    public void handleDeleteStaff(ActionEvent actionEvent) {
        Staff currentSelectStaff = getStaffCurrentRow();
        if (!CustomMessageBox.isYes("Delete", "Are you sure you want to delete")) {
            return;
        }
        if (!staffService.deleteStaff(currentSelectStaff)) {
            CustomMessageBox.boxError("Delete staff failed");
            return;
        }
        tableListStaff.getItems().remove(currentSelectStaff);
        reload();
    }

    public void handleUpdateStaff(ActionEvent actionEvent) {
        Staff currentSelectStaff = getStaffCurrentForm();
        if (!staffService.updateStaff(currentSelectStaff)) {
            CustomMessageBox.boxError("Update staff failed");
            return;
        }
//        reload();
    }
}
