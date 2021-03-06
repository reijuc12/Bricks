/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import game.ball.Ball;
import game.ball.BasicBall;
import game.brick.Brick;
import java.util.LinkedList;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 *
 * @author Julian
 */
public class Bricks extends Application {

    private Group canvas;
    private Paddle player;
    private LinkedList<Ball> balls = new LinkedList<Ball>();
    private ObservableList<Brick> bricks = FXCollections.observableArrayList();
    private Rectangle topWall, bottomWall, leftWall, rightWall;
    private final Timeline brickAnimation;
    private Duration lastRun;

    public Bricks() {
        brickAnimation = new Timeline();
        brickAnimation.getKeyFrames().add(new KeyFrame(new Duration(20), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Duration thisRun = brickAnimation.getCurrentTime();
                double tslf = thisRun.toSeconds() - lastRun.toSeconds() + 0.02;
                lastRun = thisRun;

                player.update(tslf);
                for (Ball ball : balls) {
                    ball.update(tslf);
                }
                collide();
            }
        }));
        brickAnimation.setCycleCount(Timeline.INDEFINITE);
    }

    public void collide() {
        for (Ball ball : balls) {
            if (ball.getBall().intersects(leftWall.getBoundsInLocal()) && ball.getDeltaX() <= 0) {
                ball.setDeltaX(-ball.getDeltaX());
            }
            if (ball.getBall().intersects(rightWall.getBoundsInLocal()) && ball.getDeltaX() >= 0) {
                ball.setDeltaX(-ball.getDeltaX());
            }
            if (ball.getBall().intersects(topWall.getBoundsInLocal()) && ball.getDeltaY() <= 0) {
                ball.setDeltaY(-ball.getDeltaY());
            }
            if (ball.getBall().intersects(bottomWall.getBoundsInLocal()) && ball.getDeltaY() >= 0) {
                ball.setDeltaY(-ball.getDeltaY());
            }

            for (Brick b : bricks) {
                if (b.getBrick().intersects(ball.getBall().getBoundsInLocal())) {
                    if (b.getBrick().getX() - ball.getBall().getCenterX() + ball.getBall().getRadius() > 0) {

                    }

                    canvas.getChildren().remove(b.getBrick());
                    bricks.remove(b);

                }
            }
        }
    }

    private void prepareLevel(int level) {
        switch (level) {
            case 0: {
                double padding = (800 - 62 * 10) / 2.0;

                for (int i = 0; i < 10; i++) {
                    Brick b = new Brick(1, 60, 20, padding + 62 * i, 50);
                    bricks.add(b);
                    canvas.getChildren().add(b.getBrick());
                }

                break;
            }
            case 1: {
                break;
            }
            case 2: {
                break;
            }
            case 3: {
                break;
            }
            case 4: {
                break;
            }
            default: {

            }
        }
    }

    @Override
    public void start(Stage primaryStage) {
        canvas = new Group();
        player = new Paddle(70, 10, 500, 365, 429);
        topWall = new Rectangle();
        bottomWall = new Rectangle();
        leftWall = new Rectangle();
        rightWall = new Rectangle();
        Scene scene = new Scene(canvas, 800, 500);
        Rectangle pauseImage = new Rectangle(0, 0, 800, 500);

        balls.add(new BasicBall(10, 400, 250, player));
        balls.add(new BasicBall(5, 200, 350, player));

        topWall.setX(0);
        topWall.setY(-49);
        topWall.setWidth(800);
        topWall.setHeight(50);

        bottomWall.setX(0);
        bottomWall.setY(499);
        bottomWall.setWidth(800);
        bottomWall.setHeight(50);

        leftWall.setX(-49);
        leftWall.setY(0);
        leftWall.setWidth(50);
        leftWall.setHeight(500);

        rightWall.setX(799);
        rightWall.setY(0);
        rightWall.setWidth(50);
        rightWall.setHeight(500);

        prepareLevel(0);

        canvas.getChildren().add(topWall);
        canvas.getChildren().add(bottomWall);
        canvas.getChildren().add(leftWall);
        canvas.getChildren().add(rightWall);
        canvas.getChildren().add(player.getPaddle());
        for (Ball ball : balls) {
            canvas.getChildren().add(ball.getBall());
        }
        canvas.getChildren().add(pauseImage);

        pauseImage.setFill(new Color(0, 0, 0, 0.5));
        pauseImage.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                lastRun = brickAnimation.getCurrentTime();
                brickAnimation.play();
                canvas.getChildren().remove(pauseImage);
            }
        });

        VBox vp = new VBox(7);
        vp.alignmentProperty().set(Pos.CENTER);
        Text t = new Text("Bricks");
        t.setTextAlignment(TextAlignment.CENTER);
        t.setFill(Color.CORAL);
        t.setFont(Font.font("Helvetica", 128));
        Button bt = new Button("Start!");
        bt.paddingProperty().set(new Insets(10, 70, 10, 70));
        bt.alignmentProperty().set(Pos.CENTER);
        bt.fontProperty().set(Font.font("Helvetica", 32));
        bt.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                primaryStage.setScene(scene);
            }
        });
        vp.getChildren().add(t);
        vp.getChildren().add(bt);
        Scene startMenu = new Scene(vp, 800, 500);

        StackPane sp = new StackPane();
        bt = new Button("Click to Continue");
        bt.paddingProperty().set(new Insets(10, 70, 10, 70));
        bt.alignmentProperty().set(Pos.CENTER);
        bt.fontProperty().set(Font.font("Helvetica", 32));
        bt.textFillProperty().set(Color.WHITE);
        bt.setBorder(new Border(
                new BorderStroke(Color.MIDNIGHTBLUE,
                        BorderStrokeStyle.SOLID, 
                        new CornerRadii(7), 
                        new BorderWidths(3), 
                        new Insets(5))));
        bt.setBackground(new Background(new BackgroundFill(Color.LIGHTBLUE, new CornerRadii(7), new Insets(5))));
        bt.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                primaryStage.setScene(scene);
                lastRun = brickAnimation.getCurrentTime();
                brickAnimation.play();
            }
        });
        sp.backgroundProperty().set(new Background(new BackgroundFill(Color.LIGHTCYAN, CornerRadii.EMPTY, Insets.EMPTY)));
        sp.getChildren().add(bt);
        Scene pauseMenu = new Scene(sp, 800, 500);

        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                switch (event.getCode()) {
                    case A: {
                        player.setLeft(true);
                        System.out.println("A");
                        break;
                    }
                    case D: {
                        player.setRight(true);
                        System.out.println("D");
                        break;
                    }
                    case P: {
                        brickAnimation.pause();
                        primaryStage.setScene(pauseMenu);
                    }
                }
            }
        });
        scene.setOnKeyReleased(new EventHandler<KeyEvent>() {

            @Override
            public void handle(KeyEvent event) {
                switch (event.getCode()) {
                    case A: {
                        player.setLeft(false);
                        break;
                    }
                    case D: {
                        player.setRight(false);
                        break;
                    }
                }
            }
        });

        primaryStage.setTitle("Bricks");
        primaryStage.sizeToScene();
        primaryStage.setResizable(false);
        primaryStage.setScene(startMenu);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
