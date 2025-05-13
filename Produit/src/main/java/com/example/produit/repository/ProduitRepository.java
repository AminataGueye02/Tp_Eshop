package com.example.produit.repository;

import com.example.produit.model.produit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProduitRepository extends JpaRepository<produit, Long> {
}
