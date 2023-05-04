package br.com.projetoThais.mvc.controller;

import br.com.projetoThais.mvc.dto.RequisicaoNovoPedido;
import br.com.projetoThais.mvc.model.Pedido;
import br.com.projetoThais.mvc.model.User;
import br.com.projetoThais.mvc.repository.PedidoRepository;
import br.com.projetoThais.mvc.repository.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
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

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/formulario")
    public String formulario(RequisicaoNovoPedido requisicao) {
        return "pedido/formulario";
    }

    @PostMapping("novo")
    public String novo(@Valid RequisicaoNovoPedido requisicao, BindingResult result) {
        if (result.hasErrors()) {
            return "pedido/formulario";
        }

        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByUsername(username);

        Pedido novoPedido = requisicao.toPedido();
        novoPedido.setUser(user);

        repository.save(novoPedido);

        return "redirect:/home";
    }

}
