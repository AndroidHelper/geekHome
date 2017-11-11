package com.geekhome.entity.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.geekhome.entity.Blog;

public interface BlogDao extends JpaRepository<Blog, Long>{

	@Query(nativeQuery = true, value = "SELECT COUNT(1) FROM BLOG WHERE TYPE_ID IN(SELECT ID FROM BLOG_TYPE WHERE USER_ID=:userId)")
	int getBlogByUserIdCnt(@Param("userId") Long userId);

	@Query(nativeQuery = true, value = "SELECT COUNT(1) FROM BLOG")
	int getBoleCnt();

}
