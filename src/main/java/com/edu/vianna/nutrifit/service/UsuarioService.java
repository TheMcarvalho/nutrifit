package com.edu.vianna.nutrifit.service;

import com.edu.vianna.nutrifit.models.Usuario;
import com.edu.vianna.nutrifit.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {
    @Autowired
    UsuarioRepository usuRepo;

    public Usuario getUsuarioPorNome(String nome){
        return usuRepo.findByNome(nome);
    }
    public Usuario getUsuarioLonginAndSenha(String login,String senha){
        return usuRepo.findByLoginAndSenha(login, senha);
    }
    public List<Usuario> getListaUsuarioNome(String nome){
        return usuRepo.findByNomeLike(nome);
    }
    public Usuario getUsuarioPorLogin(String login){
        return usuRepo.findByLogin(login);
    }
    public Usuario salvarUsuario(Usuario usuario){
        return usuRepo.save(usuario);
    }
    public void deletarUsuario(long id){
        usuRepo.deleteById(id);
    }
}
