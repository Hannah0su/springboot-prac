package com.hannah.springbootprac.post.service;


import com.hannah.springbootprac.post.dto.PostCreateRequestDto;
import com.hannah.springbootprac.post.dto.PostResponseDto;
import com.hannah.springbootprac.post.dto.PostUpdateRequestDto;
import com.hannah.springbootprac.post.entity.Post;
import com.hannah.springbootprac.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



@RequiredArgsConstructor
@Service
public class PostService {
    private final PostRepository postRepository;

    @Transactional
    public Long create(PostCreateRequestDto requestDto){
        return postRepository.save(requestDto.toEntity()).
                getId();
    }

    @Transactional
    public Long update(Long id, PostUpdateRequestDto requestDto){
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id="+id));
        post.update(requestDto.getTitle(), requestDto.getContent());
        return id;
    }

    public PostResponseDto findById (Long id){
        Post entity = postRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id="+id));

        return new PostResponseDto(entity);

    }

}
