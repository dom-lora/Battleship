public class Ship
{
    
    public static final int UNSET = -1;
    public static final int HORIZONTAL = 0;
    public static final int VERTICAL = 1;
    
    private int row = -1;
    private int col = -1;
    private int length = -1;
    private int direction = UNSET;
    
    public Ship(int length)
    {
        this.length = length;
        
    }
    
    public boolean isLocationSet()
    {
        return row != -1 && col != -1;
    }
    
    public boolean isDirectionSet()
    {
        return direction != UNSET;
    }
    
    public void setLocation(int row, int col)
    {
        this.row = row;
        this.col = col;
    }
    
    public void setDirection(int direction)
    {
        this.direction = direction;
    }
    
    public int getRow()
    {
        return row;
    }
    
    public int getCol()
    {
        return col;
    }
    
    public int getLength()
    {
        return length;
    }
    
    public int getDirection()
    {
        return direction;
    }
    
    private String directionToString()
    {
        String str = "";
        if(direction == VERTICAL)
        {
            str = "vertical";
        }
        if(direction == HORIZONTAL)
        {
            str = "horizontal";
        }
        if(direction == UNSET)
        {
            str = "unset direction";
        }
        return str;
    }
    
    private String locationToString()
    {
        String str = "";
        if(isLocationSet())
        {
            str = "(" + row + ", " + col + ")";
        }
        else
        {
            str = "(unset location)";
        }
        return str;
    }
    
    public String toString()
    {
        String str = directionToString() + " ship of length " + length + " at " + locationToString();
        return str;
    }
}

