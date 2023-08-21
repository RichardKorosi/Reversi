package sk.stuba.fei.uim.oop.tile;

import java.awt.*;

public enum Ownership {
    PLAYER(Color.BLACK),
    COMPUTER(Color.WHITE),
    NONE(Color.PINK),
    PLAYABLE(Color.MAGENTA),
    ACTIVATED_PLAYER_STONE(Color.GRAY),
    ACTIVATED_COMPUTER_STONE(Color.GRAY),
    PLAYABLE_HOVERED(Color.GREEN);

    public final Color myColor;

    Ownership(Color myColor) {
        this.myColor = myColor;
    }
}
