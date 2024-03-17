package finalcaserestaurant.service;

import finalcaserestaurant.exception.ResourceNotFoundException;
import finalcaserestaurant.general.Messages;
import finalcaserestaurant.mapper.RestaurantMapper;
import finalcaserestaurant.payload.dto.RestaurantDTO;
import finalcaserestaurant.payload.entity.Restaurant;
import finalcaserestaurant.payload.request.RestaurantRequest;
import finalcaserestaurant.payload.request.RestaurantUpdatedRequest;
import finalcaserestaurant.repository.RestaurantRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RestaurantService {

    private final RestaurantRepository restaurantRepository;

    public RestaurantService(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    public RestaurantDTO save(RestaurantRequest request) {

        Restaurant restaurant = RestaurantMapper.INSTANCE.converToRestaurant(request);

        restaurant = restaurantRepository.save(restaurant);

        return RestaurantMapper.INSTANCE.convertToRestaurantDTO(restaurant);
    }

    public void deleteRestaurant(Long id) {

        Restaurant restaurant = restaurantRepository.findById(id).orElseThrow(()->
                new ResourceNotFoundException(String.format(Messages.NOT_FOUND_RESTAURANT_MESSAGE)));

        restaurantRepository.deleteById(id);
    }

    public RestaurantDTO updateRestaurant(Long id, RestaurantUpdatedRequest request) {


        Restaurant restaurant = restaurantRepository.findById(id).orElseThrow(()->
                new ResourceNotFoundException(String.format(Messages.NOT_FOUND_RESTAURANT_MESSAGE)));

        RestaurantMapper.INSTANCE.updatedRestaurantFields(restaurant,request);

        restaurant.setName(request.getName());
        restaurant.setLatitude(request.getLatitude());
        restaurant.setLongitude(request.getLongitude());

        Restaurant updatedRestaurant = restaurantRepository.save(restaurant);

        return RestaurantMapper.INSTANCE.convertToRestaurantDTO(updatedRestaurant);

    }
}
