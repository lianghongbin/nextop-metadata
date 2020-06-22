package com.nextop.metadata.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.nextop.metadata.entity.Field;

import java.util.List;

/**
 * 租户信息 Mapper 接口
 *
 * @author majiangping
 * @since 2020-04-09
 */
public interface FieldMapper extends BaseMapper<Field> {

    /**
     * 根据类ID，取出所有类属性
     * @param id 类ID
     * @return 属性列表
     */
    List<Field> findAllByClassId(Long id);
}
