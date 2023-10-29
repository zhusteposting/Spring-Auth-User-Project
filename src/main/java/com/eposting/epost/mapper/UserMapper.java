package com.eposting.epost.mapper;

import com.eposting.epost.model.User;
import com.eposting.epost.model.dto.UserDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    /**
     * instance per interface, even with componentModel "spring"
     */
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserDTO toDTO(User model);
    List<UserDTO> toDTOs(List<User> models);

    User toModel(UserDTO model);
}
   