package blog.blog.controllers;

import blog.blog.model.Post;
import blog.blog.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/")
@CrossOrigin(origins = "http://localhost:3000", exposedHeaders = "X-Total-Count")
public class PostControl {
    PostService postService;

    @Autowired
    public void setPostService(PostService postService) {
        this.postService = postService;
    }

    @GetMapping
    public List<Post> findAll() {
        return postService.findAll();
    }

    @GetMapping(path = "/{id}")
    public Optional<Post> findOne(@PathVariable(name = "id") Long id) {
        return postService.findById(id);
    }

    @PostMapping
    public Post create(@RequestBody Post post) {
        return postService.create(post);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<Post> update(@RequestBody Post post, @PathVariable(name = "id") Long id) {
        if (postService.findById((id)) == null) {
            return ResponseEntity.notFound().build();
        } else {
            post.setId(id);
            Post postUpdated = postService.create(post);

           return ResponseEntity.ok(postUpdated);
        }
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> delete(@PathVariable(name = "id") Long id) {
        return postService
                .findById(id)
                .map(record -> {
                    postService.delete(record);
                    return ResponseEntity.status(202).build();
                }).orElse(ResponseEntity.notFound().build());
    }
}
