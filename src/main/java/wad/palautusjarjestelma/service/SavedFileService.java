package wad.palautusjarjestelma.service;

import org.springframework.web.multipart.MultipartFile;
import wad.palautusjarjestelma.data.SavedFile;

public interface SavedFileService extends ServiceInterface<SavedFile> {
    
    SavedFile create(MultipartFile file);
    
    SavedFile getFileContent(SavedFile file);
}
