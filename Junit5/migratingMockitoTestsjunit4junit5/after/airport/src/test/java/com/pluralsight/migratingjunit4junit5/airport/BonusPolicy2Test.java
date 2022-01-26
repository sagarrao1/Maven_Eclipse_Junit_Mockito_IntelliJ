package com.pluralsight.migratingjunit4junit5.airport;

import static org.junit.Assert.assertEquals;

public class BonusPolicy2Test extends AbstractBonusPolicyTest {

    @Override
    public void checkBonusPolicy() {
        assertEquals(420, distancesManager.getPassengersPointsMap().get(new Passenger("900-45-6797", "Harry Christensen", "GB")).longValue());
    }

}
