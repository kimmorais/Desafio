package service;

import Entity.Usuario;
import org.springframework.stereotype.Service;

@Service
public interface ValidacaoEmailUsuarioService {
    void verificarDisponibilidadeEmail(Usuario usuario);
}
