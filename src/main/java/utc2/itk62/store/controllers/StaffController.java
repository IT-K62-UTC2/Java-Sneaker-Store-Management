package utc2.itk62.store.controllers;

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
import utc2.itk62.store.Validator.StaffValidator;
import utc2.itk62.store.models.Position;
import utc2.itk62.store.models.Staff;
import utc2.itk62.store.services.PositionServie;
import utc2.itk62.store.services.StaffService;
import utc2.itk62.store.util.CustomAlert;

import java.io.File;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.chrono.ChronoLocalDateTime;

public class StaffController {
    private static final StaffService staffService = new StaffService();
    private static final PositionServie positionService = new PositionServie();
    @FXML
    public TextField idStaff;
    @FXML
    public TextField  username;
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

        setupSearchByDate();

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
        if(idStaff.getText().equals("")) {
            return;
        }
        Staff currentSelectStaff = updateStaffCurrentRowToForm();
        if (!CustomAlert.showAlert(Alert.AlertType.CONFIRMATION, idStaff.getScene().getWindow(), "Delete staff", "Are you sure you want to delete this staff")) {
            return;
        }
        if (!staffService.deleteStaff(currentSelectStaff)) {
            CustomAlert.showAlert(Alert.AlertType.ERROR, idStaff.getScene().getWindow(), "Error!","Delete staff failed");
            return;
        }
        tableListStaff.getItems().remove(currentSelectStaff);
        reloadTableView();
    }

    @FXML
    public void handleUpdateStaff(ActionEvent actionEvent) {
        Staff currentSelectStaff = getStaffCurrentForm();
        if(!callValidateFormStaff()) {
            return;
        }
        if (!staffService.updateStaff(currentSelectStaff)) {
            CustomAlert.showAlert(Alert.AlertType.ERROR, idStaff.getScene().getWindow(), "Error!", "Update staff failed");
            return;
        }
        CustomAlert.showAlert(Alert.AlertType.INFORMATION, idStaff.getScene().getWindow(), "Success!", "Update staff successfully");
        reloadTableView();
    }

    private boolean callValidateFormStaff() {
        // Validate image
        if(!StaffValidator.validateImageAvatar(imageAvatar)) {
            CustomAlert.showAlert(Alert.AlertType.INFORMATION, idStaff.getScene().getWindow(), "Form Error!", "Please selected an image");
            btnAddImage.fire();
            username.requestFocus();
            return false;
        }

        // Validate user name
        if(!StaffValidator.validateUsername(username.getText())) {
            CustomAlert.showAlert(Alert.AlertType.ERROR, username.getScene().getWindow(), "Form Error!", "Invalid username");
            username.requestFocus();
            return false;
        }

        if(StaffValidator.isExistUsername(username.getText(), idStaff.getText().equals("") ? 0 : Integer.parseInt(idStaff.getText()))) {
            CustomAlert.showAlert(Alert.AlertType.ERROR, username.getScene().getWindow(), "Form Error!", "Username is exist");
            username.requestFocus();
            return false;
        }

        // Validate full name
        if(!StaffValidator.validateFullname(fullname.getText())) {
            CustomAlert.showAlert(Alert.AlertType.ERROR, fullname.getScene().getWindow(), "Form Error!", "Invalid fullname");
            fullname.requestFocus();
            return false;
        }

        // Validate address
        if(!StaffValidator.validateAddress(address.getText())) {
            CustomAlert.showAlert(Alert.AlertType.ERROR, address.getScene().getWindow(), "Form Error!", "Invalid address");
            address.requestFocus();
            return false;
        }

        // Validate phone number
        if (!StaffValidator.validatePhoneNumber(phoneNumber.getText())){
            CustomAlert.showAlert(Alert.AlertType.ERROR, phoneNumber.getScene().getWindow(), "Form Error!", "Invalid phone number");
            phoneNumber.requestFocus();
            return false;
        }
        if(StaffValidator.isExistPhoneNumber(phoneNumber.getText(),
                idStaff.getText().equals("") ? 0
                        : Integer.parseInt(idStaff.getText()))) {
            CustomAlert.showAlert(Alert.AlertType.ERROR, phoneNumber.getScene().getWindow(), "Form Error!", "Phone number is exist");
            phoneNumber.requestFocus();
            return false;
        }

        // Validate CCCD
        if(!StaffValidator.validateCCCD(cccd.getText())){
            CustomAlert.showAlert(Alert.AlertType.ERROR, cccd.getScene().getWindow(), "Form Error!", "Invalid CCCD");
            cccd.requestFocus();
            return false;
        }
        if(StaffValidator.isExistCCCD(cccd.getText(),
                idStaff.getText().equals("") ? 0
                        : Integer.parseInt(idStaff.getText()))) {
            CustomAlert.showAlert(Alert.AlertType.ERROR, cccd.getScene().getWindow(), "Form Error!", "CCCD is exist");
            cccd.requestFocus();
            return false;
        }

        // Validate email
        if(!StaffValidator.validateEmail(email.getText())){
            CustomAlert.showAlert(Alert.AlertType.ERROR, email.getScene().getWindow(), "Form Error!", "Invalid email");
            email.requestFocus();
            return false;
        }

        if(StaffValidator.isExistEmail(email.getText(),
                idStaff.getText().equals("") ? 0
                        : Integer.parseInt(idStaff.getText()))) {
            CustomAlert.showAlert(Alert.AlertType.ERROR, fullname.getScene().getWindow(), "Form Error!", "Email is exist");
            email.requestFocus();
            return false;
        }
        return true;
    }

    @FXML
    public void handleAddStaff(ActionEvent actionEvent) {
        tableListStaff.getSelectionModel().clearSelection();
        if(idStaff.getText() != null && idStaff.getText().length() > 0) {
            cleanForm();
        }
        
        if(!callValidateFormStaff()) {
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
            CustomAlert.showAlert(Alert.AlertType.ERROR, idStaff.getScene().getWindow(), "Error!", "Sometimes the staff service is not available");
            return;
        }
        CustomAlert.showAlert(Alert.AlertType.INFORMATION, idStaff.getScene().getWindow(), "Success", "Staff created successfully");
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
        File selectedFile = fileChooser.showOpenDialog(idStaff.getScene().getWindow());
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

    private void setupSearchByDate() {
        fromDate.setOnAction(actionEvent -> {
            FilteredList<Staff> filteredList = new FilteredList<>(staffList, p -> true);
            LocalDate beginSearch = fromDate.getValue();
            // Gán FilteredList làm nguồn dữ liệu cho TableView
            tableListStaff.setItems(filteredList);
            filteredList.setPredicate(p -> {
               // TODO: return true when filtering. return false when not filtering
                if(p.getCreatedAt().toLocalDateTime().isAfter(ChronoLocalDateTime.from(fromDate.getValue()))) {
                    return true;
                }
                return  false;
            });
            if(tableListStaff.getSelectionModel() != null) {
                tableListStaff.getSelectionModel().selectFirst();
            }
            updateStaffCurrentRowToForm();
        });
    }


}
