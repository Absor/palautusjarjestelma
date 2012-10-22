package wad.palautusjarjestelma.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import wad.palautusjarjestelma.data.SavedFile;

public interface SavedFileRepository extends JpaRepository<SavedFile, Long> {
}
