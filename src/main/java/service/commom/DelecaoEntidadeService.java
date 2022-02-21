package service.commom;

public interface DelecaoEntidadeService<T, Y> {
    void deletar(T entidade);
    void deletarPorId(Y idEntidade);
}
