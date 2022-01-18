package com.dancecamp.newdancecamp.service;

import com.dancecamp.newdancecamp.repo.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import com.dancecamp.newdancecamp.repo.model.User;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public final class UserService {

    private final UserRepo userRepo;

    public List<User> fetchAll() {
        return userRepo.findAll();
    }

    public User fetchUserById(long id) throws IllegalArgumentException {
        final Optional<User> maybeUser = userRepo.findById(id);

        if (maybeUser.isPresent())
            return maybeUser.get();
        else
            throw new IllegalArgumentException("Wrong id");
    }

    public long create(String name, String surname, String email, String password, String role){
        final User user = new User(name, surname, email, password, role);
        final User savedUser = userRepo.save(user); //diff id

        return savedUser.getId();
    }

    public void update(long id, String name, String surname, String email, String password, String role)
            throws IllegalArgumentException
    {
        final Optional<User> maybeUser = userRepo.findById(id);

        if (maybeUser.isEmpty())
            throw new IllegalArgumentException("Wrong id");

        final User user = maybeUser.get();
        if (name != null && !name.isBlank()) user.setName(name);
        if (surname != null && !surname.isBlank()) user.setSurname(surname);
        if (email != null && !email.isBlank()) user.setEmail(email);
        if (password != null && !password.isBlank()) user.setPassword(password);
        if (role != null && ! role.isBlank()) user.setRole(role);
        userRepo.save(user);
    }

    public void deleteUser(long id){
        userRepo.deleteById(id);
    }
}
