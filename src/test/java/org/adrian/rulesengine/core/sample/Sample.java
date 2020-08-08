package org.adrian.rulesengine.core.sample;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.adrian.rulesengine.core.Combinator;
import org.adrian.rulesengine.core.CombinedCondition;
import org.adrian.rulesengine.core.Condition;
import org.adrian.rulesengine.core.Rule;
import org.adrian.rulesengine.core.operator.Operators;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class Sample {

    @Test
    void simpleCase() {
        // Rule : if the power of the car is greater than 250, paint it red
        var condition = new Condition<>(Operators.gt(), Car::getPower, 250);
        var rule = new Rule<>(condition, voiture -> voiture.paint("red"));

        var car = new Car("white", 256);
        rule.fire(car);
        assertThat(car.getColor()).isEqualTo("red");
    }

    @Test
    void combinatedConditionCase() {
        // Rule : if the power of the car is greater than 250 OR color is grey, paint it red
        var powerCondition = new Condition<>(Operators.gt(), Car::getPower, 250);
        var colorCondition = new Condition<>(Operators.String.eq().ignoreCase().build(), Car::getColor, "grey");
        var combinedCondition = new CombinedCondition<>(Combinator.OR, List.of(powerCondition, colorCondition));
        var rule = new Rule<>(combinedCondition, voiture -> voiture.paint("red"));

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
