package MinesweeperGame;

public class Minesweeper {

  private int numberRows;
  private int numberColumns;
  private int numberBombs;

  private int previewRows;
  private int previewColumns;

  private final boolean bomb[][];
  private final boolean flag[][];
  private final boolean exposed[][];
  private final boolean checkwinbool[][];

  private int setup;
  private int sizeX;
  private int sizeY;

  public Minesweeper() {
    numberRows = 16;
    numberColumns = 30;
    numberBombs = 99;
    previewRows = 16;
    previewColumns = 30;
    sizeX = 780;
    sizeY = 492;
    setup = 3;

    bomb = new boolean[numberRows][numberColumns];
    flag = new boolean[numberRows][numberColumns];
    exposed = new boolean[numberRows][numberColumns];
    checkwinbool = new boolean[numberRows][numberColumns];
  }

  public void restartEasy() {
    numberRows = 10;
    numberColumns = 10;
    numberBombs = 10;
    sizeX = 300;
    sizeY = 346;
    if (setup == 2) {
      previewRows = 16;
      previewColumns = 16;
      setup = 1;
    } else if (setup == 3) {
      previewRows = 16;
      previewColumns = 30;
      setup = 1;
    }
  }

  public void restartMedium() {
    numberRows = 16;
    numberColumns = 16;
    numberBombs = 40;
    sizeX = 496;
    sizeY = 540;
    if (setup == 1) {
      previewRows = 10;
      previewColumns = 10;
      setup = 2;
    } else if (setup == 3) {
      previewRows = 16;
      previewColumns = 30;
      setup = 2;
    }
  }

  public void restartHard() {
    numberRows = 16;
    numberColumns = 30;
    numberBombs = 99;
    sizeX = 780;
    sizeY = 492;
    if (setup == 1) {
      previewRows = 10;
      previewColumns = 10;
      setup = 3;
    } else if (setup == 2) {
      previewRows = 16;
      previewColumns = 16;
      setup = 3;
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

  public int getProw() {
    return previewRows;
  }

  public int getPcol() {
    return previewColumns;
  }

  public int getSizeX() {
    return sizeX;
  }

  public int getSizeY() {
    return sizeY;
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

  public boolean[][] getCheckT() {
    return checkwinbool;
  }

  public boolean[][] getBombT() {
    return bomb;
  }

  public boolean[][] getFlagT() {
    return flag;
  }

  /*
        sets
   */
  public void setBomb(int i, int j, boolean x) {
    bomb[i][j] = x;
  }

  public void setFlag(int i, int j, boolean x) {
    flag[i][j] = x;
  }

  public void setExposed(int i, int j, boolean x) {
    exposed[i][j] = x;
  }

  public void setCheck(int i, int j, boolean x) {
    checkwinbool[i][j] = x;
  }

  public void setNumberRows(int x) {
    numberRows = x;
  }

  public void setNumberColumns(int x) {
    numberColumns = x;
  }

  public void setNumberBombs(int x) {
    numberBombs = x;
  }

  public void setProw(int x) {
    previewRows = x;
  }

  public void setPcol(int x) {
    previewColumns = x;
  }

  public void setSetup(int x) {
    setup = x;
  }

}
