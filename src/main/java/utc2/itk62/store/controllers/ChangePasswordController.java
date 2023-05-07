package utc2.itk62.store.controllers;

import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import utc2.itk62.store.Validator.StaffValidator;
import utc2.itk62.store.common.User;
import utc2.itk62.store.models.Staff;
import utc2.itk62.store.services.StaffService;
import utc2.itk62.store.util.CustomAlert;
import utc2.itk62.store.util.HashedPassword;

public class ChangePasswordController {
    private static final StaffService  staffService = new StaffService();
    public TextField idStaff;
    public TextField username;
    public TextField phoneNumber;
    public TextField cccd;
    public TextField email;
    public RadioButton maleBtn;
    public RadioButton femaleBtn;
    public PasswordField oldPassword;
    public PasswordField newPassword;
    public ImageView imageAvatar;
    public Button updatePassword;
    public PasswordField confirmPassword;
    public TextField fullname;
    public TextField address;
    private ToggleGroup toggleGenderGroup = new ToggleGroup();

    public void initialize() {
        // Gender
        maleBtn.setToggleGroup(toggleGenderGroup);
        femaleBtn.setToggleGroup(toggleGenderGroup);
        updateStaffToForm();
        setupUpdatePassword();
    }

    private void updateStaffToForm() {
        Staff staff = User.staff;
        idStaff.setText(String.valueOf(staff.getId()));
        username.setText(staff.getUsername());
        email.setText(staff.getEmail());
        fullname.setText(staff.getFullName());
        address.setText(staff.getAddress());
        phoneNumber.setText(staff.getPhoneNumber());
        cccd.setText(staff.getCccd());
        try {
            Image image = new Image(staff.getPathAvatar());
            imageAvatar.setImage(image);
        } catch (Exception e) {
            e.printStackTrace();
        }

        toggleGenderGroup.selectToggle(staff.getGender().equals(maleBtn.getText()) ? maleBtn : femaleBtn);
    }

    private void setupUpdatePassword() {
        updatePassword.setOnAction(actionEvent -> {
            if(!HashedPassword.checkPassword(oldPassword.getText(), User.staff.getPassword())) {
                CustomAlert.showAlert(Alert.AlertType.ERROR, oldPassword.getScene().getWindow(), "Error!", "Old Password is incorrect");
                oldPassword.requestFocus();
                return;
            }

            if(newPassword.getText().equals("")) {
                CustomAlert.showAlert(Alert.AlertType.ERROR, oldPassword.getScene().getWindow(), "Error!", "New Password not null");
                newPassword.requestFocus();
                return;
            }

            if(!newPassword.getText().equals(confirmPassword.getText())) {
                CustomAlert.showAlert(Alert.AlertType.ERROR, oldPassword.getScene().getWindow(), "Error!", "Confirmation password is not equal to new password");
                confirmPassword.requestFocus();
                return;
            }

            if(!staffService.updatePasswordStaff(User.staff, newPassword.getText())){
                CustomAlert.showAlert(Alert.AlertType.ERROR, oldPassword.getScene().getWindow(), "Error!", "Sometimes the staff service is not available");
                return;
            }

            oldPassword.setText("");
            newPassword.setText("");
            confirmPassword.setText("");
            CustomAlert.showAlert(Alert.AlertType.INFORMATION, oldPassword.getScene().getWindow(), "Success!", "Update password successfully");

        });
    }
}
