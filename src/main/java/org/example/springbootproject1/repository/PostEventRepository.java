package org.example.springbootproject1.repository;

import org.example.springbootproject1.entity.PostEvent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostEventRepository extends JpaRepository<PostEvent, Long> {
}