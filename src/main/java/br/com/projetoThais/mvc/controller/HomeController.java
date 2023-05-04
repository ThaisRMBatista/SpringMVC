package br.com.projetoThais.mvc.controller;

import br.com.projetoThais.mvc.model.Pedido;
import br.com.projetoThais.mvc.model.StatusPedido;
import br.com.projetoThais.mvc.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
        List<Pedido> pedidos = pedidoRepository.findAllByUsuario(principal.getName());
        model.addAttribute("pedidos", pedidos);
        return "home";
    }

    @GetMapping("/{statusPedido}")
    public String porStatus(@PathVariable("statusPedido") String statusPedido, Model model) {
        List<Pedido> pedidos = pedidoRepository.findByStatusPedido(StatusPedido.valueOf(statusPedido.toUpperCase()));
        model.addAttribute("pedidos", pedidos);
        model.addAttribute("statusPedido", statusPedido);
        return "home";
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public String onError(){
        return "redirect:/home";
    }
}
