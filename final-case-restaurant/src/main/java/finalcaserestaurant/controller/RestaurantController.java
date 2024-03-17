package finalcaserestaurant.controller;


import finalcaserestaurant.RestaurantSolr.SolrRepository;
import finalcaserestaurant.payload.entity.RestaurantSolr;
import finalcaserestaurant.general.RestResponse;
import finalcaserestaurant.payload.dto.RestaurantDTO;
import finalcaserestaurant.payload.request.RestaurantRequest;
import finalcaserestaurant.payload.request.RestaurantUpdatedRequest;
import finalcaserestaurant.service.RestaurantService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping("/api/v1/restaurants")
public class RestaurantController {

    private final RestaurantService restaurantService;

    private final SolrRepository solrRepository;


    public RestaurantController(RestaurantService restaurantService, SolrRepository solrRepository) {
        this.restaurantService = restaurantService;
        this.solrRepository = solrRepository;
    }

    @GetMapping("/solr")
    public List<RestaurantSolr> getAllRestaurantSolr() {
      Iterable  restaurantList =  solrRepository.findAll();
        List listSolr =  StreamSupport.stream(restaurantList.spliterator(), false)
                .toList();
        return listSolr;
    }

    @PostMapping
    public ResponseEntity<RestResponse<RestaurantDTO>> saveRestaurant(@RequestBody RestaurantRequest request) {

        RestaurantDTO restaurantDTO = restaurantService.save(request);

        return ResponseEntity.ok(RestResponse.of(restaurantDTO));
    }

    @DeleteMapping("/{id}")
    public void deleteRestaurant(@PathVariable Long id){
        restaurantService.deleteRestaurant(id);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<RestResponse<RestaurantDTO>> updateRestaurant(@PathVariable Long id,
                                                                        @RequestBody RestaurantUpdatedRequest request){

        RestaurantDTO restaurantDTO= restaurantService.updateRestaurant(id,request);
        return ResponseEntity.ok(RestResponse.of(restaurantDTO));
    }

}
