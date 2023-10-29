package com.eposting.epost.mapper;

import com.eposting.epost.model.Profile;
import com.eposting.epost.model.dto.ProfileDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ProfileMapper {

    /**
     * instance per interface, even with componentModel "spring"
     */
    ProfileMapper INSTANCE = Mappers.getMapper(ProfileMapper.class);

    ProfileDTO toDTO(Profile model);

    Profile toModel(ProfileDTO model);
}
   