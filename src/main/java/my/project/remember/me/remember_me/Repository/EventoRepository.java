package my.project.remember.me.remember_me.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import my.project.remember.me.remember_me.Model.Evento;

public interface EventoRepository extends JpaRepository<Evento, Long> {
    //Metodo per trovare evento tramite token
    Optional<Evento> findByToken(String token);
}
