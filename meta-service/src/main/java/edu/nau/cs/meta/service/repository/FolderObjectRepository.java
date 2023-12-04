package edu.nau.cs.meta.service.repository;

import edu.nau.cs.meta.service.entity.FolderObject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FolderObjectRepository extends JpaRepository<FolderObject, String> {

    Optional<FolderObject> findByFolderName(String folderName);

    List<FolderObject> findByFolderNameIsContaining(String folderName);

}
