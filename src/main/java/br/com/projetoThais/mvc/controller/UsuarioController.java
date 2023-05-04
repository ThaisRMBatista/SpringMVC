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
@RequestMapping("usuario")
public class UsuarioController {

    @Autowired
    private PedidoRepository pedidoRepository;

    @GetMapping("pedidos")
    public String home(Model model, Principal principal) {
        List<Pedido> pedidos = pedidoRepository.findAllByUsuario(principal.getName());
        model.addAttribute("pedidos", pedidos);
        return "usuario/home";
    }

    @GetMapping("pedidos/{statusPedido}")
    public String porStatus(@PathVariable("statusPedido") String statusPedido, Model model, Principal principal) {
        List<Pedido> pedidos = pedidoRepository.findByStatusPedidoAndUser(StatusPedido.valueOf(statusPedido.toUpperCase()),principal.getName());
        model.addAttribute("pedidos", pedidos);
        model.addAttribute("statusPedido", statusPedido);
        return "usuario/home";
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public String onError(){
        return "redirect:/usuario/home";
    }
}
