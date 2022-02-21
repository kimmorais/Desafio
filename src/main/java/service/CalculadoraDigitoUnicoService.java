package service;

import org.springframework.stereotype.Service;

@Service
public interface CalculadoraDigitoUnicoService {
    Integer calcularDigitoUnico(String inteiro, Integer numeroDeVezesConcatenacao);
}
