import java.util.*;

public class GameMap{
  private Cell[][] map;

  public GameMap(int rows, int cols){
    map = new Cell[rows][cols];
    this.generateMap();
  }

  public void generateMap(){
    char CELLSPACE = ' ';
    char DOOR = ' ';
    char START = 'F';
    char WALL = '{';
    
    Stack<Cell> search = new Stack<Cell>(); //Cell Stack
    int ROW_NUM = map.length; 
    int COL_NUM = map[0].length;
    int NUM_ELEMENTS = ROW_NUM*COL_NUM;

    boolean otherRow = false;

    boolean validStart = false;

    int startRow = 0;
    int startCol = 0;

    Cell yikes = null;

    
    for(int r = 0; r < ROW_NUM; r++){ //Initialize all cells with walls/empty space
      for(int c = 0; c < COL_NUM; c++){
        if(c%2==0||r%2==0||(c==0)||(c==COL_NUM-1)||(r==0)||(r==ROW_NUM-1)){ //cell is a wall if around borders or every other wall/col
          map[r][c] = new Cell(WALL,r,c);
        } else {
          map[r][c] = new Cell('#',r,c);
        }
        
      } 
      if(otherRow){ //legacy code, might not be needed but scared to break
        otherRow = false;
      } else {
        otherRow = true;
      }
    }


    map[ROW_NUM-1][1].changeIcon('E');
    map[0][COL_NUM-2].changeIcon('S');

    

    while(!validStart){ //Start in empty space
      startRow = Random.randInt(0,ROW_NUM-1);
      startCol = Random.randInt(0,COL_NUM-1);

      if(startRow%2==1){
        if(startCol%2==1){
          validStart = true;
        }
      }
    }

    map[startRow][startCol].visit();
    map[startRow][startCol].changeIcon(START);

    search.push(map[startRow][startCol]);


    while(search.size()!=0){ //NOT Broken Depth first search generation
      Cell current = search.pop();
      
      
      int currentRow = current.getRow();
      int currentCol = current.getCol();

      if(currentRow!=startRow||currentCol!=startCol){
        map[currentRow][currentCol].changeIcon(CELLSPACE);
      }
      ArrayList<Cell> eligible = new ArrayList<Cell>();

      try{ // Find adjacent cells. Do not include or break if out of bounds
        if(!map[currentRow][currentCol+2].been()){
          eligible.add(map[currentRow][currentCol+2]);
        }
        
      } catch ( ArrayIndexOutOfBoundsException e ) {}
      try{
        if(!map[currentRow][currentCol-2].been()){
          eligible.add(map[currentRow][currentCol-2]);
        }
      } catch ( ArrayIndexOutOfBoundsException e ) {}
      try{
        if(!map[currentRow+2][currentCol].been()){
          eligible.add(map[currentRow+2][currentCol]);
        }
      } catch ( ArrayIndexOutOfBoundsException e ) {}
      try{
        if(!map[currentRow-2][currentCol].been()){
          eligible.add(map[currentRow-2][currentCol]);
        }
      } catch ( ArrayIndexOutOfBoundsException e ) {}

      if(eligible.size()!=0){ //If there are eligible cells, shuffle, pick random, fill right wall gap with door icon.
        search.push(map[currentRow][currentCol]);
        Collections.shuffle(eligible);
        Cell chosen = eligible.get(0);
        int cRow = chosen.getRow();
        int cCol = chosen.getCol();
        if(cRow-currentRow==2){
          map[currentRow+1][currentCol].changeIcon(DOOR);
        }
        if(currentRow-cRow==2){
          map[currentRow-1][currentCol].changeIcon(DOOR);
        }
        if(cCol-currentCol==2){
          map[currentRow][currentCol+1].changeIcon(DOOR);
        }
        if(currentCol-cCol==2){
          map[currentRow][currentCol-1].changeIcon(DOOR);
        }

        yikes = map[cRow][cCol];

        map[cRow][cCol].visit();
        search.push(map[cRow][cCol]);
      }

      
      
    }

    
    //map[yikes.getRow()][yikes.getCol()].changeIcon('E');
  }

  private Cell getCellByCoords(int r, int c){
    return map[r][c];
  }

  public void printMap(){
    for(Cell [] r : map){
      for(Cell c : r){
        System.out.print(c.getIcon());
      }
      System.out.println();
    }
  }
}




//(c%2==0 && !otherRow)||(c%2==1 && otherRow)||(c==0)||(c==COL_NUM-1)||(r==0)||(r==ROW_NUM-1)