package br.com.projetoThais.mvc.api;

import br.com.projetoThais.mvc.dto.RequisicaoNovoOferta;
import br.com.projetoThais.mvc.model.Oferta;
import br.com.projetoThais.mvc.model.Pedido;
import br.com.projetoThais.mvc.repository.PedidoRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/ofertas")
public class OfertasRest {

    @Autowired
    private PedidoRepository pedidoRepository;

    @PostMapping
    public Oferta criaOferta(@Valid @RequestBody RequisicaoNovoOferta resquisicao) {
        Optional<Pedido> pedidoBuscado = pedidoRepository.findById(resquisicao.getPedidoId());
        if (pedidoBuscado.isEmpty()) {
            return null;
        }

        Pedido pedido = pedidoBuscado.get();

        Oferta nova = resquisicao.toOferta();
        nova.setPedido(pedido);
        pedido.getOfertas().add(nova);
        pedidoRepository.save(pedido);

        return nova;
    }
}
