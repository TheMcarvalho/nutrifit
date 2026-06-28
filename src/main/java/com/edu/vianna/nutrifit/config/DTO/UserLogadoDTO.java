package com.edu.vianna.nutrifit.config.DTO;

import jakarta.annotation.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

public class UserLogadoDTO extends User {
    private long idUser;
    public UserLogadoDTO(String username, @Nullable String password, Collection<?extends GrantedAuthority>authorities,long idUser){
        super(username,password,authorities);
        setIdUser(idUser);
    }
    public long getIdUser() {
        return idUser;
    }

    public void setIdUser(long idUser) {
        this.idUser = idUser;
    }
}
