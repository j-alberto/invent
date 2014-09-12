package org.jar.invent.web.controller;

import org.jar.invent.config.PersistanceJPAConfig;
import org.jar.invent.config.SpringApplicationContext;
import org.jar.invent.core.service.CatalogsService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static junit.framework.TestCase.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={PersistanceJPAConfig.class, SpringApplicationContext.class})
public class ItemsTest {

	@Autowired
	CatalogsService catalogsService;

	@Test
	public void shouldGetFirstItems(){
		assertEquals(5, catalogsService.getUnitDataTypes().size());
	}
}
