package game;

import javax.swing.*;

public class Player extends Elements {

    public final int OFFSET = 40;

    public Player(int x, int y) {
        super(x, y, new ImageIcon("src/images/player.png"));
    }

    public void playerMove(Board board, int deltaX, int deltaY) {
        int newPosX = this.getX() + deltaX * OFFSET;
        int newPosY = this.getY() + deltaY * OFFSET;
        int behindNewPosX = newPosX + deltaX * OFFSET;
        int behindNewPosY = newPosY + deltaY * OFFSET;

        // Vérifier si le mouvement est valide
        if (isValidMove(board, newPosX, newPosY, behindNewPosX, behindNewPosY)) {

            Elements destinationElement = board.getElements()[newPosX / OFFSET][newPosY / OFFSET];

            // Si la destination est une boîte
            if (destinationElement instanceof Box   ){
                    Box box = (Box) destinationElement;
                    box.moveByPlayer(board, deltaX, deltaY);
                    this.setX(newPosX);
                    this.setY(newPosY);
                    board.setElement(newPosX / OFFSET, newPosY / OFFSET, this);
                    board.setComponentZOrder(this, 0);
                    board.repaint();

            } else {
                // Si la destination n'est pas une boîte
                board.setElement(this.getX() / OFFSET, this.getY() / OFFSET, new EmptySpace(this.getX(), this.getY()));
                this.setX(newPosX);
                this.setY(newPosY);
                // Mettre à jour l'élément de la case de destination
                board.setElement(newPosX / OFFSET, newPosY / OFFSET, this);
                board.setComponentZOrder(this, 0);
                board.repaint();
            }

            board.checkWinCondition();
        }
    }

    public boolean isValidMove(Board board, int newPosX, int newPosY, int behindNewPosX, int behindNewPosY) {
        // Vérifier si les nouvelles positions sont valides
        if (newPosX >= 0 && newPosX < board.getBoardWidth() * OFFSET &&
                newPosY >= 0 && newPosY < board.getBoardHeight() * OFFSET) {

            Elements destinationElement = board.getElements()[newPosX / OFFSET][newPosY / OFFSET];

            // Vérifier si la destination est un mur
            if (destinationElement instanceof Wall) {
                return false;
            }

            // Si la destination est une boîte, vérifier si la position derrière est un mur
            if (destinationElement instanceof Box) {
                try {
                    Elements behindDestinationElement = board.getElements()[behindNewPosX / OFFSET][behindNewPosY / OFFSET];
                    return !(behindDestinationElement instanceof Wall);
                }
                catch (IndexOutOfBoundsException e) {
                    return false;
                }
            }

            return true;
        }

        return false;
    }


}
