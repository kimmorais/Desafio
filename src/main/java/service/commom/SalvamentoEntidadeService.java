package service.commom;

import org.springframework.stereotype.Service;

@Service
public interface SalvamentoEntidadeService<T> {
    T salvar(T entidade);
}
