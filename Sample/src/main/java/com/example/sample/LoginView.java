package com.example.sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class LoginView {

    @FXML
    private Button btnCancel;
    @FXML
    private Label invalidLoginErr;
    @FXML
    private TextField username;
    @FXML
    private PasswordField password;


    public void cancelButtonAction(ActionEvent event){
        Stage stage = (Stage) btnCancel.getScene().getWindow();
        stage.close();
    }

    public void loginButtonAction(ActionEvent event){
        if (username.getText().isBlank() == false && password.getText().isBlank() == false){
            validateLogin();
        }else {
            invalidLoginErr.setText("Please enter username and password");
        }
    }

    private void validateLogin() {
        DatabaseConnection connection = new DatabaseConnection();
        Connection connectdb = connection.getConnection();

        String verifyLogin = "SELECT COUNT(1) FROM users WHERE username = '"+username.getText()+"' AND password = '"+password.getText()+"'";

        try{
            Statement statement = connectdb.createStatement();
            ResultSet resultSet = statement.executeQuery(verifyLogin);

            while (resultSet.next()){
                if(resultSet.getInt(1) == 1){
                    invalidLoginErr.setText("Congratulations...");
                }else{
                    invalidLoginErr.setText("Invalid Login, Plase try again..");
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            e.getCause();
        }


    }
}
