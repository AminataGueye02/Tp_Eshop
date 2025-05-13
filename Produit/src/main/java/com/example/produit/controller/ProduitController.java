package com.example.produit.controller;

import com.example.produit.exception.ResourceNotFoundException;
import com.example.produit.model.produit;
import com.example.produit.repository.ProduitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.*;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import java.util.List;

@RestController
@RequestMapping("/api/produits")
public class ProduitController {
    @Autowired
    private ProduitRepository repository;

    @GetMapping
    public List<produit> all() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public EntityModel<produit> one(@PathVariable Long id) {
        produit prod = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Produit non trouvé"));
        EntityModel<produit> model = EntityModel.of(prod);
        model.add(linkTo(methodOn(ProduitController.class).one(id)).withSelfRel());
        return model;
    }

    @PostMapping
    public produit create(@RequestBody produit p) {
        return repository.save(p);
    }

    @PutMapping("/{id}")
    public produit update(@PathVariable Long id, @RequestBody produit p) {
        p.setId(id);
        return repository.save(p);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        repository.deleteById(id);
    }
}

