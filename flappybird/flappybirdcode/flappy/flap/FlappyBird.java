package flap;
import javafx.animation.AnimationTimer;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;
import java.util.Random;

public class FlappyBird extends Pane {
    private static final int BOARD_WIDTH = 360;
    private static final int BOARD_HEIGHT = 640;
    private static final int BIRD_WIDTH = 34;
    private static final int BIRD_HEIGHT = 24;
    private static final int PIPE_WIDTH = 64;
    private static final int PIPE_HEIGHT = 512;
    private static final int GRAVITY = 1;

    private ImageView bird;
    private ArrayList<Rectangle> pipes;
    private Random random;
    private boolean gameOver;
    private double score;

    public FlappyBird() {
        setPrefSize(BOARD_WIDTH, BOARD_HEIGHT);
        gameOver = false;
        score = 0;
        random = new Random();
        bird = new ImageView(new Image(getClass().getResourceAsStream("./flappybird.png")));
        bird.setFitWidth(BIRD_WIDTH);
        bird.setFitHeight(BIRD_HEIGHT);
        pipes = new ArrayList<>();
        getChildren().add(bird);

        // Start game loop
        AnimationTimer timer = new AnimationTimer() {
            long lastTime = 0;

            @Override
            public void handle(long now) {
                if (lastTime == 0) {
                    lastTime = now;
                    return;
                }
                double elapsedTime = (now - lastTime) / 1e9; // convert nanoseconds to seconds
                lastTime = now;
                if (!gameOver) {
                    move(elapsedTime);
                    generatePipes();
                }
            }
        };
        timer.start();
    }

    private void move(double elapsedTime) {
        // Bird movement
        double velocityY = bird.getTranslateY() + GRAVITY * elapsedTime;
        bird.setTranslateY(velocityY);

        // Collision detection
        if (bird.getTranslateY() > BOARD_HEIGHT || bird.getTranslateY() < 0) {
            gameOver = true;
        }

        // Pipes movement and collision detection
        for (Rectangle pipe : pipes) {
            pipe.setTranslateX(pipe.getTranslateX() - elapsedTime * 100); // Adjust the speed of pipes here
            if (pipe.getBoundsInParent().intersects(bird.getBoundsInParent())) {
                gameOver = true;
            }
            if (pipe.getTranslateX() < -PIPE_WIDTH) {
                pipes.remove(pipe);
            }
        }
    }

    private void generatePipes() {
        if (random.nextDouble() < 0.02) { // Adjust pipe generation frequency here
            double pipeY = random.nextDouble() * (BOARD_HEIGHT - 200); // Adjust pipe position here
            Rectangle topPipe = new Rectangle(BOARD_WIDTH, pipeY, PIPE_WIDTH, PIPE_HEIGHT);
            topPipe.setFill(Color.GREEN);
            Rectangle bottomPipe = new Rectangle(BOARD_WIDTH, pipeY + 200, PIPE_WIDTH, PIPE_HEIGHT);
            bottomPipe.setFill(Color.GREEN);
            getChildren().addAll(topPipe, bottomPipe);
            pipes.add(topPipe);
            pipes.add(bottomPipe);
        }
    }
}
