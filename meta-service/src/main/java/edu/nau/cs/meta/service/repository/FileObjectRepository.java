package edu.nau.cs.meta.service.repository;

import edu.nau.cs.meta.service.entity.FileObject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileObjectRepository extends JpaRepository<FileObject, String> {
}
