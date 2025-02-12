package examples.spring.four.profiles.kindergarten;

import examples.spring.four.profiles.Food;
import examples.spring.four.profiles.FoodProviderService;

import java.util.List;

public class FoodProviderServiceImpl implements FoodProviderService {
    @Override
    public List<Food> provideLunchSet() {
        return List.of(new Food("Milk"), new Food("Biscuits"));
    }
}
