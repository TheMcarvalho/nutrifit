package com.edu.vianna.nutrifit.config;

import com.edu.vianna.nutrifit.config.DTO.UserLogadoDTO;
import com.edu.vianna.nutrifit.models.Admin;
import com.edu.vianna.nutrifit.models.Usuario;
import com.edu.vianna.nutrifit.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UsuarioDetailsService implements UserDetailsService {
    @Autowired
    UsuarioRepository usoRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = usoRepo.findByLogin(username);
        if(usuario == null){
            throw new UsernameNotFoundException("Login não encontrado");
        }
        ArrayList<SimpleGrantedAuthority> lista = new ArrayList<>();
        lista.add(new SimpleGrantedAuthority(
                (usuario instanceof Admin) ? "ROLE_ADMIN" : "ROLE_CLIENTE"
        ));
        return new UserLogadoDTO(usuario.getLogin(),usuario.getSenha(),lista,usuario.getId());
    }
}
