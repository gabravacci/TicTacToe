import java.util.*;

public class TicTacToe {

    public static void main(String[] args) {
        String player1 = "player";
        String player2 = "cpu";
        Scanner scanner = new Scanner(System.in);
        Random rand = new Random();

        char dash = '\u002D';

        char[][] TicTacToe = {{' ', '|', ' ', '|', ' '},
                          {dash, '+', dash, '+', dash},
                          {' ', '|', ' ', '|', ' '},
                          {dash, '+', dash, '+', dash},
                          {' ', '|', ' ', '|', ' '}};

        GameBoard board = new GameBoard(TicTacToe);

        while(true) {
            System.out.print("Enter where you want to place (1-9, left to right, top to bottom): ");
            int playerPos = scanner.nextInt();
            while(board.posPlayed(playerPos) || playerPos < 1 || playerPos > 9) {
                System.out.println("Please enter valid position.");
                playerPos = scanner.nextInt();
            }
            board.placeOnPosition(playerPos, player1);
            board.printBoardState();
            pause(1000);
            if(board.checkGameStatus()) {
                break;
            }

            System.out.println(); // for clarity on terminal

            int cpuPos = rand.nextInt(9) + 1;
            while(board.posPlayed(cpuPos)) {
                if(!(board.boardMaxed())) {
                    cpuPos = rand.nextInt(9) + 1;
                }
                else {
                    break;
                }
            }
            if(!(board.boardMaxed())) {
                board.placeOnPosition(cpuPos, player2);
                board.printBoardState();
            }
            pause(1000);
            if(board.checkGameStatus()) {
                break;
            }

            System.out.println();
        }
    }

    public static void pause(int ms) {
        try {
            Thread.sleep(ms);
        }
        catch(InterruptedException exception) {
            Thread.currentThread().interrupt();
        }
    }
}
