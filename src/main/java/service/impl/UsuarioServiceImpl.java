package service.impl;

import Entity.Usuario;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import repository.UsuarioRepository;
import service.UsuarioService;
import service.ValidacaoEmailUsuarioService;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private ValidacaoEmailUsuarioService validacaoEmailUsuarioService;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public List<Usuario> listarTodos() {
        return this.usuarioRepository.findAll();
    }

    @Override
    public Page<Usuario> listarPaginado(Integer pagina, Integer tamanho) {
        return this.usuarioRepository.findAll(PageRequest.of(pagina, tamanho));
    }

    @Override
    public Usuario salvar(Usuario entidade) {
        this.validacaoEmailUsuarioService.verificarDisponibilidadeEmail(entidade);
        return this.usuarioRepository.save(entidade);
    }

    @Override
    public void editar(Usuario entidade) {
        if (Optional.ofNullable(entidade.getIdUsuario()).isPresent()) {
            this.salvar(entidade);
        }
    }

    @Override
    public void deletar(Usuario entidade) {
        this.usuarioRepository.delete(entidade);
    }

    @Override
    public void deletarPorId(Long idEntidade) {
        this.usuarioRepository.deleteById(idEntidade);
    }

    @Override
    public Optional<Usuario> buscarPorId(Long idEntidade) {
        return this.usuarioRepository.findById(idEntidade);
    }

}
