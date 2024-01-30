package com.fstg.mediatech.service;

import com.fstg.mediatech.dto.ClientReponseDto;
import com.fstg.mediatech.dto.ClientRequestDto;

import java.util.List;

public interface ClientService {

    ClientReponseDto save(ClientRequestDto clientRequestDto);

    ClientReponseDto findById(Integer id);

    ClientReponseDto findByNom(String nom);

    void delete(Integer id);

    ClientReponseDto update(ClientRequestDto clientRequestDto, Integer id);

    List<ClientReponseDto> findAll();
}
