package entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Using jackson to deserialize json to java object
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class TickerMasterObject {

  private String id;
  private String name;
  private String url;
  private double distance;
  private List<Image> images;
  private List<Classification> classifications;
  private Embedded _embedded;

  public String getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public String getUrl() {
    return url;
  }

  public double getDistance() {
    return distance;
  }

  public List<Image> getImages() {
    return images;
  }

  public List<Classification> getClassifications() {
    return classifications;
  }

  public Embedded get_embedded() {
    return _embedded;
  }
}
