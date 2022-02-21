package service.commom;

import org.springframework.stereotype.Service;

@Service
public interface EdicaoEntidadeService<T> {
    void editar(T entidade);
}
