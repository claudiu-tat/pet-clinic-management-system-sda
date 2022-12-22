package com.sda.claudiu.petclinicmanagementsystem.repository;

import com.sda.claudiu.petclinicmanagementsystem.model.Veterinarian;

public class VeterinarianRepositoryImpl extends BaseRepositoryImpl<Veterinarian> implements VeterinarianRepository {
    public VeterinarianRepositoryImpl() {
        super(Veterinarian.class);          // why we have to eliminate the parameters and put the ...class here in super?
    }
}
