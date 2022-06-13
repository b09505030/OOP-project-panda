package com.example.tester;
import java.util.ArrayList;

import backend.*;
import javafx.fxml.*;
import javafx.scene.*;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.stage.*;
public class resController {
	int i=0;
	@FXML
	private Label psel ;
	@FXML
	private Label due ;
	Restaurant[] allrt= Restaurant.Fromjson_detailtoArray();
	ArrayList<RestaurantAccount> RTList= RestaurantAccount.getDB("jdbc:sqlite:userdb.db");
			
	@FXML
	protected void selonClick1() throws Exception {
		storage.ALLRT_ACCOUNT = RTList;
		for(int i=0;i<allrt.length;i++) {
			allrt[i]= storage.ALLRT_ACCOUNT.get(i).getMyRestrant();
		}
		storage.i=0;
		Parent root=FXMLLoader.load(getClass().getResource("type5menue.fxml"));
        Stage window=(Stage)psel.getScene().getWindow();
        window.setScene(new Scene(root,750,500));
	}
	@FXML
	protected void selonClick2() throws Exception {
		storage.ALLRT_ACCOUNT = RTList;
		for(int i=0;i<allrt.length;i++) {
			allrt[i]= storage.ALLRT_ACCOUNT.get(i).getMyRestrant();
		}
		storage.i=1;
		Parent root=FXMLLoader.load(getClass().getResource("type15menue.fxml"));
        Stage window=(Stage)psel.getScene().getWindow();
        window.setScene(new Scene(root,750,500));
	}
	@FXML
	protected void selonClick3() throws Exception {
		storage.ALLRT_ACCOUNT = RTList;
		for(int i=0;i<allrt.length;i++) {
			allrt[i]= storage.ALLRT_ACCOUNT.get(i).getMyRestrant();
		}
		storage.i=2;
		Parent root=FXMLLoader.load(getClass().getResource("type7menue.fxml"));
        Stage window=(Stage)psel.getScene().getWindow();
        window.setScene(new Scene(root,750,500));
	}
	@FXML
	protected void selonClick4() throws Exception {
		storage.ALLRT_ACCOUNT = RTList;
		for(int i=0;i<allrt.length;i++) {
			allrt[i]= storage.ALLRT_ACCOUNT.get(i).getMyRestrant();
		}
		storage.i=3;
		Parent root=FXMLLoader.load(getClass().getResource("type4menue.fxml"));
        Stage window=(Stage)psel.getScene().getWindow();
        window.setScene(new Scene(root,750,500));
	}
	@FXML
	protected void selonClick5() throws Exception {
		storage.ALLRT_ACCOUNT = RTList;
		for(int i=0;i<allrt.length;i++) {
			allrt[i]= storage.ALLRT_ACCOUNT.get(i).getMyRestrant();
		}
		storage.i=4;
		Parent root=FXMLLoader.load(getClass().getResource("type4menue.fxml"));
        Stage window=(Stage)psel.getScene().getWindow();
        window.setScene(new Scene(root,750,500));
	}
	@FXML
	protected void selonClick6() throws Exception {
		storage.ALLRT_ACCOUNT = RTList;
		for(int i=0;i<allrt.length;i++) {
			allrt[i]= storage.ALLRT_ACCOUNT.get(i).getMyRestrant();
		}
		storage.i=5;
		Parent root=FXMLLoader.load(getClass().getResource("type5menue.fxml"));
        Stage window=(Stage)psel.getScene().getWindow();
        window.setScene(new Scene(root,750,500));
	}
	@FXML
	protected void selonClick7() throws Exception {
		storage.ALLRT_ACCOUNT = RTList;
		for(int i=0;i<allrt.length;i++) {
			allrt[i]= storage.ALLRT_ACCOUNT.get(i).getMyRestrant();
		}
		storage.i=6;
		Parent root=FXMLLoader.load(getClass().getResource("type9menue.fxml"));
        Stage window=(Stage)psel.getScene().getWindow();
        window.setScene(new Scene(root,750,500));
	}
	@FXML
	protected void selonClick8() throws Exception {
		storage.ALLRT_ACCOUNT = RTList;
		for(int i=0;i<allrt.length;i++) {
			allrt[i]= storage.ALLRT_ACCOUNT.get(i).getMyRestrant();
		}
		storage.i=7;
		Parent root=FXMLLoader.load(getClass().getResource("type5menue.fxml"));
        Stage window=(Stage)psel.getScene().getWindow();
        window.setScene(new Scene(root,750,500));
	}
	@FXML
	protected void selonClick9() throws Exception {
		storage.ALLRT_ACCOUNT = RTList;
		for(int i=0;i<allrt.length;i++) {
			allrt[i]= storage.ALLRT_ACCOUNT.get(i).getMyRestrant();
		}
		storage.i=8;
		Parent root=FXMLLoader.load(getClass().getResource("type11menue.fxml"));
        Stage window=(Stage)psel.getScene().getWindow();
        window.setScene(new Scene(root,750,500));
	}
	@FXML
	protected void selonClick10() throws Exception {
		storage.ALLRT_ACCOUNT = RTList;
		for(int i=0;i<allrt.length;i++) {
			allrt[i]= storage.ALLRT_ACCOUNT.get(i).getMyRestrant();
		}
		storage.i=9;
		Parent root=FXMLLoader.load(getClass().getResource("type8menue.fxml"));
        Stage window=(Stage)psel.getScene().getWindow();
        window.setScene(new Scene(root,750,500));
	}
	@FXML
	protected void selonClick11() throws Exception {
		storage.ALLRT_ACCOUNT = RTList;
		for(int i=0;i<allrt.length;i++) {
			allrt[i]= storage.ALLRT_ACCOUNT.get(i).getMyRestrant();
		}
		storage.i=10;
		Parent root=FXMLLoader.load(getClass().getResource("type12menue.fxml"));
        Stage window=(Stage)psel.getScene().getWindow();
        window.setScene(new Scene(root,750,500));
	}
	@FXML
	protected void selonClick12() throws Exception {
		storage.ALLRT_ACCOUNT = RTList;
		for(int i=0;i<allrt.length;i++) {
			allrt[i]= storage.ALLRT_ACCOUNT.get(i).getMyRestrant();
		}
		storage.i=11;
		Parent root=FXMLLoader.load(getClass().getResource("type5menue.fxml"));
        Stage window=(Stage)psel.getScene().getWindow();
        window.setScene(new Scene(root,750,500));
	}
	@FXML
	protected void selonClick13() throws Exception {
		storage.ALLRT_ACCOUNT = RTList;
		for(int i=0;i<allrt.length;i++) {
			allrt[i]= storage.ALLRT_ACCOUNT.get(i).getMyRestrant();
		}
		storage.i=12;
		Parent root=FXMLLoader.load(getClass().getResource("type7menue.fxml"));
        Stage window=(Stage)psel.getScene().getWindow();
        window.setScene(new Scene(root,750,500));
	}
	@FXML
	protected void selonClick14() throws Exception {
		storage.ALLRT_ACCOUNT = RTList;
		for(int i=0;i<allrt.length;i++) {
			allrt[i]= storage.ALLRT_ACCOUNT.get(i).getMyRestrant();
		}
		storage.i=13;
		Parent root=FXMLLoader.load(getClass().getResource("type10menue.fxml"));
        Stage window=(Stage)psel.getScene().getWindow();
        window.setScene(new Scene(root,750,500));
	}
	@FXML
	protected void selonClick15() throws Exception {
		storage.ALLRT_ACCOUNT = RTList;
		for(int i=0;i<allrt.length;i++) {
			allrt[i]= storage.ALLRT_ACCOUNT.get(i).getMyRestrant();
		}
		storage.i=14;
		Parent root=FXMLLoader.load(getClass().getResource("type11menue.fxml"));
        Stage window=(Stage)psel.getScene().getWindow();
        window.setScene(new Scene(root,750,500));
	}
	@FXML
	protected void selonClick16() throws Exception {
		storage.ALLRT_ACCOUNT = RTList;
		for(int i=0;i<allrt.length;i++) {
			allrt[i]= storage.ALLRT_ACCOUNT.get(i).getMyRestrant();
		}
		storage.i=15;
		Parent root=FXMLLoader.load(getClass().getResource("type4menue.fxml"));
        Stage window=(Stage)psel.getScene().getWindow();
        window.setScene(new Scene(root,750,500));
	}
	@FXML
	protected void selonClick17() throws Exception {
		storage.ALLRT_ACCOUNT = RTList;
		for(int i=0;i<allrt.length;i++) {
			allrt[i]= storage.ALLRT_ACCOUNT.get(i).getMyRestrant();
		}storage.i=16;
		Parent root=FXMLLoader.load(getClass().getResource("type3menue.fxml"));
        Stage window=(Stage)psel.getScene().getWindow();
        window.setScene(new Scene(root,750,500));
	}
	@FXML
	protected void selonClick18() throws Exception {
		storage.ALLRT_ACCOUNT = RTList;
		for(int i=0;i<allrt.length;i++) {
			allrt[i]= storage.ALLRT_ACCOUNT.get(i).getMyRestrant();
		}storage.i=17;
		Parent root=FXMLLoader.load(getClass().getResource("type8menue.fxml"));
        Stage window=(Stage)psel.getScene().getWindow();
        window.setScene(new Scene(root,750,500));
	}
	@FXML
	protected void selonClick19() throws Exception {
		storage.ALLRT_ACCOUNT = RTList;
		for(int i=0;i<allrt.length;i++) {
			allrt[i]= storage.ALLRT_ACCOUNT.get(i).getMyRestrant();
		}storage.i=18;
		Parent root=FXMLLoader.load(getClass().getResource("type6menue.fxml"));
        Stage window=(Stage)psel.getScene().getWindow();
        window.setScene(new Scene(root,750,500));
	}
	@FXML
	protected void selonClick20() throws Exception {
		storage.ALLRT_ACCOUNT = RTList;
		for(int i=0;i<allrt.length;i++) {
			allrt[i]= storage.ALLRT_ACCOUNT.get(i).getMyRestrant();
		}storage.i=19;
		Parent root=FXMLLoader.load(getClass().getResource("type6menue.fxml"));
        Stage window=(Stage)psel.getScene().getWindow();
        window.setScene(new Scene(root,750,500));
	}
	@FXML
	protected void selonClick21() throws Exception {
		storage.ALLRT_ACCOUNT = RTList;
		for(int i=0;i<allrt.length;i++) {
			allrt[i]= storage.ALLRT_ACCOUNT.get(i).getMyRestrant();
		}storage.i=20;
		Parent root=FXMLLoader.load(getClass().getResource("type5menue.fxml"));
        Stage window=(Stage)psel.getScene().getWindow();
        window.setScene(new Scene(root,750,500));
	}
	@FXML
	protected void selonClick22() throws Exception {
		storage.ALLRT_ACCOUNT = RTList;
		for(int i=0;i<allrt.length;i++) {
			allrt[i]= storage.ALLRT_ACCOUNT.get(i).getMyRestrant();
		}storage.i=21;
		Parent root=FXMLLoader.load(getClass().getResource("type6menue.fxml"));
        Stage window=(Stage)psel.getScene().getWindow();
        window.setScene(new Scene(root,750,500));
	}
	@FXML
	protected void selonClick23() throws Exception {
		storage.ALLRT_ACCOUNT = RTList;
		for(int i=0;i<allrt.length;i++) {
			allrt[i]= storage.ALLRT_ACCOUNT.get(i).getMyRestrant();
		}storage.i=22;
		Parent root=FXMLLoader.load(getClass().getResource("type7menue.fxml"));
        Stage window=(Stage)psel.getScene().getWindow();
        window.setScene(new Scene(root,750,500));
	}
	@FXML
	protected void selonClick24() throws Exception {
		storage.ALLRT_ACCOUNT = RTList;
		for(int i=0;i<allrt.length;i++) {
			allrt[i]= storage.ALLRT_ACCOUNT.get(i).getMyRestrant();
		}storage.i=23;
		Parent root=FXMLLoader.load(getClass().getResource("type8menue.fxml"));
        Stage window=(Stage)psel.getScene().getWindow();
        window.setScene(new Scene(root,750,500));
	}
	@FXML
	protected void selonClick25() throws Exception {
		storage.ALLRT_ACCOUNT = RTList;
		for(int i=0;i<allrt.length;i++) {
			allrt[i]= storage.ALLRT_ACCOUNT.get(i).getMyRestrant();
		}storage.i=24;
		Parent root=FXMLLoader.load(getClass().getResource("type3menue.fxml"));
        Stage window=(Stage)psel.getScene().getWindow();
        window.setScene(new Scene(root,750,500));
	}
	@FXML
	protected void selonClick26() throws Exception {
		storage.ALLRT_ACCOUNT = RTList;
		for(int i=0;i<allrt.length;i++) {
			allrt[i]= storage.ALLRT_ACCOUNT.get(i).getMyRestrant();
		}storage.i=25;
		Parent root=FXMLLoader.load(getClass().getResource("type8menue.fxml"));
        Stage window=(Stage)psel.getScene().getWindow();
        window.setScene(new Scene(root,750,500));
	}
	@FXML
	protected void selonClick27() throws Exception {
		storage.ALLRT_ACCOUNT = RTList;
		for(int i=0;i<allrt.length;i++) {
			allrt[i]= storage.ALLRT_ACCOUNT.get(i).getMyRestrant();
		}storage.i=26;
		Parent root=FXMLLoader.load(getClass().getResource("type6menue.fxml"));
        Stage window=(Stage)psel.getScene().getWindow();
        window.setScene(new Scene(root,750,500));
	}
	@FXML
	protected void selonClick28() throws Exception {
		storage.ALLRT_ACCOUNT = RTList;
		for(int i=0;i<allrt.length;i++) {
			allrt[i]= storage.ALLRT_ACCOUNT.get(i).getMyRestrant();
		}storage.i=27;
		Parent root=FXMLLoader.load(getClass().getResource("type9menue.fxml"));
        Stage window=(Stage)psel.getScene().getWindow();
        window.setScene(new Scene(root,750,500));
	}
	@FXML
	protected void selonClick29() throws Exception {
		storage.ALLRT_ACCOUNT = RTList;
		for(int i=0;i<allrt.length;i++) {
			allrt[i]= storage.ALLRT_ACCOUNT.get(i).getMyRestrant();
		}storage.i=28;
		Parent root=FXMLLoader.load(getClass().getResource("type8menue.fxml"));
        Stage window=(Stage)psel.getScene().getWindow();
        window.setScene(new Scene(root,750,500));
	}
	@FXML
	protected void search() throws Exception {
		Parent root=FXMLLoader.load(getClass().getResource("check.fxml"));
        Stage window=(Stage)psel.getScene().getWindow();
        window.setScene(new Scene(root,750,500));
	}
	@FXML
	protected void VIP() throws Exception {
		storage.CLIENT.MonthVIP();
//		System.out.println(storage.CLIENT);
		due.setText("VIP到期日:"+new java.sql.Date(storage.CLIENT.getDue()));
	}
	
}
