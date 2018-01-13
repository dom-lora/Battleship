public class Grid
{
    public static final int NUM_ROWS = 10;
    public static final int NUM_COLS = 10;
    private Location[][] grid = new Location[NUM_ROWS][NUM_COLS];
    int hitCounter = 0;
    
    public Grid()
    {
        for(int i = 0; i < NUM_ROWS; i++)
        {
            for(int j = 0; j < NUM_COLS; j++)
            {
                grid[i][j] = new Location();
            }
        }
    }
    
    public void markHit(int row, int col)
    {
        Location loc = grid[row][col];
        loc.markHit();
        hitCounter++;
    }
    
    public boolean getHits()
    {
        return hitCounter == 17;
    }
    
    public void markMiss(int row, int col)
    {
        Location loc = grid[row][col];
        loc.markMiss();
    }
    
    public void setStatus(int row, int col, int status)
    {
        Location loc = grid[row][col];
        loc.setStatus(status);
    }
    
    public int getStatus(int row, int col)
    {
        Location loc = grid[row][col];
        return loc.getStatus();
    }
    
    public boolean alreadyGuessed(int row, int col)
    {
        Location loc = grid[row][col];
        return !loc.isUnguessed();
    }
    
    public void setShip(int row, int col, boolean val)
    {
        Location loc = grid[row][col];
        loc.setShip(val);
    }
    
    public boolean hasShip(int row, int col)
    {
        Location loc = grid[row][col];
        return loc.hasShip();
    }
    
    public Location get(int row, int col)
    {
        Location loc = grid[row][col];
        return loc;
    }
    
    public int numRows()
    {
        return NUM_ROWS;
    }
    
    public int numCols()
    {
        return NUM_COLS;
    }
    
    public char statusGetter(int row, int col)
    {
        char returnVal = ' ';
        Location loc = get(row, col);
        if(loc.isUnguessed())
        {
            returnVal = '-';
        }
        if(loc.checkHit())
        {
            returnVal = 'X';
        }
        if(loc.checkMiss())
        {
            returnVal = 'O';
        }
        return returnVal;
    }
    
    
    public void printStatus()
    {
        System.out.println(" 1 2 3 4 5 6 7 8 9 10");
        
        for(int i = 0; i < 10; i++)
        {
            char charPrinted = (char) (i + 65);
            System.out.print(charPrinted + " ");
            
            for(int j = 0; j < 10; j++)
            {
                System.out.print(statusGetter(i, j) + " ");
            }
            System.out.println();
        }
    }
    
    public void printShips()
    {
        System.out.println(" 1 2 3 4 5 6 7 8 9 10");
        
        for(int i = 0; i < 10; i++)
        {
            char charPrinted = (char) (i + 65);
            System.out.print(charPrinted + " ");
            
            for(int j = 0; j < 10; j++)
            {
                char printer = '-';
                
                if(hasShip(i, j))
                {
                    printer = 'X';
                }
                System.out.print(printer + " ");
            }
            System.out.println();
        }
    }
    
    public void addShip(Ship s)
    {
        int row =  s.getRow();
        int col = s.getCol();
        int direction = s.getDirection();
        int length = s.getLength();
        
        for(int i = 0; i < length; i++)
        {
            if(direction == 1)
            {
                setShip(row + i, col, true);
            }
            if(direction == 0)
            {
                setShip(row, col + i, true);
            }
        }
    }
}

