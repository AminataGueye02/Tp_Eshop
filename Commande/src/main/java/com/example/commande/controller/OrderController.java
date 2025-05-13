package com.example.commande.controller;

import com.example.commande.exception.ResourceNotFoundException;
import com.example.commande.model.Order;
import com.example.commande.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
@RequestMapping("/api/commandes")
public class OrderController {

    @Autowired
    private OrderRepository repo;

    @GetMapping
    public List<Order> list() {
        return repo.findAll();
    }

    @GetMapping("/{id}")
    public EntityModel<Order> get(@PathVariable Long id) {
        Order o = repo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Commande non trouvée"));
        EntityModel<Order> model = EntityModel.of(o);
        model.add(linkTo(methodOn(OrderController.class).get(id)).withSelfRel());
        return model;
    }

    @PostMapping
    public Order create(@RequestBody Order o) {
        return repo.save(o);
    }

    @PutMapping("/{id}")
    public Order update(@PathVariable Long id, @RequestBody Order o) {
        o.setId(id);
        return repo.save(o);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        repo.deleteById(id);
    }
}
