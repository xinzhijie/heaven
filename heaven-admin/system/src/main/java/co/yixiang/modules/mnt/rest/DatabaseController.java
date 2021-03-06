package co.yixiang.modules.mnt.rest;


import co.yixiang.exception.BadRequestException;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import co.yixiang.modules.mnt.domain.Database;
import co.yixiang.modules.mnt.service.DatabaseService;
import co.yixiang.modules.mnt.service.dto.DatabaseDto;
import co.yixiang.modules.mnt.service.dto.DatabaseQueryCriteria;
import co.yixiang.modules.mnt.util.SqlUtils;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.Set;

/**
* @author zhanghouying
* @date 2019-08-24
*/
@Api(tags = "数据库管理")
@RestController
@RequestMapping("/api/database")
public class DatabaseController {

	private String fileSavePath = System.getProperty("java.io.tmpdir");

    private final DatabaseService databaseService;

    public DatabaseController(DatabaseService databaseService) {
        this.databaseService = databaseService;
    }


	@ApiOperation("导出数据库数据")
	@GetMapping(value = "/download")
	@PreAuthorize("@el.check('admin','database:list')")
	public void download(HttpServletResponse response, DatabaseQueryCriteria criteria) throws IOException {
		databaseService.download(databaseService.queryAll(criteria), response);
	}


    @ApiOperation(value = "查询数据库")
    @GetMapping
	@PreAuthorize("@el.check('admin','database:list')")
    public ResponseEntity<Object> getDatabases(DatabaseQueryCriteria criteria, Pageable pageable){
        return new ResponseEntity<>(databaseService.queryAll(criteria,pageable),HttpStatus.OK);
    }


    @ApiOperation(value = "新增数据库")
    @PostMapping
	@PreAuthorize("@el.check('admin','database:add')")
    public ResponseEntity<Object> create(@Validated @RequestBody Database resources){
        return new ResponseEntity<>(databaseService.create(resources),HttpStatus.CREATED);
    }

    @ApiOperation(value = "修改数据库")
    @PutMapping
	@PreAuthorize("@el.check('admin','database:edit')")
    public ResponseEntity<Object> update(@Validated @RequestBody Database resources){
        databaseService.update(resources);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    @ApiOperation(value = "删除数据库")
    @DeleteMapping
	@PreAuthorize("@el.check('admin','database:del')")
    public ResponseEntity<Object> delete(@RequestBody Set<String> ids){
        databaseService.delete(ids);
        return new ResponseEntity<>(HttpStatus.OK);
    }

	@ApiOperation(value = "测试数据库链接")
	@PostMapping("/testConnect")
	@PreAuthorize("@el.check('admin','database:testConnect')")
	public ResponseEntity<Object> testConnect(@Validated @RequestBody Database resources){
		return new ResponseEntity<>(databaseService.testConnection(resources),HttpStatus.CREATED);
	}

	@ApiOperation(value = "执行SQL脚本")
	@PostMapping(value = "/upload")
	@PreAuthorize("@el.check('admin','database:add')")
	public ResponseEntity<Object> upload(@RequestBody MultipartFile file, HttpServletRequest request)throws Exception{
		String id = request.getParameter("id");
		DatabaseDto database = databaseService.findById(id);
		String fileName;
		if(database != null){
			fileName = file.getOriginalFilename();
			File executeFile = new File(fileSavePath+fileName);
//			FileUtil.del(executeFile);
			file.transferTo(executeFile);
			String result = SqlUtils.executeFile(database.getJdbcUrl(), database.getUserName(), database.getPwd(), executeFile);
			return new ResponseEntity<>(result,HttpStatus.OK);
		}else{
			throw new BadRequestException("Database not exist");
		}
	}
}
