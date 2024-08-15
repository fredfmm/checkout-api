package com.fred.checkoutapi.features.v1.checkout

import com.fred.checkoutapi.client.PaymentClient
import com.fred.checkoutapi.model.request.CheckoutRequest
import com.fred.checkoutapi.model.response.request.PaymentResponse
import org.junit.Before
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.test.context.ActiveProfiles
import spock.lang.Specification
import static org.mockito.Mockito.when
import static org.mockito.ArgumentMatchers.anyString

@SpringBootTest
@ActiveProfiles("it")
class CheckoutServiceIT extends Specification {

    @Autowired
    CheckoutService checkoutService

    @MockBean
    PaymentClient paymentClient

    @Before
    void setup() {
        when(paymentClient.processPayment(anyString())).thenReturn(Optional.of(PaymentResponse.builder().message("teste").success(true).build()))
    }

    def "should find checkout by ID"() {
        given: "a checkout with a specific ID"
        def request = CheckoutRequest.builder()
                .customerEmail("customer@example.com")
                .customerName("John Doe")
                .deliveryAddress("123 Main St")
                .productId(UUID.randomUUID())
                .quantity(1)
                .build()

        and: "the checkout is stored in the repository"
        def checkout = checkoutService.createOrder(request)

        when: "finding checkout by ID"
        def result = checkoutService.findCheckoutById(checkout.getId())

        then: "the checkout should be returned"
        result.isPresent()
    }

}
