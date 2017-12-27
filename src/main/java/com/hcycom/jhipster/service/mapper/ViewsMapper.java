package com.hcycom.jhipster.service.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.hcycom.jhipster.domain.Product;
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
	 * 添加视图权限
	 * 
	 * @param product
	 * @return
	 */
	@Insert("INSERT INTO authority(authority_name,authority_type,foreign_uuid,auhority_status) VALUES "
			+ "(#{views.views_name_cn},2,"
			+ "#{views.uuid},1)")
	public int addViewsAuthority(@Param("views") Views views);
	
	

	/**
	 * 修改视图信息
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
	 * 修改视图权限信息
	 * 
	 * @param role
	 * @return
	 */
	@Update("update authority set authority_name =  #{views.views_name_cn}"
			+ "where authority_type=2 and foreign_uuid = #{views.uuid}")
	public int updateViewsAuthority(@Param("views") Views views);

	/**
	 * 根据uuid删除产品信息
	 * 
	 * @param role
	 * @return
	 */
	@Delete("delete from views where uuid=#{views.uuid}")
	public int deleteViews(@Param("views") Views views);
	
	/**
	 * 根据视图uuid删除产品权限
	 * 
	 * @param role
	 * @return
	 */
	@Delete("delete from authority where foreign_uuid=#{views.uuid}")
	public int deleteViewsAuthorty(@Param("views") Views views);

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

	@Select("select * FROM product where uuid in "
			+ "(select foreign_uuid from authority where authority_type='2' and uuid in "
			+ "(select authority_uuid from role_authority where role_uuid=#{roleid}))")
	public List<Views> getAllViewsByRole(int roleid);

}
