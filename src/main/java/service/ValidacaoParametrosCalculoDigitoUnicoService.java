package service;

import org.springframework.stereotype.Service;

@Service
public interface ValidacaoParametrosCalculoDigitoUnicoService {
    boolean validarParametros(String inteiro, Integer numeroDeVezesConcatenacao);
}
