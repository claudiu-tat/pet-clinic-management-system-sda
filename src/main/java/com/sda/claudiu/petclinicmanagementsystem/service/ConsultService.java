package com.sda.claudiu.petclinicmanagementsystem.service;

import com.sda.claudiu.petclinicmanagementsystem.model.Consult;
import com.sda.claudiu.petclinicmanagementsystem.service.exceptions.EntityNotFoundException;
import com.sda.claudiu.petclinicmanagementsystem.service.exceptions.InvalidParameterException;

import java.util.Date;
import java.util.List;

public interface ConsultService {
    void addConsult(int petId, int vetId, Date date, String description) throws InvalidParameterException, EntityNotFoundException;
    void updateConsult(int consultId, String description) throws InvalidParameterException, EntityNotFoundException;
    List<Consult> viewAllConsults();
}
