package co.yixiang.modules.monitor.repository;

import co.yixiang.modules.monitor.domain.Visits;
import org.springframework.stereotype.Repository;
import java.util.List;

/**
 * @author Zheng Jie
 * @date 2018-12-13
 */
@Repository
public interface VisitsRepository{

    /**
     * findByDate
     * @param date 日期
     * @return Visits
     */
    Visits findByDate(String date);

    /**
     * 获得一个时间段的记录
     * @param date1 日期1
     * @param date2 日期2
     * @return List
     */
//    @Query(value = "select * FROM visits where create_time between ?1 and ?2",nativeQuery = true)
    List<Visits> findAllVisits(String date1, String date2);
}
