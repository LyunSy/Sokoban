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

            // Movement de joueur
            this.setX(newPosX);
            this.setY(newPosY);
            
            //Si la destination est une boite, Pousse la boite
            if (destinationElement instanceof Box ){
                Box box = (Box) destinationElement;
                box.moveByPlayer(board, deltaX, deltaY);
            }
            
            
            board.setElement(newPosX / OFFSET, newPosY / OFFSET, this);
            board.setComponentZOrder(this, 0);
            if (destinationElement instanceof Goal){
                board.setElement(newPosX / OFFSET, newPosY / OFFSET, new Goal(newPosX,newPosY));
                
            }else{
                board.setElement(this.getX() / OFFSET, this.getY() / OFFSET, new EmptySpace(this.getX(),this.getY()));
            }
            board.repaint();
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
