package edu.nau.cs.meta.service.service.impl;

import edu.nau.cs.meta.service.dto.FileObjectDTO;
import edu.nau.cs.meta.service.entity.Chunk;
import edu.nau.cs.meta.service.entity.FileObject;
import edu.nau.cs.meta.service.entity.FolderObject;
import edu.nau.cs.meta.service.entity.User;
import edu.nau.cs.meta.service.exception.CsFileObjectDoesNotExistsException;
import edu.nau.cs.meta.service.mapper.ChunkMapper;
import edu.nau.cs.meta.service.mapper.FileObjectMapper;
import edu.nau.cs.meta.service.repository.FileObjectRepository;
import edu.nau.cs.meta.service.repository.FolderObjectRepository;
import edu.nau.cs.meta.service.repository.UserRepository;
import edu.nau.cs.meta.service.service.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class FileServiceImpl implements FileService {

    private final FileObjectRepository fileObjectRepository;
    private final FolderObjectRepository folderObjectRepository;
    private final UserRepository userRepository;
    private final FileObjectMapper fileObjectMapper;
    private final ChunkMapper chunkMapper;

    @Override
    public FileObjectDTO getFileData(String userId, String fileId) {
        return fileObjectRepository.findByIdAndUserId(fileId, userId)
                .map(fileObjectMapper::mapFileObjectToDTO)
                .orElseThrow(() -> new CsFileObjectDoesNotExistsException(userId, fileId));
    }

    @Override
    public List<FileObjectDTO> getFilesData(String userId, List<String> fileIds) {
        return fileObjectRepository.findAllByIdInAndUserId(fileIds, userId).stream()
                .map(fileObjectMapper::mapFileObjectToDTO)
                .toList();
    }

    @Override
    public FileObjectDTO saveFileData(String userId, FileObjectDTO fileObjectDTO) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new CsFileObjectDoesNotExistsException(userId));

        FolderObject parentFolderObject = Optional.ofNullable(fileObjectDTO.getParentFolderId())
                .map(folderObjectRepository::findById)
                .filter(Optional::isPresent).map(Optional::get)
                .orElse(folderObjectRepository.findByFolderName(user.getUserName()).orElse(null));

        FileObject fileObject = fileObjectMapper.mapFileObjectToEntity(fileObjectDTO);
        List<Chunk> chunks = fileObjectDTO.getChunks().stream()
                .map(chunkMapper::mapChunkToEntity)
                .toList();
        chunks.forEach(chunk -> chunk.setFileObject(fileObject));

        fileObject.setUser(user);
        fileObject.setChunks(chunks);
        fileObject.setParentFolderObject(parentFolderObject);

        return fileObjectMapper.mapFileObjectToDTO(fileObjectRepository.save(fileObject));
    }

    @Override
    public FileObjectDTO editFileData(String userId, String fileId, FileObjectDTO fileObjectDTO) {
        return null;
    }

    @Override
    public void deleteFileData(String userId, String fileId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new CsFileObjectDoesNotExistsException(userId));
        fileObjectRepository.deleteById(fileId);
    }

    @Override
    public void deleteFilesData(String userId, List<String> fileIds) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new CsFileObjectDoesNotExistsException(userId));
        fileObjectRepository.deleteAllById(fileIds);
    }

}
