package com.sda.claudiu.petclinicmanagementsystem.repository;

import com.sda.claudiu.petclinicmanagementsystem.model.Consult;

public class ConsultRepositoryImpl extends BaseRepositoryImpl<Consult> implements ConsultRepository{
    public ConsultRepositoryImpl() {
        super(Consult.class);
    }
}
