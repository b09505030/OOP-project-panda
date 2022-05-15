import java.awt.*;
import java.awt.event.*;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;

import javax.swing.*;
import javax.swing.event.*;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

public class GUI {
	private JFrame frame;
	private String[] Stations = { "南港", "台北", "板橋", "桃園", "新竹", "苗栗", "台中", "彰化", "雲林", "嘉義", "台南", "左營" };
	private String[] TripType = { "單程", "去回程" };
	private String[] TicketType = { "無", "早鳥", "大學生" };
	private String[] SeatType = {"標準車廂", "商務車廂"};
	private String[] TicketCountChoice= {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};
	private int[][] carQueryResult = //車次, 開始時, 開始分, 結束時, 結束分, 行車時, 行車分, 折數
		{
				{838,17,32,17,39,0,7,100},
				{242,17,47,17,54,0,7,80},
				{660,17,51,17,59,0,8,65}
		};
	private int checked = 0; 
	private int mode = 1;
	private int ticketPrice = 1350;
	private int year,month,date;
	private Query query = new Query();
	private Order order = new Order();

	public GUI() {
		frame = new JFrame();
	}
	public void setQueries(int[][] result) {
		this.carQueryResult = result;
	}
	
	public GridBagConstraints getConstraints(int gridx, int gridy, int gridwidth, int gridheight, int weightx,
			int weighty, int fill) {
		GridBagConstraints ret = new GridBagConstraints();
		ret.gridx = gridx;
		ret.gridy = gridy;
		ret.gridwidth = gridwidth;
		ret.gridheight = gridheight;
		ret.weightx = weightx;
		ret.weighty = weighty;
		ret.fill = fill;
		return ret;
	}

	public JPanel getTicket() {
		JPanel ret = new JPanel();
		
		return ret;
	}
	
	public void run() {
		Calendar nowDate;
		nowDate = Calendar.getInstance();
		year = nowDate.get(Calendar.YEAR);
		month = nowDate.get(Calendar.MONTH);
		date = nowDate.get(Calendar.DATE);

		frame.setSize(960, 540);
		frame.setLayout(new GridBagLayout());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("高鐵訂票系統");

		/*
		 * JLabel label = new JLabel("" + mode); frame.add(label, getConstraints(1, 3,
		 * 1, 1, 0, 0, GridBagConstraints.CENTER));
		 */
		JButton TimePriceButton = new JButton("時刻表與票價");
		TimePriceButton.setBackground(new Color(153, 153, 153));
		frame.add(TimePriceButton, getConstraints(0, 0, 2, 1, 0, 0, GridBagConstraints.BOTH));
		
		JButton OnlineOrderButton = new JButton("網路訂票");
		OnlineOrderButton.setBackground(new Color(153, 153, 153));
		frame.add(OnlineOrderButton, getConstraints(2, 0, 2, 1, 0, 0, GridBagConstraints.BOTH));

		JButton TicketInfoButton = new JButton("我的票卷");
		TicketInfoButton.setBackground(new Color(153, 153, 153));
		frame.add(TicketInfoButton, getConstraints(4, 0, 2, 1, 0, 0, GridBagConstraints.BOTH));

//==================panel 1================================
		JPanel panel1 = new JPanel(new GridBagLayout());
		JLabel startStationLabel = new JLabel("起程站");
		JComboBox startStationComboBox = new JComboBox(Stations);
		JLabel endStationLabel = new JLabel("終點站");
		JComboBox endStationComboBox = new JComboBox(Stations);
		JComboBox tripTypeComboBox = new JComboBox(TripType);
		JLabel goDateLabel = new JLabel("去程日期");
		JLabel goTimeLabel = new JLabel("去程時間");
		UtilDateModel goModel = new UtilDateModel();
		goModel.setDate(year, month, date);
		query.StartDate = String.format("%04d",year) + "-" + String.format("%02d",month+1) + "-" + String.format("%02d",date);
		goModel.setSelected(true);
		Properties goP = new Properties();
		goP.put("text.today", "Today");
		goP.put("text.month", "Month");
		goP.put("text.year", "Year");
		JDatePanelImpl goDatePanel = new JDatePanelImpl(goModel, goP);
		JDatePickerImpl goDatePicker = new JDatePickerImpl(goDatePanel, new DateLabelFormatter());
		JSpinner goTimeSpinner = new JSpinner(new SpinnerDateModel());
		JSpinner.DateEditor goTimeEditor = new JSpinner.DateEditor(goTimeSpinner, "HH:mm");
		goTimeSpinner.setEditor(goTimeEditor);
		goTimeSpinner.setValue(new Date());
		query.StartTime = String.valueOf(goTimeSpinner.getValue());
		JLabel backDateLabel = new JLabel("回程日期");
		JLabel backTimeLabel = new JLabel("回程時間");
		UtilDateModel backModel = new UtilDateModel();
		backModel.setDate(year, month, date);
		query.EndDate = String.format("%04d",year) + "-" + String.format("%02d",month+1) + "-" + String.format("%02d",date);
		backModel.setSelected(true);
		Properties backP = new Properties();
		backP.put("text.today", "Today");
		backP.put("text.month", "Month");
		backP.put("text.year", "Year");
		JDatePanelImpl backDatePanel = new JDatePanelImpl(backModel, backP);
		JDatePickerImpl backDatePicker = new JDatePickerImpl(backDatePanel, new DateLabelFormatter());
		JSpinner backTimeSpinner = new JSpinner(new SpinnerDateModel());
		JSpinner.DateEditor backTimeEditor = new JSpinner.DateEditor(backTimeSpinner, "HH:mm");
		backTimeSpinner.setEditor(backTimeEditor);
		backTimeSpinner.setValue(new Date());
		query.EndTime = String.valueOf(backTimeSpinner.getValue());
		JLabel ticketTypeLabel = new JLabel("適用優惠");
		JComboBox ticketTypeComboBox = new JComboBox(TicketType);
		JButton queryButton = new JButton("查詢");
		queryButton.setBackground(Color.ORANGE);

		panel1.add(startStationLabel, getConstraints(0, 0, 1, 1, 0, 0, GridBagConstraints.BOTH));
		panel1.add(startStationComboBox, getConstraints(0, 1, 1, 1, 0, 0, GridBagConstraints.BOTH));
		panel1.add(endStationLabel, getConstraints(5, 0, 1, 1, 0, 0, GridBagConstraints.BOTH));
		panel1.add(endStationComboBox, getConstraints(5, 1, 1, 1, 0, 0, GridBagConstraints.BOTH));
		panel1.add(goDatePicker, getConstraints(0, 4, 4, 1, 0, 0, GridBagConstraints.BOTH));
		panel1.add(goTimeSpinner, getConstraints(4, 4, 2, 1, 0, 0, GridBagConstraints.BOTH));
		panel1.add(backDateLabel, getConstraints(0, 5, 4, 1, 0, 0, GridBagConstraints.BOTH));
		panel1.add(tripTypeComboBox, getConstraints(0, 2, 6, 1, 0, 0, GridBagConstraints.BOTH));
		panel1.add(goDateLabel, getConstraints(0, 3, 4, 1, 0, 0, GridBagConstraints.BOTH));
		panel1.add(goTimeLabel, getConstraints(4, 3, 2, 1, 0, 0, GridBagConstraints.BOTH));
		panel1.add(backTimeLabel, getConstraints(4, 5, 2, 1, 0, 0, GridBagConstraints.BOTH));
		panel1.add(backDatePicker, getConstraints(0, 6, 4, 1, 0, 0, GridBagConstraints.BOTH));
		panel1.add(backTimeSpinner, getConstraints(4, 6, 2, 1, 0, 0, GridBagConstraints.BOTH));
		panel1.add(ticketTypeLabel, getConstraints(0, 7, 6, 1, 0, 0, GridBagConstraints.BOTH));
		panel1.add(ticketTypeComboBox, getConstraints(0, 8, 6, 1, 0, 0, GridBagConstraints.BOTH));
		panel1.add(queryButton, getConstraints(0, 9, 6, 1, 0, 0, GridBagConstraints.BOTH));
//=========================================================

//==================panel 2================================
		JPanel panel2 = new JPanel(new GridBagLayout());
		
		JLabel Label2_1 = new JLabel("起程站");
		JLabel Label2_2 = new JLabel("到達站");
		JLabel Label2_3 = new JLabel("行程類型");
		JLabel Label2_4 = new JLabel("去程日期");
		JLabel Label2_5 = new JLabel("去程時間");

		JComboBox startStationComboBox2 = new JComboBox(Stations);
		JComboBox endStationComboBox2 = new JComboBox(Stations);
		JComboBox tripTypeComboBox2 = new JComboBox(TripType);
		UtilDateModel goModel2 = new UtilDateModel();
		goModel2.setDate(year, month, date);
		order.StartDate = String.format("%04d",year) + "-" + String.format("%02d",month+1) + "-" + String.format("%02d",date);
		goModel2.setSelected(true);
		Properties goP2 = new Properties();
		goP.put("text.today", "Today");
		goP.put("text.month", "Month");
		goP.put("text.year", "Year");
		JDatePanelImpl goDatePanel2 = new JDatePanelImpl(goModel2, goP2);
		JDatePickerImpl goDatePicker2 = new JDatePickerImpl(goDatePanel2, new DateLabelFormatter());
		JSpinner goTimeSpinner2 = new JSpinner(new SpinnerDateModel());
		JSpinner.DateEditor goTimeEditor2 = new JSpinner.DateEditor(goTimeSpinner2, "HH:mm");
		goTimeSpinner2.setEditor(goTimeEditor2);
		goTimeSpinner2.setValue(new Date());
		order.StartTime = String.valueOf(goTimeSpinner2.getValue());	
		JLabel backDateLabel2 = new JLabel("回程日期");
		JLabel backTimeLabel2 = new JLabel("回程時間");
		UtilDateModel backModel2 = new UtilDateModel();
		backModel2.setDate(year, month, date);
		order.EndDate = String.format("%04d",year) + "-" + String.format("%02d",month+1) + "-" + String.format("%02d",date);
		backModel2.setSelected(true);
		Properties backP2 = new Properties();
		backP2.put("text.today", "Today");
		backP2.put("text.month", "Month");
		backP2.put("text.year", "Year");
		JDatePanelImpl backDatePanel2 = new JDatePanelImpl(backModel2, backP2);
		JDatePickerImpl backDatePicker2 = new JDatePickerImpl(backDatePanel2, new DateLabelFormatter());
		JSpinner backTimeSpinner2 = new JSpinner(new SpinnerDateModel());
		JSpinner.DateEditor backTimeEditor2 = new JSpinner.DateEditor(backTimeSpinner2, "HH:mm");
		backTimeSpinner2.setEditor(backTimeEditor2);
		backTimeSpinner2.setValue(new Date());
		order.EndTime = String.valueOf(backTimeSpinner2.getValue());
		
		JLabel Label2_6 = new JLabel("車廂種類");
		JLabel Label2_7 = new JLabel("全票");
		JLabel Label2_8 = new JLabel("早鳥票");
		JLabel Label2_11 = new JLabel("大學生優惠票");
		
		JComboBox seatTypeComboBox = new JComboBox(SeatType);
		JComboBox adultCountComboBox = new JComboBox(TicketCountChoice);
		JComboBox earlybirdCountComboBox = new JComboBox(TicketCountChoice);
		JComboBox collegeCountComboBox = new JComboBox(TicketCountChoice);

		JButton queryButton2 = new JButton("查詢");
		queryButton2.setBackground(Color.ORANGE);
		
		panel2.add(Label2_1, getConstraints(0, 0, 1, 1, 0, 0, GridBagConstraints.BOTH));
		panel2.add(Label2_2, getConstraints(1, 0, 1, 1, 0, 0, GridBagConstraints.BOTH));
		panel2.add(Label2_3, getConstraints(2, 0, 1, 1, 0, 0, GridBagConstraints.BOTH));
		panel2.add(Label2_4, getConstraints(3, 0, 1, 1, 0, 0, GridBagConstraints.BOTH));
		panel2.add(Label2_5, getConstraints(4, 0, 1, 1, 0, 0, GridBagConstraints.BOTH));
		panel2.add(startStationComboBox2, getConstraints(0, 1, 1, 1, 0, 0, GridBagConstraints.BOTH));
		panel2.add(endStationComboBox2, getConstraints(1, 1, 1, 1, 0, 0, GridBagConstraints.BOTH));
		panel2.add(tripTypeComboBox2, getConstraints(2, 1, 1, 1, 0, 0, GridBagConstraints.BOTH));
		panel2.add(goDatePicker2, getConstraints(3, 1, 1, 1, 0, 0, GridBagConstraints.BOTH));
		panel2.add(goTimeSpinner2, getConstraints(4, 1, 2, 1, 0, 0, GridBagConstraints.BOTH));	
		panel2.add(backDateLabel2, getConstraints(3, 2, 1, 1, 0, 0, GridBagConstraints.BOTH));		
		panel2.add(backTimeLabel2, getConstraints(4, 2, 1, 1, 0, 0, GridBagConstraints.BOTH));
		panel2.add(backDatePicker2, getConstraints(3, 3, 1, 1, 0, 0, GridBagConstraints.BOTH));
		panel2.add(backTimeSpinner2, getConstraints(4, 3, 2, 1, 0, 0, GridBagConstraints.BOTH));
		panel2.add(Label2_6, getConstraints(0, 4, 1, 1, 0, 0, GridBagConstraints.BOTH));
		panel2.add(Label2_7, getConstraints(1, 4, 2, 1, 0, 0, GridBagConstraints.BOTH));
		panel2.add(Label2_8, getConstraints(3, 4, 1, 1, 0, 0, GridBagConstraints.BOTH));
		panel2.add(Label2_11, getConstraints(4, 4, 2, 1, 0, 0, GridBagConstraints.BOTH));
		panel2.add(seatTypeComboBox, getConstraints(0, 5, 1, 1, 0, 0, GridBagConstraints.BOTH));
		panel2.add(adultCountComboBox, getConstraints(1, 5, 2, 1, 0, 0, GridBagConstraints.BOTH));
		panel2.add(earlybirdCountComboBox, getConstraints(3, 5, 1, 1, 0, 0, GridBagConstraints.BOTH));
		panel2.add(collegeCountComboBox, getConstraints(4, 5, 2, 1, 0, 0, GridBagConstraints.BOTH));
		panel2.add(queryButton2, getConstraints(0, 6, 6, 1, 0, 0, GridBagConstraints.BOTH));
//=========================================================

//==================panel 3================================
		
		JPanel panel3 = new JPanel(new GridBagLayout());
		JLabel label3_0 = new JLabel("去程："+order.From+" - "+ order.To + "  "+ order.StartDate);
		JLabel label3_1 = new JLabel("選擇");
		JLabel label3_2 = new JLabel("車次");
		JLabel label3_3 = new JLabel("全票優惠*");
		JLabel label3_4 = new JLabel("出發時間");
		JLabel label3_5 = new JLabel("到達時間");
		JLabel label3_6 = new JLabel("行車時間");
		JLabel label3_7 = new JLabel("訂位明細");
		label3_0.setForeground(Color.ORANGE);
		label3_7.setForeground(Color.ORANGE);
		
		panel3.add(label3_0, getConstraints(0, 0, 6, 2, 0, 0, GridBagConstraints.NORTHWEST));
		panel3.add(label3_1, getConstraints(0, 2, 1, 1, 0, 0, GridBagConstraints.BOTH));
		panel3.add(label3_2, getConstraints(1, 2, 1, 1, 0, 0, GridBagConstraints.EAST));
		panel3.add(label3_3, getConstraints(2, 2, 1, 1, 0, 0, GridBagConstraints.BOTH));
		panel3.add(label3_4, getConstraints(3, 2, 1, 1, 0, 0, GridBagConstraints.BOTH));
		panel3.add(label3_5, getConstraints(4, 2, 1, 1, 0, 0, GridBagConstraints.BOTH));
		panel3.add(label3_6, getConstraints(5, 2, 1, 1, 0, 0, GridBagConstraints.BOTH));
		
		JCheckBox[] checkboxList = new JCheckBox[10];
		JLabel[] carNumberList = new JLabel[10];
		JLabel[] discountList = new JLabel[10];
		JLabel[] goTimeList = new JLabel[10];
		JLabel[] arriveTimeList = new JLabel[10];
		JLabel[] totalTimeList = new JLabel[10];
		
		/*
		 * 車次	全票優惠*	出發時間	到達時間	行車時間
		 */
		for(int i=0;i<carQueryResult.length;i++) {
			checkboxList[i] = new JCheckBox();
			carNumberList[i] = new JLabel(carQueryResult[i][0]+"");
			goTimeList[i] = new JLabel(String.format("%02d",carQueryResult[i][1])+":"+String.format("%02d",carQueryResult[i][2]));
			arriveTimeList[i] = new JLabel(String.format("%02d",carQueryResult[i][3])+":"+String.format("%02d",carQueryResult[i][4]));
			totalTimeList[i] = new JLabel(String.format("%02d",carQueryResult[i][5])+":"+String.format("%02d",carQueryResult[i][6]));
			if(carQueryResult[i][7] == 100) {
				discountList[i] = new JLabel();
			}else {
				discountList[i] = new JLabel(carQueryResult[i][7]+"折");
			}

			panel3.add(checkboxList[i], getConstraints(0, i+3, 1, 1, 0, 0, GridBagConstraints.EAST));
			panel3.add(carNumberList[i], getConstraints(1, i+3, 1, 1, 0, 0, GridBagConstraints.EAST));
			panel3.add(discountList[i], getConstraints(2, i+3, 1, 1, 0, 0, GridBagConstraints.EAST));
			panel3.add(goTimeList[i], getConstraints(3, i+3, 1, 1, 0, 0, GridBagConstraints.EAST));
			panel3.add(arriveTimeList[i], getConstraints(4, i+3, 1, 1, 0, 0, GridBagConstraints.EAST));
			panel3.add(totalTimeList[i], getConstraints(5, i+3, 1, 1, 0, 0, GridBagConstraints.EAST));
		}
		checkboxList[0].setSelected(true);
		

		panel3.add(label3_7, getConstraints(0, carQueryResult.length+3, 6, 2, 0, 0, GridBagConstraints.SOUTHWEST));
		//行程	日期	車次	起程站	到達站	出發時間	到達時間
		
		JLabel label3_8 = new JLabel("行程");
		JLabel label3_9 = new JLabel("日期");
		JLabel label3_10 = new JLabel("車次");
		JLabel label3_11 = new JLabel("起程站");
		JLabel label3_12 = new JLabel("到達站");
		JLabel label3_13 = new JLabel("出發時間");
		JLabel label3_14 = new JLabel("到達時間");
		
		panel3.add(label3_8, getConstraints(0, carQueryResult.length+5, 1, 1, 0, 0, GridBagConstraints.BOTH));
		panel3.add(label3_9, getConstraints(1, carQueryResult.length+5, 1, 1, 0, 0, GridBagConstraints.BOTH));
		panel3.add(label3_10, getConstraints(2, carQueryResult.length+5, 1, 1, 0, 0, GridBagConstraints.EAST));
		panel3.add(label3_11, getConstraints(3, carQueryResult.length+5, 1, 1, 0, 0, GridBagConstraints.BOTH));
		panel3.add(label3_12, getConstraints(4, carQueryResult.length+5, 1, 1, 0, 0, GridBagConstraints.BOTH));
		panel3.add(label3_13, getConstraints(5, carQueryResult.length+5, 1, 1, 0, 0, GridBagConstraints.BOTH));
		panel3.add(label3_14, getConstraints(6, carQueryResult.length+5, 1, 1, 0, 0, GridBagConstraints.BOTH));
		
		JLabel label3_15 = new JLabel("去程");
		JLabel label3_16 = new JLabel(order.StartDate);
		JLabel label3_17 = new JLabel(""+carQueryResult[checked][0]);
		JLabel label3_18 = new JLabel(order.From);
		JLabel label3_19 = new JLabel(order.To);
		JLabel label3_20 = new JLabel(String.format("%02d",carQueryResult[checked][1])+":"+String.format("%02d",carQueryResult[checked][2]));
		JLabel label3_21 = new JLabel(String.format("%02d",carQueryResult[checked][3])+":"+String.format("%02d",carQueryResult[checked][4]));
		
		panel3.add(label3_15, getConstraints(0, carQueryResult.length+6, 1, 1, 0, 0, GridBagConstraints.BOTH));
		panel3.add(label3_16, getConstraints(1, carQueryResult.length+6, 1, 1, 0, 0, GridBagConstraints.BOTH));
		panel3.add(label3_17, getConstraints(2, carQueryResult.length+6, 1, 1, 0, 0, GridBagConstraints.EAST));
		panel3.add(label3_18, getConstraints(3, carQueryResult.length+6, 1, 1, 0, 0, GridBagConstraints.BOTH));
		panel3.add(label3_19, getConstraints(4, carQueryResult.length+6, 1, 1, 0, 0, GridBagConstraints.BOTH));
		panel3.add(label3_20, getConstraints(5, carQueryResult.length+6, 1, 1, 0, 0, GridBagConstraints.BOTH));
		panel3.add(label3_21, getConstraints(6, carQueryResult.length+6, 1, 1, 0, 0, GridBagConstraints.BOTH));
		
		String tmp = "";
		if(order.TicketCount[0] > 0) {
			tmp += "全票"+order.TicketCount[0]+"張";
		}
		if(order.TicketCount[1] > 0) {
			tmp += " 早鳥"+order.TicketCount[1]+"張";
		}
		if(order.TicketCount[2] > 0) {
			tmp += " 大學生"+order.TicketCount[2]+"張";
		}
		
		JLabel label3_22 = new JLabel(order.SeatType);
		JLabel label3_23 = new JLabel(tmp);
		
		panel3.add(label3_22, getConstraints(0, carQueryResult.length+7, 1, 1, 0, 0, GridBagConstraints.BOTH));
		panel3.add(label3_23, getConstraints(1, carQueryResult.length+7, 1, 1, 0, 0, GridBagConstraints.BOTH));

		JButton button3_1 = new JButton("重新查詢");
		JButton button3_2 = new JButton("確認車次");
		button3_1.setBackground(new Color(153,153,153));
		button3_2.setBackground(Color.ORANGE);
		
		panel3.add(button3_1, getConstraints(0, carQueryResult.length+8, 2, 1, 0, 0, GridBagConstraints.BOTH));
		panel3.add(button3_2, getConstraints(5, carQueryResult.length+8, 2, 1, 0, 0, GridBagConstraints.BOTH));
		

//=========================================================

//==================panel 4================================
		
		JPanel panel4 = new JPanel(new GridBagLayout());

		JLabel label4_1 = new JLabel("訂位明細");
		JLabel label4_2 = new JLabel("行程");
		JLabel label4_3 = new JLabel("日期");
		JLabel label4_4 = new JLabel("車次");
		JLabel label4_5 = new JLabel("起程站");
		JLabel label4_6 = new JLabel("到達站");
		JLabel label4_7 = new JLabel("出發時間");
		JLabel label4_8 = new JLabel("到達時間");
		JLabel label4_9 = new JLabel("全票");
		JLabel label4_10 = new JLabel("早鳥, 大學生");
		JLabel label4_11 = new JLabel("小計");
		label4_1.setForeground(Color.ORANGE);
		
		panel4.add(label4_1, getConstraints(0, 0, 1, 1, 0, 0, GridBagConstraints.BOTH));
		panel4.add(label4_2, getConstraints(0, 1, 1, 1, 0, 0, GridBagConstraints.BOTH));
		panel4.add(label4_3, getConstraints(1, 1, 1, 1, 0, 0, GridBagConstraints.BOTH));
		panel4.add(label4_4, getConstraints(2, 1, 1, 1, 0, 0, GridBagConstraints.BOTH));
		panel4.add(label4_5, getConstraints(3, 1, 1, 1, 0, 0, GridBagConstraints.BOTH));
		panel4.add(label4_6, getConstraints(4, 1, 1, 1, 0, 0, GridBagConstraints.BOTH));
		panel4.add(label4_7, getConstraints(5, 1, 1, 1, 0, 0, GridBagConstraints.BOTH));
		panel4.add(label4_8, getConstraints(6, 1, 1, 1, 0, 0, GridBagConstraints.BOTH));
		panel4.add(label4_9, getConstraints(7, 1, 1, 1, 0, 0, GridBagConstraints.BOTH));
		panel4.add(label4_10, getConstraints(8, 1, 1, 1, 0, 0, GridBagConstraints.BOTH));
		panel4.add(label4_11, getConstraints(9, 1, 1, 1, 0, 0, GridBagConstraints.BOTH));
		
		JLabel label4_12 = new JLabel("去程");
		JLabel label4_13 = new JLabel(order.StartDate);
		JLabel label4_14 = new JLabel(""+carQueryResult[checked][0]);//車次, 開始時, 開始分, 結束時, 結束分, 行車時, 行車分, 折數
		JLabel label4_15 = new JLabel(order.From);
		JLabel label4_16 = new JLabel(order.To);
		JLabel label4_17 = new JLabel(String.format("%02d",carQueryResult[checked][1])+":"+String.format("%02d",carQueryResult[checked][2]));
		JLabel label4_18 = new JLabel(String.format("%02d",carQueryResult[checked][3])+":"+String.format("%02d",carQueryResult[checked][4]));
		JLabel label4_19 = new JLabel(""+order.TicketCount[0]);
		JLabel label4_20 = new JLabel(""+order.TicketCount[1]+", "+order.TicketCount[2]);
		JLabel label4_21 = new JLabel("TWD"+ticketPrice);
		
		panel4.add(label4_12, getConstraints(0, 2, 1, 1, 0, 0, GridBagConstraints.BOTH));
		panel4.add(label4_13, getConstraints(1, 2, 1, 1, 0, 0, GridBagConstraints.BOTH));
		panel4.add(label4_14, getConstraints(2, 2, 1, 1, 0, 0, GridBagConstraints.BOTH));
		panel4.add(label4_15, getConstraints(3, 2, 1, 1, 0, 0, GridBagConstraints.BOTH));
		panel4.add(label4_16, getConstraints(4, 2, 1, 1, 0, 0, GridBagConstraints.BOTH));
		panel4.add(label4_17, getConstraints(5, 2, 1, 1, 0, 0, GridBagConstraints.BOTH));
		panel4.add(label4_18, getConstraints(6, 2, 1, 1, 0, 0, GridBagConstraints.BOTH));
		panel4.add(label4_19, getConstraints(7, 2, 1, 1, 0, 0, GridBagConstraints.BOTH));
		panel4.add(label4_20, getConstraints(8, 2, 1, 1, 0, 0, GridBagConstraints.EAST));
		panel4.add(label4_21, getConstraints(9, 2, 1, 1, 0, 0, GridBagConstraints.BOTH));
		

		JLabel label4_22 = new JLabel("車廂: "+order.SeatType);
		tmp = "";
		if(order.TicketCount[0] > 0) {
			tmp += "全票"+order.TicketCount[0]+"張";
		}
		if(order.TicketCount[1] > 0) {
			tmp += " 早鳥"+order.TicketCount[1]+"張";
		}
		if(order.TicketCount[2] > 0) {
			tmp += " 大學生"+order.TicketCount[2]+"張";
		}
		JLabel label4_23 = new JLabel("票數: "+ tmp);
		JLabel label4_24 = new JLabel("總票價 TWD" + ticketPrice);
		label4_24.setForeground(Color.RED);
		panel4.add(label4_22, getConstraints(0, 3, 1, 1, 0, 0, GridBagConstraints.BOTH));
		panel4.add(label4_23, getConstraints(1, 3, 1, 1, 0, 0, GridBagConstraints.EAST));
		panel4.add(label4_24, getConstraints(9, 3, 1, 1, 0, 0, GridBagConstraints.BOTH));
		
		JLabel label4_25 = new JLabel("取票人資訊");
		JLabel label4_26 = new JLabel("身分證號碼");
		JTextArea textarea4_1 = new JTextArea();
		label4_25.setForeground(Color.ORANGE);
		panel4.add(label4_25, getConstraints(0, 4, 1, 1, 0, 0, GridBagConstraints.BOTH));
		panel4.add(label4_26, getConstraints(0, 5, 1, 1, 0, 0, GridBagConstraints.BOTH));
		panel4.add(textarea4_1, getConstraints(1, 5, 1, 1, 0, 0, GridBagConstraints.BOTH));
		
		JButton button4_1 = new JButton("取消");
		JButton button4_2 = new JButton("完成訂位");
		button4_2.setBackground(Color.ORANGE);
		panel4.add(button4_1, getConstraints(0, 6, 1, 1, 0, 0, GridBagConstraints.BOTH));
		panel4.add(button4_2, getConstraints(9, 6, 1, 1, 0, 0, GridBagConstraints.BOTH));
		

//=========================================================

//==================panel 5================================
		JPanel panel5 = new JPanel(new GridBagLayout());
		
		JLabel label5_1 = new JLabel("完成訂單");
		
		JButton button5_1 = new JButton("返回");

		panel5.add(label5_1, getConstraints(0, 0, 1, 1, 0, 0, GridBagConstraints.BOTH));
		panel5.add(button5_1, getConstraints(0, 1, 1, 1, 0, 0, GridBagConstraints.BOTH));

		
//=========================================================		
		
		backDateLabel.setVisible(false);
		backDatePicker.setVisible(false);
		backTimeLabel.setVisible(false);
		backTimeSpinner.setVisible(false);
		
		backDateLabel2.setVisible(false);
		backDatePicker2.setVisible(false);
		backTimeLabel2.setVisible(false);
		backTimeSpinner2.setVisible(false);
		
		button4_1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				panel1.setVisible(true);
				panel2.setVisible(false);
				panel3.setVisible(false);
				panel4.setVisible(false);
				panel5.setVisible(false);
			}
		});
		
		button4_2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				panel1.setVisible(false);
				panel2.setVisible(false);
				panel3.setVisible(false);
				panel4.setVisible(false);
				panel5.setVisible(true);
			}
		});
		
		
		button5_1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				panel1.setVisible(true);
				panel2.setVisible(false);
				panel3.setVisible(false);
				panel4.setVisible(false);
				panel5.setVisible(false);
				
			}
		});
		//==================panel 0 setting================================
		TimePriceButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				mode = 1;
				panel1.setVisible(true);
				panel2.setVisible(false);
				panel3.setVisible(false);
				panel4.setVisible(false);
				panel5.setVisible(false);
			}
		});
		
		OnlineOrderButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				mode = 2;
				panel1.setVisible(false);
				panel2.setVisible(true);
				panel3.setVisible(false);
				panel4.setVisible(false);
				panel5.setVisible(false);
			}
		});
		
		TicketInfoButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				mode = 3;
				panel1.setVisible(false);
				panel2.setVisible(false);
				panel3.setVisible(true);
				panel4.setVisible(false);
				panel5.setVisible(false);
			}
		});

		//=================================================================
		//==================panel 1 setting================================
		startStationComboBox.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String str = String.valueOf(startStationComboBox.getSelectedItem());
				query.From = str;
			}
		});
		endStationComboBox.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String str = String.valueOf(endStationComboBox.getSelectedItem());
				query.To = str;
			}
		});
		goDatePicker.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String str = String.valueOf(goDatePicker.getJFormattedTextField().getText());
				//System.out.println(str);
				query.StartDate = str;
			}
		});
		goTimeSpinner.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				String str = String.valueOf(goTimeSpinner.getValue());
				query.StartTime = str;
			}
		});
		backDatePicker.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String str = String.valueOf(backDatePicker.getJFormattedTextField().getText());
				//System.out.println(str);
				query.EndDate = str;
			}
		});
		backTimeSpinner.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				String str = String.valueOf(backTimeSpinner.getValue());
				query.EndTime = str;
			}
		});
		queryButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				mode = 4;
			}
		});
		tripTypeComboBox.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String tripTypeStr = String.valueOf(tripTypeComboBox.getSelectedItem());
				// System.out.println(tripTypeStr);
				if (tripTypeStr.equals("單程")) {
					backDateLabel.setVisible(false);
					backDatePicker.setVisible(false);
					backTimeLabel.setVisible(false);
					backTimeSpinner.setVisible(false);
				} else {
					backDateLabel.setVisible(true);
					backDatePicker.setVisible(true);
					backTimeLabel.setVisible(true);
					backTimeSpinner.setVisible(true);

				}
			}
		});
		//=================================================================

		//==================panel 2 setting================================
		startStationComboBox2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String str = String.valueOf(startStationComboBox2.getSelectedItem());
				order.From = str;
				label3_0.setText("去程："+order.From+" - "+ order.To + "  "+ order.StartDate);
				System.out.println(str);
				label3_18.setText(order.From);
			}
		});
		endStationComboBox2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String str = String.valueOf(endStationComboBox2.getSelectedItem());
				order.To = str;
				label3_0.setText("去程："+order.From+" - "+ order.To + "  "+ order.StartDate);
				System.out.println(str);
				label3_19.setText(order.To);
			}
		});
		tripTypeComboBox2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String str = String.valueOf(tripTypeComboBox2.getSelectedItem());
				order.TripType = str;
				if (str.equals("單程")) {
					backDateLabel2.setVisible(false);
					backDatePicker2.setVisible(false);
					backTimeLabel2.setVisible(false);
					backTimeSpinner2.setVisible(false);
				} else {
					backDateLabel2.setVisible(true);
					backDatePicker2.setVisible(true);
					backTimeLabel2.setVisible(true);
					backTimeSpinner2.setVisible(true);

				}
			}
		});
		goDatePicker2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String str = String.valueOf(goDatePicker2.getJFormattedTextField().getText());
				//System.out.println(str);
				order.StartDate = str;
				label3_0.setText("去程："+order.From+" - "+ order.To + "  "+ order.StartDate);
				label3_16.setText(order.StartDate);
			}
		});
		goTimeSpinner2.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				String str = String.valueOf(goTimeSpinner2.getValue());
				order.StartTime = str;
			}
		});
		backDatePicker2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String str = String.valueOf(backDatePicker2.getJFormattedTextField().getText());
				//System.out.println(str);
				order.EndDate = str;
			}
		});
		backTimeSpinner2.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				String str = String.valueOf(backTimeSpinner2.getValue());
				order.EndTime = str;
			}
		});
		seatTypeComboBox.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String str = String.valueOf(seatTypeComboBox.getSelectedItem());
				order.SeatType = str;
				label3_22.setText(order.SeatType);
			}
		});
		adultCountComboBox.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String str = String.valueOf(adultCountComboBox.getSelectedItem());
				order.TicketCount[0] = Integer.parseInt(str);
				String tmp = "";
				if(order.TicketCount[0] > 0) {
					tmp += "全票"+order.TicketCount[0]+"張";
				}
				if(order.TicketCount[1] > 0) {
					tmp += " 早鳥"+order.TicketCount[1]+"張";
				}
				if(order.TicketCount[2] > 0) {
					tmp += " 大學生"+order.TicketCount[2]+"張";
				}
				label3_23.setText(tmp);
			}
		});
		earlybirdCountComboBox.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String str = String.valueOf(earlybirdCountComboBox.getSelectedItem());
				order.TicketCount[1] = Integer.parseInt(str);
				String tmp = "";
				if(order.TicketCount[0] > 0) {
					tmp += "全票"+order.TicketCount[0]+"張";
				}
				if(order.TicketCount[1] > 0) {
					tmp += " 早鳥"+order.TicketCount[1]+"張";
				}
				if(order.TicketCount[2] > 0) {
					tmp += " 大學生"+order.TicketCount[2]+"張";
				}
				label3_23.setText(tmp);
			}
		});
		collegeCountComboBox.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String str = String.valueOf(collegeCountComboBox.getSelectedItem());
				order.TicketCount[2] = Integer.parseInt(str);
				String tmp = "";
				if(order.TicketCount[0] > 0) {
					tmp += "全票"+order.TicketCount[0]+"張";
				}
				if(order.TicketCount[1] > 0) {
					tmp += " 早鳥"+order.TicketCount[1]+"張";
				}
				if(order.TicketCount[2] > 0) {
					tmp += " 大學生"+order.TicketCount[2]+"張";
				}
				label3_23.setText(tmp);
			}
		});
		queryButton2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				panel1.setVisible(false);
				panel2.setVisible(false);
				panel3.setVisible(true);
				panel4.setVisible(false);
				panel5.setVisible(false);
			}
		});
		//=================================================================

		//==================panel 3 setting================================
		button3_1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				panel1.setVisible(false);
				panel2.setVisible(true);
				panel3.setVisible(false);
				panel4.setVisible(false);
				panel5.setVisible(false);
			}
		});
		button3_2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				panel1.setVisible(false);
				panel2.setVisible(false);
				panel3.setVisible(false);
				panel4.setVisible(true);
				panel5.setVisible(false);
			}
		});
		for(int i=0;i<carQueryResult.length;i++) {
			checkboxList[i].addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					for(int j=0;j<carQueryResult.length;j++) {
						if(e.getSource() == checkboxList[j]) {
							checkboxList[j].setSelected(true);
							checked = j;
							label3_16.setText(order.StartDate);
							label3_17.setText(""+carQueryResult[checked][0]);
							label3_20.setText(String.format("%02d",carQueryResult[checked][1])+":"+String.format("%02d",carQueryResult[checked][2]));
							label3_21.setText(String.format("%02d",carQueryResult[checked][3])+":"+String.format("%02d",carQueryResult[checked][4]));
							
							break;
						}
					}
					for(int j=0;j<carQueryResult.length;j++) {
						if(j != checked) {
							checkboxList[j].setSelected(false);
						}
					}
				}
			});
		}
		//=================================================================

		//==================panel 4 setting================================
		//=================================================================

		//==================panel 5 setting================================
		//=================================================================

		//=====================
		panel1.setVisible(false);
		panel2.setVisible(false);
		panel3.setVisible(false);
		panel4.setVisible(false);
		panel5.setVisible(true);
		
		frame.add(panel1, getConstraints(0, 1, 6, 10, 0, 0, GridBagConstraints.BOTH));
		frame.add(panel2, getConstraints(0, 1, 6, 10, 0, 0, GridBagConstraints.BOTH));
		frame.add(panel3, getConstraints(0, 1, 6, 10, 0, 0, GridBagConstraints.BOTH));
		frame.add(panel4, getConstraints(0, 1, 6, 10, 0, 0, GridBagConstraints.BOTH));
		frame.add(panel5, getConstraints(0, 1, 6, 10, 0, 0, GridBagConstraints.BOTH));

		frame.setVisible(true);
	}
}
