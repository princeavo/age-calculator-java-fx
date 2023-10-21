package com;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class AgeCalculator extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {

		VBox top = new VBox(0);

		Label info = new Label("Age Calculator");
		info.setFont(Font.font("Arial", 25));
		info.setMaxWidth(Double.MAX_VALUE);
		info.setBackground(new Background(new BackgroundFill(Color.web("#458729"), null, null)));
		info.setAlignment(Pos.CENTER);
		info.setPadding(new Insets(5));

		MenuBar bar = new MenuBar();

		Menu menu = new Menu("Menu");

		MenuItem propos = new MenuItem("A propos de l'auteur");

		MenuItem close = new MenuItem("Exit");
		close.setOnAction(e -> primaryStage.close());

		menu.getItems().addAll(propos, close);

		propos.setOnAction(e -> {
			Label label = new Label("Nous sommes étudiant à l'Université d'Abomey-Calavi");
			Stage stage = new Stage();
			stage.setTitle("A propos de nous ");
			VBox pane = new VBox(40);
			Button ok = new Button("Ok");
			pane.setAlignment(Pos.CENTER);
			ok.setOnAction(event -> {
				stage.close();
			});
			pane.getChildren().addAll(label, ok);
			pane.setPadding(new Insets(40));
			stage.setScene(new Scene(pane));
			stage.initModality(Modality.APPLICATION_MODAL);
			stage.setResizable(false);
			stage.showAndWait();
		});

		bar.getMenus().addAll(menu);

		GridPane grid = new GridPane();

		DatePicker date1 = new DatePicker();
		date1.setTooltip(new Tooltip("Veuillez rensigner votre année de naissance"));
		date1.setPrefWidth(250);
		date1.setPrefHeight(40);
		LocalDate local = LocalDate.now();
		LocalDate.now(ZoneId.of("GMT+1"));
		DatePicker date2 = new DatePicker();
		date2.setPrefWidth(250);
		date2.setPrefHeight(40);
		date2.setValue(local);
		date2.setEditable(false);
		date1.setEditable(false);
		date2.setDisable(true);
		date2.setStyle("-fx-background-color:#128915;");

		HBox box = new HBox();
		box.setSpacing(30);
		box.setAlignment(Pos.CENTER);

		Label l1 = new Label("Your age is:");
		l1.setFont(Font.font(20));
		Label a = new Label("Years ");
		a.setPrefSize(80, 80);
		a.setAlignment(Pos.CENTER);
		Label ageValue = new Label("00");
		Label m = new Label("Month ");
		m.setPrefSize(80, 80);
		m.setAlignment(Pos.CENTER);
		Label moisValue = new Label("00");
		Label j = new Label("Day");
		j.setPrefSize(80, 80);
		j.setAlignment(Pos.CENTER);
		Label jourValue = new Label("00");

		ageValue.setFont(Font.font(STYLESHEET_CASPIAN, FontWeight.BOLD, 15));
		a.setPadding(new Insets(10));
		a.setFont(Font.font(17));
		a.setBackground(new Background(new BackgroundFill(Color.web("#127346"), null, null)));

		moisValue.setFont(Font.font(STYLESHEET_CASPIAN, FontWeight.BOLD, 15));
		m.setPadding(new Insets(10));
		m.setFont(Font.font(17));
		m.setBackground(new Background(new BackgroundFill(Color.web("#127346"), null, null)));

		jourValue.setFont(Font.font(STYLESHEET_CASPIAN, FontWeight.BOLD, 15));
		j.setPadding(new Insets(10));
		j.setFont(Font.font(17));
		j.setBackground(new Background(new BackgroundFill(Color.web("#127346"), null, null)));

		a.setGraphic(ageValue);
		m.setGraphic(moisValue);
		j.setGraphic(jourValue);

		a.setContentDisplay(ContentDisplay.BOTTOM);
		j.setContentDisplay(ContentDisplay.BOTTOM);
		m.setContentDisplay(ContentDisplay.BOTTOM);
		box.getChildren().addAll(l1, a, m, j);

		grid.add(new Label("Date of Birth"), 0, 0);
		grid.add(new Label("Current date"), 0, 1);
		grid.add(date2, 1, 1);
		grid.add(date1, 1, 0);
		grid.setAlignment(Pos.CENTER);
		grid.setHgap(40);
		grid.setVgap(35);

		Button calButton = new Button("Calculate");
		calButton.setBackground(new Background(new BackgroundFill(Color.web("yellow"), null, null)));
		grid.add(calButton, 1, 2);
		calButton.setPrefSize(100, 30);
		calButton.setPadding(new Insets(10));

		calButton.disableProperty().bind(date1.valueProperty().isNull());
		
		date1.valueProperty().addListener((v,o,n)->{
			LocalDate local1 = date1.getValue();
			LocalDate local2 = date2.getValue();
			Period period = Period.between(local1, local2);
			ageValue.setText(period.getYears()+"");
			moisValue.setText(period.getMonths()+"");
			jourValue.setText(period.getDays()+"");
		});

		calButton.setOnAction(e -> {
			LocalDate local1 = date1.getValue();
			LocalDate local2 = date2.getValue();
			Period period = Period.between(local1, local2);
			ageValue.setText(period.getYears()+"");
			moisValue.setText(period.getMonths()+"");
			jourValue.setText(period.getDays()+"");
		});

		VBox vbox = new VBox(20);
		vbox.getChildren().addAll(grid, box);
		vbox.setAlignment(Pos.CENTER);

		BorderPane root = new BorderPane();
		top.getChildren().addAll(bar, info);
		root.setTop(top);
		root.setCenter(vbox);
		BorderPane.setAlignment(grid, Pos.CENTER);

		Scene scene = new Scene(root);
		root.setBackground(new Background(new BackgroundImage(
				new Image(getClass().getResource("image5.jpg").toString()), null, null, null, null)));

		primaryStage.setScene(scene);
		primaryStage.setMinHeight(450);
		primaryStage.setMinWidth(550);
		primaryStage.show();
		primaryStage.getIcons().add(new Image(getClass().getResource("image1.jpeg").toString()));
	}
}
