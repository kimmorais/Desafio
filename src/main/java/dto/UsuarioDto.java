package dto;

import lombok.Data;

import java.util.List;

@Data
public class UsuarioDto {

    private Long idUsuario;

    private String emailUsuario;

    private String nomeUsuario;

    private List<CalculoDigitoUnicoDto> calculosDigitoUnico;
}
