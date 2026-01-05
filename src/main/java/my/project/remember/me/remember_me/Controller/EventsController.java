package my.project.remember.me.remember_me.Controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import my.project.remember.me.remember_me.Model.Evento;
import my.project.remember.me.remember_me.Repository.EventoRepository;

@RestController
@RequestMapping("/api/event")
public class EventsController {

    @Autowired
    EventoRepository eventoRepository;

    // Mostra evento per id
    @GetMapping("/show/{id}")
    public ResponseEntity<Evento> getEventById(@PathVariable Long id) {
        Optional<Evento> eventoOptional = eventoRepository.findById(id);
        if (eventoOptional.isPresent()) {
            return ResponseEntity.ok(eventoOptional.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Creazione evento
    @PostMapping("/create")
    public ResponseEntity<?> createEvent(@Valid @RequestBody Evento evento, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.status(400).build();
        }

        try {
            Evento savedEvento = eventoRepository.save(evento);
            return ResponseEntity.ok(savedEvento);
        } catch (Exception e) {
            return ResponseEntity.status(500).build();
        }

    }

    // Aggiorna stato evento a completato tramite token
    @GetMapping("/updateStatus/{token}")
    public ResponseEntity<?> updateEventStatus(@PathVariable String token) {
        // Ricerca evento per token
        if (token == null || token.isEmpty()) {
            return ResponseEntity.status(400).body("Token mancante");
        }

        try {
            Optional<Evento> eventoOptional = eventoRepository.findByToken(token);
            Evento evento = eventoOptional.get();
            evento.setCompleted(true);
            eventoRepository.save(evento);
            return ResponseEntity.status(200).body("Stato evento aggiornato con successo");
        } catch (Exception e) {
            return ResponseEntity.status(404).build();
        }

    }
}
