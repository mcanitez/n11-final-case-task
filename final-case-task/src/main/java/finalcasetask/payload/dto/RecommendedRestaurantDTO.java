package finalcasetask.payload.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RecommendedRestaurantDTO {

    private String id;
    private String name;
    private Double latitude;
    private Double longitude;
    private double weightedScore;
    private Double score;



    public RecommendedRestaurantDTO(String id, String name, double weightedScore, Double latitude, Double longitude, Double score) {
        this.id = id;
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
        this.weightedScore = weightedScore;
        this.score = score;
    }

    @Override
    public String toString() {
        return "RecommendedRestaurantDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", weightedScore=" + weightedScore +
                ", score=" + score +
                '}';
    }
}
