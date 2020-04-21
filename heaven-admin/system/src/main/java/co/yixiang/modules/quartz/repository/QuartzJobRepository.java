package co.yixiang.modules.quartz.repository;

import co.yixiang.modules.quartz.domain.QuartzJob;

import java.util.List;

/**
 * @author Zheng Jie
 * @date 2019-01-07
 */
public interface QuartzJobRepository{

    /**
     * 查询启用的任务
     * @return List
     */
    List<QuartzJob> findByIsPauseIsFalse();
}
