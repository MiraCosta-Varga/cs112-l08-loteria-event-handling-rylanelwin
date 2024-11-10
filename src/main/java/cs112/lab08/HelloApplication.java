package cs112.lab08;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.text.Font;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.text.TextAlignment;

import java.io.IOException;

import java.util.Random;

public class HelloApplication extends Application {

    //CONSTANTS

    //array of LoteriaCards to use for game:
    private static final LoteriaCard[] LOTERIA_CARDS = {
            new LoteriaCard("Las matematicas", "1.png", 1),
            new LoteriaCard("Las ciencias", "2.png", 2),
            new LoteriaCard("La Tecnología", "8.png", 8),
            new LoteriaCard("La ingeniería", "9.png", 9),
    };

    //tracker for how many cards have been looked at
    int cardNumber = -1;

    @Override
    public void start(Stage stage) throws IOException {

        //new randomized array of cards
        LoteriaCard[] randomLoteriaCards = LOTERIA_CARDS;

        Random ran = new Random();

        for (int i = randomLoteriaCards.length - 1; i > 0; i--){
            int j = ran.nextInt(i+1);
            LoteriaCard temp = randomLoteriaCards[i];
            randomLoteriaCards[i] = randomLoteriaCards[j];
            randomLoteriaCards[j] = temp;
        }

        //COMPONENTS
        Font titleFont = new Font("Arial Bold", 18);

        Font messageFont = new Font("Arial Bold", 13);

        Font buttonFont = new Font("Arial Bold", 15);

        LoteriaCard defaultCard = new LoteriaCard();

        Label titleLabel = new Label("Welcome to EChALE STEM Loteria!");
        titleLabel.setFont(titleFont);

        ImageView cardImageView = new ImageView(defaultCard.getImage());
        cardImageView.setFitWidth(280);
        cardImageView.setPreserveRatio(true);

        Label messageLabel = new Label("Click the button below to randomly draw a card. " +
                "The progress bar will indicate how far we're into the game.");
        messageLabel.setFont(messageFont);
        messageLabel.setTextAlignment(TextAlignment.CENTER);
        messageLabel.setWrapText(true);

        Button drawCardButton = new Button("Draw Random Card");
        drawCardButton.setFont(buttonFont);
        drawCardButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                cardNumber++;
                if (cardNumber < 4){
                    cardImageView.setImage(randomLoteriaCards[cardNumber].getImage());
                    messageLabel.setText(randomLoteriaCards[cardNumber].getCardName());
                } else {
                    cardImageView.setImage(defaultCard.getImage());
                    messageLabel.setText("GAME OVER. No more cards! Exit and run program again to reset.");
                    drawCardButton.setDisable(true);
                }
            }
        });

        //VBOX
        VBox layout = new VBox(8);
        layout.setAlignment(Pos.TOP_CENTER);
        layout.getChildren().add(titleLabel);
        layout.getChildren().add(cardImageView);
        layout.getChildren().add(messageLabel);
        layout.getChildren().add(drawCardButton);

        //SCENE
        Scene scene = new Scene(layout, 350, 500);
        stage.setScene(scene);
        stage.setTitle("EChALE STEM Loteria");
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}