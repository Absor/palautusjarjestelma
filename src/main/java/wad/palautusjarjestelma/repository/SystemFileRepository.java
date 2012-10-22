package wad.palautusjarjestelma.repository;

import java.io.IOException;

public interface SystemFileRepository {

    String saveFile(byte[] file) throws IOException;

    byte[] getFile(String filename) throws IOException;
}
