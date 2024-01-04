import java.util.Scanner;

public class Piece {
    // Create Instance Variables

    private char character;
    private int row;
    private int col;
    private boolean isBlack;

    /**
     * Constructor.
     *
     * @param character The character representing the piece.
     * @param row       The row on the board the piece occupies.
     * @param col       The column on the board the piece occupies.
     * @param isBlack   The color of the piece.
     */
    public Piece(char character, int row, int col, boolean isBlack) {
        this.character = character;
        this.row = row;
        this.col = col;
        this.isBlack = isBlack;
    }

    /**
     * Determines if moving this piece is legal.
     *
     * @param board  The current state of the board.
     * @param endRow The destination row of the move.
     * @param endCol The destination column of the move.
     * @return If the piece can legally move to the provided destination on the board.
     */
    public boolean isMoveLegal(Board board, int endRow, int endCol) {
        switch (this.character) {
            case '\u2659':
            case '\u265f':
                Pawn pawn = new Pawn(row, col, isBlack);
                return pawn.isMoveLegal(board, endRow, endCol);
            case '\u2656':
            case '\u265c':
                Rook rook = new Rook(row, col, isBlack);
                return rook.isMoveLegal(board, endRow, endCol);
            case '\u265e':
            case '\u2658':
                Knight knight = new Knight(row, col, isBlack);
                return knight.isMoveLegal(board, endRow, endCol);
            case '\u265d':
            case '\u2657':
                Bishop bishop = new Bishop(row, col, isBlack);
                return bishop.isMoveLegal(board, endRow, endCol);
            case '\u265b':
            case '\u2655':
                Queen queen = new Queen(row, col, isBlack);
                return queen.isMoveLegal(board, endRow, endCol);
            case '\u265a':
            case '\u2654':
                King king = new King(row, col, isBlack);
                return king.isMoveLegal(board, endRow, endCol);
            default:
                return false;
        }
    }

    /**
     * Sets the position of the piece.
     *
     * @param row The row to move the piece to.
     * @param col The column to move the piece to.
     */
    public void setPosition(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public char getCharacter() {
        return character;
    }

    /**
     * Return the color of the piece.
     *
     * @return The color of the piece.
     */
    public boolean getIsBlack() {
        return isBlack;
    }

    /**
     * Handle promotion of a pawn.
     *
     * @param row     Current row of the pawn
     * @param isBlack Color of the pawn
     */
    public void promotePawn(int row, boolean isBlack) {

        if (isBlack) {
            if (this.character == '\u265f' && row == 7) { //checks if a pawn has made it to the edge of the board
                while (this.character == '\u265f' && row == 7) {
                    Scanner scanner = new Scanner(System.in);
                    System.out.println("What piece do you choose?:Queen, Bishop, Knight, Rook? ");
                    String input = scanner.nextLine();


                if (input.equals("Queen")) {
                    this.character = '\u265b';
                } else if (input.equals("Rook")) {
                    this.character = '\u265c';
                } else if (input.equals("Bishop")) {
                    this.character = '\u265d';
                } else if (input.equals("Bishop")) {
                    this.character = '\u265e';
                }
            }
        }
    }
        if(!isBlack)

    {
        if (this.character == '\u2659' && row == 0) { //checks if a pawn has made it to the edge of the board
            while (this.character == '\u2659' && row == 0) {
                Scanner scanner = new Scanner(System.in);
                System.out.println("What piece do you choose?:Queen, Bishop, Knight, Rook? ");
                String input = scanner.nextLine();
                if (input.equals("Queen")) {
                    this.character = '\u2655';
                } else if (input.equals("Rook")) {
                    this.character = '\u2656';
                } else if (input.equals("Bishop")) {
                    this.character = '\u2657';
                } else if (input.equals("Bishop")) {
                    this.character = '\u2658';
                }
            }
        }
    }

}

        /**
         * Returns a string representation of the piece.
         * @return  A string representation of the piece.
         */
        public String toString() {if (isBlack) {
            return Character.toString(Character.toLowerCase(character));
        } else {
            return Character.toString(character);
        }
        }


    }


