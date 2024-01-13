package game;

import javax.swing.*;

public class Box extends Elements {

    public final int OFFSET = 40;

    public Box(int x, int y) {
        super(x, y, new ImageIcon("src/images/box.png"));
    }

    public void moveByPlayer(Board board, int deltaX, int deltaY) {

        int newPosX = getX() + deltaX * OFFSET;
        int newPosY = getY() + deltaY * OFFSET;

        // Vérifier si le mouvement est valide
        if (isValidMove(board, newPosX, newPosY)) {
            // Remplacer l'ancien emplacement par emptySpace
            board.setElement(this.getX() / OFFSET, this.getY() / OFFSET, new EmptySpace(this.getX(), this.getY()));
            this.setX(newPosX);
            this.setY(newPosY);
            // Mettre à jour l'élément de la case de destination
            board.setElement(newPosX / OFFSET, newPosY / OFFSET, this);
            board.setComponentZOrder(this, 0);
            board.repaint();

        }
    }


    public boolean isValidMove(Board board, int newPosX, int newPosY) {
        if (newPosX >= 0 && newPosX < board.getBoardWidth() * OFFSET &&
                newPosY >= 0 && newPosY < board.getBoardHeight() * OFFSET) {
            Elements destinationElement = board.getElements()[newPosX / OFFSET][newPosY / OFFSET];
            return destinationElement instanceof EmptySpace || destinationElement instanceof Goal;
        }
        return false;
    }
}
