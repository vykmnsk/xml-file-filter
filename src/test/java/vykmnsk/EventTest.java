package vykmnsk;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


public class EventTest {
    @Test
    public void canAcceptValidEvents(){
//        Event ev = new Event();
//        ev.setBuyerParty("some_bank");
//        ev.setSellerParty("EMU_BANK");
//        ev.setPremiumCurrency("AUD");
//        assertTrue(ev.isValid());

        Event ev = new Event();
        ev.setBuyerParty("some_bank");
        ev.setSellerParty("BISON_BANK");
        ev.setPremiumCurrency("USD");
        assertTrue(ev.isValid());
    }

    @Test
    public void canRejectEventsWithInvalidSellerName(){
        Event ev = new Event();
        ev.setBuyerParty("emu_bank");
        ev.setSellerParty("POSUM_BANK");
        ev.setPremiumCurrency("AUD");
        assertFalse(ev.isValid());

        ev = new Event();
        ev.setBuyerParty("emu_bank");
        ev.setSellerParty("DEAR_BANK");
        ev.setPremiumCurrency("USD");
        assertFalse(ev.isValid());
    }

    @Test
    public void canRejectEventsWithInvalidSellerCurrency(){
        Event ev = new Event();
        ev.setBuyerParty("wolf_bank");
        ev.setSellerParty("emu_bank");
        ev.setPremiumCurrency("USD");
        assertFalse(ev.isValid());

        ev = new Event();
        ev.setBuyerParty("emu_bank");
        ev.setSellerParty("bison_bank");
        ev.setPremiumCurrency("AUD");
        assertFalse(ev.isValid());
    }

    @Test
    public void canRejectEventsWithAnagramBanks(){
        Event ev = new Event();
        ev.setBuyerParty("MEU_bank");
        ev.setSellerParty("EMU_bank");
        ev.setPremiumCurrency("AUD");
        assertFalse(ev.isValid());

        ev = new Event();
        ev.setBuyerParty("SOBNI_bank");
        ev.setSellerParty("BISON_bank");
        ev.setPremiumCurrency("USD");
        assertFalse(ev.isValid());
    }

}
