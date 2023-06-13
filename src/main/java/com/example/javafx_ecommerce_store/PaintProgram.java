package com.example.javafx_ecommerce_store;


import javafx.application.Application;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Separator;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class PaintProgram extends Application {

    private GraphicsContext graphicsContext;
    private String selectedShape;
    private double startX, startY;

    public void start(Stage primaryStage) {
        primaryStage.setTitle("Paint Program");

        // Create a canvas
        Canvas canvas = new Canvas(800, 600);
        graphicsContext = canvas.getGraphicsContext2D();
        graphicsContext.setStroke(Color.BLACK);
        graphicsContext.setLineWidth(2.0);

        // Create shape buttons
        Button rectangleButton = createShapeButton("Rectangle");
        rectangleButton.setOnAction(e -> selectShape("Rectangle"));

        Button triangleButton = createShapeButton("Triangle");
        triangleButton.setOnAction(e -> selectShape("Triangle"));

        Button circleButton = createShapeButton("Circle");
        circleButton.setOnAction(e -> selectShape("Circle"));

        // Create shape toolbar
        VBox shapeToolbar = new VBox(rectangleButton, triangleButton, circleButton);
        shapeToolbar.setSpacing(10);

        // Create separator
        Separator separator = new Separator(Orientation.HORIZONTAL);

        // Create the root pane and add the components
        BorderPane root = new BorderPane();
        root.setLeft(shapeToolbar);
        root.setCenter(canvas);
        root.setBottom(separator);

        // Set the scene
        Scene scene = new Scene(root, 800, 600);

        // Handle mouse events on the canvas
        canvas.setOnMousePressed(this::handleMousePressed);
        canvas.setOnMouseDragged(this::handleMouseDragged);
        canvas.setOnMouseReleased(this::handleMouseReleased);

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private Button createShapeButton(String shapeName) {
        Button button = new Button(shapeName);
        button.setPrefWidth(100);
        return button;
    }

    private void selectShape(String shapeName) {
        selectedShape = shapeName;
    }

    private void handleMousePressed(MouseEvent event) {
        startX = event.getX();
        startY = event.getY();
    }

    private void handleMouseDragged(MouseEvent event) {
        double endX = event.getX();
        double endY = event.getY();

        graphicsContext.clearRect(0, 0, graphicsContext.getCanvas().getWidth(),
                graphicsContext.getCanvas().getHeight());

        if (selectedShape != null) {
            if (selectedShape.equals("Rectangle")) {
                graphicsContext.strokeRect(startX, startY, endX - startX, endY - startY);
            } else if (selectedShape.equals("Triangle")) {
                double[] xPoints = {startX, endX, startX + (endX - startX) / 2};
                double[] yPoints = {endY, endY, startY};
                graphicsContext.strokePolygon(xPoints, yPoints, 3);
            } else if (selectedShape.equals("Circle")) {
                double radius = Math.abs(endX - startX) / 2;
                double centerX = startX + radius;
                double centerY = startY + radius;
                graphicsContext.strokeOval(centerX - radius, centerY - radius, radius * 2, radius * 2);
            }
        }
    }

    private void handleMouseReleased(MouseEvent event) {
        // No additional logic required for mouse release event
    }

    public static void main(String[] args) {
        launch(args);
    }
}
