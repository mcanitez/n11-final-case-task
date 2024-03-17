package finalcaserestaurant.mapper;

import finalcaserestaurant.payload.dto.RestaurantDTO;
import finalcaserestaurant.payload.entity.Restaurant;
import finalcaserestaurant.payload.request.RestaurantRequest;
import finalcaserestaurant.payload.request.RestaurantUpdatedRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface RestaurantMapper {

    RestaurantMapper INSTANCE = Mappers.getMapper(RestaurantMapper.class);

    Restaurant converToRestaurant (RestaurantRequest restaurantRequest);

    RestaurantDTO convertToRestaurantDTO(Restaurant restaurant);

    List<RestaurantDTO> convertToRestaurantDTOs(List<Restaurant> restaurants);

    @Mapping(target = "id" , ignore = true)
    void updatedRestaurantFields (@MappingTarget Restaurant restaurant, RestaurantUpdatedRequest request);
}
