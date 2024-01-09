package game;

import javax.swing.*;
import java.awt.*;

public class Box extends Elements{

    public final int OFFSET = 40;
    public Box(int x, int y) {
        super(x, y, new ImageIcon("src/images/box.png"));
    }

    public void moveByPlayer(Board board, int deltaX, int deltaY) {
        int newPosX = getX() + deltaX * OFFSET;
        int newPosY = getY() + deltaY * OFFSET;

        // Vérifier les limites du tableau pour éviter les déplacements en dehors du tableau
        if (newPosX >= 0 && newPosX < board.getBoardWidth() * OFFSET && newPosY >= 0 && newPosY < board.getBoardHeight() * OFFSET) {
            // Vérifier la case de destination
            Elements destinationElement = board.getElements()[newPosX / OFFSET][newPosY / OFFSET];

            // Si la case de destination est un espace vide, déplacer la boîte
            if (!(destinationElement instanceof Wall)) {
                // Mettre à jour les coordonnées de la boîte
                setBounds(newPosX / OFFSET, newPosY / OFFSET, getWidth(), getHeight());
                setX(newPosX);
                setY(newPosY);

                // Forcer le redessin du panneau
                board.setComponentZOrder(this, 0);
                board.repaint();
            }
            // Vous pouvez ajouter d'autres conditions pour gérer d'autres interactions (cible, mur, etc.)
            //if (destinationElement instanceof Goal)
                //TODO Box in the Goal

        }
    }

}
