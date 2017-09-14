package MinesweeperGame;

import java.awt.Font;
import javax.swing.JFrame;

public final class ViewGame extends JFrame {

  public PanelGame panelGame;

  private Font font;

  private int sizeX;
  private int sizeY;

  public ViewGame(Minesweeper minesweeper) {
    font = new Font("ComicSans", Font.BOLD, 17);
    setTitle("Minesweeper");
    setFont(font);
    setVisible(true);
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    panelGame = new PanelGame(minesweeper, this);
    add(panelGame);

    sizeX = minesweeper.getSizeX();
    sizeY = minesweeper.getSizeY();

    setSize(sizeX, sizeY);
    
  }
  
}
