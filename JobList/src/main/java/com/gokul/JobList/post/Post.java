package com.gokul.JobList.post;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Document(collection = "JobPost")

public class Post{

    @Id
    private Integer id;

    private String desc;

    private Integer exp;

    private String profile;

    private String techs[];

}
