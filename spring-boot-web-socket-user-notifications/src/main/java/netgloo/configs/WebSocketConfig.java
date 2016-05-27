package netgloo.configs;

import org.springframework.context.annotation.Configuration;
// import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;

/**
 * Enable and configure Stomp over WebSocket.
 */
@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig extends AbstractWebSocketMessageBrokerConfigurer {

  /**
   * Register Stomp endpoints: the url to open the WebSocket connection.
   */
  @Override
  public void registerStompEndpoints(StompEndpointRegistry registry) {
    
    // Register the "/ws" endpoint, enabling the SockJS protocol.
    // SockJS is used (both client and server side) to allow alternative 
    // messaging options if WebSocket is not available.
    registry.addEndpoint("/ws").withSockJS();
    
    return;
  }

//  /**
//   * Configure the message broker.
//   */
//  @Override
//  public void configureMessageBroker(MessageBrokerRegistry config) {
//
//    // Enable a simple memory-based message broker to send messages to the
//    // client on destinations prefixed with "/queue".
//    // Simple message broker handles subscription requests from clients, stores
//    // them in memory, and broadcasts messages to connected clients with 
//    // matching destinations.
//    config.enableSimpleBroker("/queue");
//
//    return;
//  }

} // class WebSocketConfig
