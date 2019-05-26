package jetty.example1.config;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.websocket.DeploymentException;
import javax.websocket.server.ServerContainer;
import javax.websocket.server.ServerEndpointConfig;

// @Configuration
public class AppConfig {
	@Inject
	// private WebApplicationContext context;
	private ServerContainer container;

	public class SpringServerEndpointConfigurator extends ServerEndpointConfig.Configurator {
		@Override
		public <T> T getEndpointInstance(Class<T> endpointClass) throws InstantiationException {
			// return context.getAutowireCapableBeanFactory().createBean(endpointClass);
			return null;
		}
	}

	// @Bean
	public ServerEndpointConfig.Configurator configurator() {
		return new SpringServerEndpointConfigurator();
	}

	@PostConstruct
	public void init() throws DeploymentException {
		// container = (ServerContainer) context.getServletContext().getAttribute(javax.websocket.server.ServerContainer.class.getName());
		//
		// container.addEndpoint(new AnnotatedServerEndpointConfig(BroadcastServerEndpoint.class,
		// BroadcastServerEndpoint.class.getAnnotation(ServerEndpoint.class)) {
		// @Override
		// public Configurator getConfigurator() {
		// return configurator();
		// }
		// });
	}

}