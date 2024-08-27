package com.estoque.lelu.model;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "usuarios")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String login;
    private String senha;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "usuario_role", joinColumns = @JoinColumn(name = "usuario_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;

    // Construtor sem id para quando você criar novos usuários
    public Usuario(String login, String senha, Set<Role> roles) {
        this.login = login;
        this.senha = senha;
        this.roles = roles;
    }

    // Construtor com todos os parâmetros
    public Usuario(Long id, String login, String senha, Set<Role> roles) {
        this.id = id;
        this.login = login;
        this.senha = senha;
        this.roles = roles;
    }
}
