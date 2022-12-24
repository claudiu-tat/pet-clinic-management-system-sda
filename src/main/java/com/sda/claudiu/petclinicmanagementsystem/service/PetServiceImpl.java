package com.sda.claudiu.petclinicmanagementsystem.service;

import com.sda.claudiu.petclinicmanagementsystem.model.Pet;
import com.sda.claudiu.petclinicmanagementsystem.model.Veterinarian;
import com.sda.claudiu.petclinicmanagementsystem.repository.PetRepository;
import com.sda.claudiu.petclinicmanagementsystem.repository.VeterinarianRepository;
import com.sda.claudiu.petclinicmanagementsystem.service.exceptions.EntityNotFoundException;
import com.sda.claudiu.petclinicmanagementsystem.service.exceptions.InvalidParameterException;

import java.util.Date;
import java.util.Optional;

public class PetServiceImpl implements PetService {
    private final PetRepository petRepository;
    private final VeterinarianRepository veterinarianRepository;

    public PetServiceImpl(PetRepository petRepository, VeterinarianRepository veterinarianRepository) {
        this.petRepository = petRepository;
        this.veterinarianRepository = veterinarianRepository;
    }


    @Override
    public void createPet(String race, Date birthdate, boolean isVaccinated, String ownerName, int vetId) throws InvalidParameterException, EntityNotFoundException {
        if (race == null || race.isBlank() || race.length() < 2) {
            throw new InvalidParameterException("Value for race: " + race + " is invalid!");
        }
        if (birthdate == null || race.isBlank()) {
            throw new InvalidParameterException("Value for birthdate: " + birthdate + " is invalid!");
        }
        if (ownerName == null || ownerName.isBlank() || ownerName.length() < 2) {
            throw new InvalidParameterException("Value for owner name: " + ownerName + " is invalid!");
        }
        if (vetId < 1) {
            throw new InvalidParameterException("Value for vet id: " + vetId + " is invalid!");
        }

        Optional<Veterinarian> veterinarianOptional = veterinarianRepository.findById(vetId);
        if (veterinarianOptional.isEmpty()) {
            throw new EntityNotFoundException("Vet with id: " + vetId + " was not found on the list!");
        }

        Veterinarian veterinarian = veterinarianOptional.get();
        Pet pet = new Pet(race, birthdate, isVaccinated, ownerName);
        pet.setVeterinarian(veterinarian);
        petRepository.create(pet);
    }
}

