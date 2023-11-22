package edu.nau.cs.meta.service.repository;

import edu.nau.cs.meta.service.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TagRepository extends JpaRepository<Tag, String> {
}
