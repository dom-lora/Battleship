public class Player
{
    private static final int[] SHIP_LENGTHS = {2, 3, 3, 4, 5};
    Grid playerGrid = new Grid();
    Grid opponentGrid = new Grid();
    private Ship[] ships = new Ship[5];
    
    public Player()
    {
        
    }
    
    public void printMyShips()
    {
        playerGrid.printShips();
    }
    
    public void printMyGuesses()
    {
        playerGrid.printStatus();
    }
    
    public void printOpponentShips()
    {
        opponentGrid.printShips();
    }
    
    public void printOpponentGuesses()
    {
        opponentGrid.printStatus();
    }
    
    public void chooseShipLocation(Ship s, int row, int col, int direction, int i)
    {
        s.setLocation(row, col);
        s.setDirection(direction);
        playerGrid.addShip(s);
        ships[i] = s;
    }
    
    public void recordMyGuess(int row, int col)
    {
        if(!playerGrid.alreadyGuessed(row, col))
        {
            
            if(opponentGrid.hasShip(row, col))
            {
                playerGrid.markHit(row, col);
            }
            else
            {
                playerGrid.markMiss(row, col);
            }
        }
    }
    
    public void recordOpponentGuess(int row, int col)
    {
        
        if(!opponentGrid.alreadyGuessed(row, col))
        {
        
            if(playerGrid.hasShip(row, col))
            {
                opponentGrid.markHit(row, col);
            }
            else
            {
                opponentGrid.markMiss(row, col);
            }
        }
    }
    
    public void recordOpponentShips(Ship s, int row, int col, int direction)
    {
        opponentGrid.addShip(s);
    }
    
    public boolean getHits()
    {
        return playerGrid.getHits();
    }
    
    public boolean hasShips(int row, int col)
    {
        return playerGrid.hasShip(row, col);
    }
    
    public boolean beenGuessed(int row, int col)
    {
        return playerGrid.alreadyGuessed(row, col);
    }
}

