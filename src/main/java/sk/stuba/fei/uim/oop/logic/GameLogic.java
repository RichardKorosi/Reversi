package sk.stuba.fei.uim.oop.logic;

import sk.stuba.fei.uim.oop.tile.Ownership;
import sk.stuba.fei.uim.oop.tile.Tile;

import javax.swing.*;
import java.util.ArrayList;

public class GameLogic {

    private final Ai ai;

    public GameLogic() {
        this.ai = new Ai();
    }

    public ArrayList<ArrayList<Tile>> createTileList(int numberOfTiles){
        ArrayList<ArrayList<Tile>> newTiles = new ArrayList<>();
        int count = 0;
        int middle = numberOfTiles * (numberOfTiles/2) - numberOfTiles/2 - 1;
        for (int row = 0; row < numberOfTiles; row++) {
            newTiles.add(new ArrayList<>());
            for (int col = 0; col < numberOfTiles; col++) {
                if(count == middle || count == middle + numberOfTiles + 1){
                    newTiles.get(row).add(new Tile(Ownership.COMPUTER,col, row));
                }
                else if(count == middle + 1 || count == middle + numberOfTiles){
                    newTiles.get(row).add(new Tile(Ownership.PLAYER,col, row));
                }
                else{
                    newTiles.get(row).add(new Tile(Ownership.NONE,col, row));
                }
                count++;
            }
        }
        return newTiles;
    }

    private void clearPlayableTiles(ArrayList<ArrayList<Tile>> tileArrayList){
        for(int i=0; i < tileArrayList.size(); i++){
            for (ArrayList<Tile> tiles : tileArrayList) {
                tiles.get(i).captureValue = 0;
                if (tiles.get(i).getOwnership().equals(Ownership.PLAYABLE)) {
                    tiles.get(i).setOwnership(Ownership.NONE);
                }
            }
        }
    }

    private void  doChecks(ArrayList<ArrayList<Tile>> tileArrayList, Integer turnCount){
        for(int i=0; i < tileArrayList.size(); i++){
            for(int j = 0; j < tileArrayList.size(); j++){
                tileArrayList.get(j).get(i).lookAroundAndMakeAction(tileArrayList, turnCount);
            }
        }
    }

    private boolean nextPlayerCantMakeTurn(ArrayList<ArrayList<Tile>> tileArrayList){
        for(int i=0; i < tileArrayList.size(); i++){
            for (ArrayList<Tile> tiles : tileArrayList) {
                if (tiles.get(i).getOwnership().equals(Ownership.PLAYABLE)) {
                    return false;
                }
            }
        }
        return true;
    }



    public void startGame(ArrayList<ArrayList<Tile>> tileArrayList, Integer turnCount){
        this.doChecks(tileArrayList, turnCount);
    }


    private void getInfoAboutBoard(ArrayList<ArrayList<Tile>> tileArrayList, JLabel infoPanel, boolean gameEnded){
        int balanceOfStones = 0;
        int playerStones = 0;
        int computerStones = 0;
        for(int i=0; i < tileArrayList.size(); i++) {
            for (ArrayList<Tile> tiles : tileArrayList) {
                if (tiles.get(i).getOwnership().equals(Ownership.PLAYER)) {
                    balanceOfStones++;
                    playerStones++;
                }
                else if(tiles.get(i).getOwnership().equals(Ownership.COMPUTER)){
                    balanceOfStones--;
                    computerStones++;
                }
            }
            if(gameEnded){
                if(balanceOfStones > 0){
                    infoPanel.setText("Winner is player with "+playerStones+" stones, computer has "+computerStones+" stones");
                }
                else if(balanceOfStones < 0){
                    infoPanel.setText("Winner is computer with "+computerStones+" stones, player has "+playerStones+" stones");
                }
                else{
                    infoPanel.setText("It is draw! Each participant has" +computerStones + "stones");
                }
            }
            else{
                infoPanel.setText("Players turn and balance is: B:"+playerStones+" W:"+computerStones);
            }
        }

    }

    private void playTile(Tile selectedTile,  ArrayList<ArrayList<Tile>> tileArrayList, Integer turnCount, JLabel infoPanel){
        if(turnCount % 2 ==0){
            selectedTile.setOwnership(Ownership.ACTIVATED_PLAYER_STONE);
            selectedTile.lookAroundAndMakeAction(tileArrayList, turnCount);
            selectedTile.setOwnership(Ownership.PLAYER);
        }
        else{
            selectedTile.setOwnership(Ownership.ACTIVATED_COMPUTER_STONE);
            selectedTile.lookAroundAndMakeAction(tileArrayList, turnCount);
            selectedTile.setOwnership(Ownership.COMPUTER);
        }
        getInfoAboutBoard(tileArrayList, infoPanel, false);
    }


    public void makeTurn(Tile selectedTile, ArrayList<ArrayList<Tile>> tileArrayList, Integer turnCount, JLabel infoPanel){
        //My turn
        if(turnCount % 2 == 0){
            if (selectedTile.getOwnership().equals(Ownership.PLAYABLE_HOVERED)) {
                playTile(selectedTile, tileArrayList, turnCount, infoPanel);
                turnCount++;
                clearPlayableTiles(tileArrayList);
                doChecks(tileArrayList, turnCount);
                if(nextPlayerCantMakeTurn(tileArrayList)){
                    turnCount++;
                    clearPlayableTiles(tileArrayList);
                    doChecks(tileArrayList, turnCount);
                    if(nextPlayerCantMakeTurn(tileArrayList)){
                        System.out.println("Noone can make turn, it's GG!");
                        this.getInfoAboutBoard(tileArrayList, infoPanel, true);
                        return;
                    }
                    System.out.println("PC cant play any rock and loses the turn!");
                }
            }
        }
        //AI turn if can make a turn after mine
        if(turnCount % 2 != 0) {
            ai.textOutLogic(tileArrayList);
            Tile bestTile;
            bestTile = ai.findBestTile(tileArrayList);
            playTile(bestTile, tileArrayList, turnCount, infoPanel);
            turnCount++;
            clearPlayableTiles(tileArrayList);
            doChecks(tileArrayList, turnCount);
            do{
                if(nextPlayerCantMakeTurn(tileArrayList)){
                    turnCount++;
                    clearPlayableTiles(tileArrayList);
                    doChecks(tileArrayList, turnCount);
                    if(nextPlayerCantMakeTurn(tileArrayList)){
                        System.out.println("Noone can make turn, it's GG!");
                        this.getInfoAboutBoard(tileArrayList, infoPanel, true);
                        return;
                    }
                    System.out.println("PC cant play any rock and loses the turn!");
                    bestTile = ai.findBestTile(tileArrayList);
                    playTile(bestTile, tileArrayList, turnCount, infoPanel);
                    turnCount++;
                    clearPlayableTiles(tileArrayList);
                    doChecks(tileArrayList, turnCount);
                }
                else{
                    return;
                }
            }while(true);
        }
    }
}

