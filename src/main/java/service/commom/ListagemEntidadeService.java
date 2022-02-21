package service.commom;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ListagemEntidadeService<T> {
    List<T> listarTodos();
    Page<T> listarPaginado(Integer pagina, Integer tamanho);
}
