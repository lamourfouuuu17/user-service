package com.dancecamp.newdancecamp.api;

import com.dancecamp.newdancecamp.repo.model.User;
import com.dancecamp.newdancecamp.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/users")
public final class UsersController {

    private final UserService userService;

    @GetMapping
    public ResponseEntity<List<com.dancecamp.newdancecamp.repo.model.User>> index() {
        final List<com.dancecamp.newdancecamp.repo.model.User> users = userService.fetchAll();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/{id}")
    public ResponseEntity<com.dancecamp.newdancecamp.repo.model.User> showById(@PathVariable long id) {
        try {
            final com.dancecamp.newdancecamp.repo.model.User user = userService.fetchUserById(id);
            return ResponseEntity.ok(user);
        }
        catch (IllegalArgumentException error){
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody com.dancecamp.newdancecamp.api.dto.User user) {
        final String name = user.getName();
        final String surname = user.getSurname();
        final String email = user.getEmail();
        final String password = user.getPassword();
        final String role = user.getRole();
        final long userId = userService.create(name, surname, email, password, role);
        final String userUri = String.format("/users/%d", userId);

        return ResponseEntity.created(URI.create(userUri)).build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable long id, @RequestBody com.dancecamp.newdancecamp.api.dto.User user) {
        final String name = user.getName();
        final String surname = user.getSurname();
        final String email = user.getEmail();
        final String password = user.getPassword();
        final String role = user.getRole();
        try {
            userService.update(id, name, surname, email, password, role);
            return ResponseEntity.noContent().build();
        }
        catch (IllegalArgumentException error){
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}


