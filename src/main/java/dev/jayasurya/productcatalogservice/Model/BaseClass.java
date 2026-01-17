package dev.jayasurya.productcatalogservice.Model;


import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public abstract  class BaseClass {
    private Long id;
    private Date createdAt;
    private Date updatedAt;
    private State state;

}
