package com.mateus.petcontrolsystem.models;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import com.mateus.petcontrolsystem.common.UserConstants;
import com.mateus.petcontrolsystem.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Profile;

import java.util.Optional;

/**
 * This class tests all methods in the UserRepository that using in the UserService
 */
@DataJpaTest
public class UserRepositoryTest {

    @Autowired
    private UserRepository repository;

    @Autowired
    private TestEntityManager testEntityManager;

    @Test
    public void findByEmail_WithExistsUserByEmail_ReturnsUser() {

        var validUser = UserConstants.getValidUser();
        testEntityManager.persistAndFlush(validUser);

        Optional<User> foundUser = repository.findByEmail(validUser.getEmail());

        assertThat(foundUser).isNotEmpty();
        assertThat(foundUser.get().getEmail()).isEqualTo(validUser.getEmail());
    }

    @Test
    public void findByEmail_WithNonExistentUserByEmail_ReturnsUser() {

        var validUser = UserConstants.getValidUser();

        var sut = repository.findByEmail(validUser.getEmail());

        assertThat(sut).isEqualTo(Optional.empty());
    }

    @Test
    public void save_WithValidData_ReturnsUser() {

        var validUser = UserConstants.getValidUser();

        var sut = repository.save(validUser);

        assertThat(sut).isNotNull();
        assertThat(sut.getEmail()).isEqualTo(validUser.getEmail());
    }

    @Test
    public void save_WithInvalidData_ReturnsThrowsException() {

        var invalidUser = UserConstants.getInvalidUser();

        assertThatThrownBy(() -> repository.save(invalidUser)).isInstanceOf(RuntimeException.class);
    }

    @Test
    public void save_WithAlreadyUserByEmail_ReturnsThrowsException() {

        var validUser = UserConstants.getValidUser();

        testEntityManager.persistAndFlush(validUser);
        testEntityManager.detach(validUser);
        validUser.setId(null);

        assertThatThrownBy(() -> repository.save(validUser)).isInstanceOf(RuntimeException.class);
    }

    @Test
    public void update_WithValidData_ReturnsUser() {

        var validUser = UserConstants.getValidUser();
        testEntityManager.persistAndFlush(validUser);

        var userFound = repository.findByEmailOrCpfCnpj(validUser.getEmail(), validUser.getCpfCnpj());
        userFound.setName("Maria");
        userFound.setPhone("11111111111");

        var sut = repository.save(userFound);

        assertThat(sut).isNotNull();
        assertThat(sut.getName()).isEqualTo(userFound.getName());
        assertThat(sut.getPhone()).isEqualTo(userFound.getPhone());
    }

    @Test
    public void update_WithInvalidData_ReturnThrowsException() {

        var validUser = UserConstants.getValidUser();
        testEntityManager.persistAndFlush(validUser);

        var userFound = repository.findByEmailOrCpfCnpj(validUser.getEmail(), validUser.getCpfCnpj());
        userFound.setName(null); // invalid name

        assertThatThrownBy(() -> {
            repository.save(userFound);
            testEntityManager.flush();
        }).isInstanceOf(RuntimeException.class);
    }

}
