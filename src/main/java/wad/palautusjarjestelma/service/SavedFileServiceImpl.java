package wad.palautusjarjestelma.service;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import wad.palautusjarjestelma.data.SavedFile;
import wad.palautusjarjestelma.repository.SavedFileRepository;
import wad.palautusjarjestelma.repository.SystemFileRepository;

@Service
public class SavedFileServiceImpl implements SavedFileService {

    @Autowired
    private SavedFileRepository savedFileRepository;
    @Autowired
    private SystemFileRepository systemFileRepository;

    @Override
    @Transactional(readOnly = false)
    public SavedFile create(SavedFile savedFile) {
        return savedFileRepository.save(savedFile);
    }

    @Override
    @Transactional(readOnly = true)
    public SavedFile findById(Long id) {
        return savedFileRepository.findOne(id);
    }

    @Override
    @Transactional(readOnly = false)
    public void update(SavedFile savedFile) {
        savedFileRepository.save(savedFile);
    }

    @Override
    @Transactional(readOnly = false)
    public void delete(SavedFile savedFile) {
        savedFileRepository.delete(savedFile);
    }

    @Override
    @Transactional(readOnly = false)
    public void delete(Long id) {
        savedFileRepository.delete(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<SavedFile> findAll() {
        return savedFileRepository.findAll();
    }

    @Override
    public SavedFile create(MultipartFile file) {
        SavedFile newSavedFile = new SavedFile();
        newSavedFile.setContentType(file.getContentType());
        newSavedFile.setOriginalFilename(file.getOriginalFilename());
        newSavedFile.setTimeAdded(new Date());
        try {
            newSavedFile.setFilename(systemFileRepository.saveFile(file.getBytes()));
        } catch (IOException ex) {
            return null;
        }
        return create(newSavedFile);
    }

    @Override
    public SavedFile getFileContent(SavedFile file) {
        try {
            file.setContent(systemFileRepository.getFile(file.getFilename()));
        } catch (IOException ex) {
            return null;
        }
        return file;
    }
}
