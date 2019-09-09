package io.swagger.model;

import java.util.Objects;

import com.carbonit.gpe.workshops.library_api.model.IUser;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.model.Reference;
import java.util.ArrayList;
import java.util.List;

import org.springframework.hateoas.ResourceSupport;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * User
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2019-02-26T18:01:48.763Z[GMT]")
public class User implements IUser { // TODO: make this class extend ResourceSupport
  @JsonProperty("userId")
  private Long userId = null;

  @JsonProperty("name")
  private String name = null;

  @JsonProperty("surname")
  private String surname = null;

  @JsonProperty("email")
  private String email = null;

  @JsonProperty("librarian")
  private Boolean librarian = false;

  @JsonProperty("borrowing")
  @Valid
  private List<Reference> borrowing = null;

  public User userId(Long userId) {
    this.userId = userId;
    return this;
  }

  /**
   * Get userId
   * @return userId
  **/
  @ApiModelProperty(value = "")

  public Long getUserId() {
    return userId;
  }

  public void setUserId(Long userId) {
    this.userId = userId;
  }

  public User name(String name) {
    this.name = name;
    return this;
  }

  /**
   * Get name
   * @return name
  **/
  @ApiModelProperty(required = true, value = "")
  @NotNull

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public User surname(String surname) {
    this.surname = surname;
    return this;
  }

  /**
   * Get surname
   * @return surname
  **/
  @ApiModelProperty(required = true, value = "")
  @NotNull

  public String getSurname() {
    return surname;
  }

  public void setSurname(String surname) {
    this.surname = surname;
  }

  public User email(String email) {
    this.email = email;
    return this;
  }

  /**
   * Get email
   * @return email
  **/
  @ApiModelProperty(required = true, value = "")
  @NotNull

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public User librarian(Boolean librarian) {
    this.librarian = librarian;
    return this;
  }

  /**
   * Get librarian
   * @return librarian
  **/
  @ApiModelProperty(value = "")

  public Boolean isLibrarian() {
    return librarian;
  }

  public void setLibrarian(Boolean librarian) {
    this.librarian = librarian;
  }

  public User borrowing(List<Reference> borrowing) {
    this.borrowing = borrowing;
    return this;
  }

  public User addBorrowingItem(Reference borrowingItem) {
    if (this.borrowing == null) {
      this.borrowing = new ArrayList<Reference>();
    }
    this.borrowing.add(borrowingItem);
    return this;
  }

  /**
   * Get borrowing
   * @return borrowing
  **/
  @ApiModelProperty(value = "")
  @Valid
  public List<Reference> getBorrowing() {
    return borrowing;
  }

  public void setBorrowing(List<Reference> borrowing) {
    this.borrowing = borrowing;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    User user = (User) o;
    return Objects.equals(this.userId, user.userId) &&
        Objects.equals(this.name, user.name) &&
        Objects.equals(this.surname, user.surname) &&
        Objects.equals(this.email, user.email) &&
        Objects.equals(this.librarian, user.librarian) &&
        Objects.equals(this.borrowing, user.borrowing);
  }

  @Override
  public int hashCode() {
    return Objects.hash(userId, name, surname, email, librarian, borrowing);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class User {\n");
    
    sb.append("    userId: ").append(toIndentedString(userId)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    surname: ").append(toIndentedString(surname)).append("\n");
    sb.append("    email: ").append(toIndentedString(email)).append("\n");
    sb.append("    librarian: ").append(toIndentedString(librarian)).append("\n");
    sb.append("    borrowing: ").append(toIndentedString(borrowing)).append("\n");
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
