package game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class Board extends JPanel implements KeyListener {

    private final int OFFSET = 40;
    private Elements[][] elements;
    private Player player;
    private final int boardWidth = 10;
    private final int boardHeight = 5;

    private int numberOfGoals = 0;
    private int goalsReached = 0;

    public Board(){
        this.elements = new Elements[boardWidth][boardHeight];
        initBoard();
        setLayout(new GridLayout(boardHeight, boardWidth));

        this.player = findPlayer();
        addKeyListener(this);
        setFocusable(true);
        requestFocus();

    }

    public void initBoard(){
        String[] map = {
                "##########",
                "#...x#.x.#",
                "#.$##.$..#",
                "#@.......#",
                "##########",
        };
        for (int i = 0; i < boardWidth; i++) {
            for (int j = 0; j < boardHeight; j++) {
                char currentChar = map[j].charAt(i);
                if (currentChar != ' ') {
                    elements[i][j] = Elements.createInstance(currentChar, i * OFFSET, j * OFFSET);
                    add(elements[i][j]);
                    if (currentChar == '@' || currentChar == '$' || currentChar == 'x') {
                        EmptySpace emptySpaceBehind = new EmptySpace(i * OFFSET, j * OFFSET);
                        add(emptySpaceBehind);
                    }
                    if (currentChar == 'x') {
                    numberOfGoals++;
                    }
                }
            }
        }
    }

    public Elements[][] getElements() {
        return elements;
    }


    public int getBoardHeight() {
        return boardHeight;
    }

    public int getBoardWidth() {
        return boardWidth;
    }



    public Player findPlayer() {
        for (int i = 0; i < boardWidth; i++) {
            for (int j = 0; j < boardHeight; j++) {
                if (elements[i][j] instanceof Player) {
                    return (Player) elements[i][j];
                }
            }
        }
        return null;  // Retournez null si le joueur n'est pas trouvé
    }

    public void checkWinCondition() {
        goalsReached++;

        // La condition de victoire est que tous les objectifs ont une boîte
        if (goalsReached == numberOfGoals) {
            showWinMessage();
        }
    }

    public void showWinMessage() {
        Object[] options = {"OK"};
        int choice = JOptionPane.showOptionDialog(
                this,
                "Congratulations! You won!",
                "You Won",
                JOptionPane.YES_OPTION,
                JOptionPane.INFORMATION_MESSAGE,
                null,
                options,
                options[0]
        );

        if (choice == JOptionPane.YES_OPTION) {
            System.exit(0); // Fermer le jeu
        }
    }

    public void setElement(int x, int y, Elements element) {
        elements[x][y] = element;
    }
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        for (int i = 0; i < boardWidth; i++) {
            for (int j = 0; j < boardHeight; j++) {
                Elements currentElement = elements[i][j];
                if (currentElement != null) {
                    g.drawImage(currentElement.getImage(), i * OFFSET, j * OFFSET, this);
                }
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();

        // Traitez la touche en fonction du code
        switch (keyCode) {
            case KeyEvent.VK_UP:
                player.playerMove(this, 0, -1);
                break;
            case KeyEvent.VK_DOWN:
                player.playerMove(this,0, 1);
                break;
            case KeyEvent.VK_LEFT:
                player.playerMove(this,-1, 0);
                break;
            case KeyEvent.VK_RIGHT:
                player.playerMove(this,1, 0);
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }



}


