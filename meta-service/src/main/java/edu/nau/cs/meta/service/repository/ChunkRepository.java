package edu.nau.cs.meta.service.repository;

import edu.nau.cs.meta.service.entity.Chunk;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChunkRepository extends JpaRepository<Chunk, String> {
}
