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
public class App implements Serializable {

	/**
	 * 应用编号
	 */
    private Long id;

	/**
	 * 应用名称
	 */
    private String name;

	/**
	 * 端口
	 */
	private int port;

	/**
	 * 上传目录
	 */
	private String uploadPath;

	/**
	 * 部署目录
	 */
	private String deployPath;

	/**
	 * 备份目录
	 */
	private String backupPath;

	/**
	 * 启动脚本
	 */
	private String startScript;

	/**
	 * 部署脚本
	 */
	private String deployScript;

	private Timestamp createTime;

    public void copy(App source){
        BeanUtil.copyProperties(source,this, CopyOptions.create().setIgnoreNullValue(true));
    }
}
