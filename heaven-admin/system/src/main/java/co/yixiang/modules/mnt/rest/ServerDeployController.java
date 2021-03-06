package co.yixiang.modules.mnt.rest;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import co.yixiang.modules.mnt.domain.ServerDeploy;
import co.yixiang.modules.mnt.service.ServerDeployService;
import co.yixiang.modules.mnt.service.dto.ServerDeployQueryCriteria;
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
@Api(tags = "服务器管理")
@RestController
@RequestMapping("/api/serverDeploy")
public class ServerDeployController {

    private final ServerDeployService serverDeployService;

    public ServerDeployController(ServerDeployService serverDeployService) {
        this.serverDeployService = serverDeployService;
    }


    @ApiOperation("导出服务器数据")
    @GetMapping(value = "/download")
    @PreAuthorize("@el.check('admin','serverDeploy:list')")
    public void download(HttpServletResponse response, ServerDeployQueryCriteria criteria) throws IOException {
        serverDeployService.download(serverDeployService.queryAll(criteria), response);
    }


    @ApiOperation(value = "查询服务器")
    @GetMapping
	@PreAuthorize("@el.check('admin','serverDeploy:list')")
    public ResponseEntity<Object> getServers(ServerDeployQueryCriteria criteria, Pageable pageable){
    	return new ResponseEntity<>(serverDeployService.queryAll(criteria,pageable),HttpStatus.OK);
    }


    @ApiOperation(value = "新增服务器")
    @PostMapping
	@PreAuthorize("@el.check('admin','serverDeploy:add')")
    public ResponseEntity<Object> create(@Validated @RequestBody ServerDeploy resources){
        return new ResponseEntity<>(serverDeployService.create(resources),HttpStatus.CREATED);
    }


    @ApiOperation(value = "修改服务器")
    @PutMapping
	@PreAuthorize("@el.check('admin','serverDeploy:edit')")
    public ResponseEntity<Object> update(@Validated @RequestBody ServerDeploy resources){
        serverDeployService.update(resources);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @ApiOperation(value = "删除Server")
	@DeleteMapping
	@PreAuthorize("@el.check('admin','serverDeploy:del')")
    public ResponseEntity<Object> delete(@RequestBody Set<Long> ids){
        serverDeployService.delete(ids);
        return new ResponseEntity<>(HttpStatus.OK);
    }

	@ApiOperation(value = "测试连接服务器")
	@PostMapping("/testConnect")
	@PreAuthorize("@el.check('admin','serverDeploy:add')")
	public ResponseEntity<Object> testConnect(@Validated @RequestBody ServerDeploy resources){
		return new ResponseEntity<>(serverDeployService.testConnect(resources),HttpStatus.CREATED);
	}
}
