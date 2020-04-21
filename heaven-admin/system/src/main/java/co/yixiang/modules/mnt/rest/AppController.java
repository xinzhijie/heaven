package co.yixiang.modules.mnt.rest;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import co.yixiang.modules.mnt.domain.App;
import co.yixiang.modules.mnt.service.AppService;
import co.yixiang.modules.mnt.service.dto.AppQueryCriteria;
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
* @author zhanghouying
* @date 2019-08-24
*/
@Api(tags = "应用管理")
@RestController
@RequestMapping("/api/app")
public class AppController {

    private final AppService appService;

    public AppController(AppService appService){
        this.appService = appService;
    }


    @ApiOperation("导出应用数据")
    @GetMapping(value = "/download")
    @PreAuthorize("@el.check('admin','app:list')")
    public void download(HttpServletResponse response, AppQueryCriteria criteria) throws IOException {
        appService.download(appService.queryAll(criteria), response);
    }


    @ApiOperation(value = "查询应用")
    @GetMapping
	@PreAuthorize("@el.check('admin','app:list')")
    public ResponseEntity<Object> getApps(AppQueryCriteria criteria, Pageable pageable){
        return new ResponseEntity<>(appService.queryAll(criteria,pageable),HttpStatus.OK);
    }


    @ApiOperation(value = "新增应用")
    @PostMapping
	@PreAuthorize("@el.check('admin','app:add')")
    public ResponseEntity<Object> create(@Validated @RequestBody App resources){
        return new ResponseEntity<>(appService.create(resources),HttpStatus.CREATED);
    }


    @ApiOperation(value = "修改应用")
    @PutMapping
	@PreAuthorize("@el.check('admin','app:edit')")
    public ResponseEntity<Object> update(@Validated @RequestBody App resources){
        appService.update(resources);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    @ApiOperation(value = "删除应用")
	@DeleteMapping
	@PreAuthorize("@el.check('admin','app:del')")
    public ResponseEntity<Object> delete(@RequestBody Set<Long> ids){
        appService.delete(ids);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
