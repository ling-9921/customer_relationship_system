package com.ling.manager.customer.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ling.customer.pojo.Customer;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * @author ling
 * @time 2022/6/29 9:07
 */
public interface CustomerMapper extends BaseMapper<Customer> {

    @Select({"<script>",
            "SELECT c.id,customer_id,customer_name,c.employee_id,phone,address,info FROM tb_manager m right JOIN tb_customer c",
            " on c.employee_id = m.employee_id",
            "<where>",
                "<if test='ew != null'>",
                    "<if test = 'ew.sqlSegment != null '>${ew.sqlSegment}</if>",
                "</if>",
            "</where>",
            "</script>"})
    IPage<Customer>  selectByRole(IPage<Customer>page, @Param("ew")QueryWrapper<Customer> queryWrapper);

    @Update({"<script>",
            "UPDATE tb_customer set employee_id = #{target} ",
            "<where>",
                "<if test = 'ew != null'>",
                "<if test = 'ew.sqlSegment !=null'>${ew.sqlSegment}</if>",
                "</if>",
            "</where>",
            "</script>"})
    int updateOwner(@Param("target") Integer target,@Param("ew")QueryWrapper<Customer> queryWrapper);
}
