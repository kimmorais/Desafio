package Entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CalculoDigitoUnico {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idCalculoDigitoUnico;

    private Integer inteiroParaExtracaoDigitoUnico;

    private Integer numeroDeVezesParaRepeticaoDoInteiroParaExtracaoDigitoUnico;

    private Integer resultadoExtracaoDigitoUnico;

    @ManyToMany
    @JoinTable(name = "calculo_digito_unico_usuario", joinColumns = @JoinColumn(name = "idCalculoDigitoUnico"), inverseJoinColumns = @JoinColumn(name = "idUsuario"))
    private List<Usuario> usuariosCalculoDigitoUnico;

}
