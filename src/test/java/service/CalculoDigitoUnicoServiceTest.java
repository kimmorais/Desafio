package service;

import Entity.CalculoDigitoUnico;
import Entity.Usuario;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import repository.CalculoDigitoUnicoRepository;
import service.impl.CalculadoraDigitoUnicoServiceImpl;
import service.impl.CalculoDigitoUnicoServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

class CalculoDigitoUnicoServiceTest {
	
	private CalculadoraDigitoUnicoServiceImpl calculadoraDigitoUnicoService = Mockito.mock(CalculadoraDigitoUnicoServiceImpl.class);

	private CalculoDigitoUnicoRepository calculoDigitoUnicoRepository = Mockito.mock(CalculoDigitoUnicoRepository.class);
	
	@Test
	@DisplayName("Se o Id de um usuário foi passado para o servico de calculo de digitos unicos, o calculo devera ser associado a este usuario")
	void deveChamarMetodoAssociarUsuario() {
		CalculoDigitoUnico calculoDigitoUnico = CalculoDigitoUnico.builder().inteiroParaExtracaoDigitoUnico(3).numeroDeVezesParaRepeticaoDoInteiroParaExtracaoDigitoUnico(0).build();
		Mockito.when(calculadoraDigitoUnicoService.calcularDigitoUnico(ArgumentMatchers.anyString(), ArgumentMatchers.anyInt())).thenReturn(3);
		CalculoDigitoUnicoServiceImpl calculoDigitoUnicoService = new CalculoDigitoUnicoServiceImpl(calculadoraDigitoUnicoService, calculoDigitoUnicoRepository);
		calculoDigitoUnicoService.obterDigitoUnico(calculoDigitoUnico, Optional.of(1L));
		Mockito.verify(calculoDigitoUnicoRepository, Mockito.atLeastOnce()).save(calculoDigitoUnico);
	}
	
	@Test
	@DisplayName("Se caso o calculo ja existir na base o usuario a ser associado deve entrar no fim da lista e nao sobrescrever a lista anterior")
	void deveAdicionarRelacionamentoComUsuarioENaoApagarRelacionamentosAntigos() {
		CalculoDigitoUnico calculoDigitoUnico = CalculoDigitoUnico.builder().inteiroParaExtracaoDigitoUnico(3).numeroDeVezesParaRepeticaoDoInteiroParaExtracaoDigitoUnico(0).build();
		Usuario usuarioPreviamenteAssociado = Usuario.builder().idUsuario(3L).build();
		List<Usuario> lista = new ArrayList<>();
		lista.add(usuarioPreviamenteAssociado);
		calculoDigitoUnico.setUsuariosCalculoDigitoUnico(lista);
		Mockito.when(calculadoraDigitoUnicoService.calcularDigitoUnico(ArgumentMatchers.anyString(), ArgumentMatchers.anyInt())).thenReturn(3);
		Mockito.when(calculoDigitoUnicoRepository.findByInteiroParaExtracaoDigitoUnicoAndNumeroDeVezesParaRepeticaoDoInteiroParaExtracaoDigitoUnico(3, 0)).thenReturn(Optional.of(calculoDigitoUnico));
		CalculoDigitoUnicoServiceImpl calculoDigitoUnicoService = new CalculoDigitoUnicoServiceImpl(calculadoraDigitoUnicoService, calculoDigitoUnicoRepository);
		calculoDigitoUnicoService.obterDigitoUnico(calculoDigitoUnico, Optional.of(1L));
		Assertions.assertEquals(usuarioPreviamenteAssociado.getIdUsuario(), calculoDigitoUnico.getUsuariosCalculoDigitoUnico().get(0).getIdUsuario());
		Assertions.assertEquals(1L, calculoDigitoUnico.getUsuariosCalculoDigitoUnico().get(1).getIdUsuario());
	}
	
	@Test
	@DisplayName("Garantir que o repository esta sendo chamado na busca calculos por usuario")
	void deveChamarFindAllByUsuariosCalculoDigitoUnico() {
		CalculoDigitoUnicoServiceImpl calculoDigitoUnicoService = new CalculoDigitoUnicoServiceImpl(calculadoraDigitoUnicoService, calculoDigitoUnicoRepository);
		calculoDigitoUnicoService.listarCalculosDigitoUnicoPorIdUsuario(1L);
		Mockito.verify(calculoDigitoUnicoRepository, Mockito.atLeastOnce()).findAllByUsuariosCalculoDigitoUnico(ArgumentMatchers.any(Usuario.class));
	}
	
	@Test
	@DisplayName("Garantir que o repository esta sendo chamado na busca por id")
	void deveChamarFindAllById() {
		CalculoDigitoUnicoServiceImpl calculoDigitoUnicoService = new CalculoDigitoUnicoServiceImpl(calculadoraDigitoUnicoService, calculoDigitoUnicoRepository);
		calculoDigitoUnicoService.buscarPorId(1L);
		Mockito.verify(calculoDigitoUnicoRepository, Mockito.atLeastOnce()).findById(ArgumentMatchers.anyLong());
	}

}
