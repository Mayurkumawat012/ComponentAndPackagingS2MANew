package com.componentxProcessing.main.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.componentxProcessing.main.client.PaymentClient;
import com.componentxProcessing.main.dto.PackagingAndDeliveryDTO;

@SpringBootTest
public class PaymentServiceTest {
   
	@InjectMocks
	PaymentService paymentService;
	
	@Mock
	PaymentClient paymentClient;
	
	@Test
	@DisplayName("Checking for ProcessRequestService - if it is loading or not for @GetMapping")
	 void repairServiceImplNotNullTest() {
		assertThat(paymentService).isNull();
	}
	
	@Test
	public void testMessageConfirmation() {
		when(paymentClient.paymentDetails("requestId", "creditCardNumber", 100000, 500, "token")).thenReturn(new PackagingAndDeliveryDTO(0.0));
		String message = paymentService.messageConfirmation("requestId", "creditCardNumber", 100000, 500, "token");
		assertEquals("Operation Not Successful", message);
	}
}
