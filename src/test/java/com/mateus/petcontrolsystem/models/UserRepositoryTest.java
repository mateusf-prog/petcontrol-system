package com.mateus.petcontrolsystem.models;

import static com.mateus.petcontrolsystem.common.LoginConstants.LOGIN;
import static org.assertj.core.api.Assertions.assertThat;

import com.mateus.petcontrolsystem.common.LoginConstants;
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

        var object = LoginConstants.convertDtoToUser(LOGIN);

        User user = repository.save(object);

        User sut = testEntityManager.find(User.class, user.getId());

        assertThat(sut).isNotNull();
        assertThat(sut.getPassword()).isEqualTo(object.getPassword());
        assertThat(sut.getEmail()).isEqualTo(object.getEmail());

    }
}
