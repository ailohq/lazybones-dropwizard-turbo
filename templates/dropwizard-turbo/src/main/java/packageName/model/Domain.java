package ${packageName}.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "${domainSnakeName}")
public class ${domainName} {

    @Id
    @JsonProperty
    private Integer id;

    @Column
    @JsonProperty
    private String message;

}
