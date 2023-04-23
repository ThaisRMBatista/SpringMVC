package br.com.projetoThais.mvc.controller;

import br.com.projetoThais.mvc.dto.RequisicaoNovoPedido;
import br.com.projetoThais.mvc.model.Pedido;
import br.com.projetoThais.mvc.repository.PedidoRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("pedido")
public class PedidoController {

    @Autowired
    private PedidoRepository repository;

    @GetMapping("/formulario")
    public String formulario() {
        return "pedido/formulario";
    }

    @PostMapping("novo")
    public String novo(@Valid RequisicaoNovoPedido requisicao, BindingResult result) {
        if (result.hasErrors()) {
            return "pedido/formulario";
        }
        Pedido novoPedido = requisicao.toPedido();
        repository.save(novoPedido);

        return "pedido/formulario";
    }

}
