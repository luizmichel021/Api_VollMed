package med.voll.api.infra.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import med.voll.api.domain.usuario.Usuario;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;

import java.time.LocalDateTime;
import java.time.ZoneOffset;


@Service
public class TokenService {

    @Value("${api.security.token.secret}")  // fala pro spring pegar o valor declarado dentro do aplication propriertes e adicionar a minha variavel
    private String secret;

    public String gerarToken(Usuario usuario){
        try {
            var algoritmo = Algorithm.HMAC256(secret);
            return JWT.create()
                    .withIssuer("API Voll.med")
                    .withSubject(usuario.getLogin()) //mostrar quem e a pessoa relacionada com o token que foi gerado
                    .withExpiresAt(dataExpiracao())    // Define a duração de um token, expiração
                    .sign(algoritmo);
        } catch (JWTCreationException exception){
            throw new RuntimeException("erro ao gerar token jwt", exception);
        }
    }

    public String getSubject(String tokenJWT){
        try {
            var algoritmo = Algorithm.HMAC256(secret);
            return JWT.require(algoritmo)
                    .withIssuer("API Voll.med")
                    .build()
                    .verify(tokenJWT)
                    .getSubject();
        } catch (JWTVerificationException exception){
            throw  new RuntimeException("token invalido ou expirado");
        }
    }

    // Define a data e hora de expiração do token, adicionando 2 horas à data e hora atuais e ajustando para o fuso horário de Brasília (GMT-3).
    private Instant dataExpiracao() {
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }
}

//LocalDateTime.now()://
//O que faz: Pega a data e hora atuais do sistema.

//.plusHours(2):
//O que faz: Adiciona 2 horas à data e hora atuais.

//.toInstant(ZoneOffset.of("-03:00")):
//O que faz: Converte o LocalDateTime para um Instant, que representa um ponto específico no tempo.
// O ZoneOffset.of("-03:00") define o fuso horário para o horário de Brasília (GMT-3).