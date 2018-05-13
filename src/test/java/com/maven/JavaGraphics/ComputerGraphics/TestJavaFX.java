package com.maven.JavaGraphics.ComputerGraphics;

import java.nio.ByteBuffer;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.PixelFormat;
import javafx.scene.image.PixelWriter;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Line;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeLineCap;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

public class TestJavaFX  extends Application{

	/** 
	*
	* @ClassName : TestJavaFX.java
	* @author : Magneto_Wang
	* @date  2018年5月13日 上午9:21:07
	* @Description  TODO
	* 
	*/
	// Create the Message Label
	Label messageLbl = new Label("Enter your Name into the Text Fields.");
	
	/*
	 * 
	 * A JavaFX application is a class that must inherit from the Application class that is in the javafx.application package. So it is
necessary to override the start() method
	 * (non-Javadoc)
	 * @see javafx.application.Application#start(javafx.stage.Stage)
	 */
	
	public void start(Stage stage){
		stage =new Stage();
//		TestText(stage);
//		TestLabel(stage);
//		TestTextField(stage);
//		TestButton(stage);
//		TestMenuButton(stage);
//		TestListView(stage);
//		TestTimeLine(stage);
//		TestCuePoint(stage);
//		TestPixel(stage);
		TestPath(stage);
		
		TestShape();
//		TestClear();
	}
	public void TestClear(){
		Stage stage=new Stage();
		// Create the Canvas
		Canvas canvas = new Canvas(400, 600);
		// Get the graphics context of the canvas
		GraphicsContext gc = canvas.getGraphicsContext2D();
		// Set line width
		gc.setLineWidth(2.0);
		// Set fill color
		gc.setFill(Color.GREEN);
		// Draw a rounded Rectangle
		gc.fillRoundRect(50, 50, 300, 100, 10, 10);
		// Clear the rectangular area from the canvas
		gc.clearRect(80, 80, 130, 50);
		// Create the Pane
		Pane root = new Pane();
		// Set the Style-properties of the Pane
		root.setStyle("-fx-padding: 10;" +
		"-fx-border-style: solid inside;" +
		"-fx-border-width: 2;" +
		"-fx-border-insets: 5;" +
		"-fx-border-radius: 5;" +
		"-fx-border-color: blue;");
		// Add the Canvas to the Pane
		root.getChildren().add(canvas);
		// Create the Scene
		Scene scene = new Scene(root);
		// Add the Scene to the Stage
		stage.setScene(scene);
		// Set the Title of the Stage
		stage.setTitle("Clearing the Area of a Canvas");
		// Display the Stage
		stage.show();
	}
	public void TestShape(){
		Stage stage=new Stage();
		// Create the Canvas with a width of 400 px and a height of 200 px.
		Canvas canvas = new Canvas(400, 200);
		// Get the graphics context of the canvas
		GraphicsContext gc = canvas.getGraphicsContext2D();
		// Set line width
		gc.setLineWidth(2.0);
		// Set fill color
		gc.setFill(Color.RED);
		// Draw a rounded Rectangle
		gc.strokeRoundRect(10, 10, 50, 50, 10, 10);
		// Draw a filled rounded Rectangle
		gc.fillRoundRect(100, 10, 50, 50, 10, 10);
		// Change the fill color
		gc.setFill(Color.BLUE);
		// Draw an Oval
		gc.strokeOval(10, 70, 50, 30);
		// Draw a filled Oval
		gc.fillOval(100, 70, 50, 30);
		// Draw a Line
		gc.strokeLine(200, 50, 300, 50);
		// Draw an Arc
		gc.strokeArc(320, 10, 50, 50, 40, 80, ArcType.ROUND);
		// Draw a filled Arc
		gc.fillArc(320, 70, 50, 50, 00, 120, ArcType.OPEN);
		// Create the Pane
		Pane root = new Pane();
		// Set the Style-properties of the Pane
		root.setStyle("-fx-padding: 10;" +
		"-fx-border-style: solid inside;" +
		"-fx-border-width: 2;" +
		"-fx-border-insets: 5;" +
		"-fx-border-radius: 5;" +
		"-fx-border-color: blue;");
		// Add the Canvas to the Pane
		root.getChildren().add(canvas);
		// Create the Scene
		Scene scene = new Scene(root);
		// Add the Scene to the Stage
		stage.setScene(scene);
		// Set the Title of the Stage
		stage.setTitle("Drawing Basic Shapes on a Canvas");
		// Display the Stage
		stage.show();
	}
	
	
	public void TestPath(Stage stage){
		// Create the Canvas
		Canvas canvas = new Canvas(400, 200);
		// Get the graphics context of the canvas
		GraphicsContext gc = canvas.getGraphicsContext2D();
		// Set line width
		gc.setLineWidth(2.0);
		// Set the Color
		gc.setStroke(Color.GREEN);
		// Set fill color
		gc.setFill(Color.LIGHTCYAN);
		// Start the Path
		gc.beginPath();
		// Make different Paths
		gc.moveTo(50, 50);
		gc.quadraticCurveTo(30, 150, 300, 200);
		gc.fill();
		// End the Path
		gc.closePath();
		// Draw the Path
		gc.stroke();
		// Create the Pane
		Pane root = new Pane();
		// Set the Style-properties of the Pane
		root.setStyle("-fx-padding: 10;" +
		"-fx-border-style: solid inside;" +
		"-fx-border-width: 2;" +
		"-fx-border-insets: 5;" +
		"-fx-border-radius: 5;" +
		"-fx-border-color: blue;");
		// Add the Canvas to the Pane
		root.getChildren().add(canvas);
		// Create the Scene
		Scene scene = new Scene(root);
		// Add the Scene to the Stage
		stage.setScene(scene);
		// Set the Title of the Stage
		stage.setTitle("Drawing Paths on a Canvas");
		// Display the Stage
		stage.show();
	}
	
	
	
	private static final int RECT_WIDTH = 40;
	private static final int RECT_HEIGHT = 20;
	public void TestPixel(Stage stage){
		Canvas canvas = new Canvas(400, 200);
		// Get the graphics context of the canvas
		GraphicsContext gc = canvas.getGraphicsContext2D();
		// Set line width
		gc.setLineWidth(2.0);
		// Write custom pixels to create a pattern
		writePixels(gc);
		// Create the Pane
		Pane root = new Pane();
		// Set the Style-properties of the Pane
		root.setStyle("-fx-padding: 10;" +
		"-fx-border-style: solid inside;" +
		"-fx-border-width: 2;" +
		"-fx-border-insets: 5;" +
		"-fx-border-radius: 5;" +
		"-fx-border-color: blue;");
		// Add the Canvas to the Pane
		root.getChildren().add(canvas);
		// Create the Scene
		Scene scene = new Scene(root);
		// Add the Scene to the Stage
		stage.setScene(scene);
		// Set the Title of the Stage
		stage.setTitle("Writing Pixels on a Canvas");
		// Display the Stage
		stage.show();
	}
	private void writePixels(GraphicsContext gc){
		// Define properties of the Image
		int spacing = 5;
		int imageWidth = 300;
		int imageHeight = 100;
		int rows = imageHeight / (RECT_HEIGHT + spacing);
		int columns = imageWidth / (RECT_WIDTH + spacing);
		// Get the Pixels
		byte[] pixels = this.getPixelsData();
		// Create the PixelWriter
		PixelWriter pixelWriter = gc.getPixelWriter();
		// Define the PixelFormat
		PixelFormat<ByteBuffer> pixelFormat = PixelFormat.getByteRgbInstance();
		// Write the pixels to the canvas
		for (int y = 0; y < rows; y++) {
			for (int x = 0; x < columns; x++) {
				int xPos = 50 + x * (RECT_WIDTH + spacing);
				int yPos = 50 + y * (RECT_HEIGHT + spacing);
				pixelWriter.setPixels(xPos, yPos, RECT_WIDTH, RECT_HEIGHT, 
						pixelFormat, pixels, 0, RECT_WIDTH * 3);
			}
		}
	}
	private byte[] getPixelsData(){
		// Create the Array
		byte[] pixels = new byte[RECT_WIDTH * RECT_HEIGHT * 3];
		// Set the ration
		double ratio = 1.0 * RECT_HEIGHT / RECT_WIDTH;
		// Generate pixel data
		for (int y = 0; y < RECT_HEIGHT; y++) {
			for (int x = 0; x < RECT_WIDTH; x++) {
				int i = y * RECT_WIDTH * 3 + x * 3;
				if (x <= y / ratio) {
					pixels[i] = 1;
					pixels[i + 1] = 1;
					pixels[i + 2] = 0;
				} else {
					pixels[i] = -1;
					pixels[i + 1] = 1;
					pixels[i + 2] = 0;
				}
			}
		}
		// Return the Pixels
		return pixels;
	}
	
	// Create the Text
	Text text = new Text("A Scrolling Text!");
	// Create the Pane
	Pane pane = new Pane();
	// Create the Cue Points List View
	ListView<String> cuePointsListView = new ListView<String>();
	// Create the Timeline
	Timeline timeline = new Timeline();
	public void TestCuePoint(Stage stage){
		// Create the Text
		text.setTextOrigin(VPos.TOP);
		text.setFont(Font.font(24));
		// Set the Size of the ListView
		cuePointsListView.setPrefSize(100, 150);
		// Create the Pane
		pane = new Pane(text);
		// Create the BorderPane
		BorderPane root = new BorderPane();
		// Set the Size of the BorderPane
		root.setPrefSize(600, 250);
		// Set the Style-properties of the BorderPane
		root.setStyle("-fx-padding: 10;" +
		"-fx-border-style: solid inside;" +
		"-fx-border-width: 2;" +
		"-fx-border-insets: 5;" +
		"-fx-border-radius: 5;" +
		"-fx-border-color: blue;");
		// Add the Pane and ListView to the BorderPane
		root.setBottom(pane);
		root.setLeft(cuePointsListView);
		// Create the Scene
		Scene scene = new Scene(root);
		// Add the Scene to the Stage
		stage.setScene(scene);
		// Set the Title of the Stage
		stage.setTitle("Cue Points");
		// Display the Stage
		stage.show();
		// Setup the Animation
		this.setupAnimation();
		// Add the Cue Points to the List
		this.addCuePoints();
	}
	private void setupAnimation()
	{
		// Get width of Pane and Text
		double paneWidth = pane.getWidth();
		double textWidth = text.getLayoutBounds().getWidth();
		// Define the Durations
		Duration startDuration = Duration.ZERO;
		Duration endDuration = Duration.seconds(10);
		// Create the initial and final key frames
		KeyValue startKeyValue = new KeyValue(text.translateXProperty(), paneWidth);
		KeyFrame startKeyFrame = new KeyFrame(startDuration, startKeyValue);
		KeyValue endKeyValue = new KeyValue(text.translateXProperty(), -1.0 * textWidth);
		KeyFrame endKeyFrame = new KeyFrame(endDuration, endKeyValue);
		// Create the Timeline
		timeline = new Timeline(startKeyFrame, endKeyFrame);
		// Let the animation run forever
		timeline.setCycleCount(Timeline.INDEFINITE);
		// Play the Animation
		timeline.play();
	}
	private void addCuePoints()
	{
		// Add two cue points directly to the map
		timeline.getCuePoints().put("4", Duration.seconds(4));
		timeline.getCuePoints().put("7", Duration.seconds(7));
		// Add all cue points from the map to the ListView in the order
		// of their durations
		cuePointsListView.getItems().add(0, "Start");
		cuePointsListView.getItems().addAll(1, timeline.getCuePoints().keySet());
		cuePointsListView.getItems().add("End");
		// Add Event Handler to the List
		cuePointsListView.setOnMousePressed(new EventHandler<MouseEvent>() {

			public void handle(MouseEvent event) {
				timeline.jumpTo(cuePointsListView.getSelectionModel().getSelectedItem());
			}
		});
	}
	
	
	
	
	public void TestTimeLine(Stage stage){
		// Create the Text
		Text text = new Text("A Scrolling Text!");
		// Set the Font of the Text
		text.setFont(Font.font(36));
		// Create the VBox
		VBox root = new VBox(text);
		// Set the Size of the Pane
		root.setPrefSize(400, 100);
		// Set the Style-properties of the Pane
		root.setStyle("-fx-padding: 10;" +
		"-fx-border-style: solid inside;" +
		"-fx-border-width: 2;" +
		"-fx-border-insets: 5;" +
		"-fx-border-radius: 5;" +
		"-fx-border-color: blue;");
		// Create the Scene
		Scene scene = new Scene(root);
		// Add the Scene to the Stage
		stage.setScene(scene);
		// Set the Title of the Stage
		stage.setTitle("A simple JavaFX Animation Example");
		// Display the Stage
		stage.show();
		
		// Get the Width of the Scene and the Text
		double sceneWidth = scene.getWidth();
		double textWidth = text.getLayoutBounds().getWidth();
		// Define the Durations
		Duration startDuration = Duration.ZERO;
		Duration endDuration = Duration.seconds(2);
		
		// Create the start and end Key Frames
		KeyValue startKeyValue = new KeyValue(text.translateXProperty(), sceneWidth);
		KeyFrame startKeyFrame = new KeyFrame(startDuration, startKeyValue);
		KeyValue endKeyValue = new KeyValue(text.translateXProperty(), -1.0*textWidth);
		KeyFrame endKeyFrame = new KeyFrame(endDuration, endKeyValue);
		// Create a Timeline
		Timeline timeline = new Timeline(startKeyFrame, endKeyFrame);
		// Let the animation run forever
		timeline.setCycleCount(Timeline.INDEFINITE);
		// Run the animation
		timeline.play();
	}
	// Create the Selection Label
	Label selectionLbl = new Label("Your selection: None");
	public void TestListView(Stage stage){
		// Create the ListView
		ListView<String> cars = new ListView<String>();
		// Add Items to the ListView
		cars.getItems().addAll("Ford", "Audi", "Ferrari", "Porsche");
		// Select the first car from the list
		cars.getSelectionModel().selectFirst();
		// Add ChangeListener to the ListView
		cars.getSelectionModel().selectedItemProperty().addListener(new 
		ChangeListener<String>()
		{
		public void changed(ObservableValue<? extends String> ov, final String 
		oldValue, final String newValue)
		{
		selectionLbl.setText("Your selection: " + newValue);
		}
		});
		// Create the GridPane
		GridPane root = new GridPane();
		// Set the horizontal and vertical spacing between columns and rows
		root.setVgap(10);
		root.setHgap(10);
		// Add ListView and Label to the GridPane
		root.addRow(0, cars);
		root.addRow(1, selectionLbl);
		// Set the Size of the GridPane
		root.setMinSize(300, 200);
		/*
		*
		Set the padding of the GridPane
		*
		Set the border-style of the GridPane
		*
		Set the border-width of the GridPane
		*
		Set the border-insets of the GridPane
		*
		Set the border-radius of the GridPane
		*
		Set the border-color of the GridPane
		*/
		root.setStyle("-fx-padding: 10;" +
		"-fx-border-style: solid inside;" +
		"-fx-border-width: 2;" +
		"-fx-border-insets: 5;" +
		"-fx-border-radius: 5;" +
		"-fx-border-color: blue;");
		// Create the Scene
		Scene scene = new Scene(root);
		// Add the scene to the Stage
		stage.setScene(scene);
		// Set the title of the Stage
		stage.setTitle("A ListView Example");
		// Display the Stage
		stage.show();
	}
	
	public void TestMenuButton(Stage stage){
		// Create the MenuItem ford
		MenuItem ford = new MenuItem("Ford");
		// Add EventHandler to the MenuItem
		ford.setOnAction(new EventHandler<ActionEvent>()
		{
			public void handle(ActionEvent e)
		{
		printMessage("You have selected: Ford");
		}
		});
		// Create the MenuItem audi
		MenuItem audi = new MenuItem("Audi");
		// Add EventHandler to the MenuItem
		audi.setOnAction(new EventHandler<ActionEvent>()
		{
			public void handle(ActionEvent e)
		{
		printMessage("You have selected: Audi");
		}
		});
		// Create the MenuItem ferrari
		MenuItem ferrari = new MenuItem("Ferrari");
		// Add EventHandler to the MenuItem
		ferrari.setOnAction(new EventHandler<ActionEvent>()
		{
		public void handle(ActionEvent e)
		{
		printMessage("You have selected: Ferrari");
		}
		});
		// Create the MenuItem porsche
		MenuItem porsche = new MenuItem("Porsche");
		// Add EventHandler to the MenuItem
		porsche.setOnAction(new EventHandler<ActionEvent>()
		{
		public void handle(ActionEvent e)
		{
		printMessage("You have selected: Porsche");
		}
		});
		// Create the MenuButton
		MenuButton cars = new MenuButton("Select");
		// Add menu items to the MenuButton
		cars.getItems().addAll(ford, audi, ferrari, porsche);
		// Create the VBox
		VBox root = new VBox();
		// Add the children to the VBox
		root.getChildren().addAll(cars, messageLbl);
		// Set the Size of the VBox
		root.setMinSize(350, 250);
		/*
		 * 
		 * 
		 * * Set the padding of the VBox
		 *
		 * Set the border-style of the VBox
		 *
		 * Set the border-width of the VBox
		 *
		 * Set the border-insets of the VBox
		 *
		 * Set the border-radius of the VBox
		 *
		 * Set the border-color of the VBox
		 */
		root.setStyle("-fx-padding: 10;" +
				"-fx-border-style: solid inside;" +
				"-fx-border-width: 2;" +
				"-fx-border-insets: 5;" +
				"-fx-border-radius: 5;" +
				"-fx-border-color: blue;");
				// Create the Scene
				Scene scene = new Scene(root);
				// Add the scene to the Stage
				stage.setScene(scene);
				// Set the title of the Stage
				stage.setTitle("A MenuButton Example");
				// Display the Stage
				stage.show();
	}
	public void TestButton(Stage stage){
		 messageLbl = new Label("Press any Button to see the message");
		// Create a normal button with N as its mnemonic
		 Button newBtn = new Button("_New");
		// Add EventHandler to the Button
		newBtn.setOnAction(new EventHandler<ActionEvent>()
		{
			public void handle(ActionEvent e)
		{
		printMessage("You have pressed the new Button");
		}
		});
		
		// Create a default button with S as its mnemonic
		Button saveBtn = new Button("_Save");
		// Set this Button as the Default
		saveBtn.setDefaultButton(true);
		// Add EventHandler to the Button
		saveBtn.setOnAction(new EventHandler<ActionEvent>()
		{
		public void handle(ActionEvent e)
		{
			printMessage("You have pressed the save Button");
		}
		});
		// Create a cancel button with C as its mnemonic
		Button cancelBtn = new Button("_Cancel");
		cancelBtn.setCancelButton(true);
		// Add EventHandler to the Button
		cancelBtn.setOnAction(new EventHandler<ActionEvent>()
		{
		public void handle(ActionEvent e)
		{
		printMessage("You have pressed the cancel Button");
		}
		});
		
		// Create the HBox
		HBox buttonBox = new HBox();
		// Add the children to the HBox
		buttonBox.getChildren().addAll(newBtn, saveBtn, cancelBtn);
		// Set the vertical spacing between children to 15px
		buttonBox.setSpacing(15);
		// Create the VBox
		VBox root = new VBox();
		// Add the children to the VBox
		root.getChildren().addAll(messageLbl, buttonBox);
		// Set the vertical spacing between children to 15px
		root.setSpacing(30);
		// Set the Size of the VBox
		root.setMinSize(350, 250);
		/*
		*
		Set the padding of the VBox
		*
		Set the border-style of the VBox
		*
		Set the border-width of the VBox
		*
		Set the border-insets of the VBox
		*
		Set the border-radius of the VBox
		*
		Set the border-color of the VBox
		*/
		root.setStyle("-fx-padding: 10;" +
		"-fx-border-style: solid inside;" +
		"-fx-border-width: 2;" +
		"-fx-border-insets: 5;" +
		"-fx-border-radius: 5;" +
		"-fx-border-color: blue;");
		// Create the Scene
		Scene scene = new Scene(root);
		// Add the scene to the Stage
		stage.setScene(scene);
		// Set the title of the Stage
		stage.setTitle("A Button Example");
		// Display the Stage
		stage.show();
		
	}
	public void TestTextField(Stage stage){
		// Create the TextFields
		TextField firstNameFld = new TextField();
		TextField lastNameFld = new TextField();
		// Both fields should be wide enough to display 15 chars
		firstNameFld.setPrefColumnCount(15);
		lastNameFld.setPrefColumnCount(15);
		// Set ActionEvent handlers for both fields
		firstNameFld.setOnAction(new EventHandler<ActionEvent>()
		{
		public void handle(ActionEvent e)
		{
			printMessage("You have changed the First Name!");
		}
		});
		lastNameFld.setOnAction(new EventHandler<ActionEvent>()
		{
		public void handle(ActionEvent e)
		{
			printMessage("You have changed the Last Name !");
		}
		});
		GridPane root = new GridPane();
		// Set the horizontal spacing to 10px
		root.setHgap(10);
		// Set the vertical spacing to 5px
		root.setVgap(5);
		// Add Labels and Fields to the GridPane
		root.addRow(0, messageLbl);
		root.addRow(1, new Label("First Name:"), firstNameFld);
		root.addRow(2, new Label("Last Name:"), lastNameFld);
		// Set the Size of the GridPane
		root.setMinSize(350, 250);
		/*
		*
		Set the padding of the GridPane
		*
		Set the border-style of the GridPane
		*
		Set the border-width of the GridPane
		*
		Set the border-insets of the GridPane
		*
		Set the border-radius of the GridPane
		*
		Set the border-color of the GridPane
		*/
		root.setStyle("-fx-padding: 10;" +
		"-fx-border-style: solid inside;" +
		"-fx-border-width: 2;" +
		"-fx-border-insets: 5;" +
		"-fx-border-radius: 5;" +
		"-fx-border-color: blue;");
		// Create the Scene
		Scene scene = new Scene(root);
		// Add the scene to the Stage
		stage.setScene(scene);
		// Set the title of the Stage
		stage.setTitle("A TextField Example");
		// Display the Stage
		stage.show();
	}
	// Helper Method
	public void printMessage(String message)
	{
	// Set the Text of the Label
	messageLbl.setText(message);
	}
	public void TestLabel(Stage stage){
		// Create the Text Fields
		TextField firstNameFld = new TextField();
		TextField lastNameFld = new TextField();
		// Create the Labels
		Label firstNameLbl = new Label("_First Name:");
		Label lastNameLbl = new Label("_Last Name:");
		// Bind the Label to the according Field
		firstNameLbl.setLabelFor(firstNameFld);
		// Set mnemonic parsing to the Label
		firstNameLbl.setMnemonicParsing(true);
		// Bind the Label to the according Field
		lastNameLbl.setLabelFor(lastNameFld);
		// Set mnemonic parsing to the Label
		lastNameLbl.setMnemonicParsing(true);
		// Create the GridPane
		GridPane root = new GridPane();
		// Add the Labels and Fields to the GridPane
		root.addRow(0, firstNameLbl, firstNameFld);
		root.addRow(1, lastNameLbl, lastNameFld);
		// Set the Size of the GridPane
		root.setMinSize(350, 250);
		/*
		*
		Set the padding of the GridPane
		*
		Set the border-style of the GridPane
		*
		Set the border-width of the GridPane
		*
		Set the border-insets of the GridPane
		*
		Set the border-radius of the GridPane
		*
		Set the border-color of the GridPane
		*/
		root.setStyle("-fx-padding: 10;" +
		"-fx-border-style: solid inside;" +
		"-fx-border-width: 2;" +
		"-fx-border-insets: 5;" +
		"-fx-border-radius: 5;" +
		"-fx-border-color: blue;");
		// Create the Scene
		Scene scene = new Scene(root);
		// Add the scene to the Stage
		stage.setScene(scene);
		// Set the title of the Stage
		stage.setTitle("A Label Example");
		// Display the Stage
		stage.show();
	}
	public void TestText(Stage stage){
		Text text = new Text("Hello JavaFX");
		// Create the VBox
		VBox root = new VBox();
		// Add the Text to the VBox
		root.getChildren().add(text);
		// Set the Size of the VBox
		root.setMinSize(350, 250);
		// Create the Scene
		Scene scene = new Scene(root);
		// Set the Properties of the Stage
		stage.setX(100);
		stage.setY(200);
		stage.setMinHeight(300);
		stage.setMinWidth(400);
		// Add the scene to the Stage
		stage.setScene(scene);
		// Set the title of the Stage
		stage.setTitle("Your first JavaFX Example");
		// Display the Stage
		stage.show();
	}
	public static void main(String[] args) {
		Application.launch(args);
	}
}
