package util;

import org.modelmapper.ModelMapper;

public class DTOMapper {

    private DTOMapper() { }

    public static <T> T map(Object instanciaOrigem, Class<T> classeDestino){
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(instanciaOrigem, classeDestino);
    }

}
