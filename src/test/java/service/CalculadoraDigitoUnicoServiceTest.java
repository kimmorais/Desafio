package service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import service.impl.CalculadoraDigitoUnicoServiceImpl;
import service.impl.ValidacaoParametrosCalculoDigitoUnicoServiceImpl;

class CalculadoraDigitoUnicoServiceTest {

	private ValidacaoParametrosCalculoDigitoUnicoService validacaoParametrosCalculoDigitoUnicoService = Mockito.mock(ValidacaoParametrosCalculoDigitoUnicoServiceImpl.class);
	
	@Test
	@DisplayName("Deve retornar 4 como resposta do metodo calcularDigitoUnico quando numeroDeVezesConcatenacao for 0")
	void deveRetornar4ComNumeroDeConcatenacaoo0() {
		String inteiro = "22";
		Integer numeroDeVezesConcatenacao = 0;
		CalculadoraDigitoUnicoServiceImpl calculadoraDigitoUnicoService = new CalculadoraDigitoUnicoServiceImpl(validacaoParametrosCalculoDigitoUnicoService);
		Integer resultado = calculadoraDigitoUnicoService.calcularDigitoUnico(inteiro, numeroDeVezesConcatenacao);
		Assertions.assertEquals(4, resultado);
	}
	
	@Test
	@DisplayName("Deve retornar 8 como resposta do metodo calcularDigitoUnico")
	void deveRetornar8(){
		String inteiro = "22";
		Integer numeroDeVezesConcatenacao = 2;
		CalculadoraDigitoUnicoServiceImpl calculadoraDigitoUnicoService = new CalculadoraDigitoUnicoServiceImpl(validacaoParametrosCalculoDigitoUnicoService);
		Integer resultado = calculadoraDigitoUnicoService.calcularDigitoUnico(inteiro, numeroDeVezesConcatenacao);
		Assertions.assertEquals(8, resultado);
	}
	
	@Test
	@DisplayName("Deve retornar 4 como resposta do metodo calcularDigitoUnico quando numeroDeVezesConcatenacao for 0")
	void deveRetornar4ComNumeroDeConcatenacao1(){
		String inteiro = "22";
		Integer numeroDeVezesConcatenacao = 1;
		CalculadoraDigitoUnicoServiceImpl calculadoraDigitoUnicoService = new CalculadoraDigitoUnicoServiceImpl(validacaoParametrosCalculoDigitoUnicoService);
		Integer resultado = calculadoraDigitoUnicoService.calcularDigitoUnico(inteiro, numeroDeVezesConcatenacao);
		Assertions.assertEquals(4, resultado);
	}
}
