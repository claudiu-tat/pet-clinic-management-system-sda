package com.sda.claudiu.petclinicmanagementsystem.service;

import com.sda.claudiu.petclinicmanagementsystem.model.Consult;
import com.sda.claudiu.petclinicmanagementsystem.model.Pet;
import com.sda.claudiu.petclinicmanagementsystem.model.Veterinarian;
import com.sda.claudiu.petclinicmanagementsystem.repository.ConsultRepository;
import com.sda.claudiu.petclinicmanagementsystem.repository.PetRepository;
import com.sda.claudiu.petclinicmanagementsystem.repository.VeterinarianRepository;
import com.sda.claudiu.petclinicmanagementsystem.service.exceptions.EntityNotFoundException;
import com.sda.claudiu.petclinicmanagementsystem.service.exceptions.InvalidParameterException;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public class ConsultServiceImpl implements ConsultService{
    private final VeterinarianRepository veterinarianRepository;
    private final PetRepository petRepository;
    private final ConsultRepository consultRepository;

    public ConsultServiceImpl(VeterinarianRepository veterinarianRepository, PetRepository petRepository, ConsultRepository consultRepository) {
        this.veterinarianRepository = veterinarianRepository;
        this.petRepository = petRepository;
        this.consultRepository = consultRepository;
    }

    @Override
    public void addConsult(int petId, int vetId, Date date, String description) throws InvalidParameterException, EntityNotFoundException {
        if (petId < 1) {
            throw new InvalidParameterException("Value for pet id: " + petId + " is invalid!");
        }
        if (vetId < 1) {
            throw new InvalidParameterException("Value for vet id: " + vetId + " is invalid!");
        }
        if (description == null || description.isBlank() || description.length() < 10) {
            throw new InvalidParameterException("Value for description: " + description + " is invalid!");
        }

        Optional<Pet> petOptional = petRepository.findById(petId);
        if (petOptional.isEmpty()) {
            throw new EntityNotFoundException("Pet with id: " + petId + " not found!");
        }
        Pet pet = petOptional.get();

        Optional<Veterinarian> veterinarianOptional = veterinarianRepository.findById(vetId);
        if (veterinarianOptional.isEmpty()) {
            throw new EntityNotFoundException("Vet with id: " + vetId + " was not found on the list!");
        }
        Veterinarian veterinarian = veterinarianOptional.get();

        Consult consult = new Consult(date, description);
        consult.setPet(pet);
        consult.setVeterinarian(veterinarian);

        consultRepository.create(consult);
    }

    @Override
    public void updateConsult(int consultId, String description) throws InvalidParameterException, EntityNotFoundException {
        if (consultId < 1) {
            throw new InvalidParameterException("Value for consult id: " + consultId + " is invalid!");
        }
        if (description == null || description.isBlank() || description.length() < 10) {
            throw new InvalidParameterException("Value for description: " + description + " is invalid!");
        }
        Optional<Consult> consultOptional = consultRepository.findById(consultId);
        if (consultOptional.isEmpty()) {
            throw new EntityNotFoundException("Consult with id: " + consultId + " not found!");
        }

        Consult consult = consultOptional.get();
        consult.setDescription(description);
        consultRepository.update(consult);
    }

    @Override
    public List<Consult> viewAllConsults() {
        return consultRepository.findAll();
    }
}
