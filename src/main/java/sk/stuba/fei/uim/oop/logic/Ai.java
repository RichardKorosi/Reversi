package sk.stuba.fei.uim.oop.logic;

import sk.stuba.fei.uim.oop.tile.Ownership;
import sk.stuba.fei.uim.oop.tile.Tile;

import java.util.ArrayList;

public class Ai {


    public Tile findBestTile( ArrayList<ArrayList<Tile>> tileArrayList){
        Tile bestTile = null;
        int bestValue = 0;
        for(int i=0; i < tileArrayList.size(); i++) {
            for (int j=0; j < tileArrayList.size(); j++) {
                if (tileArrayList.get(i).get(j).getOwnership().equals(Ownership.PLAYABLE)) {
                    if(tileArrayList.get(i).get(j).captureValue > bestValue){
                        bestTile = tileArrayList.get(i).get(j);
                        bestValue = bestTile.captureValue;
                    }
                }
            }
        }
        return bestTile;
    }

    public void textOutLogic(ArrayList<ArrayList<Tile>> tileArrayList){
        int pocetKamenov = 0;
        for(int i=0; i < tileArrayList.size(); i++){
            for(int j = 0; j < tileArrayList.size(); j++){
                if(tileArrayList.get(i).get(j).captureValue !=0){
                    System.out.print(tileArrayList.get(i).get(j).captureValue + " ");
                }
                else if(tileArrayList.get(i).get(j).getOwnership().equals(Ownership.PLAYER)){
                    System.out.print("K ");
                    pocetKamenov++;
                }
                else if(tileArrayList.get(i).get(j).getOwnership().equals(Ownership.COMPUTER)){
                    System.out.print("R " );
                    pocetKamenov++;
                }
                else {
                    System.out.print("0 ");
                }
            }
            System.out.println();
        }
        System.out.println("-------------"+ pocetKamenov +"------------------");
    }
}
