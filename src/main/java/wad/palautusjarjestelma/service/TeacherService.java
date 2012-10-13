package wad.palautusjarjestelma.service;

import java.util.List;
import wad.palautusjarjestelma.data.Teacher;

public interface TeacherService {

    Teacher add(Teacher teacher);

    List<Teacher> list();

    Teacher getById(Long id);

    void deleteById(Long id);
}
