package MinesweeperGame;

import java.awt.Button;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JPanel;

public class MainPanel extends JPanel {

  private Minesweeper minesweeper;
  private ViewGame viewGame;

  private Button table[][];

  private GridLayout gridLayout;

  private String surbombs;
  private int randomBombX;
  private int randomBombY;

  private int bombsRemaining;
  
  public MainPanel(Minesweeper minesweeper, ViewGame viewGame) {
    this.minesweeper = minesweeper;
    this.viewGame = viewGame;

    gridLayout = new GridLayout(minesweeper.getNumberRows(), minesweeper.getNumberColumns());
    setLayout(gridLayout);

    table = new Button[minesweeper.getNumberRows()][minesweeper.getNumberColumns()];

    bombsRemaining = minesweeper.getNumberBombs();
    initMainPanel();
  }

  public void initMainPanel() {
    MouseListener listener = new BombListener();

    int nR = minesweeper.getNumberRows();
    int nC = minesweeper.getNumberColumns();
    for (int i = 0; i < nR; i++) {
      for (int j = 0; j < nC; j++) {
        table[i][j] = new Button();
        table[i][j].addMouseListener(listener);
        add(table[i][j]);
      }
    }
    restartMainPanel();
  }

  public void restartMainPanel() {
    gridLayout.setRows(minesweeper.getNumberRows());
    gridLayout.setColumns(minesweeper.getNumberColumns());
    int bombsCount = 0;

    int nR = minesweeper.getNumberRows();
    int nC = minesweeper.getNumberColumns();
    for (int i = 0; i < nR; i++) {
      for (int j = 0; j < nC; j++) {
        table[i][j].setEnabled(true);
        table[i][j].setLabel(" ");
        table[i][j].setBackground(Color.gray);
        table[i][j].setForeground(Color.white);
        add(table[i][j]);
        minesweeper.setBomb(i, j, false);
        minesweeper.setFlag(i, j, false);
        minesweeper.setExposed(i, j, false);
        minesweeper.setCheck(i, j, false);
      }
    }
    int nB = minesweeper.getNumberBombs();
    while (bombsCount < nB) {
      randomBombX = (int) (Math.random() * (nR));
      randomBombY = (int) (Math.random() * (nC));
      if (minesweeper.getBomb(randomBombX, randomBombY) == false) {
        minesweeper.setBomb(randomBombX, randomBombY, true);
        minesweeper.setCheck(randomBombX, randomBombY, true);

        bombsCount++;
      }
    }
  }

  public void check(int i, int j) {
    exposeSurroundingButtons(i, j);
    showFreeFields(i, j);
  }

  public void showFreeFields(int x, int y) {
    for (int i = x - 1; i <= x + 1; i++) {
      for (int j = y - 1; j <= y + 1; j++) {
        while (true) {
          if ((i < 0) || (j < 0) || (i >= minesweeper.getNumberRows())
            || (j >= minesweeper.getNumberColumns())) {
            break;
          }
          if (minesweeper.getFlag(i, j) == true) {
            break;
          }
          if ((minesweeper.getExposed(i, j) == false)
            && (minesweeper.checkSurroundingBombs(i, j) == 0)) {
            exposeSurroundingButtons(i, j);
            check(i, j);
          }
          break;
        }
      }
    }
  }

  public void exposeSurroundingButtons(int x, int y) {
    String surrbombs;
    minesweeper.setExposed(x, y, true);
    for (int i = x - 1; i <= x + 1; i++) {
      for (int j = y - 1; j <= y + 1; j++) {
        while (true) {
          if ((i < 0) || (j < 0) || (i >= minesweeper.getNumberRows())
            || (j >= minesweeper.getNumberColumns())) {
            break;
          }
          if (minesweeper.getFlag(i, j) == true) {
            break;
          }
          minesweeper.setCheck(i, j, true);
          surrbombs = Integer.toString(minesweeper.checkSurroundingBombs(i, j));
          table[i][j].setLabel(surrbombs);
          break;

        }
      }
    }
  }

  private void gameOver() {
    for (int x = 0; x < minesweeper.getNumberRows(); x++) {
      for (int y = 0; y < minesweeper.getNumberColumns(); y++) {
        if (minesweeper.getBomb(x, y) == true) {
          table[x][y].setLabel("*"); //exposes all bombs
          table[x][y].setBackground(Color.red);
        }
        table[x][y].setEnabled(false); //disable all buttons
      }
    }
    int x2 = (int) minesweeper.getNumberColumns() / 2;
    int y2 = (int) minesweeper.getNumberRows() / 2;
    table[y2][x2 - 4].setLabel("Y");
    table[y2][x2 - 3].setLabel("O");
    table[y2][x2 - 2].setLabel("U");
    table[y2][x2 - 1].setLabel("");
    table[y2][x2].setLabel("L");
    table[y2][x2 + 1].setLabel("O");
    table[y2][x2 + 2].setLabel("S");
    table[y2][x2 + 3].setLabel("E");
    table[y2][x2 + 4].setLabel("!");
    for (int i = -4; i < 5; i++) {
      table[y2][x2 + i].setBackground(Color.black);
      table[y2][x2 + i].setForeground(Color.white);
    }
  }

  public void clicked() {
    for (int i = 0; i < minesweeper.getNumberRows(); i++) {
      for (int j = 0; j < minesweeper.getNumberColumns(); j++) {
        if (minesweeper.getCheck(i, j) == true && minesweeper.getFlag(i, j) == false
          && minesweeper.getBomb(i, j) == false) {
          table[i][j].setBackground(Color.darkGray);
        }
        if (minesweeper.getFlag(i, j) == false && minesweeper.checkSurroundingBombs(i, j) == 0) {
          table[i][j].setLabel("");
        }
      }
    }
  }

  public void checkWin() {
    boolean allexposed = true;
    int nR = minesweeper.getNumberRows();
    int nC = minesweeper.getNumberColumns();
    for (int i = 0; i < nR; i++) {
      for (int j = 0; j < nC; j++) {
        if ((minesweeper.getFlag(i, j) == true)
          && (minesweeper.getBomb(i, j) == false)) {
          allexposed = false;
        }

        if (minesweeper.getCheck(i, j) == false) {
          allexposed = false;
          break;
        }
      }
    }
    if (allexposed != false) {
      gameOver();
      int x2 = (int) minesweeper.getNumberColumns() / 2;
      int y2 = (int) minesweeper.getNumberRows() / 2;
      table[y2][x2 - 4].setLabel("Y");
      table[y2][x2 - 3].setLabel("O");
      table[y2][x2 - 2].setLabel("U");
      table[y2][x2 - 1].setLabel("");
      table[y2][x2].setLabel("W");
      table[y2][x2 + 1].setLabel("I");
      table[y2][x2 + 2].setLabel("N");
      table[y2][x2 + 3].setLabel("!");
      table[y2][x2 + 4].setLabel("!");
      for (int i = -4; i < 5; i++) {
        table[y2][x2 + i].setBackground(Color.black);
        table[y2][x2 + i].setForeground(Color.white);
      }
    }
  }

  private class BombListener implements MouseListener {

    @Override
    public void mouseClicked(MouseEvent event) {
      boolean gameover = false;

      int n = minesweeper.getNumberRows();
      int m = minesweeper.getNumberColumns();

      for (int i = 0; i < n; i++) {
        for (int j = 0; j < m; j++) {
          if (event.getSource() == table[i][j]) {
            if ((event.getButton() == MouseEvent.BUTTON1)
              && (minesweeper.getFlag(i, j) == false)) {
              if (minesweeper.getBomb(i, j) == true) {
                
                table[i][j].setLabel("*");
                gameOver();
                table[i][j].setBackground(Color.black);
                gameover = true;
                break;

              }
              minesweeper.setExposed(i, j, true);
              minesweeper.setCheck(i, j, true);

              surbombs = Integer.toString(minesweeper.checkSurroundingBombs(i, j)); 
              table[i][j].setLabel(surbombs);
              if (minesweeper.checkSurroundingBombs(i, j) == 0) {
                check(i, j);
              }
            } else if (event.getButton() == MouseEvent.BUTTON3) {
              if (minesweeper.getFlag(i, j) == true) {
                table[i][j].setLabel("");
                table[i][j].setForeground(Color.white);
                minesweeper.setFlag(i, j, false);
                minesweeper.setCheck(i, j, false);

                bombsRemaining++;

              } else if (minesweeper.getCheck(i, j) == false
                || minesweeper.getBomb(i, j) == true) {
                table[i][j].setLabel("|>");
                table[i][j].setForeground(Color.red);

                minesweeper.setFlag(i, j, true);
                minesweeper.setCheck(i, j, true);

                bombsRemaining--;
              }
              
              viewGame.panelGame.topPanel.restartTopPanel(bombsRemaining);
              
            } else if ((event.getButton() == MouseEvent.BUTTON2)
              && (minesweeper.getFlag(i, j) == false)
              && (minesweeper.getCheck(i, j) == true)
              && (minesweeper.getBomb(i, j) == false)) {
              if (minesweeper.getSurroundingFlags(i, j) == minesweeper.checkSurroundingBombs(i, j)) {
                for (int k = i - 1; k <= i + 1; k++) {
                  for (int l = j - 1; l <= j + 1; l++) {
                    if ((k < 0) || (l < 0) || (k >= minesweeper.getNumberRows())
                      || (l >= minesweeper.getNumberColumns())) {
                      break;
                    }
                    if (minesweeper.getBomb(k, l) == false
                      && minesweeper.getFlag(k, l) == true) {
                      gameOver();
                      gameover = true;
                      break;
                    }
                  }
                }
                if (gameover == false) {
                  exposeSurroundingButtons(i, j);
                  check(i, j);
                }
              }
            }
            if (gameover == false) {
              clicked();
            }
          }
        }
      }
      checkWin();
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
