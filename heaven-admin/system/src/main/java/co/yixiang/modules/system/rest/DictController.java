package co.yixiang.modules.system.rest;

import cn.hutool.core.util.StrUtil;

import co.yixiang.exception.BadRequestException;
import co.yixiang.modules.system.domain.Dict;
import co.yixiang.modules.system.service.DictService;
import co.yixiang.modules.system.service.dto.DictQueryCriteria;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
* @author Zheng Jie
* @date 2019-04-10
*/
@Api(tags = "系统：字典管理")
@RestController
@RequestMapping("/api/dict")
public class DictController {

    private final DictService dictService;

    private static final String ENTITY_NAME = "dict";

    public DictController(DictService dictService) {
        this.dictService = dictService;
    }


    @ApiOperation("导出字典数据")
    @GetMapping(value = "/download")
    @PreAuthorize("@el.check('admin','dict:list')")
    public void download(HttpServletResponse response, DictQueryCriteria criteria) throws IOException {
        dictService.download(dictService.queryAll(criteria), response);
    }

    @ApiOperation("查询字典")
    @GetMapping(value = "/all")
    @PreAuthorize("@el.check('admin','dict:list')")
    public ResponseEntity<Object> all(){
        return new ResponseEntity<>(dictService.queryAll(new DictQueryCriteria()),HttpStatus.OK);
    }

    @ApiOperation("查询字典")
    @GetMapping
    @PreAuthorize("@el.check('admin','dict:list')")
    public ResponseEntity<Object> getDicts(DictQueryCriteria resources, Pageable pageable){
        return new ResponseEntity<>(dictService.queryAll(resources,pageable),HttpStatus.OK);
    }

    @ApiOperation("新增字典")
    @PostMapping
    @PreAuthorize("@el.check('admin','dict:add')")
    public ResponseEntity<Object> create(@Validated @RequestBody Dict resources){
        if (resources.getId() != null) {
            throw new BadRequestException("A new "+ ENTITY_NAME +" cannot already have an ID");
        }
        return new ResponseEntity<>(dictService.create(resources),HttpStatus.CREATED);
    }

    @ApiOperation("修改字典")
    @PutMapping
    @PreAuthorize("@el.check('admin','dict:edit')")
    public ResponseEntity<Object> update(@Validated(Dict.Update.class) @RequestBody Dict resources){
        //if(StrUtil.isNotEmpty("22")) throw new BadRequestException("演示环境禁止操作");
        dictService.update(resources);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @ApiOperation("删除字典")
    @DeleteMapping(value = "/{id}")
    @PreAuthorize("@el.check('admin','dict:del')")
    public ResponseEntity<Object> delete(@PathVariable Long id){
        //if(StrUtil.isNotEmpty("22")) throw new BadRequestException("演示环境禁止操作");
        dictService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}