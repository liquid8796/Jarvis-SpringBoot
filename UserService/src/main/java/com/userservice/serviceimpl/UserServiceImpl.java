package com.userservice.serviceimpl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.userservice.dto.UserDTO;
import com.userservice.entity.Role;
import com.userservice.entity.User;
import com.userservice.handler.JarvisException;
import com.userservice.model.Response;
import com.userservice.repository.RoleRepository;
import com.userservice.repository.UserRepository;
import com.userservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import static java.util.stream.Collectors.toList;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public Response<List<UserDTO>> getUsers() {
        List<User> users = userRepository.findAll();

        List<UserDTO> result = users.stream()
                .map(d -> objectMapper.convertValue(d, UserDTO.class))
                .collect(toList());
        return new Response<>(200, "Success", result);
    }

    @Override
    public Response<UserDTO> getUserById(Long id) {
        User result = userRepository.findById(id).orElseThrow(() -> new JarvisException("Role not found.", 404));

        return new Response<>(200, "Success",objectMapper.convertValue(result, UserDTO.class));
    }

    @Override
    public Response<UserDTO> addUser(UserDTO dto) {
        User user = objectMapper.convertValue(dto, User.class);
        Role role = roleRepository.findById(dto.getRole().getRole_id()).orElseThrow(() -> new JarvisException("Role not found.", 404));
        user.setRole(role);
        return new Response<>(200, "Success", objectMapper.convertValue(userRepository.save(user), UserDTO.class));
    }

    @Override
    public Response<UserDTO> updateUser(UserDTO user) {
        return null;
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.findById(id).orElseThrow(() -> new JarvisException("Role not found.", 404));
        userRepository.deleteById(id);
    }
}
