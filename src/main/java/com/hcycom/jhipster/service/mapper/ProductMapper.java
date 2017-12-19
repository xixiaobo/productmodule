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
	 * @param product
	 * @return
	 */
	@Insert("INSERT INTO product(uuid,product_name,product_desc,product_status,product_icon) VALUES "
			+ "(#{product.uuid},#{product.product_name},#{product.product_desc},"
			+ "#{product.product_status},#{product.product_icon})")
	public int addProduct(@Param("product") Product product);

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
	 * 根据uuid删除产品信息
	 * 
	 * @param role
	 * @return
	 */
	@Delete("delete from product where uuid=#{product.uuid}")
	public int deleteProduct(@Param("product") Product product);

	/**
	 * 根据uuid查询产品信息
	 * @param product
	 * @return
	 */
	@Select("SELECT * FROM product WHERE uuid=#{product.uuid}")
	public Product getProductByID(@Param("product") Product product);

	/**
	 * 查询所有产品
	 * @return
	 */
	@Select("SELECT * FROM product")
	public List<Product> getAllProduct();

}
