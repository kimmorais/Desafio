package repository;

import Entity.CalculoDigitoUnico;
import Entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CalculoDigitoUnicoRepository extends JpaRepository<CalculoDigitoUnico, Long> {

    List<CalculoDigitoUnico> findAllByUsuariosCalculoDigitoUnico(Usuario usuario);

    Optional<CalculoDigitoUnico> findByInteiroParaExtracaoDigitoUnicoAndNumeroDeVezesParaRepeticaoDoInteiroParaExtracaoDigitoUnico(Integer inteiro, Integer numeroRepeticoesConcatenacao);
}
