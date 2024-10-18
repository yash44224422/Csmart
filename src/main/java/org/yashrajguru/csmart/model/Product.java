package org.yashrajguru.csmart.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.Date;

@Entity
@Data//lombok for getter setter
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )//for make id auto
    private int id;
    private String name;
    private String description;
    private String brand;
    private BigDecimal price;
    private String category;
    //@JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "dd-MM-yyyy")//new ui dont need  3 vala
    private Date releaseDate;
    private boolean productAvailable;
    private int stockQuantity;


    private String imageName;
    private  String imageType;
    @Lob //Large object for byte[] store big
    private byte[] imageData;
}
