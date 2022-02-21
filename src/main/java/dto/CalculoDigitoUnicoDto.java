package dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CalculoDigitoUnicoDto {

    @NotNull(message = "Parametro para extracao do digito unico nao pode estar nulo")
    private Integer inteiroParaExtracaoDigitoUnico;

    private Integer numeroDeVezesParaRepeticaoDoInteiroParaExtracaoDigitoUnico;

    private Integer resultadoExtracaoDigitoUnico;

    private UsuarioDto usuarioCalculoDigitoUnico;

}
