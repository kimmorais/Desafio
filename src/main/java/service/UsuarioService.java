package service;

import Entity.Usuario;
import org.springframework.stereotype.Service;
import service.commom.*;

@Service
public interface UsuarioService extends ListagemEntidadeService<Usuario>,
        SalvamentoEntidadeService<Usuario>,
        EdicaoEntidadeService<Usuario>,
        DelecaoEntidadeService<Usuario, Long>,
        BuscaEntidadePorIdService<Usuario, Long> {

}
