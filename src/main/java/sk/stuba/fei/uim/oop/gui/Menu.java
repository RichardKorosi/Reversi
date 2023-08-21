package sk.stuba.fei.uim.oop.gui;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import java.awt.*;
import java.awt.event.*;

public class Menu extends UniversalAdapter{
    private final JSlider tileSlider;
    private final JLabel info;
    private final Game playground;
    private int selectedNumberOfTiles;

    public int getSelectedNumberOfTiles() {
        return selectedNumberOfTiles;
    }



    public Menu(Game playground) {
        this.setBackground(Color.PINK);
        this.playground = playground;
        this.setBackground(Color.DARK_GRAY);

        JButton restartButton = new JButton("Restart");
        restartButton.addActionListener(this);
        this.add(restartButton);

        this.tileSlider = new JSlider(6,12, 6);
        this.tileSlider.setPaintTrack(true);
        this.tileSlider.setPaintTicks(true);
        this.tileSlider.setPaintLabels(true);
        this.tileSlider.setMajorTickSpacing(2);
        this.tileSlider.setSnapToTicks(true);
        this.tileSlider.addChangeListener(this);
        this.selectedNumberOfTiles = 6;
        this.add(tileSlider);
        this.info = new JLabel("Current size" + this.selectedNumberOfTiles + "x" + this.selectedNumberOfTiles);
        this.info.setForeground(Color.WHITE);
        this.add(info);
        restartButton.setFocusable(false);
        this.tileSlider.setFocusable(false);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.playground.restartPlayground(this.selectedNumberOfTiles);
        this.info.setText("Current size" + this.selectedNumberOfTiles + "x" + this.selectedNumberOfTiles);

    }

    @Override
    public void stateChanged(ChangeEvent e) {
        this.selectedNumberOfTiles = this.tileSlider.getValue();
        this.playground.restartPlayground(this.selectedNumberOfTiles);
        this.info.setText("Current size" + this.selectedNumberOfTiles + "x" + this.selectedNumberOfTiles);
    }
}
