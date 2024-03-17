package finalcaserestaurant.payload.entity;


import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.solr.core.mapping.Indexed;
import org.springframework.data.solr.core.mapping.SolrDocument;

@SolrDocument(solrCoreName = "mycore2")
public class RestaurantSolr {

    @Id
    @Indexed(name = "id", type = "string")
    private String id;

    @NotNull
    @Indexed(name = "name", type = "string")
    private String name;

    @NotNull
    @Indexed(name = "latitude", type = "double")
    private double latitude;

    @NotNull
    @Indexed(name = "longitude", type = "double")
    private double longitude;

    @Indexed(name = "score", type = "double")
    private Double score;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }
}

