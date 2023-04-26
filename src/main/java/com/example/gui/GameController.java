package com.example.gui;

import com.example.gui.modelo.Board;
import com.example.gui.modelo.EnumColour;
import com.example.gui.modelo.Position;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Popup;
import javafx.stage.PopupWindow;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;
import java.util.ArrayList;

public class GameController {


    @FXML
    Pane boardPane;
    @FXML
    Pane scenePane;

    public ArrayList<ImageView> pieceList = new ArrayList<ImageView>(32);
    int promotedPawns[]=new int[32];
    boolean stop;

    @FXML
    Label promoteLabel1;

    @FXML
    Label winnerLabel;

    @FXML
    ImageView rook1;
    @FXML
    ImageView rook2;
    @FXML
    ImageView bishop1;
    @FXML
    ImageView bishop2;
    @FXML
    ImageView queen1;
    @FXML
    ImageView queen2;
    @FXML
    ImageView knight1;
    @FXML
    ImageView knight2;

    @FXML
    ImageView piece;
    @FXML
    ImageView promotedPiece;
    @FXML
    ImageView wRook1;
    @FXML
    ImageView wRook2;
    @FXML
    ImageView bRook1;
    @FXML
    ImageView bRook2;
    @FXML
    ImageView wBishop1;
    @FXML
    ImageView wBishop2;
    @FXML
    ImageView bBishop1;
    @FXML
    ImageView bBishop2;
    @FXML
    ImageView wKnight1;
    @FXML
    ImageView wKnight2;
    @FXML
    ImageView bKnight1;
    @FXML
    ImageView bKnight2;
    @FXML
    ImageView wQueen;
    @FXML
    ImageView wKing;
    @FXML
    ImageView bQueen;
    @FXML
    ImageView bKing;

    @FXML
    ImageView wPawn1;
    @FXML
    ImageView wPawn2;
    @FXML
    ImageView wPawn3;
    @FXML
    ImageView wPawn4;
    @FXML
    ImageView wPawn5;
    @FXML
    ImageView wPawn6;
    @FXML
    ImageView wPawn7;
    @FXML
    ImageView wPawn8;
    @FXML
    ImageView bPawn1;
    @FXML
    ImageView bPawn2;
    @FXML
    ImageView bPawn3;
    @FXML
    ImageView bPawn4;
    @FXML
    ImageView bPawn5;
    @FXML
    ImageView bPawn6;
    @FXML
    ImageView bPawn7;
    @FXML
    ImageView bPawn8;


    public Parent root;
    private int currSqx;
    private int currSqy;
    private double startX;
    private double startY;
    private boolean movingPiece = false;

    Position promoteTo;
    Position promoteFrom;

    private Stage stage;
    private Scene scene;

    static String player1;

    public EnumColour turn = EnumColour.White;

    Board board = new Board();

    Stage gameStage= new Stage();


    public void initialize() {
        pieceList.add(bPawn1);
        pieceList.add(bPawn2);
        pieceList.add(bPawn3);
        pieceList.add(bPawn4);
        pieceList.add(bPawn5);
        pieceList.add(bPawn6);
        pieceList.add(bPawn7);
        pieceList.add(bPawn8);
        pieceList.add(bBishop1);
        pieceList.add(bBishop2);
        pieceList.add(bKnight1);
        pieceList.add(bKnight2);
        pieceList.add(bRook1);
        pieceList.add(bRook2);
        pieceList.add(bKing);
        pieceList.add(bQueen);
        pieceList.add(wPawn1);
        pieceList.add(wPawn2);
        pieceList.add(wPawn3);
        pieceList.add(wPawn4);
        pieceList.add(wPawn5);
        pieceList.add(wPawn6);
        pieceList.add(wPawn7);
        pieceList.add(wPawn8);
        pieceList.add(wBishop1);
        pieceList.add(wBishop2);
        pieceList.add(wKnight1);
        pieceList.add(wKnight2);
        pieceList.add(wRook1);
        pieceList.add(wRook2);
        pieceList.add(wKing);
        pieceList.add(wQueen);

        for(int i=0; i<32; i++){
            promotedPawns[i]=0;
        }
    }

    @FXML
    public void startMovingPiece(MouseEvent evt) {
        if(!stop) {
            Point2D mousePoint_start = new Point2D(evt.getSceneX(), evt.getSceneY());
            if (movingPiece == false) {
                startX = mousePoint_start.getX();
                startY = mousePoint_start.getY();
            }


            pieceIdentifier(evt);
            piece.setOpacity(0.4d);

            movingPiece = true;
        }
    }

    @FXML
    public void movePiece(MouseEvent evt) {

        if(!stop) {
            Point2D mousePoint = new Point2D(evt.getX(), evt.getY());
            Point2D mousePoint_s = new Point2D(evt.getSceneX(), evt.getSceneY());

            if (!inBoard(mousePoint_s)) {
                return;  // don't relocate() b/c will resize Pane
            }
            Point2D mousePoint_p = piece.localToParent(mousePoint);
            piece.relocate(mousePoint_p.getX() - 30, mousePoint_p.getY() - 30);
        }
    }

    private boolean inBoard(Point2D pt) {
        Point2D panePt = boardPane.sceneToLocal(pt);
        return panePt.getX() >= 0.0d
                && panePt.getY() >= 0.0d
                && panePt.getX() <= boardPane.getWidth()
                && panePt.getY() <= boardPane.getHeight();
    }

    @FXML
    public void finishMovingPiece(MouseEvent evt) throws IOException {

        if(!stop) {
            Point2D mousePoint = new Point2D(evt.getSceneX(), evt.getSceneY());
            piece.setOpacity(1);
            int X1 = (int) (startX - 160) / 60;
            int Y1 = (int) (startY - 60) / 60;
            int X2 = (int) (mousePoint.getX() - 160) / 60;
            int Y2 = (int) (mousePoint.getY() - 60) / 60;

            if (X2 < 0 || Y2 < 0 || X2 > 7 || Y2 > 7) {
                piece.setLayoutY(Y1 * 60);
                piece.setLayoutX(X1 * 60);
                movingPiece = false;
                return;
            }


            Position initialPosition = new Position(Y1, X1);
            Position finalPosition = new Position(Y2, X2);


            if (board.getPiece(initialPosition).getColour().equals(turn)) {
                if (detectPromote(Y1, Y2)) {
                    promoteTo = finalPosition;
                    promoteFrom = initialPosition;
                    currentSquare(mousePoint.getX(), mousePoint.getY());
                    deletePiece(currSqx * 60, currSqy * 60);
                    piece.relocate(currSqx * 60, currSqy * 60);
                    updateTurn();

                } else if (board.movePiece(initialPosition, finalPosition)) {
                    currentSquare(mousePoint.getX(), mousePoint.getY());
                    deletePiece(currSqx * 60, currSqy * 60);
                    piece.relocate(currSqx * 60, currSqy * 60);
                    updateTurn();
                } else if (board.castle(initialPosition, finalPosition)) {
                    System.out.println("Castle");
                    king_castle(X2, Y2);
                    updateTurn();
                } else {
                    piece.relocate(X1 * 60, Y1 * 60);
                }
            }
            else {
                System.out.println("João");
                piece.relocate(X1 * 60, Y1 * 60);
            }
            isItMate();
            movingPiece = false;
        }
    }

    public void currentSquare(double X, double Y) {


        currSqx = (int) (X - 160) / 60;
        currSqy = (int) (Y - 60) / 60;

        if (X > 640 || Y > 540 || X < 160 || Y < 60) {
            currSqx = (int) ((startX - 160) / 60);
            currSqy = (int) ((startY - 60) / 60);
        }

    }

    public void pieceIdentifier(MouseEvent evt) {
        piece = (ImageView) evt.getSource();
    }

    public void quit(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Menu2.fxml"));
        root = loader.load();

        Menu2Controller menu2Controller = loader.getController();
        menu2Controller.printName(menu2Controller.player);
        stage =(Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);

        String css = this.getClass().getResource("button.css").toExternalForm();
        scene.getStylesheets().add(css);
        stage.setScene(scene);
        stage.setTitle("JavaFX");
        stage.show();
    }

    public void deletePiece(int x, int y) {


        if (turn.equals(EnumColour.White)) {
            for (int i = 0; i < 16; i++) {
                ImageView piece2 = pieceList.get(i);
                if (x == piece2.getLayoutX() && !piece2.equals(piece)) {
                    if (y == piece2.getLayoutY()) {
                        System.out.println("rip");
                        piece2.setLayoutX(-10);
                        piece2.setLayoutY(-10);
                        piece2.setImage(null);
                        break;
                    }

                }
            }
        } else if (turn.equals(EnumColour.Black)) {
            for (int i = 16; i < 32; i++) {
                ImageView piece2 = pieceList.get(i);
                if (x == piece2.getLayoutX() && !piece2.equals(piece)) {
                    if (y == piece2.getLayoutY()) {
                        System.out.println("rip");
                        piece2.setLayoutX(-10);
                        piece2.setLayoutY(-10);
                        piece2.setImage(null);
                        break;
                    }

                }
            }
        }
    }

    public void updateTurn() {
        if (turn.equals(EnumColour.White)) {
            turn = EnumColour.Black;
        } else if (turn.equals(EnumColour.Black)) {
            turn = EnumColour.White;
        }
    }

    public void king_castle(double X, double Y) {
        if (turn.equals(EnumColour.Black)) {
            if (X == 6) {
                piece.relocate(X * 60, Y * 60);
                bRook1.relocate(5 * 60, 0);

            } else if (X == 2) {
                bRook2.relocate(3 * 60, 0);
                piece.relocate(X * 60, Y * 60);

            }
        } else if (turn.equals(EnumColour.White)) {
            if (X == 6) {
                piece.relocate(X * 60, Y * 60);
                wRook2.relocate(5 * 60, 7 * 60);

            } else if (X == 2) {
                piece.relocate(X * 60, Y * 60);
                wRook1.relocate(3 * 60, 7 * 60);

            }

        }
    }

    public boolean detectPromote(double Y, double Y2) {
        if (turn.equals(EnumColour.White)) {
            if (Y == 1 && Y2==0) {
                for (int i = 16; i < 24; i++) {
                    System.out.println("looking");
                    if (piece == pieceList.get(i) && promotedPawns[i]==0) {
                        //promoteLabel1.setText("PROMOTE PIECE:");

                        knight2.setVisible(true);
                        rook2.setVisible(true);
                        queen2.setVisible(true);
                        bishop2.setVisible(true);

                        stop=true;

                        return true;
                    }
                }
            }

        }
        else if (turn.equals(EnumColour.Black)) {
            if (Y == 6 && Y2==7) {
                for (int i = 0; i < 8; i++) {
                    if (piece == pieceList.get(i) && promotedPawns[i] == 0) {
                        //promoteLabel.setText("PROMOTE PIECE:");

                        knight1.setVisible(true);
                        rook1.setVisible(true);
                        queen1.setVisible(true);
                        bishop1.setVisible(true);

                        stop=true;

                        return true;
                    }
                }
            }
        }
        return false;
    }
    public void promotePiece (MouseEvent evt){

        int id=0;

        for(int i=0; i<32;i++){
            if(piece==pieceList.get(i)){
                promotedPawns[i]=i;
            }
        }

        promotedPiece = (ImageView) evt.getSource();
        piece.setImage(promotedPiece.getImage());

        if(promotedPiece==queen1 || promotedPiece==queen2){
            id=1;
        }
        else if(promotedPiece==rook1 || promotedPiece==rook2){
            id=2;
        }
        else if(promotedPiece==bishop1 || promotedPiece==bishop2){
            id=3;
        }
        else if(promotedPiece==knight1 || promotedPiece==knight2){
            id=4;
        }

        board.pawnPromotion(promoteFrom, promoteTo, id);

        knight1.setVisible(false);
        rook1.setVisible(false);
        queen1.setVisible(false);
        bishop1.setVisible(false);

        knight2.setVisible(false);
        rook2.setVisible(false);
        queen2.setVisible(false);
        bishop2.setVisible(false);

        stop=false;

    }
    public void isItMate() throws IOException {
        board.checkMate(board);


        if (board.mateW == true) {
            declareWinner("PopUpBlack.fxml");



        } else if (board.mateB == true) {
            declareWinner("PopUpWhite.fxml");

        }
    }
    public void declareWinner(String winner) throws IOException {
        gameStage= (Stage) boardPane.getScene().getWindow();
        gameStage.close();
        Parent root = FXMLLoader.load(getClass().getResource(winner));

        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setTitle("Game over");
        stage.setResizable(false);
        stage.setScene(scene);

        stage.show();

    }

}
