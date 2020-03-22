package com.wcq.tang.service.admin.impl;

import com.wcq.tang.bean.Constant;
import com.wcq.tang.bean.Utils;
import com.wcq.tang.dto.EChartsDto;
import com.wcq.tang.dto.SysMsgDto;
import com.wcq.tang.mapper.*;
import com.wcq.tang.model.*;
import com.wcq.tang.service.admin.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * @author wcq
 * @version 1.0
 * @date 2020/2/28 20:57
 */
@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    private OriginalMapper originalMapper;
    @Autowired
    private CorpusMapper corpusMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private OperationMapper operationMapper;
    @Autowired
    private IndexmsgMapper indexmsgMapper;
    @Resource
    private ApplicationContext applicationContext;
    @Override
    public EChartsDto getEChartsDto() {
        //获取今天的日期
        EChartsDto eChartsDto = new EChartsDto();
        //x轴的值
        String[] xData =new String[7];
        //xData[7]是今天
        for(int i = 0;i<7;i++){
            xData[i] = Utils.getOtherDay(i-6);
        }
        eChartsDto.setXData(xData);
        //y轴的值
        Long[] yData = new Long[7];
        for(int i =0;i<7;i++){
            yData[i] =originalMapper.countTodayOriginals(xData[i]);
        }
        eChartsDto.setYData(yData);
        //折线图的值
        Double[] lData = new Double[7];
        for (int i = 0;i<7;i++) {
            lData[i]=yData[i]/10.0;
        }
        eChartsDto.setLData(lData);
        return  eChartsDto;
    }

    @Override
    public SysMsgDto getSysMsgDto(HttpServletRequest request, HttpServletResponse response, Boolean refresh) {
        //查询各项数据
        //统计原始语料条目
        OriginalExample originalExample = new OriginalExample();
        long originalItems = originalMapper.countByExample(originalExample);
        //统计生语料条目
        CorpusExample corpusExample = new CorpusExample();
        long corpusItems = corpusMapper.countByExample(corpusExample);
        //统计系统用户
        UserExample userExample = new UserExample();
        long sysUsers = userMapper.countByExample(userExample);
        //获取当前在线人数
        Cookie cookie;
        try {
            //把session记录在浏览器中
            cookie = new Cookie("JSESSIONID", URLEncoder.encode(request.getSession().getId(),"utf-8"));
            cookie.setPath("/");
            cookie.setMaxAge(48*60*60);
            response.addCookie(cookie);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        //获取系统访问量
        Long browseTimes = (Long) request.getSession().getServletContext().getAttribute(Constant.BROWSE_TIMES);
        Integer onlineuser = (Integer) request.getSession().getServletContext().getAttribute(Constant.ON_LINE_USER);
        //构建对象
        SysMsgDto sysMsgDto = new SysMsgDto();
        sysMsgDto.setBrowseTimes(browseTimes);
        sysMsgDto.setCorpusItems(corpusItems);
        sysMsgDto.setOriginalItems(originalItems);
        sysMsgDto.setOnLineUsers(onlineuser);
        sysMsgDto.setSysUsers(sysUsers);
        return sysMsgDto;
    }

    @Override
    public List<List<Operation>> getOperations() {
        //直接按日期查找最近一周的操作
        OperationExample example = new OperationExample();
        OperationExample.Criteria criteria = example.createCriteria();
        Date beginDay = Utils.getDay(-6);
        Date today = new Date();
        criteria.andOperationDateBetween(beginDay,today);
        List<Operation> operations = operationMapper.selectByExample(example);
        List<Operation> uploadop = new LinkedList<>();
        List<Operation> cleanop = new LinkedList<>();
        List<Operation> participleop = new LinkedList<>();
        List<Operation> otherop = new LinkedList<>();
        List<Operation> threeop = new LinkedList<>();
        for (Operation op:operations
             ) {
            switch (op.getOperationKind()){
                case 1:uploadop.add(op);break;
                case 2:cleanop.add(op);break;
                case 3:participleop.add(op);break;
                case 4:otherop.add(op);break;
                case 5:threeop.add(op);break;
                default:System.err.println("操作格式错误");break;
            }
        }
        List<List<Operation>> results = new LinkedList<>();
        results.add(uploadop);
        results.add(cleanop);
        results.add(participleop);
        results.add(otherop);
        results.add(threeop);
        return results;
    }

    @Override
    public List<Indexmsg> getIndexMsg() {
        List<Indexmsg> indexmsgs = indexmsgMapper.selectByExample(new IndexmsgExample());
        return indexmsgs;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int delIndexmsg(Indexmsg indexmsg) {
        indexmsg.setMsgContent("");
        int i = indexmsgMapper.updateByPrimaryKey(indexmsg);
        return i;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int updateIndexMsg(Indexmsg indexmsg) {
        int i = indexmsgMapper.updateByPrimaryKey(indexmsg);
        applicationContext.publishEvent(new ContextRefreshedEvent(applicationContext));
        return i;
    }
}
