package com.hcycom.jhipster.service.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.hcycom.jhipster.domain.Views;

@Mapper
public interface ViewsMapper {

	/**
	 * 添加视图
	 * @param product
	 * @return
	 */
	@Insert("INSERT INTO views(uuid,views_name_cn,views_name_en,views_super,views_icon,views_level) VALUES "
			+ "(#{views.uuid},#{views.views_name_cn},#{views.views_name_en},"
			+ "#{views.views_super},#{views.views_icon}"
			+ ",#{views.views_level})")
	public int addViews(@Param("views") Views views);

	/**
	 * 修改产品信息
	 * 
	 * @param role
	 * @return
	 */
	@Update("update views set views_name_cn = #{views.views_name_cn} ,"
			+ "views_name_en = #{views.views_name_en},"
			+ "views_super = #{views.views_super},"
			+ "views_icon = #{views.views_icon},"
			+ "views_level = #{views.views_level}"
			+ "where uuid = #{views.uuid}")
	public int updateViews(@Param("views") Views views);

	/**
	 * 根据uuid删除产品信息
	 * 
	 * @param role
	 * @return
	 */
	@Delete("delete from views where uuid=#{views.uuid}")
	public int deleteViews(@Param("views") Views views);

	/**
	 * 根据uuid查询产品信息
	 * @param product
	 * @return
	 */
	@Select("SELECT * FROM views WHERE uuid=#{views.uuid}")
	public Views getViewsByID(@Param("views") Views views);

	/**
	 * 查询所有产品
	 * @return
	 */
	@Select("SELECT * FROM views")
	public List<Views> getAllViews();

}
