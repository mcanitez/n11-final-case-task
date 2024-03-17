package finalcaserestaurant.repository;

import finalcaserestaurant.payload.entity.Restaurant;
import org.springframework.data.solr.repository.SolrCrudRepository;

public interface RestaurantRepository extends SolrCrudRepository<Restaurant,Long> {


}
