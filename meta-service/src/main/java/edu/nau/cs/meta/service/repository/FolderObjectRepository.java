package edu.nau.cs.meta.service.repository;

import edu.nau.cs.meta.service.entity.FolderObject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FolderObjectRepository extends JpaRepository<FolderObject, String> {
}
