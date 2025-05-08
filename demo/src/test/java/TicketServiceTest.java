import com.ticket.demo.model.Ticket;
import com.ticket.demo.model.TicketRequest;
import com.ticket.demo.repository.TicketRepository;
import com.ticket.demo.service.TicketService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;



class TicketServiceTest {


    @InjectMocks
    private TicketService ticketService;

    @Mock
    private TicketRepository ticketRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGenerateTicket() {
        // Arrange
        TicketRequest ticketRequest = new TicketRequest();
        ticketRequest.setTrainNumber("12345");
        ticketRequest.setPassengerId(1L);
        ticketRequest.setSeatNumber("A1");
        ticketRequest.setTicketStatus("CONFIRMED");

        Ticket ticket = new Ticket();
        ticket.setTicketNumber(1L);
        ticket.setTrainNumber("12345");
        ticket.setPassengerId(1L);
        ticket.setSeatNumber("A1");
        ticket.setTicketStatus("CONFIRMED");

        when(ticketRepository.save(any(Ticket.class))).thenReturn(ticket);

        // Act
        Ticket result = ticketService.generateTicket(ticketRequest);

        // Assert
        assertEquals(ticket.getTicketNumber(), result.getTicketNumber());
        assertEquals(ticket.getTrainNumber(), result.getTrainNumber());
        assertEquals(ticket.getPassengerId(), result.getPassengerId());
        assertEquals(ticket.getSeatNumber(), result.getSeatNumber());
        assertEquals(ticket.getTicketStatus(), result.getTicketStatus());

        verify(ticketRepository, times(1)).save(any(Ticket.class));
    }


    @Test
    void testFetchTicketByTicketNumber() {
        // Arrange
        Long ticketNumber = 1L;
        Ticket ticket = new Ticket();
        ticket.setTicketNumber(ticketNumber);
        ticket.setTrainNumber("12345");
        ticket.setPassengerId(1L);
        ticket.setSeatNumber("A1");
        ticket.setTicketStatus("CONFIRMED");

        when(ticketRepository.findById(ticketNumber)).thenReturn(Optional.of(ticket));

        // Act
        Ticket result = ticketService.fetchTicketByTicketNumber(ticketNumber);

        // Assert
        assertEquals(ticket.getTicketNumber(), result.getTicketNumber());
        assertEquals(ticket.getTrainNumber(), result.getTrainNumber());
        assertEquals(ticket.getPassengerId(), result.getPassengerId());
        assertEquals(ticket.getSeatNumber(), result.getSeatNumber());
        assertEquals(ticket.getTicketStatus(), result.getTicketStatus());

        verify(ticketRepository, times(1)).findById(ticketNumber);
    }

    @Test
    void testFetchAllTicketsByTrain() {
        // Arrange
        String trainNumber = "12345";
        Ticket ticket1 = new Ticket();
        ticket1.setTicketNumber(1L);
        ticket1.setTrainNumber(trainNumber);
        ticket1.setPassengerId(1L);
        ticket1.setSeatNumber("A1");
        ticket1.setTicketStatus("CONFIRMED");

        Ticket ticket2 = new Ticket();
        ticket2.setTicketNumber(2L);
        ticket2.setTrainNumber(trainNumber);
        ticket2.setPassengerId(2L);
        ticket2.setSeatNumber("A2");
        ticket2.setTicketStatus("CONFIRMED");

        List<Ticket> tickets = List.of(ticket1, ticket2);

        when(ticketRepository.findByTrainNumber(trainNumber)).thenReturn(tickets);

        // Act
        List<Ticket> result = ticketService.fetchAllTicketsByTrain(trainNumber);

        // Assert
        assertEquals(2, result.size());
        assertEquals(ticket1.getTicketNumber(), result.get(0).getTicketNumber());
        assertEquals(ticket2.getTicketNumber(), result.get(1).getTicketNumber());

        verify(ticketRepository, times(1)).findByTrainNumber(trainNumber);
    }

    @Test
    void testCancelTicket_Success() {
        // Arrange
        Long ticketNumber = 1L;
        Ticket ticket = new Ticket();
        ticket.setTicketNumber(ticketNumber);
        ticket.setTicketStatus("CONFIRMED");

        when(ticketRepository.findByTicketNumber(ticketNumber)).thenReturn(Optional.of(ticket));

        // Act
        boolean result = ticketService.cancelTicket(ticketNumber);

        // Assert
        assertEquals(true, result);
        assertEquals("CANCELLED", ticket.getTicketStatus());

        verify(ticketRepository, times(1)).findByTicketNumber(ticketNumber);
        verify(ticketRepository, times(1)).save(ticket);
    }

    @Test
    void testCancelTicket_Failure() {
        // Arrange
        Long ticketNumber = 1L;

        when(ticketRepository.findByTicketNumber(ticketNumber)).thenReturn(Optional.empty());

        // Act
        boolean result = ticketService.cancelTicket(ticketNumber);

        // Assert
        assertEquals(false, result);

        verify(ticketRepository, times(1)).findByTicketNumber(ticketNumber);
        verify(ticketRepository, never()).save(any(Ticket.class));
    }



    @Test
    void testGenerateTicket_NullRequest() {
        // Arrange
        TicketRequest ticketRequest = null;

        // Act & Assert
        assertThrows(NullPointerException.class, () -> ticketService.generateTicket(ticketRequest));
    }

    @Test
    void testFetchTicketByTicketNumber_NotFound() {
        // Arrange
        Long ticketNumber = 1L;

        when(ticketRepository.findById(ticketNumber)).thenReturn(Optional.empty());

        // Act
        Ticket result = ticketService.fetchTicketByTicketNumber(ticketNumber);

        // Assert
        assertEquals(null, result);
        verify(ticketRepository, times(1)).findById(ticketNumber);
    }

    @Test
    void testFetchAllTicketsByTrain_EmptyList() {
        // Arrange
        String trainNumber = "12345";

        when(ticketRepository.findByTrainNumber(trainNumber)).thenReturn(List.of());

        // Act
        List<Ticket> result = ticketService.fetchAllTicketsByTrain(trainNumber);

        // Assert
        assertEquals(0, result.size());
        verify(ticketRepository, times(1)).findByTrainNumber(trainNumber);
    }

    @Test
    void testCancelTicket_InvalidTicketNumber() {
        // Arrange
        Long ticketNumber = 999L;

        when(ticketRepository.findByTicketNumber(ticketNumber)).thenReturn(Optional.empty());

        // Act
        boolean result = ticketService.cancelTicket(ticketNumber);

        // Assert
        assertEquals(false, result);
        verify(ticketRepository, times(1)).findByTicketNumber(ticketNumber);
        verify(ticketRepository, never()).save(any(Ticket.class));
    }

    @Test
    void testCancelAllTicketsByTrain_NoTicketsFound() {
        // Arrange
        String trainNumber = "12345";

        when(ticketRepository.findByTrainNumber(trainNumber)).thenReturn(List.of());

        // Act
        boolean result = ticketService.cancelAllTicketsByTrain(trainNumber);

        // Assert
        assertEquals(false, result);
        verify(ticketRepository, times(1)).findByTrainNumber(trainNumber);
        verify(ticketRepository, never()).saveAll(anyList());
    }


    @Test
    void testCancelAllTicketsByTrain() {
        // Arrange
        String trainNumber = "12345";
        Ticket ticket1 = new Ticket();
        ticket1.setTicketNumber(1L);
        ticket1.setTrainNumber(trainNumber);
        ticket1.setTicketStatus("CONFIRMED");

        Ticket ticket2 = new Ticket();
        ticket2.setTicketNumber(2L);
        ticket2.setTrainNumber(trainNumber);
        ticket2.setTicketStatus("CANCELLED");

        List<Ticket> tickets = List.of(ticket1, ticket2);

        when(ticketRepository.findByTrainNumber(trainNumber)).thenReturn(tickets);

        // Act
        boolean result = ticketService.cancelAllTicketsByTrain(trainNumber);

        // Assert
        assertEquals(true, result);
        assertEquals("CANCELLED", ticket1.getTicketStatus());
        assertEquals("CANCELLED", ticket2.getTicketStatus());

        verify(ticketRepository, times(1)).findByTrainNumber(trainNumber);
        verify(ticketRepository, times(1)).saveAll(tickets);
    }

}
