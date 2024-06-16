package com.mateus.petcontrolsystem.repositories;

import com.mateus.petcontrolsystem.models.User;
import com.mateus.petcontrolsystem.projections.UserDetailsProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmail(String email);

    @Query(nativeQuery = true, value = """
            SELECT tb_users.email AS username, tb_users.password, tb_roles.id AS roleId, tb_roles.authority
            FROM tb_users
            INNER JOIN tb_user_role ON tb_users.id = tb_user_role.user_id
            INNER JOIN tb_roles ON tb_roles.id = tb_user_role.role_id
            WHERE tb_users.email = :email
        """)
    List<UserDetailsProjection> searchUserAndRolesByEmail(String email);
}
