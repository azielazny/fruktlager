package com.fruktlager.members;

import junit.framework.TestCase;
import org.junit.Test;

public class MemberTest extends TestCase {

    private Address address = new Address("ul. Zabłocka 11", "Poland", "Gdańsk", "80-001");
    private Area area = new Area("Ulvik");

    private Member fullDataMember = new Member(11, "Zenek", "Testowy", address, "+47 555-88-88", MemberType.PRODUCER, "test@test.com", "hasło", area, "testowyLogin");
    private Member correspondenceDataMember = new Member("Zenek", "Testowy", address, "+47 555-88-88", MemberType.PRODUCER, area);

    //edytować dane+
    //dodać/zgłosić boxy/owoce???
    //wyświetlić dane
    //zmienić hasło
    //wyświetlić raport

    @Test
    public void shouldCheckIfMemberCanEditPersonalData() {
        // when
        fullDataMember.editMemberData(new Member("Zenek", "Testowy", address, "+47 555-88-88", MemberType.PRODUCER, area));

        // then
        assertThat(fullDataMember.getMemberData()).isEqualTo(new Member("Zenek", "Testowy", address, "+47 555-88-88", MemberType.PRODUCER, area));
    }

    @Test
    public void shouldCheckIfMemberCanAddNewMember() {
        // when
        fullDataMember.addMember(new Member("Zenek", "Testowy", address, "+47 555-88-88", MemberType.PRODUCER, area));

        // then
        assertThat(fullDataMember.getMemberData()).isEqualTo(new Member("Zenek", "Testowy", address, "+47 555-88-88", MemberType.PRODUCER, area));
    }
    @Test
    public void shouldCheckIfMemberCanNotAddNewMember() {
        // when
        fullDataMember.addMember(new Member("Zenek", "Testowy", address, "+47 555-88-88", MemberType.PRODUCER, area));

        // then
        assertThat(fullDataMember.getMemberData()).isEqualTo(new Member("Zenek", "Testowy", address, "+47 555-88-88", MemberType.PRODUCER, area));
    }

    @Test
    public void shouldCheckIfClientCanAffordWithCredit() {
        //then
        assertTrue(clientWithCredit.canAfford(Money.valueOf(200)));
        assertFalse(clientWithCredit.canAfford(Money.valueOf(201)));
    }

    @Test
    public void shouldChargeAndRechargeClient() {
        // when
        clientWithCredit.charge(Money.valueOf(200), "Testowy zakup");
        clientWithCredit.recharge(Money.valueOf(100));

        //then
        assertTrue(clientWithCredit.canAfford(Money.valueOf(100)));
        assertFalse(clientWithCredit.canAfford(Money.valueOf(101)));
    }

    @Test(expected = IllegalStateException.class)
    public void shouldNotAllowToChargeMoreThanCanAfford() {
        clientWithCredit.charge(Money.valueOf(50), "Testowy zakup");
        clientWithCredit.charge(Money.valueOf(100), "Testowy zakup");
        clientWithCredit.charge(Money.valueOf(100), "Testowy zakup");
    }

}