package com.sda.claudiu.petclinicmanagementsystem.service;

import com.sda.claudiu.petclinicmanagementsystem.model.Veterinarian;
import com.sda.claudiu.petclinicmanagementsystem.repository.VeterinarianRepository;
import com.sda.claudiu.petclinicmanagementsystem.service.exceptions.InvalidParameterException;

import java.util.List;

public class VeterinarianServiceImpl implements VeterinarianService{
    private final VeterinarianRepository veterinarianRepository;        // to have the access to the repository, we have to declare here the singleton

    public VeterinarianServiceImpl(VeterinarianRepository veterinarianRepository) {
        this.veterinarianRepository = veterinarianRepository;
    }

    @Override
    public void createVeterinarian(String firstName, String lastName, String address, String speciality) throws InvalidParameterException {
        if (firstName == null || firstName.isBlank() || firstName.length() < 3) {
            throw new InvalidParameterException("The value for first name: " + firstName + " is invalid, please try again!");
        }
        if (lastName == null || lastName.isBlank() || lastName.length() < 3) {
            throw new InvalidParameterException("The value for last name: " + lastName + " is invalid, please try again!");
        }
        if (address == null || address.isBlank() || address.length() < 3) {
            throw new InvalidParameterException("The value for address: " + address + " is invalid, please try again!");
        }
        if (speciality == null || speciality.isBlank() || speciality.length() < 3) {
            throw new InvalidParameterException("The value for speciality: " + speciality + " is invalid, please try again!");
        }

        veterinarianRepository.create(new Veterinarian(firstName, lastName, address, speciality));
    }

    /*@Override
    public void updateVeterinarian(int vetId, String firstName, String lastName) {

    }*/

    @Override
    public List<Veterinarian> getAllVeterinarians() {
        return veterinarianRepository.findAll();
    }
}