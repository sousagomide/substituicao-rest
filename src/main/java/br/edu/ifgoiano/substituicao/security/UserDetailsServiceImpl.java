package br.edu.ifgoiano.substituicao.security;

import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.edu.ifgoiano.substituicao.model.Autenticacao;
import br.edu.ifgoiano.substituicao.repository.AutenticacaoRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private AutenticacaoRepository autenticacaoRepository;
	
	@Override
	public UserDetails loadUserByUsername(String usuario) throws UsernameNotFoundException {
		Optional<Autenticacao> autenticacaoOptional = autenticacaoRepository.findByUsuario(usuario);
		Autenticacao autenticacao = autenticacaoOptional.orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado"));
		return new User(usuario, autenticacao.getSenha(), getPermissoes(autenticacao));
	}

	private Collection<? extends GrantedAuthority> getPermissoes(Autenticacao autenticacao) {
		Set<SimpleGrantedAuthority> authorities = new HashSet<>();
		
		// As ROLES adicionadas devem estar dentro da autenticacao, ex:
		// ROLE_USER, ROLE_ADMIN, ROLE_GUEST
		authorities.add(new SimpleGrantedAuthority("GENERIC_ROLE"));
		
		return authorities;
	}

}
