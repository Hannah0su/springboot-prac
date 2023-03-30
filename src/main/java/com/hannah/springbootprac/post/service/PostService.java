package com.hannah.springbootprac.post.service;


import com.hannah.springbootprac.post.dto.PostCreateRequestDto;
import com.hannah.springbootprac.post.dto.PostListResponseDto;
import com.hannah.springbootprac.post.dto.PostResponseDto;
import com.hannah.springbootprac.post.dto.PostUpdateRequestDto;
import com.hannah.springbootprac.post.entity.Post;
import com.hannah.springbootprac.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.stream.Collectors;


@RequiredArgsConstructor
@Service
public class PostService {
    private final PostRepository postRepository;

    @Transactional
    public Long create(PostCreateRequestDto requestDto) {
        return postRepository.save(requestDto.toEntity()).
                getId();
    }

    @Transactional
    public Long update(Long id, PostUpdateRequestDto requestDto) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));
        post.update(requestDto.getTitle(), requestDto.getContent());
        return id;
    }

    @Transactional
    public void delete(Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));
        postRepository.delete(post);
    }

    @Transactional(readOnly = true)
    public PostResponseDto findById(Long id) {
        Post entity = postRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));

        return new PostResponseDto(entity);

    }

    @Transactional(readOnly = true)
    public List<PostListResponseDto> findAll(Pageable pageable) {
        if (pageable == null) {
            throw new IllegalArgumentException("null");
        }
        Page<Post> postPage = postRepository.findAll(pageable);
        if (postPage.isEmpty()) {
            throw new RuntimeException("페이지 없음");
        }
        return postPage
                .stream()
                .map(PostListResponseDto::new)
                .collect(Collectors.toList());
    }

}
