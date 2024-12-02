package com.balcao.uff.service;

import com.balcao.uff.domain.User;
import com.balcao.uff.domain.dtos.UserDto;
import com.balcao.uff.repository.UserRepository;
import com.balcao.uff.service.exceptions.DatabaseException;
import com.balcao.uff.service.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User findByEmail(String email) {
        try {
            return userRepository.findByEmail(email);
        } catch (ResourceNotFoundException e) {
            throw new ResourceNotFoundException("User with email " + email + " not found");
        }
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public UserDto findById(Long id) {
        Optional<User> obj = userRepository.findById(id);

        final User user = obj.orElseThrow(()-> new ResourceNotFoundException(id));
        return new UserDto(user);
    }

    public void delete(Long id) {
        try {
            userRepository.deleteById(id);
        }
        catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException(id);
        }
        catch (DataIntegrityViolationException e) {
            throw new DatabaseException(e.getMessage());
        }
    }

    public UserDto update(UserDto obj) {
        if (obj.getId() == null) {
            throw new DatabaseException("id is required");
        }

        try {
            final Optional<User> objUser = userRepository.findById(obj.getId());

            final User user = objUser.orElseThrow(() -> new ResourceNotFoundException(obj.getId()));
            user.copyDto(obj);
            return new UserDto(userRepository.save(user));
        } catch (RuntimeException e) {
            throw new DatabaseException(e.getMessage());
        }
    }

    public User insert(User user) {
        if (user.getId() != null) {
            throw new DatabaseException("Esse usuário já existe");
        }
        return userRepository.save(user);
    }
}
