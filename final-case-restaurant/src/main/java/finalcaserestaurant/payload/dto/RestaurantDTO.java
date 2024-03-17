package finalcaserestaurant.payload.dto;


public record RestaurantDTO (Long id,
                             String name,
                             Double latitude,
                             Double longitude,
                             Double score) {

}
