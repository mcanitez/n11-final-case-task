package finalcasetask.client;

import finalcasetask.payload.dto.RestaurantInfoDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(value = "restaurant", url = "http://final-case-restaurant:8089/api/v1/restaurants")
public interface RestaurantClient {


    @GetMapping("/solr")
   List<RestaurantInfoDTO> getAllRestaurantSolr();

}
