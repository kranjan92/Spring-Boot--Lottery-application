package com.lottery.ticket.application.lotteryapplication;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.Optional;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.lottery.ticket.application.LotteryApplication;
import com.lottery.ticket.application.models.LotteryLine;
import com.lottery.ticket.application.models.Ticket;
import com.lottery.ticket.application.models.TicketModelFactory;



@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = LotteryApplication.class)
public class TicketModelFactoryTest {
	


    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Autowired
    private TicketModelFactory factory;

    @Test
    public void calculateOutcome_nullLine() throws Exception {
        exception.expect(IllegalArgumentException.class);
        factory.calculateOutcome(null);
    }

    @Test
    public void calculateOutcome_nullValues() throws Exception {
        exception.expect(IllegalArgumentException.class);
        factory.calculateOutcome(new LotteryLine());
    }

    /**
     * Expected outcome
     * 
     * if (sum = 2) --> return 10
     * if (all are same) --> 5
     * if (2nd and 3rd)!= 1sr -->1
     * else other --> 0
     * 
     *
     * @throws Exception
     */
    @Test
    public void calculateOutcome_1() throws Exception {
        LotteryLine line = new LotteryLine();
        line.setValues(new int[]{1, 2, 1});
        int outcome = factory.calculateOutcome(line);
        assertEquals(0, outcome);
    }

    /**
     * Expected outcome
     * 
     * if (sum = 2) --> return 10
     * if (all are same) --> 5
     * if (2nd and 3rd)!= 1sr -->1
     * else other --> 0
     * 
     *
     * @throws Exception
     */
    @Test
    public void calculateOutcome_2() throws Exception {
    	LotteryLine line = new LotteryLine();
        line.setValues(new int[]{1, 2, 3});
        int outcome = factory.calculateOutcome(line);
        assertEquals(1, outcome);
    }

    /**
     * Expected outcome
     * 
     * if (sum = 2) --> return 10
     * if (all are same) --> 5
     * if (2nd and 3rd)!= 1sr -->1
     * else other --> 0
     * 
     *
     * @throws Exception
     */
    @Test
    public void calculateOutcome_3() throws Exception {
    	LotteryLine line = new LotteryLine();
        line.setValues(new int[]{1, 2, 2});
        int outcome = factory.calculateOutcome(line);
        assertEquals(1, outcome);
    }

    /**
     * Expected outcome
     * 
     * if (sum = 2) --> return 10
     * if (all are same) --> 5
     * if (2nd and 3rd)!= 1sr -->1
     * else other --> 0
     * 
     *
     * @throws Exception
     */
    @Test
    public void calculateOutcome_4() throws Exception {
    	LotteryLine line = new LotteryLine();
        line.setValues(new int[]{1, 1, 1});
        int outcome = factory.calculateOutcome(line);
        assertEquals(5, outcome);
    }

    /**
     * Expected outcome
     * 
     * if (sum = 2) --> return 10
     * if (all are same) --> 5
     * if (2nd and 3rd)!= 1sr -->1
     * else other --> 0
     * 
     *
     * @throws Exception
     */
    @Test
    public void calculateOutcome_5() throws Exception {
    	LotteryLine line = new LotteryLine();
        line.setValues(new int[]{1, 1, 0});
        int outcome = factory.calculateOutcome(line);
        assertEquals(10, outcome);
    }

    /**
     * Expected outcome
     * 
     * if (sum = 2) --> return 10
     * if (all are same) --> 5
     * if (2nd and 3rd)!= 1sr -->1
     * else other --> 0
     * 
     *
     * @throws Exception
     */
    @Test
    public void calculateOutcome_6() throws Exception {
    	LotteryLine line = new LotteryLine();
        line.setValues(new int[]{0, 0, 2});
        int outcome = factory.calculateOutcome(line);
        assertEquals(10, outcome);
    }

    @Test
    public void createTicket_null() throws Exception {
        Ticket ticket = factory.createTicket(null);
        assertNotNull(ticket);
        assertEquals(0, ticket.getLines().size());
    }

    @Test
    public void createTicket_empty() throws Exception {
        Ticket ticket = factory.createTicket(Optional.empty());
        assertNotNull(ticket);
        assertEquals(0, ticket.getLines().size());
    }

    @Test
    public void createTicket() throws Exception {
        Ticket ticket = factory.createTicket(Optional.of(10));
        assertNotNull(ticket);
        assertEquals(10, ticket.getLines().size());
    }

    @Test
    public void createLine_null() throws Exception {
    	LotteryLine line = factory.createLine(null);
        assertNotNull(line);
        assertNull(line.getTicket());
    }

    @Test
    public void createLine() throws Exception {
    	LotteryLine line = factory.createLine(new Ticket());
        assertNotNull(line);
        assertNotNull(line.getTicket());
        assertEquals(1, line.getTicket().getLines().size());
    }

    @Test
    public void createLine_ticketHasNullLines() throws Exception {
        Ticket ticket = new Ticket();
        ticket.setLines(null);
        LotteryLine line = factory.createLine(ticket);
        assertNotNull(line);
        assertNotNull(line.getTicket());
        assertEquals(1, line.getTicket().getLines().size());
    }


}
