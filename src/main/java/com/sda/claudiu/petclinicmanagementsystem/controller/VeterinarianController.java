package com.sda.claudiu.petclinicmanagementsystem.controller;

import com.sda.claudiu.petclinicmanagementsystem.service.VeterinarianService;
import com.sda.claudiu.petclinicmanagementsystem.service.exceptions.EntityNotFoundException;
import com.sda.claudiu.petclinicmanagementsystem.service.exceptions.InvalidParameterException;

import java.util.Scanner;

public class VeterinarianController {
    private final VeterinarianService veterinarianService;
    private final Scanner scanner = new Scanner(System.in);

    public VeterinarianController(VeterinarianService veterinarianService) {
        this.veterinarianService = veterinarianService;
    }

    public void createVeterinarian() throws InvalidParameterException {
        try {
            System.out.println("Please insert vet first name: ");
            String firstName = scanner.nextLine();
            System.out.println("Please insert vet last name: ");
            String lastName = scanner.nextLine();
            System.out.println("Please insert vet address: ");
            String address = scanner.nextLine();
            System.out.println("Please insert vet speciality: ");
            String speciality = scanner.nextLine();

            veterinarianService.createVeterinarian(firstName, lastName, address, speciality);
            System.out.println("Veterinarian was created!");
        } catch (InvalidParameterException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println("Internal server error!");
        }

    }

    public void updateVeterinarian() {
        try {
            System.out.println("Please insert vet id: ");
            int vetId = Integer.parseInt(scanner.nextLine());
            System.out.println("Please insert firstname: ");
            String firstname = scanner.nextLine();
            System.out.println("Please insert lastname: ");
            String lastname = scanner.nextLine();
            System.out.println("Please insert address: ");
            String address = scanner.nextLine();
            System.out.println("Please insert speciality: ");
            String speciality = scanner.nextLine();

            veterinarianService.updateVeterinarian(vetId, firstname, lastname, address, speciality);
            System.out.println("Veterinarian was updated!");

        } catch (InvalidParameterException e) {
            System.out.println(e.getMessage());
        } catch (EntityNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (Exception ex) {
            System.out.println("Internal server error!");
        }
    }

    public void deleteVeterinarian() {
        try {
            System.out.println("Please insert vet id: ");
            int vetId = Integer.parseInt(scanner.nextLine());

            veterinarianService.deleteVeterinarian(vetId);
            System.out.println("Veterinarian deleted!");

        } catch (InvalidParameterException e) {
            System.out.println(e.getMessage());
        } catch (EntityNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (Exception ex) {
            System.out.println("Internal server error!");
        }
    }

    public void showAllVets() {
        veterinarianService.getAllVeterinarians().stream().forEach(veterinarian ->
                System.out.println("Vet with id: " + veterinarian.getId() + ","
                        + " firstname: " + veterinarian.getFirstName() + ","
                        + " lastname: " + veterinarian.getLastName() + ","
                        + " speciality: " + veterinarian.getSpeciality() + ".")
        );
    }
}
