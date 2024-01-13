package game;

import javax.swing.*;

public class Window extends JFrame {

    private final int WIDTH = 600;
    private final int HEIGHT = 400;


    public Window(){
            initWindow();
        }

        private void initWindow(){
            setTitle("Sokoban");
            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            this.setSize(WIDTH, HEIGHT);
            this.setLocationRelativeTo(null);
            this.setResizable(false);
            Board board = new Board();
            add(board);

        }
}
