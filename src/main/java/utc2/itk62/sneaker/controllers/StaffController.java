package utc2.itk62.sneaker.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import utc2.itk62.sneaker.Validator.StaffValidator;
import utc2.itk62.sneaker.models.Position;
import utc2.itk62.sneaker.models.Staff;
import utc2.itk62.sneaker.services.PositionServie;
import utc2.itk62.sneaker.services.StaffService;
import utc2.itk62.sneaker.util.CustomMessageBox;

import java.io.File;
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
    @FXML
    public TextField valueSearch;
    @FXML
    public RadioButton femaleBtn;
    @FXML
    public RadioButton maleBtn;
    @FXML
    public ImageView imageAvatar;

    @FXML
    public DatePicker toDate;
    @FXML
    public DatePicker fromDate;
    @FXML
    public Button btnAddImage;
    @FXML
    public TableColumn< Staff,Integer> colIdStaff;
    @FXML
    public Button btnExportExcel;

    private ToggleGroup toggleGenderGroup = new ToggleGroup();
    private ObservableList<Staff> staffList;
    private ObservableList<Position> positionList = FXCollections.observableArrayList(positionService.getAllPosition());
    private ObservableList<String> listKetSearch = FXCollections.observableArrayList(
            "ID","Username", "Name", "Address", "PhoneNumber", "CCCD");

    public void initialize() throws SQLException {
        // Gender
        maleBtn.setToggleGroup(toggleGenderGroup);
        femaleBtn.setToggleGroup(toggleGenderGroup);
        toggleGenderGroup.selectToggle(maleBtn);

        // Position
        position.getItems().clear();
        position.setItems(positionList);
        reloadTableView();


        // Search
        keySearch.setItems(listKetSearch);
        keySearch.setValue("ID");
    }

    private String getGender() {
        RadioButton radioButton = (RadioButton)toggleGenderGroup.getSelectedToggle();
        return radioButton.getText();
    }

    private void reloadTableView() {
        // table view
        tableListStaff.getItems().clear();
        staffList = FXCollections.observableArrayList(staffService.getAllStaff());
        colAddress.setCellValueFactory(new PropertyValueFactory<Staff, String>("address"));
        colIdStaff.setCellValueFactory(new PropertyValueFactory<Staff, Integer>("id"));
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
        updateStaffCurrentRowToForm();
    }

    private Staff updateStaffCurrentRowToForm() {
        Staff selectedStaff = tableListStaff.getSelectionModel().getSelectedItem();
        if (selectedStaff == null) {
            cleanForm();
            return null;
        }
        idStaff.setText(String.valueOf(selectedStaff.getId()));
        position.setValue(selectedStaff.getPosition());
        username.setText(selectedStaff.getUsername());
        email.setText(selectedStaff.getEmail());
        fullname.setText(selectedStaff.getFullName());
        address.setText(selectedStaff.getAddress());
        phoneNumber.setText(selectedStaff.getPhoneNumber());
        cccd.setText(selectedStaff.getCccd());
        imageAvatar.setImage(new Image(selectedStaff.getPathAvatar()));
//        gender.setValue(selectedStaff.getGender());
        toggleGenderGroup.selectToggle(selectedStaff.getGender().equals(maleBtn.getText()) ? maleBtn : femaleBtn);
        return selectedStaff;
    }

    private Staff getStaffCurrentRow() {
        return tableListStaff.getSelectionModel().getSelectedItem();
    }

    private Staff getStaffCurrentForm() {
        Staff staff = getStaffCurrentRow();
        staff.setPosition(position.getValue());
        staff.setUsername(username.getText());
        staff.setId(Integer.parseInt(idStaff.getText()));
        staff.setCccd(cccd.getText());
        staff.setFullName(fullname.getText());
        staff.setPhoneNumber(phoneNumber.getText());
        staff.setAddress(address.getText());
        staff.setPathAvatar(imageAvatar.getImage().getUrl());
//        staff.setGender(gender.getValue());
        staff.setGender(getGender());
        staff.setEmail(email.getText());
        return staff;
    }


    @FXML
    public void handleClickedTableView(MouseEvent mouseEvent) {
        updateStaffCurrentRowToForm();
    }

    @FXML
    public void handleOnKeyPresedTableView(KeyEvent keyEvent) {
        updateStaffCurrentRowToForm();
    }

    @FXML
    public void handleDeleteStaff(ActionEvent actionEvent) {
        Staff currentSelectStaff = updateStaffCurrentRowToForm();
        if (!CustomMessageBox.isYes("Delete", "Are you sure you want to delete")) {
            return;
        }
        if (!staffService.deleteStaff(currentSelectStaff)) {
            CustomMessageBox.boxError("Delete staff failed");
            return;
        }
        tableListStaff.getItems().remove(currentSelectStaff);
        reloadTableView();
    }

    @FXML
    public void handleUpdateStaff(ActionEvent actionEvent) {
        Staff currentSelectStaff = getStaffCurrentForm();
        if (!staffService.updateStaff(currentSelectStaff)) {
            CustomMessageBox.boxError("Update staff failed");
            return;
        }
        CustomMessageBox.boxOk("Update staff");
        reloadTableView();
    }

    @FXML
    public void handleAddStaff(ActionEvent actionEvent) {
        tableListStaff.getSelectionModel().clearSelection();
        if(idStaff.getText() != null && idStaff.getText().length() > 0) {
            cleanForm();
        }
        // Validate image
        if(!StaffValidator.validateImageAvatar(imageAvatar)) {
            CustomMessageBox.boxInfo("Please select an image");
            btnAddImage.fire();
            username.requestFocus();
            return;
        }

        // Validate user name
        if(!StaffValidator.validateUsername(username.getText())) {
            CustomMessageBox.boxError("Invalid username");
            username.requestFocus();
            return;
        }

        if(StaffValidator.isExistUsername(username.getText())) {
            CustomMessageBox.boxError("Username is exists");
            username.requestFocus();
            return;
        }

        // Validate full name
        if(!StaffValidator.validateFullname(fullname.getText())) {
            CustomMessageBox.boxError("Invalid full name");
            fullname.requestFocus();
            return;
        }

        // Validate address
        if(!StaffValidator.validateAddress(address.getText())) {
            CustomMessageBox.boxError("Invalid address");
            address.requestFocus();
            return;
        }

        // Validate phone number
        if (!StaffValidator.validatePhoneNumber(phoneNumber.getText())){
            CustomMessageBox.boxError("Invalid phone number");
            phoneNumber.requestFocus();
            return;
        }
        if(StaffValidator.isExistPhoneNumber(phoneNumber.getText())) {
            CustomMessageBox.boxError("Phone number is exists");
            phoneNumber.requestFocus();
            return;
        }

        // Validate CCCD
        if(!StaffValidator.validateCCCD(cccd.getText())){
            CustomMessageBox.boxError("Invalid cccd");
            cccd.requestFocus();
            return;
        }
        if(StaffValidator.isExistCCCD(cccd.getText())) {
            CustomMessageBox.boxError("CCCD is exists");
            cccd.requestFocus();
            return;
        }

        // Validate email
        if(!StaffValidator.validateEmail(email.getText())){
            CustomMessageBox.boxError("Invalid email");
            email.requestFocus();
            return;
        }

        if(StaffValidator.isExistEmail(email.getText())) {
            CustomMessageBox.boxError("Email is exists");
            email.requestFocus();
            return;
        }

        Staff staff = new Staff();
        staff.setPassword("1");
        staff.setGender(getGender());
        staff.setPathAvatar(imageAvatar.getImage().getUrl());
        staff.setEmail(email.getText());
        staff.setFullName(fullname.getText());
        staff.setAddress(address.getText());
        staff.setPosition(position.getValue());
        staff.setPhoneNumber(phoneNumber.getText());
        staff.setUsername(username.getText());
        staff.setCccd(cccd.getText());
        if(!staffService.createStaff(staff)) {
            CustomMessageBox.boxError("Sometimes the staff service is not available");
            return;
        }
        CustomMessageBox.boxOk("Create staff successfully");
        reloadTableView();
    }

    public void cleanForm() {
        username.setText("");
        imageAvatar.setImage(null);
//        password.setText("");
        email.setText("");
        address.setText("");
        fullname.setText("");
        idStaff.setText("");
        phoneNumber.setText("");
        cccd.setText("");

    }

    @FXML
    public void btnAddImage(ActionEvent actionEvent) {
        // Tạo một đối tượng FileChooser
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Vui lòng chọn hình ảnh");

        // Chỉ cho phép chọn file hình ảnh
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Hình ảnh", "*.jpg", "*.jpeg", "*.png"));
        // Hiển thị hộp thoại chọn file
        File selectedFile = fileChooser.showOpenDialog(new Stage());
        // Kiểm tra xem người dùng đã chọn file chưa
        if (selectedFile != null) {
            // Lấy đường dẫn của file được chọn
            imageAvatar.setImage(new Image(String.valueOf(selectedFile)));
        }
    }

    @FXML
    public void handleBtnExportExcel(ActionEvent actionEvent) {
        staffService.exportExcel(staffList);
    }

    @FXML
    public void handleChangeSearchValue(KeyEvent actionEvent) {
        // Khởi tạo FilteredList và gán nó với danh sách positionList
        FilteredList<Staff> filteredList = new FilteredList<>(staffList, p -> true);

        // Gán FilteredList làm nguồn dữ liệu cho TableView
        tableListStaff.setItems(filteredList);
        filteredList.setPredicate(p -> {
            if(valueSearch.getText().isEmpty()) {
                return true;
            }
            // "ID","Username", "Name", "Address", "PhoneNumber", "CCCD");
            if(keySearch.getValue().equals("ID")) {
                return String.valueOf(p.getId()).toLowerCase().contains(valueSearch.getText());
            }
            if(keySearch.getValue().equals("Username")) {
                return p.getUsername().toLowerCase().contains(valueSearch.getText());
            }
            if(keySearch.getValue().equals("Address")) {
                return p.getAddress().toLowerCase().contains(valueSearch.getText());
            }
            if(keySearch.getValue().equals("PhoneNumber")) {
                return p.getPhoneNumber().toLowerCase().contains(valueSearch.getText());
            }
            if(keySearch.getValue().equals("Name")) {
                return p.getFullName().toLowerCase().contains(valueSearch.getText());
            }

            return p.getCccd().toLowerCase().contains(valueSearch.getText());
        });
        if(tableListStaff.getSelectionModel() != null) {
            tableListStaff.getSelectionModel().selectFirst();
        }
        updateStaffCurrentRowToForm();
    }


}
