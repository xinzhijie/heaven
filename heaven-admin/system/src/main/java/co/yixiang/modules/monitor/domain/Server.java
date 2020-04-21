package co.yixiang.modules.monitor.domain;

import lombok.Data;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import java.io.Serializable;

/**
* @author Zhang houying
* @date 2019-11-03
*/

@Data
public class Server implements Serializable {


    private Integer id;


    private String name;


    private String address;


    private Integer port;


    private String state;


    private Double cpuRate;

    private Integer cpuCore;


    private Double memTotal;


    private Double memUsed;


    private Double diskTotal;


    private Double diskUsed;


    private Double swapTotal;


    private Double swapUsed;


    private Integer sort;

    public void copy(Server source){
        BeanUtil.copyProperties(source,this, CopyOptions.create().setIgnoreNullValue(true));
    }
}
