public class Cell{
  private char icon;
  private boolean visited;
  private boolean isStart;
  private int row;
  private int col;

  public Cell(char i, int r, int c){
    icon = i;
    visited = false;
    row = r;
    col = c;
  }

  public void changeIcon(char i){
    icon = i;
  }
  public char getIcon(){
    return icon;
  }

  public void visit(){
    visited = true;
  }

  public void setCoords(int r, int c){
    row = r;
    col = c;
  }

  public int getRow(){
    return row;
  }
  public int getCol(){
    return col;
  }

  public void unVisit(){
    visited = false;
  }
  public boolean been(){
    return visited;
  }
}
