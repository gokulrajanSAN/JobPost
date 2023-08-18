package com.gokul.JobList.post;

import com.mongodb.client.AggregateIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.bson.Document;
import org.springframework.data.mongodb.core.convert.MongoConverter;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RequiredArgsConstructor
@Service
public class PostService {
   private final PostRepo postRepo;

   private final MongoConverter mongoConverter;

   private final MongoClient mongoClient;
//    MongoClient mongoClient = new MongoClient(
//            new MongoClientURI(
//                    ""
//            )
//    );

    public Post save(Post post) {
        return postRepo.save(post);
    }
    public List<Post> findAll() {
        return postRepo.findAll();
    }


    public List<Post> findByText(String text) {
        final List<Post> postList = new ArrayList<>();

//       import org.bson.Document;
        MongoDatabase database = mongoClient.getDatabase("gokulDB");
        MongoCollection<Document> collection = database.getCollection("JobPost");

        AggregateIterable<Document> result = collection.aggregate(Arrays.asList(new Document("$search",
                        new Document("text",
                                new Document("query", text)
                                        .append("path", Arrays.asList("desc", "techs")))),
                new Document("$sort",
                        new Document("exp", 1L)),
                new Document("$limit", 5L)));

        result.forEach(document -> postList.add(mongoConverter.read(Post.class, document)));

        return postList;
    }
}
