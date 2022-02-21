package service;

import Entity.Usuario;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.data.domain.PageRequest;
import repository.UsuarioRepository;
import service.impl.UsuarioServiceImpl;

class UsuarioServiceTest {

	private ValidacaoEmailUsuarioService validacaoEmailUsuarioService = Mockito.mock(ValidacaoEmailUsuarioService.class);
	private UsuarioRepository usuarioRepository = Mockito.mock(UsuarioRepository.class);

	@Test
	@DisplayName("deve chamar findAll")
	void deveChamarFindAll() {
		UsuarioServiceImpl usuarioService = new UsuarioServiceImpl(validacaoEmailUsuarioService, usuarioRepository);
		usuarioService.listarTodos();
		Mockito.verify(usuarioRepository, Mockito.atLeastOnce()).findAll();
	}
	
	@Test
	@DisplayName("deve chamar findAllPaginado")
	void deveChamarFindAllPaginado() {
		UsuarioServiceImpl usuarioService = new UsuarioServiceImpl(validacaoEmailUsuarioService, usuarioRepository);
		PageRequest pageable = PageRequest.of(0, 5);
		usuarioService.listarPaginado(0,5);
		Mockito.verify(usuarioRepository, Mockito.atLeastOnce()).findAll(pageable);
	}
	
	@Test
	@DisplayName("deve chamar save")
	void deveChamarSave() {
		UsuarioServiceImpl usuarioService = new UsuarioServiceImpl(validacaoEmailUsuarioService, usuarioRepository);
		Mockito.doNothing().when(validacaoEmailUsuarioService).verificarDisponibilidadeEmail(ArgumentMatchers.any(Usuario.class));
		usuarioService.salvar(Usuario.builder().nomeUsuario("Julucin").emailUsuario("julucin@gmail.com").build());
		Mockito.verify(usuarioRepository, Mockito.atLeastOnce()).save(ArgumentMatchers.any(Usuario.class));
	}
	
	@Test
	@DisplayName("deve chamar save se o id estiver presente")
	void deveChamarSaveSeIdPresente() {
		UsuarioServiceImpl usuarioService = new UsuarioServiceImpl(validacaoEmailUsuarioService, usuarioRepository);
		Mockito.doNothing().when(validacaoEmailUsuarioService).verificarDisponibilidadeEmail(ArgumentMatchers.any(Usuario.class));
		usuarioService.editar(Usuario.builder().idUsuario(1L).nomeUsuario("Zélucin").emailUsuario("julucin@gmail.com").build());
		Mockito.verify(usuarioRepository, Mockito.atLeastOnce()).save(ArgumentMatchers.any(Usuario.class));
	}
	
	@Test
	@DisplayName("nao deve chamar save se o id nao estiver presente")
	void naoDeveChamarSaveSeIdNaoPresente() {
		UsuarioServiceImpl usuarioService = new UsuarioServiceImpl(validacaoEmailUsuarioService, usuarioRepository);
		Mockito.doNothing().when(validacaoEmailUsuarioService).verificarDisponibilidadeEmail(ArgumentMatchers.any(Usuario.class));
		usuarioService.editar(Usuario.builder().nomeUsuario("Zélucin").emailUsuario("julucin@gmail.com").build());
		Mockito.verify(usuarioRepository, Mockito.atMost(0)).save(ArgumentMatchers.any(Usuario.class));
	}
	
	@Test
	@DisplayName("deve chamar delete")
	void deveChamarDelete() {
		UsuarioServiceImpl usuarioService = new UsuarioServiceImpl(validacaoEmailUsuarioService, usuarioRepository);
		usuarioService.deletar(Usuario.builder().nomeUsuario("Julucin").emailUsuario("julucin@gmail.com").build());
		Mockito.verify(usuarioRepository, Mockito.atLeastOnce()).delete(ArgumentMatchers.any(Usuario.class));
	}
	
	@Test
	@DisplayName("deve chamar deleteById")
	void deveChamarDeleteById() {
		UsuarioServiceImpl usuarioService = new UsuarioServiceImpl(validacaoEmailUsuarioService, usuarioRepository);
		usuarioService.deletarPorId(1L);
		Mockito.verify(usuarioRepository, Mockito.atLeastOnce()).deleteById(ArgumentMatchers.anyLong());
	}
	
	@Test
	@DisplayName("deve chamar findById")
	void deveChamarFindById() {
		UsuarioServiceImpl usuarioService = new UsuarioServiceImpl(validacaoEmailUsuarioService, usuarioRepository);
		usuarioService.buscarPorId(1L);
		Mockito.verify(usuarioRepository, Mockito.atLeastOnce()).findById(ArgumentMatchers.anyLong());
	}
}
