package MinesweeperGame;

public class Main {

  public static void main(String[] args) {
    
    Minesweeper minesweeper = new Minesweeper();
    ViewGame viewGame = new ViewGame(minesweeper);
    
  }
}