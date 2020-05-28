package application;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.text.DateFormat;
import java.util.Date;
import java.util.TimeZone;
import java.util.*;
import java.text.*;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.MultipleSelectionModel;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;


public class Main extends Application {
	ListView<DataPoint> lstV;
	ObservableList<DataPoint> obsDataPoints;
	Label lblPopUp;
	Pane root;
	@Override
	public void start(Stage primaryStage) {
		try {
			// Declaring and initializing the json file
			File f = new File("Murder-on-the-2nd-Floor-Raw-Data.json");
			// Declaring and initializing an array of DataPoint objects
			DataPoint [] dataPoints = new DataPoint[452];

			try {
				//Reading the actual file
				BufferedReader in = new BufferedReader(new FileReader(f));

				String line = "", time = "", device = "", id = "", event = "";
				char guest = 'a';
				int lineNum = 0, pointNum = 0;
				/* Storing each event in the JSON file as
				an object in the array of DataPoint*/
				for(int i = 0; i < 452; i++) {
					dataPoints[i] = new DataPoint();
					lineNum = 0;
					do {
						line = in.readLine();

						if(lineNum == 1) {
							time = line.substring(5, 15);
							dataPoints[i].setTime(Integer.parseInt(time));
						}
						else if(lineNum == 2) {
							device = line.substring(19, 25);
							dataPoints[i].setDevice(device);
						}
						else if(lineNum == 3) {
							id = line.substring(22, 27);
							dataPoints[i].setId(id);
						}
						else if(lineNum == 4) {
							event = line.substring(18, 26);
							dataPoints[i].setEvent(event);
						}
						else if(lineNum == 5) {
							guest = line.charAt(21);
							dataPoints[i].setGuest(guest+"");
						}
						// Converting and storing given time (in UTC) in Eastern Standard Time
						Date date = new Date(dataPoints[i].getTime()*1000L);
						DateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
						format.setTimeZone(TimeZone.getTimeZone("EST"));
						String formatted = format.format(date);
						dataPoints[i].setEst(formatted);
						lineNum++;

					}while(lineNum < 6);
				}
				in.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
			//Sorting the array of Data Points by name
			Arrays.sort(dataPoints, DataPoint.DataPointComparator);
			// Renaming given data fields to make them clear when displayed 
			for (int i = 0; i < dataPoints.length; i++) 
			{
				if(dataPoints[i].getId().charAt(0)== '1' || dataPoints[i].getId().charAt(0) =='2') {
					dataPoints[i].setId(dataPoints[i].getId().substring(0,3));
				}
				
				
				if (dataPoints[i].getDevice().equals("door s"))
				{
					dataPoints[i].setDevice("door sensor");
				}


				else if (dataPoints[i].getDevice().equals("access"))
				{
					dataPoints[i].setDevice("access point");
				}

				else if (dataPoints[i].getDevice().equals("motion"))
				{
					dataPoints[i].setDevice("motion sensor");
				}
				else if (dataPoints[i].getDevice().equals("phone\""))
				{
					dataPoints[i].setDevice("phone");
				}	
				else if (dataPoints[i].getDevice().equals("door clo"))
				{
					dataPoints[i].setDevice("door closed");
				}

				if (dataPoints[i].getGuest().equals("n"))
				{
					dataPoints[i].setGuest("n/a");
				}

				else if (dataPoints[i].getGuest().equals("A"))
				{

					dataPoints[i].setGuest("Alok");
				}

				if (dataPoints[i].getGuest().equals("V"))
				{

					dataPoints[i].setGuest("Veronica");
				}
				if (dataPoints[i].getGuest().equals("E"))
				{

					dataPoints[i].setGuest("Eugene");
				}
				else if (dataPoints[i].getGuest().equals("J"))
				{
					dataPoints[i].setGuest("Jason");
				}

				else if (dataPoints[i].getGuest().equals("T"))
				{
					dataPoints[i].setGuest("Thomas");
				}

				else if (dataPoints[i].getGuest().equals("R"))

				{

					dataPoints[i].setGuest("Rob");

				}

				else if (dataPoints[i].getGuest().equals("K"))

				{

					dataPoints[i].setGuest("Kristina");

				}

				else if (dataPoints[i].getGuest().equals("M"))

				{

					dataPoints[i].setGuest("Marc-Andre");

				}

				else if (dataPoints[i].getGuest().equals("D"))

				{

					dataPoints[i].setGuest("Dave");

				}

				else if (dataPoints[i].getGuest().equals("S"))

				{

					dataPoints[i].setGuest("Salina");

				}

				else if (dataPoints[i].getGuest().equals("H"))

				{

					dataPoints[i].setGuest("Harrison");}
				
				
				if (dataPoints[i].getEvent().equals("new clie")) 
				{
					dataPoints[i].setEvent("new client");
				}
				else if (dataPoints[i].getEvent().equals("unlocked"))
				{
					dataPoints[i].setEvent("unlocked no keycard");
				}
				else if (dataPoints[i].getEvent().equals("user con"))
				{
					dataPoints[i].setEvent("user connected");
				}
				else if (dataPoints[i].getEvent().equals("door clo"))
				{
					dataPoints[i].setEvent("door closed");
				}
				else if (dataPoints[i].getEvent().equals("successf"))
				{
					dataPoints[i].setEvent("successful keycard unlock");
				}
				else if (dataPoints[i].getEvent().equals("motion d"))
				{
					dataPoints[i].setEvent("motion detected");
				}
				else if (dataPoints[i].getEvent().equals("user dis"))
				{
					dataPoints[i].setEvent("user disconnected");
				}
				else if (dataPoints[i].getEvent().equals("off hook"))
				{
					dataPoints[i].setEvent("off hook");
				}
				else if (dataPoints[i].getEvent().equals("on hook\""))
				{
					dataPoints[i].setEvent("on hook");
				}
				
				if (dataPoints[i].getId().equals("eleva"))
				{
					dataPoints[i].setId("elevator");
				}
				else if (dataPoints[i].getId().equals("recep"))
				{
					dataPoints[i].setId("reception");
				}
				else if (dataPoints[i].getId().equals("ice m"))
				{
					dataPoints[i].setId("ice machine");
				}
				else if (dataPoints[i].getId().equals("stair"))
				{
					dataPoints[i].setId("stairwell");
				}
				
			}

			//UI
			root = new Pane();
			root.setBackground(new Background(new BackgroundFill(Color.BLANCHEDALMOND, CornerRadii.EMPTY, Insets.EMPTY)));
			// Stroing them in an observableList
			obsDataPoints = FXCollections.observableList(Arrays.asList(dataPoints));
			lstV = new ListView<DataPoint>(obsDataPoints);
			// changing to a monochrome font to display evenly 
			lstV.setCellFactory(cell -> {
				return new ListCell<DataPoint>() {
					@Override
					protected void updateItem(DataPoint item, boolean empty) {
						super.updateItem(item, empty);
						if(item != null) {
							setText(item.toString());
							setFont(Font.font("Courier New",12));
						}
					}
				};
			});
			lstV.setPrefSize(720, 700);
			lstV.setLayoutX(20);
			lstV.setLayoutY(75);
			root.getChildren().add(lstV);
			// Declaring and initializing the properties of the ImageView 
			ImageView imgVFloorPlan = new ImageView();
			Image iFloorPlan = new Image("file:Floor-Plan.jpg");
			imgVFloorPlan.setImage(iFloorPlan);
			imgVFloorPlan.setLayoutX(800);
			imgVFloorPlan.setLayoutY(50);
			imgVFloorPlan.prefWidth(50);
			imgVFloorPlan.maxWidth(50);
			imgVFloorPlan.maxHeight(50);
			imgVFloorPlan.minWidth(50);
			imgVFloorPlan.minHeight(50);
			imgVFloorPlan.setFitHeight(725);
			imgVFloorPlan.setFitWidth(650);
			root.getChildren().add(imgVFloorPlan);
			
			//Declaring and initializing and setting properties of
			//the Labels that display on the map when an event is selected
			Label lblArray [] = new Label [33];
			for(int i = 0; i < 33; i++) {
				lblArray[i] = new Label();
				lblArray[i].setPrefSize(33, 33);
				lblArray[i].setOpacity(50);
				lblArray[i].setBackground(null);
				lblArray[i].setOnMouseEntered(new EventHandler<MouseEvent> () {
					public void handle(MouseEvent e) {
						Label lbl = (Label)e.getSource();
						if(lbl.getBackground() != null) {
							MultipleSelectionModel<DataPoint> selected = lstV.getSelectionModel();
							DataPoint selectedDp = selected.getSelectedItem();
							
							lblPopUp = new Label(selectedDp.getGuest() + " activated the " + selectedDp.getDevice() + 
									" in " + selectedDp.getId() + " causing the event '" + selectedDp.getEvent() + 
									"' at " + selectedDp.getEst());
							lblPopUp.setBackground(new Background(new BackgroundFill(Color.LINEN, null, null)));
							lblPopUp.setPrefSize(150, 100);
							lblPopUp.setWrapText(true);
							lblPopUp.setLayoutX(lbl.getLayoutX() - 20);
							lblPopUp.setLayoutY(lbl.getLayoutY() - 110);
							root.getChildren().add(lblPopUp);
						}
					}
				});
				lblArray[i].setOnMouseExited(new EventHandler<MouseEvent> () {
					public void handle(MouseEvent e) {
						Label lbl = (Label)e.getSource();
						if(lbl.getBackground() != null) {
							root.getChildren().remove(lblPopUp);
						}
					}
				});
				root.getChildren().add(lblArray[i]);
			}
			// Placing labels on their corresponding locations on the map
			lblArray[0].setId("AP1-1");
			lblArray[0].setLayoutX(875);
			lblArray[0].setLayoutY(100);
			
			lblArray[1].setId("AP1-4");
			lblArray[1].setLayoutX(1045);
			lblArray[1].setLayoutY(108);
			
			lblArray[2].setId("AP1-3");
			lblArray[2].setLayoutX(1045);
			lblArray[2].setLayoutY(332);
			
			lblArray[3].setId("AP1-2");
			lblArray[3].setLayoutX(1263);
			lblArray[3].setLayoutY(210);
			
			lblArray[4].setId("AP2-1");
			lblArray[4].setLayoutX(950);
			lblArray[4].setLayoutY(580);
			
			lblArray[5].setId("AP2-2");
			lblArray[5].setLayoutX(1270);
			lblArray[5].setLayoutY(580);

			
			lblArray[6].setId("AP2-3");
			lblArray[6].setLayoutX(1140);
			lblArray[6].setLayoutY(580);
	
			lblArray[7].setId("105");
			lblArray[7].setLayoutX(1045);
			lblArray[7].setLayoutY(280);	
			
			lblArray[8].setId("110");
			lblArray[8].setLayoutX(875);
			lblArray[8].setLayoutY(195);
			
			lblArray[9].setId("130");
			lblArray[9].setLayoutX(875);
			lblArray[9].setLayoutY(285);
			
			lblArray[10].setId("150");
			lblArray[10].setLayoutX(1370);
			lblArray[10].setLayoutY(210);
			
			lblArray[11].setId("151");
			lblArray[11].setLayoutX(1200);
			lblArray[11].setLayoutY(145);
			
			lblArray[12].setId("152");
			lblArray[12].setLayoutX(1180);
			lblArray[12].setLayoutY(290);
			
			lblArray[13].setId("155");
			lblArray[13].setLayoutX(1310);
			lblArray[13].setLayoutY(85);
			
			lblArray[14].setId("156");
			lblArray[14].setLayoutX(1330);
			lblArray[14].setLayoutY(260);
				
			lblArray[15].setId("210");
			lblArray[15].setLayoutX(855);
			lblArray[15].setLayoutY(428);
			
			lblArray[16].setId("231");
			lblArray[16].setLayoutX(945);
			lblArray[16].setLayoutY(420);
			
			lblArray[17].setId("232");
			lblArray[17].setLayoutX(945);
			lblArray[17].setLayoutY(670);
			
			lblArray[18].setId("233");
			lblArray[18].setLayoutX(1042);
			lblArray[18].setLayoutY(420);
			
			lblArray[19].setId("235");
			lblArray[19].setLayoutX(1132);
			lblArray[19].setLayoutY(420);
			
			lblArray[20].setId("241");
			lblArray[20].setLayoutX(1225);
			lblArray[20].setLayoutY(435);
			
			lblArray[21].setId("244");
			lblArray[21].setLayoutX(1225);
			lblArray[21].setLayoutY(655);
			
			lblArray[22].setId("248");
			lblArray[22].setLayoutX(1317);
			lblArray[22].setLayoutY(655);
			
			lblArray[23].setId("250");
			lblArray[23].setLayoutX(1370);
			lblArray[23].setLayoutY(580);
			
			lblArray[24].setId("200");
			lblArray[24].setLayoutX(1205);
			lblArray[24].setLayoutY(555);
			
			lblArray[25].setId("Elevator");
			lblArray[25].setLayoutX(1045);
			lblArray[25].setLayoutY(530);
			
			lblArray[26].setId("Ice Machine");
			lblArray[26].setLayoutX(1045);
			lblArray[26].setLayoutY(635);
			
			lblArray[27].setId("Stairwell");
			lblArray[27].setLayoutX(1395);
			lblArray[27].setLayoutY(640);
			
			lblArray[28].setId("Lobby");
			lblArray[28].setLayoutX(1043);
			lblArray[28].setLayoutY(51);
			
			lblArray[29].setId("Reception");
			lblArray[29].setLayoutX(1120);
			lblArray[29].setLayoutY(110);
			
			lblArray[30].setId("Elevator");
			lblArray[30].setLayoutX(1045);
			lblArray[30].setLayoutY(160);
			
			lblArray[31].setId("Stairwell");
			lblArray[31].setLayoutX(1395);
			lblArray[31].setLayoutY(270);
			
			lblArray[32].setId("220");
			lblArray[32].setLayoutX(855);
			lblArray[32].setLayoutY(630);
			
			//Displaying the corresponding label when an event is selected
			lstV.setOnMouseClicked(new EventHandler<MouseEvent>() {
				public void handle(MouseEvent e) {
					MultipleSelectionModel<DataPoint> selected = lstV.getSelectionModel();
					DataPoint selectedDp = selected.getSelectedItem();
					
					for(int i = 0; i < lblArray.length; i++) {
						lblArray[i].setBackground(null);
						if(selectedDp.getId().equalsIgnoreCase(lblArray[i].getId())) {
							lblArray[i].setBackground(new Background(new BackgroundFill(Color.RED, null, null)));
						}
					}
				}
			});
			// Declaring, initializing and setting properties of Button to sort by time
			Button btnTime = new Button("Time");
			btnTime.setPrefSize(150, 20);
			btnTime.setLayoutX(20);
			btnTime.setLayoutY(40);
			btnTime.setBackground(new Background(new BackgroundFill(Color.KHAKI, CornerRadii.EMPTY, Insets.EMPTY)));
			btnTime.setTextFill(Color.BLACK);
			btnTime.setFont(Font.font("avenir", FontWeight.NORMAL, FontPosture.REGULAR, 14));
			btnTime.setOnAction(new EventHandler<ActionEvent>() {
				public void handle (ActionEvent e) {
					DataPoint.field=2;
					Arrays.sort(dataPoints, DataPoint.DataPointComparator);
					//System.out.println(DataPoint.field);
					obsDataPoints = FXCollections.observableList(Arrays.asList(dataPoints));
					root.getChildren().remove(lstV);
					lstV = new ListView<DataPoint>(obsDataPoints);
					lstV.setCellFactory(cell -> {
						return new ListCell<DataPoint>() {
							@Override
							protected void updateItem(DataPoint item, boolean empty) {
								super.updateItem(item, empty);
								if(item != null) {
									setText(item.toString());
									setFont(Font.font("Courier New",12));
								}
							}
						};
					});
					lstV.setOnMouseClicked(new EventHandler<MouseEvent>() {
						public void handle(MouseEvent e) {
							MultipleSelectionModel<DataPoint> selected = lstV.getSelectionModel();
							DataPoint selectedDp = selected.getSelectedItem();
							
							for(int i = 0; i < lblArray.length; i++) {
								lblArray[i].setBackground(null);
								if(selectedDp.getId().equalsIgnoreCase(lblArray[i].getId())) {
									lblArray[i].setBackground(new Background(new BackgroundFill(Color.RED, null, null)));
								}
							}
						}
					});
					lstV.setOnKeyPressed(new EventHandler<KeyEvent>() {
						public void handle(KeyEvent k) {
							MultipleSelectionModel<DataPoint> selected = lstV.getSelectionModel();
							DataPoint selectedDp = selected.getSelectedItem();
							
							for(int i = 0; i < lblArray.length; i++) {
								lblArray[i].setBackground(null);
								if(selectedDp.getId().equalsIgnoreCase(lblArray[i].getId())) {
									lblArray[i].setBackground(new Background(new BackgroundFill(Color.RED, null, null)));
								}
							}
						}
					});
					lstV.setPrefSize(720, 700);
					lstV.setLayoutX(20);
					lstV.setLayoutY(75);
					root.getChildren().add(lstV);
				}
			});
			root.getChildren().add(btnTime);
			// Declaring, initializing and setting properties of Device to sort by time
			Button btnDevice = new Button("Device");
			btnDevice.setPrefSize(100, 20);
			btnDevice.setLayoutX(310);
			btnDevice.setLayoutY(40);
			btnDevice.setBackground(new Background(new BackgroundFill(Color.KHAKI, CornerRadii.EMPTY, Insets.EMPTY)));
			btnDevice.setTextFill(Color.BLACK);
			btnDevice.setFont(Font.font("avenir", FontWeight.NORMAL, FontPosture.REGULAR, 14));
			root.getChildren().add(btnDevice);
			btnDevice.setOnAction(new EventHandler<ActionEvent>() {
				public void handle (ActionEvent e) {
					DataPoint.field=1;
					Arrays.sort(dataPoints, DataPoint.DataPointComparator);
					//System.out.println(DataPoint.field);
					obsDataPoints = FXCollections.observableList(Arrays.asList(dataPoints));
					root.getChildren().remove(lstV);
					lstV = new ListView<DataPoint>(obsDataPoints);
					lstV.setCellFactory(cell -> {
						return new ListCell<DataPoint>() {
							@Override
							protected void updateItem(DataPoint item, boolean empty) {
								super.updateItem(item, empty);
								if(item != null) {
									setText(item.toString());
									setFont(Font.font("Courier New",12));
								}
							}
						};
					});
					lstV.setOnMouseClicked(new EventHandler<MouseEvent>() {
						public void handle(MouseEvent e) {
							MultipleSelectionModel<DataPoint> selected = lstV.getSelectionModel();
							DataPoint selectedDp = selected.getSelectedItem();
							
							for(int i = 0; i < lblArray.length; i++) {
								lblArray[i].setBackground(null);
								if(selectedDp.getId().equalsIgnoreCase(lblArray[i].getId())) {
									lblArray[i].setBackground(new Background(new BackgroundFill(Color.RED, null, null)));
								}
							}
						}
					});
					lstV.setOnKeyPressed(new EventHandler<KeyEvent>() {
						public void handle(KeyEvent k) {
							MultipleSelectionModel<DataPoint> selected = lstV.getSelectionModel();
							DataPoint selectedDp = selected.getSelectedItem();
							
							for(int i = 0; i < lblArray.length; i++) {
								lblArray[i].setBackground(null);
								if(selectedDp.getId().equalsIgnoreCase(lblArray[i].getId())) {
									lblArray[i].setBackground(new Background(new BackgroundFill(Color.RED, null, null)));
								}
							}
						}
					});
					lstV.setPrefSize(720, 700);
					lstV.setLayoutX(20);
					lstV.setLayoutY(75);
					root.getChildren().add(lstV);
				}
			});
			// Declaring, initializing and setting properties of Button to sort by ID
			Button btnDeviceId = new Button("ID");
			btnDeviceId.setPrefSize(75, 20);
			btnDeviceId.setLayoutX(420);
			btnDeviceId.setLayoutY(40);
			btnDeviceId.setBackground(new Background(new BackgroundFill(Color.KHAKI, CornerRadii.EMPTY, Insets.EMPTY)));
			btnDeviceId.setTextFill(Color.BLACK);
			btnDeviceId.setFont(Font.font("avenir", FontWeight.NORMAL, FontPosture.REGULAR, 14));
			root.getChildren().add(btnDeviceId);
			btnDeviceId.setOnAction(new EventHandler<ActionEvent>() {
				public void handle (ActionEvent e) {
					DataPoint.field=3;
					Arrays.sort(dataPoints, DataPoint.DataPointComparator);
					obsDataPoints = FXCollections.observableList(Arrays.asList(dataPoints));
					root.getChildren().remove(lstV);
					lstV = new ListView<DataPoint>(obsDataPoints);
					lstV.setCellFactory(cell -> {
						return new ListCell<DataPoint>() {
							@Override
							protected void updateItem(DataPoint item, boolean empty) {
								super.updateItem(item, empty);
								if(item != null) {
									setText(item.toString());
									setFont(Font.font("Courier New",12));
								}
							}
						};
					});
					lstV.setOnMouseClicked(new EventHandler<MouseEvent>() {
						public void handle(MouseEvent e) {
							MultipleSelectionModel<DataPoint> selected = lstV.getSelectionModel();
							DataPoint selectedDp = selected.getSelectedItem();
							
							for(int i = 0; i < lblArray.length; i++) {
								lblArray[i].setBackground(null);
								if(selectedDp.getId().equalsIgnoreCase(lblArray[i].getId())) {
									lblArray[i].setBackground(new Background(new BackgroundFill(Color.RED, null, null)));
								}
							}
						}
					});
					lstV.setOnKeyPressed(new EventHandler<KeyEvent>() {
						public void handle(KeyEvent k) {
							MultipleSelectionModel<DataPoint> selected = lstV.getSelectionModel();
							DataPoint selectedDp = selected.getSelectedItem();
							
							for(int i = 0; i < lblArray.length; i++) {
								lblArray[i].setBackground(null);
								if(selectedDp.getId().equalsIgnoreCase(lblArray[i].getId())) {
									lblArray[i].setBackground(new Background(new BackgroundFill(Color.RED, null, null)));
								}
							}
						}
					});
					lstV.setPrefSize(720, 700);
					lstV.setLayoutX(20);
					lstV.setLayoutY(75);
					root.getChildren().add(lstV);
				}
			});
			// Declaring, initializing and setting properties of Button to sort by Event
			Button btnEvent = new Button("Event");
			btnEvent.setPrefSize(200, 20);
			btnEvent.setLayoutX(530);
			btnEvent.setLayoutY(40);
			btnEvent.setBackground(new Background(new BackgroundFill(Color.KHAKI, CornerRadii.EMPTY, Insets.EMPTY)));
			btnEvent.setTextFill(Color.BLACK);
			btnEvent.setFont(Font.font("avenir", FontWeight.NORMAL, FontPosture.REGULAR, 14));
			btnEvent.setOnAction(new EventHandler<ActionEvent>() {
				public void handle(ActionEvent e) {
					DataPoint.field=1;
					Arrays.sort(dataPoints, DataPoint.DataPointComparator);
					obsDataPoints = FXCollections.observableList(Arrays.asList(dataPoints));
					root.getChildren().remove(lstV);
					lstV = new ListView<DataPoint>(obsDataPoints);
					lstV.setCellFactory(cell -> {
						return new ListCell<DataPoint>() {
							@Override
							protected void updateItem(DataPoint item, boolean empty) {
								super.updateItem(item, empty);
								if(item != null) {
									setText(item.toString());
									setFont(Font.font("Courier New",12));
								}
							}
						};
					});
					lstV.setOnMouseClicked(new EventHandler<MouseEvent>() {
						public void handle(MouseEvent e) {
							MultipleSelectionModel<DataPoint> selected = lstV.getSelectionModel();
							DataPoint selectedDp = selected.getSelectedItem();
							
							for(int i = 0; i < lblArray.length; i++) {
								lblArray[i].setBackground(null);
								if(selectedDp.getId().equalsIgnoreCase(lblArray[i].getId())) {
									lblArray[i].setBackground(new Background(new BackgroundFill(Color.RED, null, null)));
								}
							}
						}
					});
					lstV.setOnKeyPressed(new EventHandler<KeyEvent>() {
						public void handle(KeyEvent k) {
							MultipleSelectionModel<DataPoint> selected = lstV.getSelectionModel();
							DataPoint selectedDp = selected.getSelectedItem();
							
							for(int i = 0; i < lblArray.length; i++) {
								lblArray[i].setBackground(null);
								if(selectedDp.getId().equalsIgnoreCase(lblArray[i].getId())) {
									lblArray[i].setBackground(new Background(new BackgroundFill(Color.RED, null, null)));
								}
							}
						}
					});
					lstV.setPrefSize(720, 700);
					lstV.setLayoutX(20);
					lstV.setLayoutY(75);
					root.getChildren().add(lstV);
				}
			});
			root.getChildren().add(btnEvent);
			// Declaring, initializing and setting properties of Button to sort by Guest
			Button btnGuestID = new Button("Guest");
			btnGuestID.setPrefSize(100, 20);
			btnGuestID.setLayoutX(190);
			btnGuestID.setLayoutY(40);
			btnGuestID.setBackground(new Background(new BackgroundFill(Color.KHAKI, CornerRadii.EMPTY, Insets.EMPTY)));
			btnGuestID.setTextFill(Color.BLACK);
			btnGuestID.setFont(Font.font("avenir", FontWeight.NORMAL, FontPosture.REGULAR, 14));
			root.getChildren().add(btnGuestID);
			btnGuestID.setOnAction(new EventHandler<ActionEvent>() {
				public void handle (ActionEvent e) {
					DataPoint.field =0;					
					Arrays.sort(dataPoints, DataPoint.DataPointComparator);
					obsDataPoints = FXCollections.observableList(Arrays.asList(dataPoints));
					root.getChildren().remove(lstV);
					lstV = new ListView<DataPoint>(obsDataPoints);
					lstV.setCellFactory(cell -> {
						return new ListCell<DataPoint>() {
							@Override
							protected void updateItem(DataPoint item, boolean empty) {
								super.updateItem(item, empty);
								if(item != null) {
									setText(item.toString());
									setFont(Font.font("Courier New",12));
								}
							}
						};
					});
					lstV.setOnMouseClicked(new EventHandler<MouseEvent>() {
						public void handle(MouseEvent e) {
							MultipleSelectionModel<DataPoint> selected = lstV.getSelectionModel();
							DataPoint selectedDp = selected.getSelectedItem();
							
							for(int i = 0; i < lblArray.length; i++) {
								lblArray[i].setBackground(null);
								if(selectedDp.getId().equalsIgnoreCase(lblArray[i].getId())) {
									lblArray[i].setBackground(new Background(new BackgroundFill(Color.RED, null, null)));
								}
							}
						}
					});
					lstV.setOnKeyPressed(new EventHandler<KeyEvent>() {
						public void handle(KeyEvent k) {
							MultipleSelectionModel<DataPoint> selected = lstV.getSelectionModel();
							DataPoint selectedDp = selected.getSelectedItem();
							
							for(int i = 0; i < lblArray.length; i++) {
								lblArray[i].setBackground(null);
								if(selectedDp.getId().equalsIgnoreCase(lblArray[i].getId())) {
									lblArray[i].setBackground(new Background(new BackgroundFill(Color.RED, null, null)));
								}
							}
						}
					});
					lstV.setPrefSize(720, 700);
					lstV.setLayoutX(20);
					lstV.setLayoutY(75);
					root.getChildren().add(lstV);
				}
			});
			
			lstV.setOnKeyPressed(new EventHandler<KeyEvent>() {
				public void handle(KeyEvent k) {
					MultipleSelectionModel<DataPoint> selected = lstV.getSelectionModel();
					DataPoint selectedDp = selected.getSelectedItem();
					
					for(int i = 0; i < lblArray.length; i++) {
						lblArray[i].setBackground(null);
						if(selectedDp.getId().equalsIgnoreCase(lblArray[i].getId())) {
							lblArray[i].setBackground(new Background(new BackgroundFill(Color.RED, null, null)));
						}
					}
				}
			});
			
			Label lblFlr1 = new Label("Floor 1");
			lblFlr1.setFont(Font.font("avenir", FontWeight.BOLD, FontPosture.REGULAR, 14));
			lblFlr1.setRotate(-90);
			lblFlr1.setLayoutY(200);
			lblFlr1.setLayoutX(760);
			root.getChildren().add(lblFlr1);
			
			Label lblFlr2 = new Label("Floor 2");
			lblFlr2.setFont(Font.font("avenir", FontWeight.BOLD, FontPosture.REGULAR, 14));
			lblFlr2.setRotate(-90);
			lblFlr2.setLayoutY(600);
			lblFlr2.setLayoutX(760);
			root.getChildren().add(lblFlr2);
			
			Scene scene = new Scene(root,1500,800);

			primaryStage.setTitle("Mystery Tracker");
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}

		//UI
	}

	public static void main(String[] args) {
		launch(args);
	}
}
