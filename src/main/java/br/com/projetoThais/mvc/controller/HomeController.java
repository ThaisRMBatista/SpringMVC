package br.com.projetoThais.mvc.controller;

import br.com.projetoThais.mvc.model.Pedido;
import br.com.projetoThais.mvc.model.StatusPedido;
import br.com.projetoThais.mvc.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/home")
public class HomeController {

    @Autowired
    private PedidoRepository pedidoRepository;

    @GetMapping
    public String home(Model model, Principal principal) {

        Sort sort = Sort.by("dataDeEntrega").descending();
        PageRequest paging = PageRequest.of(0, 10, sort);

        List<Pedido> pedidos = pedidoRepository.findByStatusPedido(StatusPedido.ENTREGUE, paging);
        model.addAttribute("pedidos", pedidos);
        return "home";
    }

}
