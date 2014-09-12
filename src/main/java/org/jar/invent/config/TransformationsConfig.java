package org.jar.invent.config;

import org.jar.invent.core.domain.converter.CategoryEntityConverter;
import org.jar.invent.core.domain.converter.CustomerEntityConverter;
import org.jar.invent.core.domain.converter.CustomerStatusEntityConverter;
import org.jar.invent.core.domain.converter.CustomerTypeEntityConverter;
import org.jar.invent.core.domain.converter.ItemEntityConverter;
import org.jar.invent.core.domain.converter.ListConverter;
import org.jar.invent.core.domain.converter.OrderStatusEntityConverter;
import org.jar.invent.core.domain.converter.OrderTypeEntityConverter;
import org.jar.invent.core.domain.converter.UnitEntityConverter;
import org.jar.invent.core.domain.converter.WorkflowEntityConverter;
import org.jar.invent.web.domain.converter.CategoryConverter;
import org.jar.invent.web.domain.converter.CustomerConverter;
import org.jar.invent.web.domain.converter.CustomerStatusConverter;
import org.jar.invent.web.domain.converter.CustomerTypeConverter;
import org.jar.invent.web.domain.converter.ItemConverter;
import org.jar.invent.web.domain.converter.OrderStatusConverter;
import org.jar.invent.web.domain.converter.OrderTypeConverter;
import org.jar.invent.web.domain.converter.UnitConverter;
import org.jar.invent.web.domain.converter.WorkflowConverter;
import org.jar.invent.web.domain.formatter.CategoryFormatter;
import org.jar.invent.web.domain.formatter.CustomerStatusFormatter;
import org.jar.invent.web.domain.formatter.CustomerTypeFormatter;
import org.jar.invent.web.domain.formatter.ItemFormatter;
import org.jar.invent.web.domain.formatter.WorkFlowFormatter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Defines formatting and conversion beans.
 * @return
 */
@Configuration
public class TransformationsConfig {

	/* FORMATTERS : Provide transformations, mainly for making more readable the information in views */
	
	@Bean public WorkFlowFormatter workFlowFormatter(){	return new WorkFlowFormatter();	}
	@Bean public ItemFormatter itemFormatter(){	return new ItemFormatter();	}
	@Bean public CategoryFormatter categoryFormatter(){	return new CategoryFormatter();	}
	@Bean public CustomerTypeFormatter customerTypeFormatter(){	return new CustomerTypeFormatter();	}
	@Bean public CustomerStatusFormatter customerStatusFormatter(){	return new CustomerStatusFormatter();	}
	
	
	/* FORMATTERS : Provide bean transformations across domains, property inspection of just one level */

	@Bean public ListConverter listConverter(){	return new ListConverter();	}
	
	@Bean public CategoryConverter categoryConverter(){		return new CategoryConverter();	}
	@Bean public CategoryEntityConverter categoryEntityConverter(){		return new CategoryEntityConverter();	}
	
	@Bean public UnitConverter unitConverter(){ 	return new UnitConverter();	}
	@Bean public UnitEntityConverter unitEntityConverter(){		return new UnitEntityConverter();	}
	
	@Bean public CustomerTypeConverter customerTypeConverter(){		return new CustomerTypeConverter();	}
	@Bean public CustomerTypeEntityConverter customerTypeEntityConverter(){	return new CustomerTypeEntityConverter();	}

	@Bean public CustomerStatusConverter customerStatusConverter(){		return new CustomerStatusConverter();	}
	@Bean public CustomerStatusEntityConverter customerStatusEntityConverter(){	return new CustomerStatusEntityConverter();	}

	@Bean public WorkflowConverter workflowConverter(){	return new WorkflowConverter();		}
	@Bean public WorkflowEntityConverter workflowEntityConverter(){	return new WorkflowEntityConverter();	}
	
	@Bean public OrderStatusConverter orderStatusConverter(){		return new OrderStatusConverter();	}
	@Bean public OrderStatusEntityConverter orderStatusEntityConverter(){		return new OrderStatusEntityConverter();	}
	
	@Bean public OrderTypeConverter orderTypeConverter(){	return new OrderTypeConverter();	}
	@Bean public OrderTypeEntityConverter orderTypeEntityConverter(){	return new OrderTypeEntityConverter();	}

	@Bean public ItemConverter itemConverter(){	return new ItemConverter();	}
	@Bean public ItemEntityConverter itemEntityConverter(){	return new ItemEntityConverter();	}

	@Bean public CustomerConverter customerConverter(){	return new CustomerConverter();	}
	@Bean public CustomerEntityConverter customerEntityConverter(){	return new CustomerEntityConverter();	}

}
