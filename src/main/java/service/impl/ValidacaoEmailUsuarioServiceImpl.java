package service.impl;

import Entity.Usuario;
import exception.EmailJaUtilizadoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import repository.UsuarioRepository;
import service.ValidacaoEmailUsuarioService;

import java.util.Optional;

@Service
public class ValidacaoEmailUsuarioServiceImpl implements ValidacaoEmailUsuarioService {

    public ValidacaoEmailUsuarioServiceImpl(@Value("${mensagem.emailJaUtilizadoCadastroUsuario}") String mensagemEmailJaFoiUtilizado, UsuarioRepository usuarioRepository) {
        this.mensagemEmailJaFoiUtilizado = mensagemEmailJaFoiUtilizado;
        this.usuarioRepository = usuarioRepository;
    }

    @Value("${mensagem.emailJaUtilizadoCadastroUsuario}")
    private String mensagemEmailJaFoiUtilizado;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public void verificarDisponibilidadeEmail(Usuario usuario) {
        Optional<Usuario> usuarioJaPersistidoComMesmoEmail = this.usuarioRepository.findByEmailUsuario(usuario.getEmailUsuario());
        if (usuarioJaPersistidoComMesmoEmail.isPresent() && !(Optional.ofNullable(usuario.getIdUsuario()).isPresent() && usuario.getIdUsuario().equals(usuarioJaPersistidoComMesmoEmail.get().getIdUsuario()))) {
            throw new EmailJaUtilizadoException(mensagemEmailJaFoiUtilizado);
        }
    }
}
