package edu.nau.cs.meta.service.repository;

import edu.nau.cs.meta.service.entity.FileObject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FileObjectRepository extends JpaRepository<FileObject, String> {

    Optional<FileObject> findByUserId(String userId);

    List<FileObject> findAllByUserIdAndIdIn(String userId, List<String> fileIds);

}
