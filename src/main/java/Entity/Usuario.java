package Entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Usuario {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long idUsuario;

    private String nomeUsuario;

    @Column(unique=true)
    private String emailUsuario;

    @ManyToMany(mappedBy="usuariosCalculoDigitoUnico")
    private List<CalculoDigitoUnico> calculosDigitoUnico;
}
