package com.dnd.weather_app.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "user_data",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "email")
        })
public class UserData extends AbstractEntity {

    private String email;

    private String password;

    private boolean isSubscribed;

    public UserData(String password, String email) {
        this.password = password;
        this.email = email;
    }

}
