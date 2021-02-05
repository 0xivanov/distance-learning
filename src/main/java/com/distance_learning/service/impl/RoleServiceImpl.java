package com.distance_learning.service.impl;

import com.distance_learning.data.models.Role;
import com.distance_learning.data.repositories.RoleRepository;
import com.distance_learning.service.services.RoleService;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public void seedRolesInDb() {
        if(roleRepository.count() == 0) {
            this.roleRepository.saveAndFlush(new Role("ADMIN"));
            this.roleRepository.saveAndFlush(new Role("USER"));
            this.roleRepository.saveAndFlush(new Role("STUDENT"));
            this.roleRepository.saveAndFlush(new Role("TEACHER"));
        }
    }
}
