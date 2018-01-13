import java.util.Scanner;

public class Battleship
{
    private static final int[] SHIP_LENGTHS = {2, 3, 3, 4, 5};
    static Player humanPlayer = new Player();
    static Player computerPlayer = new Player();
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        //Place Ships
        placeShips();
        computerPlaceShips();
        playGame();
        //computerPlayer.printMyShips();
    }

    public static void playGame() {

        while(true)
        {
            askForGuess();
            computerPlay();

            //break when all ships are sunk
            if(humanPlayer.getHits())
            {
                endScreen(0);
                break;
            }
            else if(computerPlayer.getHits())
            {
                endScreen(1);
                break;
            }

        }
    }

    public static void endScreen(int winner)
    {
        if(winner == 0)
        {
            System.out.println("YOU WIN!");
        }
        if(winner == 1)
        {
            System.out.println("YOU LOSE :(");
        }
    }

    public static void placeShips()
    {
        System.out.println("BATTLESHIP");
        humanPlayer.printMyShips();

        for(int i = 0; i < SHIP_LENGTHS.length; i++)
        {
            Ship ship1 = new Ship(SHIP_LENGTHS[i]);
            String rowChar;
            int row;
            int col;
            String directionStr;
            int direction = -1;

            while(true)
            {
                System.out.println("Place " + shipName(i) + " (length of " + SHIP_LENGTHS[i] + ") at Row (A-J): ");
                rowChar = scanner.nextLine();
                row = (int) rowChar.charAt(0);
                row += -65;
                System.out.println("Place " + shipName(i) + " (length of " + SHIP_LENGTHS[i] + ") at Column (1-10): ");
                col = scanner.nextInt();
                col -= 1;
                scanner.nextLine();
                System.out.println("Place " + shipName(i) + " (length of " + SHIP_LENGTHS[i] + ") 'horizontal' or 'vertical': ");
                directionStr = scanner.nextLine();
                if(directionStr.equals("vert") || directionStr.equals("vertical") || directionStr.equals("v"))
                {
                    direction = 1;
                }
                if(directionStr.equals("hori") || directionStr.equals("horizontal") || directionStr.equals("h"))
                {
                    direction = 0;
                }
                if(checkCoordinates(row, col, direction, SHIP_LENGTHS[i], humanPlayer))
                {
                    break;
                }
                else
                {
                    System.out.println("Invalid Placement. Try again");
                }
            }

            humanPlayer.chooseShipLocation(ship1, row, col, direction, i);
            computerPlayer.recordOpponentShips(ship1, row, col, direction);
            humanPlayer.printMyShips();
        }
    }

    public static String shipName(int a)
    {
        String returnVal = "";

        if(a == 0)
        {
            returnVal = "Patrol Ship";
        }
        if(a == 1)
        {
            returnVal = "Submarine";
        }
        if(a == 2)
        {
            returnVal = "Destroyer";
        }
        if(a == 3)
        {
            returnVal = "Battleship";
        }
        if(a == 4)
        {
            returnVal = "Aircraft Carrier";
        }

        return returnVal;
    }

    public static void computerPlaceShips()
    {
        for(int i = 0; i < SHIP_LENGTHS.length; i++)
        {
            Ship ship1 = new Ship(SHIP_LENGTHS[i]);
            int row;
            int col;
            int direction;

            while(true)
            {

                row = Randomizer.nextInt(0, 9);
                col = Randomizer.nextInt(0, 9);
                direction = Randomizer.nextInt(0, 1);

                if(checkCoordinates(row, col, direction, SHIP_LENGTHS[i], computerPlayer))
                {
                    break;
                }
            }

            computerPlayer.chooseShipLocation(ship1, row, col, direction, i);
            humanPlayer.recordOpponentShips(ship1, row, col, direction);
        }
    }


    public static void askForGuess()
    {
        String rowStr;
        int row;
        int col;

        while(true)
        {
            System.out.println("Enter Row Guess (A-J): ");
            rowStr = scanner.nextLine();
            row = (int) rowStr.charAt(0);
            row += -65;
            System.out.println("Enter Column Guess (1-10): ");
            col = scanner.nextInt();
            scanner.nextLine();
            col -= 1;


            if(checkGuess(row, col, humanPlayer))
            {
                break;
            }
            else
            {
                System.out.println("Invalid Guess. Try again");
            }
        }
        humanPlayer.recordMyGuess(row, col);
        humanPlayer.printMyGuesses();

    }

    //uses randomizer to have computer guess
    public static void computerPlay()
    {

        System.out.println("Computer's Guess");
        int row;
        int col;

        while(true)
        {
            row = Randomizer.nextInt(0, 9);
            col = Randomizer.nextInt(0, 9);

            if(checkGuess(row, col, computerPlayer))
            {
                break;
            }
        }

        computerPlayer.recordMyGuess(row, col);
        computerPlayer.printMyGuesses();
    }

    //Checks coordinates for placing ships
    public static boolean checkCoordinates(int row, int col, int direction, int length, Player p)
    {
        boolean shipsPresent = false;
        boolean shipsFit = false;

        if(direction == 1)
        {
            if(row + length <= 10)
            {
                shipsFit = true;
            }
        }
        else if(direction == 0)
        {
            if(col + length <= 10)
            {
                shipsFit = true;
            }
        }

        if(shipsFit)
        {
            for(int i = 0; i < length; i++)
            {
                if(direction == 1)
                {
                    if(p.hasShips(row + i, col))
                    {
                        shipsPresent = true;
                    }
                }
                if(direction == 0)
                {
                    if(p.hasShips(row, col + i))
                    {
                        shipsPresent = true;
                    }
                }
            }
        }



        return !shipsPresent && shipsFit;
    }

    //Checks coordinates for guesses
    public static boolean checkGuess(int row, int col, Player p)
    {
        return row < 10 && col < 10 && !p.beenGuessed(row, col);
    }
}

