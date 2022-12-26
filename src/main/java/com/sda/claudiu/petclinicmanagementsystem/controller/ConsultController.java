package com.sda.claudiu.petclinicmanagementsystem.controller;

import com.sda.claudiu.petclinicmanagementsystem.service.ConsultService;
import com.sda.claudiu.petclinicmanagementsystem.service.exceptions.EntityNotFoundException;
import com.sda.claudiu.petclinicmanagementsystem.service.exceptions.InvalidParameterException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class ConsultController {
    private final Scanner scanner = new Scanner(System.in);
    private final ConsultService consultService;

    public ConsultController(ConsultService consultService) {
        this.consultService = consultService;
    }

    public void addConsult() {
        try {
            System.out.println("Please insert a pet id: ");
            int petId = Integer.parseInt(scanner.nextLine());
            System.out.println("Please insert a vet id: ");
            int vetId = Integer.parseInt(scanner.nextLine());
            System.out.println("Please insert a date: ");
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
            String string = scanner.nextLine();
            Date date = simpleDateFormat.parse(string);
            System.out.println("Please insert a description: ");
            String description = scanner.nextLine();

            consultService.addConsult(petId, vetId, date, description);
            System.out.println("Consult was created!");
        } catch (InvalidParameterException e) {
            System.out.println(e.getMessage());
        } catch (EntityNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Please insert a numeric value for vet id!");
        } catch (ParseException e) {
            System.out.println("Please insert a valid date format!");
        } catch (Exception e) {
            System.out.println("Internal system error!");
        }
    }
}
