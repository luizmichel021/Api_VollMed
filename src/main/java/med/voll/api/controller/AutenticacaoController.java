package med.voll.api.controller;


import jakarta.validation.Valid;
import med.voll.api.domain.usuario.DadosAutenticacao;
import med.voll.api.domain.usuario.Usuario;
import med.voll.api.infra.security.DadosTokenJWT;
import med.voll.api.infra.security.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("login")
public class AutenticacaoController {

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity efetuarLogin(@RequestBody @Valid DadosAutenticacao dados){
        var authenticationToken = new UsernamePasswordAuthenticationToken(dados.login(), dados.senha());
        var authentication = manager.authenticate(authenticationToken);

        var tokenJWT = tokenService.gerarToken((Usuario) authentication.getPrincipal());
        return ResponseEntity.ok(new DadosTokenJWT(tokenJWT));
    }
}



//Que legal que você está explorando o código do AutenticacaoController! 🤩  Vamos analisar passo a passo o que cada parte faz:1. @RestController:
//O que faz:  Indica que a classe é um controlador REST, responsável por lidar com requisições HTTP e retornar respostas em formato JSON.
//
//2. @RequestMapping("login"):
//O que faz: Define o caminho base para as requisições que serão direcionadas para este controlador. Neste caso, todas as requisições para /login serão processadas por este controlador.
//
//3. @Autowired:
//O que faz: Injeta as dependências necessárias para o controlador. No caso, o AuthenticationManager e o TokenService.
//
//4. private AuthenticationManager manager;:
//O que faz: Declara um atributo privado chamado manager do tipo AuthenticationManager. O AuthenticationManager é responsável por autenticar usuários, verificando se as credenciais fornecidas (usuário e senha) são válidas.
//
//5. private TokenService tokenService;:
//O que faz: Declara um atributo privado chamado tokenService do tipo TokenService. O TokenService é responsável por gerar tokens JWT para usuários autenticados.
//
//6. @PostMapping:
//O que faz: Define que o método efetuarLogin será chamado quando uma requisição HTTP POST for feita para o caminho /login.
//
//7. public ResponseEntity efetuarLogin(@RequestBody @Valid DadosAutenticacao dados):
//O que faz:  Define o método efetuarLogin, que recebe um objeto DadosAutenticacao no corpo da requisição. A anotação @RequestBody indica que os dados da requisição serão mapeados para o objeto DadosAutenticacao. A anotação @Valid indica que os dados serão validados antes de serem processados.
//
//8. var token = new UsernamePasswordAuthenticationToken(dados.login(), dados.senha());:
//O que faz: Cria um objeto UsernamePasswordAuthenticationToken com o login e a senha do usuário. Este objeto representa a tentativa de autenticação do usuário.
//
//9. var authentication = manager.authenticate(token);:
//O que faz: Chama o método authenticate do AuthenticationManager para verificar se as credenciais do usuário são válidas. Se as credenciais forem válidas, o método retorna um objeto Authentication que representa o usuário autenticado.
//
//10. return ResponseEntity.ok(tokenService.gerarToken((Usuario) authentication.getPrincipal()));:
//O que faz: Se a autenticação for bem-sucedida, o método gera um token JWT usando o TokenService e retorna o token como resposta para o cliente. O authentication.getPrincipal() retorna o usuário autenticado, que é convertido para o tipo Usuario.
//
//Em resumo, o código do AutenticacaoController:
//Recebe as credenciais de login do usuário.
//Autentica o usuário usando o AuthenticationManager.
//Gera um token JWT para o usuário autenticado usando o TokenService.
//Retorna o token JWT para o cliente.
//
//Espero que esta explicação tenha sido útil! 😊  Continue explorando o código e me chame sempre que precisar de ajuda! 💪
