package MinesweeperGame;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Label;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JPanel;

public class TopPanel extends JPanel {

  private Minesweeper minesweeper;
  private ViewGame viewGame;

  private JPanel panelTop;
  private JPanel panelButtons;

  private Label NEW_GAME;
  private Label bombs;

  private Button restartEasy;
  private Button restartMedium;
  private Button restartHard;
  
  private int bombsRemaining;
  
  public TopPanel(Minesweeper minesweeper, ViewGame viewGame) {
    this.minesweeper = minesweeper;
    this.viewGame = viewGame;

    setLayout(new BorderLayout());
    setBackground(Color.lightGray);
    /*
    * create and init Buttons: Easy-Medium-Hard 
     */
    panelButtons = new JPanel();

    restartEasy = new Button("Easy");
    restartMedium = new Button("Medium");
    restartHard = new Button("Hard");

    NEW_GAME = new Label("New Game");
    NEW_GAME.setBackground(Color.lightGray);
    NEW_GAME.setForeground(Color.black);
    
    bombsRemaining = minesweeper.getNumberBombs();
    bombs = new Label(Integer.toString(bombsRemaining));

    panelTop = new JPanel();

    initTopPanel();
  }

  public void initTopPanel() {
    MouseListener listener = new TopListener();

    panelButtons.add(restartEasy);
    panelButtons.add(restartMedium);
    panelButtons.add(restartHard);

    restartEasy.addMouseListener(listener);
    restartMedium.addMouseListener(listener);
    restartHard.addMouseListener(listener);

    bombs.setBackground(Color.lightGray);
    bombs.setForeground(Color.white);

    panelTop.add(NEW_GAME);
    add(panelTop, "North");
    add(panelButtons, "Center");
    add(bombs, "West");
    restartTopPanel(bombsRemaining);
  }

  public void restartTopPanel(int bombsRemaining) {
    bombs.setText(Integer.toString(bombsRemaining));
  }

  private class TopListener implements MouseListener {

    @Override
    public void mouseClicked(MouseEvent event) {

      if (event.getSource() == restartEasy) {
        minesweeper.initEasy();
      }
      if (event.getSource() == restartMedium) {
        minesweeper.initMedium();
      }
      if (event.getSource() == restartHard) {
        minesweeper.initHard();
      }

      if ((event.getSource() == restartMedium) || (event.getSource() == restartHard)
        || (event.getSource() == restartEasy)) {
        viewGame.dispose();
        ViewGame viewGame = new ViewGame(minesweeper);
      }
    }
    public void setLabel(int x){
      bombs.setText(Integer.toString(x));
    }
    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
  }
}
