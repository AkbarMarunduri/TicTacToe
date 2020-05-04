import java.util.*;

public class TicTacToe {
    static ArrayList<Integer> playerPosisions = new ArrayList<Integer>();
    static ArrayList<Integer> cpuPosisions = new ArrayList<Integer>();
    
    public static void main(String[] args) {
        char[][] gameBoard =
                       {{' ', '|', ' ', '|', ' '},
                        {'-', '+', '-', '+', '-'},
                        {' ', '|', ' ', '|', ' '},
                        {'-', '+', '-', '+', '-'},
                        {' ', '|', ' ', '|', ' '}};

        printGameBoard(gameBoard);
        Scanner scanner = new Scanner(System.in);

        while (true) {
            // input dari player
            System.out.println(" _________");
            System.out.println("| Player  |--------------------------------+");
            System.out.println(" =>Pilih kolom anda range 1-9");
            System.out.print(" =>Masukkan pilihan anda : ");
            int playerPos = scanner.nextInt();
            System.out.println(" =>Player memilih kolom no : " + playerPos + "");

            while (playerPosisions.contains(playerPos) || cpuPosisions.contains(playerPos) || playerPos > 9) {
                System.out.println(" =>Maaf!!.. kolom " + playerPos + " sudah terisi!!");
                System.out.print(" =>Pilih ulang kolom : ");
                playerPos = scanner.nextInt();
                System.out.println(" =>Player memilih kolom no : " + playerPos);
            }
            System.out.println("+------------------------------------------+");

            isiKolomGameBoard(gameBoard, playerPos, "player");

            String result = checkWinner();
            if (result.length() > 0) {
                System.out.println("");
                printGameBoard(gameBoard);
                System.out.println("");
                System.out.println(result);
                break;
            }

            //input dapi computer
            System.out.println("");
            System.out.println(" _________");
            System.out.println("| CPU     |--------------------------------+");
            Random random = new Random();
            int cpuPos = random.nextInt(9) + 1;
            while (cpuPosisions.contains(cpuPos) || playerPosisions.contains(cpuPos)) {
                cpuPos = random.nextInt(9) + 1;
            }
            System.out.println(" =>CPU memilih kolom no : " + cpuPos);
            System.out.println("+------------------------------------------+");
            System.out.println("");
            isiKolomGameBoard(gameBoard, cpuPos, "cpu");
            printGameBoard(gameBoard);

            result = checkWinner();
            if (result.length() > 0) {
                System.out.println(result);
                break;
            }
            System.out.println(result);
        }
    }

    private static void printGameBoard(char[][] gameBoard) {
        for (char[] row : gameBoard) {
            for (char d : row) {
                System.out.print(d);
            }
            System.out.println("                   ");
        }
    }

    public static void isiKolomGameBoard(char[][] gameBoard, int pos, String user) {

        char simbol = ' ';
        if (user.equals("player")) {
            simbol = 'X';
            playerPosisions.add(pos);
        } else if (user.equals("cpu")) {
            simbol = 'O';
            cpuPosisions.add(pos);
        }

        switch (pos) {
            case 1:
                gameBoard[0][0] = simbol;
                break;
            case 2:
                gameBoard[0][2] = simbol;
                break;
            case 3:
                gameBoard[0][4] = simbol;
                break;
            case 4:
                gameBoard[2][0] = simbol;
                break;
            case 5:
                gameBoard[2][2] = simbol;
                break;
            case 6:
                gameBoard[2][4] = simbol;
                break;
            case 7:
                gameBoard[4][0] = simbol;
                break;
            case 8:
                gameBoard[4][2] = simbol;
                break;
            case 9:
                gameBoard[4][4] = simbol;
                break;
        }

    }

    public static String checkWinner() {
        List topRow = Arrays.asList(1, 2, 3);
        List midRow = Arrays.asList(4, 5, 6);
        List botRow = Arrays.asList(7, 8, 9);
        List lefCou = Arrays.asList(1, 4, 7);
        List midCou = Arrays.asList(2, 5, 8);
        List rigCou = Arrays.asList(3, 6, 9);
        List diaRo1 = Arrays.asList(1, 5, 9);
        List diaRo2 = Arrays.asList(3, 5, 7);

        List<List> winning = new ArrayList<List>();
        winning.add(topRow);
        winning.add(midRow);
        winning.add(botRow);
        winning.add(lefCou);
        winning.add(midCou);
        winning.add(rigCou);
        winning.add(diaRo1);
        winning.add(diaRo2);

        for (List w : winning) {
            if (playerPosisions.containsAll(w)) {
                return "Congratulattion you Winn";
            } else if (cpuPosisions.containsAll(w)) {
                return "CPU Winn, You Lose....";
            } else if (playerPosisions.size() + cpuPosisions.size() == 9) {
                return "CAT";
            }
        }
        return "";
    }
}

