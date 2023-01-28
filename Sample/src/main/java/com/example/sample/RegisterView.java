package com.example.sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class RegisterView {

    @FXML
    private TextField firstName;
    @FXML
    private TextField lastName;
    @FXML
    private TextField username;
    @FXML
    private PasswordField password;
    @FXML
    private PasswordField repassword;

    public void register(ActionEvent event){
        DatabaseConnection databaseConnection = new DatabaseConnection();
        Connection connection = databaseConnection.getConnection();

        String registerString = "INSERT INTO users(firstname,lastname,username,password) VALUES('"+firstName.getText()+"','"+lastName.getText()+"','"+username.getText()+"','"+password.getText()+"')";

        try{
            Statement statement = connection.createStatement();
            statement.executeUpdate(registerString);

        }catch(Exception e){
            e.printStackTrace();
            e.getCause();
        }
    }
}
