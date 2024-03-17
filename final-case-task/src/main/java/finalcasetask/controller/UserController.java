package finalcasetask.controller;

import finalcasetask.client.RestaurantClient;
import finalcasetask.general.RestResponse;
import finalcasetask.payload.dto.RecommendedRestaurantDTO;
import finalcasetask.payload.dto.RestaurantInfoDTO;
import finalcasetask.payload.dto.UserDTO;
import finalcasetask.payload.request.UserRequest;
import finalcasetask.payload.request.UserUpdateRequest;
import finalcasetask.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
@Slf4j
public class UserController {

    private final UserService userService;

    private final RestaurantClient restaurantClient;


    @PostMapping
    public ResponseEntity<RestResponse<UserDTO>> saveUser(@Valid @RequestBody UserRequest request) {

        UserDTO userDTO = userService.save(request);

        return ResponseEntity.ok(RestResponse.of(userDTO));
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {

        userService.deleteUser(id);
    }

    @PutMapping("/update/{userId}")
    public ResponseEntity<RestResponse<UserDTO>> updateUser(@PathVariable Long userId,
                                                            @RequestBody UserUpdateRequest request) {

        UserDTO userDTO = userService.updateUser(request);
        return ResponseEntity.ok(RestResponse.of(userDTO));
    }

    @GetMapping("/getAllRestaurant")
    public List<RestaurantInfoDTO> getAllRestaurantSolr() {
        return restaurantClient.getAllRestaurantSolr();
    }

    @GetMapping("/recommend-restaurants")
    public List<RecommendedRestaurantDTO> recommendRestaurants(@RequestParam Double latitude, @RequestParam Double longitude) {

        List<RestaurantInfoDTO> allRestaurants = restaurantClient.getAllRestaurantSolr();
        log.info("Tüm restaurantlar: {}", allRestaurants);

        List<RecommendedRestaurantDTO> recommendedRestaurants = new ArrayList<>();

        for (RestaurantInfoDTO restaurant : allRestaurants) {

            Double restaurantLatitude = restaurant.getLatitude();
            Double restaurantLongitude = restaurant.getLongitude();

            if (restaurantLatitude != null && restaurantLongitude != null) { // Null check
                double distance = calculateDistance(latitude, longitude, restaurantLatitude, restaurantLongitude);

                if (distance > 10.0 || restaurant.getScore() == null) {
                    continue;
                }

                Double restaurantScore = restaurant.getScore();
                log.info("Restoran: {}, Puan: {}, Latitude: {}, Longitude: {}", restaurant.getName(), restaurantScore , restaurant.getLatitude(),restaurant.getLongitude());

                double weightedScore = (restaurantScore * 0.7) + ((10 - distance) * 0.3);
                log.info("Ağırlıklı Puan: {}", weightedScore);

                recommendedRestaurants.add(new RecommendedRestaurantDTO(restaurant.getId(),restaurant.getName(), weightedScore,restaurant.getLatitude(),restaurant.getLongitude(),restaurant.getScore()));
            } else {
                log.warn("Restaurant latitude or longitude is null: {}", restaurant.getName());
            }
        }

        recommendedRestaurants.sort(Comparator.comparingDouble(RecommendedRestaurantDTO::getWeightedScore).reversed());
        log.info("Tavsiye edilen restoranlar: {}", recommendedRestaurants);

        List<RecommendedRestaurantDTO> top3Restaurants = recommendedRestaurants.stream().toList();
        log.info("En iyi 3 restoran: {}", top3Restaurants);

        return top3Restaurants;
    }

    private double calculateDistance(double lat1, double lon1, double lat2, double lon2) {

        double earthRadius = 6371; // Kilometre cinsinden Dünya yarıçapı
        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lon2 - lon1);
        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
                Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) *
                        Math.sin(dLon / 2) * Math.sin(dLon / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return earthRadius * c;
    }
}

