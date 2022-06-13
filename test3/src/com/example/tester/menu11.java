package com.example.tester;

import backend.Restaurant;
import javafx.fxml.*;
import javafx.scene.*;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.stage.*;
public class menu11 {
	@FXML
    private Label menu1;
	@FXML
    private Label info;
	
	Restaurant[] allrt= Restaurant.Fromjson_detailtoArray();
	@FXML
	 public void sure() 
	 {
		
	 }
	@FXML
	 protected void back() throws Exception 
	 {
		Parent root=FXMLLoader.load(getClass().getResource("resselect.fxml"));
       Stage window=(Stage)menu1.getScene().getWindow();
       window.setScene(new Scene(root,750,500));
	 }
	
	 @FXML
	 public void initialize() 
	 {
		info.setText("測試");
		 String tmp="";
		int i=storage.i;
		for(int id=0;id<allrt[i].getMenu().size();id++)
		{
		   tmp+=allrt[i].getMenu().get(id).name;
		   tmp+=allrt[i].getMenu().get(id).price+"\n";
		}
		menu1.setText(tmp);
	 }	
}