package com.sda.claudiu.petclinicmanagementsystem;

import com.sda.claudiu.petclinicmanagementsystem.menu.UserOption;
import com.sda.claudiu.petclinicmanagementsystem.utils.SessionManager;

import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        SessionManager.getSessionFactory();

        Scanner scanner = new Scanner(System.in);

        UserOption userOption = UserOption.UNKNOWN;
        do {
            UserOption.printAllOptions();   // we print the options on the screen
            System.out.println("Please insert an option:");
            try {
                int numberGiven = Integer.parseInt(scanner.nextLine());
                userOption = UserOption.findUserOption(numberGiven);
            } catch (NumberFormatException n) {
                n.printStackTrace();
                userOption = UserOption.UNKNOWN;
            }

            switch (userOption) {
                case CREATE_VETERINARIAN:
                    System.out.println("Not implemented!");
                    break;
                case EXIT:
                    System.out.println("Good bye!");
                    break;
                case UNKNOWN:
                    System.out.println("An unknown option!");
                default:
                    System.out.println("User option " + userOption + " not implemented!");
                    break;
            }
        } while (userOption != UserOption.EXIT);

       // SessionManager.shutDown();
    }

}
