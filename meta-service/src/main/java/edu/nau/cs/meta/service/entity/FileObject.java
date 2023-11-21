package edu.nau.cs.meta.service.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode(exclude = {"chunks"})
public class FileObject {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    @Column(name = "original_name", nullable = false)
    private String originalName;

    @Column(name = "object_type", nullable = false)
    private String objectType;

    @Column(name = "is_folder", nullable = false)
    private Boolean isFolder;

    @Column(name = "s3_path", nullable = false)
    private String s3Path;

    @Column(name = "created_at", nullable = false)
    @CreatedDate
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    @LastModifiedDate
    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "fileObject", fetch = FetchType.LAZY, targetEntity = Chunk.class)
    private Set<Chunk> chunks;

}
