package blog.blog.controllers;

import blog.blog.model.User;
import blog.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "http://localhost:3000", exposedHeaders = "X-Total-Count")
public class UserControl {
    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<User> findAll() {
        return userService.findAll();
    }

    @GetMapping(path = "/{id}")
    public Optional<User> findOne(@PathVariable(name = "id") Long id) {
        return userService.findById(id);
    }

    @PostMapping
    public User create(@RequestBody User user) {
        return userService.create(user);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<User> update(@RequestBody User user, @PathVariable(name = "id") Long id) {
        if (userService.findById(id) == null) {
            return ResponseEntity.notFound().build();
        } else {
            user.setId(id);
            userService.create(user);

            User userUpdated = userService.findOne(id);

            return ResponseEntity.ok(userUpdated);
        }
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> delete(@PathVariable(name = "id") Long id) {
        return userService
                .findById(id)
                .map(record -> {
                    userService.delete(record);
                    return ResponseEntity.status(202).build();
                }).orElse(ResponseEntity.notFound().build());
    }
}