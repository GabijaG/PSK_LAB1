package lt.vu.mybatis.dao;

import java.util.List;
import lt.vu.mybatis.model.Tasklist;

public interface TasklistMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PUBLIC.TASKLIST
     *
     * @mbg.generated Wed Apr 19 12:05:35 EEST 2023
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PUBLIC.TASKLIST
     *
     * @mbg.generated Wed Apr 19 12:05:35 EEST 2023
     */
    int insert(Tasklist record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PUBLIC.TASKLIST
     *
     * @mbg.generated Wed Apr 19 12:05:35 EEST 2023
     */
    Tasklist selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PUBLIC.TASKLIST
     *
     * @mbg.generated Wed Apr 19 12:05:35 EEST 2023
     */
    List<Tasklist> selectAll();

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PUBLIC.TASKLIST
     *
     * @mbg.generated Wed Apr 19 12:05:35 EEST 2023
     */
    int updateByPrimaryKey(Tasklist record);
}