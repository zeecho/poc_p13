package com.openclassrooms.yourcaryourway.repository;

import com.openclassrooms.yourcaryourway.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<Message, Long> {
}
