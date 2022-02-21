package controller;

import Entity.CalculoDigitoUnico;
import dto.CalculoDigitoUnicoDto;
import dto.UsuarioDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import service.CalculoDigitoUnicoService;
import util.DTOMapper;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/digitos-unicos")
public class CalculoDigitoUnicoController {

    @Autowired
    private CalculoDigitoUnicoService calculoDigitoUnicoService;

    @GetMapping("/usuario/{idUsuario}")
    public ResponseEntity<List<CalculoDigitoUnicoDto>> buscarCalculosDigitoUnicoPorIdUsuario(@PathVariable Long idUsuario){
        List<CalculoDigitoUnicoDto> calculosPorUsuarioDto = this.calculoDigitoUnicoService.listarCalculosDigitoUnicoPorIdUsuario(idUsuario).stream().map(calculoEntidade -> DTOMapper.map(calculoEntidade, CalculoDigitoUnicoDto.class)).collect(Collectors.toList());
        return ResponseEntity.ok(calculosPorUsuarioDto);
    }

    @PostMapping
    public ResponseEntity<CalculoDigitoUnicoDto> obterDigitoUnico(@Valid @RequestBody CalculoDigitoUnicoDto calculoDigitoUnicoDto){
        Optional<Long> idUsuarioAssociacaoOpcional = Optional.ofNullable(calculoDigitoUnicoDto.getUsuarioCalculoDigitoUnico()).map(UsuarioDto::getIdUsuario);
        CalculoDigitoUnico calculoDigitoUnico = DTOMapper.map(calculoDigitoUnicoDto, CalculoDigitoUnico.class);
        calculoDigitoUnico = this.calculoDigitoUnicoService.obterDigitoUnico(calculoDigitoUnico, idUsuarioAssociacaoOpcional);
        return ResponseEntity.ok(DTOMapper.map(calculoDigitoUnico, CalculoDigitoUnicoDto.class));
    }
}
