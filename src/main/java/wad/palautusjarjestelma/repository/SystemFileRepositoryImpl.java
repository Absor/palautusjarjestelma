package wad.palautusjarjestelma.repository;

import java.io.File;
import java.io.IOException;
import java.util.UUID;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Repository;

@Repository
public class SystemFileRepositoryImpl implements SystemFileRepository {

    @Override
    public String saveFile(byte[] file) throws IOException {
        String filename = UUID.randomUUID().toString();
        File newFile = new File(filename);
        while (newFile.exists()) {
            filename = UUID.randomUUID().toString();
            newFile = new File(filename);
        }
        FileUtils.writeByteArrayToFile(newFile, file);
        return filename;
    }

    @Override
    public byte[] getFile(String filename) throws IOException {
        File file = new File(filename);
        return FileUtils.readFileToByteArray(file);
    }
}
