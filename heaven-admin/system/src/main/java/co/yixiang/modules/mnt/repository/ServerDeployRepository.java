package co.yixiang.modules.mnt.repository;

import co.yixiang.modules.mnt.domain.ServerDeploy;


/**
* @author zhanghouying
* @date 2019-08-24
*/
public interface ServerDeployRepository {

    /**
     * 根据IP查询
     * @param ip /
     * @return /
     */
    ServerDeploy findByIp(String ip);
}
