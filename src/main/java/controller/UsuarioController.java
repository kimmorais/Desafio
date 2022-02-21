package controller;

import Entity.Usuario;
import dto.UsuarioDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import service.UsuarioService;
import util.DTOMapper;

import java.net.URI;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public ResponseEntity<Page<UsuarioDto>> listarUsuariosPaginado(@RequestParam Integer pagina, @RequestParam Integer tamanho){
        Page<UsuarioDto> usuariosDto = this.usuarioService.listarPaginado(pagina, tamanho).map(usuarioEntidade -> DTOMapper.map(usuarioEntidade, UsuarioDto.class));
        return ResponseEntity.ok(usuariosDto);
    }

    @GetMapping("/{idUsuario}")
    public ResponseEntity<UsuarioDto> buscarUsuarioPorId(@PathVariable Long idUsuario){
        Usuario usuarioEntidade = this.usuarioService.buscarPorId(idUsuario).orElse(new Usuario());
        return ResponseEntity.ok(DTOMapper.map(usuarioEntidade, UsuarioDto.class));
    }

    @PostMapping
    public ResponseEntity<UsuarioDto> salvarUsuario(@RequestBody UsuarioDto usuarioDto, @RequestHeader String host){
        Usuario usuarioEntidade = DTOMapper.map(usuarioDto, Usuario.class);
        usuarioEntidade = this.usuarioService.salvar(usuarioEntidade);
        URI location = URI.create(host.concat("/usuarios/".concat(usuarioEntidade.getIdUsuario().toString())));
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{idUsuario}")
    public ResponseEntity<UsuarioDto> editarUsuario(@PathVariable Long idUsuario, @RequestBody UsuarioDto usuarioDto) {
        Usuario usuarioEntidade = DTOMapper.map(usuarioDto, Usuario.class);
        usuarioEntidade.setIdUsuario(idUsuario);
        this.usuarioService.editar(usuarioEntidade);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{idUsuario}")
    public ResponseEntity<UsuarioDto> deletarUsuario(@PathVariable Long idUsuario){
        this.usuarioService.deletarPorId(idUsuario);
        return ResponseEntity.noContent().build();
    }
}
