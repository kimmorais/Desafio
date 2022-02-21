package service.impl;

import com.google.common.primitives.Ints;
import exception.ParametroProibidoCalculoDigitoUnicoException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import service.ValidacaoParametrosCalculoDigitoUnicoService;

@Service
public class ValidacaoParametrosCalculoDigitoUnicoServiceImpl implements ValidacaoParametrosCalculoDigitoUnicoService {

    public ValidacaoParametrosCalculoDigitoUnicoServiceImpl(
            @Value("${validacao.tamanhoMinimoInteiroExtracaoDigitoUnico}") Integer tamanhoMinimoInteiroParaExtracaoDigitoUnico,
            @Value("${validacao.numeroMaximoRepeticaoInteiroExtracaoDigitoUnico}") Integer numeroMaximoRepeticaoInteiroExtracaoDigitoUnico,
            @Value("${validacao.numeroMinimoRepeticaoInteiroExtracaoDigitoUnico}") Integer numeroMinimoRepeticaoInteiroExtracaoDigitoUnico,
            @Value("${mensagem.parametroCalculoDigitoUnicoInvalido}") String mensagemParametroProibido){

        this.mensagemParametroProibido = mensagemParametroProibido;
        this.numeroMaximoRepeticaoInteiroExtracaoDigitoUnico = numeroMaximoRepeticaoInteiroExtracaoDigitoUnico;
        this.numeroMinimoRepeticaoInteiroExtracaoDigitoUnico = numeroMinimoRepeticaoInteiroExtracaoDigitoUnico;
        this.tamanhoMinimoInteiroParaExtracaoDigitoUnico = tamanhoMinimoInteiroParaExtracaoDigitoUnico;
    }

    @Value("${validacao.tamanhoMinimoInteiroExtracaoDigitoUnico}")
    private Integer tamanhoMinimoInteiroParaExtracaoDigitoUnico;

    @Value("${validacao.numeroMaximoRepeticaoInteiroExtracaoDigitoUnico}")
    private Integer numeroMaximoRepeticaoInteiroExtracaoDigitoUnico;

    @Value("${validacao.numeroMinimoRepeticaoInteiroExtracaoDigitoUnico}")
    private Integer numeroMinimoRepeticaoInteiroExtracaoDigitoUnico;

    @Value("${mensagem.parametroCalculoDigitoUnicoInvalido}")
    private String mensagemParametroProibido;

    @Override
    public boolean validarParametros(String inteiro, Integer numeroDeVezesConcatenacao) {
        boolean inteiroEhValido = this.verificarValidacaoInteiro(inteiro);
        boolean numeroDeVezesConcatenacaoEhValido = this.verificarValidacaoNumeroRepeticoes(numeroDeVezesConcatenacao);
        if (!(inteiroEhValido && numeroDeVezesConcatenacaoEhValido)) {
            throw new ParametroProibidoCalculoDigitoUnicoException(mensagemParametroProibido);
        }
        return true;
    }

    private boolean verificarValidacaoNumeroRepeticoes(Integer numeroDeVezesConcatenacao) {
        return (numeroMinimoRepeticaoInteiroExtracaoDigitoUnico <= numeroDeVezesConcatenacao)
                && (numeroDeVezesConcatenacao <= numeroMaximoRepeticaoInteiroExtracaoDigitoUnico);
    }

    private boolean verificarValidacaoInteiro(String inteiro) {
        Integer inteiroConvertido = Ints.tryParse(inteiro);
        return (tamanhoMinimoInteiroParaExtracaoDigitoUnico <= inteiroConvertido)
                && (inteiroConvertido <= Math.pow(10, 1000000));
    }

}
