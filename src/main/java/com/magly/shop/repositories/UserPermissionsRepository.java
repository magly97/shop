package com.magly.shop.repositories;

import com.magly.shop.modules.UserPermissions;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserPermissionsRepository extends JpaRepository<UserPermissions, Long> {
}
