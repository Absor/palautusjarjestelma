
package wad.palautusjarjestelma.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wad.palautusjarjestelma.data.Teacher;
import wad.palautusjarjestelma.repository.TeacherRepository;

@Service
public class TeacherServiceImpl implements TeacherService {
    
    @Autowired
    private TeacherRepository teacherRepository;

    @Override
    @Transactional(readOnly=false)
    public Teacher add(Teacher teacher) {
        return teacherRepository.save(teacher);
    }

    @Override
    public List<Teacher> list() {
        return teacherRepository.findAll();
    }

    @Override
    public Teacher getById(Long id) {
        return teacherRepository.findOne(id);
    }

    @Override
    public void deleteById(Long id) {
        teacherRepository.delete(id);
    }

}
