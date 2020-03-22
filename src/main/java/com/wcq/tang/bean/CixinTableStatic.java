package com.wcq.tang.bean;

import com.wcq.tang.dto.CixinDto;

import java.util.LinkedList;
import java.util.List;

/**
 * @author wcq
 * @version 1.0
 * @date 2020/3/16 16:42
 */
public class CixinTableStatic {
    public static List<CixinDto> cixinDtoList;
    static {
        cixinDtoList = new LinkedList<>();
        CixinTable cixinTable = new CixinTable();
        String[] en = cixinTable.getEn();
        String[] ch = cixinTable.getCh();
        for(int i=0;i<en.length;i++){
            CixinDto dto = new CixinDto(i+1,en[i],ch[i]);
            cixinDtoList.add(dto);
        }
    }
}
