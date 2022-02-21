package service.impl;

import com.google.common.primitives.Ints;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import service.CalculadoraDigitoUnicoService;
import service.ValidacaoParametrosCalculoDigitoUnicoService;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CalculadoraDigitoUnicoServiceImpl implements CalculadoraDigitoUnicoService {

    private final ValidacaoParametrosCalculoDigitoUnicoService validacaoParametrosCalculoDigitoUnicoService;

    @Override
    public Integer calcularDigitoUnico(String inteiro, Integer numeroDeVezesConcatenacao) {
        this.validacaoParametrosCalculoDigitoUnicoService.validarParametros(inteiro, numeroDeVezesConcatenacao);
        if (numeroDeVezesConcatenacao > 0) {
            inteiro = this.concatenarRepeticao(inteiro, numeroDeVezesConcatenacao);
        }
        List<Integer> algarismos = this.prepararAlgarismosParaExtracaoDigitoUnico(inteiro);
        return algarismos.stream().reduce(0, Integer::sum);
    }

    private String concatenarRepeticao(String inteiro, Integer numeroDeVezesConcatenacao) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int contador = 0; contador < numeroDeVezesConcatenacao; contador ++) {
            stringBuilder.append(inteiro);
        }
        return stringBuilder.toString();
    }

    private List<Integer> prepararAlgarismosParaExtracaoDigitoUnico(String inteiro) {
        List<String> algarismos = Arrays.asList(inteiro.split(""));
        return algarismos.stream().map(this::transformarEmInteiro).collect(Collectors.toList());
    }

    private Integer transformarEmInteiro(String algarismo) {
        return Optional.ofNullable(algarismo).map(Ints::tryParse).orElse(0);
    }

}
