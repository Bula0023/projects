public class Board {

    // Instance variables
    private Piece[][] board;

    //TODO:
    // Construct an object of type Board using given arguments.
    public Board() {
        board = new Piece[8][8]; // 8x8 chess board
        // Initialize the board with empty pieces
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                board[i][j] = null;
            }
            }
        }




    // Accessor Methods

    //TODO:
    // Return the Piece object stored at a given row and column
    public Piece getPiece(int row, int col) {
        return board[row][col];
    }

    //TODO:
    // Update a single cell of the board to the new piece.
    public void setPiece(int row, int col, Piece piece) {
        board[row][col] = piece;
    }

    // Game functionality methods

    //TODO:
    // Moves a Piece object from one cell in the board to another, provided that
    // this movement is legal. Returns a boolean to signify success or failure.
    // This method calls all necessary helper functions to determine if a move
    // is legal, and to execute the move if it is. Your Game class should not 
    // directly call any other method of this class.
    // Hint: this method should call isMoveLegal() on the starting piece.

    public boolean movePiece(int startRow, int startCol, int endRow, int endCol) { // Game functionality methods
        if (board[startRow][startCol].isMoveLegal(this, endRow, endCol) && board[startRow][startCol] != null) { // checks to make sure move is legal and end position is empty before moving piece
            board[endRow][endCol] = board[startRow][startCol];
            board[startRow][startCol].setPosition(endRow, endCol);
            board[startRow][startCol] = null;
            board[endRow][endCol].setPosition(endRow, endCol);
            return true;
        }
        return false;
    }





    //TODO:
    // Determines whether the game has been ended, i.e., if one player's King
    // has been captured.
    public boolean isGameOver() { //checks to see if game is over based on there being 2 kings still on the board
        int kings = 0;

        for (int i = 0; i <= 7 ; i++) {
            for (int j = 0; j <= 7; j++) {
                Piece currentPiece = board[i][j];
                if (currentPiece != null) {
                    if (currentPiece.toString().equals("\u2654")){
                        kings ++;
                    }
                    if((currentPiece.toString().equals("\u265a"))) {
                        kings ++;
                    }
                }if (kings == 2){
                    return false;
                }
            }
        }return true;
        // If there are two kings, the game is not over
    }

    // Constructs a String that represents the Board object's 2D array.
    // Returns the fully constructed String.
    public String toString() {
        StringBuilder out = new StringBuilder();
        out.append(" ");
        for(int i = 0; i < 8; i++){
            out.append(" ");
            out.append(i);
        }
        out.append('\n');
        for(int i = 0; i < board.length; i++) {
            out.append(i);
            out.append("|");
            for(int j = 0; j < board[0].length; j++) {
                out.append(board[i][j] == null ? "\u2001|" : board[i][j] + "|");
            }
            out.append("\n");
        }
        return out.toString();
    }

    //TODO:
    // Sets every cell of the array to null. For debugging and grading purposes.
        public void clear() {
            // Set every square in the board equal to an empty piece
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    board[i][j] = null;
                }
            }
        }

    // Movement helper functions

    //TODO:
    // Ensure that the player's chosen move is even remotely legal.
    // Returns a boolean to signify whether:
    // - 'start' and 'end' fall within the array's bounds.
    // - 'start' contains a Piece object, i.e., not null.
    // - Player's color and color of 'start' Piece match.
    // - 'end' contains either no Piece or a Piece of the opposite color.
    public boolean verifySourceAndDestination(int startRow, int startCol, int endRow, int endCol, boolean isBlack) {
        if ((startRow > -1) && (startRow < 8) && (startCol > -1) && (startCol < 8) && (endRow > -1) && (endRow < 8) && (endCol > -1) && (endCol < 8)) { //checks if all start and end positions are within board
            if ((getPiece(startRow, startCol) != null) && (board[startRow][startCol].getIsBlack() == isBlack)) { //checks if start position is null and the piece in the start piece and input piece are the same piece
                return (getPiece(endRow, endCol) == null) || (board[endRow][endCol].getIsBlack() != isBlack);
            }
        }
        return false;
    }

    //TODO:
    // Check whether the 'start' position and 'end' position are adjacent to each other
    public boolean verifyAdjacent(int startRow, int startCol, int endRow, int endCol) {
        int rowDiff = Math.abs(startRow - endRow);
        int colDiff = Math.abs(startCol - endCol);
        return (rowDiff <= 1  && colDiff <= 1);
    }

    //TODO:
    // Checks whether a given 'start' and 'end' position are a valid horizontal move.
    // Returns a boolean to signify whether:
    // - The entire move takes place on one row.
    // - All spaces directly between 'start' and 'end' are empty, i.e., null.
    public boolean verifyHorizontal(int startRow, int startCol, int endRow, int endCol) {
        if (startRow != endRow) {
            return false;
        }

        // Check spaces between start and destination for pieces
        int minCol = Math.min(startCol, endCol) + 1;
        int maxCol = Math.max(startCol, endCol);

        for (int col = minCol; col < maxCol; col++) {
            if (board[startRow][col] != null) {
                return false;
            }
        }
        return true;
    }



    //TODO:
    // Checks whether a given 'start' and 'end' position are a valid vertical move.
    // Returns a boolean to signify whether:
    // - The entire move takes place on one column.
    // - All spaces directly between 'start' and 'end' are empty, i.e., null.
    public boolean verifyVertical(int startRow, int startCol, int endRow, int endCol) {
        if (startCol != endCol) {
            return false;
        }

        // Check spaces between start and destination for pieces
        int minRow = Math.min(startRow, endRow) + 1;
        int maxRow = Math.max(startRow, endRow);
        for (int row = minRow; row < maxRow; row++) {
            if (board[row][startCol] != null) {
                return false;
            }
        }
        return true;
    }

    //TODO:
    // Checks whether a given 'start' and 'end' position are a valid diagonal move.
    // Returns a boolean to signify whether:
    // - The path from 'start' to 'end' is diagonal... change in row and col.
    // - All spaces directly between 'start' and 'end' are empty, i.e., null.
    public boolean verifyDiagonal(int startRow, int startCol, int endRow, int endCol) {
        int colChange = startCol - endCol;
        int rowChange = startRow - endRow;

        if (Math.abs(colChange) != Math.abs(rowChange)) {
            return false;
        }
        if (rowChange < 0 && colChange < 0) { // piece moving from left DOWN to right
            for (int i = 1; i < Math.abs(rowChange); i++) {
                if (getPiece(startRow + i, startCol + i) != null)
                    return false;
            }
        }
        if (rowChange > 0 && colChange < 0) { //piece moving from left UP to right
            for (int i = 1; i < Math.abs(rowChange); i++) {
                if (getPiece(startRow - i, startCol + i) != null)
                    return false;
            }
        }
        if (rowChange < 0 && colChange > 0) { //piece moving from right DOWN to left
            for (int i = 1; i < Math.abs(rowChange); i++) {
                if (getPiece(startRow + i, startCol - i) != null)
                    return false;
            }
        }
        if (rowChange > 0 && colChange > 0) { //piece moving from right UP to left
            for (int i = 1; i < Math.abs(rowChange); i++) {
                if (getPiece(startRow - i, startCol - i) != null)
                    return false;
            }
        }
        return true;
    }
}
