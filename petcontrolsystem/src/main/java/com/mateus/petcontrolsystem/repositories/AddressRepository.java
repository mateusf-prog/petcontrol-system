package com.mateus.petcontrolsystem.repositories;

import com.mateus.petcontrolsystem.models.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {
}
