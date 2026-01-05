package my.project.remember.me.remember_me.Service;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import my.project.remember.me.remember_me.Model.Evento;
import my.project.remember.me.remember_me.Repository.EventoRepository;

@Component
public class CheckExpiredEvents {
    
    @Autowired
    EventoRepository eventoRepository;

    @Autowired
    EmailService emailService;

    @Scheduled(fixedDelay = 60 * 1000) //ogni minuto
    private void checkExpiredEvents() {
        //logica per controllare eventi scaduti
        for (Evento singleEvento : eventoRepository.findAll()) {
            //se evento scaduto e non notificato
            if (singleEvento.getIssuedAt().isBefore(LocalDate.now()) && !singleEvento.isSended()) {
                //invia email
                emailService.sendAlertEmail(singleEvento);
                //imposta evento come notificato
                singleEvento.setSended(true);
                eventoRepository.save(singleEvento);
            }            
        }
        
    }

}
