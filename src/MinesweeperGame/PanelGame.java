package MinesweeperGame;

import java.awt.BorderLayout;
import javax.swing.JPanel;

public class PanelGame extends JPanel {
  public final TopPanel topPanel;
  private final MainPanel mainPanel;
  public PanelGame(Minesweeper minesweeper, ViewGame viewGame) {
    setLayout(new BorderLayout());
    
    topPanel = new TopPanel(minesweeper, viewGame);
    add(topPanel, BorderLayout.NORTH);
    
    mainPanel = new MainPanel(minesweeper, viewGame);
    add(mainPanel, BorderLayout.CENTER);
  }
  
}
