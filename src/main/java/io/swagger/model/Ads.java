package io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.model.Photo;
import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * AdsInner
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-10-11T11:28:57.691Z")

public class Ads   {
  @JsonProperty("id")
  private Integer id = null;

  @JsonProperty("description")
  private String description = null;

  @JsonProperty("typology")
  private String typology = null;

  @JsonProperty("houseSize")
  private Integer houseSize = null;

  @JsonProperty("gardenSize")
  private Integer gardenSize = null;

  @JsonProperty("pictures")
  @Valid
  private List<Integer> pictures = null;

  public Ads id(Integer id) {
    this.id = id;
    return this;
  }

  /**
   * Get id
   * @return id
  **/
  @ApiModelProperty(example = "1", value = "")


  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Ads description(String description) {
    this.description = description;
    return this;
  }

  /**
   * Get description
   * @return description
  **/
  @ApiModelProperty(example = "Este piso es una ganga, compra, compra, COMPRA!!!!!", value = "")


  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Ads typology(String typology) {
    this.typology = typology;
    return this;
  }

  /**
   * Get typology
   * @return typology
  **/
  @ApiModelProperty(example = "CHALET", value = "")


  public String getTypology() {
    return typology;
  }

  public void setTypology(String typology) {
    this.typology = typology;
  }

  public Ads houseSize(Integer houseSize) {
    this.houseSize = houseSize;
    return this;
  }

  /**
   * Get houseSize
   * @return houseSize
  **/
  @ApiModelProperty(example = "300", value = "")


  public Integer getHouseSize() {
    return houseSize;
  }

  public void setHouseSize(Integer houseSize) {
    this.houseSize = houseSize;
  }

  public Ads gardenSize(Integer gardenSize) {
    this.gardenSize = gardenSize;
    return this;
  }

  /**
   * Get gardenSize
   * @return gardenSize
  **/
  @ApiModelProperty(example = "0", value = "")


  public Integer getGardenSize() {
    return gardenSize;
  }

  public void setGardenSize(Integer gardenSize) {
    this.gardenSize = gardenSize;
  }

  public Ads pictures(List<Integer> pictures) {
    this.pictures = pictures;
    return this;
  }

  public Ads addPicturesItem(Integer picturesItem) {
    if (this.pictures == null) {
      this.pictures = new ArrayList<Integer>();
    }
    this.pictures.add(picturesItem);
    return this;
  }

  /**
   * Get pictures
   * @return pictures
  **/
  @ApiModelProperty(value = "")

  @Valid

  public List<Integer> getPictures() {
    return pictures;
  }

  public void setPictures(List<Integer> pictures) {
    this.pictures = pictures;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Ads adsInner = (Ads) o;
    return Objects.equals(this.id, adsInner.id) &&
        Objects.equals(this.description, adsInner.description) &&
        Objects.equals(this.typology, adsInner.typology) &&
        Objects.equals(this.houseSize, adsInner.houseSize) &&
        Objects.equals(this.gardenSize, adsInner.gardenSize) &&
        Objects.equals(this.pictures, adsInner.pictures);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, description, typology, houseSize, gardenSize, pictures);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class AdsInner {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    description: ").append(toIndentedString(description)).append("\n");
    sb.append("    typology: ").append(toIndentedString(typology)).append("\n");
    sb.append("    houseSize: ").append(toIndentedString(houseSize)).append("\n");
    sb.append("    gardenSize: ").append(toIndentedString(gardenSize)).append("\n");
    sb.append("    pictures: ").append(toIndentedString(pictures)).append("\n");
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

