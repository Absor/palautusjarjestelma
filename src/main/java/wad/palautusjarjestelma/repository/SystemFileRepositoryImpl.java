package wad.palautusjarjestelma.repository;

import java.io.File;
import java.io.IOException;
import java.util.UUID;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Repository;

@Repository
public class SystemFileRepositoryImpl implements SystemFileRepository {
    
    // Didn't get to work with @Value after changed beans to be loaded in main application context
    private String storagePath = "files";

    @Override
    public String saveFile(byte[] file) throws IOException {
        String filename = UUID.randomUUID().toString();
        File newFile = new File(FilenameUtils.concat(storagePath, filename));
        while (newFile.exists()) {
            filename = UUID.randomUUID().toString();
            newFile = new File(FilenameUtils.concat(storagePath, filename));
        }
        FileUtils.writeByteArrayToFile(newFile, file);
        return filename;
    }

    @Override
    public byte[] getFile(String filename) throws IOException {
        File file = new File(FilenameUtils.concat(storagePath, filename));
        return FileUtils.readFileToByteArray(file);
    }
}
