package br.com.projetoThais.mvc.repository;

import br.com.projetoThais.mvc.model.Pedido;
import br.com.projetoThais.mvc.model.StatusPedido;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {

    List<Pedido> findByStatusPedido(StatusPedido statusPedido, Pageable sort);

    @Query("SELECT p FROM Pedido p JOIN p.user u where u.username = :username")
    List<Pedido> findAllByUsuario(@Param("username")String username);

    @Query("SELECT p FROM Pedido p JOIN p.user u where u.username = :username and p.statusPedido = :status")
    List<Pedido> findByStatusPedidoAndUser(@Param("status")StatusPedido status, @Param("username")String username);
}
