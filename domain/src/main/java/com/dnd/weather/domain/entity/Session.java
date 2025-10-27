package com.dnd.weather.domain.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "session")
public class Session extends AbstractEntity {

    @ManyToOne
    private UserData userData;

    private String name;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "session_state_id")
    private SessionState sessionState;

}
