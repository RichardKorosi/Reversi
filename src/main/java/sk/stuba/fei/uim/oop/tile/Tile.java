package sk.stuba.fei.uim.oop.tile;


import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Tile extends JPanel{
    private Ownership ownership;
    private final int colTile;
    private final int rowTile;
    public int captureValue;



    public Tile(Ownership ownership, int colTile, int rowTile) {
        this.ownership = ownership;
        this.setBackground(ownership.myColor);
        this.setSize(10, 10);
        this.colTile = colTile;
        this.rowTile = rowTile;
        this.captureValue = 0;
    }

    public Ownership getOwnership() {
        return ownership;
    }

    public void setOwnership(Ownership ownership) {
        this.ownership = ownership;
        this.setBackground(ownership.myColor);
    }

    private boolean tileExists(int rowTile, int colTile, int length) {
        return colTile >= 0 && rowTile >= 0 && colTile < length && rowTile < length;
    }

    public void lookAroundAndMakeAction(ArrayList<ArrayList<Tile>> tileArrayList, int turn) {
        for (int rowDir = -1; rowDir <= 1; rowDir++) {
            for (int colDir = -1; colDir <= 1; colDir++) {
                if (rowDir == 0 && colDir == 0) {
                    continue;
                }
                if (tileExists(this.rowTile + rowDir, this.colTile + colDir, tileArrayList.size())) {
                    if(turn % 2 == 0){
                        lookAtDirection(tileArrayList, rowDir, colDir, turn, Ownership.COMPUTER, Ownership.PLAYER, Ownership.ACTIVATED_PLAYER_STONE);
                    }
                    else{
                        lookAtDirection(tileArrayList, rowDir, colDir, turn, Ownership.PLAYER, Ownership.COMPUTER, Ownership.ACTIVATED_COMPUTER_STONE);
                    }
                }
            }
        }
    }

    private void lookAtDirection(ArrayList<ArrayList<Tile>> tileArrayList, int rowDir, int colDir, int turn, Ownership opponentsStone, Ownership observerStone, Ownership activeStone){
        if (tileArrayList.get(this.rowTile + rowDir).get(this.colTile + colDir).getOwnership().equals(opponentsStone)) {
            if (this.ownership.equals(observerStone)) {
                findPlayableTiles(tileArrayList, this.rowTile, this.colTile, rowDir, colDir, turn);
            }
            else if (this.ownership.equals(activeStone)){
                captureRocks(tileArrayList, rowDir, colDir);
            }
        }
    }

    private void findPlayableTiles(ArrayList<ArrayList<Tile>> tileArrayList, int rowTile, int colTile, int rowDir, int colDir, int turn) {
        if(turn % 2 == 0){
            lookBehindOpponent(tileArrayList, rowTile, colTile, rowDir, colDir, Ownership.COMPUTER);
        }
        else{
            lookBehindOpponent(tileArrayList, rowTile, colTile, rowDir, colDir, Ownership.PLAYER);
        }
    }


    private void lookBehindOpponent(ArrayList<ArrayList<Tile>> tileArrayList, int rowTile, int colTile, int rowDir, int colDir, Ownership opponentsStone){
        int length = tileArrayList.size();
        int capValue = 0;
        do {
            rowTile += rowDir;
            colTile += colDir;
            if (!tileExists(rowTile, colTile, length)) {
                return;
            }
            if (tileArrayList.get(rowTile).get(colTile).getOwnership().equals(Ownership.NONE) ||
                    tileArrayList.get(rowTile).get(colTile).getOwnership().equals(Ownership.PLAYABLE)) {
                tileArrayList.get(rowTile).get(colTile).setOwnership(Ownership.PLAYABLE);
                tileArrayList.get(rowTile).get(colTile).captureValue += capValue;
                break;
            }
            capValue++;
        } while (tileArrayList.get(rowTile).get(colTile).getOwnership().equals(opponentsStone));
    }


    private void captureRocks(ArrayList<ArrayList<Tile>> tileArrayList, int rowDir, int colDir) {
        int rowTile = this.rowTile;
        int colTile = this.colTile;
        if(tileArrayList.get(rowTile).get(colTile).getOwnership().equals(Ownership.ACTIVATED_PLAYER_STONE)){
            startCapturing(tileArrayList, rowDir, colDir, Ownership.COMPUTER, Ownership.PLAYER, Ownership.ACTIVATED_PLAYER_STONE);
        }
        else if(tileArrayList.get(rowTile).get(colTile).getOwnership().equals(Ownership.ACTIVATED_COMPUTER_STONE)){
            startCapturing(tileArrayList, rowDir, colDir, Ownership.PLAYER, Ownership.COMPUTER, Ownership.ACTIVATED_COMPUTER_STONE);
        }
    }


    private void startCapturing(ArrayList<ArrayList<Tile>> tileArrayList, int rowDir, int colDir, Ownership toCapture, Ownership capturer, Ownership activatedCapturer){
        int length = tileArrayList.size();
        int rowTile = this.rowTile + rowDir;
        int colTile = this.colTile + colDir;
        while(tileArrayList.get(rowTile).get(colTile).getOwnership().equals(toCapture)){
            rowTile += rowDir;
            colTile += colDir;
            if(!tileExists(rowTile, colTile, length)){
                return;
            }
        }
        if (tileArrayList.get(rowTile).get(colTile).getOwnership().equals(capturer)){
            while (!tileArrayList.get(rowTile).get(colTile).getOwnership().equals(activatedCapturer)){
                tileArrayList.get(rowTile).get(colTile).setOwnership(capturer);
                rowTile -= rowDir;
                colTile -= colDir;
            }
        }
    }


    private void paintRock(Graphics g) {
        int size = this.getWidth();
        g.setColor(Color.PINK);
        if (this.ownership.equals(Ownership.PLAYABLE)) {
            g.fillRect(0, 0, size, size);
            g.setColor(this.ownership.myColor);
            g.fillOval((int) (size/3.15), (size/4), (int) (size/2.5), (int) (size/2.5));
        }
        else if (this.ownership.equals(Ownership.PLAYABLE_HOVERED)) {
            g.setColor(Ownership.PLAYABLE_HOVERED.myColor);
            g.fillRect(0, 0, size, size);
        } else {
            g.fillRect(0, 0, size, size);
            g.setColor(this.ownership.myColor);
            g.fillOval(size/15, 0, (int) (size/1.15), (int) (size/1.15));
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        this.paintRock(g);
    }
}