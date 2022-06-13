package com.example.tester;

import java.util.ArrayList;

import backend.Deliver;
import backend.Order;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class sender {
	@FXML
    private Label lb3;
	@FXML
    private Label lb2;
	@FXML
    private Label lb1;
	Deliver DeliverAccount;
	ArrayList<Order> WaitDLV;
	ArrayList<Order>Waitdone;
	@FXML
	protected void findorder() throws Exception 
	{	
		DeliverAccount = new Deliver("b09505030","b09505030","0988","b09505030@gmail.com","張");
		storage.DELIVER = DeliverAccount;
		storage.ALLORDER = Order.getDB("jdbc:sqlite:Orderdb.db");
		WaitDLV = DeliverAccount.DE_LookingForOrder(storage.ALLORDER);
		StringBuffer sb = new StringBuffer();
		for (Order O:WaitDLV) {
			sb.append(O.toString());
		};
		lb3.setText(sb.toString());
	}
	@FXML
	protected void getorder() throws Exception 
	{
		storage.ALLORDER = Order.getDB("jdbc:sqlite:Orderdb.db");
		if(WaitDLV.size()!=0)DeliverAccount.Accept(WaitDLV.get(0), storage.ALLORDER);
	}
	@FXML
	protected void finishorder() throws Exception 
	{	
		storage.ALLORDER = Order.getDB("jdbc:sqlite:Orderdb.db");
		Waitdone = DeliverAccount.DeliveringOrder(storage.ALLORDER);
		StringBuffer sb = new StringBuffer();
		for (Order O:Waitdone) {
			sb.append(O.toString());
		};
		lb2.setText(sb.toString());
	}
	@FXML
	protected void finish() throws Exception 
	{
		if(Waitdone.size()!=0)DeliverAccount.Done(Waitdone.get(0), storage.ALLORDER);
	}
	@FXML
	protected void hisorder() throws Exception 
	{
		storage.ALLORDER = Order.getDB("jdbc:sqlite:Orderdb.db");
		Waitdone = DeliverAccount.history(storage.ALLORDER);
		StringBuffer sb = new StringBuffer();
		for (Order O:Waitdone) {
			sb.append(O.toString());
		};
		lb1.setText(sb.toString());
	}
}