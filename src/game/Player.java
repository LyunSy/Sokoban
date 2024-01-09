package game;

import javax.swing.*;
import java.awt.*;

public class Player extends Elements {

    public final int OFFSET = 40;

    public Player(int x, int y) {
        super(x, y, new ImageIcon("src/images/player.png"));
    }

    public void playerMove(Board board, int deltaX, int deltaY) {
        board.add(new EmptySpace(this.getX(), this.getY()));
        int newPosX = this.getX() + deltaX * OFFSET;
        int newPosY = this.getY() + deltaY * OFFSET;


        if (newPosX >= 0 && newPosX < board.getBoardWidth() * OFFSET && newPosY >= 0 && newPosY < board.getBoardHeight() * OFFSET) {

            Elements destinationElement = board.getElements()[newPosX / OFFSET][newPosY / OFFSET];

            if (!(destinationElement instanceof Wall) && destinationElement != null) {
                setBounds(newPosX / OFFSET, newPosY / OFFSET, getWidth(), getHeight());
                setX(newPosX);
                setY(newPosY);
                board.setComponentZOrder(this, 0);
                board.repaint();

                if (destinationElement instanceof Box) {
                    Box box = (Box) destinationElement;
                    box.moveByPlayer(board, deltaX, deltaY);
                }
                // Si la case de destination n'est pas une boîte, déplacer le joueur
                else {
                    setBounds(newPosX / OFFSET, newPosY / OFFSET, getWidth(), getHeight());
                    setX(newPosX);
                    setY(newPosY);

                    board.repaint();
                }
            }

        }
    }
}
