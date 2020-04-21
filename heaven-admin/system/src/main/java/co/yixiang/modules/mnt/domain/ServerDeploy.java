package co.yixiang.modules.mnt.domain;

import lombok.Data;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;

import java.io.Serializable;
import java.sql.Timestamp;

/**
* @author zhanghouying
* @date 2019-08-24
*/

@Data

public class ServerDeploy implements Serializable {


    private Long id;

    private String name;

    private String ip;

    private Integer port;

    private String account;

    private String password;


    private Timestamp createTime;

    public void copy(ServerDeploy source){
        BeanUtil.copyProperties(source,this, CopyOptions.create().setIgnoreNullValue(true));
    }
}
