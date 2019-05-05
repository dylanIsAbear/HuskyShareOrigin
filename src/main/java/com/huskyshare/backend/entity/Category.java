package com.huskyshare.backend.entity;

import com.huskyshare.backend.json_entity.Jsonable;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;


@Entity
@Table(name = "tb_category")
public class Category implements Serializable {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "category_id")
   private Integer id;

   @Column(name = "category_name")
   private String category;

   public Integer getId() {
      return id;
   }

   public void setId(Integer id) {
      this.id = id;
   }

   public String getCategory() {
      return category;
   }

   public void setCategory(String category) {
      this.category = category;
   }

}
