package co.yixiang.modules.quartz.rest;

import cn.hutool.core.util.StrUtil;

import co.yixiang.exception.BadRequestException;
import co.yixiang.modules.quartz.domain.QuartzJob;
import co.yixiang.modules.quartz.service.QuartzJobService;
import co.yixiang.modules.quartz.service.dto.JobQueryCriteria;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Set;

/**
 * @author Zheng Jie
 * @date 2019-01-07
 */
@Slf4j
@RestController
@Api(tags = "系统:定时任务管理")
@RequestMapping("/api/jobs")
public class QuartzJobController {

    private static final String ENTITY_NAME = "quartzJob";

    private final QuartzJobService quartzJobService;

    public QuartzJobController(QuartzJobService quartzJobService) {
        this.quartzJobService = quartzJobService;
    }


    @ApiOperation("查询定时任务")
    @GetMapping
    @PreAuthorize("@el.check('admin','timing:list')")
    public ResponseEntity<Object> getJobs(JobQueryCriteria criteria, Pageable pageable){
        return new ResponseEntity<>(quartzJobService.queryAll(criteria,pageable), HttpStatus.OK);
    }

    @ApiOperation("导出任务数据")
    @GetMapping(value = "/download")
    @PreAuthorize("@el.check('admin','timing:list')")
    public void download(HttpServletResponse response, JobQueryCriteria criteria) throws IOException {
        quartzJobService.download(quartzJobService.queryAll(criteria), response);
    }

    @ApiOperation("导出日志数据")
    @GetMapping(value = "/logs/download")
    @PreAuthorize("@el.check('admin','timing:list')")
    public void downloadLog(HttpServletResponse response, JobQueryCriteria criteria) throws IOException {
        quartzJobService.downloadLog(quartzJobService.queryAllLog(criteria), response);
    }

    @ApiOperation("查询任务执行日志")
    @GetMapping(value = "/logs")
    @PreAuthorize("@el.check('admin','timing:list')")
    public ResponseEntity<Object> getJobLogs(JobQueryCriteria criteria, Pageable pageable){
        return new ResponseEntity<>(quartzJobService.queryAllLog(criteria,pageable), HttpStatus.OK);
    }

    @ApiOperation("新增定时任务")
    @PostMapping
    @PreAuthorize("@el.check('admin','timing:add')")
    public ResponseEntity<Object> create(@Validated @RequestBody QuartzJob resources){
        //if(StrUtil.isNotEmpty("22")) throw new BadRequestException("演示环境禁止操作");
        if (resources.getId() != null) {
            throw new BadRequestException("A new "+ ENTITY_NAME +" cannot already have an ID");
        }
        return new ResponseEntity<>(quartzJobService.create(resources),HttpStatus.CREATED);
    }


    @ApiOperation("修改定时任务")
    @PutMapping
    @PreAuthorize("@el.check('admin','timing:edit')")
    public ResponseEntity<Object> update(@Validated(QuartzJob.Update.class) @RequestBody QuartzJob resources){
        //if(StrUtil.isNotEmpty("22")) throw new BadRequestException("演示环境禁止操作");
        quartzJobService.update(resources);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    @ApiOperation("更改定时任务状态")
    @PutMapping(value = "/{id}")
    @PreAuthorize("@el.check('admin','timing:edit')")
    public ResponseEntity<Object> updateIsPause(@PathVariable Long id){
        //if(StrUtil.isNotEmpty("22")) throw new BadRequestException("演示环境禁止操作");
        quartzJobService.updateIsPause(quartzJobService.findById(id));
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    @ApiOperation("执行定时任务")
    @PutMapping(value = "/exec/{id}")
    @PreAuthorize("@el.check('admin','timing:edit')")
    public ResponseEntity<Object> execution(@PathVariable Long id){
        //if(StrUtil.isNotEmpty("22")) throw new BadRequestException("演示环境禁止操作");
        quartzJobService.execution(quartzJobService.findById(id));
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @ApiOperation("删除定时任务")
    @DeleteMapping
    @PreAuthorize("@el.check('admin','timing:del')")
    public ResponseEntity<Object> delete(@RequestBody Set<Long> ids){
        //if(StrUtil.isNotEmpty("22")) throw new BadRequestException("演示环境禁止操作");
        quartzJobService.delete(ids);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
