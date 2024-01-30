package com.fstg.mediatech.mapper;

import com.fstg.mediatech.avromodel.ClientAvro;
import com.fstg.mediatech.models.ClientEntity;
import org.mapstruct.Mapper;

@Mapper(uses = CharSequenceMapper.class)
public interface ClientMapper {
    ClientAvro sourceToDestination(ClientEntity clientEntity);
    ClientEntity sourceToDestination(ClientAvro clientAvro);
}
