package finalcaserestaurant.RestaurantSolr;

import finalcaserestaurant.payload.entity.RestaurantSolr;
import org.springframework.data.solr.repository.SolrCrudRepository;

public interface SolrRepository extends SolrCrudRepository<RestaurantSolr,String> {


}
