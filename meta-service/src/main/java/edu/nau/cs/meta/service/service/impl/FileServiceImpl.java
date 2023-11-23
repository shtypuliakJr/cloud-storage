package edu.nau.cs.meta.service.service.impl;

import edu.nau.cs.meta.service.dto.FileObjectDTO;
import edu.nau.cs.meta.service.entity.Chunk;
import edu.nau.cs.meta.service.entity.FileObject;
import edu.nau.cs.meta.service.exception.CsFileObjectDoesNotExistsException;
import edu.nau.cs.meta.service.mapper.ChunkMapper;
import edu.nau.cs.meta.service.mapper.FileObjectMapper;
import edu.nau.cs.meta.service.repository.FileObjectRepository;
import edu.nau.cs.meta.service.repository.UserRepository;
import edu.nau.cs.meta.service.service.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class FileServiceImpl implements FileService {

    private final FileObjectRepository fileObjectRepository;
    private final UserRepository userRepository;
    private final FileObjectMapper fileObjectMapper;
    private final ChunkMapper chunkMapper;

    @Override
    public FileObjectDTO getFileData(String userId, String fileId) {
        return fileObjectRepository.findByUserId(fileId)
                .map(fileObjectMapper::mapFileObjectToDTO)
                .orElseThrow(() -> new CsFileObjectDoesNotExistsException(userId, fileId));
    }

    @Override
    public List<FileObjectDTO> getFilesData(String userId, List<String> fileIds) {
        return fileObjectRepository.findAllByUserIdAndIdIn(userId, fileIds).stream()
                .map(fileObjectMapper::mapFileObjectToDTO)
                .toList();
    }

    @Override
    public FileObjectDTO saveFileData(String userId, FileObjectDTO fileObjectDTO) {
        FileObject fileObject = fileObjectMapper.mapFileObjectToEntity(fileObjectDTO);
        List<Chunk> chunks = fileObjectDTO.getChunks().stream()
                .map(chunkMapper::mapChunkToEntity)
                .toList();
        chunks.forEach(chunk -> chunk.setFileObject(fileObject));
        fileObject.setUser(userRepository.findById(userId).orElseThrow(() -> new CsFileObjectDoesNotExistsException(userId)));
        fileObject.setChunks(chunks);

        return fileObjectMapper.mapFileObjectToDTO(fileObjectRepository.save(fileObject));
    }

    @Override
    public FileObjectDTO editFileData(String userId, String fileId, FileObjectDTO fileObjectDTO) {
        return null;
    }

    @Override
    public void deleteFileData(String userId, String fileId) {

    }

    @Override
    public void deleteFilesData(String userId, List<String> fileIds) {

    }

}
