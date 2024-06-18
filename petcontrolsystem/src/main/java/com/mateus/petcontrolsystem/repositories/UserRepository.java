package com.mateus.petcontrolsystem.repositories;

import com.mateus.petcontrolsystem.models.User;
import com.mateus.petcontrolsystem.projections.UserDetailsProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

    @Query("SELECT u FROM User u WHERE u.email = :email OR u.cpfCnpj = :cpfCnpj")
    List<User> findByEmailOrCpfCnpj(@Param("email") String email, @Param("cpfCnpj") String cpfCnpj);

    @Query(nativeQuery = true, value = """
            SELECT tb_users.email AS username, tb_users.password, tb_roles.id AS roleId, tb_roles.authority
            FROM tb_users
            INNER JOIN tb_user_role ON tb_users.id = tb_user_role.user_id
            INNER JOIN tb_roles ON tb_roles.id = tb_user_role.role_id
            WHERE tb_users.email = :email
        """)
    List<UserDetailsProjection> searchUserAndRolesByEmail(String email);
}
