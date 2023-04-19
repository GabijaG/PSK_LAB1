package lt.vu.mybatis.dao;

import java.util.List;
import lt.vu.mybatis.model.Task;

public interface TaskMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PUBLIC.TASK
     *
     * @mbg.generated Wed Apr 19 12:05:35 EEST 2023
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PUBLIC.TASK
     *
     * @mbg.generated Wed Apr 19 12:05:35 EEST 2023
     */
    int insert(Task record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PUBLIC.TASK
     *
     * @mbg.generated Wed Apr 19 12:05:35 EEST 2023
     */
    Task selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PUBLIC.TASK
     *
     * @mbg.generated Wed Apr 19 12:05:35 EEST 2023
     */
    List<Task> selectAll();

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PUBLIC.TASK
     *
     * @mbg.generated Wed Apr 19 12:05:35 EEST 2023
     */
    int updateByPrimaryKey(Task record);
}