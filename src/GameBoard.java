import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GameBoard {
    private char[][] board;
    private ArrayList<Integer> playerPositions = new ArrayList<>();
    private ArrayList<Integer> cpuPositions = new ArrayList<>();


    public GameBoard(char[][] board) {
        this.board = board;
    }

    public void printBoardState() {
        for(int i = 0; i < this.board.length; i++) {
            for(int j = 0; j < 5; j++) {
                System.out.print(this.board[i][j]);
            }
            System.out.println();
        }
    }

    public void placeOnPosition(int position, String user) {
        char symbol;

        if(user.equals("player")) {
            symbol = 'X';
            playerPositions.add(position);
        }
        else {
            symbol = 'O';
            cpuPositions.add(position);
        }

        switch (position) {
            case 1 -> this.board[0][0] = symbol;
            case 2 -> this.board[0][2] = symbol;
            case 3 -> this.board[0][4] = symbol;
            case 4 -> this.board[2][0] = symbol;
            case 5 -> this.board[2][2] = symbol;
            case 6 -> this.board[2][4] = symbol;
            case 7 -> this.board[4][0] = symbol;
            case 8 -> this.board[4][2] = symbol;
            case 9 -> this.board[4][4] = symbol;
            //default -> throw new IllegalStateException("Unexpected value: " + position);
        }
    }

    public boolean checkGameStatus() {
        List<Integer> topRow = Arrays.asList(1,2,3);
        List<Integer> midRow = Arrays.asList(4,5,6);
        List<Integer> botRow = Arrays.asList(7,8,9);
        List<Integer> leftCol = Arrays.asList(1,4,7);
        List<Integer> midCol = Arrays.asList(2,5,8);
        List<Integer> rightCol = Arrays.asList(3,6,9);
        List<Integer> diag1 = Arrays.asList(1,5,9);
        List<Integer> diag2 = Arrays.asList(3,5,7);

        List<List<Integer>> winCons = new ArrayList<>(Arrays.asList(topRow, midRow, botRow, leftCol, midCol, rightCol, diag1, diag2));

        for(List<Integer> l : winCons) {
            if(playerPositions.containsAll(l)) {
                System.out.println("You win!");
                return true;
            }
            else if(cpuPositions.containsAll(l)) {
                System.out.println("You lose :(");
                return true;
            }
            else if(playerPositions.size() + cpuPositions.size() == 9) {
                /*System.out.println(playerPositions);
                System.out.println(cpuPositions);
                System.out.println(winCons);*/
                System.out.println("You tied");
                return true;
            }
        }
        return false;
    }

    public boolean playerPosPlayed(int playerPos) {
        if(playerPositions.contains(playerPos)) {
            return true;
        }
        else return false;
    }

    public boolean cpuPosPlayed(int cpuPos) {
        if(cpuPositions.contains(cpuPos)) {
            return true;
        }
        else return false;
    }

    public boolean posPlayed(int pos) {
        if(playerPositions.contains(pos) || cpuPositions.contains(pos)) {
            return true;
        }
        else return false;
    }

    public boolean boardMaxed() {
        if(playerPositions.size() + cpuPositions.size() == 9) {
            return true;
        }
        else return false;
    }
}
