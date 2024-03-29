package com.AtlasVoteGate.AtlasVoteGate.model;

import com.AtlasVoteGate.AtlasVoteGate.enums.Role;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")

public  class Utilisateur implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String login;
    private Role role;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    private String nom;
    private String prenom;

    public Utilisateur(String login, Role roleVoter, String password, String nom, String prenom) {
        this.login=login;
        this.role=roleVoter;
        this.password=password;
        this.nom=nom;
        this.prenom=prenom;
    }
}
