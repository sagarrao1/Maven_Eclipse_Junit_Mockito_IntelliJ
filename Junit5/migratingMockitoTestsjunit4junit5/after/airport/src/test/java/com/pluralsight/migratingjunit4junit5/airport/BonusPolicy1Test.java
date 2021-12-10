package com.pluralsight.migratingjunit4junit5.airport;

import static org.junit.Assert.assertEquals;

public class BonusPolicy1Test extends AbstractBonusPolicyTest {

    @Override
    public void checkBonusPolicy() {
        assertEquals(210, distancesManager.getPassengersPointsMap().get(new Passenger("900-45-6809", "Susan Todd", "GB")).longValue());
    }

}
