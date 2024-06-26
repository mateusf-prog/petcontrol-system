package com.mateus.petcontrolsystem.models;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import com.mateus.petcontrolsystem.common.UserConstants;
import com.mateus.petcontrolsystem.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

@DataJpaTest
public class UserRepositoryTest {

    @Autowired
    private UserRepository repository;

    @Autowired
    private TestEntityManager testEntityManager;

    @Test
    public void login_WithValidData_ReturnsLoginResponseDTO() {

        User userCreated = repository.save(UserConstants.getValidUser());

        User sut = testEntityManager.find(User.class, userCreated.getId());

        assertThat(sut).isNotNull();
        assertThat(sut.getPassword()).isEqualTo(userCreated.getPassword());
        assertThat(sut.getEmail()).isEqualTo(userCreated.getEmail());
    }

    @Test
    public void login_WithInvalidData_ReturnsThrowsException() {

        assertThatThrownBy(() -> repository.save(new User())).isInstanceOf(RuntimeException.class);
    }

    @Test
    public void login_WithAlreadyUser_ThrowsException() {

        var validUser = UserConstants.getValidUser();

        testEntityManager.persistFlushFind(validUser);
        testEntityManager.detach(validUser);
        validUser.setId(null);

        assertThatThrownBy(() -> repository.save(validUser)).isInstanceOf(RuntimeException.class);
    }
}
