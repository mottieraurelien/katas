package io.swagger.model;

import java.util.Objects;

import com.carbonit.gpe.workshops.library_api.model.IReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.hateoas.ResourceSupport;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Reference
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2019-02-26T18:01:48.763Z[GMT]")
public class Reference  implements IReference { // TODO: make this class  extend ResourceSupport
  @JsonProperty("referenceId")
  private Long referenceId = null;

  @JsonProperty("title")
  private String title = null;

  @JsonProperty("borrowed")
  private Boolean borrowed = false;

  @JsonProperty("author_name")
  private String authorName = null;

  public Reference referenceId(Long referenceId) {
    this.referenceId = referenceId;
    return this;
  }

  /**
   * Get referenceId
   * @return referenceId
  **/
  @ApiModelProperty(value = "")

  public Long getReferenceId() {
    return referenceId;
  }

  public void setReferenceId(Long referenceId) {
    this.referenceId = referenceId;
  }

  public Reference title(String title) {
    this.title = title;
    return this;
  }

  /**
   * Get title
   * @return title
  **/
  @ApiModelProperty(required = true, value = "")
  @NotNull

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public Reference borrowed(Boolean borrowed) {
    this.borrowed = borrowed;
    return this;
  }

  /**
   * Get borrowed
   * @return borrowed
  **/
  @ApiModelProperty(value = "")

  public Boolean isBorrowed() {
    return borrowed;
  }

  public void setBorrowed(Boolean borrowed) {
    this.borrowed = borrowed;
  }

  public Reference authorName(String authorName) {
    this.authorName = authorName;
    return this;
  }

  /**
   * Get authorName
   * @return authorName
  **/
  @ApiModelProperty(required = true, value = "")
  @NotNull

  public String getAuthorName() {
    return authorName;
  }

  public void setAuthorName(String authorName) {
    this.authorName = authorName;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Reference reference = (Reference) o;
    return Objects.equals(this.referenceId, reference.referenceId) &&
        Objects.equals(this.title, reference.title) &&
        Objects.equals(this.borrowed, reference.borrowed) &&
        Objects.equals(this.authorName, reference.authorName);
  }

  @Override
  public int hashCode() {
    return Objects.hash(referenceId, title, borrowed, authorName);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Reference {\n");
    
    sb.append("    referenceId: ").append(toIndentedString(referenceId)).append("\n");
    sb.append("    title: ").append(toIndentedString(title)).append("\n");
    sb.append("    borrowed: ").append(toIndentedString(borrowed)).append("\n");
    sb.append("    authorName: ").append(toIndentedString(authorName)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}
