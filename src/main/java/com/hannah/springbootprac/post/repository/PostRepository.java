package com.hannah.springbootprac.post.repository;
import com.hannah.springbootprac.post.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PostRepository extends JpaRepository<Post,Long> {

}
