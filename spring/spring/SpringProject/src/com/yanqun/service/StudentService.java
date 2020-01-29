package com.yanqun.service;

import com.yanqun.dao.StudentDao;
import org.springframework.stereotype.Service;


@Service("stuService")
public class StudentService {
   // @Qualifier("stuDao2")
//    @Autowired(required=false)//从IoC容器中 寻找一个 类型StudentDao的Bean（根据类型）
//    @Autowired
//    @Resource(type=StudentDao.class)
//    @Inject
    private StudentDao studentDao ;
//    @Autowired
//    public StudentService(StudentDao studentDao){
//        this.studentDao= studentDao ;
//    }

//    public StudentService(StudentDao studentDao,String xxx){
//        this.studentDao= studentDao ;
//    }
//    //@Autowired
//    public void setStudentDao(@Autowired StudentDao studentDao) {
//        System.out.println("***************************111111-");
//        this.studentDao = studentDao;
//    }

    public StudentDao getStudentDao() {
        return studentDao;
    }
}
