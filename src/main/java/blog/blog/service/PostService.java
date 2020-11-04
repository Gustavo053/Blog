package blog.blog.service;

import blog.blog.model.Post;
import blog.blog.model.User;
import blog.blog.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostService {
    private PostRepository postRepository;

    @Autowired
    public void setPostRepository(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public Post create(Post post) {
        return postRepository.save(post);
    }

    public void delete(Post post) {
        postRepository.delete(post);
    }

    public void deleteById(Long id) {
        postRepository.deleteById(id);
    }

    public List<Post> findAll() {
        return postRepository.findAll();
    }

    public Optional<Post> findById(Long id) { return postRepository.findById(id); }

    public Post findOne(Long id) {
        return postRepository.getOne(id);
    }

    public Post findByTitulo(String titulo) {
        return postRepository.findByTitulo(titulo);
    }
}
