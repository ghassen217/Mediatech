package com.fstg.mediatech.service;

import com.fstg.mediatech.dao.ClientDao;
import com.fstg.mediatech.dto.ClientReponseDto;
import com.fstg.mediatech.dto.ClientRequestDto;
import com.fstg.mediatech.exception.EntityNotFoundException;
import com.fstg.mediatech.models.ClientEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service("")
public class ClientServiceImpl implements ClientService {
    private ClientDao clientDao;
    private ModelMapper modelMapper;

    // dans ce cas on peut supprimer l'annotation Autowired
    // l'injection se fait par le constructeur
    @Autowired
    public ClientServiceImpl(ClientDao clientDao, ModelMapper modelMapper) {
        this.clientDao = clientDao;
        this.modelMapper = modelMapper;
    }

    @Override
    public ClientReponseDto save(ClientRequestDto clientRequestDto) {
        ClientEntity clientEntity = modelMapper.map(clientRequestDto, ClientEntity.class);
        ClientEntity saved = clientDao.save(clientEntity);

        return modelMapper.map(saved, ClientReponseDto.class);
    }

    @Override
    public ClientReponseDto findById(Integer id) {
        ClientEntity clientEntity = clientDao.findById(id).orElseThrow(() -> new EntityNotFoundException("Client noy found"));
        return modelMapper.map(clientEntity, ClientReponseDto.class);
    }

    @Override
    public ClientReponseDto findByNom(String nom) {
        ClientEntity clientEntity = clientDao.findByNom(nom);
        return modelMapper.map(clientEntity, ClientReponseDto.class);
    }

    @Override
    public void delete(Integer id) {
        clientDao.deleteById(id);
    }

    @Override
    public ClientReponseDto update(ClientRequestDto clientRequestDto, Integer id) {
        Optional<ClientEntity> clientEntityOptional = clientDao.findById(id);
        if (clientEntityOptional.isPresent()) {
            ClientEntity clientEntity = modelMapper.map(clientRequestDto, ClientEntity.class);
            clientEntity.setId(id);
            // si on a pas l'id il va creer une autre ligne dans la BDD si non il faut ajouter l,id dans ClientRequestDto
            // ou faire update au lieu de save
            ClientEntity updated = clientDao.save(clientEntity);
            return modelMapper.map(updated, ClientReponseDto.class);
        } else {
            throw new EntityNotFoundException("Client not found");
        }
    }

    @Override
    public List<ClientReponseDto> findAll() {
        return clientDao.findAll()
                .stream().map(e -> modelMapper.map(e, ClientReponseDto.class))
                .collect(Collectors.toList());
    }
}
