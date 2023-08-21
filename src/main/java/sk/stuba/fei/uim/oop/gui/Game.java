package sk.stuba.fei.uim.oop.gui;

import sk.stuba.fei.uim.oop.logic.GameLogic;
import sk.stuba.fei.uim.oop.tile.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class Game extends UniversalAdapter{

    private ArrayList<ArrayList<Tile>> tileArrayList;
    public Integer turn;
    private Tile lastVisitedPlayableTile;
    private final JPanel playground;
    private final JLabel infoPanel;
    private final GameLogic gameLogic;


    public Game(int numberOfTiles) {
        this.gameLogic = new GameLogic();
        this.setLayout(new BorderLayout());
        this.playground = new JPanel();
        this.playground.setBackground(Color.DARK_GRAY);
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
        this.infoPanel = new JLabel("Players turn and balance is: B:2 W:2");
        this.restartPlayground(numberOfTiles);
        this.infoPanel.setOpaque(true);
        this.infoPanel.setBackground(Color.DARK_GRAY);
        this.infoPanel.setForeground(Color.WHITE);
        this.add(infoPanel, BorderLayout.PAGE_END);
        this.add(this.playground, BorderLayout.CENTER);
    }

    public void restartPlayground(int numberOfTiles){
        this.playground.removeAll();
        this.playground.revalidate();
        this.playground.repaint();
        this.playground.setLayout(new GridLayout(numberOfTiles, numberOfTiles,1,1));
        this.tileArrayList = this.gameLogic.createTileList(numberOfTiles);
        this.printPlayground();
        this.turn = 0;
        this.infoPanel.setText("Players turn and balance is: B:2 W:2");
        this.gameLogic.startGame(this.tileArrayList, this.turn);
    }



    public void printPlayground(){
        for(int i=0; i < this.tileArrayList.size(); i++){
            for(int j = 0; j < this.tileArrayList.size(); j++){
                this.playground.add(this.tileArrayList.get(i).get(j));
            }
        }
    }



    @Override
    public void mouseClicked(MouseEvent e) {
        if (this.playground.getComponentAt(e.getPoint()) instanceof Tile) {
                Tile selectedTile = (Tile) this.playground.getComponentAt(e.getPoint());
                this.lastVisitedPlayableTile = null;
                if (this.turn % 2 == 0) {
                    this.gameLogic.makeTurn(selectedTile, this.tileArrayList, this.turn, this.infoPanel);
                }
            }
        }

    @Override
    public void mouseMoved(MouseEvent e) {
        if (this.playground.getComponentAt(e.getPoint()) instanceof Tile) {
            Tile currentTile = (Tile) this.playground.getComponentAt(e.getPoint());
            if (currentTile.getOwnership().equals(Ownership.PLAYABLE) && lastVisitedPlayableTile == null) {
                this.lastVisitedPlayableTile = currentTile;
                currentTile.setOwnership(Ownership.PLAYABLE_HOVERED);
            }
            if(lastVisitedPlayableTile != null){
                if(!currentTile.equals(lastVisitedPlayableTile)){
                    this.lastVisitedPlayableTile.setOwnership(Ownership.PLAYABLE);
                    this.lastVisitedPlayableTile = null;
                }
            }
        }
    }

}
