package edu.nau.cs.meta.service.repository;

import edu.nau.cs.meta.service.entity.FileObject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FileObjectRepository extends JpaRepository<FileObject, String> {

    Optional<FileObject> findByIdAndUserId(String id, String userId);

    List<FileObject> findAllByIdInAndUserId(List<String> fileIds, String userId);

    @Query(value = """
            SELECT fo.*
              FROM file_object fo
              WHERE user_id = :userId
                AND file_name LIKE '%.' || :extension
            """, nativeQuery = true)
    List<FileObject> findAllByFileNameHasExtensionAndUserId(String extension, String userId);

}
