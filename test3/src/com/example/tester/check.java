package com.example.tester;

import backend.Order;
import backend.Restaurant;
import javafx.fxml.*;
import javafx.scene.*;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.stage.*;
public class check {
	@FXML
    private Label label1;
	@FXML
    private Label label2;
	@FXML
	 public void back() throws Exception
	 {
		
		Parent root=FXMLLoader.load(getClass().getResource("resselect.fxml"));
	    Stage window=(Stage)label1.getScene().getWindow();
	    window.setScene(new Scene(root,750,500));
		
	 }
	 @FXML
	 public void initialize() 
	 {	
		storage.ALLORDER = Order.getDB("jdbc:sqlite:Orderdb.db");
		StringBuffer sb = new StringBuffer();
		for (Order O:storage.ALLORDER) {
			if(O.buyername.equals(storage.CLIENT.getName())&&O.state.equals("餐點抵達")) {
				sb.append(O.toString());
			}
		}
		StringBuffer sb2 = new StringBuffer();
		for (Order O:storage.ALLORDER) {
			if(O.buyername.equals(storage.CLIENT.getName())&&(O.state.equals("餐點運送中")|O.state.equals("等待商家確認")|O.state.equals("餐點製作中"))) {
				sb2.append(O.toString());
			}
		}
		label1.setText(""+sb.toString());
		label2.setText(""+sb2.toString());
	 }	
}