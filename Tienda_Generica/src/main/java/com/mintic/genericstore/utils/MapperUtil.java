package com.mintic.genericstore.utils;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class MapperUtil {

    private static final ModelMapper modelMapper = new ModelMapper();

    public static <S, T> T mapToDTO(S source, Class<T> targetClass) {
        return modelMapper.map(source, targetClass);
    }
}
