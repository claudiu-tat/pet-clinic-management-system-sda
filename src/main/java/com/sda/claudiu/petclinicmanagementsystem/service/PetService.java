package com.sda.claudiu.petclinicmanagementsystem.service;

import com.sda.claudiu.petclinicmanagementsystem.service.exceptions.EntityNotFoundException;
import com.sda.claudiu.petclinicmanagementsystem.service.exceptions.InvalidParameterException;

import java.util.Date;

public interface PetService {
    void createPet(String race, Date birthdate, boolean isVaccinated, String ownerName, int vetId) throws InvalidParameterException, EntityNotFoundException;
    
}