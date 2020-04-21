package com.yixiang.service.impl;

import com.yixiang.entity.Job;
import com.yixiang.dao.JobMapper;
import com.yixiang.service.JobService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 岗位 服务实现类
 * </p>
 *
 * @author Bean
 * @since 2020-04-21
 */
@Service
public class JobServiceImpl extends ServiceImpl<JobMapper, Job> implements JobService {

}
