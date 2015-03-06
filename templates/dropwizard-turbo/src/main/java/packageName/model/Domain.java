package ${packageName}.model;

import javax.validation.constraints.NotNull;
import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "${domainSnakeName}")
@ApiModel(value = "Representation of a ${domainName}")
public class ${domainName} {

    @Id
    @JsonProperty
    @NotNull
    @ApiModelProperty(required=true)
    private Integer id;

    @Column
    @JsonProperty
    private String message;

}
