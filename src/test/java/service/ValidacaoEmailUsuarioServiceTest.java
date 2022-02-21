package service;

import Entity.Usuario;
import exception.EmailJaUtilizadoException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import repository.UsuarioRepository;
import service.impl.ValidacaoEmailUsuarioServiceImpl;

import java.util.Optional;

class ValidacaoEmailUsuarioServiceTest {

	private UsuarioRepository usuarioRepository = Mockito.mock(UsuarioRepository.class);
	
	@Test
	@DisplayName("Nao deve lancar excecao pois nao foi encontrado usuario pelo email buscado")
	void naoDeveLancarExcecaoPoisNaoFoiEncontradoUsuarioPorEmail() {
		Usuario usuario = Usuario.builder().idUsuario(1L).emailUsuario("tiruliparretado@gmail.com").build();
		Mockito.when(usuarioRepository.findByEmailUsuario(usuario.getEmailUsuario())).thenReturn(Optional.empty());
		ValidacaoEmailUsuarioServiceImpl validacaoEmailUsuarioService = new ValidacaoEmailUsuarioServiceImpl("Mensagem pra parametrizada pra excecao", usuarioRepository);
		Assertions.assertDoesNotThrow(() -> validacaoEmailUsuarioService.verificarDisponibilidadeEmail(usuario));
	}
	
	@Test
	@DisplayName("Nao deve lancar excecao pois o usuario encontrado por email eh ele mesmo")
	void naoDeveLancarExcecaoPoisFoiEncontradoUsuarioPorEmailComMesmoId() {
		Usuario usuario = Usuario.builder().idUsuario(1L).emailUsuario("tiruliparretado@gmail.com").build();
		Usuario usuarioEncontrado = Usuario.builder().idUsuario(1L).build();
		Mockito.when(usuarioRepository.findByEmailUsuario(usuario.getEmailUsuario())).thenReturn(Optional.of(usuarioEncontrado));
		ValidacaoEmailUsuarioServiceImpl validacaoEmailUsuarioService = new ValidacaoEmailUsuarioServiceImpl("Mensagem pra parametrizada pra excecao", usuarioRepository);
		Assertions.assertDoesNotThrow(() -> validacaoEmailUsuarioService.verificarDisponibilidadeEmail(usuario));
	}
	
	@Test
	@DisplayName("deve lancar excecao pois o usuario encontrado por email eh outro usuario")
	void deveLancarExcecaoPoisFoiEncontradoUsuarioPorEmailEhOutroUsuario() {
		Usuario usuario = Usuario.builder().idUsuario(1L).emailUsuario("tiruliparretado@gmail.com").build();
		Usuario usuarioEncontrado = Usuario.builder().idUsuario(2L).build();
		Mockito.when(usuarioRepository.findByEmailUsuario(usuario.getEmailUsuario())).thenReturn(Optional.of(usuarioEncontrado));
		ValidacaoEmailUsuarioServiceImpl validacaoEmailUsuarioService = new ValidacaoEmailUsuarioServiceImpl("Mensagem pra parametrizada pra excecao", usuarioRepository);
		Assertions.assertThrows(EmailJaUtilizadoException.class, () -> validacaoEmailUsuarioService.verificarDisponibilidadeEmail(usuario));
	}
	
	@Test
	@DisplayName("deve lancar excecao pois um usuario foi encontrado por email ao tentar salvar um novo")
	void deveLancarExcecaoPoisFoiEncontradoUmUsuarioPorEmailAoTentarSalvarUmNovoUsuario() {
		Usuario usuario = Usuario.builder().emailUsuario("tiruliparretado@gmail.com").build();
		Usuario usuarioEncontrado = Usuario.builder().idUsuario(2L).build();
		Mockito.when(usuarioRepository.findByEmailUsuario(usuario.getEmailUsuario())).thenReturn(Optional.of(usuarioEncontrado));
		ValidacaoEmailUsuarioServiceImpl validacaoEmailUsuarioService = new ValidacaoEmailUsuarioServiceImpl("Mensagem pra parametrizada pra excecao", usuarioRepository);
		Assertions.assertThrows(EmailJaUtilizadoException.class, () -> validacaoEmailUsuarioService.verificarDisponibilidadeEmail(usuario));
	}
	
	
}
