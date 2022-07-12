package com.ling.manager.visit.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ling.visit.pojo.Visit;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Service;

/**
 * @author ling
 * @time 2022/6/28 12:32
 */
public interface VisitMapper extends BaseMapper<Visit> {
    @Select({"<script>",
            "SELECT c.id,customer_id,customer_name,c.employee_id FROM tb_manager m right JOIN tb_customer c on c.employee_id = m.employee_id",
            "<where>",
                "<if test = 'ew != null'>",
                    "<if test = 'ew.sqlSegment != null'>${ew.sqlSegment}</if>",
                "</if>",
            "</where>",
            "</script>"
            })
    IPage<Visit> selectByRole(IPage<Visit> page,@Param("ew") QueryWrapper<Visit> queryWrapper);
}
