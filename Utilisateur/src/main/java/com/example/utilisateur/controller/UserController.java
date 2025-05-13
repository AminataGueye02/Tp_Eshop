package com.example.utilisateur.controller;

import com.example.utilisateur.exception.ResourceNotFoundException;
import com.example.utilisateur.model.User;
import com.example.utilisateur.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserRepository repo;

    @GetMapping
    public List<User> list() {
        return repo.findAll();
    }

    @GetMapping("/{id}")
    public EntityModel<User> get(@PathVariable Long id) {
        User u = repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Utilisateur non trouvé"));
        EntityModel<User> model = EntityModel.of(u);
        model.add(linkTo(methodOn(UserController.class).get(id)).withSelfRel());
        return model;
    }

    @PostMapping
    public User create(@RequestBody User u) {
        return repo.save(u);
    }

    @PutMapping("/{id}")
    public User update(@PathVariable Long id, @RequestBody User u) {
        u.setId(id);
        return repo.save(u);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        repo.deleteById(id);
    }
}
