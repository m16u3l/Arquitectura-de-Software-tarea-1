package MinesweeperGame;

public class Minesweeper {

  private int numberRows;
  private int numberColumns;
  private int numberBombs;

  private boolean bomb[][];
  private boolean flag[][];
  private boolean exposed[][];
  private boolean checkwinbool[][];

  private int setup;

  private int randomBombX;
  private int randomBombY;

  public Minesweeper() {
    numberRows = 16;
    numberColumns = 30;
    numberBombs = 99;
    setup = 3;

    bomb = new boolean[numberRows][numberColumns];
    flag = new boolean[numberRows][numberColumns];
    exposed = new boolean[numberRows][numberColumns];
    checkwinbool = new boolean[numberRows][numberColumns];
    setBombs();
  }

  public void restartEasy() {
    numberRows = 10;
    numberColumns = 10;
    numberBombs = 10;
    
    if (setup == 2) {
      setup = 1;
    } else if (setup == 3) {
      setup = 1;
    }
    initGame();
    setBombs();
  }

  public void restartMedium() {
    numberRows = 16;
    numberColumns = 16;
    numberBombs = 40;
    if (setup == 1) {
      setup = 2;
    } else if (setup == 3) {
      setup = 2;
    }
    initGame();
    setBombs();
  }

  public void restartHard() {
    numberRows = 16;
    numberColumns = 30;
    numberBombs = 99;
    if (setup == 1) {
      setup = 3;
    } else if (setup == 2) {
      setup = 3;
    }
    initGame();
    setBombs();
  }

  private void initGame() {
    for (int i = 0; i < numberRows; i++) {
      for (int j = 0; j < numberColumns; j++) {
        bomb[i][j] = false;
        flag[i][j] = false;
        exposed[i][j] = false;
        checkwinbool[i][j] = false;
      }
    }
  }

  private void setBombs() {
    int bombsCount = 0;
    while (bombsCount < numberBombs) {
      randomBombX = (int) (Math.random() * (numberRows));
      randomBombY = (int) (Math.random() * (numberColumns));
      if (bomb[randomBombX][randomBombY] == false) {
        bomb[randomBombX][randomBombY] = true;
        checkwinbool[randomBombX][randomBombY] = true;
        bombsCount++;
      }
    }
  }

  public int getSurroundingFlags(int x, int y) {
    int surFlags = 0;
    for (int i = x - 1; i <= x + 1; i++) {
      for (int j = y - 1; j <= y + 1; j++) {
        while (true) {
          if ((i < 0) || (j < 0) || (i >= numberRows) || (j >= numberColumns)) {
            break;
          }
          if (flag[i][j] == true) {
            surFlags++;
          }
          break;
        }
      }
    }
    return surFlags;
  }

  public int checkSurroundingBombs(int x, int y) {
    int surBombs = 0;
    for (int i = x - 1; i <= x + 1; i++) {
      for (int j = y - 1; j <= y + 1; j++) {
        while (true) {
          if ((i < 0) || (j < 0) || (i >= numberRows) || (j >= numberColumns)) {
            break;
          }
          if (bomb[i][j] == true) {
            surBombs++;
          }
          break;
        }
      }
    }
    return surBombs;
  }

  /*
        gets
   */
  public int getSetup() {
    return setup;
  }
  
  public int getNumberRows() {
    return numberRows;
  }

  public int getNumberColumns() {
    return numberColumns;
  }

  public int getNumberBombs() {
    return numberBombs;
  }

  public boolean getBomb(int x, int y) {
    return bomb[x][y];
  }

  public boolean getFlag(int x, int y) {
    return flag[x][y];
  }

  public boolean getCheck(int i, int j) {
    return checkwinbool[i][j];
  }

  public boolean getExposed(int i, int j) {
    return exposed[i][j];
  }

  /*
        sets
   */
  public void setFlag(int i, int j, boolean x) {
    flag[i][j] = x;
  }

  public void setExposed(int i, int j, boolean x) {
    exposed[i][j] = x;
  }

  public void setCheck(int i, int j, boolean x) {
    checkwinbool[i][j] = x;
  }
}
