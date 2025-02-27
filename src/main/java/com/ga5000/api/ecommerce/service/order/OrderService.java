package com.ga5000.api.ecommerce.service.order;

import com.ga5000.api.ecommerce.domain.order.Order;
import com.ga5000.api.ecommerce.repository.order.OrderRepository;
import com.ga5000.api.ecommerce.repository.user.UserRepository;
import com.ga5000.api.ecommerce.service.auth.AuthService;
import com.ga5000.api.ecommerce.utils.DtoMapper;
import org.springframework.stereotype.Service;
@Service
public class OrderService implements IOrderService{
    private final OrderRepository orderRepository;
    private final AuthService authService;
    private final UserRepository userRepository;
    private final DtoMapper dtoMapper;

    public OrderService(OrderRepository orderRepository, UserRepository userRepository, AuthService authService, DtoMapper dtoMapper) {
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
        this.authService = authService;
        this.dtoMapper = dtoMapper;
    }



    void saveOrder(Order order){
        orderRepository.save(order);
    }
}
