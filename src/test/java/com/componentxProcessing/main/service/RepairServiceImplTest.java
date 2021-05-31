package com.componentxProcessing.main.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.componentxProcessing.main.client.PackagingClient;
import com.componentxProcessing.main.client.PaymentClient;
import com.componentxProcessing.main.dto.PackagingAndDeliveryDTO;
import com.componentxProcessing.main.model.ProcessRequest;
import com.componentxProcessing.main.model.ProcessResponse;
import com.componentxProcessing.main.repository.ProcessRequestRepo;
import com.componentxProcessing.main.repository.ProcessResponseRepo;

@SpringBootTest
class RepairServiceImplTest {

	@InjectMocks
	RepairServiceImpl repairServiceImpl;
		@Mock
	private PackagingClient packagingClient;
	@Mock
	private PaymentClient paymentClient;

	@Mock
	private ProcessResponse processResponseObj;
	/*
	 * @Test void test() { pass("Not yet implemented"); }
	 */

	@Test
	public void testProcessServiceSuccess() {
		ProcessRequest processRequestObj = new ProcessRequest(0, "shivam", 9876543210L, "8989898989898989", true, "Integral", "mouse", 5);
		String token = "token";
		when(processResponseObj).thenReturn(new ProcessResponse());
		when(packagingClient.save(processRequestObj.getComponentType(), processRequestObj.getQuantity(), token)).thenReturn(new PackagingAndDeliveryDTO(25.0));
		ProcessResponse processResponseObj = repairServiceImpl.processService(processRequestObj, token);
		System.out.println(processResponseObj.getPackagingAndDeliveryCharge());
		assertEquals(25.0, processResponseObj.getPackagingAndDeliveryCharge());
	}
}
