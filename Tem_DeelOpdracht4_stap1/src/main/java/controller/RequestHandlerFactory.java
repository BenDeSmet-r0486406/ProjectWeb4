package controller;

import domain.PersonService;
import org.reflections.Reflections;
import view.handlers.Mapping;
import view.handlers.RequestHandler;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class RequestHandlerFactory {

	private Map<String, RequestHandler> handlers = new HashMap<>();

	public RequestHandlerFactory(PersonService model) throws InstantiationException, IllegalAccessException{
		Reflections reflections = new Reflections("view.handlers");
		Set<Class<?>> annotated = reflections.getTypesAnnotatedWith(Mapping.class);
		for (Class<?> clazz : annotated) {
			RequestHandler handler = (RequestHandler) clazz.newInstance();
			if(handler != null){
				handler.setModel(model);
				handlers.put(clazz.getAnnotation(Mapping.class).value(), handler);
			}
		}
	}

	public RequestHandler create(String type){
		return handlers.get(type);
	}


}
