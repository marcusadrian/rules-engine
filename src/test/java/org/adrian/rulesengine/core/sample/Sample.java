package org.adrian.rulesengine.core.sample;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.adrian.rulesengine.core.Combinator;
import org.adrian.rulesengine.core.CombinedCondition;
import org.adrian.rulesengine.core.Condition;
import org.adrian.rulesengine.core.Rule;
import org.adrian.rulesengine.core.execution.RuleExecution;
import org.adrian.rulesengine.core.operator.Operators;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
class Sample {

    @Test
    void simpleCase() {
        // Rule : if the power of the car is greater than 250, paint it red
        Rule<Car> rule = Rule.builder(Car.class)
                .addPredicate(new Condition<>(Operators.gt(), Car::getPower, 250))
                .action(voiture -> voiture.paint("red"))
                .build();

        var car = new Car("white", 256);
        RuleExecution<Car> ruleExecution = rule.fire(car);
        assertThat(car.getColor()).isEqualTo("red");
        log.info("{}", ruleExecution);
    }

    @Test
    void combinatedConditionCase() {
        // Rule : if the power of the car is greater than 250 OR color is grey, paint it red
        CombinedCondition<Car> combinedCondition = CombinedCondition.builder(Car.class, Combinator.OR)
                .addPredicate(new Condition<>(Operators.gt(), Car::getPower, 250))
                .addPredicate(new Condition<>(Operators.String.eq().ignoreCase().build(), Car::getColor, "grey"))
                .build();

        Rule<Car> rule = Rule.builder(Car.class)
                .addPredicate(combinedCondition)
                .action(voiture -> voiture.paint("red"))
                .build();

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
