package com.sda.claudiu.petclinicmanagementsystem.controller;

import com.sda.claudiu.petclinicmanagementsystem.service.PetService;
import com.sda.claudiu.petclinicmanagementsystem.service.exceptions.EntityNotFoundException;
import com.sda.claudiu.petclinicmanagementsystem.service.exceptions.InvalidParameterException;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class PetController {
    private final PetService petService;

    private final Scanner scanner = new Scanner(System.in);
    // private final String dateFormat = "dd/MM/yyyy";

    public PetController(PetService petService) {
        this.petService = petService;
    }

    public void createPet() {
        try {
            System.out.println("Please insert a race: ");
            String race = scanner.nextLine();

            System.out.println("Please insert a birthdate: ");
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM-dd-yyyy");
            String string = scanner.nextLine();
            Date birthdate = simpleDateFormat.parse(string);

            System.out.println("Please insert if is vaccinated: ");
            Boolean isVaccinated = Boolean.valueOf(scanner.nextLine());
            System.out.println("Please insert an owner name: ");
            String ownerName = scanner.nextLine();
            System.out.println("Please insert a vet id: ");
            int vetId = Integer.parseInt(scanner.nextLine());

            petService.createPet(race, birthdate, isVaccinated, ownerName, vetId);
            System.out.println("Pet was created!");
        } catch (InvalidParameterException e) {
            System.out.println(e.getMessage());
        } catch (EntityNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Please insert a numeric value for author id!");
        } catch (ParseException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            System.out.println("Internal system error!");
        }
    }
}
