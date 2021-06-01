package com.componentxProcessing.main.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.componentxProcessing.main.client.AuthClient;
import com.componentxProcessing.main.dto.ValidatingDTO;
import com.componentxProcessing.main.exceptions.ComponentTyepNotFoundException;
import com.componentxProcessing.main.exceptions.InvalidTokenException;
import com.componentxProcessing.main.model.ProcessRequest;
import com.componentxProcessing.main.model.ProcessResponse;
import com.componentxProcessing.main.service.PaymentService;
import com.componentxProcessing.main.service.ReplacementServiceImpl;

@SpringBootTest
public class ComponentProcessingControllerTest {
	@InjectMocks
	ComponentProcessingController componentProcessingController = new ComponentProcessingController();
	@Mock
	ReplacementServiceImpl replacementServiceImplObj;
	@Mock
	AuthClient authClient;
	@Mock
	PaymentService paymentService;

	@Test
	@DisplayName("Checking for ComponentProcessingController - if it is loading or not for @GetMapping")
	void componentProcessingControllerNotNullTest() {
		assertThat(componentProcessingController).isNotNull();
	}

	/*
	 * Test for Success-full ProcessDetail
	 */
	@Test
	void testProcessDetailAPI() throws InvalidTokenException {
		ProcessRequest processRequest = new ProcessRequest(1, "satyam", 7982267437L, "798798798797987", true,
				"Accessory", "Mouse", 4);
		String token = "token";
		when(authClient.getsValidity(token)).thenReturn(new ValidatingDTO(true));
		when(replacementServiceImplObj.processService(processRequest, "token"))
				.thenReturn(new ProcessResponse("1234", 500, (double) 569, "05-06-2020"));
	}

	/*
	 * Test for FORBIDDEN ProcessDetail
	 */
	@Test
	void testProcessDetailAPIFORBIDDEN() throws InvalidTokenException {
		String token = "token";
		ProcessRequest processRequest = new ProcessRequest(1, "satyam", 7982267437L, "798798798797987", true,
				"Accessory", "Mouse", 4);
		try {

			when(authClient.getsValidity(token)).thenReturn(new ValidatingDTO(false));
			when(replacementServiceImplObj.processService(processRequest, "token"))
					.thenReturn(new ProcessResponse("1234", 500, (double) 569, "05-06-2020"));
		} catch (InvalidTokenException invalid) {

			assertEquals(403,
					componentProcessingController.processResponseDetails(processRequest, token).getStatusCodeValue());
		}
	}

	/*
	 * Test for Bad Request ProcessDetail
	 */
	@Test
	void testProcessDetailAPIBadRequest() throws InvalidTokenException {
		String token = "token";
		ProcessRequest processRequest = new ProcessRequest(1, "satyam", 7982267437L, "798798798797987", true,
				"Accessory-Unknown", "Mouse", 4);
		when(authClient.getsValidity(token)).thenReturn(new ValidatingDTO(true));
		when(replacementServiceImplObj.processService(processRequest, "token"))
				.thenThrow(ComponentTyepNotFoundException.class);
		assertEquals(500,
				componentProcessingController.processResponseDetails(processRequest, token).getStatusCodeValue());
	}

	/*
	 * Test for Success-full completeProcessing
	 */
	@Test
	void testCompleteProcessingAPI() throws InvalidTokenException {
		String token = "token";
		when(authClient.getsValidity(token)).thenReturn(new ValidatingDTO(true));
		when(paymentService.messageConfirmation("123456789", "8989898989898989", 100000, 500, token))
				.thenReturn("Operation Succesfull");
		assertEquals(200, componentProcessingController
				.messageConfirmation("123456789", "8989898989898989", 100000, 500, token).getStatusCodeValue());
	}

	/*
	 * Test for FORBIDDEN completeProcessing
	 */
	@Test
	void testCompleteProcessingAPIFORBIDDEN() throws InvalidTokenException {
		String token = "token";
		try {
			
		when(authClient.getsValidity(token)).thenReturn(new ValidatingDTO(false));
		when(paymentService.messageConfirmation("123456789", "8989898989898989", 100000, 500, token))
				.thenReturn("Operation Succesfull");
		}
		catch (InvalidTokenException invalid) {
			assertEquals(403, componentProcessingController
					.messageConfirmation("123456789", "8989898989898989", 100000, 500, token).getStatusCodeValue());	
		}
		
	}

}