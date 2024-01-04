import java.util.Scanner;
public class Game {

    public static void main(String[] args) {
        Board chessBoard = new Board();
        Fen.load("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR", chessBoard);
        Scanner scanner = new Scanner(System.in);

        boolean isBlack = false;
        boolean legal = true;
        System.out.println("Welcome to Chess!");
        System.out.println("Enter your moves in the format: startRow startCol endRow endCol");
        System.out.println("For example, to move a piece from (1, 2) to (3, 4), enter: 1 2 3 4");
        System.out.print(chessBoard);



        while (!chessBoard.isGameOver() && legal) {
            if (!isBlack){
                System.out.println("White turn ");
            } else if (isBlack){
                System.out.print("Black turn: ");
            }

                System.out.println("Enter your move: ");
                int startRow = scanner.nextInt();
                int startCol = scanner.nextInt();
                int endRow = scanner.nextInt();
                int endCol = scanner.nextInt();



                if (isBlack && chessBoard.verifySourceAndDestination(startRow, startCol, endRow, endCol, true) ||
                        !isBlack && chessBoard.verifySourceAndDestination(startRow, startCol, endRow, endCol, false)) {
                    System.out.println(chessBoard.getPiece(startRow, startCol));
                    if (chessBoard.getPiece(startRow, startCol) != null && chessBoard.getPiece(startRow, startCol).getIsBlack() == isBlack && chessBoard.movePiece(startRow, startCol, endRow, endCol)) {
                        chessBoard.getPiece(endRow, endCol).promotePawn(endRow, isBlack);
                        isBlack = !isBlack; // Toggle turn between black and white
                        System.out.println(chessBoard);

                    } else {
                        System.out.println(chessBoard);
                        System.out.println("Invalid Move Try Again");
                    }
                } else {
                    System.out.println(chessBoard);
                    System.out.println("Invalid Move Try Again");
                }
            }
        if (chessBoard.isGameOver()){
            if (!isBlack){
                System.out.println("Black won");
            }
            else if (isBlack){
                System.out.println("White won");
            }

        }

            // Check for game over condition and decide the winner
            // Insert your game over logic here...
        scanner.close();
        }

    }


