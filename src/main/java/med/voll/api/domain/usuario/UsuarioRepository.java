package med.voll.api.domain.usuario;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;


public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    UserDetails findByLogin(String login);
}


//create table consultas(
//
//
//    id bigint not null auto_increment,
//    medico_id  bigint not null,
//    paciente_id bigint not null,
//    data datetime not null,
//
//    primary key (id),
//    constraint fk_consultas_medico_id foreign key(medico_id) references medicos(id),
//    constraint fk_consultas_paciente_id foreign key(paciente_id) references pacientes(id)
//);