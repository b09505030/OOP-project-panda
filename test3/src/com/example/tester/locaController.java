package com.example.tester;

import backend.*;
import javafx.fxml.*;
import javafx.scene.*;
import javafx.scene.control.TextField;
import javafx.stage.*;
public class locaController {
	@FXML
    private TextField longitude;
	@FXML
    private TextField latitude;
	@FXML
    private TextField address;
	
	@FXML
	 public void initialize() 
	 {
		longitude.setText("121.545355");
		latitude.setText("25.016731");
		address.setText("106台北市大安區長興街31號");
	 }
	@FXML
	protected void setLoconClick() throws Exception {
		Parent root=FXMLLoader.load(getClass().getResource("resselect.fxml"));
        Stage window=(Stage)longitude.getScene().getWindow();
        window.setScene(new Scene(root,750,500));
		System.out.println("Long:"+longitude.getText());
		System.out.println("lati:"+latitude.getText());	
		System.out.println("addr:"+address.getText());	
		storage.DESTINATION=new Position(address.getText(),Double.parseDouble(latitude.getText()),Double.parseDouble(longitude.getText()));
	}
}
