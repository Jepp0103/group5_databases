package com.example.demo.controller.mongoapi;
import com.example.demo.model.mongomodels.LectureMongo;
import com.example.demo.repository.mongorepository.LectureMongoRepository;
import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import com.mongodb.MongoClientURI;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping(value = "/api")
public class LectureMongoController {
    @Autowired
    private LectureMongoRepository lectureMongoRepository;

    @GetMapping("/lecturesmongo")
    public Iterable<LectureMongo> getMongoLectures()  {
        return lectureMongoRepository.findAll();
    }

    @PostMapping("/addmongolecture")
    public LectureMongo addMongoLecture(@RequestBody LectureMongo lectureMongo)  {
        return lectureMongoRepository.save(lectureMongo);
    }
}