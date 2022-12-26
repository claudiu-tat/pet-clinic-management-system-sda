package com.sda.claudiu.petclinicmanagementsystem.service;

import com.sda.claudiu.petclinicmanagementsystem.model.Pet;
import com.sda.claudiu.petclinicmanagementsystem.model.Veterinarian;
import com.sda.claudiu.petclinicmanagementsystem.repository.PetRepository;
import com.sda.claudiu.petclinicmanagementsystem.repository.VeterinarianRepository;
import com.sda.claudiu.petclinicmanagementsystem.service.exceptions.EntityNotFoundException;
import com.sda.claudiu.petclinicmanagementsystem.service.exceptions.InvalidParameterException;

import java.util.Date;
import java.util.List;
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

    @Override
    public void updatePet(int petId, String race, Date birthdate, boolean isVaccinated, String ownerName, int vetId) throws InvalidParameterException, EntityNotFoundException {
        if (petId < 1) {
            throw new InvalidParameterException("Value for pet id: " + petId + " is invalid!");
        }
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

        Optional<Pet> petOptional = petRepository.findById(petId);
        if (petOptional.isEmpty()) {
            throw new EntityNotFoundException("Pet with id: " + petId + " not found!");
        }

        Pet pet = petOptional.get();    // after we found the pet id, we get it and store in a pet var
        pet.setRace(race);              // set the new values for pet
        pet.setBirthdate(birthdate);
        pet.setVaccinated(isVaccinated);
        pet.setOwnerName(ownerName);

        Optional<Veterinarian> veterinarianOptional = veterinarianRepository.findById(vetId);   // searching for vet id
        if (veterinarianOptional.isEmpty()) {
            throw new EntityNotFoundException("Vet with id: " + vetId + " was not found on the list!");
        }
        Veterinarian veterinarian = veterinarianOptional.get();     // get and store in a vet var
        pet.setVeterinarian(veterinarian);                          // connect the pet with vet
        veterinarian.setId(vetId);                                  // setting a new vet id

        petRepository.update(pet);                                  // simply updating the pet and all the fields will be updated
    }

    @Override
    public List<Pet> getAllPets() {
        return petRepository.findAll();
    }
}

