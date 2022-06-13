package com.example.tester;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class accountant {
	@FXML
    private Label money1;
	@FXML
    private Label money2;
	@FXML
    private Label money3;
   
	@FXML
	protected void ensure() throws Exception 
	{
		Parent root=FXMLLoader.load(getClass().getResource("resselect.fxml"));
        Stage window=(Stage)money1.getScene().getWindow();
        window.setScene(new Scene(root,750,500));
	}
	
	@FXML
	 public void initialize() 
	 {
		money1.setText(""+storage.CURRENTCART.getGasfee());
		money2.setText(""+storage.CURRENTCART.getFoodtotal());
		money3.setText(""+storage.CURRENTCART.getTotalprice());
	 }
}