
package wad.palautusjarjestelma.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import wad.palautusjarjestelma.data.Teacher;

public interface TeacherRepository extends JpaRepository<Teacher, Long> {

}
