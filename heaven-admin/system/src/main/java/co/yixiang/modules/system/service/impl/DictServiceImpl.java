package co.yixiang.modules.system.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import co.yixiang.modules.system.domain.Dict;
import co.yixiang.modules.system.repository.DictRepository;
import co.yixiang.modules.system.service.DictService;
import co.yixiang.modules.system.service.dto.DictDTO;
import co.yixiang.modules.system.service.dto.DictDetailDTO;
import co.yixiang.modules.system.service.dto.DictQueryCriteria;
import co.yixiang.modules.system.service.mapper.DictMapper;

import co.yixiang.utils.PageUtil;

import co.yixiang.utils.ValidationUtil;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
* @author Zheng Jie
* @date 2019-04-10
*/
@Service
@CacheConfig(cacheNames = "dict")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class DictServiceImpl implements DictService {

    private  DictRepository dictRepository;

    private  DictMapper dictMapper;


    @Override
    @Cacheable
    public Map<String, Object> queryAll(DictQueryCriteria dict, Pageable pageable){
        Page<Dict> page = null;//dictRepository.findAll((root, query, cb) -> QueryHelp.getPredicate(root, dict, cb), pageable);
        return PageUtil.toPage(page.map(dictMapper::toDto));
    }

    @Override
    public List<DictDTO> queryAll(DictQueryCriteria dict) {
        List<Dict> list = null;//dictRepository.findAll((root, query, cb) -> QueryHelp.getPredicate(root, dict, cb));
        return dictMapper.toDto(list);
    }

    @Override
    @Cacheable(key = "#p0")
    public DictDTO findById(Long id) {
        Dict dict = null;//dictRepository.findById(id).orElseGet(Dict::new);
        ValidationUtil.isNull(dict.getId(),"Dict","id",id);
        return dictMapper.toDto(dict);
    }

    @Override
    @CacheEvict(allEntries = true)
    @Transactional(rollbackFor = Exception.class)
    public DictDTO create(Dict resources) {
//        return dictMapper.toDto(dictRepository.save(resources));
        return null;
    }

    @Override
    @CacheEvict(allEntries = true)
    @Transactional(rollbackFor = Exception.class)
    public void update(Dict resources) {
        Dict dict =null; //dictRepository.findById(resources.getId()).orElseGet(Dict::new);
        ValidationUtil.isNull( dict.getId(),"Dict","id",resources.getId());
        resources.setId(dict.getId());
//        dictRepository.save(resources);
    }

    @Override
    @CacheEvict(allEntries = true)
    @Transactional(rollbackFor = Exception.class)
    public void delete(Long id) {
//        dictRepository.deleteById(id);
    }

    @Override
    public void download(List<DictDTO> dictDtos, HttpServletResponse response) throws IOException {
        List<Map<String, Object>> list = new ArrayList<>();
        for (DictDTO dictDTO : dictDtos) {
            if(CollectionUtil.isNotEmpty(dictDTO.getDictDetails())){
                for (DictDetailDTO dictDetail : dictDTO.getDictDetails()) {
                    Map<String,Object> map = new LinkedHashMap<>();
                    map.put("字典名称", dictDTO.getName());
                    map.put("字典描述", dictDTO.getRemark());
                    map.put("字典标签", dictDetail.getLabel());
                    map.put("字典值", dictDetail.getValue());
                    map.put("创建日期", dictDetail.getCreateTime());
                    list.add(map);
                }
            } else {
                Map<String,Object> map = new LinkedHashMap<>();
                map.put("字典名称", dictDTO.getName());
                map.put("字典描述", dictDTO.getRemark());
                map.put("字典标签", null);
                map.put("字典值", null);
                map.put("创建日期", dictDTO.getCreateTime());
                list.add(map);
            }
        }
//        FileUtil.downloadExcel(list, response);
    }
}