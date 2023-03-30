package com.hannah.springbootprac.post.controller;

import com.hannah.springbootprac.post.dto.PostCreateRequestDto;
import com.hannah.springbootprac.post.dto.PostListResponseDto;
import com.hannah.springbootprac.post.dto.PostResponseDto;
import com.hannah.springbootprac.post.dto.PostUpdateRequestDto;
import com.hannah.springbootprac.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class PostApiController {

    private final PostService postService;

    @PostMapping("/api/v1/posts")
    public Long create(@RequestBody PostCreateRequestDto requestDto) {
        return postService.create(requestDto);
    }

    @PutMapping("api/v1/posts/{id}")
    public Long update(@PathVariable Long id, @RequestBody PostUpdateRequestDto requestDto) {
        return postService.update(id, requestDto);
    }

    @DeleteMapping("api/v1/posts/{id}")
    public Long delete(@PathVariable Long id) {
        postService.delete(id);
        return id;
    }

    @GetMapping("api/v1/posts/{id}")
    public PostResponseDto findById(@PathVariable Long id) {
        return postService.findById(id);

    }

    @GetMapping("/api/v1/posts/list")
    public List<PostListResponseDto> findAll(@PageableDefault(sort = {"createdDate"}, direction = Sort.Direction.DESC, size = 5) Pageable pageable) {
        return postService.findAll(pageable);
    }
}
