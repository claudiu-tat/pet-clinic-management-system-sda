package com.sda.claudiu.petclinicmanagementsystem.repository;

import com.sda.claudiu.petclinicmanagementsystem.model.Pet;

import java.util.Optional;

public class PetRepositoryImpl extends BaseRepositoryImpl<Pet> implements PetRepository {
    public PetRepositoryImpl() {
        super(Pet.class);
    }

}


