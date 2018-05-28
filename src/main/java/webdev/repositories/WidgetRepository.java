package webdev.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import webdev.models.Widget;

public interface WidgetRepository extends CrudRepository<Widget, Integer> {

    @Query("SELECT w FROM widget w ORDER BY w.widgetOrder")
    Iterable<Widget> findAllOrderByWidgetOrder();
}
