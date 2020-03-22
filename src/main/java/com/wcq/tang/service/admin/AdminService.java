package com.wcq.tang.service.admin;

import com.wcq.tang.dto.EChartsDto;
import com.wcq.tang.dto.SysMsgDto;
import com.wcq.tang.model.Indexmsg;
import com.wcq.tang.model.Operation;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author wcq
 * @version 1.0
 * @date 2020/2/28 20:46
 */
public interface AdminService {
    /**
     * 构建周上传图表对象
     * @return
     */
    EChartsDto getEChartsDto();

    /**
     * 构建系统信息对象
     * @return
     */
    SysMsgDto getSysMsgDto(HttpServletRequest request, HttpServletResponse response, Boolean refresh);

    /**
     * 构建操作信息
     * @return
     */
    List<List<Operation>> getOperations();
    /**
     * 构建网站动态
     */
    List<Indexmsg> getIndexMsg();

    /**
     * 更新数据
     * @param indexmsg
     * @return
     */
    int delIndexmsg(Indexmsg indexmsg);

    int updateIndexMsg(Indexmsg indexmsg);
}
