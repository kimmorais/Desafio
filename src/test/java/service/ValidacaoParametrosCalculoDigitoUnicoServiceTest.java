package service;

import exception.ParametroProibidoCalculoDigitoUnicoException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import service.impl.ValidacaoParametrosCalculoDigitoUnicoServiceImpl;

class ValidacaoParametrosCalculoDigitoUnicoServiceTest {

	@Test
	@DisplayName("deve lancar excecao pois tamanho mínimo de inteiro para extracao nao foi respeitado")
	void deveLancarExcecaoParametroProibidoPoisTamanhoMinimoParaExtracaoNaoFoiRespeitado() {
		Integer tamanhoMinimoInteiroParaExtracaoDigitoUnico = 2;
		Integer numeroMaximoRepeticaoInteiroExtracaoDigitoUnico = 10;
		Integer numeroMinimoRepeticaoInteiroExtracaoDigitoUnico = 2;
		String mensagemParametroProibido = "Teste lancamento excecao";
		ValidacaoParametrosCalculoDigitoUnicoServiceImpl validacaoParametroCalculoDigitoUnicoService = new ValidacaoParametrosCalculoDigitoUnicoServiceImpl(tamanhoMinimoInteiroParaExtracaoDigitoUnico, numeroMaximoRepeticaoInteiroExtracaoDigitoUnico, numeroMinimoRepeticaoInteiroExtracaoDigitoUnico, mensagemParametroProibido);
		Assertions.assertThrows(ParametroProibidoCalculoDigitoUnicoException.class, () -> validacaoParametroCalculoDigitoUnicoService.validarParametros("1", 2));
	}
	@Test
	@DisplayName("deve lancar excecao pois tamanho maximo para repeticao nao foi respeitado")
	void deveLancarExcecaoParametroProibidoPoisTamanhoMaximoParaRepeticaoNaoFoiRespeitado() {
		Integer tamanhoMinimoInteiroParaExtracaoDigitoUnico = 2;
		Integer numeroMaximoRepeticaoInteiroExtracaoDigitoUnico = 10;
		Integer numeroMinimoRepeticaoInteiroExtracaoDigitoUnico = 2;
		String mensagemParametroProibido = "Teste lancamento excecao";
		ValidacaoParametrosCalculoDigitoUnicoServiceImpl validacaoParametroCalculoDigitoUnicoService = new ValidacaoParametrosCalculoDigitoUnicoServiceImpl(tamanhoMinimoInteiroParaExtracaoDigitoUnico, numeroMaximoRepeticaoInteiroExtracaoDigitoUnico, numeroMinimoRepeticaoInteiroExtracaoDigitoUnico, mensagemParametroProibido);
		Assertions.assertThrows(ParametroProibidoCalculoDigitoUnicoException.class, () -> validacaoParametroCalculoDigitoUnicoService.validarParametros("2", 22));
	}
	
	@Test
	@DisplayName("deve lancar excecao pois tamanho mínimo para repeticao nao foi respeitado")
	void deveLancarExcecaoParametroProibidoPoisTamanhoMinimoParaRepeticaoNaoFoiRespeitado() {
		Integer tamanhoMinimoInteiroParaExtracaoDigitoUnico = 2;
		Integer numeroMaximoRepeticaoInteiroExtracaoDigitoUnico = 10;
		Integer numeroMinimoRepeticaoInteiroExtracaoDigitoUnico = 2;
		String mensagemParametroProibido = "Teste lancamento excecao";
		ValidacaoParametrosCalculoDigitoUnicoServiceImpl validacaoParametroCalculoDigitoUnicoService = new ValidacaoParametrosCalculoDigitoUnicoServiceImpl(tamanhoMinimoInteiroParaExtracaoDigitoUnico, numeroMaximoRepeticaoInteiroExtracaoDigitoUnico, numeroMinimoRepeticaoInteiroExtracaoDigitoUnico, mensagemParametroProibido);
		Assertions.assertThrows(ParametroProibidoCalculoDigitoUnicoException.class, () -> validacaoParametroCalculoDigitoUnicoService.validarParametros("2", 0));
	}
	
	@Test
	@DisplayName("retorna true pois parametros sao validos")
	void deveRetornarTruePoisParametrosSaoValidos() {
		Integer tamanhoMinimoInteiroParaExtracaoDigitoUnico = 2;
		Integer numeroMaximoRepeticaoInteiroExtracaoDigitoUnico = 10;
		Integer numeroMinimoRepeticaoInteiroExtracaoDigitoUnico = 2;
		String mensagemParametroProibido = "Teste lancamento excecao";
		ValidacaoParametrosCalculoDigitoUnicoServiceImpl validacaoParametroCalculoDigitoUnicoService = new ValidacaoParametrosCalculoDigitoUnicoServiceImpl(tamanhoMinimoInteiroParaExtracaoDigitoUnico, numeroMaximoRepeticaoInteiroExtracaoDigitoUnico, numeroMinimoRepeticaoInteiroExtracaoDigitoUnico, mensagemParametroProibido);
		boolean resultado = validacaoParametroCalculoDigitoUnicoService.validarParametros("4", 5);
		Assertions.assertTrue(resultado);
	}
	
}
