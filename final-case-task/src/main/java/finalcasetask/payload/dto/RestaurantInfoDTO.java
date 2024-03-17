package finalcasetask.payload.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RestaurantInfoDTO{

    private String id;

    private String name;

    private Double latitude;

    private Double longitude;

    private Double score;

    private List<UserReviewDTO> reviews;

    public RestaurantInfoDTO(String id, String name, Double latitude, Double longitude, Double score) {
        this.id = id;
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
        this.score = score;
    }

    @Override
    public String toString() {
        return "RestaurantInfoDTO{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", score=" + score +
                '}';
    }
}