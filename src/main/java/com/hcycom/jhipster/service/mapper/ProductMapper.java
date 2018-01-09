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
	@Insert("INSERT INTO product(uuid,product_name,product_desc,product_status,product_icon,product_order) VALUES "
			+ "(#{product.uuid},#{product.product_name},#{product.product_desc},"
			+ "#{product.product_status},#{product.product_icon},#{product.product_order})")
	public int addProduct(@Param("product") Product product);

	/**
	 * 添加产品权限
	 * 
	 * @param product
	 * @return
	 */
	@Insert("INSERT INTO authority(uuid,authority_name,authority_type,foreign_uuid,authority_status) VALUES "
			+ "(#{product.uuid},#{product.product_name},1,#{product.uuid},#{product.product_status})")
	public int addProductAuthority(@Param("product") Product product);

	/**
	 * 修改产品信息
	 * 
	 * @param role
	 * @return
	 */
	@Update("UPDATE authority LEFT JOIN product " + "ON authority.foreign_uuid = product.uuid "
			+ "SET product.product_name = #{product.product_name},  "
			+ "product.product_desc = #{product.product_desc}, "
			+ "product.product_status = #{product.product_status}, "
			+ "product.product_icon = #{product.product_icon}, "
			+ "authority.authority_name = #{product.product_name}, "
			+ "authority.authority_status = #{product.product_status}  WHERE product.uuid=#{product.uuid}")
	public int updateProduct(@Param("product") Product product);

	@Update("UPDATE authority LEFT JOIN product ON authority.foreign_uuid = product.uuid "
			+ "SET product.product_status = #{product.product_status}, "
			+ "authority.authority_status = #{product.product_status} WHERE product.uuid=#{product.uuid}")
	public int updateProductActivation(@Param("product") Product product);
	
	@Update("UPDATE product "
			+ "SET product_order = #{product.product_order}, "
			+ " WHERE uuid=#{product.uuid}")
	public int updateProductOrder(@Param("product") Product product);

	/**
	 * 根据uuid删除产品信息
	 * 
	 * @param role
	 * @return
	 */
	@Delete("DELETE  p,a,r FROM product p LEFT JOIN authority a ON a.foreign_uuid = p.uuid "
			+ "LEFT JOIN role_authority r ON a.uuid = r.authority_uuid "
			+ "WHERE p.uuid=#{product.uuid}")
	public int deleteProduct(@Param("product") Product product);

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
	 * 根据角色id查询角色拥有的权限
	 * 
	 * @param roleid
	 * @return
	 */
	@Select("select * FROM product where uuid in "
			+ "(select foreign_uuid from authority where authority_type='1' and uuid in "
			+ "(select authority_uuid from role_authority where role_uuid=#{roleid}))")
	public List<Product> getAllProductByRole(@Param("roleid") int roleid);

	/**
	 * 模糊查询产品
	 * 
	 * @param product_name
	 * @return
	 */
	@Select("SELECT * FROM product where product_name COLLATE utf8_general_ci like #{product_name}")
	public List<Product> getAllProductByLike(@Param("product_name")String product_name);

}
