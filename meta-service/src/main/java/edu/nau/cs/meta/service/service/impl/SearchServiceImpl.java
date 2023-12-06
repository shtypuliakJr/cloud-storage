package edu.nau.cs.meta.service.service.impl;

import edu.nau.cs.meta.service.dao.SearchObjectResultDao;
import edu.nau.cs.meta.service.dto.search.ChunkSearchResultDTO;
import edu.nau.cs.meta.service.dto.search.FileSearchResultDTO;
import edu.nau.cs.meta.service.dto.search.FolderSearchResultDTO;
import edu.nau.cs.meta.service.dto.search.SearchResultObjectDTO;
import edu.nau.cs.meta.service.dto.search.WorkspaceResultDTO;
import edu.nau.cs.meta.service.exception.CsChunkDoesNotExistsException;
import edu.nau.cs.meta.service.exception.CsFileObjectDoesNotExistsException;
import edu.nau.cs.meta.service.exception.CsFolderObjectDoesNotExistsException;
import edu.nau.cs.meta.service.mapper.search.ChunkSearchResultMapper;
import edu.nau.cs.meta.service.mapper.search.FileSearchResultMapper;
import edu.nau.cs.meta.service.mapper.search.FolderSearchResultMapper;
import edu.nau.cs.meta.service.mapper.search.WorkspaceMapper;
import edu.nau.cs.meta.service.repository.ChunkRepository;
import edu.nau.cs.meta.service.repository.FileObjectRepository;
import edu.nau.cs.meta.service.repository.FolderObjectRepository;
import edu.nau.cs.meta.service.service.SearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class SearchServiceImpl implements SearchService {

    private final ChunkRepository chunkRepository;
    private final FileObjectRepository fileObjectRepository;
    private final FolderObjectRepository folderObjectRepository;

    private final SearchObjectResultDao searchObjectResultDao;

    private final ChunkSearchResultMapper chunkSearchResultMapper;
    private final FileSearchResultMapper fileSearchResultMapper;
    private final FolderSearchResultMapper folderSearchResultMapper;
    private final WorkspaceMapper workspaceMapper;

    @Override
    public List<SearchResultObjectDTO> searchFileOrFolderByTemplate(String objectTemplate, String userId) {
        return searchObjectResultDao.searchObjectsByTemplate(objectTemplate, userId);
    }

    @Override
    public List<FileSearchResultDTO> searchByFileExtension(String extension, String userId) {
        return fileObjectRepository.findAllByFileNameHasExtensionAndUserId(extension, userId).stream()
                .map(fileSearchResultMapper::mapToFileSearchResultDTO)
                .toList();
    }

    @Override
    public ChunkSearchResultDTO searchChunkById(String chunkId, String userId) {
        return chunkRepository.findById(chunkId)
                .map(chunkSearchResultMapper::mapToChunkSearchResultDTO)
                .orElseThrow(() -> new CsChunkDoesNotExistsException(userId, chunkId));
    }

    @Override
    public FileSearchResultDTO searchFileById(String fileId, String userId) {
        return fileObjectRepository.findByIdAndUserId(fileId, userId)
                .map(fileObject -> fileSearchResultMapper.mapToFileSearchResultDTO(fileObject, true))
                .orElseThrow(() -> new CsFileObjectDoesNotExistsException(userId, fileId));

    }

    @Override
    public FolderSearchResultDTO searchFolderById(String folderId, String userId) {
        return folderObjectRepository.findByIdAndUserId(folderId, userId)
                .map(folderSearchResultMapper::mapToFolderSearchResultDTO)
                .orElseThrow(() -> new CsFolderObjectDoesNotExistsException(userId, folderId));
    }

    @Override
    public WorkspaceResultDTO searchAll(String userId) {
        return folderObjectRepository.findByUserIdAndParentFolderIdIsNull(userId)
                .map(workspaceMapper::mapToWorkspace)
                .orElseThrow(() -> new CsFolderObjectDoesNotExistsException("User data does not exists for user = " + userId));
    }

}
