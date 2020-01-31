package org.lanqiao.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.lanqiao.entity.Studentcard;
import org.lanqiao.entity.StudentcardExample;

public interface StudentcardMapper {
    long countByExample(StudentcardExample example);

    int deleteByExample(StudentcardExample example);

    int deleteByPrimaryKey(Short cardid);

    int insert(Studentcard record);

    int insertSelective(Studentcard record);

    List<Studentcard> selectByExample(StudentcardExample example);

    Studentcard selectByPrimaryKey(Short cardid);

    int updateByExampleSelective(@Param("record") Studentcard record, @Param("example") StudentcardExample example);

    int updateByExample(@Param("record") Studentcard record, @Param("example") StudentcardExample example);

    int updateByPrimaryKeySelective(Studentcard record);

    int updateByPrimaryKey(Studentcard record);
}