import java.util.Queue;
import java.util.Random;

public class Minefield {
    /**
    Global Section
    */
    public static final String ANSI_YELLOW_BRIGHT = "\u001B[33;1m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE_BRIGHT = "\u001b[34;1m";
    public static final String ANSI_BLUE = "\u001b[34m";
    public static final String ANSI_RED_BRIGHT = "\u001b[31;1m";
    public static final String ANSI_RED = "\u001b[31m";
    public static final String ANSI_GREEN = "\u001b[32m";
    public static final String ANSI_PURPLE = "\u001b[35m";
    public static final String ANSI_CYAN = "\u001b[36m";
    public static final String ANSI_WHITE_BACKGROUND = "\u001b[47m";
    public static final String ANSI_PURPLE_BACKGROUND = "\u001b[45m";
    public static final String ANSI_GREY_BACKGROUND = "\u001b[0m";

    /* 
     * Class Variable Section
     * 
    */

    /*Things to Note:
     * Please review ALL files given before attempting to write these functions.
     * Understand the Cell.java class to know what object our array contains and what methods you can utilize
     * Understand the StackGen.java class to know what type of stack you will be working with and methods you can utilize
     * Understand the QGen.java class to know what type of queue you will be working with and methods you can utilize
     */
    private Cell[][] field;
    private int flags;
    private int rows;
    private int columns;

    
    /**
     * Minefield
     * 
     * Build a 2-d Cell array representing your minefield.
     * Constructor
     * @param rows       Number of rows.
     * @param columns    Number of columns.
     * @param flags      Number of flags, should be equal to mines
     */
    public Minefield(int rows, int columns, int flags) {
        this.flags = flags;
        this.rows = rows;
        this.columns = columns;
        field = new Cell[rows][columns];
//        builds the minefield
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                field[i][j] = new Cell(false, "0");
            }
        }
    }



    /**
     * evaluateField
     * 
     *
     * @function:
     * Evaluate entire array.
     * When a mine is found check the surrounding adjacent tiles. If another mine is found during this check, increment adjacent cells status by 1.
     * 
     */
    public void evaluateField() {
        for(int i = 0; i < rows; i++){
            for(int j = 0; j < columns; j++){

                if (field[i][j].getStatus().equals("M")){// Check if the current cell contains a mine
                    // Check the right cell
                    if (j + 1 > field.length && !field[i][j + 1].getStatus().equals("M")) {
                        int currentStatus = Integer.parseInt(field[i][j + 1].getStatus());
                        currentStatus++;
                        field[i][j + 1].setStatus(Integer.toString(currentStatus));
                    }
                    // Check the left cell
                    if (j - 1 > 0  && !field[i][j - 1].getStatus().equals("M")) {
                        int currentStatus = Integer.parseInt(field[i][j - 1].getStatus());
                        currentStatus++;
                        field[i][j - 1].setStatus(Integer.toString(currentStatus));
                    }
                    // Check the above cell
                    if (i + 1 < field.length  && !field[i + 1][j].getStatus().equals("M")) {
                        int currentStatus = Integer.parseInt(field[i + 1][j].getStatus());
                        currentStatus++;
                        field[i + 1][j].setStatus(Integer.toString(currentStatus));
                    }
                    // Check the below cell
                    if (i - 1 > 0  && !field[i - 1][j].getStatus().equals("M")) {
                        int currentStatus = Integer.parseInt(field[i -1][j].getStatus());
                        currentStatus++;
                        field[i -1][j].setStatus(Integer.toString(currentStatus));
                    }
                    // check top right cell
                    if (i + 1 < field.length && j + 1 < field.length  && !field[i + 1][j + 1].getStatus().equals("M")) {
                        int currentStatus = Integer.parseInt(field[i + 1][j + 1].getStatus());
                        currentStatus++;
                        field[i + 1][j + 1].setStatus(Integer.toString(currentStatus));
                    }
//                    check top left cell
                    if (i + 1 < field.length && j - 1 > 0  && !field[i + 1][j - 1].getStatus().equals("M")) {
                        int currentStatus = Integer.parseInt(field[i + 1][j - 1].getStatus());
                        currentStatus++;
                        field[i + 1][j - 1].setStatus(Integer.toString(currentStatus));
                    }
//                    check bottom right cell
                    if (i - 1 > 0 && j + 1 < field.length  && !field[i - 1][j + 1].getStatus().equals("M")) {
                        int currentStatus = Integer.parseInt(field[i - 1][j + 1].getStatus());
                        currentStatus++;
                        field[i - 1][j + 1].setStatus(Integer.toString(currentStatus));
                    }
                    // Check the bottom left cell
                    if (i - 1 > 0 && j - 1 > 0  && !field[i - 1][j - 1].getStatus().equals("M")) {
                        int currentStatus = Integer.parseInt(field[i - 1][j - 1].getStatus());
                        currentStatus++;
                        field[i - 1][j - 1].setStatus(Integer.toString(currentStatus));
                    }
                }
            }
        }
    }


    /**
     * createMines
     * 
     * Randomly generate coordinates for possible mine locations.
     * If the coordinate has not already been generated and is not equal to the starting cell set the cell to be a mine.
     * utilize rand.nextInt()
     * 
     * @param x       Start x, avoid placing on this square.
     * @param y        Start y, avoid placing on this square.
     * @param mines      Number of mines to place.
     */
    public void createMines(int x, int y, int mines) {
        Random rand = new Random();
        int rows = field.length;
        int columns = field[0].length;
// Loop to place the specified number of mines
        for (int i = 0; i < mines;) {
            // Generates random coordinates for a mine
            int mineX = rand.nextInt(rows);
            int mineY = rand.nextInt(columns);
            // Checks conditions before placing a mine: if it doesnt equal m,or f, or the orginal coordinates inputed
            if (!field[mineX][mineY].getStatus().equals("M") && !field[mineX][mineY].getStatus().equals("F") && (field[mineX][mineY] != field[x][y])) {
                // Place a mine at the selected coordinates
                field[mineX][mineY].setStatus("M");
                // Counts for placed mines
                i++;
            }
        }
    }

    /**
     * guess
     * 
     * Check if the guessed cell is inbounds (if not done in the Main class). 
     * Either place a flag on the designated cell if the flag boolean is true or clear it.
     * If the cell has a 0 call the revealZeroes() method or if the cell has a mine end the game.
     * At the end reveal the cell to the user.
     * 
     * 
     * @param x       The x value the user entered.
     * @param y       The y value the user entered.
     * @param flag    A boolean value that allows the user to place a flag on the corresponding square.
     * @return boolean Return false if guess did not hit mine or if flag was placed, true if mine found.
     */
    public boolean guess(int x, int y, boolean flag) {
        // Check if the guessed cell is out of bounds
        if (x < 0 || x >= field.length || y < 0 || y >= field[0].length) {
            return false;
        }


        Cell select = field[x][y];
        // If the flag is true see if you can put it in the cell
        if (flag == true) {
            // Check if flags are available and the cell is not already flagged
            if(flags > 0 && !select.getStatus().equals("F"))  {
                // Place a flag on the cell, reveal it, and lower flag count
                select.setStatus("F");
                select.setRevealed(true);
                flags--;
                return false;
                // Return false since no mine is hit
            }
        } else {
            // If the cell contains a mine, reveal it and return true for game over
            if (select.getStatus().equals("M")) {
                select.setRevealed(true);
                return true;
            } else if (select.getStatus().equals("0")) {
                // If the cell has a 0, call the revealZeroes() method
                revealZeroes(x, y);
            }
            else{
                // If the cell has a number 1 to 5, reveal it
                select.setRevealed(true);
            }
            return false;
        }
        return false;
    }

    /**
     * gameOver
     * 
     * Ways a game of Minesweeper ends:
     * 1. player guesses a cell with a mine: game over -> player loses
     * 2. player has revealed the last cell without revealing any mines -> player wins
     * 
     * @return boolean Return false if game is not over and squares have yet to be revealed, otheriwse return true.
     */
    public boolean gameOver() {
        // Loops through all cells in the field
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[0].length; j++) {
                // Gets the current cell
                Cell currentCell = field[i][j];

                // Check if the current cell is not revealed and does not contain a mine
                if (!currentCell.getRevealed() && !currentCell.getStatus().equals("M")) {
                    return false; // If there is at least unrevealed non-mine cell, the game is not over
                }
            }
        }

        return true; // If all non-mine cells have been revealed, the game is over
    }

    /**
     * Reveal the cells that contain zeroes that surround the inputted cell.
     * Continue revealing 0-cells in every direction until no more 0-cells are found in any direction.
     * Utilize a STACK to accomplish this.
     *
     * This method should follow the psuedocode given in the lab writeup.
     * Why might a stack be useful here rather than a queue?
     *
     * @param x      The x value the user entered.
     * @param y      The y value the user entered.
     */
    public void revealZeroes(int x, int y) {
        Stack1Gen<int[]> t = new Stack1Gen<>();
        // Push the coordinates onto the stack
        t.push(new int[]{x,y});
//         Continue the loop until the stack is empty
        while (!t.isEmpty()){
            int[] cord = t.pop();
            int newX = cord[0];
            int newY = cord[1];


            field[newX][newY].setRevealed(true);
            // Check and push the adjacent cells with a status of "0" onto the stack for them to be processed
            if (newX + 1 < field.length && !field[newX + 1][newY].getRevealed() && field[newX + 1][newY].getStatus().equals("0")) {
                t.push(new int[]{newX + 1, newY});
            }

            if (newX - 1 >= 0 && !field[newX - 1][newY].getRevealed() && field[newX - 1][newY].getStatus().equals("0")) {
                t.push(new int[]{newX - 1, newY});
            }

            if (newY + 1 < field[0].length && !field[newX][newY + 1].getRevealed() && field[newX][newY + 1].getStatus().equals("0")) {
                t.push(new int[]{newX, newY + 1});
            }

            if (newY - 1 >= 0 && !field[newX][newY - 1].getRevealed() && field[newX][newY - 1].getStatus().equals("0")) {
                t.push(new int[]{newX, newY - 1});
            }

        }

    }


    /**
     * revealStartingArea
     *
     * On the starting move only reveal the neighboring cells of the inital cell and continue revealing the surrounding concealed cells until a mine is found.
     * Utilize a QUEUE to accomplish this.
     * 
     * This method should follow the psuedocode given in the lab writeup.
     * Why might a queue be useful for this function?
     *
     * @param x     The x value the user entered.
     * @param y     The y value the user entered.
     */
    public void revealStartingArea(int x, int y) {
        Q1Gen<int[]> q = new Q1Gen<>();
        q.add(new int[]{x,y});
        while (q.length() > 0){
            int[] cord = q.remove();
            int newX = cord[0];
            int newY = cord[1];
            // Set the current cell as revealed
            field[newX][newY].setRevealed(true);

            // Break the loop if a mine is encountered
            if (field[newX][newY].getStatus().equals("M")){
                break;
            }
            // Add neighboring diagonal cells to the queue for further processing
            if (newX - 1 > 0 && newX - 1 < field.length && !field[newX - 1][newY].getRevealed()){
                q.add(new int[] {newX - 1, newY});

            }

            if (newX + 1 > 0 && newX + 1 < field.length && !field[newX + 1][newY].getRevealed()){
                q.add(new int[] {newX + 1, newY});
            }

            if (newY + 1 > 0 && newY + 1 < field.length && !field[newX][newY + 1].getRevealed()){
                q.add(new int[] {newX, newY + 1});
            }

            if (newY - 1 > 0 && newY - 1 < field.length && !field[newX][newY - 1].getRevealed()){
                q.add(new int[] {newX, newY - 1});
            }



        }


    }

    /**
     * For both printing methods utilize the ANSI colour codes provided! 
     * 
     * 
     * 
     * 
     * 
     * debug
     *
     * @function This method should print the entire minefield, regardless if the user has guessed a square.
     * *This method should print out when debug mode has been selected. 
     */
    public void debug() {
        StringBuilder out = new StringBuilder();
        out.append(" ");
        // Iterate through rows to make you field
        for (int i = 0; i < rows; i++) {
            out.append(" ");
            out.append(i);
        }
        out.append('\n');
        // Iterate through columns to make your field
        for (int i = 0; i < field.length; i++) {
            out.append(i);
            out.append("|");
            for (int j = 0; j < field[0].length; j++) {
                out.append("\u2001");

                //these statements set the color of the numbers, mines, and flags on the field
                if (field[i][j].getStatus().equals("M")){
                    out.append(ANSI_RED + field[i][j].getStatus() + ANSI_GREY_BACKGROUND);
                }
                if (field[i][j].getStatus().equals("F")){
                    out.append(ANSI_PURPLE + field[i][j].getStatus()+ ANSI_GREY_BACKGROUND);
                }
                if (field[i][j].getStatus().equals("0")){
                    out.append(ANSI_BLUE + field[i][j].getStatus() + ANSI_GREY_BACKGROUND);
                }
                if (field[i][j].getStatus().equals("1")){
                    out.append(ANSI_YELLOW + field[i][j].getStatus() + ANSI_GREY_BACKGROUND);
                }
                if (field[i][j].getStatus().equals("2")){
                    out.append(ANSI_CYAN + field[i][j].getStatus() + ANSI_GREY_BACKGROUND);
                }
                if (field[i][j].getStatus().equals("3")){
                    out.append(ANSI_GREEN + field[i][j].getStatus() + ANSI_GREY_BACKGROUND);
                }
                if (field[i][j].getStatus().equals("4")){
                    out.append(ANSI_YELLOW_BRIGHT + field[i][j].getStatus() + ANSI_GREY_BACKGROUND);
                }
                if (field[i][j].getStatus().equals("5")){
                    out.append(ANSI_BLUE_BRIGHT + field[i][j].getStatus() + ANSI_GREY_BACKGROUND);
                }



            }
            out.append("\n");
        }

        System.out.println(out);
    }


        /**
         * toString
         *
         * @return String The string that is returned only has the squares that has been revealed to the user or that the user has guessed.
         */
        public String toString () {
            StringBuilder out = new StringBuilder();
            out.append(" ");
            for (int i = 0; i < rows; i++) {
                out.append(" ");
                out.append(i);
            }
            out.append('\n');
            // Iterate through rows to make you field
            for (int i = 0; i < field.length; i++) {
                out.append(i);
                out.append("|");
                // Iterate through columnss to make you field
                for (int j = 0; j < field[0].length; j++) {
                    out.append("\u2001");
//                  While a dash isn't picked the number won't be revealed and still be a dash
                    if (!field[i][j].getRevealed()){
                        out.append("-");
                    }
                    // Set the color of the revealed numbers, mines, and flags on the field so when it is picked it with show on the field
                    if (field[i][j].getRevealed() && field[i][j].getStatus().equals("M")){
                        out.append(ANSI_RED + field[i][j].getStatus() + ANSI_GREY_BACKGROUND);
                    }
                    if (field[i][j].getRevealed() && field[i][j].getStatus().equals("0")){
                        out.append(ANSI_BLUE + field[i][j].getStatus() + ANSI_GREY_BACKGROUND);
                    }
                    if (field[i][j].getRevealed() && field[i][j].getStatus().equals("1")){
                        out.append(ANSI_YELLOW + field[i][j].getStatus() + ANSI_GREY_BACKGROUND);
                    }
                    if (field[i][j].getRevealed() && field[i][j].getStatus().equals("2")){
                        out.append(ANSI_CYAN + field[i][j].getStatus() + ANSI_GREY_BACKGROUND);
                    }
                    if (field[i][j].getRevealed() && field[i][j].getStatus().equals("3")){
                        out.append(ANSI_GREEN + field[i][j].getStatus() + ANSI_GREY_BACKGROUND);
                    }
                    if (field[i][j].getRevealed() && field[i][j].getStatus().equals("4")){
                        out.append(ANSI_YELLOW_BRIGHT + field[i][j].getStatus() + ANSI_GREY_BACKGROUND);
                    }
                    if (field[i][j].getRevealed() && field[i][j].getStatus().equals("5")){
                        out.append(ANSI_BLUE_BRIGHT + field[i][j].getStatus() + ANSI_GREY_BACKGROUND);
                    }
                    if (field[i][j].getRevealed() && field[i][j].getStatus().equals("F")){
                        out.append(ANSI_PURPLE + field[i][j].getStatus() + ANSI_GREY_BACKGROUND);
                    }


                }
                out.append("\n");
            }
            return out.toString();

        }

        public int getFlags() {
            return this.flags;
        }

}
