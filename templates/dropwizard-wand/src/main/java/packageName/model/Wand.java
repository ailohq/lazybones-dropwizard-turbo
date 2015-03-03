package packageName.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "wand")
public class Wand {

    @Id
    @JsonProperty
    private String name;

    @Column
    @JsonProperty
    private String master;

}
