package com.sda.claudiu.petclinicmanagementsystem.service;

import com.sda.claudiu.petclinicmanagementsystem.service.exceptions.EntityNotFoundException;
import com.sda.claudiu.petclinicmanagementsystem.service.exceptions.InvalidParameterException;

import java.util.Date;

public interface ConsultService {
    void addConsult(int petId, int vetId, Date date, String description) throws InvalidParameterException, EntityNotFoundException;
}
