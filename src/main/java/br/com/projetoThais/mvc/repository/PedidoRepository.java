package br.com.projetoThais.mvc.repository;

import br.com.projetoThais.mvc.model.Pedido;
import br.com.projetoThais.mvc.model.StatusPedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {

    List<Pedido> findByStatusPedido(StatusPedido statusPedido);
}
