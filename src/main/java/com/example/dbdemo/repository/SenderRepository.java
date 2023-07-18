package com.example.dbdemo.repository;


import com.example.dbdemo.entity.Sender;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SenderRepository extends JpaRepository<Sender, Long> {

    @NonNull
    Optional<Sender> findByName(final String name);

}
