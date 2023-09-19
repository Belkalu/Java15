package ru.netology.manager;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.domain.Ticket;
import ru.netology.domain.TicketTimeComparator;

import java.util.Comparator;

public class AviaSoulsTest {
    AviaSouls manager = new AviaSouls();
    Ticket ticket1 = new Ticket("airport1", "airport2", 10_000, 8, 12);
    Ticket ticket2 = new Ticket("airport1", "airport2", 15_000, 10, 14);
    Ticket ticket3 = new Ticket("airport1", "airport2", 12_000, 22, 4);
    Ticket ticket4 = new Ticket("airport2", "airport3", 8_000, 11, 20);
    Ticket ticket5 = new Ticket("airport2", "airport3", 10_000, 9, 17);
    Ticket ticket6 = new Ticket("airport3", "airport1", 9_000, 10, 14);
    Ticket ticket7 = new Ticket("airport3", "airport1", 11_000, 22, 4);
    Ticket ticket8 = new Ticket("airport3", "airport1", 13_000, 13, 17);
    Ticket ticket9 = new Ticket("airport2", "airport4", 7_000, 8, 13);

    @BeforeEach
    public void add() {
        manager.add(ticket1);
        manager.add(ticket2);
        manager.add(ticket3);
        manager.add(ticket4);
        manager.add(ticket5);
        manager.add(ticket6);
        manager.add(ticket7);
        manager.add(ticket8);
        manager.add(ticket9);
    }

    @Test
    public void sortingByPriceTicket() {
        Ticket[] actual = manager.search("airport1", "airport2");
        Ticket[] expected = {ticket1, ticket3, ticket2};

        Assertions.assertArrayEquals(actual, expected);
    }

    @Test
    public void notSearchSortingByPriceTicket() {
        Ticket[] actual = manager.search("airport1", "airport4");
        Ticket[] expected = {};

        Assertions.assertArrayEquals(actual, expected);
    }

    @Test
    public void findOneTicketPriceSorting() {
        Ticket[] actual = manager.search("airport2", "airport4");
        Ticket[] expected = {ticket9};

        Assertions.assertArrayEquals(actual, expected);
    }

    @Test
    public void shouldCompareToBigPriceTicket() {
        System.out.println(ticket1.compareTo(ticket2));

        int actual = ticket1.compareTo(ticket2);
        int expected = -1;

        Assertions.assertEquals(actual, expected);
    }

    @Test
    public void shouldCompareToSmallerPriceTicket() {
        System.out.println(ticket1.compareTo(ticket6));

        int actual = ticket1.compareTo(ticket6);
        int expected = 1;

        Assertions.assertEquals(actual, expected);
    }

    @Test
    public void shouldCompareToEqualPriceTicket() {
        System.out.println(ticket1.compareTo(ticket5));

        int actual = ticket1.compareTo(ticket5);
        int expected = 0;

        Assertions.assertEquals(actual, expected);
    }

    @Test
    public void findComparatorTimeTicket() {
        Comparator<Ticket> comparator = new TicketTimeComparator();

        Ticket[] actual = manager.searchAndSortBy("airport1", "airport2", comparator);
        Ticket[] expected = {ticket1, ticket2, ticket3};

        Assertions.assertArrayEquals(actual, expected);
    }

    @Test
    public void notFindComparatorTimeTicket() {
        Comparator<Ticket> comparator = new TicketTimeComparator();

        Ticket[] actual = manager.searchAndSortBy("airport3", "airport4", comparator);
        Ticket[] expected = {};

        Assertions.assertArrayEquals(actual, expected);
    }
}