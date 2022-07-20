package com.pedroeugenio.helpdesk.services;

import com.pedroeugenio.helpdesk.domain.Pessoa;
import com.pedroeugenio.helpdesk.repositories.PessoaRepository;
import com.pedroeugenio.helpdesk.security.UserSS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private PessoaRepository pessoaRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<Pessoa> user = pessoaRepository.findByEmail(email);
        if (user.isPresent()) {
            return new UserSS(user.get().getId(), user.get().getEmail(), user.get().getSenha(), user.get().getPerfils());
        }
        throw new UsernameNotFoundException(email);
    }
}
