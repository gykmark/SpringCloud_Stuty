package cn.itcast.order.service;

import cn.itcast.order.mapper.OrderMapper;
import cn.itcast.order.pojo.Order;
import cn.itcast.order.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class OrderService {

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private RestTemplate restTemplate;

    public Order queryOrderById(Long orderId) {
        // 1.查询订单
        Order order = orderMapper.findById(orderId);
        //2.填写url和发起http请求实现远程调用
        String url = "http://localhost:8081/user/"+order.getUserId();
        System.out.println(order.getUserId());
        User user = restTemplate.getForObject(url, User.class);
        //3.封装获得的user对象
        order.setUser(user);
        // 4.返回
        return order;
    }
}
