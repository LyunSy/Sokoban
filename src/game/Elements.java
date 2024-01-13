package game;

import javax.swing.*;
import java.awt.*;

public class Elements extends JLabel {

    private int x;
    private int y;
    private Image image;

    public Elements(int x, int y, Icon icon) {
        this.x = x;
        this.y = y;
        setIcon(icon);
        setBounds(x, y, icon.getIconWidth(), icon.getIconHeight());
    }

    public Image getImage() {
        return image;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public static Elements createInstance(char character, int x, int y) {
        switch (character) {
            case '#':
                return new Wall(x, y);
            case '.':
                return new EmptySpace(x, y);
            case 'x':
                return new Goal(x, y);
            case '@':
                return new Player(x, y);
            case '$':
                return new Box(x, y);

            default:
                return null;
        }
    }

}
