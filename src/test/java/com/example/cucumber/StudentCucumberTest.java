package com.example.cucumber;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.maven.Maven;
import org.junit.runner.RunWith;

import cucumber.runtime.arquillian.CukeSpace;
import cucumber.runtime.arquillian.api.Features;
import cucumber.runtime.arquillian.api.Glues;

@Glues({StudentCucumberSteps.class})
@Features({"features/1_add_student.feature",
		   "features/2_update_student.feature",
		   "features/3_find_student.feature",
		   "features/4_find_all_students.feature",
		   "features/5_delete_student.feature"
		   })
@RunWith(CukeSpace.class)
public class StudentCucumberTest {
	@Deployment
	public static WebArchive createDeployment() {
		return ShrinkWrap.create(WebArchive.class, "tomeeembedded.war")
				 .addPackages(true, "com.example")
				 .addAsWebInfResource("beans.xml")
				 .addAsResource("META-INF/test-persistence.xml", "META-INF/persistence.xml")
				 .addAsResource("test.properties")
				 .addAsResource("base.properties")
				 .addAsLibraries(Maven.resolver().resolve("org.tomitribe:sabot:0.9").withTransitivity().asFile());
	}
	
}
