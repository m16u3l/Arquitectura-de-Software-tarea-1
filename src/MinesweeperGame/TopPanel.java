package MinesweeperGame;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Label;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JPanel;

public class TopPanel extends JPanel {

  private final Minesweeper minesweeper;
  private final ViewGame viewGame;

  private final JPanel panelTop;
  private final JPanel panelButtons;

  private final Label NEW_GAME;
  private final Label bombs;

  private final Button restartEasy;
  private final Button restartMedium;
  private final Button restartHard;
  
  private final int bombsRemaining;
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
        minesweeper.restartEasy();
      }
      if (event.getSource() == restartMedium) {
        minesweeper.restartMedium();
      }
      if (event.getSource() == restartHard) {
        minesweeper.restartHard();
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
