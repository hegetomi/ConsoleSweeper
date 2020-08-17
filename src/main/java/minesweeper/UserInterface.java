package minesweeper;

import java.util.Scanner;

public class UserInterface {

    private final Scanner scan;

    public UserInterface() {

        this.scan = new Scanner(System.in);


    }

    public void start() {
        System.out.print("How many mines do you want on the field? ");
        int numOfBombs = scan.nextInt();
        System.out.println();

        PlayField field = new PlayField(9, numOfBombs);

        field.printField();

        mainLoop:
        while (true) {
            if (!field.checkIfWon()) {
                System.out.println("Congratulations! You found all mines!");
                break;
            }
            boolean isValidEntry = false;

            while (!isValidEntry) {
                System.out.print("Set/unset mines marks or claim a cell as free: ");
                int coordY = scan.nextInt() - 1;
                int coordX = scan.nextInt() - 1;
                String command = scan.next();
                int result = field.translateCommand(command, coordX, coordY);
                switch (result) {
                    case -1:
                        field.printField();
                        System.out.println("You stepped on a mine and failed!");
                        break mainLoop;
                    case 1:
                        isValidEntry = true;
                        break;
                    case 4:
                        System.out.println("There is a number here!");
                        break;
                    case 6:
                        System.out.println("Invalid command");
                }
            }
            System.out.println();
            field.printField();

        }

    }
}