package com.yixiang.service.impl;

import com.yixiang.entity.Dept;
import com.yixiang.dao.DeptMapper;
import com.yixiang.service.DeptService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 部门 服务实现类
 * </p>
 *
 * @author Bean
 * @since 2020-04-21
 */
@Service
public class DeptServiceImpl extends ServiceImpl<DeptMapper, Dept> implements DeptService {

}
