package sk.stuba.fei.uim.oop.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class ReversiWindow extends JFrame implements KeyListener {
    private final Game playground;
    private final Menu sideMenu;

    public ReversiWindow() throws HeadlessException {
        this.setTitle("Reversi");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(800, 800);
        this.setResizable(false);
        this.setLayout(new BorderLayout());

        this.playground = new Game(6);
        this.add(playground, BorderLayout.CENTER);
        this.sideMenu = new Menu(playground);
        this.add(sideMenu, BorderLayout.PAGE_END);
        this.setFocusable(true);
        this.addKeyListener(this);

        this.setVisible(true);
    }

    private void exit(){
        this.dispose();
        System.exit(0);
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyChar()) {
            case 27:
                exit();
                break;
            case 82:
            case 114:
                this.playground.restartPlayground(this.sideMenu.getSelectedNumberOfTiles());
                break;
        }
     }

    @Override
    public void keyReleased(KeyEvent e) {
    }
}
