package com.pluralsight.migratingjunit4junit5.airport;

import static org.junit.Assert.assertEquals;

public class BonusPolicy3Test extends AbstractBonusPolicyTest {

    @Override
    public void checkBonusPolicy() {
        assertEquals(630, distancesManager.getPassengersPointsMap().get(new Passenger("123-45-6799", "Bethany King", "US")).longValue());
    }

}
