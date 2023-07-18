package com.example.dbdemo.mapper;

import com.example.dbdemo.dto.MessageDto;
import com.example.dbdemo.entity.Message;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface MessageMapper {

    MessageMapper INSTANCE = Mappers.getMapper(MessageMapper.class);

    MessageDto toDto(final Message entity);

}
