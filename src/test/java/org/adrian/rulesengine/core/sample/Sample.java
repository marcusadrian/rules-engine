package org.adrian.rulesengine.core.sample;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.adrian.rulesengine.core.*;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class Sample {

    @Test
    void simpleCase() {
        // Rule : if the power of the car is greater than 250, paint it red
        var condition = new Condition<Car, Integer>(Operator.GT, Car::getPower, 250);
        var rule = new Rule<Car>(condition, voiture -> voiture.paint("red"));

        var car = new Car("white", 256);
        rule.fire(car);
        assertThat(car.getColor()).isEqualTo("red");
    }

    @Test
    void combinatedConditionCase() {
        // Rule : if the power of the car is greater than 250 OR color is grey, paint it red
        var powerCondition = new Condition<Car, Integer>(Operator.GT, Car::getPower, 250);
        var colorCondition = new Condition<Car, String>(Operator.EQ, Car::getColor, "grey");
        var combinedCondition = new CombinedCondition<Car>(Combinator.OR, List.of(powerCondition, colorCondition));
        var rule = new Rule<Car>(combinedCondition, voiture -> voiture.paint("red"));

        var car = new Car("grey", 150);
        rule.fire(car);
        assertThat(car.getColor()).isEqualTo("red");
    }

    @Getter
    @AllArgsConstructor
    private static class Car {
        private String color;
        private final int power;

        public void paint(String color) {
            this.color = color;
        }
    }
}
