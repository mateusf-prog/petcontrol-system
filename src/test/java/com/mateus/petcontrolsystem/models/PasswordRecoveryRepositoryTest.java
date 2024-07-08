package com.mateus.petcontrolsystem.models;

import com.mateus.petcontrolsystem.common.PasswordRecoveryConstants;
import com.mateus.petcontrolsystem.repositories.PasswordRecoveryRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

@DataJpaTest
public class PasswordRecoveryRepositoryTest {

    @Autowired
    private PasswordRecoveryRepository repository;

    @Autowired
    private TestEntityManager testEntityManager;

    @Test
    public void findByUserEmail_WithExistsEntityByEmail_ReturnsEntity() {

        var validEntity = PasswordRecoveryConstants.getValidPasswordEntity();
        testEntityManager.persistAndFlush(validEntity);

        var sut = repository.findByUserEmail(validEntity.getUserEmail());

        assertThat(sut).isEqualTo(Optional.of(validEntity));
        assertThat(sut.get().getUserEmail()).isEqualTo(validEntity.getUserEmail());
    }

    @Test
    public void findByUserEmail_WithNonExistentEntityByEmail_ThrowsException() {

        var validEntity = new PasswordRecovery();

        var sut = repository.findByUserEmail(validEntity.getUserEmail());

        assertThat(sut).isEqualTo(Optional.empty());
    }

    @Test
    public void save_WithValidData_ReturnsEntity() {

        var validEntity = PasswordRecoveryConstants.getValidPasswordEntity();

        var sut = repository.save(validEntity);

        assertThat(sut).isEqualTo(validEntity);
        assertThat(sut.getUserEmail()).isEqualTo(validEntity.getUserEmail());
        assertThat(sut.getRecoveryCode()).isEqualTo(validEntity.getRecoveryCode());
    }

    @Test
    public void save_WithInvalidData_ThrowsException() {

        assertThatThrownBy(() -> repository.save(new PasswordRecovery())).isInstanceOf(RuntimeException.class);
    }
}
