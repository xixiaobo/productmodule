package com.hcycom.jhipster.service.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.hcycom.jhipster.domain.Product;

@Mapper
public interface ProductMapper {

	/**
	 * 添加产品
	 * 
	 * @param product
	 * @return
	 */
	@Insert("INSERT INTO product(uuid,product_name,product_desc,product_status,product_icon) VALUES "
			+ "(#{product.uuid},#{product.product_name},#{product.product_desc},"
			+ "#{product.product_status},#{product.product_icon})")
	public int addProduct(@Param("product") Product product);
	
	/**
	 * 添加产品权限
	 * 
	 * @param product
	 * @return
	 */
	@Insert("INSERT INTO authority(authority_name,authority_type,foreign_uuid,auhority_status) VALUES "
			+ "(#{product.product_name},1,"
			+ "#{product.uuid},#{product.product_status})")
	public int addProductAuthority(@Param("product") Product product);

	/**
	 * 修改产品信息
	 * 
	 * @param role
	 * @return
	 */
	@Update("update product set product_name = #{product.product_name} ,product_desc = #{product.product_desc} ,"
			+ "product_status = #{product.product_status} ,product_icon = #{product.product_icon} "
			+ "where uuid = #{product.uuid}")
	public int updateProduct(@Param("product") Product product);
	
	/**
	 * 修改产品权限信息
	 * 
	 * @param role
	 * @return
	 */
	@Update("update authority set authority_name = #{product.product_name} ,"
			+ "auhority_status = #{product.product_status}  "
			+ "where authority_type=1 and foreign_uuid = #{product.uuid}")
	public int updateProductAuthority(@Param("product") Product product);

	/**
	 * 根据uuid删除产品信息
	 * 
	 * @param role
	 * @return
	 */
	@Delete("delete from product where uuid=#{product.uuid}")
	public int deleteProduct(@Param("product") Product product);
	
	/**
	 * 根据产品uuid删除产品权限
	 * 
	 * @param role
	 * @return
	 */
	@Delete("delete authority,role_authority from where authority left join role_authority foreign_uuid=#{product.uuid}")
	public int deleteProductAuthorty(@Param("product") Product product);

	/**
	 * 根据uuid查询产品信息
	 * 
	 * @param product
	 * @return
	 */
	@Select("SELECT * FROM product WHERE uuid=#{product.uuid}")
	public Product getProductByID(@Param("product") Product product);

	/**
	 * 查询所有产品
	 * 
	 * @return
	 */
	@Select("SELECT * FROM product")
	public List<Product> getAllProduct();

	/**
	 * 根据角色id删除角色拥有的权限
	 * @param roleid
	 * @return
	 */
	@Select("select * FROM product where uuid in "
			+ "(select foreign_uuid from authority where authority_type='1' and uuid in "
			+ "(select authority_uuid from role_authority where role_uuid=#{roleid}))")
	public List<Product> getAllProductByRole(@Param("roleid") int roleid);

}
