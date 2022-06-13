package com.example.tester;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.ArrayList;

import com.google.gson.Gson;

import backend.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.Parent;

public class HelloController {

    // Strings which hold css elements to easily re-use in the application
    protected
    String successMessage = String.format("-fx-text-fill: GREEN;");
    String errorMessage = String.format("-fx-text-fill: RED;");
    String errorStyle = String.format("-fx-border-color: RED; -fx-border-width: 2; -fx-border-radius: 5;");
    String successStyle = String.format("-fx-border-color: #A9A9A9; -fx-border-width: 2; -fx-border-radius: 5;");

    // Import the application's controls
    @FXML
    private Label invalidLoginCredentials;
    @FXML
    private Label invalidSignupCredentials;
    @FXML
    private Button cancelButton;
    @FXML
    private TextField loginUsernameTextField;
    @FXML
    private TextField loginPasswordPasswordField;
    @FXML
    private TextField signUpUsernameTextField;
    @FXML
    private TextField signUpEmailTextField;
    @FXML
    private TextField signUpPasswordPasswordField;
    @FXML
    private TextField signUpRepeatPasswordPasswordField;

    // Creation of methods which are activated on events in the forms
    @FXML
    protected void onCancelButtonClick() {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    protected void onLoginButtonClick() throws Exception{
        if (loginUsernameTextField.getText().isBlank() || loginPasswordPasswordField.getText().isBlank()) {
            invalidLoginCredentials.setText("The Login fields are required!");
            invalidLoginCredentials.setStyle(errorMessage);
            invalidSignupCredentials.setText("");

            if (loginUsernameTextField.getText().isBlank()) {
                loginUsernameTextField.setStyle(errorStyle);
            } else if (loginPasswordPasswordField.getText().isBlank()) {
                loginPasswordPasswordField.setStyle(errorStyle);
            }
        } else {
            String Id = loginUsernameTextField.getText();
            String pass =loginPasswordPasswordField.getText();
            String  jdbcUrl = "jdbc:sqlite:userdb.db";
    		ArrayList<Client> Cent = Client.getDB(jdbcUrl);
    		for(Client c:Cent) {
    			if((Id.equals(c.getEmail())||Id.equals(c.getName()))&&pass.equals(c.getPassword())) {
    				storage.CLIENT=c;
    				invalidLoginCredentials.setText("Login Successful!");
		            invalidLoginCredentials.setStyle(successMessage);
		            loginUsernameTextField.setStyle(successStyle);
		            loginPasswordPasswordField.setStyle(successStyle);
		            invalidSignupCredentials.setText("");
		            //WRITE TO A FILE
		            try {
    	    			BufferedWriter bw = new BufferedWriter(new FileWriter("currentClient.json"));
    	    			bw.write(new Gson().toJson(c, Client.class));
    	    			bw.close();
    	    		} catch (Exception ex) {
    	    			System.out.println("Error occered at Write to currentclient");
    	    		}
		            Parent root=FXMLLoader.load(getClass().getResource("2.fxml"));
    	            Stage window=(Stage)cancelButton.getScene().getWindow();
    	            window.setScene(new Scene(root,750,500));
    	            break;
    			}
    		}
            invalidLoginCredentials.setText("unexist account!");
            }
    }

    @FXML
    protected void onSignUpButtonClick() {
    	
        if (signUpUsernameTextField.getText().isBlank() || signUpEmailTextField.getText().isBlank() || signUpPasswordPasswordField.getText().isBlank() || signUpRepeatPasswordPasswordField.getText().isBlank()) {
            invalidSignupCredentials.setText("Please fill in all fields!");
            invalidSignupCredentials.setStyle(errorMessage);
            invalidLoginCredentials.setText("");

            if (signUpUsernameTextField.getText().isBlank()) {
                signUpUsernameTextField.setStyle(errorStyle);
            } else if (signUpEmailTextField.getText().isBlank()) {
                signUpEmailTextField.setStyle(errorStyle);
            } else if (signUpPasswordPasswordField.getText().isBlank()) {
                signUpPasswordPasswordField.setStyle(errorStyle);
            } else if (signUpRepeatPasswordPasswordField.getText().isBlank()) {
                signUpRepeatPasswordPasswordField.setStyle(errorStyle);
            }
        } else if (signUpRepeatPasswordPasswordField.getText().equals(signUpPasswordPasswordField.getText())) {
            String  jdbcUrl = "jdbc:sqlite:userdb.db";
            Client MyAccount = new Client(signUpUsernameTextField.getText(),signUpEmailTextField.getText(),signUpPasswordPasswordField.getText(),"0988233936",signUpEmailTextField.getText());
    		System.out.println(MyAccount);
    		MyAccount.storeToDB(jdbcUrl);
    		try {
//    			WRITE TO A FILE
    			BufferedWriter bw = new BufferedWriter(new FileWriter("currentClient.json"));
    			bw.write(new Gson().toJson(MyAccount, Client.class));
    			bw.close();
//    			BufferedReader br = new BufferedReader(new FileReader("currentClient.json"));
//    			Position destination = new Gson().fromJson(br.readLine(),Client.class);
//    			br.close();
    			

    		} catch (Exception ex) {
    			System.out.println("Error occered at Fromgson_detailtoList()");
    		}
    		invalidSignupCredentials.setText("You are set!");
            invalidSignupCredentials.setStyle(successMessage);
            signUpUsernameTextField.setStyle(successStyle);
            signUpEmailTextField.setStyle(successStyle);
            signUpPasswordPasswordField.setStyle(successStyle);
            signUpRepeatPasswordPasswordField.setStyle(successStyle);
            invalidLoginCredentials.setText("");
        } else {
            invalidSignupCredentials.setText("The Passwords don't match!");
            invalidSignupCredentials.setStyle(errorMessage);
            signUpPasswordPasswordField.setStyle(errorStyle);
            signUpRepeatPasswordPasswordField.setStyle(errorStyle);
            invalidLoginCredentials.setText("");
            
            signUpUsernameTextField.getText();
            signUpEmailTextField.getText();
            signUpPasswordPasswordField.getText();
            String  jdbcUrl = "jdbc:sqlite:userdb.db";
            Client MyAccount = new Client(signUpUsernameTextField.getText(),signUpEmailTextField.getText(),signUpPasswordPasswordField.getText(),"0988233936",signUpEmailTextField.getText());
    		System.out.println(MyAccount);
    		MyAccount.MonthVIP();
    		System.out.println(MyAccount);
    		MyAccount.storeToDB(jdbcUrl);
            
        }
    }
}