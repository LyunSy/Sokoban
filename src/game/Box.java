package game;

import javax.swing.*;

public class Box extends Elements {

    public final int OFFSET = 40;

    public Box(int x, int y) {
        super(x, y, new ImageIcon("src/images/box.png"));
    }

    public void moveByPlayer(Board board, int deltaX, int deltaY) {
        System.out.println(this);

        int newPosX = this.getX() + deltaX * OFFSET;
        int newPosY = this.getY() + deltaY * OFFSET;

        // Vérifier si le mouvement est valide
        if (isValidMove(board, newPosX, newPosY)) {
            
            this.setX(newPosX);
            this.setY(newPosY);
            
            // Mettre à jour l'élément de la case de destination
            Elements destinationElement = board.getElements()[newPosX / OFFSET][newPosY / OFFSET];
            
            board.setElement(newPosX / OFFSET, newPosY / OFFSET, this);
            board.setComponentZOrder(this, 0);
            board.repaint();
            if (destinationElement instanceof Goal){
                board.checkWinCondition();
            }
          
        }
    }


    public boolean isValidMove(Board board, int newPosX, int newPosY) {
        if (newPosX >= 0 && newPosX < board.getBoardWidth() * OFFSET &&
                newPosY >= 0 && newPosY < board.getBoardHeight() * OFFSET) {
            Elements destinationElement = board.getElements()[newPosX / OFFSET][newPosY / OFFSET];
            System.out.println(destinationElement.getClass().getName());
            return !(destinationElement instanceof Wall) || !(destinationElement instanceof Box);
        }
        return false;
    }
}
