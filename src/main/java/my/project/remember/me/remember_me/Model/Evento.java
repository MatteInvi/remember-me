package my.project.remember.me.remember_me.Model;

import java.time.LocalDate;

import org.hibernate.annotations.UuidGenerator;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "evento")
public class Evento {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Inserire titolo")
    private String title;

    private String description;

    @NotBlank(message = "Inserire email utente")
    @Email(message = "Inserire una email valida")
    private String userEmail;

    @NotNull(message = "Inserire data da ricordare")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate issuedAt;

    private boolean completed = false;

    private boolean sended = false;

    @UuidGenerator
    private String token;


    // Getters and Setters

    public String getToken() {
        return this.token;
    }

    public void setToken(String token) {
        this.token = token;
    }



    public String getUserEmail() {
        return this.userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }



    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getIssuedAt() {
        return this.issuedAt;
    }

    public void setIssuedAt(LocalDate issuedAt) {
        this.issuedAt = issuedAt;
    }

    public boolean isCompleted() {
        return this.completed;
    }

    public boolean getCompleted() {
        return this.completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public boolean isSended() {
        return this.sended;
    }

    public boolean getSended() {
        return this.sended;
    }

    public void setSended(boolean sended) {
        this.sended = sended;
    }


    
}
