package com.distance_learning.service.impl;

import com.distance_learning.data.models.Role;
import com.distance_learning.data.models.User;
import com.distance_learning.data.repositories.RoleRepository;
import com.distance_learning.data.repositories.UserRepository;
import com.distance_learning.service.models.UserServiceRegisterModel;
import com.distance_learning.service.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final RoleRepository roleRepository;
    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper, BCryptPasswordEncoder bCryptPasswordEncoder, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.roleRepository = roleRepository;
    }

    @Override
    public void registerUser(UserServiceRegisterModel model) {

        User user = modelMapper.map(model, User.class);
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        if(userRepository.count() == 0) {
            user.setAuthorities(new HashSet<>(this.roleRepository.findAll()));
        } else {
            Set<Role> authorities = new HashSet<>(Set.of(this.roleRepository.findByAuthority("USER"),
                    this.roleRepository.findByAuthority(model.getRole())));
            user.setAuthorities(authorities);
        }


        userRepository.saveAndFlush(user);
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return this.userRepository.findByUsername(username);
    }
}
