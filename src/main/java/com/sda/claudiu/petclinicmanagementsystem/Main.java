package com.sda.claudiu.petclinicmanagementsystem;

import com.sda.claudiu.petclinicmanagementsystem.controller.VeterinarianController;
import com.sda.claudiu.petclinicmanagementsystem.menu.UserOption;
import com.sda.claudiu.petclinicmanagementsystem.repository.VeterinarianRepositoryImpl;
import com.sda.claudiu.petclinicmanagementsystem.service.VeterinarianServiceImpl;
import com.sda.claudiu.petclinicmanagementsystem.service.exceptions.InvalidParameterException;
import com.sda.claudiu.petclinicmanagementsystem.utils.SessionManager;

import java.util.Scanner;


public class Main {
    public static void main(String[] args) throws InvalidParameterException {
        SessionManager.getSessionFactory();
        VeterinarianController veterinarianController= new VeterinarianController(new VeterinarianServiceImpl(new VeterinarianRepositoryImpl()));
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
                    veterinarianController.createVeterinarian();
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

       SessionManager.shutDown();
    }

}
