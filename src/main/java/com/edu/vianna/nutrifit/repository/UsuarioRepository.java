package com.edu.vianna.nutrifit.repository;

import com.edu.vianna.nutrifit.models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UsuarioRepository extends JpaRepository<Usuario,Long> {
    public Usuario findByNome(String nome);
    public List<Usuario> findByNomeLike(String nome);
    Usuario findByLogin(String login);
    public Usuario findByLoginAndSenha(String login,String senha);

}
