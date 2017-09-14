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
    switch (minesweeper.getSetup()) {
      case 1:
        sizeX = 300;
        sizeY = 346;
        break;
      case 2:
        sizeX = 496;
        sizeY = 540;
        break;
      default:
        sizeX = 780;
        sizeY = 492;
        break;
    }
    setSize(sizeX, sizeY);
  }

}
