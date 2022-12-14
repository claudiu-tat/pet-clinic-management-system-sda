package com.sda.claudiu.petclinicmanagementsystem.service;

import com.sda.claudiu.petclinicmanagementsystem.model.Veterinarian;
import com.sda.claudiu.petclinicmanagementsystem.service.exceptions.EntityNotFoundException;
import com.sda.claudiu.petclinicmanagementsystem.service.exceptions.InvalidParameterException;

import java.util.List;

public interface VeterinarianService {
    void createVeterinarian(String firstName, String lastName, String address, String speciality) throws InvalidParameterException;
    void updateVeterinarian(int vetId, String firstName, String lastName, String address, String speciality) throws InvalidParameterException, EntityNotFoundException;
    void deleteVeterinarian(int vetId) throws InvalidParameterException, EntityNotFoundException;
    List<Veterinarian> getAllVeterinarians();
}
