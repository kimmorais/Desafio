package service;

import Entity.CalculoDigitoUnico;
import org.springframework.stereotype.Service;
import service.commom.BuscaEntidadePorIdService;
import service.commom.SalvamentoEntidadeService;

import java.util.List;
import java.util.Optional;

@Service
public interface CalculoDigitoUnicoService extends SalvamentoEntidadeService<CalculoDigitoUnico>,
        BuscaEntidadePorIdService<CalculoDigitoUnico, Long> {
    List<CalculoDigitoUnico> listarCalculosDigitoUnicoPorIdUsuario(Long idUsuario);
    CalculoDigitoUnico obterDigitoUnico(CalculoDigitoUnico calculoDigitoUnico, Optional<Long> idUsuarioAssociacao);
    Optional<CalculoDigitoUnico> buscarCalculoDigitoUnicoPorParametrosDeCalculo(Integer inteiro, Integer numeroRepeticoesConcatenacao);
}
