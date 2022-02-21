package service.commom;

import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface BuscaEntidadePorIdService<T, Y> {
    Optional<T> buscarPorId(Y idEntidade);
}
