package com.mateus.petcontrolsystem.models;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import com.mateus.petcontrolsystem.common.UserConstants;
import com.mateus.petcontrolsystem.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

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
    public void findByEmail_WithNonExistentUserByEmail_ReturnsEmptyOptional() {

        var validUser = UserConstants.getValidUser();

        var sut = repository.findByEmail(validUser.getEmail());

        assertThat(sut).isEmpty();
    }

    @Test
    public void findByCpfCnpj_WithExistsUserByCpfCnpj_ReturnsUser() {

        var validUser = UserConstants.getValidUser();
        testEntityManager.persistAndFlush(validUser);

        var sut = repository.findByCpfCnpj(validUser.getCpfCnpj());

        assertThat(sut).isEqualTo(Optional.of(validUser));
        assertThat(sut.get().getName()).isEqualTo(validUser.getName());
        assertThat(sut.get().getCpfCnpj()).isEqualTo(validUser.getCpfCnpj());
    }

    @Test
    public void findByCpfCnpj_WithNonExistentUserByCpfCnpj_ReturnsEmptyOptional() {

        var validUser = UserConstants.getValidUser();

        var sut = repository.findByCpfCnpj(validUser.getCpfCnpj());

        assertThat(sut).isEmpty();
    }

    @Test
    public void findByEmailOrCpfCnpj_WithValidEmailAndCpfCnpj_ReturnsUser() {

        var validUser = UserConstants.getValidUser();
        testEntityManager.persistAndFlush(validUser);

        var sut = repository.findByEmailOrCpfCnpj(validUser.getEmail(), validUser.getCpfCnpj());

        assertThat(sut).isNotNull();
        assertThat(sut.getEmail()).isEqualTo(validUser.getEmail());
    }

    @Test
    public void findByEmailOrCpfCnpj_WithInvalidEmailAndValidCpfCnpj_ReturnsUser() {

        var validUser= UserConstants.getValidUser();
        testEntityManager.persistAndFlush(validUser);
        testEntityManager.detach(validUser);

        var sut = repository.findByEmailOrCpfCnpj(null, validUser.getCpfCnpj());

        assertThat(sut).isNotNull();
        assertThat(sut.getEmail()).isEqualTo(validUser.getEmail());
        assertThat(sut.getCpfCnpj()).isEqualTo(validUser.getCpfCnpj());
    }

    @Test
    public void findByEmailOrCpfCnpj_WithValidEmailAndInvalidCpfCnpj_ReturnsUser() {

        var validUser= UserConstants.getValidUser();
        testEntityManager.persistAndFlush(validUser);
        testEntityManager.detach(validUser);

        var sut = repository.findByEmailOrCpfCnpj(validUser.getEmail(), null);

        assertThat(sut).isNotNull();
        assertThat(sut.getEmail()).isEqualTo(validUser.getEmail());
        assertThat(sut.getCpfCnpj()).isEqualTo(validUser.getCpfCnpj());
    }

    @Test
    public void findById_WithValidId_ReturnsUser() {

        var validUser= UserConstants.getValidUser();
        validUser = testEntityManager.persistAndFlush(validUser);
        testEntityManager.detach(validUser);

        var sut = repository.findById(validUser.getId());

        assertThat(sut).isNotNull();
        assertThat(sut.get().getEmail()).isEqualTo(validUser.getEmail());
        assertThat(sut.get().getCpfCnpj()).isEqualTo(validUser.getCpfCnpj());
    }

    @Test
    public void findById_WithNonExistingUserById_ReturnsEmptyOptional() {

        var nonExistingUserId = 1L;

        var sut = repository.findById(nonExistingUserId);

        assertThat(sut).isEmpty();
    }

    @Test
    public void save_WithValidData_ReturnsUser() {

        var validUser = UserConstants.getValidUser();

        var sut = repository.save(validUser);

        assertThat(sut).isNotNull();
        assertThat(sut.getEmail()).isEqualTo(validUser.getEmail());
    }

    @Test
    public void save_WithAlreadyUserByEmail_ThrowsException() {

        var validUser = UserConstants.getValidUser();

        testEntityManager.persistAndFlush(validUser);
        testEntityManager.detach(validUser);
        validUser.setId(null);

        assertThatThrownBy(() -> repository.save(validUser)).isInstanceOf(RuntimeException.class);
    }

    @Test
    public void update_WithValidData_ReturnsUpdatedUser() {

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

}
