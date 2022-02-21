package service.impl;

import Entity.CalculoDigitoUnico;
import Entity.Usuario;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import repository.CalculoDigitoUnicoRepository;
import service.CalculadoraDigitoUnicoService;
import service.CalculoDigitoUnicoService;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CalculoDigitoUnicoServiceImpl implements CalculoDigitoUnicoService {

    private final CalculadoraDigitoUnicoService calculadoraDigitoUnicoService;

    private final CalculoDigitoUnicoRepository calculoDigitoUnicoRepository;

    @Override
    public CalculoDigitoUnico obterDigitoUnico(CalculoDigitoUnico calculoDigitoUnico, Optional<Long> idUsuarioAssociacaoOpcional) {
        this.associarCalculoComUsuario(calculoDigitoUnico, idUsuarioAssociacaoOpcional.get());
        return calculoDigitoUnico;
    }

    @Override
    public List<CalculoDigitoUnico> listarCalculosDigitoUnicoPorIdUsuario(Long idUsuario) {
        return this.calculoDigitoUnicoRepository.findAllByUsuariosCalculoDigitoUnico(Usuario.builder().idUsuario(idUsuario).build());
    }

    @Override
    public Optional<CalculoDigitoUnico> buscarPorId(Long idEntidade) {
        return this.calculoDigitoUnicoRepository.findById(idEntidade);
    }

    @Override
    public CalculoDigitoUnico salvar(CalculoDigitoUnico entidade) {
        return this.calculoDigitoUnicoRepository.save(entidade);
    }

    private CalculoDigitoUnico executarCalculoDigitoUnico(CalculoDigitoUnico calculoDigitoUnico) {
        Integer digitoUnico = this.calculadoraDigitoUnicoService.calcularDigitoUnico(calculoDigitoUnico.getInteiroParaExtracaoDigitoUnico().toString(), Optional.ofNullable(calculoDigitoUnico.getNumeroDeVezesParaRepeticaoDoInteiroParaExtracaoDigitoUnico()).orElse(0));
        calculoDigitoUnico.setResultadoExtracaoDigitoUnico(digitoUnico);
        return calculoDigitoUnico;
    }

    private void associarCalculoComUsuario(CalculoDigitoUnico calculoDigitoUnico, Long idUsuario) {
        Usuario usuario = Usuario.builder().idUsuario(idUsuario).build();
        Optional<CalculoDigitoUnico> calculoJaPersistido = this.buscarCalculoDigitoUnicoPorParametrosDeCalculo(calculoDigitoUnico.getInteiroParaExtracaoDigitoUnico(), calculoDigitoUnico.getNumeroDeVezesParaRepeticaoDoInteiroParaExtracaoDigitoUnico());
        if (calculoJaPersistido.isPresent()) {
            calculoJaPersistido.get().getUsuariosCalculoDigitoUnico().add(usuario);
            this.salvar(calculoJaPersistido.get());
        }
        else {
            calculoDigitoUnico.setUsuariosCalculoDigitoUnico(Arrays.asList(usuario));
            this.salvar(calculoDigitoUnico);
        }
    }

    @Override
    public Optional<CalculoDigitoUnico> buscarCalculoDigitoUnicoPorParametrosDeCalculo(Integer inteiro, Integer numeroRepeticoesConcatenacao) {
        return this.calculoDigitoUnicoRepository.findByInteiroParaExtracaoDigitoUnicoAndNumeroDeVezesParaRepeticaoDoInteiroParaExtracaoDigitoUnico(inteiro, numeroRepeticoesConcatenacao);
    }

}
