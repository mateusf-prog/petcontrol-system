package com.mateus.petcontrolsystem.services;

import com.mateus.petcontrolsystem.dto.UserLoginDTO;
import com.mateus.petcontrolsystem.models.User;
import com.mateus.petcontrolsystem.repositories.UserRepository;
import com.mateus.petcontrolsystem.services.exceptions.ResourceNotFoundException;
import com.mateus.petcontrolsystem.services.exceptions.InvalidPasswordException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository repository, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional(readOnly = true)
    public UserLoginDTO userLogin(UserLoginDTO dto) {
        Optional<User> entity = repository.findByEmail(dto.getUsername());

        if (entity.isEmpty()) {
            throw new ResourceNotFoundException("USUÁRIO NÃO ENCONTRADO");
        }

        User user = entity.get();
        if (!user.getPassword().equals(dto.getPassword())) {
            throw new InvalidPasswordException("SENHA INVÁLIDA");
        }
        return new UserLoginDTO(user);
    }







//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//
//        List<UserDetailsProjection> result = repository.searchUserAndRolesByEmail(username);
//        if (result.isEmpty()) {
//            throw new UsernameNotFoundException("User not found");
//        }
//        User user = new User();
//        user.setEmail(username);
//        user.setPassword(result.get(0).getPassword());
//        for (UserDetailsProjection projection : result) {
//            user.addRole(new Role(projection.getRoleId(), projection.getAuthority()));
//        }
//        return user;
//    }
}
