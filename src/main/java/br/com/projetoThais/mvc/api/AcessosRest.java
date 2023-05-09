package br.com.projetoThais.mvc.api;

import br.com.projetoThais.mvc.interceptor.InterceptadorDeAcessos;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("acessos")
@RestController
public class AcessosRest {

    @GetMapping
    public List<InterceptadorDeAcessos.Acesso> getAcessos() {
        return InterceptadorDeAcessos.acessos;
    }
}
